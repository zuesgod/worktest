package entity.annotation;

import annotationlearn.MyField;

/**
 * 测试注解
 *
 * @author zeus
 * @date 2022-01-27 11:19
 **/
public class MyFieldEntity {

    //使用我们的自定义注解
    @MyField(description = "用户名", length = 12)
    private String name;
}
