# Commands
REST-сервис для хранения данных о спортивных командах.


REST-сервис для хранения данных о спортивных командах.

Структура данных:

Команда
-----------------------------------------
Идентификатор (порядковый номер)

Название команды

Вид спорта

Дата основания

Участники команды
-----------------------------------------
Идентификатор

Идентификатор команды

Фамилия

Имя

Отчество

Дата рождения

Роль/позиция в команде
----------------------------------------

REST-сервис должен предоставлять следующие методы:

- Получить все команды ( localhost:8080/commands )

-- должна быть возможность фильтрации по виду спорта

-- должна быть возможность получить команды за период по дате основания

- Получить всех участников конкретной команды
-- должна быть возможность фильтрации по роли/позиции в команде
- Добавить команду
- Добавить участника команды
- Перевести участника из одной команды в другую

- Изменение данных команды
- Изменение данных участника команды
- Удаление команды
- Удаление участника команды

Сервис при первом запуске самостоятельно создаёт объекты в БД с помощью Liquibase.
Использован PostgreSQL.

Для реализации необходимо использовать Java 8, Maven, Spring Boot, Hibernate, PostgreSQL,
Liquibase.
Исходный код загрузить в GitHub и в описании указать краткую инструкцию по запуску
приложения и описание реализованных методов API.
