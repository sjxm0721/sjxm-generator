package com.sjxm.maker.meta.template.model;

import lombok.Data;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/27
 * @Description:
 */
@Data
public class TemplateMakerOutputConfig {

    //从未分组的文件中移除组内的同名文件
    private boolean removeGroupFilesFromRoot = true;

}
