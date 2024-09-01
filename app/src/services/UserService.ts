import { BaseService } from "./BaseService";
import { User } from "../types";

export class UserService extends BaseService {
  /**
   * The base path for the resources of this service
   */
  readonly BASE_PATH: string = "/users";

  /**
   * Retrieves current user details
   */
  async getCurrentUser(): Promise<User> {
    return this.doRemoteCallAsync(
      `${this.baseUrl}${this.BASE_PATH}/current`,
      "get current user"
    );
  }
}

const userService = new UserService();
export default userService;
