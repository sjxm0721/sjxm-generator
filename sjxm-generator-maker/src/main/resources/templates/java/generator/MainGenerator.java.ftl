package ${basePackage}.generator;

import freemarker.template.TemplateException;

import ${basePackage}.model.DataModel;

import java.io.File;
import java.io.IOException;


<#macro generateFile indent fileInfo>
    ${indent}inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
    ${indent}outputPath = new File(outputRootPath,"${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "static">
        ${indent}StaticGenerator.copyFilesByHutool(inputPath,outputPath);
    <#else>
        ${indent}DynamicGenerator.doGenerate(inputPath, outputPath,object);
    </#if>
</#macro>


/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description:
 */
public class MainGenerator {

    public static void doGenerate(DataModel object) throws TemplateException, IOException {

        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String inputPath;
        String outputPath;

<#list modelConfig.models as modelInfo>
<#--    有分组-->
    <#if modelInfo.groupKey??>
        <#list modelInfo.models as subModelInfo>
            ${subModelInfo.type} ${subModelInfo.fieldName} = object.${modelInfo.groupKey}.${subModelInfo.fieldName};
        </#list>
    <#else>
        ${modelInfo.type} ${modelInfo.fieldName} = object.${modelInfo.fieldName};
    </#if>
</#list>

<#list fileConfig.files as fileInfo>
    <#if fileInfo.groupKey??>
        //groupKey = ${fileInfo.groupKey}
        <#if fileInfo.condition??>
            if(${fileInfo.condition}){
        </#if>
            <#list fileInfo.files as fileInfo>
                <@generateFile indent="    "  fileInfo=fileInfo />
            </#list>
        <#if fileInfo.condition??>
            }
        </#if>
        <#else>

            <#if fileInfo.condition??>
                if(${fileInfo.condition}){
            </#if>

            <@generateFile indent="" fileInfo=fileInfo/>

            <#if fileInfo.condition??>
                }
            </#if>

    </#if>

</#list>
    }
}
