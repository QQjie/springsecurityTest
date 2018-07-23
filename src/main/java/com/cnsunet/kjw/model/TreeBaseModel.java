package com.cnsunet.kjw.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangjie
 * @Description :实体类的基类
 * @Date: Created in 9:23 2018/7/13
 * @Modified By:
 */
public class TreeBaseModel {
    /**
    *id
    */
    private Integer id;

    /**
    *parentId
    */
    private Integer parentId;

    /**
    * NAME
    */
    private String name;

    /**
    *status
    */
    private Integer status;



    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static <T extends TreeBaseModel> Map<Integer,T> idEntityMap(Collection<T> list){

        Map<Integer,T> map=new HashMap<>();

        if(null==list||0==list.size()){
            return map;
        }

        for(T entity:list){
            map.put(entity.getId(),entity);
        }
        return map;
    }
    public TreeBaseModel(){}

    public TreeBaseModel(Integer id, Integer parentId, String name, Integer status) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.status = status;
    }
}
