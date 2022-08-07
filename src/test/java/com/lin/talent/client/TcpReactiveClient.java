package com.lin.talent.client;

import com.lin.TalentApplication;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.boot.SpringApplication;

public class TcpReactiveClient {

    private final String serverHost;
    private final int serverPort;
    /**
     * 将<code>Channel</code>保存起来, 可用于在其他非handler的地方发送数据
     */
    private Channel channel;
    private Bootstrap bootstrap;

    public TcpReactiveClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        init();
    }

    public static void main(String[] args) {
        SpringApplication.run(TalentApplication.class, args);

    }

    public void connect() {
        ChannelFuture connect = bootstrap.connect(serverHost, serverPort);
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

}