package com.bubbles.server.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Properties;

/**
 * File service for images to save to specific location.
 *
 * @author Mingshan Lei
 * @since 2015/5/25
 */
@Service
public class ImageFileService {

    private Logger logger = LoggerFactory.getLogger(ImageFileService.class);

    private static String location;

    @PostConstruct
    public void init() {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/imglocation.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("linux") >= 0) {
            location = properties.getProperty("avatar.linux.location");
        } else if (os.indexOf("windows") >= 0) {
            location = properties.getProperty("win.linux.location");
        }
    }

    public String getLocation() {
        return location;
    }

    public boolean saveImgFile(InputStream inputStream, String outputFileName) {
        if (inputStream == null || outputFileName == null) {
            return false;
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(location + outputFileName);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != 0) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
