package com.chen.vtg.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.net.URI;

/**
 * @Author : Chen
 * @Date : Create in 2019/8/11 17:56
 * @Description :
 * @Modify by :
 */
public class UrlUtil {
    public static ImmutablePair</**host*/String, /**uri*/String> formatUri(String uri) {
        URI u = URI.create(uri);
        String host = u.getHost();
        if (u.getPort() > 0 && u.getPort() != 80) {
            host = host + ":80";
        }

        String baseUri = u.getPath();
        if (u.getFragment() != null) {
            baseUri = baseUri + "#" + u.getFragment();
        }

        if (StringUtils.isNotBlank(baseUri)) {
            baseUri = host + baseUri;
        } else {
            baseUri = host;
        }

        return ImmutablePair.of(host, baseUri);
    }
}
