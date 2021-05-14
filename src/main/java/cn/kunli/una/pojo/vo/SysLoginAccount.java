package cn.kunli.una.pojo.vo;

import cn.kunli.una.pojo.system.SysAccount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Ponzio
 * @version 2020年2月17日09:39:47
 * 工具类，登录用户数据
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysLoginAccount extends SysAccount {

	private String captcha;				//登录验证码

	private Integer isRemember;	//登录验证码

	private String loginIp;	//登录ip

	private String token;	//

	private String sessionName;	//

	private String macId;	//mac地址，设备唯一标识

	private String deviceType;

	private List<String> permissionCodeList;

	private Long loginTime;		//登录时间戳

	private Long expireTime;	//过期时间戳

}
