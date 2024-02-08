# Итоговая контрольная работа

## Информация о проекте

Необходимо организовать систему учета для питомника в котором живут
домашние и вьючные животные.

## Как сдавать проект

Для сдачи проекта необходимо создать отдельный общедоступный
репозиторий(Github, gitlub, или Bitbucket). Разработку вести в этом
репозитории, использовать пул реквесты на изменения. Программа должна
запускаться и работать, ошибок при выполнении программы быть не должно.

Программа, может использоваться в различных системах, поэтому необходимо
разработать класс в виде конструктора

## Задание

1. Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными (заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека).
```
cat>"Домашние животные"

cat>"Вьючные животные"

cat "Домашние животные" "Вьючные животные" > "Животные"

cat "Животные"

mv "Животные" "Друзья человека"

ll
```

![](/images/1.png)

2. Создать директорию, переместить файл туда.

```
mkdir task2

mv "Друзья человека" task2

cd task2

ll
```

![](/images/2.png)

3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.
```
sudo su

wget -O- https://repo.mysql.com/RPM-GPG-KEY-mysql-2023 | sudo apt-key add -

apt update

apt install mysql-server

sudo mysql

ALTER USER 'root'@'localhost' IDENTIFIED WITH caching_sha2_password BY 'Password_123';

exit
```

4. Установить и удалить deb-пакет с помощью dpkg.

```
wget https://download.virtualbox.org/virtualbox/7.0.12/virtualbox-7.0_7.0.12-159484~Ubuntu~jammy_amd64.deb

sudo nano /etc/apt/sources.list.d/vbox.list

Пишем текст
deb [arch=amd64 signed-by=/usr/share/keyrings/oracle-virtualbox-2016.gpg] https://download.virtualbox.org/virtualbox/debian jammy contrib


wget -O- https://www.virtualbox.org/download/oracle_vbox_2016.asc | sudo gpg --dearmor --yes --output /usr/share/keyrings/oracle-virtualbox-2016.gpg

sudo dpkg -i virtualbox-7.0_7.0.12-159484~Ubuntu~jammy_amd64.deb

sudo apt --fix-broken install

sudo dpkg -i virtualbox-7.0_7.0.12-159484~Ubuntu~jammy_amd64.deb
```

5. Выложить историю команд в терминале ubuntu

    **control.txt**

6. Нарисовать диаграмму, в которой есть класс родительский класс, домашние
животные и вьючные животные, в составы которых в случае домашних
животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные
войдут: Лошади, верблюды и ослы).



7. В подключенном MySQL репозитории создать базу данных “Друзья
человека”
```
create database HUMAN_FRIENDS;
show databases;

+--------------------+
| Database           |
+--------------------+
| HUMAN_FRIENDS      |
| gb                 |
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
```

8. Создать таблицы с иерархией из диаграммы в БД

```
use HUMAN_FRIENDS;

CREATE TABLE animals
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	animal_type VARCHAR(30)
);

CREATE TABLE pets
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	animal_kind VARCHAR(30),
	animal_type_id INT DEFAULT 1,
	FOREIGN KEY (animal_type_id) REFERENCES animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE pack_animals
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	animal_kind VARCHAR(30),
	animal_type_id INT DEFAULT 2,
	FOREIGN KEY (animal_type_id) REFERENCES animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE dogs 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(30), 
    commands VARCHAR(100),
    birthday DATE,
    animal_kind_id INT DEFAULT 1,
    Foreign KEY (animal_kind_id) REFERENCES pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE cats 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(30), 
    commands VARCHAR(100),
    birthday DATE,
    animal_kind_id INT DEFAULT 2,
    Foreign KEY (animal_kind_id) REFERENCES pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE hamsters 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(30), 
    commands VARCHAR(100),
    birthday DATE,
    animal_kind_id INT DEFAULT 3,
    Foreign KEY (animal_kind_id) REFERENCES pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE horses 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(30), 
    commands VARCHAR(100),
    birthday DATE,
    animal_kind_id INT DEFAULT 1,
    Foreign KEY (animal_kind_id) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE camels 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(30), 
    commands VARCHAR(100),
    birthday DATE,
    animal_kind_id INT DEFAULT 2,
    Foreign KEY (animal_kind_id) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE donkeys 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(30), 
    commands VARCHAR(100),
    birthday DATE,
    animal_kind_id INT DEFAULT 3,
    Foreign KEY (animal_kind_id) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

show tables;

+-------------------------+
| Tables_in_HUMAN_FRIENDS |
+-------------------------+
| animals                 |
| camels                  |
| cats                    |
| dogs                    |
| donkeys                 |
| hamsters                |
| horses                  |
| pack_animals            |
| pets                    |
+-------------------------+
```

9. Заполнить низкоуровневые таблицы именами(животных), командами
которые они выполняют и датами рождения
```
INSERT INTO animals (animal_type)
VALUES ('Домашние животные'), ('Вьючные животные');

INSERT INTO pets (animal_kind)
VALUES ('Собаки'), ('Кошки'), ('Хомяки');

INSERT INTO pack_animals (animal_kind)
VALUES ('Лошади'), ('Верблюды'), ('Ослы');

INSERT INTO dogs (name, commands, birthday)
VALUES ('Жучка', 'ко мне, апрот, рядом, сидеть', '2021-04-12'),
('Стрелка', 'дай лапу, голос, сидеть', '2015-01-01'),
('Белка', 'сидеть, стереги, след, ко мне, дай', '2018-09-07');

INSERT INTO cats (name, commands, birthday)
VALUES ('Барсик', 'голос', '2015-07-05'),
('Пушок', 'служи', '2019-04-06'),
('Маркиз', 'спать', '2021-02-05');

INSERT INTO hamsters (name, commands, birthday)
VALUES ('Жора', 'служи', '2020-05-19'),
('Вася', 'служи, кушать, ко мне', '2020-07-20'),
('Пушок', 'ням-ням', '2019-12-27');

INSERT INTO horses (name, commands, birthday)
VALUES ('Марат', 'стой, лечь, пошли', '2018-03-09'),
('Плутон', 'стой, рысь, лечь, пошли, шагом', '2014-10-22'),
('Рысь', 'стой, встать, сесть, тихо, хоп', '2015-06-21');

INSERT INTO camels (name, commands, birthday)
VALUES ('Маркиз', 'стой, лечь, пошли', '2017-10-10'),
('Питон', 'стой, лечь, пошли', '2016-11-11'),
('Макс', 'стой, лечь, пошли', '2015-12-12');

INSERT INTO donkeys (name, commands, birthday)
VALUES ('Ляпа', 'вперед, стоять', '2015-11-12'),
('Серега', 'вперед, стоять', '2022-02-10'),
('Мурка', 'вперед, стоять', '2018-07-29');
```

10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

```
DELETE FROM camels;

CREATE TABLE horses_donkeys SELECT * FROM horses
UNION SELECT * FROM donkeys;
```

11. Создать новую таблицу “молодые животные” в которую попадут все
животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице



12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам.



13. Создать класс с Инкапсуляцией методов и наследованием по диаграмме.
14. Написать программу, имитирующую работу реестра домашних животных.
В программе должен быть реализован следующий функционал:
14. 1. Завести новое животное
14. 2. определять животное в правильный класс
14. 3. увидеть список команд, которое выполняет животное
14. 4. обучить животное новым командам
14. 5. Реализовать навигацию по меню
15. Создайте класс Счетчик, у которого есть метод add(), увеличивающий̆
значение внутренней̆int переменной̆на 1 при нажатие “Завести новое
животное” Сделайте так, чтобы с объектом такого типа можно было работать в
блоке try-with-resources. Нужно бросить исключение, если работа с объектом
типа счетчик была не в ресурсном try и/или ресурс остался открыт. Значение
считать в ресурсе try, если при заведения животного заполнены все поля.