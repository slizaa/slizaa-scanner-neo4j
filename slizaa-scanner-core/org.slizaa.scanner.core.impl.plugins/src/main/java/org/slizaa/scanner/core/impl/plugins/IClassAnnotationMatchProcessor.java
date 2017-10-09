package org.slizaa.scanner.core.impl.plugins;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 */
public interface IClassAnnotationMatchProcessor {

  Class<? extends Annotation> getAnnotationToMatch();

  void consume(Object codeSource, List<Class<?>> classesWithAnnotation, Class<?> codeSourceType,
      ClassLoader classLoader);
}
