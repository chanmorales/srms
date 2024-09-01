import { RequestMethod } from "../types";
import { AppHelper } from "./AppHelper";

export const ApiHelper = {
  /**
   * Helper method to call an API asynchronously
   *
   * @param url - the url
   * @param action - the action
   * @param method - http request method
   * @param token - the json web token
   * @param payload - payload if there's any for POST/PUT request
   */
  async doRemoteCallAsync(
    url: string,
    action: string,
    method: RequestMethod = RequestMethod.GET,
    token?: string,
    payload?: any
  ) {
    try {
      const options: RequestInit = {
        method: method,
      };

      // Append payload if there's any
      if (typeof payload !== "undefined" && payload !== null) {
        options.headers = {
          "Content-Type": "application/json",
        };
        options.body = JSON.stringify(payload);
      }

      // Append token if there's any
      if (token) {
        options.headers = {
          ...options.headers,
          Authorization: `JWT ${token}`,
        };
      }

      // Perform API call
      const response = await fetch(url, options);
      switch (response.status) {
        case 204:
          // Success with no content
          return;
        case 400:
        case 401:
        case 403:
        case 404:
        case 500:
          // Handle Error
          console.error(`Request failed: ${action}`);
          throw response;
        default:
          // Success with data in response
          const responseData = await response.text();
          try {
            return Promise.resolve(JSON.parse(responseData));
          } catch (e) {
            return Promise.resolve(responseData);
          }
      }
    } catch (err: any) {
      const errorDetails = await err.text();
      throw AppHelper.tryParseJson(errorDetails);
    }
  },
};
