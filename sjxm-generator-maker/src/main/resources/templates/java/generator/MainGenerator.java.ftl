package ${basePackage}.generator;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description:
 */
public class MainGenerator {

    public static void doGenerate(Object object) throws TemplateException, IOException {

        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String inputPath;
        String outputPath;

<#list fileConfig.files as fileInfo>
    inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
    outputPath = new File(outputRootPath,"${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "static">
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);
    <#else>
        DynamicGenerator.doGenerate(inputPath, outputPath,object);
    </#if>

</#list>
    }
}
