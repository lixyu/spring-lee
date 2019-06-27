package cn.lee.db.service;

import cn.lee.db.mapper.PersonMapper;
import cn.lee.db.model.Person;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;


/**
 * @author : Lee
 * @date : 2019/6/26
 */
@Repository
public class PersonService extends ServiceImpl<PersonMapper, Person> {
}
