# 📡 Webhook Listener - Spring Boot + PostgreSQL + Docker + Ngrok

Este projeto implementa um **listener de webhooks** em **Spring Boot 3**, com banco **PostgreSQL 16** rodando em **Docker Compose** e controle de migrações com **Flyway**.  
O endpoint `/webhook` recebe eventos via **HTTP POST** e persiste os dados em banco.  
Para expor a API externamente e testar integrações reais, utilizamos **Ngrok**.

---

## 🚀 Stack utilizada
- **Java 21** + **Spring Boot 3**  
- **PostgreSQL 16**  
- **Flyway** (versionamento do schema)  
- **Docker Compose** (infra isolada)  
- **Ngrok** (túnel para acesso externo)  

---

## ⚙️ Como rodar o projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/webhook-listener.git
   cd webhook-listener

**Crie um arquivo .env com as variáveis:**
   ```bash
    env
    POSTGRES_USER=postgres
    POSTGRES_PASSWORD=postgres
    POSTGRES_DB=webhookdb
    POSTGRES_PORT=5432
    APP_PORT=8080 

**Suba os containers:**

bash
Copiar código
docker compose up --build
Exponha sua API com Ngrok:

bash
Copiar código
ngrok http 8080
Isso irá gerar uma URL pública, algo como:

bash
Copiar código
https://xxxxxx.ngrok-free.dev -> http://localhost:8080
🔗 Endpoints disponíveis
POST /webhook → Recebe eventos externos em JSON e salva no banco

GET / → Health-check (opcional)

Exemplo de request:

bash
Copiar código
curl -X POST https://xxxxxx.ngrok-free.dev/webhook \
  -H "Content-Type: application/json" \
  -d '{"messageId": "123", "payload": "Hello Webhook!"}'
🗄️ Banco de Dados
O Flyway gerencia a tabela webhook_events automaticamente no startup:

sql
Copiar código
create table webhook_events (
    id bigserial primary key,
    message_id varchar(255),
    payload text,
    processed boolean default false,
    received_at timestamp default now()
);
🛠️ Próximos Passos
Adicionar autenticação no endpoint

Criar página de monitoramento simples (dashboard)

Deploy em ambiente cloud (Heroku, Render, Railway, AWS)

✨ Créditos
Feito com ☕ e ⚡ por Bruno Joaquim 
