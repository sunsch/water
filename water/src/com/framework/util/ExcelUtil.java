package com.framework.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ExcelUtil {

	private OutputStream out;
	private  WritableWorkbook workbook;
	private WritableSheet ws;
	private int rowNum = 0;
	
	public void open(String filename)
	{
		try {
			out = new FileOutputStream(new File(filename));
			workbook = Workbook.createWorkbook(out);
			ws = workbook.createSheet("sheet_1", 0);//创建sheet
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void write(String content)//add one line to excel
	{
		try {
			//要写的行，jxl操作excel时，第一行是从0开始，以此类推
			Object []cells=content.split("\t");
			//Object[] cells = (Object[]) list.get(i);
			putRow(ws, rowNum, cells);    //压一行到sheet
			rowNum++;
			//workbook.write();
			//workbook.close();    //一定要关闭, 否则没有保存Excel
		} catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	private void putRow(WritableSheet ws, int rowNum, Object[] cells) throws RowsExceededException, WriteException
	{
		for(int j=0; j<cells.length; j++)
		{	//写一行
			Label cell = new Label(j, rowNum, ""+cells[j]);
			ws.addCell(cell);
		}
	}

	public void close()
	{
		try {
			 workbook.write();
	            workbook.close();    //一定要关闭, 否则没有保存Excel
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
