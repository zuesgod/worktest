package treetest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.TreeEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Author: zues
* @ClassName: TreeTest
* @Description: 测试项目中树形结构数据处理
* @Date: 2021/11/8 16:01
*/
public class TreeTest {

    @Test
    public void testTree(){
        List<TreeEntity> treeData = getData();
        System.out.println("------------------------------------");
        List<TreeEntity> tree = buildTree(treeData,0);
        System.out.println("tree = " + tree);
//        List<TreeEntity> list = JSON.parseArray(tree.toString(),TreeEntity.class);
        JSONArray parseArray = JSONArray.parseArray(JSON.toJSONString(tree));
        System.out.println("parseArray = " + parseArray);

    }


    public List<TreeEntity> buildTree(List<TreeEntity> list,int firstPid){
      List<TreeEntity> treeList = new ArrayList<TreeEntity>();
      for (TreeEntity tree : list) {
            if (tree.getPid() == firstPid) {
                tree.setChild(buildTree(list, tree.getId()));
                treeList.add(tree);
            }
        }
        return treeList;
    }

    private List<TreeEntity> getData(){
        List<TreeEntity> list = new ArrayList<TreeEntity>();
        Map<String, Object> map = new HashMap<String, Object>();
        TreeEntity t1 = new TreeEntity(1,"树1",0);
        TreeEntity t2 = new TreeEntity(2,"树1-1",1);
        TreeEntity t3 = new TreeEntity(3,"树1-2",1);
        TreeEntity t4 = new TreeEntity(4,"树1-1-1",2);
        TreeEntity t5 = new TreeEntity(5,"树1-1-2",2);
        TreeEntity t6 = new TreeEntity(6,"树2",0);
        TreeEntity t7 = new TreeEntity(7,"树2-1",6);
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        return list;
    }

}
