package com.sd.energy.grpc;

import com.sd.energy.grpc.services.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.Map;

@GrpcService
@Slf4j
public class ChatService extends ChatServiceGrpc.ChatServiceImplBase {

    Map<Long, StreamObserver<Message>> userMessagesStreamMap = new HashMap<>();
    Map<Pair<Long, Long>, StreamObserver<Message>> adminMessagesStreamMap = new HashMap<>();

    @Override
    public void startUserChat(UserChatRequest request, StreamObserver<Message> responseObserver) {
        userMessagesStreamMap.put(request.getUserId(), responseObserver);
        log.info("User [" + request.getUserId() + "] started a chat!");
    }

    @Override
    public void stopUserChat(UserChatRequest request, StreamObserver<Empty> responseObserver) {
        userMessagesStreamMap.remove(request.getUserId());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
        log.info("User [" + request.getUserId() + "] stopped a chat!");
    }

    @Override
    public void startAdminChat(AdminChatRequest request, StreamObserver<Message> responseObserver) {
        adminMessagesStreamMap.put(Pair.of(request.getAdminId(), request.getUserId()), responseObserver);
        log.info("admin [" + request.getAdminId() + "] started a chat with user[" + request.getUserId() + "]");
    }

    @Override
    public void stopAdminChat(AdminChatRequest request, StreamObserver<Empty> responseObserver) {
        adminMessagesStreamMap.remove(Pair.of(request.getAdminId(), request.getUserId()));
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
        log.info("admin [" + request.getAdminId() + "] stopped a chat with user[" + request.getUserId() + "]");
    }

    @Override
    public void sendAdminMessage(MessageRequest request, StreamObserver<Empty> responseObserver) {
        userMessagesStreamMap.keySet().stream()
                .filter(userId -> userId.equals(request.getUserId()))
                .map(userMessagesStreamMap::get)
                .forEach(messageStreamObserver -> messageStreamObserver.onNext(request.getMessage()));

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
        log.info("admin [" + request.getAdminId() + "] sended a msg to user[" + request.getUserId() + "]");
    }

    @Override
    public void sendUserMessage(MessageRequest request, StreamObserver<Empty> responseObserver) {

        adminMessagesStreamMap.keySet().stream()
                .filter(adminUser -> adminUser.getSecond().equals(request.getUserId()))
                .map(adminMessagesStreamMap::get)
                .forEach(messageStreamObserver -> messageStreamObserver.onNext(request.getMessage()));

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
        log.info("user[" + request.getUserId() + "] sends a msg to admins");
    }
}
