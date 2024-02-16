package com.sjxm.maker.generator;

import java.io.*;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/16
 * @Description:
 */
public class JarGenerator {

    public static void doGenerate(String projectDir) throws InterruptedException, IOException {

        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String mavenCommand = "mvn clean package -DskipTests=true";

        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));
        Process process = processBuilder.start();

        //获取命令输出
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = bufferedReader.readLine())!=null){
            System.out.println(line);
        }

        int exitCode = process.waitFor();

        System.out.println("命令执行结束，退出码："+exitCode);
    }
}
