import {
  createContext,
  FC,
  ReactNode,
  useCallback,
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
    const savedToken = localStorage.getItem("jwt");
    if (savedToken) {
      setToken(savedToken);
    }
  }, []);

  const saveToken = useCallback((newToken: string | null) => {
    setToken(newToken);
    newToken
      ? localStorage.setItem("jwt", newToken)
      : localStorage.removeItem("jwt");
  }, []);

  const authContext = useMemo(
    () => ({
      baseUrl: "",
      token: token,
      setToken: saveToken,
    }),
    [token, saveToken]
  );

  return (
    <AuthContext.Provider value={authContext}>{children}</AuthContext.Provider>
  );
};
