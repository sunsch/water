package com.framework.auto.util;

import java.lang.reflect.Field;
import java.util.Vector;

import com.framework.auto.anno.IDMarker;
import com.framework.auto.anno.LargeObjMarker;
import com.framework.auto.anno.ShowMarker;
import com.framework.auto.anno.ZnValue;
import com.framework.auto.path.Path;

public class ReflectUtil 
{

	/*
	 * 	return the fields which marked as show. if no ShowMarker or ShowMarker=false, it will 
	 *  not be showed in the pages.
	 */
	public static Vector<Field> calVisibles(String entyObjClzName)
	{
		Vector<Field> visible=new Vector<Field>(10);
		String packo=PathUtil.calPackage(Path.ENTITY_PATH)+"."+entyObjClzName;
		
		Class<?> c;
		try {
			c = Class.forName(packo);

			Field fs[] = c.getDeclaredFields();
			for(Field f:fs)
			{
				if(f.getAnnotation(ShowMarker.class)!=null)
				{
					if(f.getAnnotation(ShowMarker.class).value())
					{
						visible.add(f);
					}
					
				}
			}

		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return visible;
	}
	/*
	 * 	return the fields which marked as largeObject
	 */
	public static Vector<Field> calLargeObj(String entyObjClzName)
	{
		Vector<Field> large=new Vector<Field>(10);
		String packo=PathUtil.calPackage(Path.ENTITY_PATH)+"."+entyObjClzName;
		Class<?> c;
		try {
			c = Class.forName(packo);

			Field fs[] = c.getDeclaredFields();
			for(Field f:fs)
			{
				if(f.getAnnotation(LargeObjMarker.class)!=null)
				{
					large.add(f);
				}
			}
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return large;
	}
	/*
	 * 	return the fields which marked as id
	 */
	public static String calid(String entyObjClzName)
	{
		String packo=PathUtil.calPackage(Path.ENTITY_PATH)+"."+entyObjClzName;
		Class<?> c;
		try {
			c = Class.forName(packo);

			Field fs[] = c.getDeclaredFields();
			//first find IDMarker as its ID
			for(Field f:fs)
			{
				if(f.getAnnotation(IDMarker.class)!=null)
				{
					return f.getName();
				}
			}
			//if can not find IDMarker, use the field which its name end with 'id'
			for(Field f:fs)
			{
				if(isInt(f))
				{
					if(f.getName().endsWith("id"))
					return f.getName();
				}
			}
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		//last, if nothing matches, return NO_ID
		return "NO_ID";
	}
	/*
	 * 	return the fields which is string type
	 */
	public static boolean isString(Field fc)
	{
		return fc.getType().getName().equals("java.lang.String");
			
	}
	/*
	 * 	return the fields which is int type
	 */
	public static boolean isInt(Field fc)
	{
		return fc.getType().getName().equals("java.lang.Integer")||fc.getType().getName().equals("int");
			
	}
	/*
	 * 	return the fields which is date type
	 */
	public static boolean isDate(Field fc)
	{
		return fc.getType().getName().equals("java.util.Date")||fc.getType().getName().equals("java.sql.Date");
			
	}
	/*
	 * 	return the field's zn value, the field should have ZnValue annotation
	 */
	public static String znValue(Field f)
	{
		ZnValue zn=f.getAnnotation(ZnValue.class);
		return zn==null?f.getName():zn.value();
	}
}
