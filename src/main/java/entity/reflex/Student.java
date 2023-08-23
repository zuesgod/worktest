package entity.reflex;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *测试反射的实体
 *
 * @author zeus
 * @date 2022-01-19 11:28
 **/
@Data
@Accessors(chain = true)
public class Student {

    private Integer id;

    private String name;

    private Integer sex;
}
