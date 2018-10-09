package com.netty.client;

import com.netty.handler.HttpFileClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AsciiString;

/**
 * 发送Http信息客户端
 */
public class HttpFileNettyClient {
    public static String host = "127.0.0.1";  //ip地址
    public static int port = 6789;          //端口

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是ServerBootstrap。
     **/
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast("decoder", new StringDecoder())//客户端接受response,加载response解码
                                    .addLast("encoder", new StringEncoder())//客户端发送request,加载request编码
                                    .addLast("handler",new HttpFileClientHandler());//具体业务处理逻辑
                        }
                    });
            // 连接服务端
            ChannelFuture f = bootstrap.connect(host, port).sync();
            System.out.println("客户端成功启动...");
            f.channel().writeAndFlush("D:\\prod.jpg");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
        }

    }
}
