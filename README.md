# Desafio Sicredi: API Assembléia de Votação

Uma solução back-end para gerenciamento de associados, pautas e sessões de votação do sicredi.

## 🛠 O que é necessário para rodar o projeto?

Para conseguir utilizar essa aplicação você precisa dos seguintes itens instalados:

* Java 8
* PostgreeSQL 12.12
* Intellij ou outra IDE de sua preferência

Nesse projeto também foram usadas as seguintes tecnologias:

* Spring Boot - 2.7.4
* JPA
* Hibernate
* Banco de Dados H2 
* Maven 4.0.0
* Swagger 2.9.2
* JUnit 4

Para poder executar o projeto localmente você deve clonar o repositório em uma pasta de sua preferência:

    git clone https://github.com/NapoleaoHerculano/dbc-backend-v1.git
    
Acesse o repositório clonado e o importe com sua IDE. No arquivo application.properties selecione o perfil de execução do projeto, tendo em mente o seguinte:
  
  * Perfil "test" - Utiliza o banco H2;
  
  * Perfil "prod" - Utiliza o banco Postgres.
  
  > NOTA (PERFIL PROD): Lembre-se de criar uma base de dados com o nome igual ao definido no arquivo application-prod.properties.
  
Definido o perfil de execução, basta apenas executar a função contida na classe Application, pela sua IDE.    
    

## :closed_book:Documentação

Foi utilizado o Swagger2 para documentar os recursos oferecidos pela API.

Ao ser executada localmente, a documentação pode ser consultada pelo link: http://localhost:8080/swagger-ui.html
    
A API também está disponível online, hospedada no Heroku. Para consultar a sua documentação e conhecer os endpoints oferecidos, acesse: https://sicredi-rest.herokuapp.com/swagger-ui.html

## :mailbox: Ficou com alguma dúvida?

* Entre em contato comigo pelo e-mail -> [![Gmail Badge](https://img.shields.io/badge/-francivaldo.napoleao@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:francivaldo.napoleao@gmail.com)](francivaldo.napoleao@gmail.com) 

