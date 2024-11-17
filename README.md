## Дипломная работа профессии «Тестировщик ПО»

1. Скачать репозиторий
2. Открыть проект в IntelliJ IDEA

2. Запустить Docker Desktop

3. В терминале IntelliJ IDEA выполнить команду `docker-compose up -d`, дождаться подъема контейнеров (*около 1 минуты*).

4. В терминале IntelliJ IDEA выполнить команду для запуска сервиса:
- для MySQL:
  `java -jar artifacts/aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app`

- для Postgres:
  `java -jar artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app`


5. В build.gradle выбрать адрес БД:


- для MySQL:
  `systemProperty 'datasource', System.getProperty ('datasource', 'jdbc:mysql://localhost:3306/app')`


- для Postgres:
  `systemProperty 'datasource', System.getProperty ('datasource', 'jdbc:postgresql://localhost:5432/app')`


6. В терминале IntelliJ IDEA выполнить команду для запуска тестов:

- для MySQL:
  ` .\gradlew clean test -D datasource=jdbc:mysql://localhost:3306/app -D datasource.user=app -D datasource.password=pass -D website.url=http://localhost:8080 -D app.url=http://localhost:9999`
- для Postgres:
  `.\gradlew clean test -D datasource=jdbc:postgresql://localhost:5432/app -D datasource.user=app -D datasource.password=pass -D website.url=http://localhost:8080 -D app.url=http://localhost:9999`

7. В терминале IntelliJ IDEA выполнить команду для генерации отчета:
   `.\gradlew allureServe `

**После завершения авто-тестов и получения отчета:**
- Завершить обработку отчета сочетанием клавиш `CTRL + C`, в терминале нажать клавишу `Y`, нажать `Enter`.
- Закрыть приложение сочетанием клавиш `CTRL + C` в терминале запуска.
- Остановить работу контейнеров командой `docker-compose down`.