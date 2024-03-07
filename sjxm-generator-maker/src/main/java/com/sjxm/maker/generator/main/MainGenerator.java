package com.sjxm.maker.generator.main;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/15
 * @Description:
 */
public class MainGenerator extends GenerateTemplate {

    @Override
    protected String buildDist(String outputPath, String sourceCopyDestPath, String shellOutputFilePath, String jarPath) {
        System.out.println("不要给我输出dist了");
        return "";
    }
}
