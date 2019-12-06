package com.handongkeji.config.configuration;

import com.handongkeji.config.interceptor.EncodingInterceptor;
import com.handongkeji.config.interceptor.PageInterceptor;
import com.handongkeji.config.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Bean
    public EncodingInterceptor rechargeInterceptor() {
        return new EncodingInterceptor();
    }

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns(Arrays.asList("/**/auth", "/**/auth/**", "/**/auth/**"));
    }

    /**
     * @Author yws
     * @Description //TODO 重写虚拟路径
     * @Date 2019/7/8 0008 17:03
     * @Param
     * @return
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
        // registry.addResourceHandler("/**").addResourceLocations("/static");
    }
}
