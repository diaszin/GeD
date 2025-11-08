import { AuthToken } from "@/config/AuthToken";
import type { Project } from "@/types/Project";
import axios from "axios";

export class ProjectAPI {
  private static baseURL: string = import.meta.env.VITE_BACKEND_URL;
  private static readonly url = `${this.baseURL}/project`;
  private static readonly token = "Bearer " + AuthToken.get();

  public static async create(title: string) {
    const response = await axios.post(
      this.url,
      { title },
      {
        headers: {
          Authorization: this.token,
        },
      }
    );

    return response;
  }

  public static async get() {
    const response = await axios.get<Project[]>(this.url, {
      headers: {
        Authorization: this.token,
      },
    });

    return response;
  }
}
