package com.base.annontation;

/**
 * @desc: Created by Fengyun on 4/10/2018.
 */
public class Student {
    private String stuNO;
    private String name;
    private int age;

    public String getStuNO() {
        return stuNO;
    }

    @ValueBind(type = ValueBind.fieldType.STRING,value = "NO0001")
    public void setStuNO(String stuNO) {
        this.stuNO = stuNO;
    }

    public String getName() {
        return name;
    }

    @ValueBind(type = ValueBind.fieldType.STRING,value = "Tom")
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @ValueBind(type = ValueBind.fieldType.INT,value = "18")
    public void setAge(int age) {
        this.age = age;
    }
}
