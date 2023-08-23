package reflextest;

import entity.reflex.Student;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * 反射测试
 *
 * @author zeus
 * @date 2022-01-19 13:13
 **/
public class ReflexTest {

    @Test
    void test1() throws Exception {
        Class<Student> student = Student.class;
        Student stu = student.getConstructor().newInstance();
        Field id = student.getDeclaredField("id");
        id.setAccessible(true);
        id.set(stu,15);
        System.out.println("id = " + id);
        System.out.println("stu = " + stu);
    }

}
