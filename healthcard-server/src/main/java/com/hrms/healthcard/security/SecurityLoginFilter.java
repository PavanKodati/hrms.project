package com.hrms.healthcard.security;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.healthcard.dto.LoginRequestDTO;
import com.hrms.healthcard.enums.ProviderEnum;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityLoginFilter extends AbstractAuthenticationProcessingFilter {

	private TokenService tokenService;

	protected SecurityLoginFilter(RequestMatcher requiresAuthenticationRequestMatcher,
			AuthenticationManager authenticationManager, TokenService tokenService) {
		super(requiresAuthenticationRequestMatcher, authenticationManager);
		this.tokenService = tokenService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		final LoginRequestDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);

		AbstractAuthenticationToken token = null;
		if (loginDTO.getProvider().equals(ProviderEnum.Google.toString())) {
			token = new GoogleAuthToken(null, loginDTO.getCredentials(), null);
		}
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		GoogleAuthToken tokenObj = (GoogleAuthToken) authentication;
		log.info(tokenObj.toString());
		
			tokenService.addTokenToAuthHeader((String) tokenObj.getPrincipal(), response);
		
	}

}
