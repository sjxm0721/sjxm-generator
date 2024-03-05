package com.sjxm.maker.meta.template;

import cn.hutool.core.util.StrUtil;
import com.sjxm.maker.meta.Meta;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/27
 * @Description: 模板制作工具类
 */
public class TemplateMakerUtils {

    /**
     * 从未分组的文件中移除同名文件
     * @param fileInfoList
     * @return
     */
    public static List<Meta.FileConfig.FileInfo> removeGroupFilesFromRoot(List<Meta.FileConfig.FileInfo> fileInfoList){
        //1.获取所有分组
        List<Meta.FileConfig.FileInfo> groupFileInfoList = fileInfoList.stream().filter(fileInfo -> StrUtil.isNotBlank(fileInfo.getGroupKey())).collect(Collectors.toList());
        // 2. 获取所有分组内的文件列表
        List<Meta.FileConfig.FileInfo> groupInnerFileInfoList = groupFileInfoList.stream().flatMap(fileInfo -> fileInfo.getFiles().stream()).collect(Collectors.toList());
        //3.获取所有分组内的输入路径集合
        Set<String> fileInputPathSet = groupInnerFileInfoList.stream().map(Meta.FileConfig.FileInfo::getInputPath).collect(Collectors.toSet());
        //4.移除所有在集合内的外层文件
        return fileInfoList.stream().filter(fileInfo -> !fileInputPathSet.contains(fileInfo.getInputPath())).collect(Collectors.toList());
    }
}
