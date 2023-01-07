/**
 * @fileoverview gRPC-Web generated client stub for com.sd.energy.grpc.services
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!


/* eslint-disable */
// @ts-nocheck



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.com = {};
proto.com.sd = {};
proto.com.sd.energy = {};
proto.com.sd.energy.grpc = {};
proto.com.sd.energy.grpc.services = require('./chat_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.com.sd.energy.grpc.services.ChatServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.com.sd.energy.grpc.services.ChatServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.sd.energy.grpc.services.UserChatRequest,
 *   !proto.com.sd.energy.grpc.services.Message>}
 */
const methodDescriptor_ChatService_startUserChat = new grpc.web.MethodDescriptor(
  '/com.sd.energy.grpc.services.ChatService/startUserChat',
  grpc.web.MethodType.SERVER_STREAMING,
  proto.com.sd.energy.grpc.services.UserChatRequest,
  proto.com.sd.energy.grpc.services.Message,
  /**
   * @param {!proto.com.sd.energy.grpc.services.UserChatRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.sd.energy.grpc.services.Message.deserializeBinary
);


/**
 * @param {!proto.com.sd.energy.grpc.services.UserChatRequest} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.com.sd.energy.grpc.services.Message>}
 *     The XHR Node Readable Stream
 */
proto.com.sd.energy.grpc.services.ChatServiceClient.prototype.startUserChat =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/startUserChat',
      request,
      metadata || {},
      methodDescriptor_ChatService_startUserChat);
};


/**
 * @param {!proto.com.sd.energy.grpc.services.UserChatRequest} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.com.sd.energy.grpc.services.Message>}
 *     The XHR Node Readable Stream
 */
proto.com.sd.energy.grpc.services.ChatServicePromiseClient.prototype.startUserChat =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/startUserChat',
      request,
      metadata || {},
      methodDescriptor_ChatService_startUserChat);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.sd.energy.grpc.services.UserChatRequest,
 *   !proto.com.sd.energy.grpc.services.Empty>}
 */
const methodDescriptor_ChatService_stopUserChat = new grpc.web.MethodDescriptor(
  '/com.sd.energy.grpc.services.ChatService/stopUserChat',
  grpc.web.MethodType.UNARY,
  proto.com.sd.energy.grpc.services.UserChatRequest,
  proto.com.sd.energy.grpc.services.Empty,
  /**
   * @param {!proto.com.sd.energy.grpc.services.UserChatRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.sd.energy.grpc.services.Empty.deserializeBinary
);


/**
 * @param {!proto.com.sd.energy.grpc.services.UserChatRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.sd.energy.grpc.services.Empty)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.sd.energy.grpc.services.Empty>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.sd.energy.grpc.services.ChatServiceClient.prototype.stopUserChat =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/stopUserChat',
      request,
      metadata || {},
      methodDescriptor_ChatService_stopUserChat,
      callback);
};


/**
 * @param {!proto.com.sd.energy.grpc.services.UserChatRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.sd.energy.grpc.services.Empty>}
 *     Promise that resolves to the response
 */
proto.com.sd.energy.grpc.services.ChatServicePromiseClient.prototype.stopUserChat =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/stopUserChat',
      request,
      metadata || {},
      methodDescriptor_ChatService_stopUserChat);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.sd.energy.grpc.services.AdminChatRequest,
 *   !proto.com.sd.energy.grpc.services.Message>}
 */
const methodDescriptor_ChatService_startAdminChat = new grpc.web.MethodDescriptor(
  '/com.sd.energy.grpc.services.ChatService/startAdminChat',
  grpc.web.MethodType.SERVER_STREAMING,
  proto.com.sd.energy.grpc.services.AdminChatRequest,
  proto.com.sd.energy.grpc.services.Message,
  /**
   * @param {!proto.com.sd.energy.grpc.services.AdminChatRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.sd.energy.grpc.services.Message.deserializeBinary
);


/**
 * @param {!proto.com.sd.energy.grpc.services.AdminChatRequest} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.com.sd.energy.grpc.services.Message>}
 *     The XHR Node Readable Stream
 */
proto.com.sd.energy.grpc.services.ChatServiceClient.prototype.startAdminChat =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/startAdminChat',
      request,
      metadata || {},
      methodDescriptor_ChatService_startAdminChat);
};


/**
 * @param {!proto.com.sd.energy.grpc.services.AdminChatRequest} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.com.sd.energy.grpc.services.Message>}
 *     The XHR Node Readable Stream
 */
proto.com.sd.energy.grpc.services.ChatServicePromiseClient.prototype.startAdminChat =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/startAdminChat',
      request,
      metadata || {},
      methodDescriptor_ChatService_startAdminChat);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.sd.energy.grpc.services.AdminChatRequest,
 *   !proto.com.sd.energy.grpc.services.Empty>}
 */
const methodDescriptor_ChatService_stopAdminChat = new grpc.web.MethodDescriptor(
  '/com.sd.energy.grpc.services.ChatService/stopAdminChat',
  grpc.web.MethodType.UNARY,
  proto.com.sd.energy.grpc.services.AdminChatRequest,
  proto.com.sd.energy.grpc.services.Empty,
  /**
   * @param {!proto.com.sd.energy.grpc.services.AdminChatRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.sd.energy.grpc.services.Empty.deserializeBinary
);


/**
 * @param {!proto.com.sd.energy.grpc.services.AdminChatRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.sd.energy.grpc.services.Empty)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.sd.energy.grpc.services.Empty>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.sd.energy.grpc.services.ChatServiceClient.prototype.stopAdminChat =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/stopAdminChat',
      request,
      metadata || {},
      methodDescriptor_ChatService_stopAdminChat,
      callback);
};


/**
 * @param {!proto.com.sd.energy.grpc.services.AdminChatRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.sd.energy.grpc.services.Empty>}
 *     Promise that resolves to the response
 */
proto.com.sd.energy.grpc.services.ChatServicePromiseClient.prototype.stopAdminChat =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/stopAdminChat',
      request,
      metadata || {},
      methodDescriptor_ChatService_stopAdminChat);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.sd.energy.grpc.services.MessageRequest,
 *   !proto.com.sd.energy.grpc.services.Empty>}
 */
const methodDescriptor_ChatService_sendAdminMessage = new grpc.web.MethodDescriptor(
  '/com.sd.energy.grpc.services.ChatService/sendAdminMessage',
  grpc.web.MethodType.UNARY,
  proto.com.sd.energy.grpc.services.MessageRequest,
  proto.com.sd.energy.grpc.services.Empty,
  /**
   * @param {!proto.com.sd.energy.grpc.services.MessageRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.sd.energy.grpc.services.Empty.deserializeBinary
);


/**
 * @param {!proto.com.sd.energy.grpc.services.MessageRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.sd.energy.grpc.services.Empty)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.sd.energy.grpc.services.Empty>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.sd.energy.grpc.services.ChatServiceClient.prototype.sendAdminMessage =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/sendAdminMessage',
      request,
      metadata || {},
      methodDescriptor_ChatService_sendAdminMessage,
      callback);
};


/**
 * @param {!proto.com.sd.energy.grpc.services.MessageRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.sd.energy.grpc.services.Empty>}
 *     Promise that resolves to the response
 */
proto.com.sd.energy.grpc.services.ChatServicePromiseClient.prototype.sendAdminMessage =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/sendAdminMessage',
      request,
      metadata || {},
      methodDescriptor_ChatService_sendAdminMessage);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.sd.energy.grpc.services.MessageRequest,
 *   !proto.com.sd.energy.grpc.services.Empty>}
 */
const methodDescriptor_ChatService_sendUserMessage = new grpc.web.MethodDescriptor(
  '/com.sd.energy.grpc.services.ChatService/sendUserMessage',
  grpc.web.MethodType.UNARY,
  proto.com.sd.energy.grpc.services.MessageRequest,
  proto.com.sd.energy.grpc.services.Empty,
  /**
   * @param {!proto.com.sd.energy.grpc.services.MessageRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.sd.energy.grpc.services.Empty.deserializeBinary
);


/**
 * @param {!proto.com.sd.energy.grpc.services.MessageRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.sd.energy.grpc.services.Empty)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.sd.energy.grpc.services.Empty>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.sd.energy.grpc.services.ChatServiceClient.prototype.sendUserMessage =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/sendUserMessage',
      request,
      metadata || {},
      methodDescriptor_ChatService_sendUserMessage,
      callback);
};


/**
 * @param {!proto.com.sd.energy.grpc.services.MessageRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.sd.energy.grpc.services.Empty>}
 *     Promise that resolves to the response
 */
proto.com.sd.energy.grpc.services.ChatServicePromiseClient.prototype.sendUserMessage =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.sd.energy.grpc.services.ChatService/sendUserMessage',
      request,
      metadata || {},
      methodDescriptor_ChatService_sendUserMessage);
};


module.exports = proto.com.sd.energy.grpc.services;

