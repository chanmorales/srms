import { Context, useContext } from "react";

/**
 * A generic hook to retrieve context container
 *
 * @param context - the context type
 */
export const useInject = <T>(context: Context<T>) => {
  const container = useContext(context);
  if (!container) {
    throw new Error(
      `${context} container not found. Make sure to wrap your components with a correct provider.`
    );
  }

  return container;
};
