package cn.lee.db.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Lee
 * @date : 2019/6/26
 */
@Data
@NoArgsConstructor
@TableName("t_person")
public class Person {

    @TableId
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private Date createdTime;
    @TableField(update = "now()", fill = FieldFill.UPDATE)
    private Date updatedTime;

    public Person(Long id, String name, String gender, Integer age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
}
