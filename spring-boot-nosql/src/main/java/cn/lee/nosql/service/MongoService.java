package cn.lee.nosql.service;

import cn.lee.nosql.entity.Person;
import cn.lee.nosql.util.MD5Utils;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Lee
 * @date 2019-07-24 15:13
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MongoService {
    private final MongoTemplate mongoTemplate;
    private final GridFsTemplate gridFsTemplate;
    private final GridFSBucket gridFSBucket;

    public void addUser(String name, int age) {
        Person person = new Person(name, age);
        mongoTemplate.save(person);
    }

    public Person findById(String userId) {
        return mongoTemplate.findById(userId, Person.class);
    }

    public String uploadFile() throws IOException {
        File file = ResourceUtils.getFile("classpath:static/bd_logo1.png");

        ClassPathResource resource = new ClassPathResource("static" + File.separator + "bd_logo1.png");

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectId objectId = gridFsTemplate.store(resource.getInputStream(), createResourceCode());
        return objectId.toString();

    }

    public GridFsResource queryFile() throws IOException {
        String id = "5d3822f623b4dd3a307f6b82";
        //根据id查询文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        //打开流下载对象
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, downloadStream);
        return gridFsResource;
    }

    public void testDelFile(String id) throws IOException {
        //根据文件id删除fs.files和fs.chunks中的记录
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
    }

    private String createResourceCode() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return MD5Utils.encode(String.valueOf((uuid).hashCode())).toUpperCase();
    }
}
