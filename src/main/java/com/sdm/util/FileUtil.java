package com.sdm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

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

    public static void nioFile(){
        RandomAccessFile rFile = null;
        try{
            rFile = new RandomAccessFile("/Users/apple/Downloads/iefdb-2018-04-28.sql", "rw");
            FileChannel fileChannel = rFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = fileChannel.read(buffer);
            System.out.println("read = " + read);
            while (read != -1){
                buffer.flip();
                while (buffer.hasRemaining()){
                    System.out.print((char)buffer.get());
                    if("\n".equals((char)buffer.get())){
                        System.out.println();
                    }
                }
                buffer.compact();
                read = fileChannel.read(buffer);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {

            try{
                if(rFile != null){
                    rFile.close();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
    }

    public static void main(String[] args){
//        nioFile();
        List list = new ArrayList();
        List linkedList = new LinkedList();

        Map map = new HashMap();

        int MAXIMUM_CAPACITY = 1 << 30;
        System.out.println("MAXIMUM_CAPACITY = " + MAXIMUM_CAPACITY);

        Hashtable hashtable = new Hashtable();
    }
}
