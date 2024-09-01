import { useMemo } from "react";
import { useInject } from "./useInject";
import AuthService from "../services/AuthService";
import { AuthContext } from "../providers";
import appConfig from "../config/app-config.json";
import UserService from "../services/UserService";

/**
 * Use Service Hook
 */
export const useService = () => {
  const baseUrl = appConfig.SERVER_URL;
  const { token } = useInject(AuthContext);

  const authService = useMemo(() => {
    AuthService.config = {
      baseUrl,
      token,
    };
    return AuthService;
  }, [baseUrl, token]);

  const userService = useMemo(() => {
    UserService.config = {
      baseUrl,
      token,
    };
    return UserService;
  }, [baseUrl, token]);

  return {
    authService,
    userService,
  };
};
