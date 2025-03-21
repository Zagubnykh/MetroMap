# MetroMap

  
Данная программа собирает данные из разных источников и записывает два JSON-файла.  

Парсинг разных данных происходит в разных классах. 

**Класс парсинга веб-страницы**.   

В нём выполняются следующие действия:  

- получение HTML-кода страницы [«Список станций Московского метрополитена»](https://skillbox-java.github.io) с помощью библиотеки jsoup;  

- парсинг полученной страницы и получение из неё следующих данных:
  + линии московского метро (имя и номер линии);
  + станции московского метро (имя станции и номер линии).


**Класс поиска файлов в папках.**  

В методах этого класса реализован обход папок, лежащих в архиве.     

Метод для обхода папок принимает путь до папки, в которой надо производить поиск.  

**Класс парсинга файлов формата JSON.**   

Принимает JSON-данные и выдает список соответствующих им объектов.  

**Класс парсинга файлов формата CSV.**   

Принимает CSV-данные и выдает список соответствующих им объектов.  

**Класс, в который можно добавлять данные, полученные на предыдущих шагах, и который создаёт и записывает на диск два JSON-файла:**   

- со списком станций по линиям и списком линий;  

- файл stations.json со свойствами станций
