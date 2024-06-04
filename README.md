# 🏥 VitalMed - API de Gerenciamento Hospitalar

Este projeto está sendo desenvolvido para a disciplina de Desenvolvimento Web II, onde o objetivo do grupo é criar uma API de gerenciamento hospitalar. O objetivo é fornecer uma API que permita o gerenciamento de pacientes, médicos, enfermeiros, departamentos, agendamentos de consultas e cirurgias, entre outros recursos hospitalares.

## 🛠️ Tecnologias Utilizadas
<div>
<img align="center" alt="Jv-csharp" height="40" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" /> 
<img align="center" alt="Jv-csharp" height="40" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg" /> 
<img align="center" alt="Jv-csharp" height="40" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mysql/mysql-original-wordmark.svg" />
<img align="center" alt="Jv-csharp" height="40" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/git/git-plain-wordmark.svg" />
<img align="center" alt="Jv-csharp" height="40" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/swagger/swagger-original-wordmark.svg" />
</div>
  

<br>


## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java 8 ou superior
- Maven
- MySQL

### Passos para Execução

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/joaov12/VitalMedAPI.git
   cd VitalMedApi
   ```

2. **Configure o Banco de Dados**

   Crie um banco de dados no MySQL e ajuste as configurações de conexão no arquivo `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/NOME_DO_BD
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. **Compile e Execute a Aplicação**

   Use uma IDE de sua preferência para executar o projeto

4. **Acesse a API**

   A aplicação estará disponível no endereço `http://localhost:8080`.

## 🌐 Endpoints da API (Em desenvolvimento)

Aqui estão alguns dos principais endpoints disponíveis na API:

- **Pacientes**
  - `GET /pacientes`: Lista todos os pacientes
  - `POST /pacientes`: Cria um novo paciente
  - `GET /pacientes/{id}`: Consulta um paciente pelo ID
  - `PUT /pacientes/{id}`: Atualiza os dados de um paciente
  - `DELETE /pacientes/{id}`: Remove um paciente

- **Médicos**
  - `GET /medicos`: Lista todos os médicos
  - `POST /medicos`: Cria um novo médico
  - `GET /medicos/{id}`: Consulta um médico pelo ID
  - `PUT /medicos/{id}`: Atualiza os dados de um médico
  - `DELETE /medicos/{id}`: Remove um médico

- **Consultas**
  - `GET /consultas`: Lista todas as consultas
  - `POST /consultas`: Agenda uma nova consulta
  - `GET /consultas/{id}`: Consulta uma consulta pelo ID
  - `PUT /consultas/{id}`: Atualiza os dados de uma consulta
  - `DELETE /consultas/{id}`: Cancela uma consulta


## 💻 Desenvolvedores
- [Clarice Alves](https://github.com/claricealvs)
- [João Vitor Soares](https://github.com/joaov12)
