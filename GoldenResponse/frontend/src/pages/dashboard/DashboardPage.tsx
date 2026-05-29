import { useQuery } from '@tanstack/react-query';
import { getDashboardOverview } from '../../services/dashboard';

export function DashboardPage() {
  const { data, isLoading } = useQuery(['dashboardOverview'], getDashboardOverview);

  if (isLoading) {
    return <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-8 shadow-glass">Loading dashboard…</div>;
  }

  return (
    <div className="space-y-6">
      <section className="grid gap-4 md:grid-cols-2 xl:grid-cols-4">
        <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-glass">
          <p className="text-sm uppercase text-slate-400">Skills</p>
          <p className="mt-3 text-4xl font-semibold text-white">{data?.totalSkills ?? 0}</p>
        </div>
        <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-glass">
          <p className="text-sm uppercase text-slate-400">Goals</p>
          <p className="mt-3 text-4xl font-semibold text-white">{data?.totalGoals ?? 0}</p>
        </div>
        <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-glass">
          <p className="text-sm uppercase text-slate-400">Roadmaps</p>
          <p className="mt-3 text-4xl font-semibold text-white">{data?.totalRoadmaps ?? 0}</p>
        </div>
        <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-glass">
          <p className="text-sm uppercase text-slate-400">Assessments</p>
          <p className="mt-3 text-4xl font-semibold text-white">{data?.totalAssessments ?? 0}</p>
        </div>
      </section>
      <section className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-glass">
        <h2 className="text-xl font-semibold text-white">Recommendation feed</h2>
        <div className="mt-4 space-y-4">
          {data?.recommendations.map((item, index) => (
            <div key={index} className="rounded-3xl border border-slate-700 bg-slate-950/60 p-4">
              <p className="text-sm uppercase text-sky-300">{item.type}</p>
              <p className="mt-1 font-semibold text-white">{item.title}</p>
              <p className="mt-2 text-slate-400">{item.reason}</p>
            </div>
          ))}
        </div>
      </section>
    </div>
  );
}
