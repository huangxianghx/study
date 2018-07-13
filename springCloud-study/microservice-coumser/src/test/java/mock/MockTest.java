package mock;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年04月28日
 */
public class MockTest {

    @Test
    public void test(){
        //mock一个Iterator类
        Iterator iterator = mock(Iterator.class);
        //预设当iterator调用next()时第一次返回hello，第n次都返回world
        when(iterator.next()).thenReturn("hello").thenReturn("world");
        //使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next()+" "+ iterator.next();
        //验证结果
        assertEquals("hello world world",result);
    }



}
