package com.mpc.vrekon.util.io;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NesiaFileStream {
    public static FileOutputStream outputStream(String destination){
        try {
            File temp = new File(destination);
            Files.createDirectories(Paths.get(temp.getAbsolutePath().
                    substring(0,temp.getAbsolutePath().lastIndexOf(File.separator))));
            return new FileOutputStream(temp);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
