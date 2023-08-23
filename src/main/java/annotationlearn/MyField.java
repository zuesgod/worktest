package annotationlearn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 测试注解
* @author zeus
* @date 2022/1/27 11:16
*/
@Target(ElementType.FIELD)            //注解用于字段上
@Retention(RetentionPolicy.RUNTIME)   //保留到运行时，可通过注解获取
public @interface MyField {
    String description();
    int length();
}
