import { AuthToken } from "@/config/AuthToken";
import type { Folder } from "@/types/Folder";
import axios from "axios";

export class FolderAPI {
  private static readonly baseURl = import.meta.env.VITE_BACKEND_URL;
  private static readonly url = `${this.baseURl}/folder`;

  static async create(title: string, projectID: string) {
    const token = "Bearer " + AuthToken.get();

    const response = await axios.post(
      this.url,
      { title: title },
      {
        headers: {
          Authorization: token,
        },
        params: {
          project: projectID,
        },
      }
    );

    return response;
  }

  static async delete(id: string) {
    const token = "Bearer " + AuthToken.get();

    const response = await axios.delete(this.url, {
      params: { id },
      headers: {
        Authorization: token,
      },
    });

    return response;
  }

  static async update(id: string, data: Pick<Folder, "title">) {
    const token = "Bearer " + AuthToken.get();

    const response = await axios.patch(this.url, data, {
      params: { id },
      headers: {
        Authorization: token,
      },
    });

    return response;
  }
}
