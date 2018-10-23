package com.hqjy.mustang.transfer.export.controller;

import com.hqjy.mustang.common.base.annotation.SysLog;
import com.hqjy.mustang.common.base.utils.R;
import com.hqjy.mustang.transfer.export.model.query.*;
import com.hqjy.mustang.transfer.export.service.PromotionCompanyCostService;
import com.hqjy.mustang.transfer.export.service.PromotionCustomerService;
import com.hqjy.mustang.transfer.export.service.PromotionDailyService;
import com.hqjy.mustang.transfer.export.service.PromotionSmsCostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xyq
 * @date create on 2018/9/17
 * @apiNote 推广报表控制层
 */

@Api(tags = "推广报表管理", description = "PromotionReportController")
@RestController
@RequestMapping("/report/promotion")
public class PromotionReportController {

    private PromotionDailyService promotionDailyService;
    private PromotionCustomerService promotionCustomerService;
    private PromotionSmsCostService promotionSmsCostService;
    private PromotionCompanyCostService promotionCompanyCostService;

    @Autowired
    public void setPromotionCompanyCostService(PromotionCompanyCostService promotionCompanyCostService) {
        this.promotionCompanyCostService = promotionCompanyCostService;
    }

    @Autowired
    public void setPromotionSmsCostService(PromotionSmsCostService promotionSmsCostService) {
        this.promotionSmsCostService = promotionSmsCostService;
    }

    @Autowired
    public void setPromotionService(PromotionCustomerService promotionCustomerService) {
        this.promotionCustomerService = promotionCustomerService;
    }

    @Autowired
    public void setPromotionDailyService(PromotionDailyService promotionDailyService) {
        this.promotionDailyService = promotionDailyService;
    }

    @ApiOperation(value = " 招转日常数据报表列表", notes = "请求参数格式：\n" +
            "分页参数：/report/promotion/promotionDailyList?pageNum=1&pageSize=10\n" +
            "高级查询参数：\n" +
            "{\n" +
            "  \"beginTime\": \"2018-09-10\",\n" +
            "  \"endTime\": \"2018-10-20\",\n" +
            "  \"deptId\":1869,\n" +
            "  \"companyId\":1869,\n" +
            "  \"getWay\":1869,\n" +
            "  \"sourceId\":1869\n" +
            "}\n" +
            "请求成功：\n" +
            "{\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"result\": {\n" +
            "    \"currPage\": 1,\n" +
            "    \"list\": [\n" +
            "      {\n" +
            "        \"businessNum\": 0,\n" +
            "        \"dealNum\": 0,\n" +
            "        \"deptId\": 1873,\n" +
            "        \"deptName\": \"广州天河校区\",\n" +
            "        \"followNum\": 0,\n" +
            "        \"intentionNum\": 0,\n" +
            "        \"unFailNum\": 0,\n" +
            "        \"unFailRate\": \"0.00%\",\n" +
            "        \"validNum\": 0,\n" +
            "        \"validRate\": \"0.00%\",\n" +
            "        \"visitDealRate\": \"0.00%\",\n" +
            "        \"visitIntentionRate\": \"0.00%\",\n" +
            "        \"visitNum\": 0,\n" +
            "        \"visitValidNum\": 0,\n" +
            "        \"visitValidRate\": \"0.00%\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"pageSize\": 10,\n" +
            "    \"size\": 1,\n" +
            "    \"totalCount\": 1,\n" +
            "    \"totalPage\": 1\n" +
            "  },\n" +
            "  \"code\": 0\n" +
            "}")
    @PostMapping("/promotionDailyList")
    public R promotionDailyList(@ModelAttribute PageParams params, @RequestBody(required = false) DailyQueryParams query) {
        return R.result(promotionDailyService.promotionDailyList(params, query));
    }

    @SysLog("导出招转日常数据")
    @ApiOperation(value = "导出招转日常数据", notes = "请求参数格式:\n" +
            "{\n" +
            "  \"beginTime\": \"2018-09-10\",\n" +
            "  \"endTime\": \"2018-10-20\",\n" +
            "  \"deptId\":1869,\n" +
            "  \"companyId\":1869,\n" +
            "  \"getWay\":1869,\n" +
            "  \"sourceId\":1869\n" +
            "}\n" +
            "请求成功：\n" +
            "{\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"result\": \"http://hqcrm.oss-cn-shenzhen.aliyuncs.com/export/%E6%8B%9B%E8%BD%AC%E6%97%A5%E5%B8%B8%E6%95%B0%E6%8D%AE%E6%8A%A5%E8%A1%A8_20181015_174951_502.xls?Expires=1539600591&OSSAccessKeyId=LTAIAO3rjtbLdTNb&Signature=9IQDmE6TT2gi9P0tnrjbntHn7m0%3D\",\n" +
            "  \"code\": 0\n" +
            "}"
    )
    @PostMapping("/exportPromotionDaily")
    public R exportPromotionDaily(@RequestBody DailyQueryParams query) {
        return R.result(promotionDailyService.exportPromotionDaily(query));
    }

    @ApiOperation(value = "客服推广报表数据列表", notes = "请求参数格式：\n" +
            "分页参数：/report/promotion/promotionDailyList?pageNum=1&pageSize=10\n" +
            "高级查询参数：\n" +
            "{\n" +
            "\"beginTime\": \"2018-09-10\",\n" +
            "\"endTime\": \"2018-10-20\",\n" +
            "\"userId\":1869,\n" +
            "\"companyId\":1869,\n" +
            "\"sourceId\":1869\n" +
            "}" +
            "请求成功：\n" +
            "{\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"result\": {\n" +
            "    \"currPage\": 1,\n" +
            "    \"list\": [\n" +
            "      {\n" +
            "        \"businessNum\": 0,\n" +
            "        \"dealNum\": 0,\n" +
            "        \"deptId\": 22,\n" +
            "        \"deptName\": \"广州白云校区\",\n" +
            "        \"name\": \"客服1\",\n" +
            "        \"userId\": 4,\n" +
            "        \"validNum\": 0,\n" +
            "        \"visitNum\": 0,\n" +
            "        \"visitValidNum\": 0\n" +
            "      }\n" +
            "    ],\n" +
            "    \"pageSize\": 10,\n" +
            "    \"size\": 1,\n" +
            "    \"totalCount\": 1,\n" +
            "    \"totalPage\": 1\n" +
            "  },\n" +
            "  \"code\": 0\n" +
            "}")
    @PostMapping("/promotionCustomerList")
    public R promotionCustomerList(@ModelAttribute PageParams params, @RequestBody(required = false) CustomerQueryParams query) {
        return R.result(promotionCustomerService.promotionCustomerList(params, query));
    }

    @SysLog("导出客服推广报表数据")
    @ApiOperation(value = "导出客服推广报表数据", notes = "请求参数格式:\n" +
            "{\n" +
            "\"beginTime\": \"2018-09-10\",\n" +
            "\"endTime\": \"2018-10-20\",\n" +
            "\"userId\":1869,\n" +
            "\"companyId\":1869,\n" +
            "\"sourceId\":1869\n" +
            "}" +
            "请求成功：\n" +
            "{\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"result\": \"http://hqcrm.oss-cn-shenzhen.aliyuncs.com/export/%E6%8B%9B%E8%BD%AC%E6%97%A5%E5%B8%B8%E6%95%B0%E6%8D%AE%E6%8A%A5%E8%A1%A8_20181015_174951_502.xls?Expires=1539600591&OSSAccessKeyId=LTAIAO3rjtbLdTNb&Signature=9IQDmE6TT2gi9P0tnrjbntHn7m0%3D\",\n" +
            "  \"code\": 0\n" +
            "}")
    @PostMapping("/exportPromotionCustomer")
    public R exportPromotionCustomer(@RequestBody CustomerQueryParams query) {
        return R.result(promotionCustomerService.exportPromotionCustomer(query));
    }

    @ApiOperation(value = "客服推广短信费用报表数据列表", notes = "请求参数格式：\n" +
            "分页参数：/report/promotion/promotionDailyList?pageNum=1&pageSize=10\n" +
            "高级查询参数：\n" +
            "{\n" +
            "\"beginTime\": \"2018-09-10\",\n" +
            "\"endTime\": \"2018-10-20\",\n" +
            "\"deptId\":20\n" +
            "}" +
            "请求成功：\n"
    )
    @PostMapping("/promotionSmsCostList")
    public R promotionSmsCostList(@ModelAttribute PageParams params, @RequestBody(required = false) SmsCostQueryParams query) {
        return R.result(promotionSmsCostService.promotionSmsCostList(params, query));
    }

    @SysLog("导出推广短信费用报表数据")
    @ApiOperation(value = "导出推广短信费用报表数据", notes = "请求参数格式:\n" +
            "{\n" +
            "\"beginTime\": \"2018-09-10\",\n" +
            "\"endTime\": \"2018-10-20\",\n" +
            "\"deptId\":20\n" +
            "}" +
            "请求成功：\n" +
            "{\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"result\": \"http://hqcrm.oss-cn-shenzhen.aliyuncs.com/export/%E6%8B%9B%E8%BD%AC%E6%97%A5%E5%B8%B8%E6%95%B0%E6%8D%AE%E6%8A%A5%E8%A1%A8_20181015_174951_502.xls?Expires=1539600591&OSSAccessKeyId=LTAIAO3rjtbLdTNb&Signature=9IQDmE6TT2gi9P0tnrjbntHn7m0%3D\",\n" +
            "  \"code\": 0\n" +
            "}")
    @PostMapping("/exportPromotionSmsCost")
    public R exportPromotionSmsCost(@RequestBody SmsCostQueryParams query) {
        return R.result(promotionSmsCostService.exportPromotionSmsCost(query));
    }


    @ApiOperation(value = "推广公司费用报表数据列表", notes = "请求参数格式：\n" +
            "分页参数：/report/promotion/promotionDailyList?pageNum=1&pageSize=10\n" +
            "高级查询参数：\n" +
            "{\n" +
            "\"beginTime\": \"2018-09-10\",\n" +
            "\"endTime\": \"2018-10-20\",\n" +
            "\"deptId\":20\n" +
            "}" +
            "请求成功：\n"
    )
    @PostMapping("/promotionCompanyCostList")
    public R promotionCompanyCostList(@ModelAttribute PageParams params, @RequestBody(required = false) CompanyCostQueryParams query) {
        return R.result(promotionCompanyCostService.promotionCompanyCostList(params, query));
    }

    @SysLog("导出推广公司费用报表数据")
    @ApiOperation(value = "导出推广公司费用报表数据", notes = "请求参数格式:\n" +
            "{\n" +
            "\"beginTime\": \"2018-09-10\",\n" +
            "\"endTime\": \"2018-10-20\",\n" +
            "\"deptId\":20\n" +
            "}" +
            "请求成功：\n" +
            "{\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"result\": \"http://hqcrm.oss-cn-shenzhen.aliyuncs.com/export/%E6%8B%9B%E8%BD%AC%E6%97%A5%E5%B8%B8%E6%95%B0%E6%8D%AE%E6%8A%A5%E8%A1%A8_20181015_174951_502.xls?Expires=1539600591&OSSAccessKeyId=LTAIAO3rjtbLdTNb&Signature=9IQDmE6TT2gi9P0tnrjbntHn7m0%3D\",\n" +
            "  \"code\": 0\n" +
            "}")
    @PostMapping("/exportPromotionCompanyCost")
    public R exportPromotionCompanyCost(@RequestBody CompanyCostQueryParams query) {
        return R.result(promotionCompanyCostService.exportPromotionCompanyCost(query));
    }
}
