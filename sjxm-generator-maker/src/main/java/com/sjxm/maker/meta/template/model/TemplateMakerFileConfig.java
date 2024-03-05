package com.sjxm.maker.meta.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/25
 * @Description:
 */
@Data
public class TemplateMakerFileConfig {

    private List<FileInfoConfig> files;

    private FileGroupConfig fileGroupConfig;

    @NoArgsConstructor
    @Data
    public static class FileInfoConfig{
        private String path;

        private String condition;

        private List<FileFilterConfig> fileFilterConfigList;
    }

    @NoArgsConstructor
    @Data
    public static class FileGroupConfig{
        private String condition;
        private String groupKey;
        private String groupName;
    }
}
