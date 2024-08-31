import { useMemo } from "react";
import { useInject } from "./useInject";
import AuthService from "../services/AuthService";
import { AuthContext } from "../providers";
import appConfig from "../config/app-config.json";

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

  return {
    authService,
  };
};
