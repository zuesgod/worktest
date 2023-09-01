package streamtest;

import entity.BoardPlan;
import entity.Student;
import entity.reflex.Student2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream学习
 *
 * @author zeus
 * @date 2023-08-18 8:44
 **/
public class StreamLearn {

    /**
     * 普通对象集合，三个参数 String,Integer,Long
     *
     * @return java.util.List<entity.Student>
     * @author zues
     * @date 2023/8/18 8:46
     */
    public List<Student> init() {
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

    /**
     * 普通对象集合，四个参数 String,List<Integer>,Integer,Long
     *
     * @return java.util.List<entity.reflex.Student2>
     * @author zues
     * @date 2023/8/18 8:46
     */
    public List<Student2> init2() {
        ArrayList<Student2> list = new ArrayList<>();
        list.add(new Student2("张三", Arrays.asList(2, 3, 5, 6), 13, 1L));
        list.add(new Student2("李四", Arrays.asList(11, 123, 453, 54), 18, 2L));
        list.add(new Student2("王五", Arrays.asList(6, 58, 67), 33, 3L));
        list.add(new Student2("赵六", Arrays.asList(1, 32, 123, 5), 12, 4L));
        list.add(new Student2("哈麻皮", Arrays.asList(12, 123, 43), 12, 5L));
        list.add(new Student2("妈妈怕1", Arrays.asList(323, 43), 32, 6L));
        list.add(new Student2("妈妈怕2", Arrays.asList(7, 8, 9, 10), 32, 7L));
        list.add(new Student2("妈妈怕3", Arrays.asList(10, 11, 12, 12), 32, 8L));
        return list;
    }

    /**
     * 创建Stream
     * 需要注意的是，Stream对象是一种一次性使用的对象，它只能被消费一次。
     * 一旦对Stream执行了终止操作（如收集结果，遍历元素），Stream就会被关闭，后续无法再使用
     * 因此在使用Stream时，需要每次都创建新的Stream对象
     */
    @Test
    public void create() {
        //从集合创建
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Stream<Integer> collStream = integers.stream();
        //从数组创建
        String[] names = {"11", "22", "33"};
        Stream<String> arrStream = Arrays.stream(names);
        //通过Stream.of()创建
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        //通过Stream.builder创建
        Stream.Builder<Object> streamBuilder = Stream.builder();
        streamBuilder.add("1");
        streamBuilder.add(1);
        streamBuilder.add(3);
        Stream<Object> build = streamBuilder.build();
        //从IO资源创建
        Path path = Paths.get("data.txt");
        try (Stream<String> lines = Files.lines(path);) {
            //使用stream处理数据
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //通过生成器创建
        Stream<Integer> generate = Stream.generate(() -> 0);//创建一个无线流，每个元素都是0
        Stream<Integer> iterate = Stream.iterate(0, n -> n + 1);//创建一个无限流，从0开始递增
    }

    /**
     * 测试Stream常用方法
     *
     * @author zues
     * @date 2023/8/18 9:03
     */
    @Test
    public void testMethod() {
        System.out.println("============================filter()=================================");
        //filter()法接受一个 Predicate 函数作为参数，用于过滤 Stream 中的元素。只有满足 Predicate 条件的元素会被保留下来。例如：
        Stream.of(1, 2, 3, 4, 6, 7, 8)
                .filter(n -> n % 2 == 0)//过滤出偶数
                .forEach(System.out::println);

        System.out.println("============================map()=================================");
        //map() 方法接受一个 Function 函数作为参数，用于对 Stream 中的元素进行映射转换。对每个元素应用函数后的结果会构成一个新的 Stream。例如：
        Stream.of("apple", "xiaomi", "huawei")
                .map(String::length)//映射单词长度
                .forEach(System.out::println);

        System.out.println("=============================flatMap()================================");
        //flatMap() 方法类似于 map() 方法，不同之处在于它可以将每个元素映射为一个流，并将所有流连接成一个流。这主要用于解决嵌套集合的情况。例如：
        List<List<Integer>> flatMapList = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6),
                Arrays.asList(1, 2));
        flatMapList.stream().flatMap(List::stream)//扁平化为一个集合
                .forEach(System.out::println);
        System.out.println("============================limit()=================================");
        //limit() 方法可以限制 Stream 的大小，只保留前 n 个元素。例如：
        Stream.of(1, 2, 3, 4, 5)
                .limit(2) // 只保留前 2 个元素
                .forEach(System.out::println);

        System.out.println("============================skip()=================================");
        //skip() 方法可以跳过 Stream 中的前 n 个元素，返回剩下的元素组成的新 Stream。例如：
        Stream.of(1, 2, 3, 4, 5)
                .skip(2) // 跳过前 2 个元素
                .forEach(System.out::println);

        System.out.println("===========================sorted()==================================");
        //sorted() 方法用于对 Stream 中的元素进行排序，默认是自然顺序排序。还可以提供自定义的 Comparator 参数来指定排序规则。例如：
        Stream.of(11, 2, 33, 4, 25)
                .sorted() // 排序
                .forEach(System.out::println);

        System.out.println("===========================distinct()==================================");
        //distinct() 方法用于去除 Stream 中的重复元素，根据元素的 equals() 和 hashCode() 方法来判断是否重复。例如：
        Stream.of(11, 2, 2, 4, 25,32,25)
                .distinct() // 去重
                .forEach(System.out::println);


        System.out.println("============================collect()=================================");

        //collect() 方法用于将 Stream 中的元素收集到结果容器中，如 List、Set、Map 等。可以使用预定义的 Collectors 类提供的工厂方法来创建收集器，也可以自定义收集器。例如：
        Stream<String> collectStream = Stream.of("apple","banana","cherry");
        List<String> collect = collectStream.collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("==========================reduce()===================================");
        //reduce() 方法用于将 Stream 中的元素依次进行二元操作，得到一个最终的结果。它接受一个初始值和一个 BinaryOperator 函数作为参数。例如：
        //相当于sql的count，sum()函数
        Stream<Integer> reduceStream = Stream.of(1, 2, 3, 4, 5);
        Optional<Integer> sum = reduceStream.reduce((a, b) -> a + b); // 对所有元素求和
        System.out.println("sum.get() = " + sum.get());

        System.out.println("==============================summaryStatistics()===============================");
        //summaryStatistics() 方法可以从 Stream 中获取一些常用的统计信息，如元素个数、最小值、最大值、总和和平均值。例如：
        //上面reduce的进阶版
        IntStream summaryStatisticstream = IntStream.of(1, 2, 3, 4, 5);
        IntSummaryStatistics stats = summaryStatisticstream.summaryStatistics();
        System.out.println("Count: " + stats.getCount());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Average: " + stats.getAverage());
        System.out.println("=============================================================");
    }

    /**
     * Stream的终端操作
     * @author zues
     * @date 2023/9/1 16:00
     */
    public void finalTest(){
        System.out.println("==============================forEach 和 peek===============================");
        //forEach： forEach是一个终端操作方法，它接受一个Consumer函数作为参数，对流中的每个元素执行该函数。
        // 它没有返回值，因此无法将操作结果传递给后续操作。forEach会遍历整个流，对每个元素执行相同的操作。


        System.out.println("==============================匹配操作（allMatch、anyMatch 和 noneMatch）===============================");
        //在 Stream API 中，allMatch、anyMatch 和 noneMatch 是用于进行匹配操作的方法，它们可以用来检查流中的元素是否满足特定的条件。


        System.out.println("==============================查找操作（findFirst 和 findAny）===============================");
        //reduce和collect都是Stream API中用于聚合操作的方法，它们可以将流中的元素进行汇总、计算和收集。


        System.out.println("==============================（reduce 和 collect）===============================");
        //reduce和collect都是Stream API中用于聚合操作的方法，它们可以将流中的元素进行汇总、计算和收集。


        System.out.println("==============================（reduce 和 collect）===============================");
        //reduce和collect都是Stream API中用于聚合操作的方法，它们可以将流中的元素进行汇总、计算和收集。


        System.out.println("==============================（reduce 和 collect）===============================");
        //reduce和collect都是Stream API中用于聚合操作的方法，它们可以将流中的元素进行汇总、计算和收集。






    }
}
