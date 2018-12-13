package com.tt.springbootorder.config.page;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ClassName: com.tt.springbootorder.config.page.PageHelperConfig
 * @Description:  分页配置类(这里是在application.properties配置的基础上进一步起作用)
 *                  配置在配置文件里比较方便，当然配置在代码里支持热启动。
 * @Author:      Administrator
 * @CreateDate: 2018/12/6 15:22
 * @UpdateUser:   Administrator
 * @Version:        1.0
 **/
@Configuration
public class PageHelperConfig {

    @Bean
    public PageHelper getPageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
