package cn.lee.json;

import cn.lee.json.util.JacksonUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Lee
 * @date 2019-09-27 11:39
 */
public class JsonTest {
    public static void main(String[] args) {
        Demo demo=new Demo("11","22","44");
        System.out.println(JSON.toJSONString(demo));

        System.out.println(JacksonUtils.obj2Json(demo));
    }
}

@Data
class Demo{
    private String name;
    private String code;
    @JSONField(serialize = false)
    private String desc;

    public Demo(String name, String code, String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }
}
