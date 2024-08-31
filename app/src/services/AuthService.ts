import { BaseService } from "./BaseService";
import { RequestMethod } from "../types";

/**
 * Authentication Service
 */
export class AuthService extends BaseService {
  /**
   * Log in a user
   *
   * @param username - the username
   * @param password - the password
   */
  async login(username: string, password: string) {
    return this.doRemoteCallAsync(
      `${this.baseUrl}/login`,
      "login",
      RequestMethod.GET,
      {
        username,
        password,
      }
    );
  }
}

const authService = new AuthService();
export default authService;
