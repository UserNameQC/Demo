package com.qjc.scroll_recycler.entity;

/**
 * Created by QiaoJunChao on 2018/12/28.
 */

public class UserEntity {
    public String name;
    public int age;
    public String address;
    public int ItemType;

    public int getItemType() {
        return ItemType;
    }

    public void setItemType(int itemType) {
        ItemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
