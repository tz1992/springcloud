package com.tz.fastJson.filter;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import com.tz.fastJson.annotation.Lookup;
import com.tz.fastJson.data.LookupData;
import com.tz.fastJson.entity.Pair;
import com.tz.fastJson.util.PlaceholderUtil;


public class LookupFilter extends MyAfterFilter<Lookup> {
  
  private static Logger logger= LoggerFactory.getLogger(LookupFilter.class);

  
  @Autowired
  public ConversionService conversionService;

  public LookupFilter(Class<Lookup> filterClazz) {
    super(Lookup.class);
  }

  @Override
  protected void loadInfo(Set<Serializable> ids, Map<String, Object> map) {
    
    List<Pair> list = LookupData.getLookupData(ids);
    logger.info(ids.toString());
    if (list != null && !list.isEmpty()) {
        list.forEach((p) -> {
            map.put(p.getKey(), p.getValue());
        });
    }
    
  }

  @Override
  protected void preload(Field field, Object value, Lookup l) {
//    String valueStr = (String) this.conversionService.convert(value, String.class);
    String valueStr=value.toString();
    Arrays.asList(valueStr.split(",")).forEach((t) -> {
        if (StringUtils.isNotBlank(t)) {
            Pair pair = new Pair(l.type(), t);
            this.add(new Serializable[]{pair});
        }

    });
    
  }

  @Override
  protected void doWrite(Map<String, Object> map, Field field, Object value, Lookup l) {
//    String valueStr = (String) this.conversionService.convert(value, String.class);
    String valueStr=value.toString();
    String lookupName = field.getName() + "Desp";
    List<String> hashCode = new ArrayList<String>();
    Arrays.asList(valueStr.split(",")).forEach((t) -> {
        String key = PlaceholderUtil.createJsonPlaceholder(new String[]{l.type(), t});
        Object obj = map.get(key);
        if (obj != null) {
            hashCode.add(obj.toString());
        }

    });
    String lookupDesp = ((String) hashCode.stream().collect(Collectors.joining("->"))).toString();
    this.writeKeyValue(lookupName, lookupDesp);
    
  }

}
