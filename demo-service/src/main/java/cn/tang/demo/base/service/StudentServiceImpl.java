package cn.tang.demo.base.service;

import cn.tang.demo.base.daos.StudentMapper;
import cn.tang.demo.base.entity.Student;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
@Service(version = "1.0",retries = 0,timeout = 30000)
@Component
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    private static final RedisSerializer redisSerializer = new StringRedisSerializer();

    @Override
    public String sayHello(String name) {
        logger.info("接收到的消息：service receive msg:" + name);
        return "Hi,SpringBoot-Dubbo"+name;
    }

    @Override
    public Student getStudentById(Integer id) {
        redisTemplate.setKeySerializer(redisSerializer);
        Student student = (Student) redisTemplate.opsForValue().get("userNew" + id);
        //高并发情况下，存在缓存穿透，此处用双重检测锁
        if (null == student) {
            synchronized (this){
                student = (Student) redisTemplate.opsForValue().get("userNew" + id);
                if (null == student) {
                    System.out.println("查询数据库");
                    student = studentMapper.selectByPrimaryKey(id);
                    if (!ObjectUtils.isEmpty(student)) {
                        redisTemplate.opsForValue().set("userNew" + id, student);
                    }
                }
                if (ObjectUtils.isEmpty(student)) {
                    return new Student();
                }
            }
        }else {
            System.out.println("查询缓存");
        }
        return student;
    }

}

