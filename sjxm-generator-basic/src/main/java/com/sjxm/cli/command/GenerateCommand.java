package com.sjxm.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.sjxm.generator.MainGenerator;
import com.sjxm.model.MainTemplateConfig;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/14
 * @Description:
 */
@Data
@Command(name="generate",mixinStandardHelpOptions = true)
public class GenerateCommand implements Callable {


    /**
     * 作者
     */
    @Option(names = {"-a","--author"},description = "作者名称",arity = "0..1",interactive = true,echo = true)
    private String author = "四季夏目";

    /**
     * 输出文本
     */
    @Option(names = {"-o","--outputText"},description = "输出文本",arity = "0..1",interactive = true,echo = true)
    private String outputText = "输出结果：";

    /**
     * 是否循环
     */
    @Option(names = {"-l","--loop"},description = "是否循环",arity = "0..1",interactive = true,echo = true)
    private boolean loop;

    @Override
    public Integer call() throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this,mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
