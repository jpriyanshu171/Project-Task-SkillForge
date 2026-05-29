# SkillForge Enterprise Platform

This repository contains a production-grade enterprise learning ecosystem with a React + Vite frontend and Spring Boot backend.

## Features

- JWT authentication with role-based access
- Skills management, dashboard analytics, recommendations, and user profile
- PostgreSQL persistence with Flyway migrations
- Docker Compose deployment
- Backend security, validation, and structured API responses
- Frontend responsive UI built with Tailwind CSS
- CI workflow for backend and frontend builds

## Setup

1. Copy environment files:
   - `backend/.env.example` -> `backend/.env`
   - `frontend/.env.example` -> `frontend/.env.local`
2. Start services:
   - `docker-compose up --build`
3. Access:
   - Frontend: `http://localhost:4173`
   - Backend: `http://localhost:8080`

## Backend

- Spring Boot 3
- Java 21
- PostgreSQL
- Flyway migrations
- JWT auth

## Frontend

- React 18
- Vite
- Tailwind CSS
- React Query
- Axios
- React Router

## Development

- Backend: `cd backend && mvn spring-boot:run`
- Frontend: `cd frontend && npm install && npm run dev`
