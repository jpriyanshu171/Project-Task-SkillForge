# SkillForge Backend

Spring Boot backend for SkillForge enterprise platform.

## Run locally

1. Ensure PostgreSQL is running.
2. Set environment variables from `.env.example`.
3. Run `./mvnw spring-boot:run`.

## API Endpoints

- `POST /api/auth/register`
- `POST /api/auth/login`
- `POST /api/auth/refresh-token`
- `GET /api/auth/me`
- `GET /api/skills`
- `POST /api/skills`
- `GET /api/dashboard/overview`
- `GET /api/recommendations`
- `POST /api/recommendations/generate`

All APIs use JSON request and response structures.
