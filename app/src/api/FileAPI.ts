import { AuthToken } from "@/config/AuthToken";
import axios from "axios";
import type { File as TFile  } from "@/types/File";

export class FileAPI {
  private static readonly baseURl = import.meta.env.VITE_BACKEND_URL;
  public static readonly url = `${this.baseURl}/file`;

  static async upload(title: string, file: File, folderId: string) {
    const token = "Bearer " + AuthToken.get();
    const data = new FormData();

    data.append("file", file);
    data.append("title", title);
    data.append("folder", folderId);

    const response = await axios.post(this.url, data, {
      headers: {
        Authorization: token,
      },
    });

    return response;
  }

  static async delete(id: string) {
    const token = "Bearer " + AuthToken.get();

    const response = await axios.delete(this.url, {
      params: {
        id,
      },
      headers: {
        Authorization: token,
      },
    });

    return response;
  }

  static async update(id: string, data: Pick<TFile, "title">) {
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
