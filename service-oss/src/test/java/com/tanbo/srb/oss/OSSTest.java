package com.tanbo.srb.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.Test;

/**
 * @author tanbo
 * @create 2021-10-22-12:18
 */
public class OSSTest {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "oss-cn-chengdu.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    String accessKeyId = "LTAI5tPFwGhLstgM6244CEcX";
    String accessKeySecret = "NuwX8thyRMnetYIK9ZAgkeh3YhKRE9";
    String bucketName = "service-oss-test";

    @Test
    public void testCreateBucket() {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        boolean flag = ossClient.doesBucketExist(bucketName);

        System.out.println(flag);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
