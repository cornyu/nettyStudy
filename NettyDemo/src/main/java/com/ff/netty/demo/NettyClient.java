package com.ff.netty.demo;

import java.util.Date;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author cornyu
 * @version 创建时间：2018年12月21日 下午7:20:14 类说明
 */
public class NettyClient {

	public static void main(String args[]) throws InterruptedException {

		Bootstrap bootstrap = new Bootstrap();
		NioEventLoopGroup group = new NioEventLoopGroup();

		bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) {
				ch.pipeline().addLast(new StringEncoder());
			}
		});

		Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();

		while (true) {
			channel.writeAndFlush(new Date() + ": hello world!");
			Thread.sleep(2000);
		}
	}

}
