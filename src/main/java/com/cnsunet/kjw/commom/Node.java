package com.cnsunet.kjw.commom;

import io.swagger.models.auth.In;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: huangjie
 * @Description :
 * @Date: Created in 9:54 2018/7/4
 * @Modified By:
 */
public class Node implements Comparable<Node>{
    private int id;
    private int parentId;
    private String text;
    private Integer state;   //节点的状态
   // private NodeAttribute nodeAttribute;
    private List<Node> children=new LinkedList<>();
    @Override
    public int compareTo(Node o) {
        if(id>o.id)
            return 1;
        if(id < o.id)
            return -1;
        return 0;
    }
    public Node(int id, int parentId, String text, Integer state/*, NodeAttribute nodeAttribute*/) {
        this.id = id;
        this.parentId = parentId;
        this.text = text;
        this.state = state;

      //  this.nodeAttribute = nodeAttribute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


   /* public NodeAttribute getNodeAttribute() {
        return nodeAttribute;
    }

    public void setNodeAttribute(NodeAttribute nodeAttribute) {
        this.nodeAttribute = nodeAttribute;
    }*/

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
