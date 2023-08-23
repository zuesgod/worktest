package mqtttest;

import java.util.HashMap;
import java.util.Map;

/**
 * 属性值
 *
 * @author zeus
 * @date 2023-06-02 13:14
 **/
public class AttrMap {

    private static final Map<String, AttrMapValue> map = new HashMap<>();

    static {
        //有功电能示值
        map.put ("A29", new AttrMapValue("01","kWh"));
        //电表倍率
        map.put ("A97", new AttrMapValue("03","/"));
        //有功功率
        map.put ("A16", new AttrMapValue("04","kW"));
    }

    public static AttrMapValue getValue (String key) {
        return map.get (key);
    }

    public static boolean containsKey (String key) {
        return map.containsKey (key);
    }
}
