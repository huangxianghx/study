package com.hx.service;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangxiang
 * @Note spring cloud 集成 grpc 服务端
 * @date 2017年11月21日 20:19
 */
@GrpcService(GreeterGrpc.class)
public class CloudGrpcServer extends GreeterGrpc.GreeterImplBase{
    private static final Logger logger = LoggerFactory.getLogger(CloudGrpcServer.class);

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        logger.info("receive =============>{}",req.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello =============> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}

