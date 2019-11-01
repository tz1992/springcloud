package com.tz.fastJson.data;

import java.util.ArrayList;
import java.util.List;

import com.tz.fastJson.entity.Pair;

public class LookupData {
  
  public static List<Pair> getLookupData(){
    List<Pair> list=new ArrayList<Pair>();
    Pair pair=new Pair("01", "武汉");
    Pair pair1=new Pair("02", "南京");
    list.add(pair);
    list.add(pair1);
    return list;
  }

}
