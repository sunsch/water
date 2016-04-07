package com.framework.auto.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SearchMarker
{

	//the value will mark if the field will be used as search key in the page or not
	boolean value();
}
