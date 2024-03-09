package com.expense.oauth.config;

import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

public class SutiTokenEnhancer extends JwtAccessTokenConverter{

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User userDto=(User)authentication.getPrincipal();
		Map<String,Object> info =accessToken.getAdditionalInformation();
		//info.put("email", userDto.getUsername());
		new DefaultOAuth2AccessToken(accessToken).setAdditionalInformation(info);;
		
		
		
		return super.enhance(accessToken, authentication);
	}
	
	

}
