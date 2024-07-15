<h1 align="center">Fórum Hub Challenge</h1>

<h4 align="center">
<em>Challenge do programa Oracle Next Education</em>
<br>
<em>Alura LATAM x Oracle</em>
</h4>

✅ Requisitos:
-------------------
- JDK 17
- MySQL

Resumo:
-------------------
Esse projeto foi realizado para aprimorar a construção de APIs RESTful, com autenticação via JWT Tokens, utilizando princípios SOLID durante o desenvolvimento. A API fornece um modelo para o backend de um web fórum.

⚙️ Configuração:
-------------------

Abra o terminal e clone o repositório para sua máquina:

    git clone https://github.com/marinhomich/forum-api.git

Configure a url JDBC, o usuário e a senha do seu banco de dados no arquivo:

    /forum-api/src/main/resources/application.properties

> 💡 *É **altamente recomendável** que você utilize as variáveis de ambiente já definidas no arquivo, basta criar variáveis de
> ambiente com os mesmos nomes no seu ambiente de execução e atribuir os valores corretos para acessar o seu banco de dados.*
> 
> *Exemplo:*
> - *DB_HOST=localhost*
> - *DB_USER=root*
> - *DB_PASSWORD=123456*
> - *DB_NAME=seu_banco_de_dados*
> 
> ***Não** é recomendável que você utilize os valores de acesso diretamente no arquivo `application.properties`, a menos*
> *que o scraper esteja hospedado em seu localhost.*

Agora, retorne ao diretório raiz do projeto...

      /forum-api

e instale as dependências utilizando esse comando do Maven Wrapper, que já está contido no projeto. Para Linux e Mac:

      ./mvnw clean compile

No Windows:

      mvnw.cmd clean compile

Execute o projeto com esse comando no Linux e Mac:

      ./mvnw spring-boot:run -q

e no Windows:

      mvnw.cmd spring-boot:run -q

> Pronto! Agora acesse *http://localhost:8080/swagger-ui.html* para a documentação da API.

### Tech Stack

    - Java
    - Spring Boot
    - Spring Web
    - Spring Doc
    - Spring Data JPA
    - Flyway Migrations
    - Lombok
    - Auth0 JWT
    - MySQL