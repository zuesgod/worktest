package jstest;

import org.junit.jupiter.api.Test;
import utils.ScriptCompile;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Java运行JS测试
 * @author zeus
 * @date 2022-09-16 15:41
 **/
public class JsTest {

    @Test
    void demo1(){
        String jsStr ="function myFuc(param){return \"the param is:\"+param;}";//js脚本内容
        System.out.println(jsObjFunc(jsStr,"myFuc","test"));
    }

    @Test
    void demo2(){
        Map<String,Object> map=new HashMap<>();
        map.put("counter",9);
        ScriptCompile sc = new ScriptCompile(
                "function count(num) {counter = counter +num; " +
                        " return counter; }; " +
//                "count();" +
                        "",map);

        for(int i=0;i<5;i++) {
            try {
//                map.put("counter",9);   //本次执行 要传递的参数
//                System.out.println(sc.run());
                System.out.println(sc.executeFuc("count",2));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private Object jsObjFunc(String jsStr,String function,Object... args) {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine scriptEngine = sem.getEngineByName("js");
        try {
            scriptEngine.eval(jsStr);
            Invocable inv2 = (Invocable) scriptEngine;
            return  inv2.invokeFunction(function,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
