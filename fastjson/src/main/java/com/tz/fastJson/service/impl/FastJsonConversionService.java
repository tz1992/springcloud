package com.tz.fastJson.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tz.fastJson.filter.MyAfterFilter;
import com.tz.fastJson.service.JsonConversionService;
import com.tz.fastJson.util.BeanContext;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class FastJsonConversionService implements JsonConversionService {
    public static final Charset UTF8 = Charset.forName("UTF-8");
    private SerializeConfig serializeConfig = SerializeConfig.getGlobalInstance();
    private final SerializerFeature[] features;
    @Autowired(required = false)
    private List<MyAfterFilter<?>> extendedAttributeFilters;

    public FastJsonConversionService(SerializerFeature[] feature) {
        this.features = feature;
    }

    public SerializerFeature[] getFeatures() {
        return this.features;
    }

    public String toJSONString(Object object) {
        SerializeWriter out = new SerializeWriter();

        String var4;
        try {
            JSONSerializer serializer = this.createSerializer(out);
            serializer.write(object);
            var4 = out.toString();
        } finally {
            out.close();
        }

        return var4;
    }

    public int writeTo(OutputStream out, Object object) throws IOException {
        SerializeWriter writer = new SerializeWriter();
        List<MyAfterFilter> list = BeanContext.getBeansOfType(MyAfterFilter.class);
        list.forEach((t) -> {
            t.reset();
            t.preload(object);
            t.loadInfo();
        });

        int var6;
        try {
            JSONSerializer serializer = this.createSerializer(writer);
            serializer.write(object);
            var6 = writer.writeToEx(out, UTF8);
        } finally {
            writer.close();
        }

        return var6;
    }

    private JSONSerializer createSerializer(SerializeWriter out) {
        JSONSerializer serializer = new JSONSerializer(out, this.serializeConfig);
        if (this.features != null) {
            SerializerFeature[] var3 = this.features;
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                SerializerFeature feature = var3[var5];
                serializer.config(feature, true);
            }
        }

        if (this.extendedAttributeFilters != null) {
            serializer.getAfterFilters().addAll(this.extendedAttributeFilters);
        }

        return serializer;
    }

    public <T> T parseObject(Type type, byte[] input) {
        return JSON.parseObject(input, type, new Feature[0]);
    }

    public <T> T parseObject(Type type, String input) {
        return JSON.parseObject(input, type, new Feature[0]);
    }
}