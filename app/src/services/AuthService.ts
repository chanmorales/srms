import { BaseService } from "./BaseService";
import { RequestMethod } from "../types";
import { Jwt } from "../types/Login";

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
  async login(username: string, password: string): Promise<Jwt> {
    return this.doRemoteCallAsync(
      `${this.baseUrl}/authentication/login`,
      "login",
      RequestMethod.POST,
      {
        username,
        password,
      }
    );
  }
}

const authService = new AuthService();
export default authService;
