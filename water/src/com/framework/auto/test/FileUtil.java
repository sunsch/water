package com.framework.auto.test;

import java.io.*;

public class FileUtil 
{
        private FileOutputStream fo;
        private OutputStreamWriter os;
        private FileInputStream fi;
        private InputStreamReader is;
        private BufferedWriter bw;
        //private PrintWriter pw;
        //private FileReader fr;
        private BufferedReader br;
        
        /*
         *  PrintWriter
			public PrintWriter(OutputStream out)
    		Create a new PrintWriter, without automatic line flushing, from an existing OutputStream. This convenience constructor creates the necessary intermediate OutputStreamWriter, which will convert characters into bytes using the default character encoding.
    		Parameters:
        	out - An output stream
    		See Also:
        	OutputStreamWriter.OutputStreamWriter(java.io.OutputStream)
         */
        
        public void openForRead(String fileName)
        {
                try {
                        fi=new FileInputStream(fileName);
                        is=new InputStreamReader(fi,"UTF-8");
                        br = new BufferedReader(is);
                } catch (FileNotFoundException e)
                {
                        e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
        public String readLine()
        {
                try {
                        return br.readLine();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }
        public void closeForRead()
        {
                try {
                br.close();
                is.close();
                fi.close();
                } catch (IOException e)
                {
                        e.printStackTrace();
                }
        }
        public boolean deleteFile(String fileName)
        {
                File f=new File(fileName);
                //Log.console("delete file "+fileName);
                return f.delete();
        }
        public void renameTo(String src,String dest)
        {
                File f=new File(src);
                f.renameTo(new File(dest));
        }
        public void backup(String fileName)
        {
                String shortName=new File(fileName).getName();
                String bkfile=new File(fileName).getParent()+"\\backupOf"+shortName;
                deleteFile(bkfile);
                createFileIfNotExist(bkfile);
                
                openForRead(fileName);
                open(bkfile);
                String temp;
                while((temp=readLine())!=null)
                {
                        write(temp);
                }
                closeForRead();
                close();
                
        }
        //if not exist, create it
        public void createDirIfNotExist(String dir)
        {
                File file =new File(dir);
                if(!file.exists())
                {
                        if(!file.mkdir())
                        {
                                
                        }
                }
        }
        //if not exist, create it
        public void createFileIfNotExist(String f)
        {
                File file =new File(f);
                
                if(!file.exists())
                {
                        try {
                                if(!file.createNewFile())
                                {
                                        
                                }else
                                {
                                        //Log.console("create file "+f);
                                }
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
        public boolean checkExist(String dirOrFile)
        {
                File file =new File(dirOrFile);
                if(file.exists())
                        return true;
                return false;
        }
        public void open(String fileName)
        {
                try {
                        fo=new FileOutputStream(fileName);
                        os=new OutputStreamWriter(fo,"UTF-8");
                        bw=new BufferedWriter(os);
                        
                        
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
        public void flush()
        {
                try {
					bw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
        
        public void close()
        {
                try {
                        bw.close();
                        os.close();
                        fo.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        
        public void write(String content)
        {
                try {
					bw.write(content);
					addEmptyLine(1);
					bw.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
        }
        public void addEmptyLine(int newLineCount)
        {
                for(int i=0;i<newLineCount;i++)
                {
                	try {
						bw.write("\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                try {
					bw.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
        public static String getParent(String path)
        {
                File f=new File(path);
                return f.getParent();
        }

}
