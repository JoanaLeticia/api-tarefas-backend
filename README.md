
# 📋 API de Gerenciamento de Tarefas

Este projeto consiste em uma API REST para gerenciamento de tarefas, desenvolvida com [Quarkus](https://quarkus.io/), com front-end em [Angular](https://angular.io/) e banco de dados PostgreSQL. Ele foi criado como projeto prático para demonstrar a **padronização de ambientes de testes, homologação e produção**, conforme estudo de caso baseado nas práticas adotadas pelo Spotify.

---

## 🧩 Funcionalidades da API

- Criar tarefa
- Listar tarefas
- Atualizar tarefa
- Deletar tarefa
- Organização por perfis de ambiente (`dev`, `test`, `prod`)

---

## 🌍 Ambientes e Perfis

O projeto está estruturado para suportar múltiplos ambientes, com configurações separadas para cada um:

| Ambiente     | Perfil               | Banco de Dados              | Uso                          |
|--------------|----------------------|-----------------------------|-------------------------------|
| Desenvolvimento | `dev`               | `tarefas_dev`               | Desenvolvimento local com recarga automática |
| Homologação  | `test`              | `tarefas_test`              | Testes de integração / pré-produção |
| Produção     | `prod`              | `tarefas_prod`              | Ambiente final (simulado)     |

Cada ambiente possui seu próprio arquivo de configuração:

- `application-dev.properties`
- `application-test.properties`
- `application-prod.properties`

---

## 🚀 Como executar

### Requisitos
- Java 17+
- Maven
- PostgreSQL em execução

### Comandos

**Ambiente de desenvolvimento:**
```bash
./mvnw quarkus:dev -Dquarkus.profile=dev
```

**Ambiente de homologação (teste):**
```bash
./mvnw quarkus:dev -Dquarkus.profile=test
```

**Ambiente de produção (simulado):**
```bash
./mvnw quarkus:dev -Dquarkus.profile=prod
```

---

## 💻 Front-end Angular

O front-end está disponível no diretório `/frontend` (ou em repositório separado) e foi configurado para se comunicar com a API conforme o ambiente:

- Ambiente `dev`: `http://localhost:8080`
- Ambiente `test`: `http://homolog.frontend.com`
- Ambiente `prod`: `https://app.minhaempresa.com.br`

> O front usa arquivos `environment.ts`, `environment.test.ts` e `environment.prod.ts` para trocar URLs de API.

---

## 🛠️ Padronização e Práticas DevOps

Este projeto segue os princípios de padronização abordados no estudo de caso do Spotify:

- Separação de ambientes com perfis distintos
- Arquivos de configuração isolados por ambiente
- Estrutura de banco de dados separada por perfil
- Branches Git por ambiente (`main`, `dev`, `homolog`)
- Scripts de execução padronizados
- (Opcional) Integração com CI/CD via GitHub Actions

---

## 📁 Estrutura de diretórios

```
apitarefas/
├── src/
├── src/main/resources/
│   ├── application-dev.properties
│   ├── application-test.properties
│   └── application-prod.properties
├── target/
└── README.md
```

---

## 🧪 Testes

- Testes podem ser executados no perfil `test` sem afetar dados do ambiente `dev` ou `prod`.
- (Opcional) Implementação de testes unitários com `@Test` e simulação de chamadas REST.

---

## 📌 Autora

Joana – Projeto prático para a disciplina de Computação Orientada a Serviços  
Curso de Sistemas de Informação

---

## 📚 Referência técnica

Este projeto se baseia em conceitos extraídos do estudo de caso:  
**Spotify – Padronização de Ambientes com DevOps e CI/CD**
