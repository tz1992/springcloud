package com.tz.fastJson.filter;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.validator.internal.util.ConcurrentReferenceHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import com.alibaba.fastjson.serializer.AfterFilter;
import com.tz.fastJson.entity.POJO;
import com.tz.fastJson.util.AnnotationScanner;
import com.tz.fastJson.util.ClassKey;

public abstract class MyAfterFilter<T extends Annotation> extends AfterFilter {

  private final ThreadLocal<Set<Serializable>> tls;
  private final ThreadLocal<Map<String, Object>> tlinfo;
  protected final Logger log ;
  private final Set<ClassKey> ignoreClassCaches ;
  private final Map<ClassKey, Field[]> fieldsCache ;
  private final Class<T> filterClazz;

  public final void reset() {
    ((Set) this.tls.get()).clear();
    ((Map) this.tlinfo.get()).clear();
  }

  public final void loadInfo() {
    Set<Serializable> set = (Set) this.tls.get();
    Map<String, Object> map = (Map) this.tlinfo.get();
    if (set != null && !set.isEmpty()) {
      this.loadInfo(set, map);
    }
  }

  protected abstract void loadInfo(Set<Serializable> var1, Map<String, Object> var2);

  protected final void preload(Object obj) {
    AnnotationScanner<T> scanner = new AnnotationScanner<T>() {

      @Override
      protected void record(Field field, Object value, T a) {
        MyAfterFilter.this.preload(field, value, a);
      }

    };
    scanner.field(obj, this.filterClazz);
  }

  protected abstract void preload(Field var1, Object var2, T var3);

  protected final void add(Serializable... ids) {
    Set<Serializable> set = (Set) this.tls.get();
    Serializable[] var3 = ids;
    int var4 = ids.length;

    for (int var5 = 0; var5 < var4; ++var5) {
      Serializable id = var3[var5];
      set.add(id);
    }

  }



  protected MyAfterFilter(Class<T> filterClazz) {
    this.tls = new ThreadLocal<Set<Serializable>>() {
      protected Set<Serializable> initialValue() {
        return new HashSet();
      }
    };
    this.tlinfo = new ThreadLocal<Map<String, Object>>() {
      protected Map<String, Object> initialValue() {
        return new HashMap();
      }
    };
    this.log = LoggerFactory.getLogger(getClass());
    this.ignoreClassCaches = new HashSet(256);

    this.fieldsCache = new ConcurrentReferenceHashMap(256);



    this.filterClazz = filterClazz;
  }


  protected boolean canFilter(ClassKey key) {
    if (this.ignoreClassCaches.contains(key)) {
      return false;
    } else if (this.fieldsCache.containsKey(key)) {
      return true;
    } else {
      synchronized (this) {
        return this.init(key);
      }
    }
  }

  protected boolean init(ClassKey key) {
    if (this.ignoreClassCaches.contains(key)) {
      return false;
    } else if (this.fieldsCache.containsKey(key)) {
      return true;
    } else {
      Class<?> clazz = key.getClazz();
      if (!this.supports(clazz)) {
        this.log.info("not supports class '{}' for '@{}'", key, this.filterClazz.getSimpleName());
        this.ignoreClassCaches.add(key);
        return false;
      } else {
        List<Field> fields = new ArrayList(6);
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
          public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
            if (AnnotationUtils.getAnnotation(field, MyAfterFilter.this.filterClazz) != null) {
              ReflectionUtils.makeAccessible(field);
              fields.add(field);
            }
          }
        });
        if (fields.isEmpty()) {
          this.log.info("not supports class '{}'", key);
          this.ignoreClassCaches.add(key);
          return false;
        } else {
          this.fieldsCache.put(key, fields.toArray(new Field[fields.size()]));
          return true;
        }
      }
    }
  }

  public boolean supports(Class<?> clazz) {
    return ClassUtils.isAssignable(POJO.class, clazz);
  }

  public final void writeAfter(Object object) {
    if (object != null) {
      ClassKey key = new ClassKey(object.getClass());
      if (this.canFilter(key)) {
        Field[] fields = (Field[]) this.fieldsCache.get(key);
        if (fields == null) {
          this.log.warn("class '{}' has no fields to filter,but what happened?", key);
        } else {
          this.doWrite(fields, object);
        }
      }
    }
  }

  protected final void doWrite(Field[] fields, Object object) {
    Map<String, Object> map = (Map) this.tlinfo.get();
    Field[] var4 = fields;
    int var5 = fields.length;

    for (int var6 = 0; var6 < var5; ++var6) {
      Field field = var4[var6];
      T t = AnnotationUtils.getAnnotation(field, this.filterClazz);
      Object value = ReflectionUtils.getField(field, object);
      if (value != null) {
        this.doWrite(map, field, value, t);
      }
    }

  }

  protected abstract void doWrite(Map<String, Object> var1, Field var2, Object var3, T var4);

}
