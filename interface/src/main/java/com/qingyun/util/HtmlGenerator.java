package com.qingyun.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/30 0030 13:26
 */
public class HtmlGenerator {

    public void createHtml(Map<String,Object> mapContent,String pageTemplate,String pageName) throws Exception{
//①创建配置对象
        Configuration cfg = new Configuration();//注意:这里需要传递一个版本
        File f = new File("D:/handongkeji/html/template");
//②读取模板文件夹
        cfg.setDirectoryForTemplateLoading(f);//设置要加载的模板文件的路径
//③设置模板的编码格式
        cfg.setDefaultEncoding("UTF-8");
//④获取模板对象
        Template template = cfg.getTemplate(pageTemplate);//hello.ftl是模板名称
//⑤创建数据模型(这里使用map类型) --[数据模型可以是List、Map对象 注意:Map类型的key必须是String类型]

//⑥将模板和数据模型合并 --> 输出模板,生成文件
        PrintWriter pw = new PrintWriter(new File("D:/handongkeji/html", pageName+".html"));
        template.process(mapContent, pw);//合并 map:数据模型 pw:输出流对象
        pw.close();//关闭流
    }
}
