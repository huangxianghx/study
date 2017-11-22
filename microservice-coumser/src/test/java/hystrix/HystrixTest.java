package hystrix;

import com.hx.common.hystrix.HystrixResponseEntity;
import com.hx.common.hystrix.HystrixBreakerCommand;
import org.junit.Test;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年02月03日
 */
public class HystrixTest {
    /**
     * 测试断路器超时
     */
    @Test
    public void timeoutTest(){
        HystrixBreakerCommand<String,String> command=new HystrixBreakerCommand<>("testGroup","testKey","testThreadPoolKey",1000);
        HystrixResponseEntity<String> str=command.execute();
        System.out.println(str.getMsg());
    }
}
