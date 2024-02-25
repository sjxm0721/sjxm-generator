package com.sjxm.generator;

import freemarker.template.TemplateException;

import com.sjxm.model.DataModel;

import java.io.File;
import java.io.IOException;




/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description:
 */
public class MainGenerator {

    public static void doGenerate(DataModel object) throws TemplateException, IOException {

        String inputRootPath = ".source/acm-template-pro";
        String outputRootPath = "/Users/sijixiamu/code/java_code/javaTest/yupicloud/sjxm-generator/generated";

        String inputPath;
        String outputPath;

        boolean needGit = object.needGit;
        boolean loop = object.loop;
            String author = object.mainTemplate.author;
            String outputText = object.mainTemplate.outputText;



    inputPath = new File(inputRootPath, "src/com/sjxm/acm/MainTemplate.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/com/sjxm/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);



        //groupKey = git
            if(needGit){
        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath,".gitignore").getAbsolutePath();
            StaticGenerator.copyFilesByHutool(inputPath,outputPath);
        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath,"README.md").getAbsolutePath();
            StaticGenerator.copyFilesByHutool(inputPath,outputPath);
            }

    }
}
