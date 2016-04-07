package com.framework.auto.test;

	
	import java.util.Map;
	import java.util.HashMap;
import java.lang.reflect.Field;
	import java.lang.reflect.Method;
	import java.lang.annotation.Annotation;
	import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.framework.auto.anno.ZnValue;

	

	public class TestAnnotation {
		
		@ZnValue("用户名")
		private String userName;
		
		private String password;
		
	    public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public static void main(String args[]) {
	    	TestAnnotation demo = new TestAnnotation();
	        //demo.sayHi("001", "Alice");
	        //demo.sayHi("004", "Malory");

	        try {
	            Class<TestAnnotation> clazz;
					clazz = TestAnnotation.class;
					

	            //
	            // To get the sayHi method we need to pass not only the method name
	            // but also its parameters type so the the getMethod() method return
	            // the correct method for us to use.
	            //
	            Method method = clazz.getMethod("sayHi", String.class, String.class);

	            //
	            // Get all annotations from the sayHi method. But this will only
	            // return 1 annotation actually. Because only the HelloAnno
	            // annotation that has RUNTIME retention policy, which means that
	            // the other annotations associated with sayHi method is not
	            // available at runtime.
	            //
	            ZnValue a = method.getAnnotation(ZnValue.class);
	            //for (Annotation anno : annotations) {
	                System.out.println(a.value()+a.annotationType().getName());
	            //}
	                for(Field f:clazz.getDeclaredFields())
	                {
	                	System.out.println(f.getName());
	                	if(f.getAnnotation(ZnValue.class)!=null)
	                	{
	                		System.out.println(f.getAnnotation(ZnValue.class).value());
	                	}
	                	
	                }
	                //Field f=clazz.getField("userName");
	               // System.out.println(f.getAnnotation(MyAnno.class).value());
	                
	        } catch (NoSuchMethodException e) {
	            e.printStackTrace();
	        } catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//} 
	    }

	  //@HelloAnno(value = "Hello", greetTo = "Everyone")
	    @SuppressWarnings("unchecked")
	    @ZnValue("Hi")
	    public void sayHi(String dataId, String name) {
	        Map data = getData();
	        if (data.containsKey(dataId)) {
	            System.out.println("Hello " + data.get(dataId));
	        } else {
	            data.put(dataId, name);
	        }
	    }

	    public Map<String, String> getData() {
	        Map<String, String> data = new HashMap<String, String>();
	        data.put("001", "Alice");
	        data.put("003", "Bob");
	        data.put("003", "Carol");

	        return data;
	    }
	}

