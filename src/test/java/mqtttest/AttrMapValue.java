package mqtttest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单位与属性值
 *
 * @author zeus
 * @date 2023-06-02 14:44
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttrMapValue {

    /**
     * 指标代码
     */
    private String code;

    /**
     * 单位
     */
    private String unit;

}
