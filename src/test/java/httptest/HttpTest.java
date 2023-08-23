package httptest;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * @author zeus
 * @date 2022-09-15 8:45
 **/
public class HttpTest {


    @Test
    void demo1(){
//        String result = HttpUtil.get("http://127.0.0.1:8084/demo");
//        System.out.println("result = " + result);
        HashMap<String, Object> kvEntity = new HashMap<>();
        kvEntity.put("key","嘻嘻哈哈");
        kvEntity.put("value","嘻嘻哈哈");
        kvEntity.put("ts",new Date());

        String json = JSON.toJSONString(kvEntity);
        String result = HttpRequest.post("http://127.0.0.1:8084/demo/edit")
                .body(json)
                .execute().body();


//        String result = HttpUtil.post("http://127.0.0.1:8084/demo/edit", kvEntity);
        System.out.println("result = " + result);
        System.out.println("JSON.toJSONString(result) = " + JSON.toJSONString(result));

    }

}
