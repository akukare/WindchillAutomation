package com.itc.utilities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Retain annotation at runtime to allow reflection
@Retention(RetentionPolicy.RUNTIME)
// Applicable on methods only
@Target(ElementType.METHOD)
public @interface TestInfo {
    String FunctionalArea() default "";
    String Owner() default "";
    String[] Tags() default {};
    String TestCaseID() default "";
    String Description() default "";
}
