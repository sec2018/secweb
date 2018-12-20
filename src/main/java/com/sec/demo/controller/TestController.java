package com.sec.demo.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.sec.demo.pojo.Equipmenttype;
import com.sec.demo.pojo.User;
import com.sec.demo.pojo.UserMongo;
import com.sec.demo.service.EquipmenttypeService;
import com.sec.demo.util.redis.RedisService;
import com.sec.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class TestController {
	protected static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
    private RedisTemplate redisTemplate;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private RedisService redisService;

	@Resource
    private EquipmenttypeService equipmenttypeService;

    @Autowired
    private MongoTemplate mongoTemplate;
   
    public void set(){
        redisTemplate.opsForValue().set("key1","testValue1");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        Assert.assertEquals("testValue1",redisTemplate.opsForValue().get("key1"));
    }

    public void testobjSerializer() throws InterruptedException {
    	
       User user = userService.findUserByName("wang");
       redisTemplate.opsForValue().set("User:wang",user);
       Assert.assertEquals(true,redisTemplate.hasKey("User:wang"));
       if (redisTemplate.hasKey("User:wang")) {
           redisTemplate.delete("User:wang");
           logger.info("删除用户时候，从缓存中删除用户 >> User:wang");
       }
       User user2 = userService.findUserByName("chen");
       // 放入缓存，并设置缓存时间
       redisTemplate.opsForValue().set("User:chen", user2, 600, TimeUnit.SECONDS);
    }


    public void testRedisService() {
    	redisService.set("wang:phone", "15221275860");
    	System.out.println(redisService.get("wang:phone"));
    }


    @Transactional
    public void testRepository(){
        Equipmenttype equipmenttype = equipmenttypeService.findById(1);
        System.out.println(equipmenttype.toString());
    }


    public void testRuntime(){
        Runtime r = Runtime.getRuntime();
        Process p = null;
        try{
            p = r.exec("notepad");
        } catch (Exception e) {
            System.out.println("Error executing notepad.");
        }
    }

    @Test
    public void testMongoDb() throws Exception{
//        Query query=new Query(Criteria.where("username").is("rfsfd"));
//        UserMongo mgt =  mongoTemplate.findOne(query , UserMongo.class);
//        System.out.println(mgt.toString());

        UserMongo user1 = new UserMongo();
        user1.setUid(2);
        user1.setUsername("Tseng");
        user1.setPassword("sssss");
        UserMongo user2 = new UserMongo();
        user2.setUid(3);
        user2.setUsername("rfsfd");
        user2.setPassword("frgrr");
        mongoTemplate.save(user1);
        mongoTemplate.save(user2);
        Query query = new Query();
        Criteria criteria=Criteria.where("username").is("rfsfd");
        query.addCriteria(criteria);
        List<UserMongo> userList = mongoTemplate.find(query,UserMongo.class);
        System.out.println(userList.toArray()[0].toString());
    }
}
