<template>
  <v-container justify="center">
    <v-btn
        icon
        @click.stop="chatDialog = true">
      <v-icon x-large>mdi-wechat</v-icon>
    </v-btn>
    <v-dialog v-model="chatDialog" max-width="800px">
      <v-alert type="success" :value="msgAlert">
        {{ msgAlertContent }}
      </v-alert>
      <v-card
          flat
          class="d-flex flex-column fill-height"
      >
        <v-card-title>
          {{ user.username }}
        </v-card-title>
        <v-card-text class="flex-grow-1 overflow-y-auto">
          <template v-for="(msg, i) in messages">
            <div
                :key="i"
                :class="{ 'd-flex flex-row-reverse': msg.me }"

            >
              <v-menu offset-y>
                <template v-slot:activator="{ on }">
                  <v-chip
                      :color="msg.me ? 'primary' : ''"
                      dark
                      style="height:auto;white-space: normal;"
                      class="pa-4 mb-2"
                      v-on="on"
                  >
                    {{ msg.content }}
                    <sub
                        class="ml-2"
                        style="font-size: 0.5rem;"
                    >{{ msg.createdAt }}</sub>
                  </v-chip>
                </template>
              </v-menu>
            </div>
          </template>
          <v-progress-circular
              indeterminate
              color="green"
              v-if="isTyping"
          ></v-progress-circular>
        </v-card-text>
        <v-card-text class="flex-shrink-1">
          <v-text-field
              v-model="messageForm.content"
              label="type something"
              type="text"
              no-details
              outlined
              @keyup.enter="sendMessage"
              hide-details
              @focusout="sendTypingFocusOut"
              @input="sendTypingInput"
          />
        </v-card-text>
      </v-card>
    </v-dialog>
    <v-avatar
        color="teal"
        size="16"
    >
      <div class="white--text">{{ msgAlertCount }}</div>
    </v-avatar>
  </v-container>
</template>

<script>
import {AdminChatRequest, Message, MessageRequest, MessageType, UserChatRequest} from "@/proto/chat_pb";
import {ChatServiceClient} from "@/proto/chat_grpc_web_pb";

export default {
  name: "AdminChatCard",
  props: {
    admin: null,
    user: null
  },
  data: () => ({
    chatDialog: false,
    msgAlert: false,
    msgAlertCount: 0,
    messages: [],
    messageForm: {
      content: "",
      me: true,
      createdAt: new Date().toDateString()
    },
    chatServiceClient: null,
    messagesStream: null,
    isTyping: false,
    sendedTyping: false,
    msgAlertContent: ""
  }),
  methods: {
    sendMessage() {
      this.messageForm.createdAt = new Date().toDateString();

      let messageRequest = new MessageRequest();

      messageRequest.setUserid(this.user.id);
      messageRequest.setAdminid(this.admin.id);
      let message = new Message();
      message.setMessagetype(MessageType.CONTENT);
      message.setMessage(this.messageForm.content);
      message.setCreatedat(this.messageForm.createdAt);

      messageRequest.setMessage(message);
      this.chatServiceClient.sendAdminMessage(messageRequest);

      this.messages.push({
        content: this.messageForm.content,
        me: this.messageForm.me,
        createdAt: this.messageForm.createdAt
      });

      this.messageForm.content = "";
      this.sendedTyping = false;
    },
    sendSeen() {
      let messageRequest = new MessageRequest();

      messageRequest.setUserid(this.user.id);
      messageRequest.setAdminid(this.admin.id);
      let message = new Message();
      message.setMessagetype(MessageType.SEEN);
      messageRequest.setMessage(message);

      this.chatServiceClient.sendAdminMessage(messageRequest);
    },
    sendTyping() {
      let messageRequest = new MessageRequest();

      messageRequest.setUserid(this.user.id);
      messageRequest.setAdminid(this.admin.id);
      let message = new Message();
      message.setMessagetype(MessageType.TYPING);
      messageRequest.setMessage(message);

      this.chatServiceClient.sendAdminMessage(messageRequest);
    },
    sendTypingInput() {
      if (this.messageForm.content.length === 1 && !this.sendedTyping) {
        this.sendedTyping = !this.sendedTyping;
        this.sendTyping();
      } else if (this.sendedTyping && this.messageForm.content.length === 0) {
        this.sendedTyping = !this.sendedTyping;
        this.sendTyping();
      }
    },
    sendTypingFocusOut() {
    }
  },
  mounted() {
    // this.chatServiceClient = new ChatServiceClient("http://andreimariciuc4.germanywestcentral.azurecontainer.io:9091", null, null);
    this.chatServiceClient = new ChatServiceClient("http://localhost:9091", null, null);
    const adminRequest = new AdminChatRequest();
    adminRequest.setUserid(this.user.id);
    adminRequest.setAdminid(this.admin.id);
    this.messagesStream = this.chatServiceClient.startAdminChat(adminRequest);

    this.messagesStream.on("data", message => {
      if (message.getMessagetype() === MessageType.SEEN) {
        this.msgAlertContent = `${this.user.username} a citit ultimele mesaje!`;
        this.msgAlert = true;
        setTimeout(() => {
          this.msgAlert = false;
        }, 5000);
        return;
      }

      if (message.getMessagetype() === MessageType.TYPING) {
        this.isTyping = !this.isTyping;
        return;
      }

      this.isTyping = false;

      this.messages.push({content: message.getMessage(), me: false, createdAt: message.getCreatedat()});
      if (!this.chatDialog) {
        this.msgAlertCount++;
      }
    });
  },
  watch: {
    chatDialog(n, _) {
      if (n === true) {

        if (this.msgAlertCount > 0) {
          this.sendSeen();
        }

        this.msgAlertCount = 0;
      }
    }
  },
  beforeUnmounted() {
    const userRequest = new UserChatRequest();
    userRequest.setUserid(this.admin.id);
    this.chatServiceClient.stopUserChat(userRequest);
  }
}
</script>

<style scoped>

</style>