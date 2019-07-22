package com.ff.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author cornyu
 * @version 创建时间：2018年12月21日 下午7:17:57 类说明
 */
public class NettyServer {

	public static void main(String[] args) {
		//创建了一个引导类 ServerBootstrap，这个类将引导我们进行服务端的启动工作，直接new出来开搞。
		ServerBootstrap serverBootstrap = new ServerBootstrap();

		//bossGroup 表示监听端口，accept 新连接的线程组
		NioEventLoopGroup boss = new NioEventLoopGroup();
		
		//workerGroup表示处理每一条连接的数据读写的线程组
		NioEventLoopGroup worker = new NioEventLoopGroup();
		
		//NioServerSocketChannel和NioSocketChannel的概念可以和 BIO 编程模型中的ServerSocket以及Socket两个概念对应上
		serverBootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					protected void initChannel(NioSocketChannel ch) {
						ch.pipeline().addLast(new StringDecoder());
						ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
							@Override
							protected void channelRead0(ChannelHandlerContext ctx, String msg) {
								System.out.println("ctx:"+ctx+";msg:"+msg);
							}
						});
					}
				}).bind(8000);
				
	}

}
