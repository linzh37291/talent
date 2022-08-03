package com.lin.talent.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;
import java.util.stream.IntStream;

public class TcpReactiveClient {

	private String host;
	private int port;
	/**
	 * 将<code>Channel</code>保存起来, 可用于在其他非handler的地方发送数据
	 */
	private Channel channel;
	private Bootstrap bootstrap;

	public TcpReactiveClient(String host, int port) {
		this.host = host;
		this.port = port;
		init();
	}

	public void connect() {
		ChannelFuture connect = bootstrap.connect(host, port);
		this.channel = connect.channel();
	}

	public void send(String data) {
		channel.writeAndFlush(data);
	}

	private void init() {
		EventLoopGroup group = new NioEventLoopGroup();
		// bootstrap 可重用, 只需在TcpClient实例化的时候初始化即可.
		bootstrap = new Bootstrap();
		bootstrap.group(group).channel(NioSocketChannel.class)
				.handler(new ClientHandlersInitializer(TcpReactiveClient.this));
	}

	public static void main(String[] args) {
		TcpReactiveClient tcpClient = new TcpReactiveClient("localhost", 9997);
		tcpClient.connect();
		IntStream.range(1, 10000000).parallel().forEach(i -> {
			tcpClient.send(UUID.randomUUID().toString());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
		});

	}

}