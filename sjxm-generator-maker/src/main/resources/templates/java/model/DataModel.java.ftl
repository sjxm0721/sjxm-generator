package ${basePackage}.model;

import lombok.Data;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description:数据模型
 */

@Data
public class DataModel {

<#list modelConfig.models as modelInfo>

    <#if modelInfo.description??>
        /**
         * ${modelInfo.description}
         */
    </#if>
    private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;

</#list>


}
