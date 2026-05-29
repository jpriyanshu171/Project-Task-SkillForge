import { Navigate } from 'react-router-dom';

const tokenKey = 'skillforge_access_token';

export function PrivateRoute({ children }: { children: JSX.Element }) {
  const token = localStorage.getItem(tokenKey);
  if (!token) {
    return <Navigate to="/auth/login" replace />;
  }
  return children;
}
