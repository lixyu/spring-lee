package cn.lee.stream.po;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Lee
 * @date : 2019/6/19
 */
@Data
@NoArgsConstructor
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
