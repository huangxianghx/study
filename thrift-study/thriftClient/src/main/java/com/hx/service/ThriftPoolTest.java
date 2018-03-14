package com.hx.service;

import com.hx.thrift.TestService;
import com.wmz7year.thrift.pool.ThriftConnectionPool;
import com.wmz7year.thrift.pool.config.ThriftConnectionPoolConfig;
import com.wmz7year.thrift.pool.exception.ThriftConnectionPoolException;
import org.apache.thrift.TException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ThriftPoolTest {
    public static void main(String[] args) throws Exception {

        try {
            ThriftConnectionPool<TestService.Client> pool = initConnectionPool();
            for(int i=0;i<100;i++){
                TestService.Client client = pool.getConnection().getClient();
                String response = client.sayHello("hello:"+i);
                System.out.println("response:"+response);
            }
            pool.close();
        } catch (ThriftConnectionPoolException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ThriftConnectionPool<TestService.Client> initConnectionPool() throws Exception{
        ThriftConnectionPoolConfig config = new ThriftConnectionPoolConfig();
        config.setConnectTimeout(3000);
        config.setThriftProtocol(ThriftConnectionPoolConfig.TProtocolType.BINARY);
        config.setClientClass(TestService.Client.class);
        config.addThriftServer("localhost", 8090);
        config.setMaxConnectionPerServer(2);
        config.setMinConnectionPerServer(1);
        config.setIdleMaxAge(2, TimeUnit.SECONDS);
        config.setMaxConnectionAge(2);
        config.setLazyInit(false);
        ThriftConnectionPool<TestService.Client> pool = new ThriftConnectionPool<TestService.Client>(config);
        return pool;
    }
}
