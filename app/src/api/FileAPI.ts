import { AuthToken } from "@/config/AuthToken";
import axios from "axios";

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
}
