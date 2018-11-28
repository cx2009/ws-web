package com.jsxk.ws.common;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class AWSS3Util {


    private static String downloadUrl;
    private static String bucketName;

    private static AWSS3Util awsS3Util;
    private static AmazonS3 s3;
    // private static ObjectMetadata metadata;


    /**
     * 上传公有文件
     *
     * @param filePath 待上传文件路径
     * @param key      该文件在bucket中的文件名
     * @param isPub    是否公有
     * @return String        文件访问url
     * @throws FileNotFoundException
     */
    public String putObject(String filePath, String key, boolean isPub,ObjectMetadata metadata) throws FileNotFoundException {
        return putObject(new File(filePath), key, isPub,metadata);
    }

    /**
     * @param file  待上传文件
     * @param key   该文件在bucket中的文件名
     * @param isPub 上传文件是否公有
     * @return String    文件访问url
     * @throws FileNotFoundException
     */
    public String putObject(File file, String key, boolean isPub,ObjectMetadata metadata) throws FileNotFoundException {
        return putObject(new FileInputStream(file), key, isPub,metadata);
    }

    /**
     * @param inputStream 待上传文件流
     * @param key         该文件在bucket中的文件名
     * @param isPub       上传文件是否公有
     * @return String            文件访问url
     * @throws FileNotFoundException
     */
    public String putObject(InputStream inputStream, String key, boolean isPub, ObjectMetadata metadata) throws FileNotFoundException {


        PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, metadata);
        if (isPub) request.setCannedAcl(CannedAccessControlList.PublicRead);
        s3.putObject(request);
        return isPub ? new StringBuffer().append(downloadUrl).append(key).toString() : getPublicURL(key, null);
    }

    /**
     * 获取文件公有链接
     *
     * @param key            该文件在bucket中的文件名
     * @param expirationDate 设置过期时间 无过期时间传null
     * @return
     */
    public String getPublicURL(String key, Date expirationDate) {
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key);
        if (null != expirationDate) urlRequest.setExpiration(expirationDate);
        return s3.generatePresignedUrl(urlRequest).toString();
    }

    public static AWSS3Util getInstance(String filename) throws Exception {
        if (awsS3Util == null) {
            synchronized (AWSS3Util.class) {
                if (awsS3Util == null) {
                    awsS3Util = new AWSS3Util();
                    Properties pro = PropertiesUtils.getProperties();
                    s3 = AmazonS3ClientBuilder.standard()
                            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(pro.getProperty("aws.accessKey"), pro.getProperty("aws.secretKey"))))
                            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(pro.getProperty("aws.endpointUrl"), pro.getProperty("aws.region")))
                            .build();
                    // metadata = new ObjectMetadata();

                    // metadata.setContentType(getContentTypeByFileName(filename));//目前只用于上传svg v1.0
                    downloadUrl = pro.getProperty("aws.downloadUrl");
                    bucketName = pro.getProperty("aws.bucketName");
                }
            }
        }
        return awsS3Util;
    }


    public  static String getContentTypeByFileName(String fileName) {
        String CONTENT_TYPE_SVG = "image/jpeg";

        switch (fileName) {

            case ".jpg":
                CONTENT_TYPE_SVG = "image/jpeg";
                break;
            case ".jpeg":
                CONTENT_TYPE_SVG = "image/jpeg";
                break;
            case ".mp4":
                CONTENT_TYPE_SVG = "video/mp4";
                break;
            case ".rmp":
                CONTENT_TYPE_SVG = "application/vnd.rn-rn_music_package";
                break;
            case ".png":
                CONTENT_TYPE_SVG = "image/png";
                break;

        }

        return CONTENT_TYPE_SVG;


    }


}
