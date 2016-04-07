package com.framework.auto.test;


public class TestStringSplit {
	
	/*
	    temp="1#题目： [_AUDIO_]/exam/shared/190/5418/name.wav[_AUDIO_]";
	  	1#题目： 
		/exam/shared/190/5418/name.wav
	
		temp="[_AUDIO_]/exam/shared/190/5418/name.wav[_AUDIO_]aasd";
	
		/exam/shared/190/5418/name.wav
		aasd


		temp="aaaa[_AUDIO_]/exam/shared/190/5418/name.wav[_AUDIO_]aasd";
		aaaa
		/exam/shared/190/5418/name.wav
		aasd
		
		
		RESULT: /exam/shared/190/5418/name.wav is always the second string!
		
	 */
	public static void main(String[]args)
	{
		String temp="AUDIOAUDIOAUDIO<p class=MsoNormal style='mso-layout-grid-align:none;text-autospace:none'><spanlang=EN-US style='font-size:12.0pt;font-family:\"Times New Roman\"'></span><spanstyle='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:\"Times New Roman\";mso-hansi-font-family:\"Times New Roman\"'></span><span style='font-size:12.0pt;font-family:\"Times New Roman\"'> <span lang=EN-US>[_AUDIO_]/exam/shared/audio/Aaaa.mp3[_AUDIO_]<o:p></o:p></span></span></p>";
		String []tempc=temp.split("\\[_AUDIO_\\]");
		System.out.println(tempc.length);
		String newtemc="";
		for(int i=0;i<tempc.length;i++)
		{
			System.out.println(tempc[i]);
			if(i==1)
			{
				newtemc+="<embed classid=\"clsid:"+System.currentTimeMillis()+i+" src=\""+tempc[i]+"\" enablecontextmenu=\"false\" autostart=\"true\" width=\"480\" height=\"40\" />";
			}else
			{
				newtemc+=tempc[i];
			}
		}
		System.out.println("------------");
		System.out.println(newtemc);
	}

}
