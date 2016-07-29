package com.nd.im.log4j2.demo.config;

import com.nd.gaea.rest.AbstractWafWebApplicationInitializer;

/**
 * <p>Title: WafWebApplicationInitializer </p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2016 </p>
 * <p>Company: ND Websoft Inc. </p>
 * <p>Create Time: 2016/7/29 0029 </p>
 *
 * @author weixb
 * @version 1.0
 */
public class WafWebApplicationInitializer extends AbstractWafWebApplicationInitializer {

    // 设置全局配置，包括安全以及数据仓储等
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApiSecurityConfig.class, AppConfig.class};
    }

    // 设置web配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }
}
