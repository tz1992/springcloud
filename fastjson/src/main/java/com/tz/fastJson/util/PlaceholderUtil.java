package com.tz.fastJson.util;

import java.util.Arrays;

public class PlaceholderUtil {
  public static int createHashCode(String... keys) {
    int prime = 31;
    int result = 1;
    return 31 * result + Arrays.hashCode(keys);
  }



  public static String createJsonPlaceholder(String... keys) {
    return String.valueOf(createHashCode(keys));
  }
}
