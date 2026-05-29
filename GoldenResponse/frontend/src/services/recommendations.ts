import { api } from './api';
import { RecommendationSummary } from '../types';

export async function getRecommendations() {
  const { data } = await api.get('/recommendations');
  return data.data as RecommendationSummary[];
}

export async function generateRecommendations() {
  const { data } = await api.post('/recommendations/generate');
  return data.data as RecommendationSummary[];
}
