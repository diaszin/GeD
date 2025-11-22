import axios, { AxiosError } from "axios";

axios.interceptors.response.use(
  function (response) {
    return response;
  },
  (error: AxiosError) => {
    if (error.status && (error.status == 403 || error.status >= 500)) {
      window.dispatchEvent(new Event("axios-403"));
    }
    throw error;
  }
);
