# Folio — Resume Studio

A full-stack resume builder inspired by the supplied tutorial and restyled as a calm, editorial portfolio product. The React app includes an interactive landing page, signup/login flow, saved resume dashboard, multi-step editor, live resume preview, four visual themes, editable accent colors, and browser print-to-PDF. Browser storage makes the UI usable immediately without credentials or services.

## Run the app

Requirements: Node.js 20+, Java 17+, Maven 3.8+, and MongoDB 6+ (for the API).

```sh
npm install
npm run dev
```

Open http://localhost:5173. `npm run build` creates the production front-end in `dist/`.

## Run the Spring Boot API

Start MongoDB, then in a second terminal:

```sh
cd backend
mvn spring-boot:run
```

The API listens on port 8080. Health: `GET /actuator/health`. Set `MONGODB_URI`, `JWT_SECRET` (32+ bytes), and `WEB_ORIGIN` for your environment. The local JWT secret is for development only.

### API routes

| Method | Route | Authentication | Purpose |
| --- | --- | --- | --- |
| POST | `/api/auth/register` | No | Create account; JSON `{ "name", "email", "password" }` |
| POST | `/api/auth/login` | No | Sign in; returns bearer token and profile |
| GET | `/api/auth/me` | Bearer JWT | Current account |
| GET, POST | `/api/resumes` | Bearer JWT | List or create owned resumes |
| GET, PUT, DELETE | `/api/resumes/{id}` | Bearer JWT | Read, update, or delete an owned resume |

Passwords are BCrypt hashed, JWTs are stateless, and resume queries are scoped to the authenticated owner. MongoDB stores accounts and resume JSON.

## Integrations and production setup

The transcript describes external services that require real accounts and credentials. This starter keeps the local experience runnable without secrets: browser storage provides demo accounts/resumes when the API is not configured, PDF export uses the browser print dialog, and the “Try premium demo” action simulates an upgrade. It does **not** charge a card. To connect the UI to persistent account and resume APIs, start the backend and create a `.env.local` file in the project root containing `VITE_API_URL=http://localhost:8080`, then restart Vite. Registration/login and resume create/read/update operations will use the API and JWT; without that setting they stay in browser storage.

The tutorial also includes email verification and resume delivery, Razorpay order creation plus server-side signature verification, and Cloudinary image uploads. Those external integrations are not implemented in this starter yet; the Plus upgrade is a demo simulation and account email is not verified. Add these services, HTTPS, a production JWT secret, database backups, rate limiting, and deployment-specific CORS before exposing the API publicly. Never put service secrets in front-end code.

## Project layout

- `src/` — React + Vite product UI
- `backend/` — Spring Boot 3 / Java 17 REST API, MongoDB, Spring Security, JWT

## Deploy with Render

`render.yaml` defines a Render Static Site for the Vite frontend and a Docker web service for the Spring API. The API uses Render's generated `JWT_SECRET`; Render asks you to provide `MONGODB_URI` for an external MongoDB deployment (for example MongoDB Atlas). The Blueprint connects the frontend URL to the API's CORS setting. The Vite build gets the API URL at build time.

To deploy, push this project to a GitHub, GitLab, or other Git provider repository, then create a Blueprint from that repository in Render and supply `MONGODB_URI` when prompted. Render Blueprints create and update services from a repository containing `render.yaml`; this workspace currently has no Git remote or Render account connection, so the services are configured here but have not been published yet. See [Render Blueprint documentation](https://render.com/docs/blueprint-spec) and [Render static site docs](https://render.com/docs/static-sites).


