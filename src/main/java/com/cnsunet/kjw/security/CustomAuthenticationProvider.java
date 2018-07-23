package com.cnsunet.kjw.security;


import com.cnsunet.kjw.model.sysnamager.UserModel;
import com.cnsunet.kjw.repository.sysmanage.UserRepository;
import com.cnsunet.kjw.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangjie
 * @Description :AuthenticationProvider提供登录验证处理逻辑，实现该接口编写自己的验证逻辑。
 * @Date: Created in 11:49 2018/6/28
 * @Modified By:
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;

    /*@Autowired
    private RedisService redisService;*/
    @Override
    public Authentication authenticate(Authentication authentication) throws UsernameNotFoundException, AuthenticationException {

        System.out.println(authentication.getDetails());
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();  // 如上面的介绍，这里通过authentication.getDetails()获取详细信息
        System.out.println(details.getToken());
        details.setToken("huagnjietoken");
        System.out.println(details);
        System.out.println(details.getUserName()+"=========");
        UserModel userModel=userRepository.getUserByName(details.getUserName());

//        System.out.println(userModel.getUserName()+userModel.getPassword()+"------");
        if (userModel==null||!(userModel.getPassword().equals(MD5Util.encode(details.getPassword())))) {
            throw new UsernameNotFoundException(
                    "User '" + details.getUserName() +"' not found ."
            );
        }
        List<String> list = userRepository.getUserPermAndOper(details.getUserName());
        List<GrantedAuthority> authorities =new ArrayList<GrantedAuthority>();

        for (int i = 0; i < list.size(); i++) {
            authorities.add(new SimpleGrantedAuthority(list.get(i)));
        }
      /*  if(userModel.getUserName().equals("hj")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        }else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }*/
       // authorities = userRepository.getUserPmsByName(details.getUserName());

        return new UsernamePasswordAuthenticationToken(userModel.getUserName(),userModel.getPassword(),authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
