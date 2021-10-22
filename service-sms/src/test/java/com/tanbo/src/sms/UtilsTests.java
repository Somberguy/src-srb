package com.tanbo.src.sms;

import com.tanbo.srb.sms.ServiceSMSApplication;
import com.tanbo.srb.sms.config.AliyunSMSConfigrationProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ServiceSMSApplication.class)
@RunWith(SpringRunner.class)
public class UtilsTests {

    @Test
    public void testProperties(){
        System.out.println(AliyunSMSConfigrationProperties.KEY_ID);
        System.out.println(AliyunSMSConfigrationProperties.KEY_SECRET);
        System.out.println(AliyunSMSConfigrationProperties.REGION_Id);
    }
}