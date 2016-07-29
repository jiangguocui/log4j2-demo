package com.nd.im.log4j2.demo.component;

import com.nd.gaea.client.WafResourceAccessException;
import com.nd.gaea.rest.exceptions.WafRestErrorResolver;
import com.nd.gaea.rest.exceptions.rest.RestErrorMappings;
import com.nd.im.log4j2.demo.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ErrorResolver extends WafRestErrorResolver {

    private static Logger logger = LoggerFactory.getLogger(ErrorResolver.class);

    //内部
    private static final Integer EXCEPTION_TYPE_INTERNAL = 1;

    //外部
    private static final Integer EXCEPTION_TYPE_EXT = 2;

    //忽略
    private static final Integer EXCEPTION_TYPE_INGORE = 3;


    protected final Map<Class, Integer> exceptionMapping = new HashMap<>();


    public ErrorResolver() {

        for (RestErrorMappings mapping : RestErrorMappings.values()) {
            final Class clazz = mapping.getThrowableClass();

            if (clazz == WafResourceAccessException.class ) {
                // WafResourceAccessException记录到外部日志
                exceptionMapping.put(clazz, EXCEPTION_TYPE_EXT);
            }
            else if (clazz == Exception.class) {
                //程序崩溃异常，记录到内部错误日志。
                exceptionMapping.put(clazz, EXCEPTION_TYPE_INTERNAL);
            }
            else {
                exceptionMapping.put(clazz, EXCEPTION_TYPE_INGORE);
            }

            addHandler(mapping.getThrowableClass(), mapping.getHandler());
        }
        
        //业务层出现异常日志自行记录
        exceptionMapping.put(AppException.class, EXCEPTION_TYPE_INGORE);

    }

    @Override
    public boolean process(Throwable throwable, HttpServletRequest request, HttpServletResponse response) {

        for (Class clazz = throwable.getClass(); clazz != Throwable.class; clazz = clazz.getSuperclass()) {
            final Integer type = exceptionMapping.get(clazz);
            if (type != null) {
                if (type != EXCEPTION_TYPE_INGORE) {
                    logger.error(type == EXCEPTION_TYPE_EXT ? Constants.MARKER_EXT : Constants.MARKER_INT,  throwable.getMessage(), throwable);
                } else {
                    logger.debug(throwable.getMessage(), throwable);
                }

                break;
            }
        }

        return this.resolver(throwable, request, response);

    }
}
