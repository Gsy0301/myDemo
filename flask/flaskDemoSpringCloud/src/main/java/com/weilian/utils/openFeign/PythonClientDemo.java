package com.weilian.utils.openFeign;

import feign.Headers;
import feign.RequestLine;

/**
 * <p> description </p>
 *
 * @author Guo S.Y.
 * @version V1.0
 * @since 2022/4/13-16:25
 */
public interface PythonClientDemo {
    /**
     * @param str 输入字符串
     * @return <p>@RequestLine 它只能标注在Method方法上。为请求定义HttpMethod和UriTemplate
     * 标注在方法上的就是一个HttpMethod，且写好了URI（可是绝对路径，也可是相对的，一般写后部分即可））
     * 表达式、用大括号括起来的值{expression}最终会用对应的@Param注解填进去（根据key匹配）</p>
     */
    @RequestLine("POST /demo")
    @Headers("Content-Type: application/json")
    String pythonDemo(String str);

    @RequestLine("POST /demo")
    @Headers("Content-Type: application/json")
    String pythonDemo();
}

