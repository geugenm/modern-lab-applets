# Руководство по использованию локально `Windows`

### 1. Установите [расширение](https://chromewebstore.google.com/detail/cheerpj-applet-runner/bbmolahhldcbngedljfadjlognfaaein)

![msedge_P5HepHXSzr](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/b2f387b8-5ac3-45e8-91f2-4ea885dc257c)

### 2. Запустите `start_server.exe` из корневого каталога репозитория и нажмите кнопку `Start`

![image](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/91cf1bf5-95df-4c27-8351-8b0668bdc51e)

### 3. Убедитесь, что сервер запущен

![image](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/4f1cb012-065d-4e4c-9e72-03e877e800a0)

### 4. Нажмите кнопку `Open URL`

![image](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/ede82c03-7c8f-43e8-aed3-1dcd345acb26)

### 5. Теперь вы можете работать. Убедитесь, что URL открыт в браузере, в котором вы установили расширение

![image](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/d5cba673-c801-4b6b-a8a5-e4191e4a4b2f)

### 6. Как только перейдете во вкладку с апплетом и увидите красный текст, то нажмите на иконку расширения cheerpj.

![image](https://github.com/leaningtech/cheerpj-appletrunner/blob/master/media/cheerpj_applet_demo1.gif?raw=true)

### 7. Когда вы закончите, нажмите кнопку `Stop`

![image](https://github.com/rfapplets/modern-lab-comp-applets/assets/60469435/cd863678-a52b-4153-950b-21112a8e9292)

## Часто задаваемые вопросы

#### 1. У меня не работает, я все сделал(а) по инструкции, но когда я кликаю на иконку расширения ничего не загружается, почему?

- Очистите кеш браузера, отключите все сторонние расширения на время работы (например, впн может мешать прогрузке),
  перезапустите сервер. Если ничего не помогло, то нужно смотреть в консоль разработчика браузера (обычно F12) и гуглить
  ошибку.

#### 2. Могу ли я все делать на linux/macos/freebsd?

- Да, достаточно склонировать [этот](https://github.com/rfapplets/modern-lab-comp-applets/tree/master) репозиторий и
  запустить python скрипт, как в инструкции readme.md.

#### 3. Где я могу больше узнать о расширении?

- Само решение проприетарное, но есть в открытом
  доступе [документация, где в случае чего можно найти решение проблем в разделе issues](https://github.com/leaningtech/cheerpj-appletrunner).

#### 4. Кто эти апплеты делал? Где исходники?

- Апплеты были взяты студентами в 2000е с сайта университета [Buffalo](https://www.buffalo.edu/), я их лишь
  прорефакторил. На момент декабря 2023
  уцелело [зеркало](https://www.acsu.buffalo.edu/~wie/applet/applet.old). [Оригинал](http://jas.eng.buffalo.edu/)
  скрыли, на WebArchive смог найти только скомпилированные `.jar` и `.class` файлы.

#### 5. Как скомпилировать питон скрипт?

- Используйте модуль `pyinstaller` или вспомогательный скрипт `compile_to_exe.py` в папке `scripts`.

#### 6. Как превратить этот readme в pdf?

- Используйте [сайт](https://www.markdowntopdf.com/).
