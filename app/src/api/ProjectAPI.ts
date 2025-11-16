import { AuthToken } from "@/config/AuthToken";
import type { Folder } from "@/types/Folder";
import type { Project } from "@/types/Project";
import axios from "axios";

export class ProjectAPI {
  private static baseURL: string = import.meta.env.VITE_BACKEND_URL;
  private static readonly url = `${this.baseURL}/project`;

  public static async create(title: string) {
    const token = "Bearer " + AuthToken.get();

    const response = await axios.post(
      this.url,
      { title },
      {
        headers: {
          Authorization: token,
        },
      }
    );

    return response;
  }

  public static async get() {
    const token = "Bearer " + AuthToken.get();
    const response = await axios.get<Project[]>(this.url, {
      headers: {
        Authorization: token,
      },
    });

    return response;
  }

  public static async deleteById(id: string) {
    const token = "Bearer " + AuthToken.get();

    const response = await axios.delete(this.url, {
      params: { id },
      headers: {
        Authorization: token,
      },
    });

    return response;
  }

  public static async updateTitle(id: string, title: string) {
    const token = "Bearer " + AuthToken.get();

    const response = await axios.put(
      this.url,
      { title },
      {
        params: { id },
        headers: {
          Authorization: token,
        },
      }
    );

    return response;
  }

  public static async allFolders(id: string) {
    const token = "Bearer " + AuthToken.get();
    const url = `${this.url}/folder`;
    const response = await axios.get<Folder[]>(
      url,

      {
        params: { id },
        headers: { Authorization: token },
      }
    );

    return response;
  }
}
