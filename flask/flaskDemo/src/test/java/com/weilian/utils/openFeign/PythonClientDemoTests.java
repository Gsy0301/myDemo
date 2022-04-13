package com.weilian.utils.openFeign;

import feign.Feign;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p> description </p>
 *
 * @author Guo S.Y.
 * @version V1.0
 * @since 2022/4/13-16:00
 */
@SpringBootTest
public class PythonClientDemoTests {
    @Test
    public void pythonTest() {
        //绑定客户端接口
        PythonClientDemo pythonClient = Feign.builder().target(PythonClientDemo.class, "http://localhost:5001");
        System.out.println("pythonClient : " + pythonClient);
        String result = pythonClient.pythonDemo("hello word");
        System.out.println("result = " + result);
    }
}
