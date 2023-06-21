package com.example.springbasic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author junyeong.jo .
 * @since 2023-06-21
 */
@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(12);
        System.out.println("helloLombok.getAge()= " + helloLombok.getAge());
        System.out.println("helloLombok = " + helloLombok);
    }
}
