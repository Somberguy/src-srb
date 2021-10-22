package com.tanbo.srb.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tanbo.srb.core.pojo.dto.ExcelDictDTO;
import com.tanbo.srb.core.pojo.entity.Dict;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author tanbo
 * @since 2021-10-17
 */
public interface DictService extends IService<Dict> {

    void uploadExcelFile(InputStream fileInputStream);

    List<ExcelDictDTO> getDictList();

    List<Dict> getByParentId(Long parentId);
}
