# API-EncurtadorURLs
Como Executar a Aplicação de Encurtador de URL
Pré-requisitos
Java Development Kit (JDK) - Certifique-se de que o JDK está instalado e configurado no seu sistema. A versão recomendada é o JDK 17.
Maven - A ferramenta de gerenciamento de projetos Maven deve estar instalada.
Spring Boot - A aplicação está configurada para usar o Spring Boot.
Banco de Dados - Configure um banco de dados compatível com JPA (MySQL).
Passos para Executar a Aplicação
Clone o repositório do projeto:

sh
Copiar código
git clone https://github.com/LaysDiasSantos/API-EncurtadorURLs.git
cd API-EncurtadorURLs

Configure o arquivo application.properties:
No diretório src/main/resources, edite o arquivo application.properties para configurar a conexão com o banco de dados. Um exemplo para MYSql:

properties
spring.application.name=API_EncurtadorDeUrl
## Database Properties
server.port = 8080
spring.datasource.url= jdbc:mysql://localhost:3306/dbencurtadorurl?useSSL=false&useTimezone=true&serverTimezone=UTC
spring.datasource.username = username
spring.datasource.password = password
## Hibernate Properties
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

sh

mvn spring-boot:run
A aplicação estará disponível no endereço http://localhost:8080.



