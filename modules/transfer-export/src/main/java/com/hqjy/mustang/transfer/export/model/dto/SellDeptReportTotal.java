package com.hqjy.mustang.transfer.export.model.dto;

import com.hqjy.mustang.common.base.annotation.ExcelAttribute;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author gmm
 * @date create on 2018/10/22
 * @apiNote
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "部门电销排行报表数据对象")
public class SellDeptReportTotal {

    @ApiModelProperty(value = "部门")
    @ExcelAttribute(name = "部门", column = "A")
    private String deptName;

    @ApiModelProperty(value = "有效商机上门率")
    @ExcelAttribute(name = "有效商机上门率", column = "B")
    private String visitValidRate;

    @ApiModelProperty(value = "上门量")
    @ExcelAttribute(name = "上门量", column = "C")
    private int visitNum;

    @ApiModelProperty(value = "今日预约上门量")
    @ExcelAttribute(name = "今日预约上门量", column = "D")
    private int visitTodayAppointNum;

    @ApiModelProperty(value = "明日预约上门量")
    @ExcelAttribute(name = "名日预约上门量", column = "E")
    private int visitTomorrowAppointNum;

    @ApiModelProperty(value = "商机量")
    @ExcelAttribute(name = "商机量", column = "F")
    private int businessNum;

    @ApiModelProperty(value = "商机有效量")
    @ExcelAttribute(name = "商机有效量", column = "G")
    private int validNum;

    @ApiModelProperty(value = "商机有效率")
    @ExcelAttribute(name = "商机有效率", column = "H")
    private String validRate;

    @ApiModelProperty(value = "实际上门率")
    @ExcelAttribute(name = "实际上门率", column = "I")
    private String visitRate;

    @ApiModelProperty(value = "成交量")
    @ExcelAttribute(name = "成交量", column = "J")
    private int dealNum;

    @ApiModelProperty(value = "有效上门量")
    private int visitValidNum;
}
