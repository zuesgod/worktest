package utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

public class JsonUtils {


    /**
     * @Author: zues
     * @Description: //判断是否为JSON格式
     * @Date: 2021/11/22 18:20
     * @Params: [java.lang.String]
     * @ResultType: boolean
     */
    public static boolean isJson(String content) {
        if(StringUtils.isEmpty(content)){
            return false;
        }
        boolean isJsonObject = true;
        boolean isJsonArray = true;
        try {
            JSONObject.parseObject(content);
        } catch (Exception e) {
            isJsonObject = false;
        }
        try {
            JSONObject.parseArray(content);
        } catch (Exception e) {
            isJsonArray = false;
        }
        if(!isJsonObject && !isJsonArray){ //不是json格式
            return false;
        }
        return true;
    }
}
