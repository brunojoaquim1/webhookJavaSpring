# ?? Webhook Listener - Spring Boot + PostgreSQL + Docker + Ngrok

Este projeto implementa um **listener de webhooks** em **Spring Boot 3**, com banco **PostgreSQL 16** rodando em **Docker Compose** e controle de migra��es com **Flyway**.  
O endpoint `/webhook` recebe eventos via **HTTP POST** e persiste os dados em banco.  
Para expor a API externamente e testar integra��es reais, utilizamos **Ngrok**.

---

## ?? Stack utilizada
- **Java 21** + **Spring Boot 3**  
- **PostgreSQL 16**  
- **Flyway** (versionamento do schema)  
- **Docker Compose** (infra isolada)  
- **Ngrok** (t�nel para acesso externo)  

---

## ?? Como rodar o projeto

1. Clone este reposit�rio:
   ```bash
   git clone https://github.com/seu-usuario/webhook-listener.git
   cd webhook-listener
   ```

2. Crie um arquivo .env com as vari�veis:**
   ```bash
    env
    POSTGRES_USER=postgres
    POSTGRES_PASSWORD=postgres
    POSTGRES_DB=webhookdb
    POSTGRES_PORT=5432
    APP_PORT=8080
```

3. Suba os containers:

    ```bash
    docker compose up --build
```

4. Exponha sua API com Ngrok:

    ```bash
    ngrok http 8080
    ```

Isso ir� gerar uma URL p�blica, algo como:

    https://xxxxxx.ngrok-free.dev -> http://localhost:8080
    
**?? Endpoints dispon�veis**

* POST /webhook ? Recebe eventos externos em JSON e salva no banco
* GET / ? Health-check (opcional)

**Exemplo de request:**

```bash
curl -X POST https://xxxxxx.ngrok-free.dev/webhook \
  -H "Content-Type: application/json" \
  -d '{"messageId": "123", "payload": "Hello Webhook!"}'
```

**??? Banco de Dados**

O Flyway gerencia a tabela webhook_events automaticamente no startup:

```sql
create table webhook_events (
    id bigserial primary key,
    message_id varchar(255),
    payload text,
    processed boolean default false,
    received_at timestamp default now()
);
```


? Cr�ditos
Feito com muito ? por Brujo Joaquim