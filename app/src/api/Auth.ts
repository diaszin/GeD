import axios from "axios";

export class Auth {
  private static baseURL = import.meta.env.VITE_BACKEND_URL;

  static async signIn(email: string, password: string) {
    const url = `${this.baseURL}/user/login`;

    const response = await axios.post(url, { email, password });

    console.log(response.status);

    return response
  }

  static async signUp(
    name: string,
    email: string,
    password: string,
    birthdayDate: string
  ) {
    const url = `${this.baseURL}/user`;
    const response = await axios.post(url, {
      email,
      password,
      name,
      birthdayDate,
    });

    console.log(response.status);


    return response
  }
}
