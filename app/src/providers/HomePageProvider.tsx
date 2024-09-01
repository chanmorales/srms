import { User } from "../types";
import { createContext } from "react";

export interface HomePageContextType {
  user?: User;
}

export const HomePageContext = createContext<HomePageContextType>({});
