package com.sjxm.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description: 动态文件生成器
 */
public class DynamicFileGenerator {

    /**
     *
     * @param inputPath 文件输入路径
     * @param outputPath 文件输出路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath,String outputPath,Object model) throws IOException,TemplateException{
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        //指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        configuration.setNumberFormat("0.######");

        //设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        //创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        //如果文件不存在则创建目录
        if(!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }

        //生成
        Writer fileWriter = new FileWriter(outputPath);

        template.process(model,fileWriter);

        //关闭文件流
        fileWriter.close();
    }
}
