package csvtest;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;
import org.junit.jupiter.api.Test;
import utils.MD5Util;

import java.io.File;
import java.util.List;

/**
 * @author zeus
 * @date 2022-09-15 8:47
 **/
public class CsvTest {

    @Test
    void test1(){
        //测试读写csv文件
        //读
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file("D:\\需求总结\\捷创AR\\csv_io\\power_control.csv"));
        List<CsvRow> rows = data.getRows();
        //遍历行
        for (CsvRow csvRow : rows) {
            //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
            Console.log(csvRow.getRawList());
            System.out.println("------------------------");
            System.out.println("csvRow.getRawList().get(0) = " + csvRow.getRawList().get(0));
        }

        //写
        //指定路径和编码
        CsvWriter writer = CsvUtil.getWriter("D:\\需求总结\\捷创AR\\csv_io\\power_status.csv", CharsetUtil.CHARSET_UTF_8);
        //按行写出
        CsvWriter write = writer.write(
                new String[]{"2"}
        );
    }

    @Test
    public void test12(){
        String str = "2022/9/20,0:10:48,JTX200003,1,12.28,4.19,4.45,13.5,12.76,1.23,999.99,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:50,JTX200003,1,12.35,4.12,4.49,14.04,12.63,2.2,2051,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:51,JTX200003,1,12.2,4.15,4.52,15.96,13.91,3.2,2890,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:52,JTX200003,1,12,4.13,4.5,18.27,16.04,6.42,4190,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:53,JTX200003,1,11.8,3.83,4.9,19.04,16.78,14.92,5126,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:53,JTX200003,1,11.77,3.82,4.9,19.68,17.5,14.77,5312,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:54,JTX200003,1,11.77,3.82,4.9,20.04,17.88,14.8,5345,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:55,JTX200003,1,12.04,4.17,4.48,21.22,19.24,6.36,4248,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:56,JTX200003,1,12.28,4.13,4.52,22.06,20.12,3.49,3056,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:57,JTX200003,1,12.35,4.16,4.46,22.78,20.86,2.58,2313,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:58,JTX200003,1,12.28,4.12,4.48,21.76,20.4,1.23,149,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:10:59,JTX200003,1,12.47,4.19,4.41,20.78,19.5,0.26,194,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:11:00,JTX200003,1,12.55,4.22,4.25,20.65,19.4,0.18,0,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:13:39,JTX200003,1,12.28,4.19,4.45,13.5,12.76,1.23,999.99,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n" +
                "2022/9/20,0:13:42,JTX200003,1,12.28,4.19,4.45,13.5,12.76,1.23,999.99,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n";
//        CsvReader reader = CsvUtil.getReader();
//        //从文件中读取CSV数据
//        CsvData data = reader.read(FileUtil.file("D:\\需求总结\\飞奥\\testPath\\partern.csv"));

        //写
        //指定路径和编码
//        CsvWriter writer = CsvUtil.getWriter("D:\\\\需求总结\\\\飞奥\\\\testPath\\\\partern.csv", CharsetUtil.CHARSET_GBK);
//        //按行写出
//        CsvWriter write = writer.write(
//                new String[]{str}
//        );

        FileAppender appender = new FileAppender(new File("D:\\需求总结\\飞奥\\testPath\\partern.csv"), 16, false);
        for (int i = 0; i < 100000; i++) {
            appender.append(str);
        }
        appender.flush();
        appender.toString();
    }

    @Test
    void demo2(){
        //测试MD5
        String str= "accessKey=D34iYfk6jP181aVc&timestamp=1661482717083&type=start&accessSecret=d2Hqyx8DUOu4D6GlAkHGCChzlvtaM6EgAoHjo31mkid3oFtrMNmqkiQyDRRJjmaW";
        String res1 = MD5Util.textToMd5U32(str);
        System.out.println("res1 = " + res1);

    }
}
