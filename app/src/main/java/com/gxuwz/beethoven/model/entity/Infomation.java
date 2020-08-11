package com.gxuwz.beethoven.model.entity;

public class Infomation {
    public String getType(){
        String clazz = this.getClass().toString();
        String classType = clazz.substring(clazz.lastIndexOf(".")+1);
        return classType;
    }
}
