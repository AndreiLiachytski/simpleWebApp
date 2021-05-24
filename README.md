
This simple WebApp without UI is based on Spring and Tomcat.<br><br>
It has next layers :<br>
* Controllers<br>
simple RestController returning JSON<br>
* Service<br>
there is no business logic in this task,and the service just makes a DAO call<br>
* DAO<br>
based on JDBC Template and BeanPropertyRowMapper  <br>
* DTO<br>
contains two classes full and short Model<br>

Postman was used instead UI

Swagger<br>
http://localhost:8080/v2/api-docs        return JSON <br>
http://localhost:8080/docApi/swagger-ui.html
  

