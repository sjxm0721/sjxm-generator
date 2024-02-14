package com.sjxm.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.sjxm.model.MainTemplateConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/14
 * @Description:
 */
@Command(name="config",mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{
    @Override
    public void run() {
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        for (Field field : fields) {
            System.out.println("字段类型："+field.getType());
            System.out.println("字段名称"+field.getName());
            System.out.println("---");
        }
    }
}
