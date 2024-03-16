import axios from "axios";

export default function Request() {
  const defaultRequest = async (path, body) => {
    const url = process.env.REACT_APP_API_URL + path;
    console.warn('url', process.env.REACT_APP_API_URL);
    try {
      const response = await body(url);
      return response;
    } catch (err) {
      console.log(err);
      throw err;
    }
  };

  const get = async (path, params) => {
    return await defaultRequest(path, async (url) => {
      const response = await axios.get(url, {
        params: params,
      });
      return response;
    });
  };

  return { get };
}