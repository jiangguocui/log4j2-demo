package com.nd.im.log4j2.demo.config;

import com.nd.gaea.rest.exceptions.WafErrorResolver;
import com.nd.im.log4j2.demo.component.ErrorResolver;
import com.nd.im.log4j2.demo.component.ResponseErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Title: AppConfig </p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2016 </p>
 * <p>Company: ND Websoft Inc. </p>
 * <p>Create Time: 2016/7/29 0029 </p>
 *
 * @author weixb
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = {"com.nd.im.log4j2.demo.component", "com.nd.im.log4j2.demo.service"})
@PropertySource(value = {"classpath:waf.properties"})
public class AppConfig {

    @Bean
    public WafErrorResolver wafErrorResolver() {
        return new ErrorResolver();
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler());

        return restTemplate;

    }
}
