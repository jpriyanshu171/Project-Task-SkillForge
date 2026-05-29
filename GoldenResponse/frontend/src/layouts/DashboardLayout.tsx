import { NavLink, Outlet } from 'react-router-dom';

const navItems = [
  { label: 'Dashboard', path: '/app' },
  { label: 'Skills', path: '/app/skills' },
  { label: 'Recommendations', path: '/app/recommendations' },
  { label: 'Profile', path: '/app/profile' },
];

export function DashboardLayout() {
  return (
    <div className="min-h-screen bg-slate-950 text-slate-100">
      <div className="mx-auto max-w-7xl px-4 py-6">
        <header className="mb-8 flex flex-col gap-4 rounded-3xl border border-slate-800 bg-slate-900/80 p-4 shadow-glass backdrop-blur-xl sm:flex-row sm:items-center sm:justify-between">
          <div>
            <p className="text-sm uppercase text-sky-300">SkillForge</p>
            <h1 className="text-3xl font-semibold">Enterprise learning command center</h1>
          </div>
          <nav className="flex flex-wrap gap-3">
            {navItems.map((item) => (
              <NavLink
                key={item.path}
                to={item.path}
                className={({ isActive }) =>
                  `rounded-2xl px-4 py-2 transition ${isActive ? 'bg-slate-700 text-white' : 'bg-slate-800/70 text-slate-300 hover:bg-slate-700'}`
                }
              >
                {item.label}
              </NavLink>
            ))}
          </nav>
        </header>
        <main className="space-y-6">
          <Outlet />
        </main>
      </div>
    </div>
  );
}
