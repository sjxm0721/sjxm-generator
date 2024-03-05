package com.sjxm.maker.meta.template;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import com.sjxm.maker.meta.template.model.TemplateMakerConfig;
import org.junit.Test;

import java.io.File;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/26
 * @Description:
 */
public class TemplateMakerTest {

    @Test
    public void testMakeTemplateWithJson() {
        String configStr = ResourceUtil.readUtf8Str("templateMaker.json");
        TemplateMakerConfig templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        long id = TemplateMaker.makeTemplate(templateMakerConfig);
        System.out.println(id);
    }

    /**
     * 制作springboot模板
     */
    @Test
    public void makeSpringBootTemplate() {
        String rootPath = "example/springboot-init";
        String configStr = ResourceUtil.readUtf8Str(rootPath+ File.separator+"templateMaker.json");
        TemplateMakerConfig templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        long id = TemplateMaker.makeTemplate(templateMakerConfig);

         configStr = ResourceUtil.readUtf8Str(rootPath+ File.separator+"templateMaker1.json");
         templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
         TemplateMaker.makeTemplate(templateMakerConfig);

        configStr = ResourceUtil.readUtf8Str(rootPath+ File.separator+"templateMaker3.json");
        templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);
        System.out.println(id);
    }


}