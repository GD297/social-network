/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class OutputImageToFile implements Serializable {

    static final Logger LOGGER = Logger.getLogger(OutputImageToFile.class);

    public static void copyInputStreamToFile(InputStream inputStream, File file, String fileName) throws IOException {
        FileOutputStream outputStream = null;
        try {
            System.out.println("Create folder of user: " + file.mkdirs());
            outputStream = new FileOutputStream(file + "//" + fileName);
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException ex) {
            LOGGER.info("FileNotFoundException " + ex.getMessage());
        } finally {

            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

}
