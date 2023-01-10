<template>
  <v-container justify="center">
    <v-alert type="success" :value="msgAlert">
      {{msgAlertContent}}
    </v-alert>
    <v-btn
        icon
        @click.stop="chatDialog = true"
        style="position: absolute; right: 0; bottom: 0; margin: 1%"
        class="btn__user_chat"
        x-large>

      <v-icon x-large>
        mdi-wechat
      </v-icon>
    </v-btn>
    <v-dialog v-model="chatDialog" max-width="600px">
      <v-alert type="success" :value="msgAlertIn">
        {{msgAlertContent}}
      </v-alert>
      <v-card
          flat
          class="d-flex flex-column fill-height"
      >
        <v-card-title>
          Consulta un admin
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
  </v-container>
</template>

<script>
import {ChatServiceClient} from "@/proto/chat_grpc_web_pb";
import {Message, MessageRequest, MessageType, UserChatRequest} from "@/proto/chat_pb";

export default {
  name: "ChatCard",
  props: {
    user: null
  },
  data: () => ({
    chatDialog: false,
    messages: [],
    messageForm: {
      content: "",
      me: true,
      createdAt: new Date().toDateString()
    },
    chatServiceClient: null,
    messagesStream: null,
    msgAlert: false,
    msgAlertIn: false,
    msgAlertCount: 0,
    isTyping: false,
    sendedTyping: false,
    msgAlertContent: ""
  }),
  methods: {
    sendMessage() {
      this.messageForm.createdAt = new Date().toDateString();

      let messageRequest = new MessageRequest();

      messageRequest.setUserid(this.user.id);
      let message = new Message();
      message.setMessagetype(MessageType.CONTENT);
      message.setMessage(this.messageForm.content);
      message.setCreatedat(this.messageForm.createdAt);
      if (message.getMessage() === '') return;
      messageRequest.setMessage(message);
      this.chatServiceClient.sendUserMessage(messageRequest);

      this.messages.push({
        content: this.messageForm.content,
        me: this.messageForm.me,
        createdAt: this.messageForm.createdAt
      });

      this.sendedTyping = false;
      this.messageForm.content = "";
    },
    sendSeen() {
      let messageRequest = new MessageRequest();

      messageRequest.setUserid(this.user.id);
      let message = new Message();
      message.setMessagetype(MessageType.SEEN);
      messageRequest.setMessage(message);

      this.chatServiceClient.sendUserMessage(messageRequest);
    },
    sendTyping() {
      let messageRequest = new MessageRequest();

      messageRequest.setUserid(this.user.id);
      let message = new Message();
      message.setMessagetype(MessageType.TYPING);
      messageRequest.setMessage(message);

      this.chatServiceClient.sendUserMessage(messageRequest);
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
    this.chatServiceClient = new ChatServiceClient("http://andreimariciuc4.germanywestcentral.azurecontainer.io:9091", null, null);
    // this.chatServiceClient = new ChatServiceClient("http://localhost:9091", null, null);
    const userRequest = new UserChatRequest();
    userRequest.setUserid(this.user.id);
    this.messagesStream = this.chatServiceClient.startUserChat(userRequest);

    this.messagesStream.on("data", message => {
      if (message.getMessagetype() === MessageType.SEEN) {
        this.msgAlertContent = `Unul dintre adminti ti-a vazut mesajele!`;
        this.msgAlertIn = true;
        setTimeout(() => {
          this.msgAlertIn = false;
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
        this.msgAlert = true;
        this.msgAlertContent = "Unul dintre admini ti-a dat mesaj!";
        setTimeout(() => {
          this.msgAlert = false;
        }, 5000);

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
    userRequest.setUserid(this.user.id);
    this.chatServiceClient.stopUserChat(userRequest);
  }
}
</script>

<style scoped>

</style>