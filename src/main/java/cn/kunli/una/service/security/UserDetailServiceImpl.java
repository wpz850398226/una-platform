package cn.kunli.una.service.security;

import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.service.system.SysAccountService;
import cn.kunli.una.service.system.SysPermissionService;
import cn.kunli.una.utils.common.MapUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysAccountService sysAccountService;
    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("执行用户认证");
        //1、根据用户名去数据库查询，如果不存在则抛出UsernameNotFoundException异常
        SysAccount sysAccount = sysAccountService.selectOne(MapUtil.getMap("username",username));
        if(sysAccount ==null){
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        }else if(sysAccount.getStatusDcode().equals("locked")){
            throw new LockedException("用户被锁定，无法登陆");
        }else if(sysAccount.getStatusDcode().equals("disabled")){
            throw new DisabledException("用户已作废");
        }

        SysLoginAccountDetails sysLoginAccountDetails = new SysLoginAccountDetails();
        BeanUtils.copyProperties(sysAccount, sysLoginAccountDetails);

        List<String> list = sysPermissionService.selectCodeByUserIdCollection(sysLoginAccountDetails.getId());
        sysLoginAccountDetails.setPermissionCodeList(list);
//        sysLoginAccountDetails.setPassword(new BCryptPasswordEncoder().encode(sysLoginAccountDetails.getPassword()));

        return sysLoginAccountDetails;

        /*Set<SimpleGrantedAuthority> collect = list.parallelStream().map(p -> new SimpleGrantedAuthority(p)).collect(Collectors.toSet());
        return new User(sysLoginAccountDetails.getUsername(),sysLoginAccountDetails.getPassword(),collect);*/
    }
}
