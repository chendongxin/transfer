package com.hqjy.mustang.admin.api;


import com.hqjy.mustang.admin.service.SysDeptService;
import com.hqjy.mustang.common.base.constant.Constant;
import com.hqjy.mustang.common.base.utils.R;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xyq
 * @date create on 2018/9/17
 * @apiNote TEST
 */
@RestController
@RequestMapping(Constant.API_PATH)
@Slf4j
public class SysDeptApi {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 获取所有部门
     */
    @ApiOperation(value = "获取所有部门信息", notes = "获取所有部门信息")
    @GetMapping(value = "/dept/all")
    public R getAllDept() {
        return R.ok(sysDeptService.getAllDeptList());
    }
}

