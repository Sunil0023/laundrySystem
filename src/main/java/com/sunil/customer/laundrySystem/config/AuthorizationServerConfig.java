//package com.sunil.customer.laundrySystem.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
//@EnableAuthorizationServer
//@Configuration
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.tokenKeyAccess("permitAll()")
//				.checkTokenAccess("isAuthenticated()");
//	} 
//	
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		
//		clients
//				.inMemory()
//				.withClient("clientId")
//				.secret("secret")
//				.authorizedGrantTypes("authorization_code")
//				.scopes("user_info")
//				.autoApprove(true);
//	}
//	
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.authenticationManager(authenticationManager);
//	}
//
//	
//
//
//}
//
//
//
