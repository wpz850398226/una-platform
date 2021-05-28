package cn.kunli.una.pojo.vo;

import cn.kunli.una.pojo.system.SysAccount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ponzio
 * @version 2020年2月17日09:39:47
 * 工具类，登录用户详情数据
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysLoginAccountDetails extends SysAccount implements UserDetails {

	private String captcha;				//登录验证码

	private Boolean isRemember;	//登录验证码

	private String loginIp;	//登录ip

	private String token;	//

	private String sessionName;	//

	private String macId;	//mac地址，设备唯一标识

	private String deviceType;

	private List<String> permissionCodeList;

	private Long loginTime;		//登录时间戳

	private Long expireTime;	//过期时间戳

	private Collection<? extends GrantedAuthority> authorities;

	public void setPermissionCodeList(List<String> permissionCodeList) {
		this.permissionCodeList = permissionCodeList;

		this.authorities = permissionCodeList.parallelStream()
				//.filter(p -> StringUtils.isEmpty(p))
				.map(p -> new SimpleGrantedAuthority(p)).collect(Collectors.toSet());
	}

	//账户是否未过期
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//账户是否未锁定
	@Override
	public boolean isAccountNonLocked() {
		return getStatusDcode() != "locked";
	}

	//密码是否未过期
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//账户是否激活
	@Override
	public boolean isEnabled() {
		return true;
	}
}
