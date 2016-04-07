package com.framework.auto.json;
import net.sf.json.util.PropertyFilter;

public class JsonIncluder implements PropertyFilter
{
	private String[] includer=new String[]{};
	public JsonIncluder(String[] includer)
	{
		this.includer=includer;
	}

	@Override
	public boolean apply(Object source, String name, Object value)
	{
		for(String p:includer)
		{
			if(p.equals(name))
			{
				return false;
			}
		}
		return true;
	}
	
}
