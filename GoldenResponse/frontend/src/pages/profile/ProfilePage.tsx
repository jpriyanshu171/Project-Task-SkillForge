import { useQuery } from '@tanstack/react-query';
import { fetchProfile } from '../../services/auth';

export function ProfilePage() {
  const { data, isLoading } = useQuery(['profile'], fetchProfile);

  if (isLoading) {
    return <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-glass">Loading profile…</div>;
  }

  return (
    <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-8 shadow-glass">
      <h2 className="text-2xl font-semibold text-white">Profile</h2>
      <div className="mt-6 grid gap-4 sm:grid-cols-2">
        <div className="rounded-3xl bg-slate-950/70 p-6 text-slate-300">
          <p className="text-sm uppercase text-slate-400">Name</p>
          <p className="mt-3 text-lg text-white">{data?.fullName}</p>
        </div>
        <div className="rounded-3xl bg-slate-950/70 p-6 text-slate-300">
          <p className="text-sm uppercase text-slate-400">Email</p>
          <p className="mt-3 text-lg text-white">{data?.email}</p>
        </div>
        <div className="rounded-3xl bg-slate-950/70 p-6 text-slate-300">
          <p className="text-sm uppercase text-slate-400">Role</p>
          <p className="mt-3 text-lg text-white">{data?.role}</p>
        </div>
        <div className="rounded-3xl bg-slate-950/70 p-6 text-slate-300">
          <p className="text-sm uppercase text-slate-400">Status</p>
          <p className="mt-3 text-lg text-white">{data?.isActive ? 'Active' : 'Disabled'}</p>
        </div>
      </div>
    </div>
  );
}
