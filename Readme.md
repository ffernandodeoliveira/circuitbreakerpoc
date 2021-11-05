# Freight Quote Circuit Breaker

## Configuração:
```
CircuitBreakerConfiguration.java
```

---

## Executando o request sem chamar a contingência:

```
http://localhost:8080/quotes?url=https://jsonplaceholder.typicode.com/albums
```
### Response
```
Running by contingency = true
```

---

## Executando o request chamando a contingência:

```
http://localhost:8080/quotes
```

### Response
```
Running by contingency = false
```