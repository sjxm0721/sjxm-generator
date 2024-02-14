package com.sjxm.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/14
 * @Description:
 */
@Command(name = "list",mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {
    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");
//        File parentFile = new File(projectPath).getParentFile();
        //输入路径
        String inputPath = new File("sjxm-generator-demo-projects/acm-template").getAbsolutePath();
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file);
        }
    }
}
