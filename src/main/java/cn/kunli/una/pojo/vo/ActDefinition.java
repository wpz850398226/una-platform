package cn.kunli.una.pojo.vo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 * 自定义：流程定义
 * */
@Data
@Accessors(chain = true)
public class ActDefinition implements Serializable {

    //部署方式
    private Integer deployType;

    //名称
    private String name;

    //文件id
    private String fileId;

    private int pageNum;			// 当前页数,从客户端传递到服务端

    private int pageSize;  		//每页显示的条数,固定的

    //流程定义id
    private String id;

    //流程定义key
    private String key;


    public ActDefinition() {
        this.pageNum = 1;
        this.pageSize = 10;
    }
}
