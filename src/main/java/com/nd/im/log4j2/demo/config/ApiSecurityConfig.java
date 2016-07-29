package com.nd.im.log4j2.demo.config;

import com.nd.gaea.rest.config.WafWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * <p>Title: ApiSecurityConfig </p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2016 </p>
 * <p>Company: ND Websoft Inc. </p>
 * <p>Create Time: 2016/7/29 0029 </p>
 *
 * @author weixb
 * @version 1.0
 */
@Configuration
@EnableWebMvcSecurity
public class ApiSecurityConfig  extends WafWebSecurityConfigurerAdapter {

    /**
     * 此处统一配置安全访问控制
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void onConfigure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
    }
}
