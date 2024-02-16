package com.sjxm.maker.generator.file;

import com.sjxm.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description:
 */
public class FileGenerator {

    public static void doGenerate(DataModel dataModel) throws TemplateException, IOException {
//        String projectPath = System.getProperty("user.dir");
//        String inputPath = projectPath + File.separator+ "sjxm-generator-demo-projects"+ File.separator+"acm-template";
//        String outputPath = projectPath;
//        StaticGenerator.copyFilesByRecursive(inputPath,outputPath);
//
//
//        String dynamicInputPath = projectPath + File.separator+"sjxm-generator-basic"+File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
//        String dynamicOutputPath = projectPath + File.separator + "acm-template/src/com/sjxm/acm/MainTemplate.java";
//
//        StaticGenerator.java.ftl.doGenerate(dynamicInputPath,dynamicOutputPath,mainTemplateConfig);

        String projectPath = System.getProperty("user.dir");
        // 整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        String inputPath = new File(parentFile, "sjxm-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        // 生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        // 生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/sjxm/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, dataModel);
    }
}
