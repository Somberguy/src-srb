package com.tanbo.srb.core.mapper;

import com.tanbo.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author tanbo
 * @since 2021-10-17
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<Dict> list);

}
