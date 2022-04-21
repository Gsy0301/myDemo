package com.weilian.entity;

/**
 * <p> description </p>
 *
 * @author Guo S.Y.
 * @version V1.0
 * @since 2022/4/13-17:57
 */
public class Stu {
    private String name;
    private int age;

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

    @Override
    public String toString() {
        return "Stu{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
