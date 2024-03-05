package com.sjxm.maker.meta.template.model;

import com.sjxm.maker.meta.Meta;
import com.sjxm.maker.meta.template.model.TemplateMakerFileConfig;
import com.sjxm.maker.meta.template.model.TemplateMakerModelConfig;
import lombok.Data;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/26
 * @Description: 模板制作配置
 */
@Data
public class TemplateMakerConfig {

    private Long id;

    private Meta meta = new Meta();

    private String originProjectPath;

    private TemplateMakerFileConfig fileConfig = new TemplateMakerFileConfig();

    private TemplateMakerModelConfig modelConfig = new TemplateMakerModelConfig();

    private TemplateMakerOutputConfig templateMakerOutputConfig = new TemplateMakerOutputConfig();


}
