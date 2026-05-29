import { useMemo, useState, type FormEvent } from 'react';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { createSkill, deleteSkill, getCategories, getSkills, getSkillAnalytics } from '../../services/skill';
import { SkillRequest, SkillResponse, SkillAnalyticsResponse } from '../../types';

const defaultSkill: SkillRequest = { name: '', category: '', proficiency: 'Beginner', priority: 1, progress: 0 };

export function SkillsPage() {
  const queryClient = useQueryClient();
  const [model, setModel] = useState(defaultSkill);
  const { data: skills = [], isLoading } = useQuery<SkillResponse[]>(['skills'], getSkills);
  const { data: categories = [] } = useQuery<string[]>(['categories'], getCategories);
  const { data: analytics } = useQuery<SkillAnalyticsResponse>(['skillsAnalytics'], getSkillAnalytics);

  const createMutation = useMutation(createSkill, {
    onSuccess: () => {
      queryClient.invalidateQueries(['skills']);
      queryClient.invalidateQueries(['skillsAnalytics']);
      queryClient.invalidateQueries(['categories']);
      setModel(defaultSkill);
    },
  });

  const deleteMutation = useMutation(deleteSkill, {
    onSuccess: () => queryClient.invalidateQueries(['skills']),
  });

  const categoryOptions = useMemo(() => ['Development', 'Design', 'Leadership', 'Analytics', 'DevOps', ...categories], [categories]);

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    createMutation.mutate(model);
  };

  return (
    <div className="space-y-6">
      <section className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-glass">
        <h2 className="text-xl font-semibold text-white">Skill management</h2>
        <form className="mt-6 grid gap-4 sm:grid-cols-2" onSubmit={handleSubmit}>
          <input className="rounded-2xl border border-slate-700 bg-slate-950 p-3 text-white" placeholder="Skill name" value={model.name} onChange={(e) => setModel({...model, name: e.target.value})} required />
          <select className="rounded-2xl border border-slate-700 bg-slate-950 p-3 text-white" value={model.category} onChange={(e) => setModel({...model, category: e.target.value})} required>
            <option value="">Select category</option>
            {categoryOptions.map((category) => <option key={category} value={category}>{category}</option>)}
          </select>
          <select className="rounded-2xl border border-slate-700 bg-slate-950 p-3 text-white" value={model.proficiency} onChange={(e) => setModel({...model, proficiency: e.target.value})}>
            {['Beginner', 'Intermediate', 'Advanced', 'Expert'].map((level) => <option key={level} value={level}>{level}</option>)}
          </select>
          <input className="rounded-2xl border border-slate-700 bg-slate-950 p-3 text-white" type="number" min="1" max="5" value={model.priority} onChange={(e) => setModel({...model, priority: Number(e.target.value)})} required />
          <input className="rounded-2xl border border-slate-700 bg-slate-950 p-3 text-white" type="number" min="0" max="100" value={model.progress} onChange={(e) => setModel({...model, progress: Number(e.target.value)})} required />
          <button type="submit" className="col-span-full rounded-2xl bg-sky-500 px-4 py-3 text-white transition hover:bg-sky-400" disabled={createMutation.isLoading}>Add skill</button>
        </form>
      </section>
      <section className="grid gap-4 lg:grid-cols-[1fr_0.45fr]">
        <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-glass">
          <h2 className="text-xl font-semibold text-white">Active skills</h2>
          <div className="mt-4 space-y-3">
            {isLoading ? <p>Loading skills…</p> : skills.map((skill) => (
              <div key={skill.id} className="rounded-3xl border border-slate-700 bg-slate-950/70 p-4">
                <div className="flex items-center justify-between gap-3">
                  <div>
                    <p className="font-semibold text-white">{skill.name}</p>
                    <p className="text-sm text-slate-400">{skill.category} • {skill.proficiency}</p>
                  </div>
                  <button onClick={() => deleteMutation.mutate(skill.id)} className="rounded-2xl bg-red-600 px-3 py-2 text-sm text-white hover:bg-red-500">Delete</button>
                </div>
                <div className="mt-3 h-2 overflow-hidden rounded-full bg-slate-800">
                  <div className="h-full rounded-full bg-sky-400" style={{ width: `${skill.progress}%` }} />
                </div>
              </div>
            ))}
          </div>
        </div>
        <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-glass">
          <h2 className="text-xl font-semibold text-white">Skill analytics</h2>
          <div className="mt-5 space-y-4 text-slate-300">
            <p>Total skills: <span className="text-white">{analytics?.totalSkills ?? 0}</span></p>
            <p>Average progress: <span className="text-white">{analytics?.averageProgress.toFixed(0) ?? 0}%</span></p>
            <div>
              <p className="font-semibold text-white">By category</p>
              <ul className="mt-2 space-y-2">
                {analytics ? Object.entries(analytics.skillsByCategory).map(([category, count]) => (
                  <li key={category} className="flex justify-between rounded-2xl bg-slate-950/70 px-4 py-3 text-sm text-slate-300">
                    <span>{category}</span>
                    <span>{count}</span>
                  </li>
                )) : <li>Loading…</li>}
              </ul>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}
