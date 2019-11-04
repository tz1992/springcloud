package com.tz.fastJson.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.tz.fastJson.entity.Pair;
import com.tz.fastJson.util.PlaceholderUtil;

public class LookupData {
  
  public static List<Pair> getLookupData1(Set<Pair>ids){
    
    HashMap<String, String>map=new HashMap<>();
    map.put("01", "武汉");    
    map.put("02", "南京");
    List<Pair> list = new ArrayList<Pair>(ids.size());
     for(Pair pair:ids){
       list.add(new Pair(PlaceholderUtil.createJsonPlaceholder(new String[]{pair.getKey(),pair.getValue()}), map.get(pair.getValue())));
     }
     
     return list;
  }

  public static List<Pair> getLookupData(Set<Serializable> ids) {
    // TODO Auto-generated method stub
    return null;
  }

}
