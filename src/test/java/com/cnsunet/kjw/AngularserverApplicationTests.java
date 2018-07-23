package com.cnsunet.kjw;

import com.cnsunet.kjw.commom.Node;
import com.cnsunet.kjw.commom.Tree;
import com.cnsunet.kjw.exception.DBErrorException;
import com.cnsunet.kjw.model.sysnamager.MenuModel;
import com.cnsunet.kjw.model.sysnamager.PermissionModel;
import com.cnsunet.kjw.model.sysnamager.UserModel;
import com.cnsunet.kjw.repository.FlowAllRepository;
import com.cnsunet.kjw.repository.sysmanage.PermissionRepository;
import com.cnsunet.kjw.repository.sysmanage.RoleRepository;
import com.cnsunet.kjw.repository.sysmanage.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.INITIALIZE;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AngularserverApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlowAllRepository flowAllRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Test
    public void contextLoads() {

        try {
            userRepository.getUserByName("name");
            List<PermissionModel> l=userRepository.getUserPermissionById(1);
            UserModel user=userRepository.getUserById(1);
            List<String> list=userRepository.getUserPermAndOper("name");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
           // System.out.println(list.get(0).get("用户管理权限"));
            List<MenuModel> list1=userRepository.getAllMenuByUserId(1);
            List<MenuModel> list2=userRepository.getFirstLevelMenu(1);
            for (int i = 0; i < list2.size(); i++) {
                System.out.println(list2.get(i).getName()+"----");
            }
            List<MenuModel> list3=userRepository.getChildrenMenu(1,1);
            for (int i = 0; i < list3.size(); i++) {
                System.out.println(list3.get(i).getName()+"zeeee----");
            }
            System.out.println("----------------------permission--------------------------");
            List<Integer> ids=new ArrayList<>();
            ids.add(2);
            ids.add(4);
            ids.add(8);
           // permissionRepository.addPmsForRole(6,ids);
            permissionRepository.updatePmsOperate(5,ids);


           // permissionRepository.updatePmsForRole(6,ids);

            List<Integer> ilist=roleRepository.getPmsIdsRole(6);
            for (int i = 0; i < ilist.size(); i++) {
                System.out.println(ilist.get(i));
            }

        }catch (DBErrorException E){
            E.printStackTrace();
        }


    }

}
