# API tipo de cambio
El servicio se encarga de calcular el tipo de cambio entre monedas.

### Dependencias
* JVM '17'
* Spring Boot 3.2.1

### Orden de ejecución
1. service-registry
2. ms-authentication
3. ms-exchange-rate
4. ms-exchange-rate-logs
5. ms-exchange-rate-calculate
6. api-gateway

> [!NOTE]  
> Importar en Postman los archivos de la carpeta "_postman", contiene variables de entorno y las solicitudes HTTP

### Arquitectura
![exchange-rate-diagram.drawio.svg](_diagram%2Fexchange-rate-diagram.drawio.svg)