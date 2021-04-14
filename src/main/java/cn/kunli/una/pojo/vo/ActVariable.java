package cn.kunli.una.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@Accessors(chain = true)

public class ActVariable implements Serializable {

    private String id;

    private String taskId;      //任务id

    private String instanceId;      //实例id

    private Map<String,Object> globalVariableMap;       //全局变量

    private Map<String,Object> localVariableMap;        //局部变量

    private String variableName;




}
