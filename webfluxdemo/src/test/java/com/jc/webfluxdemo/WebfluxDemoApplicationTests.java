package com.jc.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@SpringBootTest
class WebfluxDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    // 压缩文件测试
    public static void main(String[] args) throws IOException {
        ZipOutputStream zipOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\CarShipTaxData\\0000990000\\out.zip");
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            File file = new File("D:\\CarShipTaxData\\0000990000\\A202010000099000000011.xls");
            fileInputStream = new FileInputStream(file);
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            int len;
            byte[] buf = new byte[2048];
            while ((len = fileInputStream.read(buf)) != -1) {
                zipOutputStream.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(zipOutputStream != null) {
                zipOutputStream.closeEntry();
            }
            if(fileInputStream != null){
                fileInputStream.close();
            }
        }

    }
}
