import { AuthToken } from "@/config/AuthToken";
import type { Folder } from "@/types/Folder";
import axios from "axios";
import { FileAPI } from "./FileAPI";
import type { File } from "@/types/File";

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

  static async getFiles(id: string) {
    const token = "Bearer " + AuthToken.get();

    const url = FileAPI.url;
    const response = await axios.get<File[]>(url, {
      params: {
        folder: id,
      },
      headers: {
        Authorization: token,
      },
    });

    return response;
  }

  static async getFileKpis(id: string) {
    const token = "Bearer " + AuthToken.get();
    const response = await axios.get(this.url + "/kpis", {
      params: { id },
      headers: {
        Authorization: token,
      },
    });

    return response;
  }

  static async getFileKpisByPeriod(id: string) {
    const token = "Bearer " + AuthToken.get();
    const response = await axios.get(this.url + "/kpis/period", {
      params: { id },
      headers: {
        Authorization: token,
      },
    });

    return response;
  }
}
