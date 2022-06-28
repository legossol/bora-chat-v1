package kr.bora.chatv1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.Socket;
import java.util.ArrayList;

public class WebServer  {
    static ArrayList<Socket> userList = new ArrayList<>();


    EventLoopGroup adminGroup = new NioEventLoopGroup(1);
    EventLoopGroup clientGroup = new NioEventLoopGroup();
    private final int port;
    public WebServer(int port) {
        this.port = port;
    }
    public void start(){
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //쓰레드 그룹 초기화
            bootstrap.group(adminGroup, clientGroup)
                    //채널 초기화
                    .channel(NioServerSocketChannel.class)//NIO 전송 채널을 이용하도록 지정 + nonblocking
//          .channel(EpollServerSocketChannel.class)epoll리눅스에서만 사용 가능
//          .channel(KQueueServerSocketChannel.class)//블로킹
//          .localAddress(new InetSocketAddress(port))
//          .handler(echoServerInitializer)

                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)//연결시도시 안됫을때 연결 시도 시간?
                    .option(ChannelOption.SO_BACKLOG,5)//동시에 수용 가능한 소켓 연결 요청 수
//          .childOption(ChannelOption.TC,true)
                    .childOption(ChannelOption.SO_LINGER,0)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)//정해진 시간마다 keepalive packet전송
                    .childOption(ChannelOption.SO_REUSEADDR,true)

                    //자식 채널의 초기화
//          .childHandler(new EchoServerHealthCheck())
                    .childHandler(new EchoServerInitializer());
//          {
//            //클라이언트로부터 연결된 채널이 초기화 될때의 기본동작이 지정되는 추상클래스
//            //해
//            protected void initChannel(SocketChannel ch) {
//
//              //채널 파이프라인의 객체 생성
//              ChannelPipeline pipe = ch.pipeline();
//
//              //채널 파이프라인에 EchoServerHandler 클래스를 등록하여 클라이언트와 연결될때 데이터 처리를 담당
//              //에코 서버 = 메아리 서버 : 클라이언트가 보낸 메세지를 그대로 돌려줘서 보낸 client가 볼 수 있게한다
//              pipe.addLast(new EchoServerHandler());
//            }
//          }
//      );
            ChannelFuture channelFuture = bootstrap.bind(port).sync();//서버를 비동기식으로 바인딩
            channelFuture.channel().closeFuture().sync();//채널의 CloseFuture를 얻고 완료될 때까지 현재 쓰레드를 블로킹
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            stop();
        }
    }

    //EventLoopGroup을 종료하고 모든 리소스를 해제
    private void stop(){

        adminGroup.shutdownGracefully().awaitUninterruptibly();
        clientGroup.shutdownGracefully().awaitUninterruptibly();
    }
}
