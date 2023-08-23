package utils;

import javax.script.*;
import java.util.Map;

/**
 * @author zeus
 * @date 2022-09-16 15:56
 **/
public class ScriptCompile {

    CompiledScript script;
    ScriptEngine engine ;

    ScriptCompile(String scriptText){
        this.script =initScript(scriptText,null);
    }

    /**
     *
     * @param scriptText    js脚本内容
     * @param initParams    在编译时, 初始化时传入脚本的参数
     */
    public ScriptCompile(String scriptText, Map<String, Object> initParams){   //构造函数, 先编译
        this.script =initScript(scriptText,initParams);
    }

    public Object execute() throws ScriptException {
        return execute(null);
    }

    public Object executeFuc(String fucName,Object...args) throws Exception {
        return executeFuc(null,fucName,args);
    }

    public CompiledScript initScript(String scriptText,Map<String,Object> initParams){
        CompiledScript script =null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");
        if(initParams!=null&&!initParams.isEmpty()) {
            for (Map.Entry<String, Object> entry : initParams.entrySet()) {
                engine.put(entry.getKey(), entry.getValue());
            }
        }
        this.engine= engine;
        if (engine instanceof Compilable) {
            try {
                script = ((Compilable) engine).compile(scriptText);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        return script;
    }

    /**
     * 执行脚本
     * @param bindingsMap   本次执行时传入的参数
     * @return
     * @throws ScriptException
     */
    public Object execute(Map<String,Object> bindingsMap) throws ScriptException {
        if(bindingsMap!=null&&!bindingsMap.isEmpty()){
            Bindings bindings = new SimpleBindings();
            for(Map.Entry<String,Object> entry: bindingsMap.entrySet()){
                bindings.put(entry.getKey(),entry.getValue());
            }
            return script.eval(bindings);
        }else{
            return script.eval();
        }
    }

    /**
     * 调用脚本中的某个函数
     * @param bindingsMap   本次执行传入的参数
     * @param fucName  函数名
     * @param args  函数参数,可以有多个参数
     * @return
     * @throws Exception
     */
    public Object executeFuc(Map<String,Object> bindingsMap, String fucName, Object...args) throws Exception {
        execute(bindingsMap);
        Invocable inv2 = (Invocable) engine;
        return  inv2.invokeFunction(fucName,args);
    }
}
