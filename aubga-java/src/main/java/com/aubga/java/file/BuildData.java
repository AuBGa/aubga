package com.aubga.java.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BuildData {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\\\hiworklog_业务架构\\\\对账\\\\通联\\\\tonglian_files.txt");
        FileInputStream fis = null;
        try {
            ReadFile readFile = new ReadFile();
            fis = new FileInputStream(file);
            int available = fis.available();
            int maxThreadNum = 10;
            // 线程粗略开始位置
            int i = available / maxThreadNum;
            for (int j = 0; j < maxThreadNum; j++) {
                // 计算精确开始位置
                long startNum = j == 0 ? 0 : readFile.getStartNum(file, i * j);
                long endNum = j + 1 < maxThreadNum ? readFile.getStartNum(file, i * (j + 1)) : -2;
                // 具体监听实现
            //    ProcessDataByPostgisListeners listeners = new ProcessDataByPostgisListeners("gbk");
                new ReadFileThread(startNum, endNum, file.getPath()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}