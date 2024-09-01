import { Layout } from "antd";
import { Content, Footer } from "antd/es/layout/layout";
import { Header } from "../components";
import { HomePageContext, HomePageContextType } from "../providers";
import { useEffect, useMemo, useState } from "react";
import { useService } from "../hooks";
import { User } from "../types";

export const Home = () => {
  const [currentUser, setCurrentUser] = useState<User>();
  const { userService } = useService();

  useEffect(() => {
    (async () => {
      try {
        const user = await userService.getCurrentUser();
        setCurrentUser(user);
      } catch (err) {}
    })();
  }, [userService]);

  const homePageContext: HomePageContextType = useMemo(
    () => ({
      user: currentUser,
    }),
    [currentUser]
  );

  return (
    <HomePageContext.Provider value={homePageContext}>
      <Layout>
        <Header />
        <Content>{"Content"}</Content>
        <Footer>{"Footer"}</Footer>
      </Layout>
    </HomePageContext.Provider>
  );
};
