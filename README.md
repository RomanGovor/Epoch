# Epoch
1.Запускать приложение по localhost:8080. Вы перейдете на мтраницу приветствия.
#
2.localhost:8080/main - упрощенный rest controller, кнопка добавления(учтена проверка на добавление пустых сообщений или состоящих из одних пробелов).Функция фильтра(поиск по тэгу).Кнопка удалить (пустая).
#
3.localhost:8080/testmessage - CRUD (к сожалению только через командную строку) https://gist.github.com/drucoder/a1d8576e1d15be38aae5bac3f914b874
#
4.При нажатии на кнопку Sign out в main окне перейдете в окно авторизации созданного на основе springboot security
#
5.Добавление Exception Handling(обработка 8 критикал кейсов). Вывод в удобочитаемом виде(Шрифт, разные цвета,текущее время возникновения ошибки, примерное решение ошибки и возраст на страницу авторизации).
#
14.04.20
#
Добавление Swagger и Scheduled Job(Добавление сообщений в базу по расписанию)
#
Подключена база данных Postgresql, добавление контроллера регистрации новых пользователей
24.05.20
#
Добавлены роли пользователей(админ и пользователь), улучшен интерфейс(теперь удобопонятен) при помощи классов BootStrap
#
Добавлено кеширование Ehcache, и транзакция при добавлении сообщений
#
Регистрация новых пользователей сопровождается с привязкой Email почты
