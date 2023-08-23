package mathtest;

import cn.hutool.core.util.ObjectUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数学计算测试
 *
 * @author zeus
 * @date 2022-10-14 9:38
 **/
public class MathTest {

    //科学技术发转数字字符串测试
    @Test
    void test1(){
        System.out.println("科学计数法数字");
        double num1 = 50123.12E25;
        BigDecimal bd1 = new BigDecimal(num1);
        final String s1 = changeScienceNum(num1);
        System.out.println("s1 = " + s1);
        System.out.println("普通数字");
        double num2 = 50123.12;
        final String s2 = changeScienceNum(num2);
        System.out.println("s2 = " + s2);
    }

    public String changeScienceNum(double num){
        if(ObjectUtil.isNull(num)) return null;
        return new BigDecimal(String.valueOf(num)).setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
