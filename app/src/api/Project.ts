import { AuthToken } from "@/config/AuthToken";
import axios from "axios";

export class Project {
  private static baseURL: string = import.meta.env.VITE_BACKEND_URL;
  private static readonly token = AuthToken.get();

  public static async create(title: string) {
    const url = `${this.baseURL}/project`;
    

    const response = await axios.post(
      url,
      { title },
      {
        headers: {
          Authorization: "Bearer " + this.token,
        },
      }
    );

    return response;
  }
}
