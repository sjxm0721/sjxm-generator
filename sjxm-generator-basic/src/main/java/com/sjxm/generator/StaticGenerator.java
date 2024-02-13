package com.sjxm.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description: 静态文件生成器
 * */
public class StaticGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator+ "sjxm-generator-demo-projects"+ File.separator+"acm-template";
        String outputPath = projectPath;
        copyFilesByHutool(inputPath,outputPath);
    }

    public static void copyFilesByHutool(String inputPath,String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    public static void copyFilesByRecursive(String inputPath,String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try{
            copyFileByRecursive(inputFile,outputFile);
        }catch (Exception e){
            System.out.println("文件复制失败");
            e.printStackTrace();
        }
    }


    private static void copyFileByRecursive(File inputFile,File outputFile) throws IOException {
        //区分是文件还是目录
        if(inputFile.isDirectory()){
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile,inputFile.getName());
            //如果是目录，首先创建目标目录
            if(!destOutputFile.exists()){
                destOutputFile.mkdirs();
            }
            //获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            //无子文件，直接结束
            if(ArrayUtil.isEmpty(files)){
                return;
            }
            for (File file : files) {
                //递归拷贝下一层文件
                copyFileByRecursive(file,destOutputFile);
            }
        }else{
            //是文件，直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(),destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
