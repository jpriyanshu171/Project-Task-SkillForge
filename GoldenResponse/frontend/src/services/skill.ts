import { api } from './api';
import { SkillRequest, SkillResponse, SkillAnalyticsResponse } from '../types';

export async function getSkills() {
  const { data } = await api.get('/skills');
  return data.data as SkillResponse[];
}

export async function createSkill(payload: SkillRequest) {
  const { data } = await api.post('/skills', payload);
  return data.data as SkillResponse;
}

export async function deleteSkill(id: number) {
  await api.delete(`/skills/${id}`);
}

export async function getCategories() {
  const { data } = await api.get('/skills/categories');
  return data.data as string[];
}

export async function getSkillAnalytics() {
  const { data } = await api.get('/skills/analytics');
  return data.data as SkillAnalyticsResponse;
}
