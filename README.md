<div align="center">
   <img src="./.github/logo.svg" alt="logo" width="35%">
   <h1>Mock Api Hub</h1>
</div>

<p align="center">
   <a href="https://github.com/nell-shark/mock-api-hub/actions">
      <img src="https://github.com/nell-shark/mock-api-hub/actions/workflows/build.yml/badge.svg" alt=""/>
   </a>
   <a href="https://github.com/nell-shark/mock-api-hub/blob/master/LICENSE">
      <img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="">
   </a>
    <a href="https://github.com/nell-shark/mock-api-hub">
      <img src="https://img.shields.io/github/repo-size/nell-shark/mock-api-hub.svg" alt="" />
   </a>
    <a href="https://github.com/nell-shark/mock-api-hub/pulls">
      <img src="https://img.shields.io/github/issues-pr-raw/nell-shark/mock-api-hub.svg" alt="" />
   </a> 
    <a href="https://github.com/nell-shark/mock-api-hub/issues">
      <img src="https://img.shields.io/github/issues-raw/nell-shark/mock-api-hub.svg" alt="" />
   </a>
    <a href="https://github.com/nell-shark/mock-api-hub">
      <img src="https://img.shields.io/github/last-commit/nell-shark/mock-api-hub.svg" alt="" />
   </a>
    <a href="https://github.com/nell-shark/mock-api-hub">
      <img src="https://codecov.io/gh/nell-shark/mock-api-hub/branch/master/graph/badge.svg" alt="" />
   </a>
</p>


<div align="center">
<strong>

[Introduction](#-introduction) ·
[Guide](#-guide) ·
[Installation](#%EF%B8%8F-installation) ·
[Contributing](#-contributing) ·
[License](#%EF%B8%8F-license)

Made with ❤️ by [NellShark](https://github.com/nell-shark)

</strong>
</div>

## ⚡ Introduction

A free simulated API designed to facilitate testing and experimentation.
This service ensures the simplicity of the development process.

## 📑 Guide

Fetching data using bash:

```bash
curl http://localhost:80/api/v1/addresses/1
```

Fetching data using JavaScript:

```javascript
fetch('http://localhost:80/api/v1/addresses/1')
      .then(response => response.json())
      .then(json => console.log(json))
```

| Resources             | Counts |
|:----------------------|:------:|
| /api/v1/addresses     |  100   |
| /api/v1/books         |  100   |
| /api/v1/comments      |  100   |
| /api/v1/companies     |  100   |
| /api/v1/courses       |  100   |
| /api/v1/employees     |  100   |
| /api/v1/events        |  100   |
| /api/v1/messages      |  100   |
| /api/v1/notifications |  100   |
| /api/v1/orders        |  100   |
| /api/v1/posts         |  100   |
| /api/v1/products      |  100   |
| /api/v1/projects      |  100   |
| /api/v1/recipes       |  100   |
| /api/v1/reviews       |  100   |
| /api/v1/todos         |  100   |
| /api/v1/users         |  100   |

| Methods | Routes                           |
|---------|:---------------------------------|
| GET     | /api/v1/addresses                |
| GET     | /api/v1/addresses/1              |
| GET     | /api/v1/addresses?page=1         |
| GET     | /api/v1/addresses?size=5         |
| GET     | /api/v1/addresses?sort=city      |
| GET     | /api/v1/addresses?direction=DESC |
| GET     | /api/v1/addresses?country=China  |
| POST    | /api/v1/addresses                |
| PUT     | /api/v1/addresses/1              |
| PATCH   | /api/v1/addresses/1              |
| DELETE  | /api/v1/addresses/1              |

> **Note**\
> The server won't actually update the resource, but it will simulate the update as if it did

## 🛠️ Installation

You can install this project locally:

1. Install **[Docker](https://www.docker.com/)**
2. Clone this repository
    ```bash
    git clone https://github.com/nell-shark/mock-api-hub
    ```
3. Run all containers with:
   ```bash
   docker compose up
    ```
4. If everything goes well, you can start using it:
   ```bash
   curl http://localhost:80/api/v1/addresses
   ```

## 🤝 Contributing

If you are interested in contributing to this project, I highly recommend checking out
the [contributing guidelines](https://github.com/nell-shark/mock-api-hub/blob/master/CONTRIBUTE.md).

## ⚖️ License

This project is licensed under the [MIT License](https://github.com/nell-shark/mock-api-hub/blob/master/LICENSE).
