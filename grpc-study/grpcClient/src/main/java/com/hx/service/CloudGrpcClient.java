package com.hx.service;

import io.grpc.Channel;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author huangxiang
 * @Note spring cloud 集成 grpc 客户端
 * @date 2017年11月21日 20:30
 */
@Service
public class CloudGrpcClient {
    @GrpcClient("grpc-server")
    protected Channel serverChannel;

    public String sayHello(String name){
        HelloReply response = getStub().sayHello(HelloRequest.newBuilder().setName(name).build());
        return response.getMessage();
    }

    private GreeterGrpc.GreeterBlockingStub getStub() {
        return GreeterGrpc.newBlockingStub(serverChannel);
    }
}
