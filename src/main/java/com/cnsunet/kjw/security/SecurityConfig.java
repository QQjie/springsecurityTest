package com.cnsunet.kjw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Author: huangjie
 * @Description :spring security框架配置
 * @Date: Created in 15:21 2018/6/22
 * @Modified By:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("myDataSource")
    private DataSource myDataSource;

    /*@Autowired
    private UserServiceForSecurity userServiceForSecurity;*/
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    private CustomAuthenticationFailHander customAuthenticationFailHander;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置自定义的AuthenticationProvider
        auth.authenticationProvider(authenticationProvider);
            /*auth.userDetailsService(userServiceForSecurity).passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence rawPassword) {
                    return MD5Util.encode((String)rawPassword);
                }

                @Override
                public boolean matches(CharSequence rawPassword, String encodedPassword) {
                    return encodedPassword.equals(MD5Util.encode((String)rawPassword));
                }
            });*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
        http.csrf().disable()
               // .authorizeRequests().antMatchers("/api/user").hasAnyRole("北京组:SADMIN-15")
              //  .antMatchers("/api/redis").hasRole("ADMIN")
               // .antMatchers("/").permitAll()
               // .antMatchers("/api/login").permitAll()
               // .antMatchers("/swagger-ui.html").permitAll()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint( customNotLoginAuthenticationEntryPoint())//用户未登录处理
                .and()
                .formLogin()
                .loginPage("/index.html")
               // .loginProcessingUrl("/api/notlogin")  这个请求路径是 form表单的anction值
               // .failureHandler(customAuthenticationFailHander)
                //.successHandler(savedRequestAwareAuthenticationSuccessHandler())
                .authenticationDetailsSource(authenticationDetailsSource)
               // .successForwardUrl("/swagger-ui.html")
                //.loginPage("/login.html")/*.loginProcessingUrl("/api/login")*/.successForwardUrl("/index.html")
                //.defaultSuccessUrl("/swagger-ui.html")
                .failureUrl("/error.html")
               // .loginProcessingUrl("/api/login").usernameParameter("username").passwordParameter("password")
                //.failureForwardUrl("/index.html")//.usernameParameter("username").passwordParameter("password")
               // .defaultSuccessUrl("/swagger-ui.html")
                .permitAll()//.and()
                .and()
                .logout()//.addLogoutHandler(getCustomLogoutHandler())
                //.logoutUrl("/api/logout")
                .logoutSuccessUrl("/login.html")
                .permitAll();
               // .rememberMe().tokenValiditySeconds(3149200);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",   //swagger api json
                "/swagger-resources/configuration/ui",         //用来获取支持的动作
                "/swagger-resources",                             //用来获取api_doc的url
                "/swagger-resources/configuration/security",  //安全选项
                "/swagger-ui.html","/webjars/**",
                "/api/login","/api/logout","/api/notlogin",
                "/**.js"                                            //允许static文件夹下所有的js文件可访问，防止前后端整合时，文件不可访问的问题
        );
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        ProviderManager authenticationManager = new ProviderManager(Arrays.asList(customAuthenticationProvider));
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return authenticationManager;
        // return super.authenticationManagerBean();
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new RestAuthenticationAccessDeniedHandler();
    }

   /* @Bean
    public CustomLogoutHandler getCustomLogoutHandler() {
        return new CustomLogoutHandler();
    }*/
   @Bean
   public AuthenticationEntryPoint customNotLoginAuthenticationEntryPoint() {
       return new CustomNotLoginAuthenticationEntryPoint("/api/login");
   }


    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("/api/login");
        return auth;
    }

    /*@Bean
     public DefaultMethodSecurityExpressionHandler get(){
         DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler=new DefaultMethodSecurityExpressionHandler();
         defaultMethodSecurityExpressionHandler.setPermissionEvaluator(new MyPermissionEvaluator());
         return defaultMethodSecurityExpressionHandler;
     }*/

}
