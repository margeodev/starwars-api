# Star Wars Resistence Social Network

## Descrição da API
Api para controle de interações dos rebeldes.

### Tecnologias utilizadas
- Java 1.8;
- Spring Boot 2.1;
- Framework Spring;
- Banco de dados H2;
- Swagger;

### Configurando o projeto

1. Clonar o projeto e importar no eclipse (foi utilizada a ide STS) 
2. Executar o projeto como um Spring boot app

Não é necessário fazer nenhuma configuração relativa a banco de dados.

### Acessando a API
O acesso à API é feito através da URI: <strong>localhost:8080/rebeldes</strong>

### Cobertura de testes

A API pode ser testada através dos endpoints aqui indicados ou pode ser testada usando a biblioteca Swagger, bastando acessar a uri: http://localhost:8080/swagger-ui.html e clicando no link rebelde-resource. A biblioteca Swagger permite que a API seja testada no browser, também foram implementados os testes de integração no pacote: src/test/java e classe StarwarsApiTests.

Observação: Não é possível testar a troca de itens usando o swagger, esse teste pode ser feito usando o postman ou os testes de integração. 

## Requisitos implementados

* **Adicionar rebeldes**

  Um rebelde deve ter um *nome*, *idade*, *gênero*, *localização*(latitude, longitude e nome, na galáxia, da base ao qual faz parte).

  Um rebelde também possui um inventário que deverá ser passado na requisição com os recursos em sua posse.
  
  Modelo do Json:
  
  <code>
   {
    "nome": "Paulswith",
    "idade": 89,
    "genero": "MASCULINO",
    "localizacao": {
        "latitude": 23.417203,
        "longitude": 15.66283,
        "nomeLocalizacao": "Bosthirda"
    },
    "inventario": [
    	"COMIDA",
        "COMIDA",
        "AGUA",
        "AGUA"
    ]}
  </code>  
  
  - Acessada através da URI: <strong>localhost:8080/rebeldes</strong>
  - Método HTTP: <strong>POST</strong>

* **Atualizar localização do rebelde**

  Faz uma atualização parcial no rebelde(apenas a localização). É necessário informar o id do rebelde na uri.
  
  Modelo do Json:
  
  <code>
   { 	"localizacao": { "latitude": 13.416203, "longitude": 15.66283, "nomeLocalizacao": "Tatooine" } }
  </code>
  
  - Acessada através da URI: <strong>localhost:8080/rebeldes/localizacao/1</strong> 
  - Método HTTP: <strong>PATCH</strong>

* **Reportar o rebelde como um traidor**

  Faz uma atualização parcial no rebelde.
  Para reportar um rebelde como traidor basta acessar a seguinte uri informando o id do rebelde traidor.
  
  
  - Acessada através da URI: <strong>localhost:8080/rebeldes/denuncia/3</strong>
  - Método HTTP: <strong>PATCH</strong>

* **Rebeldes não podem Adicionar/Remover itens do seu inventário**

  Seus pertences devem ser declarados quando eles são registrados no sistema. Após isso eles só poderão mudar seu inventário através de negociação com os outros rebeldes.

* **Negociar itens**

  A API permite que sejam feitas negociações entre rebeldes não traidores. As regras de negocio relativas à troca de itens foram respeitadas.

  Ambos os lados deverão oferecer a mesma quantidade de pontos. Por exemplo, 1 arma e 1 água (1 x 4 + 1 x 2) valem 6 comidas (6 x 1) ou 2 munições (2 x 3).

  | Item      | Pontos   |
  |-----------|----------|
  | 1 Arma    | 4 pontos |
  | 1 Munição | 3 pontos |
  | 1 Água    | 2 pontos |
  | 1 Comida  | 1 ponto  |

  Para negociar um ítem deve ser informado os ids dos rebeldes envolvidos na transação e uma lista de itens de cada rebelde com os itens que serão trocados. 

  Modelo do Json:
  
  <code>
   [{ "id": 1, "inventario": ["AGUA"] }, {"id": 3, "inventario": ["COMIDA", "COMIDA"] }]
  </code>
  
  - Acessada através da URI: <strong>localhost:8080/rebeldes</strong> 
  - Método HTTP: <strong>PUT</strong>
  
 * **Excluir rebeldes**
 
    É possível excluir rebeldes da API. É necessário informar o id do rebelde na uri.
  
  - Acessada através da URI: <strong>localhost:8080/rebeldes/6</strong> 
  - Método HTTP: <strong>DELETE</strong>
  
* **Relatórios**

  A API exibe os seguintes relatórios:

  1. Porcentagem de traidores.
  2. Porcentagem de rebeldes.
  3. Quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde).
  4. Pontos perdidos devido a traidores.
  
  Observação: Para o cálculo da quantidade média dos itens por rebelde não foram considerados os itens dos traidores.
  Para acessar aos relatórios da API é necessário entrar na sequinte uri:

  - URI: <strong>localhost:8080/rebeldes/dashboard</strong> 
  - Método HTTP: <strong>GET</strong>
 

