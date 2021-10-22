package com.tanbo.srb.oss.controller;

import com.tanbo.srb.base.exception.ServiceException;
import com.tanbo.srb.common.result.R;
import com.tanbo.srb.common.result.ResponseEnum;
import com.tanbo.srb.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author tanbo
 * @create 2021-10-22-13:57
 */

@RestController
@RequestMapping("/oss")
public class OSSController {


    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public R uploadOSS(@RequestParam("ossfile") MultipartFile file,
                       @RequestParam("module") String module){

        try {

            String fileName = file.getOriginalFilename();

            String downloadUrl = fileService.upload(file.getInputStream(), module, fileName);

            return R.OK().setData("downloadUrl",downloadUrl).resetMessage("文件上传成功");

        } catch (IOException e) {

            throw new ServiceException(ResponseEnum.UPLOAD_ERROR);

        }
    }

    @DeleteMapping("/remove")
    public R removeFile(@RequestParam("ossurl") String url){

        fileService.removeFileInOSS(url);

        return R.OK().resetMessage("删除成功");
    }


}
