package com.exam.utils.sort;

import java.util.List;
import java.util.Set;
import java.util.Vector;

public class RandomUtil 
{
	/*
	 * e.g. pass 10, it will return 3 9 8 2 0 4 6 7 1 5 
	 */
	public static List<Integer> getRandomList(int maxElementSize)
	{
		List<Integer> ran=new Vector<Integer>();
		for(int i=0;i<10;i++)
		{
			ran.add(i);
		}
		java.util.Collections.shuffle(ran);
		/*for(int i:ran)
		{
			System.out.print(i+" ");
		}*/
		return ran;
	}
	
	public static List<Integer> getRandomList(Set<Integer> allk)
	{
		List<Integer> ran=new Vector<Integer>();
		for(int i:allk)
		{
			ran.add(i);
		}
		java.util.Collections.shuffle(ran);
		return ran;
	}
}
