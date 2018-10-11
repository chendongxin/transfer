package com.hqjy.mustang.admin.api;

import com.hqjy.mustang.admin.service.SysUserDeptService;
import com.hqjy.mustang.common.base.constant.Constant;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gmm
 * @date create on 2018/9/26
 */
@RestController
@RequestMapping(Constant.API_PATH)
@Slf4j
public class SysUserDeptApi {
    @Autowired
    private SysUserDeptService sysUserDeptService;

    /**
     * 返回用户所有部门id集合
     */
    @ApiOperation(value = "返回用户所有部门id集合", notes = "返回用户所有部门id集合")
    @GetMapping(value = "/UserDeptAll/{userId}")
    public List<Long> getUserDeptIdList(@PathVariable("userId") Long userId) {
        return sysUserDeptService.getUserDeptIdList(userId);
    }

}