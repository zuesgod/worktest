package annotationtest;

import annotationlearn.MyField;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * 自定义注解测试
 *
 * @author zeus
 * @date 2022-01-27 11:21
 **/
public class MyFieldTest {

    @Test
    public void test1() throws Exception {
        Class<?> clazz = Class.forName("entity.annotation.MyFieldEntity");
        for (Field field : clazz.getDeclaredFields()) {
            if(field.isAnnotationPresent(MyField.class)){
                MyField annotation = field.getAnnotation(MyField.class);
                System.out.println("字段:[" + field.getName() + "], 描述:[" + annotation.description() + "], 长度:[" + annotation.length() +"]");
            }
        }
    }

}
