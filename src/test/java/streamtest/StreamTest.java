package streamtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import entity.BoardPlan;
import entity.PlanEntity;
import entity.PlanEntity2;
import entity.Student;
import entity.reflex.Student2;
import entity.stream.NumCount;
import org.junit.Test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * JDK8  Stream测试
 * @author zeus
 * @date 2022-05-30 9:31
 **/
public class StreamTest {

    @Test
    public void test1(){
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList.stream() // 创建流
                .filter(s -> s.startsWith("c")) // 执行过滤，过滤出以 c 为前缀的字符串
                .map(String::toUpperCase) // 转换成大写
                .sorted() // 排序
                .forEach(System.out::println); // for 循环打印。
    }

    @Test
    public void test2(){
//        Stream.of("a1", "a2", "b1", "c2", "c1")
//                .findFirst()
//                .ifPresent(System.out::println);


        IntStream.range(2,5)
                .forEach(System.out::println);
    }

    @Test
    public void test3(){
        List<Student> studentList = init();

//        studentList.stream().filter(student -> student.getAge() > 18).forEach(System.out::println);
//        List<String> names = studentList.stream().filter(student -> student.getAge() > 18).map(student -> student.getName()).collect(Collectors.toList());
//        System.out.println("names = " + names);
//        System.out.println("studentList.stream().anyMatch(student -> student.getAge() > 18) = " + studentList.stream().anyMatch(student -> student.getAge() > 18));
//
//        final List<String> collect = studentList.stream().map(Student::getName).collect(Collectors.toList());
//        System.out.println("collect = " + collect);

//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//        // 获取对应的平方数
//        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//        squaresList.stream().forEach(System.out::println);

        List<Integer> numbers1 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers1.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    /**
     * 创建无限流
     * @author zeus
     * @date 2022/6/10 8:16
     */
    @Test
    public void test4() {
        //迭代
        Stream.iterate(0,x -> x + 2).limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);
    }

    /**
     * Stream的中间操作
     * @author zeus
     * @date 2022/6/10 8:16
     */
    @Test
    public void test5(){
        List<Student> studentList = init();

        //filter过滤  获取出非空的数据
        List<String> list1 = Arrays.asList("Hollis", "", "HollisChuang", "H", "hollis");
        list1.stream().filter(string -> !string.isEmpty()).forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        //map映射
        List<Integer> list2 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        list2.stream().map( i -> i*i).forEach(System.out::println);
        //对对象集合使用map映射可以获取到对象的单个属性、或可以进行判断
        studentList.stream().map(Student::getName).forEach(System.out::println);
        studentList.stream().map(student -> student.getAge() > 18).forEach(System.out::println);
        //flatMap将流中的每个元素都转换成另一个流，然后把所有流连接成一个流
        System.out.println("-----------------------------------------------------");
        final List<? extends Serializable> studentlist2 = studentList.stream().flatMap(student -> Stream.of(student.getName(), student.getAge())).collect(Collectors.toList());
        System.out.println("studentlist2 = " + studentlist2);
        System.out.println("--------------------------limit限制---------------------------");
        //limit限制 获取前n个元素 索引从1开始
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().limit(4).forEach(System.out::println);
        System.out.println("------------------------skip跳过-----------------------------");
        //skip跳过 跳过前面的元素索引从1开始
        numbers.stream().skip(3).forEach(System.out::println);
        System.out.println("--------------------------distinct去重---------------------------");
        //distinct去重
        List<Integer> numbers2 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers2.stream().distinct().forEach(System.out::println);
        //测试使用distinct对对象中按照年龄去重
        studentList.stream().map(Student::getAge).distinct().forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        //sorted排序

        System.out.println("-----------------------------------------------------");
        //peek查看

        System.out.println("-----------------------------------------------------");
        //count统计

        System.out.println("-----------------------------------------------------");
        //reduce合并

        System.out.println("-----------------------------------------------------");
        //find查找

        System.out.println("-----------------------------------------------------");

        //anyMatch测试

        System.out.println("-----------------------------------------------------");
        //allMatch测试

        System.out.println("-----------------------------------------------------");
        //noneMatch测试

        System.out.println("-----------------------------------------------------");
        //findFirst测试

        System.out.println("-----------------------------------------------------");
        //findAny测试

        System.out.println("-----------------------------------------------------");
        //max测试

        System.out.println("-----------------------------------------------------");
        //min测试

        System.out.println("-----------------------------------------------------");
        //sum测试

        System.out.println("-----------------------------------------------------");
        //average测试

        System.out.println("-----------------------------------------------------");
        //count测试

        System.out.println("-----------------------------------------------------");

    }

    @Test
    public void test6() {
        List<Student> studentList = init();
        //获取出年龄等于32岁的全部姓名并放入集合中
        List<String> names = studentList.stream().filter(student -> student.getAge() == 32).map(Student::getName).collect(Collectors.toList());
        System.out.println("names = " + names);

        System.out.println("-----------------------------------------------------");
        List<Student2> student2List = init2();
        final List<List<Integer>> list = student2List.stream().filter(student2 -> student2.getAge() == 32)
                .map(student2 -> student2.getNos()).collect(Collectors.toList());
        System.out.println("list = " + list);
        //将多维数组转换成一维数组，并去重
        List<Integer> list1 = list.stream().flatMap(List::stream).distinct().collect(Collectors.toList());
        System.out.println("list1 = " + list1);
    }

    /**
     * 将对象集合中的某一属性多维数组去重后转化为key，并将这个对象集合中其中所有对应此key的另外一属性的值放入集合中
     * 例：
     *   [
     *     {
     *       "id": 12123123, //系统id
     *       "types": ["0", "1"] //当前系统数据授权的工单类型
     *     }
     *   ]
     * 将如上格式转换为：
     * [
     *     {
     *       "type": 0, //类型  字典：work_order_type  0:点检,1:维修,2:维护,3:保养
     *       "systemIds": ["1485777150878", "1485776889019"], //系统id
     *     }
     * ]
     *
     */
    @Test
    public void test7() {
        List<SystemSnapshot> systemSnapshot = new ArrayList<>();
        systemSnapshot.add(new SystemSnapshot(123123123123L,Arrays.asList("0","1")));
        systemSnapshot.add(new SystemSnapshot(345323243233L,Arrays.asList("1","2")));
        systemSnapshot.add(new SystemSnapshot(435434534534L,Arrays.asList("0","1")));
        systemSnapshot.add(new SystemSnapshot(458764896788L,Arrays.asList("1","3")));
        //将集合中的id数据按照类型分组并设置为type为map的key，value为id集合
        List<String> collects = systemSnapshot.stream()
                .flatMap(systemSnapshot1 -> systemSnapshot1.getType().stream()).distinct().collect(Collectors.toList());
        HashMap<String, List<Long>> map = new HashMap<>(collects.size());
        collects.forEach(collect -> {
            List<Long> list = systemSnapshot.stream().filter(systemSnapshot1 -> systemSnapshot1.getType()
                    .contains(collect)).map(SystemSnapshot::getId).collect(Collectors.toList());
            System.out.println("list = " + list);
            map.put(collect, list);
        });
        System.out.println("map = " + map);
    }

    /**
     * 将一个对象集合中的某一属性按照另一属性分组，并将这个对象集合中其中所有对应此key的另外一属性的值放入集合中
     * 如下，将对象集合中的所有名称按照其年龄分组并放入map中
     * 返回： nameMap = {32=[妈妈怕1, 妈妈怕2, 妈妈怕3], 33=[王五], 18=[张三, 李四], 12=[赵六, 哈麻皮]}
     * @author zeus
     * @date 2022/6/14 9:26
     */
    @Test
    public void test8(){
        List<Student> list = init();
        Map<Integer, List<String>> nameMap = list.stream()
                .collect(Collectors.groupingBy(Student::getAge,
                        Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println("nameMap = " + nameMap);
    }

    @Test
    public void test9(){
//        List<Student> studentList = init();
//        studentList.forEach(System.out::println);
//        System.out.println("---------------------------------------------------");
//        Map<String,Object> map = studentList.stream().collect(Collectors.toMap(Student::getAge, Student::getName, Student::getId, student -> student));
//
////        studentList.stream().map(student -> Stream.of(student.getName(), student.getAge(),student.getId()));
//        map.forEach(System.out::println);
    }

    @Test
    public void test10(){
        List<Student> studentList = init();
//        List<String> names = studentList.stream().map(Student::getName).collect(Collectors.toList());
//        System.out.println(names);
        List<Integer> list = Arrays.asList(12, 13, 33);
        List<Student> students = studentList.stream().filter(student -> {
            if (list.contains(student.getAge())) {
                return false;
            }
           return true;
       }).collect(Collectors.toList());
        System.out.println("students = " + students);
    }

    @Test
    public void test11(){
        BoardPlan plan1 = new BoardPlan("张三", "11", "33", "44", "55", "44");

        final String res = JSONObject.toJSONString(plan1, SerializerFeature.MapSortField);
        System.out.println("res = " + res);
    }

    @Test
    public void test12(){
        ArrayList<PlanEntity> list1 = new ArrayList<>();
        list1.add(new PlanEntity("2022-09-22 00:00:00", 0));
        list1.add(new PlanEntity("2022-09-21 00:00:00", 2));
        list1.add(new PlanEntity("2022-09-20 00:00:00", 0));
        list1.add(new PlanEntity("2022-09-19 00:00:00", 0));
        ArrayList<PlanEntity> list2 = new ArrayList<>();
        list2.add(new PlanEntity("2022-09-22 00:00:00", 0));
        list2.add(new PlanEntity("2022-09-21 00:00:00", 3));
        list2.add(new PlanEntity("2022-09-20 00:00:00", 0));
        list2.add(new PlanEntity("2022-09-19 00:00:00", 0));

        Map<String, Integer> collect1 = list1.stream().collect(Collectors.toMap(PlanEntity::getTs, PlanEntity::getCom));
        Map<String, Integer> collect2 = list2.stream().collect(Collectors.toMap(PlanEntity::getTs, PlanEntity::getCom));



        List<PlanEntity2> entity2s = new ArrayList<PlanEntity2>();

        collect1.keySet().forEach(key -> {
            PlanEntity2 entity = new PlanEntity2();
            entity.setTs(key);
            entity.setPlan(collect1.get(key));
            entity.setCom(collect2.get(key));
            entity2s.add(entity);
        });
        System.out.println("JSON.toJSONString(entity2s) = " + JSON.toJSONString(entity2s));
    }

    /**
     * 根据对象集中的某个对象属性分组后，计算值的和，并放入另一个map中，其中id为key，求和后的值为value
     * @author zues
     * @date 2023/7/31 11:38
     */
    @Test
    public void test13(){
        ArrayList<NumCount> numCounts = new ArrayList<>();
        NumCount n1 = new NumCount(1, new BigDecimal(1));
        NumCount n2 = new NumCount(2, new BigDecimal(1));
        NumCount n3 = new NumCount(3, new BigDecimal(1));
        NumCount n4 = new NumCount(1, new BigDecimal(1));
        NumCount n5 = new NumCount(2, new BigDecimal(1));
        NumCount n6 = new NumCount(1, new BigDecimal(1));
        NumCount n7 = new NumCount(4, new BigDecimal(1));
        numCounts.add(n1);
        numCounts.add(n2);
        numCounts.add(n3);
        numCounts.add(n4);
        numCounts.add(n5);
        numCounts.add(n6);
        numCounts.add(n7);
        // 使用Stream API按照materialId分组，并计算每组的number总和
        final Map<Integer, BigDecimal> map = numCounts.stream()
                .collect(Collectors.groupingBy(NumCount::getId,
                        Collectors.reducing(BigDecimal.ZERO, NumCount::getNumber, BigDecimal::add)));
        System.out.println("map = " + map);
    }

    public List<Student> init(){
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("张三", 18, 1L));
        list.add(new Student("李四", 18, 2L));
        list.add(new Student("王五", 33, 3L));
        list.add(new Student("赵六", 12, 4L));
        list.add(new Student("哈麻皮", 12, 5L));
        list.add(new Student("妈妈怕1", 32, 6L));
        list.add(new Student("妈妈怕2", 32, 7L));
        list.add(new Student("妈妈怕2", 32, 8L));
        return list;
    }

    public List<Student2> init2(){
        ArrayList<Student2> list = new ArrayList<>();
        list.add(new Student2("张三", Arrays.asList(2,3,5,6),13,1L));
        list.add(new Student2("李四",Arrays.asList(11,123,453,54), 18, 2L));
        list.add(new Student2("王五", Arrays.asList(6,58,67),33, 3L));
        list.add(new Student2("赵六", Arrays.asList(1,32,123,5),12, 4L));
        list.add(new Student2("哈麻皮",Arrays.asList(12,123,43), 12, 5L));
        list.add(new Student2("妈妈怕1",Arrays.asList(323,43), 32, 6L));
        list.add(new Student2("妈妈怕2", Arrays.asList(7,8,9,10),32, 7L));
        list.add(new Student2("妈妈怕3", Arrays.asList(10,11,12,12),32, 8L));
        return list;
    }

    public List<BoardPlan> init3(){
        ArrayList<BoardPlan> list = new ArrayList<>();
        list.add(new BoardPlan("张三","11","33","44","55","44"));
        list.add(new BoardPlan("李四","11","33","44","55","44"));
        list.add(new BoardPlan("王五","11","33","44","55","44"));
        list.add(new BoardPlan("赵六","11","33","44","55","44"));
        list.add(new BoardPlan("哈麻皮","11","33","44","55","44"));
        list.add(new BoardPlan("妈妈怕1","11","33","44","55","44"));
        list.add(new BoardPlan("妈妈怕2","11","33","44","55","44"));
        list.add(new BoardPlan("妈妈怕2","11","33","44","55","44"));
        return list;
    }


}
