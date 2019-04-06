package com.yuandian.server.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author twjitm 2019/4/6/21:31
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageAnnotation {
     short cmd ();
}
