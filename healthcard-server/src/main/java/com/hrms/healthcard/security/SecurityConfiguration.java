package com.hrms.healthcard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hrms.healthcard.urlConfig.RestUrlConfig;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

	@Autowired
	TokenService tokenService;
	
	@Autowired
	private EntryPoint entryPoint;
	
	
	// read the clientId from @value annotation from properties file
	
		@Bean
  public AuthenticationEntryPoint myAuthenticationEntryPoint() {
      return new EntryPoint();
  }
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher(RestUrlConfig.HEALTHCARD_SERVER+"/social"),new AntPathRequestMatcher("/health")).permitAll().anyRequest().authenticated()).exceptionHandling();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authenticationProvider(new GoogleAuthProvider()).exceptionHandling()
		.authenticationEntryPoint(myAuthenticationEntryPoint());
//		((request, response, e) -> {
//			response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setContentType("application/json");
//            response.getWriter().write("{ \r\n"
//            		+ "  \"Error message\": \"You are not authenticated/Token Expired\",\r\n"
//            		+ "  \"Status\": \"False\",\r\n"
//            		+ "  \"Status code\": \"401\"\r\n"
//            		+ "}" );
//        });
		
		
		http.apply(MyCustomDsl.customDsl(tokenService));
		http.csrf().disable();
		return http.build();
	}
	
	
	
	
	

   
}
