# Ged - Sistema de Gestão de Documentos

## Resumo

O GeD é um sistema de armazenamento de documentos. Transformando arquivos físicos em arquivos virtuais

# 🛠️Tecnologias usadass

## Front-end

* Vite

* React

* Tailwind

* React Hook Forms

* Zod

* Shadcn.io

## Back-end

* Java 21

* Spring boot

* Gradle

* Docker

## ▶️ Como rodar localmente

## Front-end

1. Crie um arquivo `.env` na pasta `app` e preencha com os seguintes valores:
   
   ```bash
   VITE_BACKEND_URL=<url do backend>
   ```

2. Após isso, rode o comando `yarn dev`

## Back-end

1. Crie um arquivo `.env` na pasta `app` e preencha com os seguintes valores:

```bash
DB_PORT=5432
POSTGRES_USER= # Usuário do banco de dados
POSTGRES_PASSWORD= # Senha do banco de dados
POSTGRES_DB=ged
TZ=America/Bahia
ALLOWED_APPLICATIONS= # URL do frontend (CORS)
AUTH_SECRET_KEY=# Chave de criptografia para a senha
JDBC_URL=jdbc:postgresql://<host ou serviço>:${DB_PORT}/${POSTGRES_DB}
```

2. Se tive o Docker instalado em sua máquina, execute `docker compose up -d --build`. Caso não, execute `./gradlew assemble | java -jar <nome do programa buildado>`

## 🔖Objetivo

A proposta desse projeto é construir um sistema com requisitos minimamentes funcionais e operacionais e como objetivo pessoal, seguir a **arquitetura hexagonal** na construção de novas features

## 👤 Autor

* Kaique Dias Pereira

## 🌐 Link

**Link de produção**: https://gedapp-production-20c4.up.railway.app/signin
