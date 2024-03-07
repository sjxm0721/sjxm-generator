package com.sjxm.maker.generator.main;

/**
 * @Author: 四季夏目
 * @Date: 2024/3/6
 * @Description:
 */
public class ZipGenerator extends GenerateTemplate{

    @Override
    protected String buildDist(String outputPath, String sourceCopyDestPath, String shellOutputFilePath, String jarPath) {
        String distPath = super.buildDist(outputPath, sourceCopyDestPath, shellOutputFilePath, jarPath);
        return super.buildZip(distPath);
    }
}
