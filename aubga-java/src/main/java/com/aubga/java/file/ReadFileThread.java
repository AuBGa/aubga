package com.aubga.java.file;

public class ReadFileThread extends Thread {
	 
    private String filePath;
    private long start;
    private long end;
 
    public ReadFileThread(long start,long end,String file) {
        this.setName(this.getName()+"-ReadFileThread");
        this.start = start;
        this.end = end;
        this.filePath = file;
    }
 
    @Override
    public void run() {
        ReadFile readFile = new ReadFile();
        try {
            readFile.readFileByLine(filePath, start, end + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}