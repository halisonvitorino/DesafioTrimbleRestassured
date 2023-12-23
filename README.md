# Testes API Trimble

HTTP Bin (rest)

    HTTP Bin é um serviço simples de requisição e respostas HTTP construído para demonstrar o uso do protocolo HTTP.
    **A documentação (swagger) encontrada em: http://httpbin.org**


## ⚙️ Executando os testes

Dados para realização dos testes: 

GET http://httpbin.org/base64/{value}

Descrição:
    Decodifica uma string codificada em base64.

Critérios de Aceitação:
    Validação do status code.
    Validação da string decodificada na response.
    Você pode usar a string codificada no exemplo abaixo ou usar uma de sua preferência.

Exemplo:
curl -X GET "http://httpbin.org/base64/SXMgaXMgYWxtb3N0IENocmlzdG1hcw==" \
-H "accept: text/html"

--------------------------------------------------------------------------------------------------------------

POST http://httpbin.org/anything

Descrição:
    Recebe um JSON qualquer no corpo da request e responde com o JSON enviado como parte do corpo da response.
    Considere que você está testando um endpoint que irá cadastrar um TODO Item (item de uma lista de tarefas)

Critérios de Aceitação:
    Validação do status code.
    Validação do retorno contendo o valor passado na request.

Exemplo:
curl -X POST "http://httpbin.org/anything" \
-H "accept: application/json" \
-H "Content-Type: application/json" \
-d '{"todo":"New Item", "checked": false}'

--------------------------------------------------------------------------------------------------------------

PUT http://httpbin.org/anything/{id}

Descrição:
    Considere que você está modificando um item de uma lista de tarefas passando o ID na query string.
    Critérios de Aceitação
    Uso do método PUT
    Validação do status code

Exemplo:
curl -X PUT "http://httpbin.org/anything/1" \
-H "accept: application/json" \
-H "Content-Type: application/json" \
-d '{"todo":"Changed Item"}'

--------------------------------------------------------------------------------------------------------------

DELETE http://httpbin.org/anything/{id}

Descrição:
    Considere que você está deletando um item de uma lista de tarefas passando o ID na query string.
    Critérios de Aceitação
    Uso do método DELETE
    Validação do status code

Exemplo:
curl -X DELETE "http://httpbin.org/anything/1" \
-H "accept: application/json"


### 🔩 Ferramentas Utilizadas

* IDE - Intellij
* Postman
    **Testes manuais com validações automatizadas em javascript - Acesso a collection disponibilizada ao avaliador**
* (https://jsonformatter.org/json-to-jsonschema) - Criador de schema
* (https://jsonpathfinder.com) - Localizador de paths em json


## 🛠️ Construído com

* [RestAssured](https://rest-assured.io) - Framework
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [TestNG](https://testng.org/doc/) - Framework
* [JAVA](https://www.java.com/pt-BR/) - Linguagem

## 📌 Versão

Ver. 001

## ✒️ Autores

* **Halison Vitorino** - *Trabalho Inicial* - [Analista de testes](https://github.com/halisonvitorino)

