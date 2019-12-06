package com.qingyun.form.SysProblem;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Classname PmPharmacyTabooSaveForm
 * @Date 2019/1/16 15:58
 * @Created by Gavin
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Data
public class SysProblemSaveForm {

    private Long problemid;

    private String ids;

    @NotBlank(message = "请输入问题标题")
    private String problemtitle;

    @NotBlank(message = "请输入问题答案")
    private String problemanswer;

//    @NotNull(message = "请输入排序序号")
//    @Min(value = 1, message = "排序不能小于1")
//    @Max(value = 9999, message = "排序不能大于9999")
//    private Integer problemsort;

    @NotBlank(message = "请输入备注信息")
    private String problemremarks;

    @NotNull(message = "请输入显示状态")
    private Integer problemstats;
}
