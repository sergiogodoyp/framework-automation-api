@SCRUM-17
Feature: Listar Usuarios

  Background:
    Given url urlBase
    * def userId = 2
    * def schema = read('classpath:apis/schema/userSchema.json')

  @SCRUM-9
  Scenario: Consultar un usuario existente
    And path "/users/" + userId
    And header x-api-key = apiKey
    When method get
    Then status 200
    * print response
    And match response.data.id == 2
    And match response == schema