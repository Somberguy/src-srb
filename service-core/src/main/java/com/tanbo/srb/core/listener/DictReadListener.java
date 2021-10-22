package com.tanbo.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tanbo.srb.core.mapper.DictMapper;
import com.tanbo.srb.core.pojo.entity.Dict;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanbo
 * @create 2021-10-21-14:51
 */
@Slf4j
@NoArgsConstructor
public class DictReadListener extends AnalysisEventListener<Dict> {

    private DictMapper dictMapper;

    private List<Dict> list  = new ArrayList();

    private static final int BATCH_COUNT = 5;

    public DictReadListener(DictMapper dictMapper) {

        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(Dict dict, AnalysisContext analysisContext) {

        list.add(dict);

        if (list.size() >= BATCH_COUNT) {

            dictMapper.insertBatch(list);

            list.clear();

        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        dictMapper.insertBatch(list);

    }
}
