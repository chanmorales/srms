import { FC, ReactNode, useCallback } from "react";
import { Button, Divider, Space, Typography } from "antd";
import { useInject, useService } from "../../hooks";
import { AuthContext, HomePageContext } from "../../providers";
import "../../styles/profile.css";

const { Text } = Typography;

interface ProfileProps {
  avatar: ReactNode;
}

export const Profile: FC<ProfileProps> = ({ avatar }) => {
  const { setToken } = useInject(AuthContext);
  const { user } = useInject(HomePageContext);
  const { authService } = useService();

  const handleLogout = useCallback(async () => {
    try {
      await authService.logout();
      setToken(null);
    } catch (err) {
      console.log(err);
    }
  }, [authService, setToken]);

  return (
    <Space direction="vertical" className="flex min-w-64" align="center">
      {avatar}
      <Text>{user?.username}</Text>
      <Text strong>{user?.displayName}</Text>
      <Divider className="m-0" type="horizontal" />
      <Button type="link" onClick={handleLogout}>
        {"Logout"}
      </Button>
    </Space>
  );
};
