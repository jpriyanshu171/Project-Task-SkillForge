export interface AuthResponse {
  accessToken: string;
  refreshToken: string;
  tokenType: string;
}

export interface UserProfile {
  id: number;
  fullName: string;
  email: string;
  role: string;
  avatarUrl?: string;
  bio?: string;
  isActive: boolean;
}

export interface SkillRequest {
  name: string;
  category: string;
  proficiency: string;
  priority: number;
  progress: number;
}

export interface SkillResponse {
  id: number;
  name: string;
  category: string;
  proficiency: string;
  priority: number;
  progress: number;
}

export interface SkillAnalyticsResponse {
  totalSkills: number;
  skillsByCategory: Record<string, number>;
  averageProgress: number;
}

export interface RecommendationSummary {
  title: string;
  type: string;
  reason: string;
}

export interface DashboardOverview {
  totalSkills: number;
  totalGoals: number;
  totalRoadmaps: number;
  totalAssessments: number;
  recommendations: RecommendationSummary[];
}
