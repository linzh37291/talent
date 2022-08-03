package com.lin.facade.handle;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@ChannelHandler.Sharable
@Service
@Slf4j
public class TcpDecoderHandler extends MessageToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List list) {
		log.info("解析client上报数据");
	}
}
