package com.tanbo.srb.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanbo.srb.core.listener.DictReadListener;
import com.tanbo.srb.core.mapper.DictMapper;
import com.tanbo.srb.core.pojo.dto.ExcelDictDTO;
import com.tanbo.srb.core.pojo.entity.Dict;
import com.tanbo.srb.core.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author tanbo
 * @since 2021-10-17
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    private DictMapper dictMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void uploadExcelFile(InputStream fileInputStream) {

        EasyExcel.read(fileInputStream,Dict.class,new DictReadListener(baseMapper)).sheet().doRead();

    }

    @Override
    public List<ExcelDictDTO> getDictList() {

        List<Dict> dictList = baseMapper.selectList(null);

        List<ExcelDictDTO> list = new ArrayList<>();

        dictList.forEach(dict -> {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();

            BeanUtils.copyProperties(dict,excelDictDTO);

            list.add(excelDictDTO);
        });

        return list;
    }

    @Override
    public List<Dict> getByParentId(Long parentId) {

        List<Dict> list = null;

        try {
            list = (List<Dict>) redisTemplate.opsForValue().get("dict" + parentId);

            if (list != null){

                list.forEach(dict -> {

                    log.info("dict: {}",dict);

                });

                return list;

            }
        } catch (Exception e) {

            log.error("redis服务器异常：" + ExceptionUtils.getStackTrace(e));

        }

        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();

        dictQueryWrapper.eq("parent_id",parentId);

        list = baseMapper.selectList(dictQueryWrapper);

        list.forEach(dict -> {

            dict.setHasChildren(this.hasChildren(dict.getId()));

        });

        try {

            redisTemplate.opsForValue().set("dict" + parentId,list,5, TimeUnit.MINUTES);

            log.error("过来了");

        } catch (Exception e) {

            log.error("redis服务器异常：" + ExceptionUtils.getStackTrace(e));

        }

        return list;
    }

    public Boolean hasChildren(Long parentId){

        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();

        dictQueryWrapper.eq("parent_id",parentId);

        Integer count = baseMapper.selectCount(dictQueryWrapper);

        if (count > 0){

            return true;

        }else {

            return false;

        }

    }
}
