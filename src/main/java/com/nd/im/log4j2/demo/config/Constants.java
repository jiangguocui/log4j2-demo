package com.nd.im.log4j2.demo.config;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * <p>Title: Constants </p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2016 </p>
 * <p>Company: ND Websoft Inc. </p>
 * <p>Create Time: 2016/7/29 0029 </p>
 *
 * @author weixb
 * @version 1.0
 */
public class Constants {

    //内部marker
    public static final Marker MARKER_INT = MarkerFactory.getMarker("INT");

    //外部marker
    public static final Marker MARKER_EXT = MarkerFactory.getMarker("EXT");
}
