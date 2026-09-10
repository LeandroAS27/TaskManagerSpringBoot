# TaskManager

API REST para gerenciamento de tarefas, desenvolvida com Spring Boot. O projeto permite criar, consultar, atualizar e remover tarefas por meio de endpoints HTTP.

## Tecnologias

- Java 25
- Spring Boot 4.1.1
- Spring Web MVC
- Maven Wrapper
- H2 e Spring Data JPA disponíveis no projeto

## Como executar

No Windows, abra o terminal na raiz do projeto e execute:

```powershell
$env:JAVA_HOME = "C:\.jdks\openjdk-25"
.\mvnw.cmd spring-boot:run
```

A aplicação será iniciada em:

```text
http://localhost:8081
```

Para compilar o projeto e os testes:

```powershell
.\mvnw.cmd clean test-compile
```

Para executar os testes:

```powershell
.\mvnw.cmd test
```

## Estrutura principal

```text
src/main/java/com/example/TaskManager/
├── controller/TaskController.java       # Endpoints HTTP
├── model/Task.java                      # Modelo de tarefa
├── model/PostTaskRequest.java           # Dados recebidos na criação
├── model/UpdateTaskRequest.java         # Dados recebidos na atualização
└── service/TaskService.java             # Regras e lista de tarefas
```

## Funcionalidades aplicadas

### Tarefas em memória

A aplicação começa com três tarefas padrão:

- Task 1
- Task 2
- Task 3

As tarefas são armazenadas em uma lista dentro do `TaskService`. O ID da nova tarefa é gerado automaticamente com base no maior ID existente.

Como o armazenamento atual é em memória, os dados adicionados, alterados ou removidos são perdidos quando a aplicação é reiniciada.

### Criar uma tarefa

`POST /tasks`

Request:

```json
{
  "title": "Estudar Spring Boot",
  "description": "Criar uma API REST de tarefas"
}
```

Resultado esperado: HTTP `201 Created`.

```json
{
  "id": 4,
  "title": "Estudar Spring Boot",
  "description": "Criar uma API REST de tarefas",
  "completed": false
}
```

O título e a descrição são obrigatórios. Se algum campo estiver vazio, a API retorna HTTP `400 Bad Request`.

### Listar todas as tarefas

`GET /tasks`

Retorna a lista atual de tarefas:

```json
[
  {
    "id": 1,
    "title": "Task 1",
    "description": "Description 1",
    "completed": false
  }
]
```

### Buscar uma tarefa por ID

`GET /tasks/{id}`

Exemplo:

```text
GET http://localhost:8081/tasks/1
```

Quando a tarefa não existe, a aplicação retorna uma tarefa indicando `Task not found`.

### Atualizar a descrição

`PUT /tasks/{id}`

Request:

```json
{
  "description": "Descrição atualizada"
}
```

Resultado esperado:

```text
Task updated successfully!
```

Se o ID não existir, a API retorna HTTP `404 Not Found`.

### Remover uma tarefa

`DELETE /tasks/{id}`

Exemplo:

```text
DELETE http://localhost:8081/tasks/2
```

Resultado esperado:

```text
Task deleted successfully!
```

Se o ID não existir, a API retorna HTTP `404 Not Found`.

### Verificar se a aplicação está funcionando

`GET /`

Resultado esperado:

```text
Funcionando!
```

## Testando com curl

Criar uma tarefa:

```powershell
curl.exe -X POST http://localhost:8081/tasks `
  -H "Content-Type: application/json" `
  -d '{"title":"Nova tarefa","description":"Tarefa criada pelo cliente"}'
```

Listar tarefas:

```powershell
curl.exe http://localhost:8081/tasks
```

Atualizar uma tarefa:

```powershell
curl.exe -X PUT http://localhost:8081/tasks/1 `
  -H "Content-Type: application/json" `
  -d '{"description":"Descrição modificada"}'
```

Excluir uma tarefa:

```powershell
curl.exe -X DELETE http://localhost:8081/tasks/1
```

## Resultado atual

O projeto já possui um fluxo funcional para manipular tarefas por HTTP:

1. O cliente envia uma requisição `POST` com título e descrição.
2. O controller recebe o JSON através de `PostTaskRequest`.
3. O service valida os campos, gera o próximo ID e adiciona a tarefa em `tasks` usando `tasks.add(task)`.
4. A nova tarefa é retornada com status `201 Created`.
5. Os endpoints de consulta, alteração e exclusão operam sobre a mesma lista em memória.

## Próximos passos sugeridos

- Persistir as tarefas no banco H2 usando JPA, em vez de manter os dados somente em memória.
- Adicionar validações com `@NotBlank` nos DTOs e tratamento global de erros.
- Criar testes unitários para `TaskService` e testes de integração para `TaskController`.
- Adicionar uma operação para marcar tarefas como concluídas.
