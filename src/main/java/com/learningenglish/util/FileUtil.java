package com.learningenglish.util;

import org.apache.poi.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FileUtil {

    private final HttpServletRequest request;

    public FileUtil(HttpServletRequest request) {
        this.request = request;
    }

    // Kiểm tra kích thước của file
    public static int validateAttachFile(MultipartFile attachFile, String fileName) {
        String fileExt = "7z,rar,zip,txt,ppt,pptx,doc,docx,xls,xlsx,pdf,jpg,jpeg,png,bmp,gif";
        List<String> lstValidFileExt = Arrays.asList(fileExt.split(","));

        String fileType = fileName.toLowerCase().substring(fileName.lastIndexOf(".") + 1);
        if (!lstValidFileExt.contains(fileType)) {
            return 24;
        }

        double bytes = attachFile.getSize();
        double kilobytes = (bytes / 1024);
        double megabytes = (kilobytes / 1024);
        if (megabytes > 20) {
            return 25;
        }
        return 0;
    }

    // Kiểm tra file excel
    public static int validateAttachFileExcel(MultipartFile attachFile, String fileName){
        String fileExt = "xlsx,xls";
        List<String> lstValidFileExt = Arrays.asList(fileExt.split(","));
        String fileType = fileName.toLowerCase().substring(fileName.lastIndexOf(".") + 1);
        if(!lstValidFileExt.contains(fileType)){
            return 24;
        }

        double bytes = attachFile.getSize();
        double kilobytes = (bytes/ 1024);
        double megabytes = (kilobytes / 1024);
        if(megabytes > 20){
            return 25;
        }
        return 0;
    }

    // dowload file
    public static Object dowloadFile(String path, String fileName){
        File file;
        file = new File(path + fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return IOUtils.toByteArray(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
