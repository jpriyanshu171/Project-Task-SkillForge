# SkillForge Enterprise Platform

A complete enterprise-grade platform for skills management, learning roadmaps, assessments, and recommendations.

## Overview

`SkillForge` is built as a full-stack application with:
- Spring Boot backend and PostgreSQL persistence
- React + Vite frontend with Tailwind CSS
- JWT authentication and role-based access control
- Docker Compose orchestration for local development

## Features

- Secure user registration and login
- Role-based access for users and admins
- Skills management, dashboard analytics, and personalized suggestions
- Profile management and progress tracking
- PostgreSQL database with Flyway schema migrations
- CI pipeline for backend and frontend build validation

## Quick Start

1. Copy environment files:
   - `backend/.env.example` -> `backend/.env`
   - `frontend/.env.example` -> `frontend/.env.local`
   - `./.env.example` -> `./.env` (optional root Docker env)

2. Start the application stack:

```powershell
docker-compose up --build
```

3. Open the apps:
   - Frontend: `http://localhost:4173`
   - Backend API: `http://localhost:8080`

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

## Repository Structure

- `backend/` — Spring Boot app with controllers, services, repositories, security, and migrations
- `frontend/` — React UI with pages, routing, API clients, and Tailwind styles
- `docker-compose.yml` — local orchestration for backend, frontend, and PostgreSQL
- `README.md` — project overview and setup instructions

## Notes

- Backend runs at `http://localhost:8080`
- Frontend runs at `http://localhost:4173`
- PostgreSQL credentials are set via `backend/.env`
- Flyway applies database migrations automatically on startup

## Recommended Workflow

1. Create environment config files
2. Launch Docker Compose or start services separately
3. Use the frontend UI or backend API endpoints
4. Extend backend routes and frontend pages as needed
