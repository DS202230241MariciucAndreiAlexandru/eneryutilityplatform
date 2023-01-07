import * as grpcWeb from 'grpc-web';

import * as chat_pb from './chat_pb';


export class ChatServiceClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: any; });

  startUserChat(
    request: chat_pb.UserChatRequest,
    metadata?: grpcWeb.Metadata
  ): grpcWeb.ClientReadableStream<chat_pb.Message>;

  stopUserChat(
    request: chat_pb.UserChatRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.RpcError,
               response: chat_pb.Empty) => void
  ): grpcWeb.ClientReadableStream<chat_pb.Empty>;

  startAdminChat(
    request: chat_pb.AdminChatRequest,
    metadata?: grpcWeb.Metadata
  ): grpcWeb.ClientReadableStream<chat_pb.Message>;

  stopAdminChat(
    request: chat_pb.AdminChatRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.RpcError,
               response: chat_pb.Empty) => void
  ): grpcWeb.ClientReadableStream<chat_pb.Empty>;

  sendAdminMessage(
    request: chat_pb.MessageRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.RpcError,
               response: chat_pb.Empty) => void
  ): grpcWeb.ClientReadableStream<chat_pb.Empty>;

  sendUserMessage(
    request: chat_pb.MessageRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.RpcError,
               response: chat_pb.Empty) => void
  ): grpcWeb.ClientReadableStream<chat_pb.Empty>;

}

export class ChatServicePromiseClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: any; });

  startUserChat(
    request: chat_pb.UserChatRequest,
    metadata?: grpcWeb.Metadata
  ): grpcWeb.ClientReadableStream<chat_pb.Message>;

  stopUserChat(
    request: chat_pb.UserChatRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<chat_pb.Empty>;

  startAdminChat(
    request: chat_pb.AdminChatRequest,
    metadata?: grpcWeb.Metadata
  ): grpcWeb.ClientReadableStream<chat_pb.Message>;

  stopAdminChat(
    request: chat_pb.AdminChatRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<chat_pb.Empty>;

  sendAdminMessage(
    request: chat_pb.MessageRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<chat_pb.Empty>;

  sendUserMessage(
    request: chat_pb.MessageRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<chat_pb.Empty>;

}

