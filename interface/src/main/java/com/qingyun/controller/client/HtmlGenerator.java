package com.qingyun.controller.client;

import java.io.*;
import java.util.HashMap;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/30 0030 09:57
 */
@Slf4j
public class HtmlGenerator {
    public void testCreateHtml() throws Exception{
//①创建配置对象
        Configuration cfg = new Configuration();//注意:这里需要传递一个版本
        File f = new File("D:/");
//②读取模板文件夹
        cfg.setDirectoryForTemplateLoading(f);//设置要加载的模板文件的路径
//③设置模板的编码格式
        cfg.setDefaultEncoding("UTF-8");

//④获取模板对象
        Template template = cfg.getTemplate("a.html");//hello.ftl是模板名称

//⑤创建数据模型(这里使用map类型) --[数据模型可以是List、Map对象 注意:Map类型的key必须是String类型]
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "郑清");
        HashMap<String, Object> map2 = new HashMap<>();//map2存储的是a标签的href和显示名字
        map2.put("href","https://www.baidu.com");
        map2.put("name","百度");
        map.put("a", map2);

//⑥将模板和数据模型合并 --> 输出模板,生成文件
        PrintWriter pw = new PrintWriter(new File(f, "hello.html"));
        template.process(map, pw);//合并 map:数据模型 pw:输出流对象
        pw.close();//关闭流
    }

//    public static void main(String[] args) throws Exception {
//        HtmlGenerator ht = new HtmlGenerator();
//        ht.testCreateHtml();
//    }

}
