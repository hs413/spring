### 1. Service that will accept HTTP GET requests at http://localhost:8080/greeting

### It will respond with a JSON representation of a greeting, as the following listing shows:

```json
{"id":1," content":"Hello, World!"}
```

### 2. Customize the greeting with an optional name parameter in the query string, as the following listing shows:

```
http://localhost:8080/greeting?name=User
```

### 3. The name parameter value overrides the default value of World and is reflected in the response, as the following listing shows

```json
{"id":1,"content":"Hello, User!"}
```