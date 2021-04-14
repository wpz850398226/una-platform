package cn.kunli.una.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
* @author 作者 : Ponzio
* @version 创建时间：2019年9月26日 上午11:59:57
* 类说明 :基础父类，所有实体类继承此类
*/
@Data
@AllArgsConstructor
public class SysToken implements Serializable {

    private static final long serialVersionUID = 640860669042858758L;

    private String token;

    private Long loginTime;     //登录时间戳

}
