# roadmap-todo
Inspired by https://roadmap.sh/projects/todo-list-api  
Submitted
https://roadmap.sh/projects/todo-list-api/solutions?u=671f97b531d65c235d3ca1bc

## spec
java 21, kotlin, springboot 3.4.0, resilience4j   

1. [How to build](#how-to-build)
2. [CRUD](#crud)
3. [Rate limiting to prevent abuse of API](#rate-limiting-to-prevent-abuse-of-api)


# How to build
```shell
./gradlew build
```

# CRUD

## Create
<details>
<summary style="font-size: 1.5em;">Register</summary>

### Request body
```shell
curl --location 'localhost:8080/register' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "John Doe",
  "email": "john@doe.com",
  "password": "password"
}'
```

### Response body
```json
{
    "token": "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwZjI0MGQ1Yy0xZDZkLTQ0ZjktYWRkMC0zYTZlODQ1OGRmMzIiLCJpc3MiOiJyb2FkbWFwLXRvZG8iLCJpYXQiOjE3MzMzMTc1OTEsImV4cCI6MTczMzQwMzk5MX0.J7mc8PL9dqcwAKQ4P8OJvjD2uDdEnabVrN_5gisrzPsXHAeR8bOBFasdUbbdc8gnkmDzejj9XeaNbQUEUDgFEBLL9qpsY5vjej74sP-Tj2MkDc7FGv1rzHvDeMC6xT0Ww0VIogrzy_e7jQA07UPzS7veZeshGurQhZkfGftrUlXVoAA6HwYvZ0hr4ViLJfI10HRJDs_XsZYXrOh-2TRuo2OXNI2AvaXYh4JQmDd0gm8cSgYvMNNG5wzOV9_XRLlBbZE-_JljQosyEgYFoZzne1WrNwsWjBrQPYlCyKTATvAaNTcfv0xEkjT-Y__YfwWpK8aC1dTFUR53J_7AJj_VBA"
}
```

</details>

<details>
<summary style="font-size: 1.5em;">Todos</summary>

### Request body
```shell
curl --location 'localhost:8080/todos' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwZjI0MGQ1Yy0xZDZkLTQ0ZjktYWRkMC0zYTZlODQ1OGRmMzIiLCJpc3MiOiJyb2FkbWFwLXRvZG8iLCJpYXQiOjE3MzMzMTc1OTEsImV4cCI6MTczMzQwMzk5MX0.J7mc8PL9dqcwAKQ4P8OJvjD2uDdEnabVrN_5gisrzPsXHAeR8bOBFasdUbbdc8gnkmDzejj9XeaNbQUEUDgFEBLL9qpsY5vjej74sP-Tj2MkDc7FGv1rzHvDeMC6xT0Ww0VIogrzy_e7jQA07UPzS7veZeshGurQhZkfGftrUlXVoAA6HwYvZ0hr4ViLJfI10HRJDs_XsZYXrOh-2TRuo2OXNI2AvaXYh4JQmDd0gm8cSgYvMNNG5wzOV9_XRLlBbZE-_JljQosyEgYFoZzne1WrNwsWjBrQPYlCyKTATvAaNTcfv0xEkjT-Y__YfwWpK8aC1dTFUR53J_7AJj_VBA' \
--header 'Content-Type: application/json' \
--data '{
  "title": "Buy groceries",
  "description": "Buy milk, eggs, and bread"
}'
```

### Response body
```json
{
    "id": 1,
    "title": "Buy groceries",
    "description": "Buy milk, eggs, and bread"
}
```
</details>

## Read
<details>
<summary style="font-size: 1.5em;">Todos</summary>

### Request body
```shell
curl --location 'localhost:8080/todos?page=0&size=10&sort=id%2Cdesc' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwZjI0MGQ1Yy0xZDZkLTQ0ZjktYWRkMC0zYTZlODQ1OGRmMzIiLCJpc3MiOiJyb2FkbWFwLXRvZG8iLCJpYXQiOjE3MzMzMTc1OTEsImV4cCI6MTczMzQwMzk5MX0.J7mc8PL9dqcwAKQ4P8OJvjD2uDdEnabVrN_5gisrzPsXHAeR8bOBFasdUbbdc8gnkmDzejj9XeaNbQUEUDgFEBLL9qpsY5vjej74sP-Tj2MkDc7FGv1rzHvDeMC6xT0Ww0VIogrzy_e7jQA07UPzS7veZeshGurQhZkfGftrUlXVoAA6HwYvZ0hr4ViLJfI10HRJDs_XsZYXrOh-2TRuo2OXNI2AvaXYh4JQmDd0gm8cSgYvMNNG5wzOV9_XRLlBbZE-_JljQosyEgYFoZzne1WrNwsWjBrQPYlCyKTATvAaNTcfv0xEkjT-Y__YfwWpK8aC1dTFUR53J_7AJj_VBA'
```

### Response body
```json
{
    "content": [
        {
            "id": 14,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, and bread"
        },
        {
            "id": 13,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, and bread"
        },
        {
            "id": 12,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, and bread"
        },
        {
            "id": 11,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, and bread"
        },
        {
            "id": 10,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, and bread"
        },
        {
            "id": 9,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, and bread"
        },
        {
            "id": 8,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, and bread"
        },
        {
            "id": 7,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, and bread"
        },
        {
            "id": 6,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, and bread"
        },
        {
            "id": 5,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, and bread"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalPages": 2,
    "totalElements": 14,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 10,
    "empty": false
}
```
</details>

<details>
<summary style="font-size: 1.5em;">Raised rate limiting</summary>

### Error response body
```json
{
    "message": "RateLimiter 'todos-get' does not permit further calls"
}
```
</details>

## Update
<details>
<summary style="font-size: 1.5em;">Todos</summary>

### Request body
```shell
curl --location --request PUT 'localhost:8080/todos/5' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwZjI0MGQ1Yy0xZDZkLTQ0ZjktYWRkMC0zYTZlODQ1OGRmMzIiLCJpc3MiOiJyb2FkbWFwLXRvZG8iLCJpYXQiOjE3MzMzMTc1OTEsImV4cCI6MTczMzQwMzk5MX0.J7mc8PL9dqcwAKQ4P8OJvjD2uDdEnabVrN_5gisrzPsXHAeR8bOBFasdUbbdc8gnkmDzejj9XeaNbQUEUDgFEBLL9qpsY5vjej74sP-Tj2MkDc7FGv1rzHvDeMC6xT0Ww0VIogrzy_e7jQA07UPzS7veZeshGurQhZkfGftrUlXVoAA6HwYvZ0hr4ViLJfI10HRJDs_XsZYXrOh-2TRuo2OXNI2AvaXYh4JQmDd0gm8cSgYvMNNG5wzOV9_XRLlBbZE-_JljQosyEgYFoZzne1WrNwsWjBrQPYlCyKTATvAaNTcfv0xEkjT-Y__YfwWpK8aC1dTFUR53J_7AJj_VBA' \
--header 'Content-Type: application/json' \
--data '{
  "title": "Buy groceries",
  "description": "Buy milk, eggs, bread, and cheese"
}'
```

### Response body
```json
{
    "id": 5,
    "title": "Buy groceries",
    "description": "Buy milk, eggs, bread, and cheese"
}
```
</details>

## Delete
<details>
<summary style="font-size: 1.5em;">Todos</summary>

### Request body
```shell
curl --location --request DELETE 'localhost:8080/todos/2' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwZjI0MGQ1Yy0xZDZkLTQ0ZjktYWRkMC0zYTZlODQ1OGRmMzIiLCJpc3MiOiJyb2FkbWFwLXRvZG8iLCJpYXQiOjE3MzMzMTc1OTEsImV4cCI6MTczMzQwMzk5MX0.J7mc8PL9dqcwAKQ4P8OJvjD2uDdEnabVrN_5gisrzPsXHAeR8bOBFasdUbbdc8gnkmDzejj9XeaNbQUEUDgFEBLL9qpsY5vjej74sP-Tj2MkDc7FGv1rzHvDeMC6xT0Ww0VIogrzy_e7jQA07UPzS7veZeshGurQhZkfGftrUlXVoAA6HwYvZ0hr4ViLJfI10HRJDs_XsZYXrOh-2TRuo2OXNI2AvaXYh4JQmDd0gm8cSgYvMNNG5wzOV9_XRLlBbZE-_JljQosyEgYFoZzne1WrNwsWjBrQPYlCyKTATvAaNTcfv0xEkjT-Y__YfwWpK8aC1dTFUR53J_7AJj_VBA' \
--header 'Content-Type: application/json' \
--data '{
  "title": "Buy groceries",
  "description": "Buy milk, eggs, bread, and cheese"
}'
```

### Response body
_204 No Content_
</details>

# Rate limiting to prevent abuse of API
## Using resilience4j
### config
```yaml
resilience4j:
  ratelimiter:
    instances:
      todos-get:
        limit-refresh-period: 20s
        limit-for-period: 5
        timeout-duration: 2s
```
Allows up to 5 requests in 20 seconds.  
And set maximum time. when request is blocked to 2 seconds

### Controller
```kotlin
@GetMapping
@RateLimiter(name = "todos-get")
fun get(
    pageable: Pageable
): ResponseEntity<Page<TodosResponseDto>> {
    val userId = getCurrentUsername()
    return ResponseEntity.ok()
        .body(todosService.get(userId, pageable))
}
```
Using `@RateLimiter(name = "todos-get")` weathers instance.
