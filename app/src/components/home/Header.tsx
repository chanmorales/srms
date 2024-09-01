import { Header as LayoutHeader } from "antd/es/layout/layout";
import { Avatar, Menu, Popover } from "antd";
import Animal from "react-animals";
import { Profile } from "./Profile";
import { useMemo } from "react";

export const Header = () => {
  const currentAvatar = useMemo(() => {
    return <Animal />;
  }, []);

  return (
    <LayoutHeader className="flex justify-between py-[20px]">
      <div className="flex items-center w-full">
        <div className="text-white font-bold text-lg mr-5">{"LOGO"}</div>
        <Menu theme="dark" mode="horizontal" className="w-full">
          <Menu.Item key="1">{"Menu 1"}</Menu.Item>
          <Menu.Item key="2">{"Menu 2"}</Menu.Item>
          <Menu.Item key="3">{"Menu 3"}</Menu.Item>
        </Menu>
      </div>
      <Popover
        className="flex self-center"
        trigger="click"
        placement="bottomRight"
        content={<Profile avatar={currentAvatar} />}>
        <Avatar icon={currentAvatar} className="cursor-pointer" />
      </Popover>
    </LayoutHeader>
  );
};
