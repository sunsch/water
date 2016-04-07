package com.framework.auto.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface LargeObjMarker 
{
	//the value will mark if the field is collect or other entity object
	boolean value();

}
