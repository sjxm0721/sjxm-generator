package com.sjxm.maker.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description: 静态文件生成器
 * */
public class StaticFileGenerator {

    public static void copyFilesByHutool(String inputPath,String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }
}
