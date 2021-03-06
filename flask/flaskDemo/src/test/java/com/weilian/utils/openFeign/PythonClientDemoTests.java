package com.weilian.utils.openFeign;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.weilian.entity.Exce;
import com.weilian.entity.Stu;
import feign.Feign;
import feign.Request;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        PythonClientDemo pythonClient = Feign.builder()
                .options(new Request.Options(3, TimeUnit.MINUTES, 3, TimeUnit.MINUTES, true))
                .target(PythonClientDemo.class, "http://localhost:5000");
        System.out.println("pythonClient : " + pythonClient);
        Stu stu = new Stu();
        stu.setName("tom");
        stu.setAge(11);
        Gson gson = new Gson();
        String s = gson.toJson(stu);
        String result = pythonClient.pythonDemo(s);
        System.out.println("result = " + result);

        // json
        // Exce exce = new Gson().fromJson(result, Exce.class);
        // System.out.println("exce = " + exce.toString());

        // json数组对象
        ArrayList<Exce> res = new Gson().fromJson(result, new TypeToken<List<Exce>>(){}.getType());
        System.out.println(res);

    }
}
