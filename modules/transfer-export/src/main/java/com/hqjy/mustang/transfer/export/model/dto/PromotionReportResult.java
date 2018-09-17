package com.hqjy.mustang.transfer.export.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xyq
 * @date create on 2018/9/17
 * @apiNote
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "招转推广报表数据输出对象")
public class PromotionReportResult {

    private List<PromotionReportData> list;

    private PromotionReportTotal total;
}
