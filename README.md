# task_library
Приложение системы ввода и отображения информации об авторах книг посредством REST  API (Java spring)
1. Автор
    1. Имя
    2. Фамилия
    3. Отчество
    4. Дата рождения
    5. Книги
2. Книга
    1. ISBN
    2. Жанр
    3. Название книги
3. Жанр
    1. Название
    2. Id жанра

В приложении реализовано: CRUD операции, Liquibase database, WebSecurity auntification, Swagger.
Для запуска проекта нужно изменить ссылку для базы данных Postgres и указать нужный порт. в [application.properties](https://github.com/flesh22812/task_library/blob/main/src/main/resources/application.properties)  
После запуска проекта перейти на http://localhost:8080/ и зарегестрироваться. Для получения полного доступа при регистрации в поле First name нужно указать **_admin_** 
