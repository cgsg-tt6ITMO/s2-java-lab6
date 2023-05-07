# JAVA, 2nd semester, Laba № 6 [![CodeFactor](https://www.codefactor.io/repository/github/cgsg-tt6itmo/s2-java-lab6/badge)](https://www.codefactor.io/repository/github/cgsg-tt6itmo/s2-java-lab6)

23.04.2023 I got the variant

07.05.2023 It is almost ready... 29 hours spent...

## Как оно работает

Совершите действия именно в этой последовательности:

1. Запустите сервер, потом запустите клиент.

2. В клиенте: введите 'start', далее вводите команды, ответ будет выведен там  же.

Для `execute_script` можете ввести это:
```
execute_script
C:\Z\ITMO\LabaN6\src\files\inp.txt
```

(Вообще путь зависит от расположения скриптов на вашем компе)

3. Если вы отключились Клиентом (ввели 'exit' или просто завершили работу Клиента, не завершая работу Сервера), но хотите подключиться к Серверу ещё раз другим Клиентом, то найдите в классе Client -> метод main этот кусок кода и увеличьте значение port ровно на один.
```
// need to change the number after client disconnection
int port = 6000;
```
Далее запустите Клиент. Если соединиться не получилось, вы получите сообщение об этом красными буквами. Если же всё успешно, вам будет предложено ввести 'start'.

При отключении Клиента от Сервера, на Сервере можно заметить надпись "Возможно, один Клиент отключился..."

## THE TASK, variant № 12556587

![image_2023-04-25_17-49-00](https://user-images.githubusercontent.com/76934492/234315269-4ad03efc-3c65-4d9b-b6f8-87ee5b852d54.png)

