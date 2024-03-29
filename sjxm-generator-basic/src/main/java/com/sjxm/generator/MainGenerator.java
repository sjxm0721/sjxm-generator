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

    public static void doGenerate(MainTemplateConfig mainTemplateConfig) throws TemplateException, IOException {

        String inputRootPath = "/Users/sijixiamu/code/java_code/javaTest/yupicloud/sjxm-generator/sjxm-generator-demo-projects/acm-template-pro";
        String outputRootPath = "/Users/sijixiamu/code/java_code/javaTest/yupicloud/sjxm-generator";

        String inputPath;
        String outputPath;

        //生成动态文件
        inputPath = new File(inputRootPath, "src/com/sjxm/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/com/sjxm/acm/MainTemplate.java").getAbsolutePath();

        DynamicGenerator.doGenerate(inputPath, outputPath,mainTemplateConfig);


        //生成静态文件
        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath,".gitignore").getAbsolutePath();

        StaticGenerator.copyFilesByHutool(inputPath,outputPath);

        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath,"README.md").getAbsolutePath();

        StaticGenerator.copyFilesByHutool(inputPath,outputPath);

    }
}
