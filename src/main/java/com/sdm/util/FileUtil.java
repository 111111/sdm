package com.sdm.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * com.sdm.util说明:
 * Created by qinyun
 * 18/5/29 22:35
 */
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
