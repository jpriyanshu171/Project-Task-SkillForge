import { Outlet } from 'react-router-dom';

export function AuthLayout() {
  return (
    <div className="min-h-screen bg-slate-950 text-white flex items-center justify-center p-4">
      <div className="w-full max-w-xl rounded-3xl bg-slate-900/85 border border-slate-700 shadow-glass backdrop-blur-xl p-8">
        <Outlet />
      </div>
    </div>
  );
}
