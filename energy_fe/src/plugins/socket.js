import * as SockJS from "sockjs-client";
import axios from "@/plugins/myAxios";
import * as Stomp from "stompjs";

const soc = new SockJS(axios.defaults.baseURL + "/vue-socket-endpoint");
const stompClient = Stomp.over(soc);

export default stompClient;