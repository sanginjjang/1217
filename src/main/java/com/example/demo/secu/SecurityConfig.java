package com.example.demo.secu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 설정 클래스 입니다. 라는 뜻
@EnableWebSecurity // 시큐리티 설정이라는 뜻.
public class SecurityConfig {

   
   @Bean
   BCryptPasswordEncoder passwordEncoder() {
      
      return new BCryptPasswordEncoder();
   }
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
         .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()) // 모든 요청 허용
         .csrf(csrf -> csrf.disable()) // CSRF 비활성화
         .formLogin(form -> form.disable()) // 로그인 폼 비활성화
         .httpBasic(basic -> basic.disable()); // HTTP 기본 인증 비활성화
      
      return http.build();
   }
//   @Bean
//   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//      http.authorizeHttpRequests((auth) -> auth
//    		.requestMatchers("/images/**","/css/**","/js/**","/error/**","/fragments/**").permitAll()
//    		.requestMatchers("/","/registForm","/registStudent","/loginProc").permitAll()
//            .requestMatchers("/student/**","/test/**").hasAnyRole("STUDENT","ADMIN")
//            .requestMatchers("/admin/**").hasRole("ADMIN")
//            .anyRequest().authenticated());
//      
//      http.formLogin((auth) -> auth
//    		.loginPage("/loginForm")
//    		.loginProcessingUrl("/loginProc")
//    		.failureUrl("/loginProc?error")
//            .defaultSuccessUrl("/") //url 패턴으로 가는거니깐 //page1 //url 패턴으로 가는거
//            .permitAll()
//            ); //리다이렉트를 사용해서 로그인폼으로 가게끔 만들어주는거
//      
//      http.csrf(auth -> auth.disable()); //GET 이외의 맵핑도 가능하게 해주는 메소드
//      
//      http.logout(auth -> auth.logoutSuccessUrl("/"));
//      return http.build();
//
//   }
}
