package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ValidatorUtil {


    public static Boolean vaildEquipmentJson(String data) {
        JSONArray array = JSON.parseArray(data);
        LinkedList<String> groupList = new LinkedList<>();
        LinkedList<String> attrList = new LinkedList<>();
        ArrayList<String> groupIdList = new ArrayList<>(Arrays.asList("1001", "1002", "1003", "1004"));
        ArrayList<String> attrIdList = new ArrayList<>(Arrays.asList("10001", "10002", "10003", "10004", "10005"));
        for (Object o : array) {
            JSONObject obj = (JSONObject) o;
            String groupId = obj.get("groupId").toString();
            if (null == groupId) {
                return false;
            }
            groupList.add(groupId);
            JSONArray items = JSON.parseArray(obj.get("items").toString());
            if (null == items) {
                return false;
            }
            for (Object item : items) {
                JSONObject attr = (JSONObject) item;
                if (null == attr.get("attributeId")) {
                    return false;
                }
                attrList.add(attr.get("attributeId").toString());
            }
        }
        if (!groupIdList.containsAll(groupList)) {
            return false;
        }
        if (!attrIdList.containsAll(attrList)) {
            return false;
        }
        return true;
    }

}
