import { api } from './api';
import { DashboardOverview } from '../types';

export async function getDashboardOverview() {
  const { data } = await api.get('/dashboard/overview');
  return data.data as DashboardOverview;
}
