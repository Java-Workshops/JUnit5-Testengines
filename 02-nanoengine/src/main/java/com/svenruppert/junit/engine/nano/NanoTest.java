package com.svenruppert.junit.engine.nano;

import org.junit.platform.commons.annotation.Testable;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Testable //for IDE support
public @interface NanoTest { }
