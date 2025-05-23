# Template Spring Boot Projeto Crédito


Este projeto é um para serviços desenvolvidos com **Spring Boot**, com foco em boas práticas 
e documentação.

## 📦 Estrutura do Projeto

- **Spring Boot** 3.2.0
- **Java** 21
- **Banco de Dados**: PostgreSQL
- **Swagger/OpenAPI** via Springdoc
- **GitHub** como repositório.
- **JUnit** para testes unitários.

### Pré-requisitos

- Java 21+
- Maven
- Postgree JDBC driver
- Docker (opcional para banco local)

### Executando localmente

-(Local)
mvn spring-boot:run

-(Docker)
*Build
docker build -t credito-1.0.0:latest .

*Run
docker run -d -p 8080:8080 --name credito-1.0.0 credito-1.0.0:latest


A aplicação será disponibilizada por padrão em `http://localhost:8080/api/credito`.

### Variáveis configuráveis via `application.yaml`

- `PORT`: Porta da aplicação (padrão: 8080)

## 🔍 Documentação Swagger

Disponível automaticamente via Springdoc em:

```
${config-swagger.server}/swagger-ui/index.html
```

## 🧪 Testes 

- `mvn test` para rodar os testes

# Manual de Boas Práticas de Arquitetura 

Este manual foi elaborado com base nos testes de arquitetura definidos com o ArchUnit. Ele tem como objetivo garantir uma arquitetura limpa, desacoplada e padronizada para os projetos Java Spring Boot do STJ.

## 📦 Organização de Pacotes

- `..controller..`: Apenas Controllers, terminando com `Controller`. Devem retornar `ResponseEntity<T>` onde T termina com `Response`.
- `..service..`: Apenas serviços, terminando com `Service`. Devem retornar apenas DTOs (terminando com `Dto` ou `DTO`) ou `void`.
- `..repository..`: Apenas repositórios, terminando com `Repository`.
- `..entity..`: Apenas entidades JPA, terminando com `Entity`. Não devem acessar DTOs.
- `..dto..`: Apenas DTOs. Não devem acessar entidades e não devem conter lógica de negócio.
- `..web.controller.domain..`: Apenas classes `Request` e `Response`, terminando com esses sufixos.

- A estrutura de pacotes do projeto deve seguir os padrões abaixo para garantir uma organização clara e evitar violações arquiteturais:

### 1. `service`
- **Regra:** Todas as classes que terminam com `Service` devem estar exclusivamente neste pacote.
- **Exemplo:** `CreditoService` → `...service`

### 2. `repository`
- **Regra:** Todas as classes que terminam com `Repository` devem estar exclusivamente neste pacote.
- **Exemplo:** `CreditoRepository` → `...repository`

### 3. `dto`
- **Regra:** Todas as classes que terminam com `Dto`, `IDto`(Projection) devem estar exclusivamente neste pacote.
- **Exemplo:** `CreditoDto` → `...dto`

### 4. `web.controller`
- **Regra:** Classes que terminam com `Controller` devem estar exclusivamente neste pacote.
- **Exemplo:** `CreditoController` → `...web.controller`

### 5. `web.controller.domain`
- **Regra:** Classes que terminam com `Request` ou `Response` devem estar exclusivamente neste subpacote.
- **Exemplo:** `UserRequest`, `UserResponse` → `...web.controller.domain`

## 🔄 Regras de Acesso entre Camadas

- Controllers **não devem acessar diretamente** repositórios.
- Repositórios devem ser acessados **apenas** por serviços.
- Serviços **não devem acessar Controllers**.
- DTOs não devem acessar entidades, e entidades não devem depender de DTOs.

## ✅ Convenções de Nomenclatura

- `@RestController` → classes devem terminar com `Controller`.
- `@Service` → classes devem terminar com `Service`.
- DTOs → terminam com `Dto` ou `DTO`.
- Entidades → terminam com `Entity`.
- Repositórios → terminam com `Repository`.
- Requisições/Respostas da camada web → terminam com `Request` / `Response`.

## 🚫 Restrições

- Evitar o uso direto de `java.util.Date` ou `Calendar`; utilizar `java.time.*` (JSR-310).
- Controllers **não devem retornar** tipos primitivos ou `String` diretamente.
- DTOs **devem conter apenas** atributos e métodos `get/set`.

## 🧪 Boas Práticas de Testes

- Adicionar novas regras sempre que um padrão for estabelecido.


---

© Edivaldo Alves Nogueira - 2025 - Desenvolvedor Java.
