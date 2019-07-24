package cn.lee.nosql.web;

import cn.lee.nosql.entity.Person;
import cn.lee.nosql.service.MongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Lee
 * @date 2019-07-24 15:19
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/mongo")
public class MongoController {

    private final MongoService mongoService;

    @PostMapping("/add")
    public void add(@RequestBody Person person) {
        mongoService.addUser(person.getName(), person.getAge());
    }

    @GetMapping("/find/{id}")
    public Person find(@PathVariable("id") String id) {
        return mongoService.findById(id);
    }

    @GetMapping("/upload")
    public String upload() throws IOException {
        return mongoService.uploadFile();
    }

    @GetMapping("/download")
    public GridFsResource download() throws IOException {
        return mongoService.queryFile();
    }

    @GetMapping("/del/{id}")
    public String del(@PathVariable String id) throws IOException {
        mongoService.testDelFile(id);
        return "SUCCESS";
    }


}
