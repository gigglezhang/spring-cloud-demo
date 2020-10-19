package com.jc.oauth2.config;

import com.jc.oauth2.component.MockUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author jincheng.zhang
 */
@Configuration
@EnableAuthorizationServer
@Order(2)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {



    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MockUserDetailServiceImpl mockUserDetailService;
    @Autowired
    RedisTokenStore redisTokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //ClientId、Client-Secret：这两个参数对应请求端定义的 cleint-id 和 client-secret
                .withClient("customer")
                .secret(passwordEncoder.encode("customer-secret-8888"))
                /* authorization_code：授权码类型。
                    implicit：隐式授权类型。
                    password：资源所有者（即用户）密码类型。
                    client_credentials：客户端凭据（客户端ID以及Key）类型。
                    refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。
                 */
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                //token 的有效期
                .accessTokenValiditySeconds(1800)
                //用来限制客户端访问的权限，在换取的 token 的时候会带上 scope 参数，只有在 scopes 定义内的，才可以正常换取 token
                .scopes("all").redirectUris("http://www.baidu.com");
        super.configure(clients);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");
    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(mockUserDetailService)
                .tokenStore(redisTokenStore);
    }
}
