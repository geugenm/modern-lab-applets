# Часто задаваемые вопросы

## Как запустить локально на Windows?

<details>
  <summary>Руководство по использованию локально на Windows</summary>
  
  1. Установите <a href = "https://chromewebstore.google.com/detail/cheerpj-applet-runner/bbmolahhldcbngedljfadjlognfaaein">CheerpJ Applet Runner плагин</a>
  
  ![msedge_P5HepHXSzr](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/b2f387b8-5ac3-45e8-91f2-4ea885dc257c)
  
  2. Скачайте <a href="https://github.com/rfapplets/modern-lab-comp-applets/releases">архив, соответствующий вашей операционной системе</a> 
  
  3. Запустите файл `start_server.exe`, расположенный в корневом каталоге репозитория, и в открывшемся окне нажмите кнопку `Старт`
  
  ![image](https://github.com/geugenm/modern-lab-applets/assets/60469435/d704b6d6-8871-4efa-b747-80151a44c148)
  
  4. Программа автоматически откроет окно браузера. Убедитесь, что URL открыт в браузере, в котором вы установили расширение.
  
  ![image](https://github.com/geugenm/modern-lab-applets/assets/60469435/0a36d60b-2e97-4660-b560-6728d20dd25e)

  5. Перейдите на вкладку с апплетом и, увидев красный текст, нажмите на иконку расширения CheerpJ (в правом верхнем углу браузера -> расширения -> CheerpJ, см. подробнее).
  
  - Перейти к расширениям.
  ![image](https://github.com/rfapplets/rfapplets.github.io/assets/60469435/e36ea88b-c319-4cb7-ae52-fa338cc3bfa3)

  - Нажать на иконку CheerpJ Applet Runner.
  ![image](https://github.com/rfapplets/rfapplets.github.io/assets/60469435/902c3fbe-f74b-4537-9c3a-f1896dbc4fee)
  
  6. После этого начнется загрузка апплета (на месте апплета иконка расширения и надпись `Loading` с анимацией загрузки, см. подробнее).

  ![image](https://github.com/rfapplets/rfapplets.github.io/assets/60469435/98ff24f8-b383-404e-a12e-7bc028422d4e)
  
  7. По завершении работы нажмите кнопку `Стоп` или просто закройте программу.
  
  ![image](https://github.com/geugenm/modern-lab-applets/assets/60469435/92c9e624-b637-40df-baa2-81fa11684240)

</details>



## У меня не работает, я все сделал(а) по инструкции, но когда я кликаю на иконку расширения ничего не загружается, почему?

- Очистите кеш браузера, отключите все сторонние расширения на время работы (например, впн может мешать прогрузке),
  перезапустите сервер. Если ничего не помогло, то нужно смотреть в консоль разработчика браузера (обычно F12) и гуглить
  ошибку.

## Могу ли я все делать на linux/macos/freebsd?

- Да, достаточно в вашей os установить python и выполнить в терминале из директории апплетов команду `python scripts/start_server.py`

## Ошибка 404

- Распакуйте архив и запустите сервер еще раз. Windows удалил файлы временно распакованного архива, если Вы запустили `.exe` файл прямо из архива.



# Для хакеров


## Где я могу больше узнать о расширении?

- Само решение проприетарное, но есть в открытом
  доступе [документация, где можно найти решение проблем в разделе issues](https://github.com/leaningtech/cheerpj-appletrunner).

## Как скомпилировать питон скрипт?

- Используйте модуль `pyinstaller` или вспомогательный скрипт `compile_to_exe.py` в папке `scripts`.
