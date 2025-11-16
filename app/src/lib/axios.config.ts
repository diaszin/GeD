import axios, { AxiosError } from "axios";

axios.interceptors.response.use(
  function (response) {
    return response;
  },
  (error: AxiosError) => {
    if (error.status == 403) {
      window.dispatchEvent(new Event("axios-403"));
    } 
    throw Promise.reject(error);
  }
);
