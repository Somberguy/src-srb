package com.tanbo.srb.core.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tanbo.srb.base.exception.Assert;
import com.tanbo.srb.common.result.R;
import com.tanbo.srb.common.result.ResponseEnum;
import com.tanbo.srb.core.pojo.entity.IntegralGrade;
import com.tanbo.srb.core.service.IntegralGradeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author tanbo
 * @since 2021-10-17
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @GetMapping("/list")
    public R getIntegralGrade(@RequestParam("currentPage") long currentPage,
                              @RequestParam("pageSize") long pageSize){

        Page<IntegralGrade> integralGradePage = new Page<>();

        integralGradePage.setCurrent(currentPage);

        integralGradePage.setSize(pageSize);

        Page<IntegralGrade> gradePage = integralGradeService.page(integralGradePage);

        if (gradePage == null) {
            return R.error().resetMessage("获取列表失败");
        }
        return R.OK().setData("list",gradePage).resetMessage("获取列表成功");
    }

    @DeleteMapping ("/remove/{id}")
    public R deleteIntegralGradeById(@PathVariable("id") long id){

        boolean flag = integralGradeService.removeById(id);

        if (flag){

            return R.OK().setData("flag",flag).resetMessage("删除成功");

        }

        return R.error().resetMessage("删除失败");
    }

    @PostMapping("/save")
    public R saveIntegralGrade(@RequestBody IntegralGrade integralGrade){

        boolean flag = integralGradeService.save(integralGrade);

        if (flag){

            return R.OK().resetMessage("新增成功");

        }

        return R.error().resetMessage("新增失败");

    }

    @GetMapping("/get/{id}")
    public R getIntegralGrade(@PathVariable("id") Long id){

        IntegralGrade integralGrade = integralGradeService.getById(id);

        if (integralGrade == null) {

            return R.error().resetMessage("你查的数据没有");

        }

        return R.OK().resetMessage("查询成功").setData("integralGrade",integralGrade);

    }

    @PutMapping("/update")
    public R updateIntegralGrade(@RequestBody IntegralGrade integralGrade){

        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.EXPORT_DATA_ERROR);

        boolean flag = integralGradeService.updateById(integralGrade);

        if (flag){

            return R.OK().resetMessage("更新成功");

        }

        return R.error().resetMessage("更新失败");

    }

}

