package kr.bora.chatv1.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HealthCheckHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()) {
                case READER_IDLE:
                    log.info("IDLE STATE");
                    break;
                case WRITER_IDLE:
                    log.info("WRITER_IDLE");
                    break;
                case ALL_IDLE:
                    log.info("ALL_IDLE channel , users: "+ EchoServerHandler.users.size());
                    Channel channel = ctx.channel();
                    channel.close();
                    log.info("channel , users : "+EchoServerHandler.users.size());
            }
        }
    }
}
