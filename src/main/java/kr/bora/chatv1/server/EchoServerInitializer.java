package kr.bora.chatv1.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

public class EchoServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LineBasedFrameDecoder(65536));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new EchoServerHandler());
//    pipeline.addLast(new HttpServerCodec());
//    pipeline.addLast(new HttpServerUpgradeHandler());

        //연결된 클라이언트의 접속 허용 시간 이 인수(초) 해당 시간동안 채팅을 안치면 자동 아웃 시켜버림
        pipeline.addLast(new ReadTimeoutHandler(600, TimeUnit.SECONDS));
    }
}