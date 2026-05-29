import { api } from './api';

export interface LoginPayload { email: string; password: string; }
export interface RegisterPayload { fullName: string; email: string; password: string; }

export async function login(payload: LoginPayload) {
  const { data } = await api.post('/auth/login', payload);
  return data.data;
}

export async function register(payload: RegisterPayload) {
  const { data } = await api.post('/auth/register', payload);
  return data.data;
}

export async function fetchProfile() {
  const { data } = await api.get('/users/profile');
  return data.data;
}
