# Projeto JDBC - Guia Prático

Este projeto apresenta uma implementação completa de acesso a bancos de dados utilizando a API JDBC (Java Database Connectivity). O foco foi explorar os principais recursos da tecnologia, implementando operações CRUD (Create, Read, Update e Delete) com o padrão DAO, além de incluir suporte a transações e boas práticas de programação orientada a objetos.

---

## Tecnologias Utilizadas

* **Java 17**
* **JDBC** para conexão e manipulação de dados
* **MySQL Server** e **MySQL Workbench** para gerenciamento do banco de dados
* 
---

## Objetivos Alcançados

* **Configuração do Ambiente:**

  * Banco de dados `coursejdbc` criado no MySQL Workbench.
  * Driver MySQL Java Connector configurado como User Library no Eclipse.
  * Arquivo `db.properties` criado para armazenar os dados de conexão, incluindo usuário, senha e URL do banco.

* **Estrutura do Projeto:**

  * Exceção personalizada `DbException` implementada para tratamento de erros relacionados ao banco.
  * Classe utilitária `DB` criada com métodos estáticos para abrir e fechar conexões, `ResultSet` e `Statement`.

* **Operações CRUD:**

  * **Inserção de Dados:**

    * Uso de `PreparedStatement` para garantir segurança contra SQL Injection.
    * Recuperação de chaves geradas automaticamente com `Statement.RETURN_GENERATED_KEYS`.
  * **Consulta de Dados:**

    * Queries para buscar vendedores e departamentos, com suporte a filtros como `findById` e `findByDepartment`.
    * Implementação de navegação de registros utilizando `ResultSet`.
  * **Atualização e Exclusão de Dados:**

    * Queries parametrizadas para modificar e excluir registros, tratando exceções de integridade referencial com `DbIntegrityException`.

* **Transações:**

  * Implementação de transações para garantir consistência em operações múltiplas.
  * Controle manual com `setAutoCommit(false)`, `commit()` e `rollback()`.

---

## Padrão DAO

O projeto segue o padrão Data Access Object (DAO), organizando o código para separar a lógica de acesso aos dados da lógica de negócio.

* **Classes e Interfaces Criadas:**

  * `DepartmentDao` e `SellerDao`: interfaces definindo as operações CRUD para cada entidade.
  * `SellerDaoJDBC`: implementação concreta das operações para a entidade `Seller`.
  * `DaoFactory`: fábrica para instanciar objetos DAO sem expor a implementação.

* **Entidades Modeladas:**

  * **Department:** representa o departamento com atributos, métodos getters/setters, `hashCode`, `equals` e `toString`.
  * **Seller:** representa o vendedor com atributos adicionais como `email`, `baseSalary` e `birthDate`. Inclui referência a um objeto `Department`.

---

## Scripts e Consultas SQL

* **Criação da Base de Dados:**

  ```sql
  CREATE DATABASE coursejdbc;
  ```

* **Consultas Implementadas:**

  * **Busca por ID:**

    ```sql
    SELECT seller.*, department.Name as DepName
    FROM seller INNER JOIN department
    ON seller.DepartmentId = department.Id
    WHERE seller.Id = ?;
    ```
  * **Busca por Departamento:**

    ```sql
    SELECT seller.*, department.Name as DepName
    FROM seller INNER JOIN department
    ON seller.DepartmentId = department.Id
    WHERE DepartmentId = ?
    ORDER BY Name;
    ```
  * **Inserção de Dados:**

    ```sql
    INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)
    VALUES (?, ?, ?, ?, ?);
    ```
  * **Atualização de Dados:**

    ```sql
    UPDATE seller
    SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ?
    WHERE Id = ?;
    ```
  * **Exclusão de Dados:**

    ```sql
    DELETE FROM seller
    WHERE Id = ?;
    ```

---

## Testes

Testes foram realizados em uma classe principal para validar as operações CRUD e verificar a consistência das transações.

---

---

### Sobre o Desenvolvedor

Este projeto foi desenvolvido por [Gustavo Pereira Brum](https://www.linkedin.com/in/gustavo-pereira-brum-42671b241/), estudante de Engenharia de Software, entusiasta de Java e apaixonado por desenvolvimento backend.
