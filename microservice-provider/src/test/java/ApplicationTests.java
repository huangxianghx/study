import com.mindai.Bootstrap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2016年11月22日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Bootstrap.class)
public class ApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void test() throws Exception {
        // 保存字符串
        redisTemplate.opsForValue().set("aaa", "-------111");
        System.out.println(redisTemplate.opsForValue().get("aaa"));
        Assert.assertEquals("-------111", redisTemplate.opsForValue().get("aaa"));
    }
}