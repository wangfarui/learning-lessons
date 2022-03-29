package com.wfr.learning.resource.utls;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * 基于 {@link org.springframework.core.io.Resource} 实现的资源管理工具类
 *
 * @author wangfarui
 * @since 2022/3/29
 * @see EncodedResource
 * @see IOUtils
 */
public abstract class ResourceUtils {

    public static final String DEFAULT_CHARSET;

    static {
        DEFAULT_CHARSET = Charset.defaultCharset().name();
    }

    /**
     * {@link EncodedResource} Spring 编码资源类
     * <p> EncodedResource 可以获取 {@link java.io.InputStream}、{@link java.io.Reader}
     */
    public static String getContent(Resource resource) {
        EncodedResource encodedResource = new EncodedResource(resource, DEFAULT_CHARSET);
        try {
            return getContentByInputStream(encodedResource.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getContentByInputStream(InputStream inputStream) throws IOException {
        return IOUtils.toString(inputStream, Charset.defaultCharset());
    }

    public static String getContentByReader(Reader reader) throws IOException {
        return IOUtils.toString(reader);
    }
}
