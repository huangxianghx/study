package com.netty.server;

import com.netty.handler.StringServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 接受string信息服务器
 */
public class StringNettyServer {
    private int port;

    public StringNettyServer(int port){
        this.port = port;
    }

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是ServerBootstrap。
     **/
    public void start() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();//accept
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();//bizWork
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("decoder", new StringDecoder())
                                    .addLast("encoder", new StringEncoder())
                                    .addLast("handler", new StringServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = bootstrap.bind(port).sync();
            System.out.println("server start success...");
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
            StringNettyServer stringNettyServer = new StringNettyServer(6789);
            stringNettyServer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
