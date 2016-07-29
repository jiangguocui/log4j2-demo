package com.nd.im.log4j2.demo.config;

import com.nd.gaea.rest.config.WafWebMvcConfigurerAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <p>Title: WebConfig </p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2016 </p>
 * <p>Company: ND Websoft Inc. </p>
 * <p>Create Time: 2016/7/29 0029 </p>
 *
 * @author weixb
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.nd.im.log4j2.demo.controller"})
public class WebConfig extends WafWebMvcConfigurerAdapter {
}
