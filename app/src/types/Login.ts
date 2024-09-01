export interface LoginFormData {
  username: string;
  password: string;
}

export interface Jwt {
  token: string;
  expiration: number;
}
