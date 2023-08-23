package handler.file;

import cn.hutool.core.io.LineHandler;

/**
 * 文件变化监听器
 *
 * @author zeus
 * @date 2022-10-12 11:30
 **/
public class IoUtilLineHandler implements LineHandler {
    @Override
    public void handle(String s) {
        System.out.println("文件变化了，变化的内容为：");
        System.out.println("s = " + s);
    }
}
