import { useInject } from "../hooks";
import { AuthContext } from "../providers";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { Home, Login, Register } from "../pages";
import { ProtectedRoute } from "./ProtectedRoute";

export const Routes = () => {
  const { token } = useInject(AuthContext);

  const unauthenticatedRoutes = [
    {
      path: "/login",
      element: <Login />,
    },
    {
      path: "/register",
      element: <Register />,
    },
  ];

  const authenticatedRoutes = [
    {
      path: "/",
      element: <ProtectedRoute />,
      children: [
        {
          path: "/",
          element: <Home />,
        },
      ],
    },
  ];

  const router = createBrowserRouter([
    ...(!token ? unauthenticatedRoutes : []),
    ...authenticatedRoutes,
  ]);

  return <RouterProvider router={router} />;
};
