package cn.kunli.una.expression;

import cn.kunli.una.pojo.BasePojo;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

public class CustomSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private String entityName;

    public CustomSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public final boolean hasAnyAuthorityByEntity(BasePojo entity, String... authorities) {
        String entityName = entity.getClass().getSimpleName();
        List<String> permissionList = new ArrayList<>();
        for (String authority : authorities) {
            permissionList.add(entityName+":"+authority);
        }
        String[] strings = permissionList.toArray(new String[permissionList.size()]);
        return super.hasAnyAuthority(strings);
    }

    @Override
    public void setFilterObject(Object o) {

    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object o) {

    }

    @Override
    public Object getReturnObject() {
        return null;
    }

    @Override
    public Object getThis() {
        return null;
    }
}
