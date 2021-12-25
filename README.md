# test-automation-2021

### 1. Модульное тестирование бэкэнда

В процессе выполнения заданий ознакомиться с созданием параметризованных и динамических тестов с использованием JUnit 5.

Задания:
1. Реализовать простое консольное приложение калькулятор, позволяющее производить следующие арифметические операции в двоичной системе счисления: сложение, вычитание, умножение, деление, сложение по модулю 2.
2. Реализовать несколько параметризованных тестов для проверки каждой арифметической операции. Должно быть, как минимум, по одному тесту, которые принимают в качестве параметров csv и источник данных. Отдельно проверить выбрасывание исключения (assertThrows) при попытке деления на 0.
3. Подготовить yaml или csv файл с данными для тестирования. Реализовать динамические тесты на основе данных из подготовленного файла. У каждого теста должно быть читаемое имя.

### 2. Интеграционное тестирование

В процессе выполнения заданий ознакомиться с фреймворком для управления миграциями баз данных Flyway и фреймворком для интеграционного тестирования Testcontainers.

Задания:
1.	Необходимо спроектировать таблицы для хранения данных паспортов. У одного человека может быть несколько паспортов (внутренний, заграничный). Данные о просроченных и утерянных паспортах также хранятся в системе.
2.	Создать Spring Boot приложение, в котором реализовать следующие конечные точки: 
*   Получение данных о владельце паспорта по его серии и номеру.
*   Получение данных о всех паспортных данных по фамилии, имени, году рождения. Любой из этих параметров может быть не задан. Если не задан ни один параметр – необходимо вывести все паспортные данные.
*   Все DDL операции должны быть оформлены в виде миграций Flyway.
3.  Протестировать интеграционное тестирование приложения с использованием фреймворка Testcontainers. Миграции с тестовыми данными (20-30 записей) должны накатываться при запуске тестов (для каждого теста отдельно или перед запуском всех тестов).

### 3. Модульное тестирование

В процессе выполнения заданий ознакомиться с фреймворком DBUnit. Изучить основы модульного тестирования баз данных.

Задания:
Расширить функциональность программы из второй лабораторной работы:
*   написать триггер, который удалит все данные о паспортах, при удалении человека из базы данных;
*   добавить функционал удаления людей;
*   написать хранимую процедуру, которая сохранит в представлении все данные о действительных паспортах;
*   написать тест, которые проверит правильность работы триггера;
*   написать тест, который проверит правильность выполнения процедуры.

### 4. Разработка через поведение

В процессе выполнения заданий познакомиться с языком Gherkin и с фреймворком Cucumber.

Задание 1. Разработать не менее 3 пользовательских сценария для программы, реализованной в процессе 2 и 3 лабораторных работах.

Задание 2:
1.  Создать docker-compose.yaml файл, в нём описать образ СУБД PostgreSSL, которую необходимо развернуть. Для пароля, логина, порта использовать переменные среды.
2.  Создать .bat файл для запуска Docker-контейнера. Перед запуском Docker-контейнера устанавливаются переменные среды (например, setx POSTGRES_PORT 5432).
3.  Создать .bat файл для остановки Docker-контейнера.
4.  Создать application-test.properties файл, в котором будут прописаны настройки для запуска тестов, которые будут обращаться к базе данных, развернутой в Docker-контейнере.

Задание 3:
1.  Описать созданные пользовательские сценарии в нотации Gherkin.
2.  Перед запуском тестов база данных должна быть пустая.
3.  Реализовать интеграционное тестирование по разработанным пользовательским сценариям с помощью фреймворка Cucumber. После прохода сценария, база данных должна возвращаться в исходное состояние.
