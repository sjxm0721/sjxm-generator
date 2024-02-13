package com.sjxm.generator;

import com.sjxm.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description:
 */
public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator+ "sjxm-generator-demo-projects"+ File.separator+"acm-template";
        String outputPath = projectPath;
        StaticGenerator.copyFilesByRecursive(inputPath,outputPath);


        String dynamicInputPath = projectPath + File.separator+"sjxm-generator-basic"+File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "acm-template/src/com/sjxm/acm/MainTemplate.java";

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("sjxm0721");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("result:");

        DynamicGenerator.doGenerate(dynamicInputPath,dynamicOutputPath,mainTemplateConfig);
    }
}
