package filetest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileWriter;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 文件生成测试
 *
 * @author zeus
 * @date 2022-10-12 16:09
 **/
public class FileInsertTest {


    /**
     * 飞奥csv文件不断生成与修改测试   模拟真实FTP服务器
     *
     * @author zeus
     * @date 2022/10/12 16:10
     */
    @Test
    void feiaoTest1() throws UnsupportedEncodingException {
        while (true){
            //第一行数据
            String title = "日期,时间,试验编号,测试序号,PE,PA,PM,TE,TA,Q,X,AC,SG,SZ,PASS,导阀型号,导阀编号,导阀弹簧,预阀型号,预阀编号,预阀弹簧,调压器号,PE1,PE2,PE3\n";

            //试验编号
            List<String> serialList = new ArrayList<>();
            serialList.add("JAC EN-2006-0012-0203");
            serialList.add("JAC EN-2006-0012-0202");
            serialList.add("JAC EN-2006-0012-0204");
            serialList.add("JAC EN-2006-0012-0205");
            serialList.add("JAC EN-2006-0012-0206");

            //文件夹集
            List<String> dirList = new ArrayList<>();
            dirList.add("D:\\需求总结\\飞奥\\data\\paraflowcoeffcient");
            dirList.add("D:\\需求总结\\飞奥\\data\\test_lowtemperature");
            dirList.add("D:\\需求总结\\飞奥\\data\\test_parameter2");
            dirList.add("D:\\需求总结\\飞奥\\data\\testparameterclose");
            dirList.add("D:\\需求总结\\飞奥\\data\\testparameteredt");

            //模拟的新增原始数据
            String date = "2022/9/21";

            //生成日期数据集
            for (int i = 0; i < serialList.size(); i++) {
                System.out.println("----------------------------------");
                //需要生成的尾行数据
                List<String> dataList = new ArrayList();
                for (int j = 0; j < 5; j++) {
                    //设置时间 偏移天
                    DateTime dateTime = DateUtil.offsetDay(DateUtil.parse(date), j + 1);
                    String tempDate = new SimpleDateFormat("yyyy/M/d").format(dateTime);
                    dataList.add(tempDate + ",0:10:50," + serialList.get(i) + ",1,12.35,4.12,4.49,14.04,12.63,2.2,2051,8,10,9,11,2,4,6,3,5,7,1,13,0,0\n");
                }
                String filePath = dirList.get(i) + File.separator + (i + 1) + ".csv";
                File file = new File(filePath);
                //生成文件与数据
                FileWriter writer = new FileWriter(filePath);
                //标题行
                System.out.println("file.exists() = " + file.exists());
                if(!file.exists()){
                    System.out.println(111);
                    writer.write(title, true);
                }
                for (String tempData : dataList) {
                    //生成的假数据
                    writer.write(tempData, true);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
