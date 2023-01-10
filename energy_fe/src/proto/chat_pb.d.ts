import * as jspb from 'google-protobuf'



export class Empty extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Empty.AsObject;
  static toObject(includeInstance: boolean, msg: Empty): Empty.AsObject;
  static serializeBinaryToWriter(message: Empty, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Empty;
  static deserializeBinaryFromReader(message: Empty, reader: jspb.BinaryReader): Empty;
}

export namespace Empty {
  export type AsObject = {
  }
}

export class UserChatRequest extends jspb.Message {
  getUserid(): number;
  setUserid(value: number): UserChatRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): UserChatRequest.AsObject;
  static toObject(includeInstance: boolean, msg: UserChatRequest): UserChatRequest.AsObject;
  static serializeBinaryToWriter(message: UserChatRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): UserChatRequest;
  static deserializeBinaryFromReader(message: UserChatRequest, reader: jspb.BinaryReader): UserChatRequest;
}

export namespace UserChatRequest {
  export type AsObject = {
    userid: number,
  }
}

export class AdminChatRequest extends jspb.Message {
  getAdminid(): number;
  setAdminid(value: number): AdminChatRequest;

  getUserid(): number;
  setUserid(value: number): AdminChatRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): AdminChatRequest.AsObject;
  static toObject(includeInstance: boolean, msg: AdminChatRequest): AdminChatRequest.AsObject;
  static serializeBinaryToWriter(message: AdminChatRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): AdminChatRequest;
  static deserializeBinaryFromReader(message: AdminChatRequest, reader: jspb.BinaryReader): AdminChatRequest;
}

export namespace AdminChatRequest {
  export type AsObject = {
    adminid: number,
    userid: number,
  }
}

export class Message extends jspb.Message {
  getMessagetype(): MessageType;
  setMessagetype(value: MessageType): Message;

  getMessage(): string;
  setMessage(value: string): Message;

  getCreatedat(): string;
  setCreatedat(value: string): Message;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Message.AsObject;
  static toObject(includeInstance: boolean, msg: Message): Message.AsObject;
  static serializeBinaryToWriter(message: Message, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Message;
  static deserializeBinaryFromReader(message: Message, reader: jspb.BinaryReader): Message;
}

export namespace Message {
  export type AsObject = {
    messagetype: MessageType,
    message: string,
    createdat: string,
  }
}

export class MessageRequest extends jspb.Message {
  getUserid(): number;
  setUserid(value: number): MessageRequest;

  getAdminid(): number;
  setAdminid(value: number): MessageRequest;

  getMessage(): Message | undefined;
  setMessage(value?: Message): MessageRequest;
  hasMessage(): boolean;
  clearMessage(): MessageRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): MessageRequest.AsObject;
  static toObject(includeInstance: boolean, msg: MessageRequest): MessageRequest.AsObject;
  static serializeBinaryToWriter(message: MessageRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): MessageRequest;
  static deserializeBinaryFromReader(message: MessageRequest, reader: jspb.BinaryReader): MessageRequest;
}

export namespace MessageRequest {
  export type AsObject = {
    userid: number,
    adminid: number,
    message?: Message.AsObject,
  }
}

export enum MessageType { 
  CONTENT = 0,
  TYPING = 1,
  SEEN = 2,
}
