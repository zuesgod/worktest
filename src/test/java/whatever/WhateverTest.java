package whatever;

import entity.FinallEntity;
import entity.StepEntity;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class WhateverTest {


    @Test
    void test1(){
        System.out.println("".isBlank());
    }

    @Test
    void test2(){
        Arrays.asList("1","3","a","6","5","c").forEach( e -> System.out.println(e));
    }

    @Test
    void test3(){
        List<StepEntity> list = new ArrayList<>();
        StepEntity stepEntity1 = new StepEntity();
        stepEntity1.setValue(1);
        stepEntity1.setStepType(1);
        StepEntity stepEntity2 = new StepEntity();
        stepEntity2.setValue(1);
        stepEntity2.setStepType(2);
        StepEntity stepEntity3 = new StepEntity();
        stepEntity3.setValue(2);
        stepEntity3.setStepType(3);
        StepEntity stepEntity4 = new StepEntity();
        stepEntity4.setValue(1);
        stepEntity4.setStepType(4);
        list.add(stepEntity1);
        list.add(stepEntity2);
        list.add(stepEntity3);
        list.add(stepEntity4);
        List<FinallEntity> list1 = new ArrayList<>();
        for (StepEntity stepEntity : list) {
            FinallEntity finallEntity = new FinallEntity();
            FinallEntity entity = finallEntity.getData(stepEntity.getStepType(), stepEntity.getValue());
            list1.add(entity);
        }
        list1.forEach(finallEntity -> System.out.println(finallEntity));

    }

    @Test
    void test4(){
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.MONTH, -3);  //设置为前3月
        dBefore = calendar.getTime();   //得到前3月的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间
        System.out.println("三个月之前时间======="+defaultStartDate + "-01 00:00:00");
    }



}
