package com.sjxm.cli;

import com.sjxm.cli.command.ConfigCommand;
import com.sjxm.cli.command.GenerateCommand;
import com.sjxm.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/14
 * @Description:
 */

@Command(name="sjxm",mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{

    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        //不输入子命令时，给出友好提示
        System.out.println("请输入具体命令，或者输入 --help 查看命令提示");
    }

    /**
     * 执行命令
     * @param args
     * @return
     */
    public Integer doExecute(String[] args){
        return commandLine.execute(args);
    }
}
