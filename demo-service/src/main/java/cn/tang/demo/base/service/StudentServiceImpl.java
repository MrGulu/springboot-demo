package cn.tang.demo.base.service;

import cn.tang.demo.base.daos.StudentMapper;
import cn.tang.demo.base.entity.Student;
import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ObjectUtils;

/**
 * @description 测试demo service
 * @author tangwenlong
 */
@Slf4j
@Service(timeout = 30000)
@SuppressWarnings("unused")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    private static final RedisSerializer STRING_REDIS_SERIALIZER = new StringRedisSerializer();

    @Override
    public String sayHello(String name) {
        log.info("接收到的消息：service receive msg:" + name);
        return "Hi,SpringBoot-Dubbo "+name;
    }

    @Override
    public Student getStudentById(Integer id) {
        redisTemplate.setKeySerializer(STRING_REDIS_SERIALIZER);
        Student student = (Student) redisTemplate.opsForValue().get("user" + id);
        //高并发情况下，存在缓存穿透，此处用双重检测锁
        if (null == student) {
            synchronized (this){
                student = (Student) redisTemplate.opsForValue().get("user" + id);
                if (null == student) {
                    log.info("查询数据库id=[{}]", id);
                    student = studentMapper.selectByPrimaryKey(id);
                    if (!ObjectUtils.isEmpty(student)) {
                        redisTemplate.opsForValue().set("user" + id, student);
                    }
                }
                //数据库中也没有返回空对象
                if (ObjectUtils.isEmpty(student)) {
                    return new Student();
                }
            }
        }else {
            log.info("查询缓存id=[{}],student=[{}]", id, student);
        }
        return student;
    }

}

