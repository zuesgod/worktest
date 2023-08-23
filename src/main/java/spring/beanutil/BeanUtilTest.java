package spring.beanutil;

import com.alibaba.fastjson.JSON;
import spring.beanutil.entity.Father;
import spring.beanutil.entity.Life;
import spring.beanutil.entity.Son;
//import org.springframework.beans.BeanUtils;

/**
* @Author: zues
* @ClassName: BeanUtilTest
* @Description: 测试Spring的BeanUtil的copyproperties方法
* @Date: 2021/11/17 13:40
*/
public class BeanUtilTest {

    public static void main(String[] args) {
        Father cuishan = new Father();
        cuishan.setFace("handsome");
        cuishan.setHeight("180");
        Life cuishanLife = new Life();
        cuishanLife.setStatus("alive");
        cuishan.setLife(cuishanLife);
        Son wuji=new Son();
        //BeanUtils.copyProperties(cuishan,wuji);
//        Life wujiLife = wuji.getLife();
//        wujiLife.setStatus("alive");
//        wuji.setLife(wujiLife);
//        cuishanLife.setStatus("dead"); // 翠山后来自刎了

        System.out.println(JSON.toJSONString(cuishan));
        System.out.println(JSON.toJSONString(wuji));
    }

}
