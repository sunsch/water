package com.framework.auto.util;

import java.io.UnsupportedEncodingException;

import com.framework.auto.consts.Consts;

public class EncodUtil {
	
	public static String defautEncod(String txt)
	{
		return transform(Consts.OS_FILE_ENCODING,Consts.WEB_PAGE_ENCODING,txt);
	}
	public static String transform(String encodFrom, String encodTo, String txt)
	{
		String encodNotSupport="encod not support";
		try {
			return new String(txt.getBytes(encodTo), encodFrom);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return encodNotSupport;
		}
	}

}
