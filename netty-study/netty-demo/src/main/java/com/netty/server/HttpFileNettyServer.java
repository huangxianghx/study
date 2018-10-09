package com.netty.server;

import com.netty.handler.HttpFileServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileNettyServer {
    private static final String DEFAULT_URL = "src/main/java/com/netty";
    private static String host = "127.0.0.1";
    private static int port = 6789;

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是ServerBootstrap。
     **/
    public void start(final int port,final String url) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();//accept
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();//bizWork
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast("encoder", new StringEncoder())//服务端返回response,加载response编码
                                    .addLast("decoder", new StringDecoder())//服务端接受request,加载request解码
                                    .addLast("chunked", new ChunkedWriteHandler())//支持异步发送大码流
                                    .addLast("file-handler", new HttpFileServerHandler());//具体业务处理逻辑
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = bootstrap.bind(host,port).sync();
            System.out.println("http file server start success... url:"+host+":"+port);
            // 等待服务器  socket 关闭 。
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    //启动netty服务器
    public static void main(String[] args){
        try{
            String url = DEFAULT_URL;
            new HttpFileNettyServer().start(port,url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
