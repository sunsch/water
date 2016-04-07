package com.framework.auto.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ShowMarker 
{

	//the value will mark if the field will be showed in the page or not
	boolean value();
}
