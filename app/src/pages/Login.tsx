import "../styles/login.css";
import { Alert, Button, Divider, Flex, Form, Input, Typography } from "antd";
import { useCallback, useState } from "react";
import { LockOutlined, LoginOutlined, UserOutlined } from "@ant-design/icons";
import { useInject, useService } from "../hooks";
import { AuthContext } from "../providers";
import { useNavigate } from "react-router-dom";
import packageJson from "../../package.json";
import { LoginFormData } from "../types";

const { Text, Title, Link } = Typography;

export const Login = () => {
  const navigate = useNavigate();
  const { setToken } = useInject(AuthContext);
  const { authService } = useService();
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  const onFinish = useCallback(
    async (loginFormData: LoginFormData) => {
      try {
        setErrorMessage(null);
        const jwt = await authService.login(
          loginFormData.username,
          loginFormData.password
        );
        setToken(jwt.token);
        navigate("/home", { replace: true });
      } catch (err: any) {
        setErrorMessage(err.message);
      }
    },
    [setToken, navigate, authService]
  );

  return (
    <div id="login-page-container">
      <div id="login-form-container">
        <Form
          name="login"
          className="max-w-[360px]"
          initialValues={{ remember: true }}
          onFinish={onFinish}>
          <Form.Item>
            <Title level={2} className="font-sans">
              Login to your account
            </Title>
            <Divider className="m-0 border-white border-[1px]" />
          </Form.Item>
          {errorMessage && (
            <Form.Item>
              <Alert
                type="error"
                message={<Text type="danger">{errorMessage}</Text>}
              />
            </Form.Item>
          )}
          <Form.Item
            name="username"
            rules={[
              {
                required: true,
                message: (
                  <Text strong className="text-red-600">
                    Please input your username.
                  </Text>
                ),
              },
            ]}>
            <Input prefix={<UserOutlined />} placeholder="Username" />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[
              {
                required: true,
                message: (
                  <Text strong className="text-red-600">
                    Please input your password.
                  </Text>
                ),
              },
            ]}>
            <Input
              prefix={<LockOutlined />}
              type="password"
              placeholder="Password"
            />
          </Form.Item>
          <Form.Item>
            <Flex justify="right">
              <Link href="#">Forgot password</Link>
            </Flex>
          </Form.Item>
          <Form.Item>
            <Button
              block
              type="primary"
              htmlType="submit"
              icon={<LoginOutlined />}>
              Log in
            </Button>
            <Flex justify="left">
              or{" "}
              <Link onClick={() => navigate("/register")} className="ml-1">
                Register Now!
              </Link>
            </Flex>
          </Form.Item>
          <Form.Item>
            <Flex justify="center">
              <Text>2024 © &lt;School Name Here&gt; - SRMS</Text>
            </Flex>
            <Flex justify="center">
              <Text>{packageJson.version}</Text>
            </Flex>
          </Form.Item>
        </Form>
      </div>
    </div>
  );
};
