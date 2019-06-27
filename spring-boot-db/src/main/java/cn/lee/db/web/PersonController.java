package cn.lee.db.web;

import cn.lee.db.model.Person;
import cn.lee.db.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Lee
 * @date : 2019/6/27
 */
@Slf4j
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/add")
    public String add(@RequestBody Person person) {

        personService.save(person);
        log.info("插入一条记录");
        return "SUCCESS";
    }

    @GetMapping("/get/{id}")
    public Person get(@PathVariable("id") Long id) {

        Person person = personService.getById(id);
        log.info("查询一条记录");
        return person;
    }

    @PostMapping("/update")
    public String update(@RequestBody Person person) {
        Person p = new Person(person.getId(), person.getName(), person.getGender(), person.getAge());
        personService.updateById(p);
        log.info("更新一条记录");
        return "SUCCESS";
    }

    @GetMapping("/del/{id}")
    public String del(@PathVariable("id") Long id) {

        personService.removeById(id);
        System.out.println("删除记录id=" + id);
        log.info("删除{}记录", id);
        return "SUCCESS";
    }
}
