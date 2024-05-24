# Travel-Itinerary-Planning-API-

## Introduction
This API serves for travel itinerary planning, including destination research, booking management, and personalized itinerary creation. The system should integrate with various travel services.

## Base URL
`http://localhost:8080/api`

## Endpoints

### 1. Create a New Account

**HTTP Method:** `POST`

**Endpoint:** `/accounts`

**Description:** Allows users to create a new account.

**Request Headers:**
- `Content-Type: application/json`

**Request Body:**
```json
{
    "username": "StefanS",
    "password": "stavrevski123",
    "email": "stavrevskistefan@gmail.com"
}
```

**Responses:**

- **201 Created:**
  ```json
  {
    "id": 3,
    "username": "StefanS",
    "password": "stavrevski123",
    "email": "stavrevskistefan@gmail.com"
  }
  ```
- **400 Bad Request:** Invalid input

---

### 2. View Current Accounts

**HTTP Method:** `GET`

**Endpoint:** `/accounts`

**Description:** View the current accounts.

**Request Headers:**
- `Accept: application/json`

**Responses:**

- **200 OK:**
  ```json
  [
    {
        "id": 1,
        "username": "mykonos_user",
        "password": "password123",
        "email": "mykonos_user@example.com"
    },
    {
        "id": 2,
        "username": "kopaonik_user",
        "password": "password456",
        "email": "kopaonik_user@example.com"
    },
    {
        "id": 3,
        "username": "StefanS",
        "password": "stavrevski123",
        "email": "stavrevskistefan@gmail.com"
    }
  ]
  ```
- **204 No Content:** No issues found

---

### 3. Retrieve a Single Account by ID

**HTTP Method:** `GET`

**Endpoint:** `/accounts/{id}`

**Description:** Retrieve a specific account by its ID.

**Request Headers:**
- `Accept: application/json`

**Path Parameters:**
- `id` (integer): ID of the issue

**Responses:**

- **200 OK:**
  ```json
  {
    "id": 3,
    "username": "StefanS",
    "password": "stavrevski123",
    "email": "stavrevskistefan@gmail.com"
  }
  ```
- **404 Not Found:** Issue not found

---

### 4. Update Issue Status

**HTTP Method:** `PUT`

**Endpoint:** `/accounts/{id}`

**Description:** Update the username, password or email of an account.

**Request Headers:**
- `Content-Type: application/json`

**Path Parameters:**
- `id` (integer): ID of the issue

**Request Body:**
```json
{
    "username": "StefanStavrevski",
    "password": "stavrevski123",
    "email": "stavrevskistefan@gmail.com"
}
```

**Responses:**

- **200 OK:**
  ```json
  {
    "id": 3,
    "username": "StefanStavrevski",
    "password": "stavrevski123",
    "email": "stavrevskistefan@gmail.com"
  }
  ```
- **400 Bad Request:** Invalid input
- **404 Not Found:** Issue not found

---

### 5. Close an Issue Report

**HTTP Method:** `DELETE`

**Endpoint:** `/accounts/{id}`

**Description:** Delete an account

**Request Headers:**
- `Accept: application/json`

**Path Parameters:**
- `id` (integer): ID of the issue

**Responses:**

- **204 No Content:** Successfully closed
- **404 Not Found:** Issue not found

---
