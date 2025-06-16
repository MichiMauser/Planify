# 🗓️ Planify - Plan Your Future

Planify is a monolithic web application that helps users organize their goals, track progress, and engage with a productivity-driven community. Featuring notebooks, weekly archives, group chats, and achievements, Planify makes personal planning effective and motivating.

---

## 🚀 Features

- 📝 Create, edit, sort, archive notebooks
- 🗂️ View past performance through notebook archives
- 🧑‍🤝‍🧑 Join interest-based groups (**Work in Progress**)
- 🏆 Track achievements and goal progress
- 👤 Admin panel to manage users and content

---

## 🏗️ Project Structure (Multi-Layered Monolith)

```bash
src/
├── config/             # Security, web, and general configuration
├── controller/         # Handles incoming HTTP requests
├── model/              # Domain and DTO classes
├── repository/         # Spring Data JPA Repositories
├── service/            # Business logic and transactions
├── utility/            # Helper classes (e.g., validation, mapping)
└── PlanifyApplication.java
