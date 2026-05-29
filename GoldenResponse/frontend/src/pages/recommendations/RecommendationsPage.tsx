import { useMutation, useQuery } from '@tanstack/react-query';
import { generateRecommendations, getRecommendations } from '../../services/recommendations';

export function RecommendationsPage() {
  const { data = [], isLoading, refetch } = useQuery(['recommendations'], getRecommendations);
  const mutation = useMutation(generateRecommendations, {
    onSuccess: () => refetch(),
  });

  return (
    <div className="space-y-6">
      <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-glass">
        <div className="flex flex-wrap items-center justify-between gap-4">
          <div>
            <h2 className="text-xl font-semibold text-white">AI recommendations</h2>
            <p className="mt-2 text-slate-400">Personalized guidance based on your skills and assessments.</p>
          </div>
          <button className="rounded-2xl bg-sky-500 px-4 py-3 text-sm font-semibold text-slate-950 hover:bg-sky-400" onClick={() => mutation.mutate()}>
            Regenerate insights
          </button>
        </div>
      </div>
      <div className="grid gap-4">
        {isLoading ? <p>Loading recommendations…</p> : data.map((item, idx) => (
          <div key={idx} className="rounded-3xl border border-slate-700 bg-slate-950/80 p-6 shadow-glass">
            <p className="text-sm uppercase text-sky-300">{item.type}</p>
            <p className="mt-2 text-xl font-semibold text-white">{item.title}</p>
            <p className="mt-3 text-slate-300">{item.reason}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
