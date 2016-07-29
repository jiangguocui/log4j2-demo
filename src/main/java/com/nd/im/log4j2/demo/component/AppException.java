package com.nd.im.log4j2.demo.component;

import com.nd.gaea.WafException;

/**
 * <p>Title: AppException </p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2016 </p>
 * <p>Company: ND Websoft Inc. </p>
 * <p>Create Time: 2016/7/29 0029 </p>
 *
 * @author weixb
 * @version 1.0
 */
public class AppException extends WafException {
    public AppException(String code, String message) {
        super(code, message);
    }
}
