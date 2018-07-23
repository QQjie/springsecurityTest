/*
package com.cnsunet.kjw.security.CustomSercurityExpression;

import com.cnsunet.kjw.repository.sysmanage.UserRepository;
import com.cnsunet.kjw.security.CustomSecurityExpressionRoot;
import com.cnsunet.kjw.security.CustomWebAuthenticationDetails;
import com.cnsunet.kjw.utils.page.OperateConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

*/
/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 11:47 2018/7/18
 * @Modified By:
 *//*

@Component
public class UserExpression extends CustomSecurityExpressionRoot {
    @Autowired
    private UserRepository userRepository;

    */
/**
     *@Author  huangjie
     *@Description 自定义用户修改权限
     *@Date  2018/7/18 10:04
     *@Param
     *@Return
     *@Modyfied by
     *//*

    public boolean hasUserUpdatePms(){
        CustomWebAuthenticationDetails customWebAuthenticationDetails=(CustomWebAuthenticationDetails)authentication.getDetails();
        String userName=customWebAuthenticationDetails.getUserName();
        List<String> list = userRepository.getUserPermAndOper(userName);
        for (int i = 0; i < list.size(); i++) {
            String[] arr=list.get(i).split("-");
            if (arr[0].equals("用户管理权限")&&((Integer.valueOf(arr[1])&OperateConst.PUT)>=OperateConst.PUT)) {
                return true;
            }
        }
        return false;
    }
}
*/
