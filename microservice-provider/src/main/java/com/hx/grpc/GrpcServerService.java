//package com.mindai.grpc;
//
//import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
//
//@GrpcService(GreeterGrpc.class)
//public class GrpcServerService extends GreeterGrpc.GreeterImplBase {
//
//    @Override
//    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
//        HelloReply reply = HelloReply.newBuilder().setMessage("Hello =============> " + req.getName()).build();
//        responseObserver.onNext(reply);
//        responseObserver.onCompleted();
//    }
//}