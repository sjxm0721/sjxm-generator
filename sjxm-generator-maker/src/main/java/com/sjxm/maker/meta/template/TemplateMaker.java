package com.sjxm.maker.meta.template;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.sjxm.maker.meta.Meta;
import com.sjxm.maker.meta.enums.FileGenerateEnum;
import com.sjxm.maker.meta.enums.FileTypeEnum;
import com.sjxm.maker.meta.template.enums.FileFilterRangeEnum;
import com.sjxm.maker.meta.template.enums.FileFilterRuleEnum;
import com.sjxm.maker.meta.template.model.FileFilterConfig;
import com.sjxm.maker.meta.template.model.TemplateMakerFileConfig;
import com.sjxm.maker.meta.template.model.TemplateMakerModelConfig;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/21
 * @Description: 模板制作工具
 */
public class TemplateMaker {


    /**
     * 制作模板
     * @param newMeta
     * @param originProjectPath
     * @param templateMakerFileConfig
     * @param templateMakerModelConfig
     * @param id
     * @return
     */
    private static long makeTemplate(Meta newMeta, String originProjectPath, TemplateMakerFileConfig templateMakerFileConfig, TemplateMakerModelConfig templateMakerModelConfig, Long id){
        //没有id则生成
        if(id == null){
            id = IdUtil.getSnowflakeNextId();
        }


        //复制目录
        String projectPath = System.getProperty("user.dir");
        String tempDirPath = projectPath+File.separator+".temp";
        String templatePath = tempDirPath+File.separator+id;
        if(!FileUtil.exist(templatePath)){
            FileUtil.mkdir(templatePath);
        }
        FileUtil.copy(originProjectPath,templatePath,true);

        // 一. 输入信息
        //1.项目基本信息

        //处理模型信息
        List<TemplateMakerModelConfig.ModelInfoConfig> models = templateMakerModelConfig.getModels();
        //转换成modelInfo对象
        List<Meta.ModelConfig.ModelInfo> inputModelInfoList = models.stream().map(modelInfoConfig -> {
            Meta.ModelConfig.ModelInfo modelInfo = new Meta.ModelConfig.ModelInfo();
            BeanUtil.copyProperties(modelInfoConfig, modelInfo);
            return modelInfo;
        }).collect(Collectors.toList());

        //本次新增的模型列表
        List<Meta.ModelConfig.ModelInfo> newModelInfoList = new ArrayList<>();
        //如果是模型组
        TemplateMakerModelConfig.ModelGroupConfig modelGroupConfig = templateMakerModelConfig.getModelGroupConfig();
        if(modelGroupConfig!=null){
            String condition = modelGroupConfig.getCondition();
            String groupKey = modelGroupConfig.getGroupKey();
            String groupName = modelGroupConfig.getGroupName();

            Meta.ModelConfig.ModelInfo groupModelInfo = new Meta.ModelConfig.ModelInfo();
            groupModelInfo.setCondition(condition);
            groupModelInfo.setGroupKey(groupKey);
            groupModelInfo.setGroupName(groupName);
            //模型全放到一个分组内
            groupModelInfo.setModels(inputModelInfoList);

            newModelInfoList = new ArrayList<>();
            newModelInfoList.add(groupModelInfo);
        }else{
            //不分组,增加所有的模型信息列表
            newModelInfoList.addAll(inputModelInfoList);
        }


                //2.输入文件信息
        String sourceRootPath = templatePath+File.separator+FileUtil.getLastPathEle(Paths.get(originProjectPath)).toString();

        List<TemplateMakerFileConfig.FileInfoConfig> fileInfoConfigList = templateMakerFileConfig.getFiles();

        List<Meta.FileConfig.FileInfo> newFileInfoList = new ArrayList<>();
        for (TemplateMakerFileConfig.FileInfoConfig fileInfoConfig : fileInfoConfigList) {
            String fileInputPath = fileInfoConfig.getPath();
            String fileInputAbsolutePath = sourceRootPath+File.separator+fileInputPath;

            List<File> fileList = FileFilter.doFilter(fileInputAbsolutePath, fileInfoConfig.getFileFilterConfigList());

            for (File file : fileList) {
                Meta.FileConfig.FileInfo fileInfo = makeFileTemplate(templateMakerModelConfig, sourceRootPath,file);
                newFileInfoList.add(fileInfo);
            }
        }

        //如果是文件组
        TemplateMakerFileConfig.FileGroupConfig fileGroupConfig = templateMakerFileConfig.getFileGroupConfig();
        if(fileGroupConfig!=null){
            String condition = fileGroupConfig.getCondition();
            String groupKey = fileGroupConfig.getGroupKey();
            String groupName = fileGroupConfig.getGroupName();

            Meta.FileConfig.FileInfo groupFileInfo = new Meta.FileConfig.FileInfo();
            groupFileInfo.setCondition(condition);
            groupFileInfo.setGroupKey(groupKey);
            groupFileInfo.setGroupName(groupName);
            //文件全放到一个分组内
            groupFileInfo.setFiles(newFileInfoList);

            newFileInfoList = new ArrayList<>();
            newFileInfoList.add(groupFileInfo);
        }

        //三、生成配置文件
        String metaOutputPath = sourceRootPath + File.separator + "meta.json";

        //已有meta文件，不是第一次制作，在meta基础上修改
        if(FileUtil.exist(metaOutputPath)){
            newMeta = JSONUtil.toBean(FileUtil.readUtf8String(metaOutputPath), Meta.class);
            //1.追加配置操作
            List<Meta.FileConfig.FileInfo> fileInfoList = newMeta.getFileConfig().getFiles();
            fileInfoList.addAll(newFileInfoList);

            List<Meta.ModelConfig.ModelInfo> modelInfoList = newMeta.getModelConfig().getModels();
            modelInfoList.addAll(newModelInfoList);

            //配置去重
            newMeta.getFileConfig().setFiles(distinctFiles(fileInfoList));
            newMeta.getModelConfig().setModels(distinctModels(modelInfoList));

        }else{

            Meta.FileConfig fileConfig = new Meta.FileConfig();
            fileConfig.setSourceRootPath(sourceRootPath);

            List<Meta.FileConfig.FileInfo> fileInfoList = new ArrayList<>();

            fileInfoList.addAll(newFileInfoList);

            fileConfig.setFiles(fileInfoList);
            newMeta.setFileConfig(fileConfig);


            Meta.ModelConfig modelConfig = new Meta.ModelConfig();

            List<Meta.ModelConfig.ModelInfo> modelInfoList = new ArrayList<>();
            modelInfoList.addAll(newModelInfoList);
            modelConfig.setModels(modelInfoList);
            newMeta.setModelConfig(modelConfig);
        }
        //2.输出元信息文件
        FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(newMeta),metaOutputPath);
        return id;
    }

    /**
     * 制作模板文件
     * @param templateMakerModelConfig
     * @param sourceRootPath
     * @param inputFile
     * @return
     */
    private static Meta.FileConfig.FileInfo makeFileTemplate(TemplateMakerModelConfig templateMakerModelConfig, String sourceRootPath,File inputFile) {
        String fileInputPath = inputFile.getAbsolutePath().replace(sourceRootPath+"/","");
        String fileOutputPath = fileInputPath + ".ftl";


        //二、使用字符串替换，生成模板文件
        String fileInputAbsolutePath = inputFile.getAbsolutePath();
        String fileOutputAbsolutePath = inputFile.getAbsolutePath()+".ftl";

        String fileContent;

        //已有模板文件，不是第一次制作，则在原有模板文件上再挖坑
        if(FileUtil.exist(fileOutputAbsolutePath)){
            fileContent = FileUtil.readUtf8String(fileOutputAbsolutePath);
        }else{
            fileContent = FileUtil.readUtf8String(fileInputAbsolutePath);
        }

        //支持多个模型，对于同一个文件的内容，便利模型进行多轮替换
        List<TemplateMakerModelConfig.ModelInfoConfig> models = templateMakerModelConfig.getModels();
        TemplateMakerModelConfig.ModelGroupConfig modelGroupConfig = templateMakerModelConfig.getModelGroupConfig();
        String newFileContent = fileContent;
        String replacement;
        for (TemplateMakerModelConfig.ModelInfoConfig modelInfoConfig : models) {
            String fieldName = modelInfoConfig.getFieldName();
            //模型配置
            //是否是分组
            if(modelGroupConfig == null){
                replacement = String.format("${%s}", fieldName);
            }else{
                String groupKey = modelGroupConfig.getGroupKey();
                replacement = String.format("${%s.%s}", groupKey,fieldName);
            }
            newFileContent = StrUtil.replace(fileContent, modelInfoConfig.getReplaceText(),replacement);
        }


        //文件配置信息
        Meta.FileConfig.FileInfo fileInfo = new Meta.FileConfig.FileInfo();
        fileInfo.setInputPath(fileInputPath);
        fileInfo.setType(FileTypeEnum.FILE.getValue());
        fileInfo.setGenerateType(FileGenerateEnum.DYNAMIC.getValue());

        //和原文件一致，没有挖坑，静态生成
        if(newFileContent.equals(fileContent)){
            fileInfo.setOutputPath(fileInputPath);
            fileInfo.setGenerateType(FileGenerateEnum.STATIC.getValue());
        }else{
            fileInfo.setOutputPath(fileOutputPath);
            fileInfo.setGenerateType(FileGenerateEnum.DYNAMIC.getValue());

            //输出模板文件
            FileUtil.writeUtf8String(newFileContent,fileOutputAbsolutePath);
        }
        return fileInfo;
    }

    public static void main(String[] args) {
        Meta meta = new Meta();
        meta.setName("acm-template-generator");
        meta.setDescription("ACM项目模板生成器");
        //指定原始项目路径
        String projectPath = System.getProperty("user.dir");
        //要挖坑的项目根路径
        String originProjectPath = new File(projectPath).getParent() + File.separator + "sjxm-generator-demo-projects/springboot-init";
        //要挖坑的文件
        List<String> fileInputPathList = new ArrayList<>();
        String fileInputPath1 = "src/main/java/com/yupi/springbootinit/common";
        String fileInputPath2 = "src/main/resources/application.yml";
        fileInputPathList.add(fileInputPath1);
        fileInputPathList.add(fileInputPath2);
        //输入模型参数信息
//        Meta.ModelConfig.ModelInfo modelInfo = new Meta.ModelConfig.ModelInfo();
//        modelInfo.setFieldName("outputText");
//        modelInfo.setType("String");
//        modelInfo.setDefaultValue("Sum = ");

        //输入模型参数信息二
        Meta.ModelConfig.ModelInfo modelInfo = new Meta.ModelConfig.ModelInfo();
        modelInfo.setFieldName("className");
        modelInfo.setType("String");

        //替换变量
//        String searchStr = "Sum: ";
        String searchStr = "BaseResponse";


        //文件过滤配置
        TemplateMakerFileConfig.FileInfoConfig fileInfoConfig1 = new TemplateMakerFileConfig.FileInfoConfig();
        fileInfoConfig1.setPath(fileInputPath1);
        List<FileFilterConfig> fileFilterConfigList = new ArrayList<>();
        FileFilterConfig fileFilterConfig = FileFilterConfig.builder().
                range(FileFilterRangeEnum.FILE_NAME.getValue())
                .rule(FileFilterRuleEnum.CONTAINS.getValue())
                .value("Base")
                .build();
        fileFilterConfigList.add(fileFilterConfig);
        fileInfoConfig1.setFileFilterConfigList(fileFilterConfigList);

        TemplateMakerFileConfig.FileInfoConfig fileInfoConfig2 = new TemplateMakerFileConfig.FileInfoConfig();
        fileInfoConfig2.setPath(fileInputPath2);

        List<TemplateMakerFileConfig.FileInfoConfig> fileInfoConfigList = Arrays.asList(fileInfoConfig1,fileInfoConfig2);

        TemplateMakerFileConfig templateMakerFileConfig = new TemplateMakerFileConfig();

        templateMakerFileConfig.setFiles(fileInfoConfigList);

        //分组配置
        TemplateMakerFileConfig.FileGroupConfig fileGroupConfig = new TemplateMakerFileConfig.FileGroupConfig();
        fileGroupConfig.setCondition("outputText");
        fileGroupConfig.setGroupKey("test");
        fileGroupConfig.setGroupName("测试分组");
        templateMakerFileConfig.setFileGroupConfig(fileGroupConfig);

//        long id = makeTemplate(meta, originProjectPath, templateMakerFileConfig, modelInfo, searchStr, 1761595950539358208L);
//        System.out.println(id);

    }

    /**
     * 文件去重
     * @param fileInfoList
     * @return
     */
    private static List<Meta.FileConfig.FileInfo> distinctFiles(List<Meta.FileConfig.FileInfo> fileInfoList){
        //1. 将所有文件配置（fileInfo） 分为有分组和无分组

        //先处理分组的文件
        Map<String, List<Meta.FileConfig.FileInfo>> groupKeyFileInfoListMap = fileInfoList.stream().filter(fileInfo -> StrUtil.isNotBlank(fileInfo.getGroupKey())).collect(Collectors.groupingBy(Meta.FileConfig.FileInfo::getGroupKey)
        );

        //合并后的fileInfo map
        Map<String, Meta.FileConfig.FileInfo> groupKeyMergedFileInfoMap = new HashMap<>();

        //2. 同组内配置合并
        for (Map.Entry<String, List<Meta.FileConfig.FileInfo>> entry : groupKeyFileInfoListMap.entrySet()) {
            List<Meta.FileConfig.FileInfo> tempFileInfoList = entry.getValue();
            List<Meta.FileConfig.FileInfo> newFileInfoList = new ArrayList<>(tempFileInfoList.stream()
                    .flatMap(fileInfo -> fileInfo.getFiles().stream()).collect(Collectors.toMap(Meta.FileConfig.FileInfo::getInputPath,o->o,(exist,replacement)->replacement)).values());

            //使用新的group配置
            Meta.FileConfig.FileInfo newFileInfo = CollUtil.getLast(tempFileInfoList);
            newFileInfo.setFiles(newFileInfoList);
            String groupKey = entry.getKey();
            groupKeyMergedFileInfoMap.put(groupKey,newFileInfo);
        }

        //3. 创建新的文件配置列表（结果列表），先将合并后的分组添加到结果列表
        ArrayList<Meta.FileConfig.FileInfo> resultList = new ArrayList<>(groupKeyMergedFileInfoMap.values());

        //4.再将无分组的问价配置列表添加到结果列表
        resultList.addAll(new ArrayList<>(fileInfoList.stream().filter(fileInfo -> StrUtil.isBlank(fileInfo.getGroupKey())).collect(Collectors.toMap(Meta.FileConfig.FileInfo::getInputPath,o->o,(exist,replacement)->replacement)).values()));

        return resultList;
    }

    /**
     * 模型去重
     * @param modelInfoList
     * @return
     */
    private static List<Meta.ModelConfig.ModelInfo> distinctModels(List<Meta.ModelConfig.ModelInfo> modelInfoList){

        //1. 将所有模型配置（modelInfo） 分为有分组和无分组

        //先处理分组的模型
        Map<String, List<Meta.ModelConfig.ModelInfo>> groupKeyModelInfoListMap = modelInfoList.stream().filter(modelInfo -> StrUtil.isNotBlank(modelInfo.getGroupKey())).collect(Collectors.groupingBy(Meta.ModelConfig.ModelInfo::getGroupKey)
        );

        //合并后的modelInfo map
        Map<String, Meta.ModelConfig.ModelInfo> groupKeyMergedModelInfoMap = new HashMap<>();

        //2. 同组内配置合并
        for (Map.Entry<String, List<Meta.ModelConfig.ModelInfo>> entry : groupKeyModelInfoListMap.entrySet()) {
            List<Meta.ModelConfig.ModelInfo> tempModelInfoList = entry.getValue();
            List<Meta.ModelConfig.ModelInfo> newModelInfoList = new ArrayList<>(tempModelInfoList.stream()
                    .flatMap(modelInfo -> modelInfo.getModels().stream()).collect(Collectors.toMap(Meta.ModelConfig.ModelInfo::getFieldName,o->o,(exist,replacement)->replacement)).values());

            //使用新的group配置
            Meta.ModelConfig.ModelInfo newModelInfo = CollUtil.getLast(tempModelInfoList);
            newModelInfo.setModels(newModelInfoList);
            String groupKey = entry.getKey();
            groupKeyMergedModelInfoMap.put(groupKey,newModelInfo);
        }

        //3. 创建新的模型配置列表（结果列表），先将合并后的分组添加到结果列表
        ArrayList<Meta.ModelConfig.ModelInfo> resultList = new ArrayList<>(groupKeyMergedModelInfoMap.values());

        //4.再将无分组的问价配置列表添加到结果列表
        resultList.addAll(new ArrayList<>(modelInfoList.stream().filter(modelInfo -> StrUtil.isBlank(modelInfo.getGroupKey())).collect(Collectors.toMap(Meta.ModelConfig.ModelInfo::getFieldName,o->o,(exist,replacement)->replacement)).values()));

        return resultList;
    }
}
