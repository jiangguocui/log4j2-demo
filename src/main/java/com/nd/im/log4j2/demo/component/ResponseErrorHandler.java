package com.nd.im.log4j2.demo.component;

import com.nd.im.log4j2.demo.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.IOException;

/**
 * <p>Title: ResponseErrorHandler </p>
 * <p>Description: 外部错误统一处理 </p>
 * <p>Copyright: Copyright (c) 2016 </p>
 * <p>Company: ND Websoft Inc. </p>
 * <p>Create Time: 2016/7/29 0029 </p>
 *
 * @author weixb
 * @version 1.0
 */
public class ResponseErrorHandler extends DefaultResponseErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        try {
            super.handleError(response);
        } catch (HttpStatusCodeException ex) {
            logger.error(Constants.MARKER_EXT, "http failed,  status:{},  body:{}", ex.getStatusCode(), ex.getResponseBodyAsString());
            throw new AppException("DEMO/REMOTE_REQUEST_FAILED", "The remote request failed.");
        } catch (Exception ex) {
            logger.error(Constants.MARKER_EXT, "http failed", ex);
            throw new AppException("DEMO/REMOTE_REQUEST_FAILED", "The remote request failed.");
        }
    }
}
