import { useInject } from "../hooks";
import { AuthContext } from "../providers";
import { Navigate, Outlet } from "react-router-dom";

export const ProtectedRoute = () => {
  const { token } = useInject(AuthContext);

  // If not authenticated, navigate to login page
  return token ? <Outlet /> : <Navigate to={"/login"} />;
};
