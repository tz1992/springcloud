package com.tz.fastJson.service;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

public interface JsonConversionService {
  String toJSONString(Object var1);

  int writeTo(OutputStream var1, Object var2) throws IOException;

  <T> T parseObject(Type var1, byte[] var2);

  <T> T parseObject(Type var1, String var2);
}