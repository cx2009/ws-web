package com.jsxk.ws.common;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtils {


    private static Properties properties;


    public static java.util.Properties getProperties() throws Exception {


        if (properties == null) {
            properties = new Properties();

            initdata();

        }


        return properties;
    }


    private static void initdata() throws Exception {

        Resource resource = new ClassPathResource("aws.properties");

        // FileInputStream fis = new FileInputStream(resource.getFile());
        //properties.load(fis);
        //new FileInputStream("./aws.properties")
        properties.setProperty("aws.accessKey", "AKIAJJAHMBRKAFGMBWIQ");

        properties.setProperty("aws.secretKey", "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");

        properties.setProperty("aws.endpointUrl", "s3.dualstack.ap-northeast-1.amazonaws.com");
        properties.setProperty("aws.region", "ap-northeast-1");

        properties.setProperty("aws.downloadUrl", "");

        properties.setProperty("aws.region", "ap-northeast-1");
        properties.setProperty("aws.bucketName", "caomeivideo");


    }


}
