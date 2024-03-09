package com.expense.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

@Configuration
public class Oauth2ServerConfiguration {
	
	
	@Autowired
	private UserDetailsService UserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(JwtAccessTokenConverter());
	}
	
	
	
	@Bean
	public JwtAccessTokenConverter  JwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter =new SutiTokenEnhancer();
		jwtAccessTokenConverter.setKeyPair(new KeyStoreKeyFactory(new ClassPathResource("sutiexpense.jks"),"teja9959".toCharArray()).getKeyPair("expensejwt"));
		return jwtAccessTokenConverter;
	}
	
	
	@Configuration
	@EnableResourceServer
	protected class Oauth2ResourceServer extends ResourceServerConfigurerAdapter{

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.tokenStore(tokenStore());
			super.configure(resources);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/**").authenticated();
			super.configure(http);
		}
		
	}
	
	@Configuration
	@EnableAuthorizationServer
	protected class Oauth2AuthrizationServer extends AuthorizationServerConfigurerAdapter{

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
			super.configure(security);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("client").secret(passwordEncoder.encode("client")).authorizedGrantTypes("password");
			super.configure(clients);
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.tokenStore(tokenStore()).tokenEnhancer(JwtAccessTokenConverter()).authenticationManager(authenticationManager).userDetailsService(UserDetailsService);
			super.configure(endpoints);
		}
		
	}
	
	

}
