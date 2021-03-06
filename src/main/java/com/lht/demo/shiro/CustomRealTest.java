package com.lht.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @Description TODO
 * @Author LiHaitao
 * @Date 2018/8/19 14:34
 * @UpdateUser
 * @UpdateDescrip
 * @UpdateDate
 * @Version 1.0.0
 **/
public class CustomRealTest {
    public static void main(String[] args) {
        CustomRealm customRealm=new CustomRealm();
        //构建SecurityMannager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);
        EhCacheManager cacheManager = new EhCacheManager();
        //加密后的认证
        HashedCredentialsMatcher hashedCredentialsMatcher=new RetryLimitHashedCredentialsMatcher(cacheManager);
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);


        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();
        int i;
        for( i=0;i<4;i++) {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("lihaitao", "1234567");
            subject.login(usernamePasswordToken);
            System.out.println("isAuthenticated:" + subject.isAuthenticated());
        }
        System.err.println("i:"+i);
        subject.checkPermission("user:delete");
        subject.checkRole("admin");



    }
}
