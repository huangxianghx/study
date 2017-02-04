import com.mindai.Bootstrap;
import com.mindai.facade.model.UserResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Bootstrap.class)
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, UserResponse> redisTemplate;


    @Test
    public void test() throws Exception {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        System.out.println("------->"+stringRedisTemplate.opsForValue().get("aaa"));
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }


    @Test
    public void testUser() throws Exception {
        System.out.println("---->1"+redisTemplate.opsForValue().get("huangxiang"));
        // 保存对象
        UserResponse user = new UserResponse();
        user.setName("huangxiang");
        user.setAge("22");
        user.setSex("man");
        redisTemplate.opsForValue().set(user.getName(), user);
        System.out.println("---->2"+redisTemplate.opsForValue().get(user.getName()));
//        Assert.assertEquals("22", redisTemplate.opsForValue().get("huangxiang").getAge());
    }
}