package com.tz.fastJson.util;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaceholderUtil {
  
    private static Logger logger= LoggerFactory.getLogger(PlaceholderUtil.class);
  public static int createHashCode(String... keys) {
    int prime = 31;
    int result = 1;
    return 31 * result + Arrays.hashCode(keys);
  }



  public static String createJsonPlaceholder(String... keys) {
    logger.info(String.valueOf(createHashCode(keys)));
    return String.valueOf(createHashCode(keys));
  }
}
