import { RequestMethod } from "../types";
import { ApiHelper } from "../helpers";

export interface ServiceOption {
  token: string | null;
  baseUrl: string;
}

export class BaseService {
  private options?: ServiceOption;

  public set config(options: ServiceOption) {
    this.options = options;
  }

  get baseUrl() {
    return this.options?.baseUrl;
  }

  get token() {
    return this.options?.token;
  }

  async doRemoteCallAsync<T>(
    url: string,
    action: string,
    method: RequestMethod = RequestMethod.GET,
    body?: T
  ) {
    return ApiHelper.doRemoteCallAsync(
      url,
      action,
      method,
      this.token ?? undefined,
      body
    );
  }
}
