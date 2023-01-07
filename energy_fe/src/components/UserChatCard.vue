<template>
  <v-container justify="center">
    <v-alert type="success" :value="msgAlert">
      Unul dintre admini ti-a dat mesaj!
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
import {Message, MessageRequest, UserChatRequest} from "@/proto/chat_pb";

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
    isTyping: false,
    sendedTyping: false
  }),
  methods: {
    sendMessage() {
      this.messageForm.createdAt = new Date().toDateString();

      let messageRequest = new MessageRequest();

      messageRequest.setUserid(this.user.id);
      let message = new Message();
      message.setMessage(this.messageForm.content);
      message.setCreatedat(this.messageForm.createdAt);
      if(message.getMessage() === '') return;
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
    sendTyping() {

      let messageRequest = new MessageRequest();

      messageRequest.setUserid(this.user.id);
      messageRequest.setMessage(null);

      this.chatServiceClient.sendUserMessage(messageRequest);
    },
    sendTypingInput() {
      if (this.messageForm.content.length === 1 &&  !this.sendedTyping) {
        this.sendedTyping = !this.sendedTyping;
        this.sendTyping();
      } else if(this.sendedTyping && this.messageForm.content.length === 0) {
        this.sendedTyping = !this.sendedTyping;
        this.sendTyping();
      }
    },
    sendTypingFocusOut() {
      // if (this.sendedTyping) {
      //   this.sendedTyping = !this.sendedTyping;
      //   this.sendTyping();
      // }
    }
  },
  mounted() {
    this.chatServiceClient = new ChatServiceClient("http://andreimariciuc4.germanywestcentral.azurecontainer.io:9091", null, null);
    const userRequest = new UserChatRequest();
    userRequest.setUserid(this.user.id);
    this.messagesStream = this.chatServiceClient.startUserChat(userRequest);

    this.messagesStream.on("data", message => {
      if (!message.getMessage()) {
        this.isTyping = !this.isTyping;
        return;
      }
      this.isTyping = false;

      this.messages.push({content: message.getMessage(), me: false, createdAt: message.getCreatedat()});
      if (!this.chatDialog) {
        this.msgAlert = true;
        setTimeout(() => {
          this.msgAlert = false;
        }, 5000);
      }
    });
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