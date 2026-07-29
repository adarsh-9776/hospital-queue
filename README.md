# Hospital Queue Management System

A simple backend application for managing patients and hospital queues.

## Tech Stack

- Java
- Spring Boot
- PostgreSQL
- Spring Data JPA
- REST API
- Maven

## Current Features

- Add a new patient
- Get all patients
- Get a patient by ID
- Store patient data in PostgreSQL

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/patients` | Add a patient |
| GET | `/patients` | Get all patients |
| GET | `/patients/{id}` | Get patient by ID |

## Project Structure

```text
controller
service
repository
model
