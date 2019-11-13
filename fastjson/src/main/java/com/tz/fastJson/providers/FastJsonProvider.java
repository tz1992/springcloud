package com.tz.fastJson.providers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.HashSet;

import javax.annotation.Priority;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.util.DelegatingOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.tz.fastJson.util.BeanContext;
import com.tz.fastJson.util.ClassKey;

@Provider
@Consumes({"application/json", "text/json"})
@Produces({"application/json", "text/json"})
@Priority(6000)
public class FastJsonProvider extends Object
    implements
      MessageBodyReader<Object>,
      MessageBodyWriter<Object> {
  private boolean addNoSniffHeader = false;
  public static final HashSet<ClassKey> _untouchables;
  protected static final HashSet<ClassKey> DEFAULT_UNTOUCHABLES = new HashSet<ClassKey>();
  public static final Class<?>[] DEFAULT_UNREADABLES;
  public static final Class<?>[] DEFAULT_UNWRITABLES;
  public static final Class<?>[] _unreadableClasses;
  public static final Class<?>[] _unwritableClasses;
  private static final Charset UTF8 = Charset.forName("UTF-8");
  private static Logger logger=LoggerFactory.getLogger(FastJsonProvider.class);

  static {
    DEFAULT_UNTOUCHABLES.add(new ClassKey(InputStream.class));
    DEFAULT_UNTOUCHABLES.add(new ClassKey(Reader.class));
    DEFAULT_UNTOUCHABLES.add(new ClassKey(OutputStream.class));
    DEFAULT_UNTOUCHABLES.add(new ClassKey(Writer.class));
    DEFAULT_UNTOUCHABLES.add(new ClassKey(char[].class));
    DEFAULT_UNTOUCHABLES.add(new ClassKey(String.class));
    DEFAULT_UNTOUCHABLES.add(new ClassKey(byte[].class));
    DEFAULT_UNREADABLES = new Class[] {InputStream.class, Reader.class};
    DEFAULT_UNWRITABLES = new Class[] {InputStream.class, OutputStream.class, Writer.class,
        StreamingOutput.class, Response.class};
    _untouchables = DEFAULT_UNTOUCHABLES;
    _unreadableClasses = DEFAULT_UNREADABLES;
    _unwritableClasses = DEFAULT_UNWRITABLES;
  }



  @Override
  public boolean isWriteable(Class<?> clazz, Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    if (!hasMatchingMediaType(mediaType)) {
      return false;
    }



    if (_isIgnorableForWriting(new ClassKey(clazz))) {
      return false;
    }

    for (Class<?> cls : _unwritableClasses) {
      if (cls.isAssignableFrom(clazz)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public long getSize(Object t, Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    return -1L;
  }

  @Override
  public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream out)
      throws IOException, WebApplicationException {
    
      logger.info("=============writeTo方法开始执行================");
    DelegatingOutputStream delegatingOutputStream = new DelegatingOutputStream(out) {
      public void flush() {}
    };

    this.modifyHeaders(value, type, genericType, annotations, httpHeaders);
    int len = BeanContext.getJsonConversionService().writeTo(delegatingOutputStream, value);
    httpHeaders.add("Content-Length", Integer.valueOf(len));

    delegatingOutputStream.flush();
  }

  @Override
  public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    if (!hasMatchingMediaType(mediaType)) {
      return false;
    }
    


    if (_isIgnorableForReading(new ClassKey(type))) {
      return false;
    }

    for (Class<?> cls : _unreadableClasses) {
      if (cls.isAssignableFrom(type)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations,
      MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream in)
      throws IOException, WebApplicationException {
    return JSON.parseObject(in, UTF8, genericType, new Feature[0]);
  }

  protected void modifyHeaders(Object value, Class<?> type, Type genericType,
      Annotation[] annotations, MultivaluedMap<String, Object> httpHeaders) throws IOException {
    if (this.addNoSniffHeader) {
      httpHeaders.add("X-Content-Type-Options", "nosniff");
    }
  }

  protected boolean hasMatchingMediaType(MediaType mediaType) {
    if (mediaType == null) {
      return true;
    } else {
      String subtype = mediaType.getSubtype();
      return "json".equalsIgnoreCase(subtype) || subtype.endsWith("+json")
          || "javascript".equals(subtype) || "x-javascript".equals(subtype)
          || "x-json".equals(subtype);
    }
  }

  protected boolean _isIgnorableForWriting(ClassKey typeKey) {
    return _untouchables.contains(typeKey);
  }


  protected boolean _isIgnorableForReading(ClassKey typeKey) {
    return _untouchables.contains(typeKey);
  }

}
