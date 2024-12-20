# План автоматизации

#### Объект тестирования

Веб-сервис оплаты путешествия двумя способами:

* дебетовой картой
* выдачей кредита по реквизитам банковской карты

#### Данные тестовых карт

* Номер валидной (APPROVED) карты: 4444 4444 4444 4441
* Номер невалидной (DECLINED) карты: 4444 4444 4444 4442
* Месяц: двузначное число, диапазон от 01 до 12
* Год: двузначное число, диапазон плюс 5 лет от текущего года
* Владелец: латинские буквы
* CVC: трёхзначное число, диапазон 000-999

## Перечень автоматизируемых сценариев

1. Купить. Успешная оплата валидной картой
    * Нажать "Купить"
    * Заполнить данные валидной карты
    * Нажать "Продолжить"
    * Ожидаемый результат: уведомление "Успешно. Операция одобрена Банком".

2. Купить. Отказ в оплате невалидной картой
    * Нажать "Купить"
    * Заполнить данные невалидной карты
    * Нажать "Продолжить"
    * Ожидаемый результат: уведомление "Ошибка! Банк отказал в проведении операции!".

3. Купить. Ошибка оплаты. Поле "Номер карты" пустое, все остальные поля валидные
    * Нажать на кнопку "Купить"
    * Оставить поле "Номер карты" пустым, все остальные заполнить валидными данными
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Поле обязательно для заполнения"
5. Купить. Ошибка оплаты. В поле "Номер карты" один знак
    * Нажать "Купить"
    * Заполнить все поля, кроме номера, валидными данными
    * В поле "Номер карты" ввести невалидный номер из одной цифры: 4
    * Нажать "Продолжить"
    * Ожидаемый результат: Оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "
      Неверный формат"
6. Купить. Ошибка оплаты. В поле "Номер карты" не хватает знаков
    * Нажать "Купить"
    * Заполнить все поля, кроме номера, валидными данными
    * В поле "Номер карты" ввести: 4444 4444 4444 444
    * Нажать "Продолжить"
    * Ожидаемый результат: Оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Неверный формат"

7.  Купить. Ошибка оплаты. Данные карты, которой нет в базе данных
    * Нажать "Купить"
    * Заполнить все поля, кроме номера валидными данными, а поле "Номер карты" номером карты, которого нет в базе данный банка: 1111 1111 1111 1111
    * Нажать "Продолжить"
    * Ожидаемый результат: отправка формы возможна, уведомление "Ошибка! Банк отказал в проведении операции!"
  
9. Купить. Ошибка оплаты. Поле "Месяц" пустое, все остальные поля валидные
    * Нажать "Купить"
    * Заполнить все поля, кроме "Месяц" валидными данными
    * Нажать  "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Поле
      обязательно для заполнения"

10. Купить. Ошибка оплаты. В поле "Месяц" значение 00

    * Нажать "Купить"
    * Заполнить все поля, кроме "Месяц" валидными данными, а поле "Месяц" невалидным значением меньше граничного: 00
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверно указан срок действия карты"

11. Купить. Ошибка оплаты. В поле "Месяц" значение 13

    * Нажать  "Купить"
    * Заполнить все поля, кроме "Месяц" валидными данными, а поле "Месяц" невалидным значением больше граничного: 13
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверно указан срок действия карты"

12. Купить. Ошибка оплаты. В поле "Месяц" один знак
    * Нажать "Купить"
    * Заполнить все поля, кроме "Месяц", валидными данными
    * В поле "Месяц" ввести невалидный номер из одной цифры: 1
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверный формат"

13. Купить. Ошибка оплаты. Карта просрочена на месяц
    * Нажать "Купить"
    * Заполнить все поля, кроме "Месяц", валидными данными
    * В поле "Месяц" ввести прошедший
    * Нажать "Продолжить"
    * Ожидаемый результат: Оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем сообщение "Неверно указан срок действия карты"


14. Купить. Ошибка оплаты. Поле "Год" пустое, все остальные поля валидные

    * Нажать "Купить"
    * Заполнить все поля, кроме "Год" валидными данными, поле "Год" оставить пустым
    * Нажать  "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Год" подчёркнуто красным, под полем сообщение "Поле обязательно для заполнения"

15. Купить. Ошибка оплаты. Карта просрочена на год
    * Нажать "Купить"
    * Заполнить все поля, кроме года, валидными данными
    * В поле "Год" ввести прошлый год: 23
    * Нажать "Продолжить"
    * Ожидаемый результат: Оплата не происходит. Поле "Год" подчёркнуто красным, под полем сообщение "Неверно указан срок действия карты"

16. Купить. Ошибка оплаты. Срок действия больше 5 лет
    * Нажать "Купить"
    * Заполнить все поля, кроме года, валидными данными
    * В поле "Год" ввести текущий +6 лет: 30
    * Нажать "Продолжить"
    * Ожидаемый результат: Оплата не происходит. Поле "Год" подчёркнуто красным, под полем есть сообщение "Неверно указан срок действия карты"


17. Купить. Успешная оплата. Поле "Владелец" заполнено в верхнем регистре
    * Нажать "Купить"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" заполнить латинскими буквами в верхнем регистре: IVANOV IVAN
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата происходит, уведомление "Успешно. Операция одобрена Банком.".

19. Купить. Успешная оплата. Поле "Владелец" заполнено данными через дефис

    * Нажать "Купить"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" заполнить латинскими буквами через дефис: IVANOV-SIDOROV IVAN
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата происходит, уведомление "Успешно. Операция одобрена Банком.".

20. Купить. Ошибка оплаты. Поле "Владелец" пустое
    * Нажать "Купить"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" оставить пустым
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Поле обязательно для заполнения"

21. Купить. Ошибка оплаты. В поле "Владелец" нет имени
    * Нажать "Купить"
    * Заполнить все поля, кроме "Владелец" валидными данными, в поле "Владелец" заполнить только фамилией: IVANOV
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"

22. Купить. Ошибка оплаты. Поле "Владелец" заполнено кириллицей
    * Нажать "Купить"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" буквами на кириллице: ИВАНОВ ИВАН
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный
      формат"


23. Купить. Ошибка оплаты. Поле "Владелец" заполнено цифрами
    * Нажать "Купить"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" заполнить цифрами: 1234 1234
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"


24. Купить. Ошибка оплаты. Поле "Владелец" заполнено спецсимволами
    * Нажать "Купить"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" спецсимволами: !@#$
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"


23. Купить. Ошибка оплаты. Поле "CVC/CVV" пустое

    * Нажать "Купить"
    * Заполнить все поля, кроме "CVC/CVV" валидными данными, поле "Владелец" оставить пустым
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "CVC/CVV" подчёркнуто красным, под полем есть сообщение "Поле обязательно для заполнения"


24. Купить. Ошибка оплаты. В поле "CVC/CVV" один знак.
    * Нажать "Купить"
    * Заполнить все поля, кроме "CVC/CVV", валидными данными
    * В поле "CVC/CVV" ввести одну цифру: 1
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "CVC/CVV" подчёркнуто красным, под полем есть сообщение "Неверный
    формат"

24. Купить. Ошибка оплаты. В поле "CVC/CVV" два знака
    * Нажать "Купить"
    * Заполнить все поля, кроме "CVC/CVV", валидными данными
    * В поле "CVC/CVV" ввести две цифры: 11
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "CVC/CVV" подчёркнуто красным, под полем есть сообщение "Неверный формат"

25. Купить. Ошибка оплаты. Все поля пустые
    * Нажать "Купить"
    * Оставить все поля пустыми
    * Нажать "Продолжить"
    * Ожидаемый результат: все поля подчёркнуты красным, под каждым полем есть сообщение "Поле обязательно для заполнения".


#### Аналогично для оплаты в кредит:

1. Купить в кредит. Успешная оплата валидной картой
    * Нажать "Купить в кредит"
    * Заполнить данные валидной карты:
    * Нажать "Продолжить"
    * Ожидаемый результат: уведомление "Успешно. Операция одобрена Банком.".

2. Купить в кредит. Отказ в оплате невалидной картой
    * Нажать "Купить в кредит"
    * Заполнить данные невалидной карты
    * Нажать "Продолжить"
    * Ожидаемый результат: уведомление "Ошибка! Банк отказал в проведении операции!".

3. Купить в кредит. Ошибка оплаты. Поле "Номер карты" пустое, все остальные поля валидные
    * Нажать на кнопку "Купить в кредит"
    * Оставить поле "Номер карты" пустым, все остальные заполнить валидными данными
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Поле обязательно для заполнения"

4. Купить в кредит. Ошибка оплаты. В поле "Номер карты" один знак
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме номера, валидными данными
    * В поле "Номер карты" ввести невалидный номер из одной цифры: 4
    * Нажать "Продолжить"
    * Ожидаемый результат: Оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Неверный формат"

5. Купить в кредит. Ошибка оплаты. В поле "Номер карты" не хватает знаков
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме номера, валидными данными
    * В поле "Номер карты" ввести: 4444 4444 4444 444
    * Нажать "Продолжить"
    * Ожидаемый результат: Оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Неверный формат"

6. Купить в кредит. Ошибка оплаты. Данные карты, которой нет в базе данных
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме номера валидными данными, а поле "Номер карты" номером карты, которого нет в базе данный банка: 1111 1111 1111 1111
    * Нажать "Продолжить"
    * Ожидаемый результат: отправка формы возможна, уведомление "Ошибка! Банк отказал в проведении операции!"
   ----
7. Купить в кредит. Ошибка оплаты. Поле "Месяц" пустое, все остальные поля валидные
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Месяц" валидными данными
    * Нажать  "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Поле обязательно для заполнения"

8. Купить в кредит. Ошибка оплаты. В поле "Месяц" значение 00
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Месяц" валидными данными, а поле "Месяц" невалидным значением меньше граничного: 00
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверно указан срок действия карты"


10. Купить в кредит. Ошибка оплаты. В поле "Месяц" значение 13
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Месяц" валидными данными, а поле "Месяц" невалидным значением больше граничного: 13
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверно указан срок действия карты"

10. Купить в кредит. Ошибка оплаты. В поле "Месяц" один знак
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Месяц", валидными данными
    * В поле "Месяц" ввести невалидный номер из одной цифры: 1
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверный формат"

11. Купить в кредит. Ошибка оплаты. Карта просрочена на месяц
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Месяц", валидными данными
    * В поле "Месяц" ввести прошедший
    * Нажать "Продолжить"
    * Ожидаемый результат: Оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем сообщение "Неверно указан
      срок действия карты"

----


13. Купить в кредит. Ошибка оплаты. Поле "Год" пустое, все остальные поля валидные
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Год" валидными данными, поле "Год" оставить пустым
    * Нажать  "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Год" подчёркнуто красным, под полем сообщение "Поле обязательно для заполнения"

13. Купить в кредит. Ошибка оплаты. Карта просрочена на год
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме года, валидными данными
    * В поле "Год" ввести прошлый год: 23
    * Нажать "Продолжить"
    * Ожидаемый результат: Оплата не происходит. Поле "Год" подчёркнуто красным, под полем сообщение "Неверно указан срок действия карты"

14. Купить в кредит. Ошибка оплаты. Срок действия больше 5 лет
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме года, валидными данными
    * В поле "Год" ввести текущий +6 лет: 30
    * Нажать "Продолжить"
    * Ожидаемый результат: Оплата не происходит. Поле "Год" подчёркнуто красным, под полем есть сообщение "Неверно указан срок действия карты"

----

16. Купить в кредит. Ошибка оплаты. Поле "Владелец" пустое
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" оставить пустым
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Поле обязательно для заполнения"

----
15. Купить в кредит. Успешная оплата. Поле "Владелец" заполнено в верхнем регистре
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" заполнить латинскими буквами в верхнем регистре: IVANOV IVAN
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата происходит, уведомление "Успешно. Операция одобрена Банком.".

16. Купить в кредит. Успешная оплата. Поле "Владелец" заполнено данными через дефис
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" заполнить латинскими буквами через дефис:IVANOV-SIDOROV IVAN
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата происходит, уведомление "Успешно. Операция одобрена Банком.".

17. Купить в кредит. Ошибка оплаты. Поле "Владелец" пустое
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" оставить пустым
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Поле обязательно для заполнения"

18. Купить в кредит. Ошибка оплаты. В поле "Владелец" нет имени
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Владелец" валидными данными, в поле "Владелец" заполнить только фамилией: IVANOV
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"

20. Купить в кредит. Ошибка оплаты. Поле "Владелец" заполнено цифрами
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" заполнить цифрами: 1234 1234
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"

21. Купить в кредит. Ошибка оплаты. Поле "Владелец" заполнено кириллицей
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" буквами на кириллице: ИВАНОВ ИВАН
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"

20. Купить в кредит. Ошибка оплаты. Поле "Владелец" заполнено цифрами
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" заполнить цифрами: 1234 1234
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"

21. Купить в кредит. Ошибка оплаты. Поле "Владелец" заполнено спецсимволами
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "Владелец" валидными данными, поле "Владелец" спецсимволами: !@#$
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"

---

23. Купить в кредит. Ошибка оплаты. Поле "CVC/CVV" пустое
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "CVC/CVV" валидными данными, поле "Владелец" оставить пустым
    * Нажать на кнопку "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "CVC/CVV" подчёркнуто красным, под полем есть сообщение "Поле обязательно для заполнения"

24. Купить в кредит. Ошибка оплаты. В поле "CVC/CVV" один знак
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "CVC/CVV", валидными данными
    * В поле "CVC/CVV" ввести одну цифру: 1
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "CVC/CVV" подчёркнуто красным, под полем есть сообщение "Неверный формат"

24. Купить в кредит. Ошибка оплаты. В поле "CVC/CVV" два знака
    * Нажать "Купить в кредит"
    * Заполнить все поля, кроме "CVC/CVV", валидными данными
    * В поле "CVC/CVV" ввести две цифры: 11
    * Нажать "Продолжить"
    * Ожидаемый результат: оплата не происходит. Поле "CVC/CVV" подчёркнуто красным, под полем есть сообщение "Неверный формат"
    
25. Купить в кредит. Ошибка оплаты. Все поля пустые
     * Нажать "Купить в кредит"
     * Оставить все поля пустыми
     * Нажать "Продолжить"
     * Ожидаемый результат: все поля подчёркнуты красным, под каждым полем есть сообщение "Поле обязательно для заполнения".
#### Проверка результатов операции приобретения в базе данных
1. Отправка данных валидной карты 4444 4444 4444 4441 (approved) на URL-адрес http://localhost:9999/payment   
   **Ожидаемый результат:** статус операции: APPROVED

2. Отправка данных невалидной карты 4444 4444 4444 4442 (declined) на URL-адрес http://localhost:9999/payment  
   **Ожидаемый результат:** статус операции: DECLINED

3. Отправка данных валидной карты 4444 4444 4444 4441 (approved) на URL-адрес http://localhost:9999/credit  
   **Ожидаемый результат:** статус операции: APPROVED

4. Отправка данных невалидной карты  4444 4444 4444 4442 (declined) на URL-адрес http://localhost:9999/credit  
   **Ожидаемый результат:** статус операции: DECLINED
## Перечень используемых инструментов:

* IntelliJ IDEA - интегрированная среда разработки ПО, поддерживает разные технологии и языки программирования, функции
  для тестирования веб-приложений, включая поддержку Selenium, интеграцию с CI.
* Java 11 - последняя версия объектно-ориентированного язык программирования, который подходит для написания тестов.
* JUnit 5 - библиотека для написания и запуска тестов на языке Java, позволяет использовать несколько процессов
  тестирования одновременно.
* Github - сервис для хранения версий проекта с кодом.
* Docker - платформа, которая позволяет изолировать от операционной системы приложение вместе с его окружением и
  зависимостями.
* MySQL - это система управления базами данных.
* PostgreSQL - свободная объектно-реляционная система управления базами данных с открытым исходным кодом.
* Gradle - система автоматической сборки, упрощает работу с JAVA.
* Selenide - это фреймворк для автоматизированного тестирования веб-приложений на основе Selenium WebDriver, дающий
  преимущества: мощные селекторы, простая конфигурация.
* Faker - это библиотека для генерации случайных данных. С ее помощью можно заполнить таблицы в базе данных.
* DBeaver - программа для работы с базами данных, работает с MySQL и PostgreSQL.
* Allure - фреймворк для создания понятных отчётов выполнения тестов.

## Перечень и описание возможных рисков при автоматизации:

- При неполном покрытии тестами можно пропустить ошибки приложения.
- Неправильная настройка инструментов автоматизации может привести к неверным результатам тестирования.
- Из-за отсутствия документации к приложению могут возникнуть проблемы с интерпретацией ответов приложения.
- Авто-тесты могут быть не адаптированы к изменениям в веб-приложении, что приведёт к ошибкам при дальнейшей разработке
  приложения.

## Интервальная оценка с учётом рисков (в часах):

- Планирование: 14 часов;
- Автоматизация: 20 часов;
- Отчетные документы по итогам тестирования: 7 ч;
- Отчетные документы по итогам автоматизации: 7 ч;

## План сдачи работ:

*Ориентировочные даты:*

- 20.10.2024 - написание авто-тестов.
- 11.11.2024 - подготовка отчетов по результатам тестирования, автоматизации.
