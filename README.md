# Sorteador de Duplas - II Maduro Open de Beach Tênis

## Descrição

O **Sorteador de Duplas** é um sistema auxiliar desenvolvido para o **II Maduro Open de Beach Tênis**, um torneio entre amigos. O sistema tem como principal função realizar o sorteio das duplas inscritas, garantindo um nivelamento justo entre os jogadores, de acordo com suas classificações.

### Regras do Sorteio de Duplas

- Cada jogador é classificado em diferentes níveis ao se cadastrar.
- Jogadores de nível mais alto são pareados com jogadores de nível mais baixo, seguindo a ordem:
  1. "Muito Bom" com "Mediano"
  2. "Bom" com "Mediano"
  3. "Mediano" com "Mediano"
- Esse processo continua até que todos os jogadores tenham sido sorteados em duplas balanceadas.

CRUD de Classificações

O sistema conta com um CRUD para gerenciar as classificações dos jogadores. O administrador pode definir os níveis de classificação, ajustando qual será considerado o nível mais alto e o mais baixo no torneio.

### Sorteio Especial

Além do sorteio de duplas, o sistema também realiza um sorteio simples entre todos os inscritos para definir um vencedor de um prêmio especial no dia do evento.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.2**
- **Spring Security**
- **JPA/Hibernate**
- **MySQL** (conector padrão, mas pode ser adaptado para PostgreSQL)
- **JWT (JSON Web Token)** para autenticação
- **Lombok** para simplificação do código

## Configuração do Projeto

Este projeto utiliza **Maven** como gerenciador de dependências. Para configurá-lo, siga os passos abaixo:

### 1. Clonar o Repositório

```sh
 git clone https://github.com/andrehgustavo/sorteadorDuplasBT.git
 cd sorteadorDuplasBT
```

### 2. Configurar Banco de Dados

O sistema está configurado para utilizar **MySQL**. Para alterá-lo, edite o arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sorteio
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

Se preferir usar **PostgreSQL**, basta adicionar a dependência no `pom.xml` e ajustar a URL:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sorteio
```
### 3. Configure as variáveis de ambiente: 
Crie um arquivo .env na raiz do projeto e adicione as seguintes variáveis:

DB_URL=jdbc:mysql://seu-host:3306/seu-banco
DB_USERNAME=seu-usuario
DB_PASSWORD=sua-senha
JWT_SECRET=suaChaveSecretaSuperSeguraParaJWT

### 4. Rodar a Aplicação

```sh
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## Endpoints Principais

| Método | Endpoint                | Descrição                                  |
| ------ | ----------------------- | ------------------------------------------ |
| GET    | /jogadores              | Lista todos os jogadores cadastrados       |
| POST   | /jogadores              | Cadastra um novo jogador                   |
| GET    | /sorteio/duplas-ativas  | Retorna as duplas sorteadas                |
| POST   | /sorteio/duplas         | Realiza o sorteio de duplas (Apenas Admin) |
| POST   | /sorteio/sortear-brinde | Realiza o sorteio do prêmio                |

## Contato

Criado por **@andrehgustavo** GitHub: [https://github.com/andrehgustavo](https://github.com/andrehgustavo)

