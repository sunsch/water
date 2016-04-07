package com.framework.auto.json;
import net.sf.json.util.PropertyFilter;

public class JsonExcluder implements PropertyFilter
{
	private String[] includer=new String[]{};
	public JsonExcluder(String[] includer)
	{
		this.includer=includer;
	}

	@Override
	public boolean apply(Object source, String name, Object value)
	{
		if(value==null)
		{
			return false;
		}
		for(String p:includer)
		{
			if(p.equals(name))
			{
				return true;
			}
		}
		return false;
	}
	
}
