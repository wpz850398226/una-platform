package cn.kunli.una.pojo.vo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 * 自定义：流程实例
 * */
@Data
@Accessors(chain = true)
public class ActInstance implements Serializable {

    //名称
    private String name;

    private int pageNum;			// 当前页数,从客户端传递到服务端

    private int pageSize;  		//每页显示的条数,固定的

    //流程实例id
    private String id;

    //流程定义id
    private String definitionId;

    //流程实例key
    private String key;

    //流程定义key
    private String definitionKey;

    //流程变量名
    private String variableName;

    //流程字典定义编码
    private String processCode;

    //申请人id
    private String userId;


    public ActInstance() {
        this.pageNum = 1;
        this.pageSize = 10;
    }
}
