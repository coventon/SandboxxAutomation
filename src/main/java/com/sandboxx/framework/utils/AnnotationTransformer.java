package com.sandboxx.framework.utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class AnnotationTransformer implements IAnnotationTransformer {

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,Method testMethod){

        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
