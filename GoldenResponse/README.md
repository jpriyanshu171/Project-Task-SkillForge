# SkillForge Enterprise Platform

A production-ready enterprise learning and skills management platform with a React + Vite frontend and Spring Boot backend.

## What’s included

- JWT authentication and role-based access control
- Skills management, dashboard analytics, recommendations, and user profile pages
- PostgreSQL persistence with Flyway database migrations
- Docker Compose for local development and service orchestration
- Tailwind CSS frontend styling with React Router, React Query, and Axios
- CI pipeline for backend and frontend builds

## Quick Start

1. Copy environment examples:
   - `backend/.env.example` -> `backend/.env`
   - `frontend/.env.example` -> `frontend/.env.local`
   - `./.env.example` -> `./.env` if you want root Docker environment variables
2. Build and start the stack:
   - `docker-compose up --build`
3. Open in browser:
   - Frontend: `http://localhost:4173`
   - Backend: `http://localhost:8080`

## Local Development

### Backend

```powershell
cd backend
mvn clean package
mvn spring-boot:run
```

### Frontend

```powershell
cd frontend
npm install
npm run dev
```

## Project Structure

- `backend/` — Spring Boot application, security, services, controllers, repositories, DTOs, exceptions
- `frontend/` — React app, pages, components, services, API clients, routing, and tests
- `docker-compose.yml` — local orchestration for backend, frontend, and PostgreSQL
- `README.md` — project overview and quickstart guide

## Notes

- Backend runs on `http://localhost:8080`
- Frontend runs on `http://localhost:4173`
- PostgreSQL credentials are configured via environment variables and Flyway initializes the schema on startup

## Recommended Workflow

1. Prepare `.env` files
2. Start Docker services
3. Use backend API via Postman or frontend UI
4. Extend features in `backend/src/main/java` and `frontend/src`
