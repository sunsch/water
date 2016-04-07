package com.framework.auto.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SysDateMarker {

	//the value will mark the field will be set with the system date and will be not processed by users
	boolean value();
}
