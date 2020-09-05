package com.demo.registration.shiro.realm;
import com.demo.registration.dao.UserDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class CustomAuthRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String userName =  usernamePasswordToken.getUsername();
        if(StringUtils.isEmpty(userName))
        {
            return null;
        }
        //查询密码
        String password = userDao.findPasswordByUsername(userName);
        if (!StringUtils.isEmpty(password))
        {
            return new SimpleAuthenticationInfo(userName, password,
                    ByteSource.Util.bytes(userName), getName());
        }
        return null;
    }
}
