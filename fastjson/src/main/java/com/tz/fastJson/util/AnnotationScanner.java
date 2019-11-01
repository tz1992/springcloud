package com.tz.fastJson.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;

import com.tz.fastJson.entity.POJO;

import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

public abstract class AnnotationScanner<A extends Annotation> {


  public void field(final Object value, final Class<A> annotationClass) {
    if (value == null) {
      return;
    }
    Class<?> clazz = value.getClass();

    if (POJO.class.isAssignableFrom(clazz)) {
      ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
          A a = (A) field.getAnnotation(annotationClass);
          ReflectionUtils.makeAccessible(field);
          Object v = ReflectionUtils.getField(field, value);
          if (a != null && v != null) {
            AnnotationScanner.this.record(field, v, a);
          } else {
            AnnotationScanner.this.field(v, annotationClass);
          }

        }
      });
    } else if (clazz.isArray()) {

      Class<?> componentType = clazz.getComponentType();
      if (POJO.class.isAssignableFrom(componentType)
          && Collection.class.isAssignableFrom(componentType) && componentType.isArray()) {
        Object[] arr = ObjectUtils.toObjectArray(value);
        Arrays.stream(arr).filter(v -> (v != null)).forEach(v -> field(v, annotationClass));
      }
    } else if (Collection.class.isAssignableFrom(clazz)) {

      ((Collection) value).stream().filter(v -> (v != null))
          .forEach(v -> field(v, annotationClass));
    }
  }



  static final ReflectionUtils.FieldFilter ABLE_FIELDS = new ReflectionUtils.FieldFilter() {
    public boolean matches(Field field) {
      return (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers()));
    }
  };

  protected abstract void record(Field paramField, Object paramObject, A paramA);
}
