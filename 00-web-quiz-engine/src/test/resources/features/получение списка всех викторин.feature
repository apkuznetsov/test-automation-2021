Feature: получение списка всех викторин

  Scenario: пользователь посылает запрос получения списка всех викторин
    Given в базе данных есть некоторый список викторин
    When пользователь посылает запрос GET /api/quizzes
    Then получает код состояния 200
    And получает тот некоторый список викторин
