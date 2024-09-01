import { notification } from "antd";
import { ArgsProps } from "antd/es/notification";
import { ReactNode } from "react";

/**
 * Shows a notification
 *
 * @param type - the type of notification
 * @param message - the title of the notification
 * @param description - the content of the notification
 * @param options - other properties
 */
export const showNotification = (
  type: "success" | "error" | "info" | "warning",
  message: ReactNode,
  description: ReactNode,
  options: Omit<ArgsProps, "message" | "description"> = {}
): void => {
  notification[type]({ message, description, ...options });
};
