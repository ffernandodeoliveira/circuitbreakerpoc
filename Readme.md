# Freight Quote Circuit Breaker

## Configuração:
```
CircuitBreakerConfiguration.java
```

##Executando o request sem chamar a contingência:

```
http://localhost:8080/quotes?url=https://jsonplaceholder.typicode.com/albums
```

##Executando o request chamando a contingência:

```
http://localhost:8080/quotes
```