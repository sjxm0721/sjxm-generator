package com.sjxm.springbootinit.model.dto.generator;

import com.sjxm.maker.meta.Meta;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 使用代码生成器请求
 */
@Data
public class GeneratorMakeRequest implements Serializable {

    /**
     * 原数据信息
     */
    private Meta meta;

    /**
     * 模板文件压缩包路径
     */
    private String zipFilePath;

    private static final long serialVersionUID = 1L;

}