Feature: получение паспорта по серийному номеру 1678756113

  Scenario: пользователь посылает запрос с серийным номером, чтобы получить паспорт
    Given в базе данных есть паспорт с серийным номером 1678756113
    When пользователь посылает запрос GET /api/passport/ 1678756113
    Then получает код состояния 200
    And получает паспорт с серийным номером 1678756113
