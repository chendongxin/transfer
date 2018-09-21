package com.hqjy.mustang.transfer.crm.feign;

import com.hqjy.mustang.common.base.constant.Constant;
import com.hqjy.mustang.common.base.utils.R;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "mustang-admin")
public interface SysDeptServiceFeign {

    @GetMapping(Constant.API_PATH + "/dept/all")
    R getAllDept();
}