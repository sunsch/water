package com.framework.auto.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UIType 
{
	//the value will mark the field's UI type in the page
	String value();

}
