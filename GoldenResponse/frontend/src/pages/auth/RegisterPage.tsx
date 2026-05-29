import { useState, type FormEvent } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useMutation } from '@tanstack/react-query';
import { register } from '../../services/auth';

export function RegisterPage() {
  const navigate = useNavigate();
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { mutate, isLoading, error } = useMutation(register, {
    onSuccess: (data) => {
      localStorage.setItem('skillforge_access_token', data.accessToken);
      navigate('/app');
    },
  });

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    mutate({ fullName, email, password });
  };

  return (
    <div>
      <h2 className="text-3xl font-semibold">Create your SkillForge account</h2>
      <p className="mt-2 text-slate-400">Build your roadmap, track career goals, and unlock skill analytics.</p>
      <form className="mt-8 space-y-4" onSubmit={handleSubmit}>
        <div className="space-y-2">
          <label className="block text-sm text-slate-300">Full name</label>
          <input
            className="w-full rounded-2xl border border-slate-700 bg-slate-950 p-3 text-white"
            type="text"
            value={fullName}
            onChange={(e) => setFullName(e.target.value)}
            required
          />
        </div>
        <div className="space-y-2">
          <label className="block text-sm text-slate-300">Email</label>
          <input
            className="w-full rounded-2xl border border-slate-700 bg-slate-950 p-3 text-white"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="space-y-2">
          <label className="block text-sm text-slate-300">Password</label>
          <input
            className="w-full rounded-2xl border border-slate-700 bg-slate-950 p-3 text-white"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <p className="text-sm text-red-400">Registration failed. Try again with a valid email.</p>}
        <button
          type="submit"
          className="w-full rounded-2xl bg-sky-500 px-4 py-3 text-sm font-semibold text-slate-950 transition hover:bg-sky-400"
          disabled={isLoading}
        >
          {isLoading ? 'Creating account…' : 'Register'}
        </button>
      </form>
      <p className="mt-4 text-sm text-slate-400">
        Already have an account?{' '}
        <Link className="text-sky-300 underline" to="/auth/login">
          Sign in
        </Link>
      </p>
    </div>
  );
}
