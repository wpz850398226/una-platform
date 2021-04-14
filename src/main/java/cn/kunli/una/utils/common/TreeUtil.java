package cn.kunli.una.utils.common;


import cn.kunli.una.pojo.vo.SysTree;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ponzio
 * @version 2019年7月25日 上午11:05:42
 * 字符串工具类
 */
public class TreeUtil {

    /**
     * 格式化为树结构的数据类型
     * @param objList       //目标集合
     * @param fieldName     //关联父类字段（entityId,departmentId等）
     * @return
     */
	public static List<SysTree> formatting(List<Object> objList, String fieldName) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<SysTree> treeList = new ArrayList<>();
        //反射获取类
        Class<?> clazz = objList.get(0).getClass();
        if(fieldName!=null&&!fieldName.equals("")){
            //如果传递了父id字段，则认定是子类集合
            try {
                Field parentIdField = clazz.getDeclaredField(fieldName);

                if(parentIdField!=null){
                    //如果有父id字段，则认定是子类
                    for(Object obj:objList){
                        Field theParentIdField = obj.getClass().getDeclaredField(fieldName);
                        Field theIdField = obj.getClass().getDeclaredField("id");
                        Field theNameField = obj.getClass().getDeclaredField("name");
                        theParentIdField.setAccessible(true);
                        theIdField.setAccessible(true);
                        theNameField.setAccessible(true);
                        map.put("parentId", theParentIdField.get(obj));// 设置键值
                        map.put("id",theIdField.get(obj));
                        map.put("name",theNameField.get(obj));

                        if(map.get("id")!=null&&!map.get("id").equals("")&&map.get("parentId")!=null&&!map.get("parentId").equals("")){
                            //treeList.add(new SysTree().setParentId(String.valueOf(map.get("parentId"))).setId(String.valueOf(map.get("id"))).setName(String.valueOf(map.get("name"))));
                        }
                    }
                }
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            //如果没有传递父id字段，则认定是父类集合
            try {
                for(Object obj:objList){
                    Field theIdField = obj.getClass().getDeclaredField("id");
                    Field theNameField = obj.getClass().getDeclaredField("name");
                    theIdField.setAccessible(true);
                    theNameField.setAccessible(true);
                    map.put("id",theIdField.get(obj));
                    map.put("name",theNameField.get(obj));

                    if(map.get("id")!=null&&!map.get("id").equals("")){
                        //treeList.add(SysTree.SysTreeBuilder.aSysTree().withId(String.valueOf(map.get("id"))).withName(String.valueOf(map.get("name"))));
                    }
                }
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return treeList;
	}

}
