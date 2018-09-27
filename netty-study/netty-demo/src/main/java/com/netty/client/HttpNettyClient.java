package com.netty.client;

import com.netty.handler.HttpClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;

import java.net.URI;

/**
 * 发送Http信息客户端
 */
public class HttpNettyClient {
    private static AsciiString contentType = HttpHeaderValues.TEXT_PLAIN;

    public static String host = "127.0.0.1";  //ip地址
    public static int port = 6789;          //端口

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是ServerBootstrap。
     **/
    public static void main(String[] args) throws Exception {
        System.out.println("客户端成功启动...");
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                 .channel(NioSocketChannel.class)
                 .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast("decoder", new HttpResponseDecoder())//客户端接受response,加载response解码
                                .addLast("encoder", new HttpRequestEncoder())//客户端发送request,加载request编码
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                .addLast("handler",new HttpClientHandler());//具体业务处理逻辑
                }
            });
        // 连接服务端
        ChannelFuture f = bootstrap.connect(host, port).sync();
        // 构建http请求
        URI uri = new URI("http://127.0.0.1:6789");
        String msg = "Are you ok?";
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));
        request.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
        request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        // 通过客户端像服务器发送http请求
        f.channel().write(request);
        f.channel().flush();
        f.channel().closeFuture().sync();
    }
}
