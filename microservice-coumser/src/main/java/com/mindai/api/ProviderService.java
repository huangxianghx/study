package com.mindai.api;

import com.mindai.common.hystrix.HystrixBreakerCommand;
import com.mindai.common.hystrix.HystrixCommandHandler;
import com.mindai.common.hystrix.HystrixResponseEntity;
import com.mindai.facade.model.HelloRequest;
import com.mindai.facade.model.HelloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年02月03日
 */
@Component
public class ProviderService implements HystrixCommandHandler<HelloRequest,HelloResponse> {
    @Autowired
    private RestTemplate restTemplate;

    public HelloResponse generate(@RequestBody HelloRequest request) {
        HystrixBreakerCommand<HelloRequest,HelloResponse> command=new HystrixBreakerCommand<>("testGroup","testKey","testThreadPoolKey",1000);
        command.setDealParam(request,this.getClass());
        HystrixResponseEntity<HelloResponse> entity=command.execute();
        return entity.getData();
    }

    @Override
    public HelloResponse handle(HelloRequest request) throws Exception {
        HelloResponse response=new HelloResponse();
        String result=restTemplate.postForEntity("http://microservice-provider/hello", request.getName(), String.class).getBody();
        response.setResult(result);
        return response;
    }
}
