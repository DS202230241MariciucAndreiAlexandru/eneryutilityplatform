import axios from "axios";

export default class AuthService {
    static login(loginForm) {
        return axios.post("/login", loginForm);
    }
}