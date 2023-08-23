package filetest;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.thread.ThreadUtil;
import handler.file.IoUtilLineHandler;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件测试
 *
 * @author zeus
 * @date 2022-10-10 8:49
 **/
public class FileTest {
    //测试飞奥指定文件行读取内容
    @Test
    void test1() {
//        // 指定读取的行号
//        int lineNumber = 12;
        long startTime = System.currentTimeMillis();
        //读取csv文件数据

        // 读取文件
        File sourceFile = new
                File("D:\\需求总结\\飞奥\\testPath\\partern.csv");
        try {
            //读取到的有效数据
            String resultStr = null;

            int lineNumber = getTotalLines(sourceFile);
            boolean readFlag = true;
            String[] strs = null;
            //循环获取到文件中有效的最后一条数据
            while (readFlag) {
                resultStr = readAppointedLineNumber(sourceFile, 1);
                //读取文件标题行列数
                int titleLength = geiTitleLength(sourceFile);
                strs = resultStr.split(",");
                if (strs.length == titleLength && StringUtils.isNotBlank(strs[0]) && StringUtils.isNotBlank(strs[1]) && StringUtils.isNotBlank(strs[2])) {
                    readFlag = false;
                }
                lineNumber--;
            }

            resultStr.split(",");
            for (String str : strs) {
                System.out.println("最后一行数据： = " + str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        long endTime = System.currentTimeMillis();
        System.out.println("读取文件耗时：" + (endTime - startTime) + "ms");
    }

    //测试文件拷贝
    @Test
    void test2() {
        BufferedInputStream in = FileUtil.getInputStream("D:\\需求总结\\飞奥\\testPath\\partern.csv");
        String randomName = getRandomName("partern.csv");
        System.out.println("randomName = " + randomName);
        BufferedOutputStream out = FileUtil.getOutputStream("D:\\需求总结\\飞奥\\" + getDirName("JTX200003") + File.separator + randomName);
        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
        System.out.println("copySize = " + copySize);
    }

    //删除指定路径的文件
    @Test
    void test3() throws IOException {
//        String path = "D:\\需求总结\\飞奥\\testPath\\5892b56f85db4e32a2d7a54a0a878881.csv";
        String path = "D:\\需求总结\\飞奥\\testPath\\partern.csv";
        System.out.println("path = " + path);
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.close();
        //删除文件
        boolean result = FileUtil.del(file);
        System.out.println("result = " + result);

        //删除文件夹
        //删除一个存在的文件夹，应返回true
//        boolean result = FileUtil.del("D:\\需求总结\\飞奥\\JAC EN-2006-0012-020620221012\\");
//        Assert.assertTrue(result);

    }

    //测试监听文件变化
    @Test
    void test4() {
        //监听文件变化
        ThreadUtil.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        //此方法会阻塞当前线程，需要另外启动一个线程执行
                        //实现类似Linux下"tail -f"命令功能 但监听启动时，toCopyTest1.txt文件有新增内容时，就会触发自定义的IoUtilLineHandler
                        FileUtil.tail(FileUtil.file("D:\\需求总结\\飞奥\\testPath\\partern.csv"), new IoUtilLineHandler());
                    }
                }
        );

        //删除文件
        ThreadUtil.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        String path = "D:\\需求总结\\飞奥\\testPath\\partern.csv";
                        //删除文件
                        boolean result = FileUtil.del(new File(path));
                        System.out.println("result = " + result);
                    }
                }
        );

    }

    //获取一个文件夹下的所有文件夹，并监听其文件夹下的文件变化
    @Test
    void test5() {
        String dirPath = "D:\\需求总结\\飞奥\\data";
        //递归遍历目录以及子目录中的所有文件 可以加过滤条件
        List<File> files = FileUtil.loopFiles(dirPath, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                //可以获取出指定文件名规则的文件
                return true;
            }
        });
        System.out.println("files.size() = " + files.size());
        for (File file : files) {
            System.out.println(file.getName());
        }

    }

    //判断当前文件是否已被打开
    @Test
    void test6() {
        String path = "C:\\Users\\RISE3\\Desktop\\新建文件夹\\新建 XLSX 工作表.xlsx";
        try {
            File file = new File(path);
            System.out.println(file.exists());
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.close();
            System.out.println("File not being used");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File being used");
        }
    }

    //测试生成文件
    @Test
    void test7(){
        String filePath = "D:\\测试文件\\测试文件1\\test.txt";
        File file = new File(filePath);
        if(!file.exists()){
            File parentPath = new File(file.getParent());
            parentPath.mkdirs();
        }
//        System.out.println("file.getPath() = " + file.getPath());
//        System.out.println("file.getParentFile() = " + file.getParentFile());
//        System.out.println("file.getParent() = " + file.getParent());

        try (BufferedWriter writer =
                     Files.newBufferedWriter(Path.of(filePath), StandardCharsets.UTF_8)) {
            writer.write("Hello World -创建文件!!");
        } catch (IOException e) {
            e.printStackTrace();
        }


        //追加写模式
//        try (BufferedWriter writer =
//                     Files.newBufferedWriter(path,
//                             StandardCharsets.UTF_8,
//                             StandardOpenOption.APPEND)){
//            writer.write("Hello World -字母哥!!");
//        }

    }

    //测试读取文件内容
    @Test
    void test8(){
        String content = txt2String(new File("D:\\需求总结\\飞奥\\testPath\\test.txt"));
        System.out.println("content = " + content);
    }




    public static String getRandomName(String fileName) {
        int index = fileName.lastIndexOf(".");
        String houzhui = fileName.substring(index);//获取后缀名
        String uuidFileName = UUID.randomUUID().toString().replace("-", "") + houzhui;
        return uuidFileName;
    }

    //生成文件夹名称  项目序号 + 日期时间
    public static String getDirName(String projectSerial) {
        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
        return projectSerial + dateStr;
    }

    /**
     * 查询csv文件的标题行列数
     *
     * @param csvFile csv文件
     * @return int
     * @author zeus
     * @date 2022/10/10 10:45
     */
    public static int geiTitleLength(File csvFile) throws IOException {
        String str = readAppointedLineNumber(csvFile, 1);
        return str.split(",").length;
    }

    /**
     * 读取文件中指定行数的内容
     *
     * @param sourceFile 文件
     * @param lineNumber 要读取的行数
     * @return java.lang.String
     * @author zeus
     * @date 2022/10/10 10:08
     */
    public static String readAppointedLineNumber(File sourceFile, int lineNumber)
            throws IOException {
        String str = null;
        if (lineNumber < 0 || lineNumber > getTotalLines(sourceFile)) {
            System.out.println("传入行数" + lineNumber + "有误，不在范围之内");
        } else {
            InputStreamReader inputReader = null;
            BufferedReader bufferReader = null;
            OutputStream outputStream = null;
            try {
                InputStream inputStream = new FileInputStream(sourceFile);
                inputReader = new InputStreamReader(inputStream, "GBK");
                bufferReader = new BufferedReader(inputReader);

                // 读取一行
                String lineContent = null;
                StringBuffer strBuffer = new StringBuffer();
                int currentReadLine = 0;
                while ((lineContent = bufferReader.readLine()) != null) {
                    currentReadLine++;
                    if (currentReadLine == lineNumber) {
                        strBuffer.append(lineContent);
                        break;
                    }
                }
                str = strBuffer.toString();
            } catch (IOException e) {
                System.out.println("" + e.getMessage());
            } finally {
                IoUtil.close(outputStream);
                IoUtil.close(bufferReader);
                IoUtil.close(inputReader);
            }
        }
        return str;
    }

    /**
     * 判断文件总行数
     *
     * @param file 文件对象
     * @return int
     * @author zeus
     * @date 2022/10/10 10:07
     */
    public static int getTotalLines(File file) throws IOException {
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        in.close();
        return lines;
    }

    /**
     * 读取文件内容
     * @param file 文件对象
     * @return java.lang.String
     * @author zeus
     * @date 2023/1/18 14:41
     */
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            //使用readLine方法，一次读一行
            while((s = br.readLine())!=null){
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

}
