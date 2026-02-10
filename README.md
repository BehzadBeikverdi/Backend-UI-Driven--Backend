# 🧠 Backend‑UI‑Driven‑Backend

This is the **Spring Boot backend service** for the Backend‑Driven UI architecture, powering dynamic UI definitions and business data for the frontend application.  
The backend provides REST APIs that deliver **UI schemas**, app **data**, and workflow definitions that the frontend renders dynamically. :contentReference[oaicite:0]{index=0}

---

## 📌 Project Overview

Backend‑UI‑Driven‑Backend is designed to:

- Define UI screens and components on the server side
- Deliver JSON UI schemas to frontend applications (React, Flutter, mobile, etc.)
- Separate UI logic and rendering from backend workflows
- Allow CI/CD UI updates without redeploying frontend

By sending structured UI definitions instead of static views, this backend enables frontend apps to **build screens dynamically at runtime**. :contentReference[oaicite:1]{index=1}

---

## 🧱 Tech Stack

| Layer | Technology |
|-------|------------|
| Framework | Spring Boot (Java) |
| API Style | REST |
| JSON Processing | Jackson |
| Build | Maven / Gradle |
| Database | (Optional) PostgreSQL / H2 |
| Testing | JUnit, MockMvc |

---

## 📁 Project Structure
```
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/yourorg/backenduibridge/
│ │ │ ├── controller/
│ │ │ ├── service/
│ │ │ └── model/
│ │ └── resources/
│ │ ├── application.yml
│ │ └── db/
│ └── test/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```


- **controller/** — REST controllers exposing API endpoints
- **service/** — Business logic and UI schema generation
- **model/** — Domain and DTO classes
- **repository/** — Database access interfaces (if needed)

---

## 🚀 Features

✔️ Deliver dynamic UI screen definitions in JSON  
✔️ Centralized business logic that drives both UI and data  
✔️ Extendable API to support new screens/components  
✔️ Clean separation of backend logic and frontend rendering

---

## 🛠 Getting Started

### 🔁 Requirements

- Java 17+
- Maven or Gradle
- (Optional) PostgreSQL

### 🧾 Configuration

Configure your **application.yml**:

```yaml
server:
  port: 8080
