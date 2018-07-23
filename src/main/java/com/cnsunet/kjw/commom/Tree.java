package com.cnsunet.kjw.commom;



import com.cnsunet.kjw.model.TreeBaseModel;

import java.util.*;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 9:57 2018/7/4
 * @Modified By:
 */
public class Tree<T extends TreeBaseModel> {

    private List<Node> nodes=new LinkedList<>();

    private Node root=null;//根节点

    public Tree(int id,List<T> areaInfoList){
        int pid=-1;
        for (T areaInfo : areaInfoList) {
            /*if(id==areaInfo.getId()){
                pid=areaInfo.getParentId();
            }*/
            Node node=new Node(areaInfo.getId(),areaInfo.getParentId(),areaInfo.getName(),areaInfo.getStatus());
            nodes.add(node);
            if(node.getParentId()==pid){
                root=node;
            }
        }
    }

    public List<Node> build(){
        buildTree(root);
        List<Node> results=new ArrayList<>();
        results.add(root);
        return results;
    }

    private void buildTree(Node parent){
        Node node=null;
        Iterator<Node> it=nodes.iterator();
        while (it.hasNext()){
            node=it.next();
            if(Objects.equals(node.getParentId(),parent.getId())){
                parent.getChildren().add(node);
                buildTree(node);
            }
        }
    }
}
