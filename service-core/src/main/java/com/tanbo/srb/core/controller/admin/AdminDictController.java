package com.tanbo.srb.core.controller.admin;


import com.alibaba.excel.EasyExcel;
import com.tanbo.srb.base.exception.ServiceException;
import com.tanbo.srb.common.result.R;
import com.tanbo.srb.common.result.ResponseEnum;
import com.tanbo.srb.core.pojo.dto.ExcelDictDTO;
import com.tanbo.srb.core.pojo.entity.Dict;
import com.tanbo.srb.core.service.DictService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author tanbo
 * @since 2021-10-17
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/core/dict")
public class AdminDictController {

    @Resource
    private DictService dictService;

    @PostMapping("/upload")
    public R uploadExcel(@RequestParam("file") MultipartFile file){

        try {
            InputStream fileInputStream = file.getInputStream();

            dictService.uploadExcelFile(fileInputStream);

            return R.OK().resetMessage("Excel文件上传成功!");

        } catch (Exception e) {

            throw new ServiceException(ResponseEnum.UPLOAD_ERROR);

        }
    }
    @GetMapping("/download")
    public void downloadExcel(HttpServletResponse response){

        try {
            response.setCharacterEncoding("UTF-8");

            response.setContentType("application/vnd.ms-excel");

            String fileName = URLEncoder.encode("dict","UTF-8").replaceAll("\\+", "%20");

            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("样本").doWrite(dictService.getDictList());

        } catch (Exception e) {
            throw new ServiceException(ResponseEnum.EXPORT_DATA_ERROR);
        }
    }

    @GetMapping("/list/{parentId}")
    public R getList(@PathVariable Long parentId){

        List<Dict> list = dictService.getByParentId(parentId);

        return R.OK().setData("list",list);
    }

}

