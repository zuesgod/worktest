package filetest;

import cn.hutool.core.util.ObjectUtil;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件打包为压缩包测试
 *
 * @author zeus
 * @date 2023-04-16 14:29
 **/
public class FileZipTest {



    @Test
    void test1(){
        FileOutputStream fos = null;
        ZipOutputStream zipOut = null;

        try {
            fos = new FileOutputStream("equip.zip");
            zipOut = new ZipOutputStream(fos);
            List<ZipEntity> list = new ArrayList();

            final ZipEntity entity1 = new ZipEntity("/upload/20230416/b4a418df9ee545f39ad8c8af809a9192.png", "hcode1_黄测试压缩包1");
            final ZipEntity entity2 = new ZipEntity("/upload/20230416/f0e8e09ccb9a4b8c8b8a7c663cb9ecd1.png", "hcode2_黄测试压缩包2");
            list.add(entity2);

            for (ZipEntity entity : list) {
//                JSONObject jsonObject = JSON.parseObject(entity.getQrCodePath());
//                String content = entity.getUuid();
                String fileName = entity.getName() + ".png";

                // 创建 ZipEntry 对象，并设置文件名
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);

                // 生成二维码并写入压缩包 (原先的)
//                QRCodeWriter writer = new QRCodeWriter();
//
//                BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                MatrixToImageWriter.writeToStream(matrix, "png", baos);
//                zipOut.write(baos.toByteArray());
                //查询出选中的设备二维码并放入压缩包,需要搭配系统盘符前缀
//                InputStream is = new FileInputStream(new File("D:\\" + entity.getUrl()));
                final byte[] bytes = fileToByteArray(new File("D:\\" + entity.getUrl()));
//                byte[] buffer = new byte[1024];
//                int length;
//                while ((length = is.read(buffer)) != -1) {
//                    zipOut.write(buffer, 0, length);
//                }
                zipOut.write(bytes);
                // 关闭当前 ZipEntry
                zipOut.closeEntry();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 关闭 ZipOutputStream
            try {
                if (ObjectUtil.isNotNull(zipOut)) {
                    zipOut.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static byte[] fileToByteArray(File file) throws IOException {
        try (InputStream in = new FileInputStream(file);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        }
    }
}



