package com.lin.facade.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linzihao
 */
@Service
@Slf4j
public class UdpDecoderHandler extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket byteBuf, List<Object> list)
            throws Exception {
        ByteBuf byteBuf1 = byteBuf.content();
        int size = byteBuf1.readableBytes();
        byte[] data = new byte[size];
        byteBuf1.readBytes(data);
        log.info(new String(data));
    }
}
