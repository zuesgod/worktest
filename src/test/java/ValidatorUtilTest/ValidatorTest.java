package ValidatorUtilTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import utils.ValidatorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ValidatorTest {

    /**
     * @Author: zues
     * @Description: //测试验证JSON字符串合法性
     * @Date: 2021/11/17 16:57
     * @Params: []
     * @ResultType: java.lang.Boolean
     */
    @Test
    void testValid() {
        String data = getWrongData();
        Boolean flag = ValidatorUtil.vaildEquipmentJson(data);
        System.out.println("flag = " + flag);
    }


    private String getData() {
        return "[\n" +
                "  {\n" +
                "\t\t\"groupId\": \"1001\",\n" +
                "\t\t\"groupName\": \"空压机\",\n" +
                "\t\t\"items\": [\n" +
                "\t      {\"attributeId\": \"10001\", \"name\": \"额定电压\",\"value\":\"56\",\"unit\":\"V\"},\n" +
                "\t      {\"attributeId\": \"10002\", \"name\": \"额定功率\",\"value\":\"89\",\"unit\":\"kwh\"}\n" +
                "\t\t]\n" +
                "  },\n" +
                "  {\n" +
                "\t\t\"groupId\": \"1002\",\n" +
                "\t\t\"groupName\": \"压缩机\",\n" +
                "\t\t\"items\": [\n" +
                "\t      {\"attributeId\": \"10003\", \"name\":\"功\",\"value\":\"22\",\"unit\":\"W\"},\n" +
                "\t      {\"attributeId\": \"10002\", \"name\":\"席巴\",\"value\":\"12\",\"unit\":\"KW\"}\n" +
                "\t\t]\n" +
                "  },\n" +
                "  {\n" +
                "  \t\"groupId\": \"1003\",\n" +
                "\t\t\"groupName\": \"other\",\n" +
                "\t\t\"items\": [\n" +
                "\t\t\t{\"attributeId\": \"10000\", \"name\":\"哈麻皮\",\"value\":\"221\",\"unit\":\"A\"},\n" +
                "\t\t\t{\"attributeId\": \"10004\", \"name\":\"皮皮虾\",\"value\":\"256\",\"unit\":\"L\"}\n" +
                "\t\t]\n" +
                "  }\n" +
                "]";
    }

    private String getWrongData(){
        return "[\n" +
                "  {\n" +
                "\t\t\"groupId\": \"1001\",\n" +
                "\t\t\"groupName\": \"空压机\",\n" +
                "\t\t\"items\": [\n" +
                "\t      {\"attributeId\": \"10001\", \"name\": \"额定电压\",\"value\":\"56\",\"unit\":\"V\"},\n" +
                "\t      {\"attributeId\": \"10056\", \"name\": \"额定功率\",\"value\":\"89\",\"unit\":\"kwh\"}\n" +
                "\t\t]\n" +
                "  },\n" +
                "  {\n" +
                "\t\t\"groupId\": \"1002\",\n" +
                "\t\t\"groupName\": \"压缩机\",\n" +
                "\t\t\"items\": [\n" +
                "\t      {\"attributeId\": \"10003\", \"name\":\"功\",\"value\":\"22\",\"unit\":\"W\"},\n" +
                "\t      {\"attributeId\": \"10002\", \"name\":\"席巴\",\"value\":\"12\",\"unit\":\"KW\"}\n" +
                "\t\t]\n" +
                "  },\n" +
                "  {\n" +
                "  \t\"groupId\": \"1003\",\n" +
                "\t\t\"groupName\": \"other\",\n" +
                "\t\t\"items\": [\n" +
                "\t\t\t{\"attributeId\": \"10000\", \"name\":\"哈麻皮\",\"value\":\"221\",\"unit\":\"A\"},\n" +
                "\t\t\t{\"attributeId\": \"10004\", \"name\":\"皮皮虾\",\"value\":\"256\",\"unit\":\"L\"}\n" +
                "\t\t]\n" +
                "  }\n" +
                "]";
    }
}
