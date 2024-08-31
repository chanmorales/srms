import {
  createContext,
  FC,
  ReactNode,
  useEffect,
  useMemo,
  useState,
} from "react";

export interface AuthContextType {
  token: string | null;
  setToken: (token: string | null) => void;
}

export const AuthContext = createContext<AuthContextType | null>(null);

export const AuthProvider: FC<{ children: ReactNode }> = ({ children }) => {
  const [token, setToken] = useState<string | null>(null);

  useEffect(() => {
    const savedToken = localStorage.getItem("token");
    if (savedToken) {
      setToken(savedToken);
    }
  }, []);

  const authContext = useMemo(
    () => ({
      baseUrl: "",
      token: token,
      setToken: setToken,
    }),
    [token]
  );

  return (
    <AuthContext.Provider value={authContext}>{children}</AuthContext.Provider>
  );
};
