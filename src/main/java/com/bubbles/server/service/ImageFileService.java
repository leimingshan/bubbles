package com.bubbles.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * File service for images to save to specific location
 * and url management.
 *
 * @author Mingshan Lei
 * @since 2015/5/25
 */
@Service
public class ImageFileService {

    private Logger logger = LoggerFactory.getLogger(ImageFileService.class);

    /**
     * Base folder location to save uploaded images.
     */
    private static String basePath;

    /**
     * Base http url for getting images.
     */
    private static String baseUrl;

    /**
     * Init bean, read resource properties and get path settings.
     */
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
            basePath = properties.getProperty("avatar.linux.location");
        } else if (os.indexOf("windows") >= 0) {
            basePath = properties.getProperty("avatar.win.location");
        }

        baseUrl = properties.getProperty("baseURL");

        if (basePath == null || baseUrl == null) {
            logger.error("Read property file: imglocation.properties failed.");
        }
    }

    public String getBasePath() {
        return basePath;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Try to save image to disk and generate http url.
     *
     * @param file           uploaded file from spring mvc
     * @param outputFileName saved file name
     * @return url path for getting the file
     */
    public String saveImgFile(MultipartFile file, String outputFileName) {
        if (basePath == null || baseUrl == null) {
            return null;
        }
        if (file == null || outputFileName == null) {
            return null;
        }

        try {
            file.transferTo(new File(basePath + outputFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseUrl + outputFileName;
    }
}
