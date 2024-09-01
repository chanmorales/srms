import { BaseService } from "./BaseService";
import { Jwt, RequestMethod } from "../types";

/**
 * Authentication Service
 */
export class AuthService extends BaseService {
  /**
   * The base path for the resources of this service
   */
  readonly BASE_PATH: string = "/authentication";

  /**
   * Logs in a user
   *
   * @param username - the username
   * @param password - the password
   */
  async login(username: string, password: string): Promise<Jwt> {
    return this.doRemoteCallAsync(
      `${this.baseUrl}${this.BASE_PATH}/login`,
      "login",
      RequestMethod.POST,
      {
        username,
        password,
      }
    );
  }

  /**
   * Logs out a user
   */
  async logout(): Promise<void> {
    return this.doRemoteCallAsync(
      `${this.baseUrl}${this.BASE_PATH}/logout`,
      "logout",
      RequestMethod.POST
    );
  }
}

const authService = new AuthService();
export default authService;
