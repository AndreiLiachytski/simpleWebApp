
This simple WebApp, which should allow CRUD to perform operations on employees.<br><br>
Contains next layers :<br>
* Controller<br>
simple RestController returning JSON<br>
* Service<br>
there is no business logic in this project, and the service just makes a DAO call<br>
* DAO<br>
based on JDBC Template and BeanPropertyRowMapper  <br>
* DTO<br>
contains two classes full and short Model,<br>
  using Mapstruct for mapping dtos

Swagger documentation is available at the following link<br>
http://localhost:8080/docApi/swagger-ui.html
  

