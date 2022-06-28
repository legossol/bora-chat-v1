package kr.bora.chatv1.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    //기존 접속자들
    public static final ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    public void channelActive(ChannelHandlerContext ctx)throws Exception{
        log.info("NEW USER JOINED = {}log", ctx.name());
        Channel newUser = ctx.channel();
        for(Channel channel : users){
            if(channel != newUser) {
                channel.writeAndFlush("[SERVER NOTIFY] == 새로운 유저가 입장 했습니다! 'ip:port ==" + newUser.remoteAddress()
                        + "' Has Joined!!!" + "\r\n");
            }
        }
        users.add(newUser);
        log.info( " 현재 채팅방의 전체 유저 수는:::::::"+users.size()+ "\r\n");
    }



    //채널이 비활성화 되었을때 발생
    //유저가 나갈때
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        Channel exitUser = ctx.channel();
        for(Channel channel: users){
            /*
             * if문이 없이 for문에 writeAndFlush하면 기존 유저들에게 메시지가 가지 않는다.*/
            if (channel != exitUser) {
                channel.writeAndFlush("[sened by : " + exitUser.remoteAddress() + "]" + "\r\n");
//        log.info("[sened by : " + exitUser.remoteAddress() + "]" + "\n");
            }
            channel.writeAndFlush("[SERVER NOTIFY] == One User 'ip:port =="+
                    exitUser.remoteAddress() + "'  Left The Chat Room "+ "\r\n");
        }
        log.info("[user exit the group]log ctx------"+ctx);

        log.info( " 그럼 사이즈는 :::::::"+users.size());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception{

        log.info("[SERVER-channelRead] Receive message =====" +  msg);
        Channel incoming = ctx.channel();

        log.info("channelGroup은???"+users +"세부보기"+ users.stream().sorted().collect(Collectors.toList()));

        for (Channel channel : users) {
            if (channel != incoming) {
                //flush : 채널에 기록된 데이터를 서버로 전송
                //write : 채널에 데이터를 기록
                //클라이언트1에게 받은 메시지를 다른 클라이언트에게 보낸다. 상대방의 정보와 메시지 전달.
                channel.writeAndFlush("[sened by : " + incoming.remoteAddress() + "]" + msg + "\n");
            }
        }
        if ("quit".equals(msg)) {
            ctx.close();
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception{
        cause.printStackTrace();
        log.info("예외 발생 !!!!");
        ctx.close();
    }
}
