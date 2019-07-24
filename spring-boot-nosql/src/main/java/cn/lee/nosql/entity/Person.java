package cn.lee.nosql.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Lee
 * @date 2019-07-24 15:08
 */
@Data
@NoArgsConstructor
@Document(collection = "user")
public class Person {
    @Id
    private String userId;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
