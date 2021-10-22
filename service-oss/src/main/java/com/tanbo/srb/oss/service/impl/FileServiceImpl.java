package com.tanbo.srb.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.tanbo.srb.oss.config.AliyunOSSConfigrationProperties;
import com.tanbo.srb.oss.service.FileService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(InputStream inputStream, String module, String fileName) {

        OSS oss = new OSSClientBuilder().build(
                AliyunOSSConfigrationProperties.ENDPOINT,
                AliyunOSSConfigrationProperties.KEY_ID,
                AliyunOSSConfigrationProperties.KEY_SECRET);

        if (!oss.doesBucketExist(AliyunOSSConfigrationProperties.BUCKET_NAME)){

            oss.createBucket(AliyunOSSConfigrationProperties.BUCKET_NAME);

        }


        String folder = new DateTime().toString("yyyy:MM:dd");

        fileName = UUID.randomUUID().toString() + fileName  + fileName.substring(fileName.lastIndexOf("."));

        String dir = module + "/" + folder + "/" + fileName;

        oss.putObject(
                AliyunOSSConfigrationProperties.BUCKET_NAME,
                dir,inputStream);

        oss.shutdown();

        return "https://" + AliyunOSSConfigrationProperties.BUCKET_NAME + "." + AliyunOSSConfigrationProperties.ENDPOINT + "/" + dir;
    }

    @Override
    public void removeFileInOSS(String ossUrl) {


        OSS oss = new OSSClientBuilder().build(
                AliyunOSSConfigrationProperties.ENDPOINT,
                AliyunOSSConfigrationProperties.KEY_ID,
                AliyunOSSConfigrationProperties.KEY_SECRET);

        String host = "https://" + AliyunOSSConfigrationProperties.BUCKET_NAME + "." + AliyunOSSConfigrationProperties.ENDPOINT + "/";

        String fileName = ossUrl.substring(host.length());

        oss.deleteObject(AliyunOSSConfigrationProperties.BUCKET_NAME,fileName);

    }
}