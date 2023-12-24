# Часто задаваемые вопросы

## Как запустить локально на Windows?

<details>
  <summary>Руководство по использованию локально на Windows</summary>
  
  1. Установите <a href = "https://chromewebstore.google.com/detail/cheerpj-applet-runner/bbmolahhldcbngedljfadjlognfaaein">CheerpJ плагин</a>
  
  ![msedge_P5HepHXSzr](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/b2f387b8-5ac3-45e8-91f2-4ea885dc257c)
  
  2. Скачайте <a href="https://github.com/rfapplets/modern-lab-comp-applets/releases">архив, соответствующий вашей операционной системе</a> 
  
  3. Запустите файл `start_server.exe`, расположенный в корневом каталоге репозитория, и нажмите кнопку `Start`
  
  ![image](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/91cf1bf5-95df-4c27-8351-8b0668bdc51e)
  
  4. Убедитесь, что сервер запущен
  
  ![image](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/4f1cb012-065d-4e4c-9e72-03e877e800a0)
  
  5. Нажмите кнопку `Open URL`
  
  ![image](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/ede82c03-7c8f-43e8-aed3-1dcd345acb26)
  
  6. Теперь вы готовы к работе. Убедитесь, что URL открыт в браузере, в котором вы установили расширение.
  
  ![image](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/d5cba673-c801-4b6b-a8a5-e4191e4a4b2f)
  
  7. Перейдите на вкладку с апплетом и, увидев красный текст, нажмите на иконку расширения cheerpj.

  7.1. Перейти к расширениям.
  ![image](https://github.com/rfapplets/rfapplets.github.io/assets/60469435/e36ea88b-c319-4cb7-ae52-fa338cc3bfa3)

  7.2. Нажать на иконку CheerpJ Applet Runner.
  ![image](https://github.com/rfapplets/rfapplets.github.io/assets/60469435/902c3fbe-f74b-4537-9c3a-f1896dbc4fee)

  7.3. Убедиться в том, что началась загрузка апплета.
  ![image](https://github.com/rfapplets/rfapplets.github.io/assets/60469435/98ff24f8-b383-404e-a12e-7bc028422d4e)
  
  8. По завершении работы нажмите кнопку `Stop` или просто закройте программу.
  
  ![image](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/cd863678-a52b-4153-950b-21112a8e9292)
</details>

## У меня не работает, я все сделал(а) по инструкции, но когда я кликаю на иконку расширения ничего не загружается, почему?

- Очистите кеш браузера, отключите все сторонние расширения на время работы (например, впн может мешать прогрузке),
  перезапустите сервер. Если ничего не помогло, то нужно смотреть в консоль разработчика браузера (обычно F12) и гуглить
  ошибку.

## Могу ли я все делать на linux/macos/freebsd?

- Да, достаточно склонировать [этот](https://github.com/rfapplets/modern-lab-comp-applets/tree/master) репозиторий и
  запустить python скрипт, как в инструкции readme.md.



# Для хакеров


## Где я могу больше узнать о расширении?

- Само решение проприетарное, но есть в открытом
  доступе [документация, где можно найти решение проблем в разделе issues](https://github.com/leaningtech/cheerpj-appletrunner).

## Как скомпилировать питон скрипт?

- Используйте модуль `pyinstaller` или вспомогательный скрипт `compile_to_exe.py` в папке `scripts`.
