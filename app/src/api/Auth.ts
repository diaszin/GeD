import axios from "axios";

export class Auth {
  static baseURL = import.meta.env.VITE_BACKEND_URL;

  static async signIn(email: string, password: string) {
    const url = `${this.baseURL}/user/login`;

    const response = await axios.post(url, { email, password });

    console.log(response.status);
  }


}
