package com.nd.im.log4j2.demo.controller;

import com.nd.im.log4j2.demo.component.AppException;
import com.nd.im.log4j2.demo.component.ErrorResolver;
import com.nd.im.log4j2.demo.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: DemoController </p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2016 </p>
 * <p>Company: ND Websoft Inc. </p>
 * <p>Create Time: 2016/7/29 0029 </p>
 *
 * @author weixb
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorResolver.class);

    @Resource
    private RestTemplate restTemplate;

    /**
     * 空指针异常
     *
     * @return
     */
    @RequestMapping(value = "/logs/null", method = RequestMethod.GET)
    public String testNull() {
        List<String> list = null;
        return list.get(0);
    }

    /**
     * 显性记录内部日志
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logs/internal", method = RequestMethod.GET)
    public void testInternalLog() {
        List<String> list = null;
        try {
            list.get(0);
        } catch (Exception ex) {
            // 记录内部日志
            logger.error(Constants.MARKER_INT, "failed:this is an internal error.", ex);
            throw new AppException("DEMO/BAD_REQUEST", "Bad Request.");
        }
    }

    /**
     * 显性记录外部日志
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logs/external", method = RequestMethod.GET)
    public void testExternalLog() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject("http://localhost:8080/demo/logs/null", Object.class);
        } catch (Exception ex) {
            // 记录外部日志，日志内容以ext.开头
            logger.error(Constants.MARKER_EXT, "failed:this is a error from the external.", ex);
            throw new AppException("DEMO/BAD_REQUEST", "Bad Request.");
        }
    }

    /**
     * 调用外部接口错误统一处理
     */
    @RequestMapping(value = "/logs/response_handle", method = RequestMethod.GET)
    public void testResponseHandle() {
        restTemplate.getForObject("http://localhost:8080/demo/logs/null", Object.class);
    }

}
