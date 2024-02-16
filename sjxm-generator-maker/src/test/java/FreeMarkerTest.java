import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description:
 */
public class FreeMarkerTest {
    @Test
    public void test() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        //指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        configuration.setNumberFormat("0.######");

        //设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        //创建模板对象，加载指定模板
        Template template = configuration.getTemplate("");

        //数据模型
        Map<String,Object> map = new HashMap<>();

        Writer fileWriter = new FileWriter("");

        template.process(map,fileWriter);

        //关闭文件流
        fileWriter.close();
    }
}
