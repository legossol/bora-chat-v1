package kr.bora.chatv1.server.domain;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import kr.bora.chatv1.server.EchoServerHandler;
import kr.bora.chatv1.server.EchoServerInitializer;
import kr.bora.chatv1.server.HealthCheckHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NettyServer {
    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public NettyServer(){
        this.mainGroup = new NioEventLoopGroup();
        this.subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap().group(mainGroup,subGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)//연결시도시 안됫을때 연결 시도 시간?
                .option(ChannelOption.SO_BACKLOG,5)//동시에 수용 가능한 소켓 연결 요청 수
//          .childOption(ChannelOption.TC,true)
                .childOption(ChannelOption.SO_LINGER,0)
                .childOption(ChannelOption.SO_KEEPALIVE,true)//정해진 시간마다 keepalive packet전송
                .childOption(ChannelOption.SO_REUSEADDR,true)
                .childHandler(new HealthCheckHandler())
                .childHandler(new EchoServerHandler())

        ;
    }

    public void start(){
        log.info("netty webSocket server started");
        try {
            future = server.bind(8888).sync();//서버를 비동기식으로 바인딩
            future.channel().closeFuture().sync();//채널의 CloseFuture를 얻고 완료될 때까지 현재 쓰레드를 블로킹
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            mainGroup.shutdownGracefully().awaitUninterruptibly();
            subGroup.shutdownGracefully().awaitUninterruptibly();
        }
    }

    private static class singletonNettyServer{
        static final NettyServer instance = new NettyServer();
    }

    public static NettyServer getInstance() {
        return singletonNettyServer.instance;
    }
}
