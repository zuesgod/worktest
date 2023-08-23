package threadtest;

import java.util.concurrent.*;

import org.junit.jupiter.api.Test;

/**
 * 线程测试
 *
 * @author zeus
 * @date 2021-12-17 11:22
 **/
public class ThreadTest {

    private static ExecutorService pool;

    @Test
    void test1(){
        ExecutorService pool = Executors.newCachedThreadPool();
        try{
            for (int i = 1; i <= 10 ; i++) {
                pool.execute(() ->{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }


    }


    @Test
    void test2(){

        pool = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "办理业务1");
                }
            });
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "办理业务2");
                }
            });
    }





}
