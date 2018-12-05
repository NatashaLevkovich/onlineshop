use schema onlineshop;

CREATE TABLE USER (ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
EMAIL VARCHAR (40) UNIQUE,
PASSWORD VARCHAR(30),
FIRST_NAME VARCHAR (30),
LAST_NAME VARCHAR (30),
ADDRESS VARCHAR (50),
PHONE_NUMBER CHAR(13),
DISCOUNT DECIMAL (4,2),
STATUS VARCHAR(20));

CREATE TABLE PRODUCT (ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
NAME VARCHAR (255),
PRICE DECIMAL(5,2),
DISCOUNT DECIMAL (2,2),
CATEGORY ENUM('CLOTHES',  'TOYS', 'HYGIENE'),
SUBCATEGORY ENUM('GIRLS', 'BOYS', 'NEWBORN', 'UNDERWEAR'),
IMAGE VARCHAR (255),
MANUFACTURER VARCHAR (255),
MATERIAL VARCHAR (255));

CREATE TABLE PRODUCT_SIZE (ID INT PRIMARY KEY AUTO_INCREMENT,
PRODUCT_ID INT NOT NULL ,
SIZE INT,
QUANTITY INT);

CREATE TABLE `ORDER` (ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
USER_ID INT NOT NULL,
TOTAL_PRICE DECIMAL (10,2),
STATUS ENUM ('NEW', 'IS_FORMED', 'SENT', 'DELIVERED'),
ORDER_DATE DATE NULL);

CREATE TABLE ITEM (ID INT PRIMARY KEY AUTO_INCREMENT,
PRODUCT_ID INT NOT NULL,
PRODUCT_SIZE INT,
ORDER_ID INT NOT NULL,
QUANTITY INT,
DISCOUNT DECIMAL (4,2));

ALTER TABLE ITEM ADD CONSTRAINT PRODUCT_FK FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID) ON DELETE CASCADE;
ALTER TABLE ITEM ADD CONSTRAINT ORDER_FK FOREIGN KEY (ORDER_ID) REFERENCES `ORDER`(ID) ON DELETE CASCADE;
ALTER TABLE `ORDER` ADD CONSTRAINT USER_FK FOREIGN KEY (USER_ID) REFERENCES USER(ID);
ALTER TABLE PRODUCT_SIZE ADD CONSTRAINT PRODUCT_KEY FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID);

SELECT * FROM PRODUCT WHERE SUBCATEGORY = 'GIRLS';


INSERT INTO PRODUCT (NAME, PRICE, DISCOUNT, CATEGORY, SUBCATEGORY, IMAGE) VALUE
('майка',	7,	0.0,	'CLOTHES',	'UNDERWEAR', ''),
('майка белая', 4.25,	0.0,	'CLOTHES', 'UNDERWEAR', ''),
('комплект',	9.99,	0.0,	'CLOTHES',	'UNDERWEAR', 'image/1.jpg'),
('майка для девочки',	4.5,	0.3,	'CLOTHES',	'UNDERWEAR', ''),
('майка для мальчика',	5,	0.3,	'CLOTHES',	'UNDERWEAR', '');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ('пижама с цветами', '5', '15', 'CLOTHES', 'UNDERWEAR');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ('пижама', '7', '18', 'CLOTHES', 'UNDERWEAR');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'пижама для мальчика', '5', '20', 'CLOTHES', 'UNDERWEAR');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'трусы для девочки', '10', '2', 'CLOTHES', 'UNDERWEAR');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'трусы для мальчика', '10', '2', 'CLOTHES', 'UNDERWEAR');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'трусы', '5', '3', 'CLOTHES', 'UNDERWEAR');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'халат тонкий', '5', '20', 'CLOTHES', 'UNDERWEAR');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'халат теплый', '8', '25', 'CLOTHES', 'UNDERWEAR');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'халат для девочки', '5', '23', 'CLOTHES', 'UNDERWEAR');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'халаи для мальчика', '5', '22', 'CLOTHES', 'UNDERWEAR');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'водолазка белая', '15', '12', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'водолазка', '10', '18', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'джемпер', '9', '25', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'джемпер', '7', '27', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'джемпер', '5', '22', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'жилетка', '10', '20', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'комплект (майка и шорты)', '8', '15', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'брюки', '5', '12', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'костюм спортивный', '12', '30', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'легинсы', '10', '9', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'майка', '10', '6', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'майка белая', '10', '5', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'платье красное', '10', '20', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'платье', '10', '25', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'платье в горошек', '10', '30', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'сарфан', '10', '18', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'куртка', '10', '40', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'толстовка', '10', '30', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'юбка короткая', '10', '20', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'юбка длинная', '10', '15', 'CLOTHES', 'GIRLS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'водолазка белая', '10', '12', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'водолазка', '10', '18', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'джемпер', '10', '25', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'джемпер', '10', '27', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'джемпер', '10', '22', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'жилетка', '10', '20', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'комплект (майка и шорты)', '10', '15', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'брюки', '10', '12', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'костюм спортивный', '10', '30', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'майка', '10', '9', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'майка белая', '10', '6', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'куртка', '10', '45', 'CLOTHES', 'BOYS');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'толстовка', '10', '25', 'CLOTHES', 'BOYS');
--
--
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, SUBCATEGORY) VALUES ( 'Конструктор в кор. Город 659 дет.', '2', '50', 'TOYS', null );
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор в кор.Поезд 586 дет.', '2', '45', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор в кор. Ферма 626 дет.', '2', '48', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор в кор. Пит стоп 852 дет.', '2', '55', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор в кор. Музыкальный 130 дет.', '2', '20', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор в кор. Пиратский замок 310 дет.', '2', '30', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор в кор. Пиратский корабль Черная Жемчужина 870 дет.', '2', '45', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор в кор. Пожарная Бригада 774 дет.', '2', '43', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор в кор. Пожарная часть и техника 980 дет.', '2', '60', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор в пак. Строительная Техника  3 вида по-русски', '2', '40', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор для девочек в кор. 1035 деталей', '2', '62', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор для девочек в кор. 305 деталей', '2', '33', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Конструктор разобр. в кор Крейсер 970 дет.', '2', '51', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Доктор в коробке   82см,звук', '2', '42', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Доктор в коробке  свет', '2', '68', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Дом для кукол 132 эл.', '2', '30', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Доктор в чемодане набор', '2', '40', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Волшебное зеркало интерактивное', '2', '35', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Зеркало туалетный столик', '2', '42', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Зеркало туалетный столик 669-010', '2', '53', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Кухня 008-27', '2', '80', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Набор уборка на тележке', '2', '50', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Кухня 008-32', '2', '46', 'TOYS', '');
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Касса в кор. Мой магазин 018', '2', '36', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Касса в кор. с набором', '2', '41', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Касса в кор. с набором', '2', '20', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Кухня в кор.', '2', '30', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Кухня в кор.', '2', '35', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Кухня в кор.  59*49*15см', '2', '39', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Магазин в кор.', '2', '25', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Посуда фарфоровая', '2', '20', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Посуда в пакете кухонный набор', '2', '15', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Пистолет на присосках', '2', '50', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Пистолет с шариками', '2', '46', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Интерактивный Робот Линк', '2', '36', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Микроскоп', '2', '41', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Машина мусоровоз звук,кабина металл', '2', '20', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Набор военного,автомат трещотка', '2', '30', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Сабля', '2', '35', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Инструменты набор в коробке с верстаком', '2', '39', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Инструменты набор в коробке 63 дет. с верстаком', '2', '25', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Набор пиратская крепость', '2', '20', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Набор рыцарский корабль свет звук', '2', '15', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Арбалет со стрелами и лазерным прицелом 35881Н', '2', '25', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Автомат BlazeStorm', '2', '30', 'TOYS', NULL);
-- INSERT INTO `product` ( `NAME`, `QUANTITY`, `PRICE`, `CATEGORY`, `SUBCATEGORY`) VALUES ( 'Автомат на пульках', '2', '20', 'TOYS', NULL);
--
--
-- SELECT * FROM `ORDER` WHERE USER_ID = 5 AND STATUS = "NEW"