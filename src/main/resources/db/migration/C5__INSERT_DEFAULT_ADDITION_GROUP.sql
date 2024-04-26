-- tsx_add_group default 1.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of business', 'Вид бизнеса', 'Biznes turi', function_getid('Транспорт'), 0);

INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'State', 'Состояние', 'Holati', function_getid('Транспорт'), 0);

INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Model', 'Модель', 'Model', function_getid('Легковые автомобили'), 3);

INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Year of issue', 'Год выпуска', 'Chiqarilgan yili', function_getid('Легковые автомобили'), 1);

INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Body type', 'Тип кузова', 'Tana turi', function_getid('Легковые автомобили'), 3);

INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Mileage', 'Пробег', 'Kilometr', function_getid('Легковые автомобили'), 2);

INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Transmission', 'Коробка передач', 'Yuqish', function_getid('Легковые автомобили'), 0);

INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Color', 'Цвет', 'Rang', function_getid('Легковые автомобили'), 4);

INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Engine capacity', 'Объем двигателя', 'Dvigatel quvvati', function_getid('Легковые автомобили'), 2);


INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of fuel', 'Вид топлива', 'Yoqilg''i turi', function_getid('Легковые автомобили'), 0);

INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Number of hosts', 'Количество хозяев', 'Xostlar soni', function_getid('Легковые автомобили'), 1);

-- tsx_add_group default 1.2.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of spare part', 'Вид запчасти', 'Ehtiyot qismlar turi', function_getid('Автозапчасти'), 3);

-- tsx_add_group default 1.2.3
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Subcategories', 'Подкатегории', 'Pastki toifalar', function_getid('Автозвук'), 3);

INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Brand_1', 'Марка_1', 'Brend_1', function_getid('Автозвук'), 3);

-- tsx_add_group default 1.2.4
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Brand_2', 'Марка_2', 'Brend_2', function_getid('Транспорт на запчасти'), 3);

-- tsx_add_group default 1.2.5
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Brand_3', 'Марка_3', 'Brend_3', function_getid('GPS-навигаторы_авторегистраторы'), 3);
-- tsx_add_group default 1.3.0
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Diameter (inches)', 'Диаметр (дюймы)', 'Diametri (dyuym)', function_getid('Шины, диски и колёса'), 2);
-- tsx_add_group default 1.3.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Profile width', 'Ширина профиля', 'Profil kengligi', function_getid('Автошины'), 1),
    ('CREATED', NOW(), 'Profile height', 'Высота профиля', 'Profil balandligi', function_getid('Автошины'), 1),
    ('CREATED', NOW(), 'Seasonality', 'Сезонность', 'Mavsumiylik', function_getid('Автошины'), 0);

-- tsx_add_group default 1.3.3
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Disc type', 'Тип дисков', 'Disk turi', function_getid('Диски'), 0),
    ('CREATED', NOW(), 'Diameter (inches)', 'Диаметр (дюймы)', 'Diametri (dyuym)', function_getid('Диски'), 2),
    ('CREATED', NOW(), 'Number of mounting holes', 'Количество крепежных отверстий', 'O''rnatish teshiklari soni', function_getid('Диски'), 1);

-- tsx_add_group default 1.3.4
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Number of mounting holes 1', 'Количество крепежных отверстий 1', 'O''rnatish teshiklari soni 1', function_getid('Колесо в сборе'), 1),
    ('CREATED', NOW(), 'Profile width', 'Ширина профиля', 'Profil kengligi', function_getid('Колесо в сборе'), 1),
    ('CREATED', NOW(), 'Profile height 1', 'Высота профиля 1', 'Profil balandligi 1', function_getid('Колесо в сборе'), 1);

-- tsx_add_group default 1.4.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Brand_5', 'Марка_5', 'Brend_5', function_getid('Мопеды / скутеры'), 3),
    ('CREATED', NOW(), 'Mileage 1', 'Пробег 1', 'Kilometr 1', function_getid('Мопеды / скутеры'), 2),
    ('CREATED', NOW(), 'Year of issue 1', 'Год выпуска 1', 'Chiqarilgan yili 1', function_getid('Мопеды / скутеры'), 1),
    ('CREATED', NOW(), 'Engine capacity 1', 'Объем двигателя 1', 'Dvigatel quvvati 1', function_getid('Мопеды / скутеры'), 2);


-- tsx_add_group default 1.4.2
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Brand_6', 'Марка_6', 'Brend_6', function_getid('Квадроциклы'), 3),
    ('CREATED', NOW(), 'Brand_7', 'Марка_7', 'Brend_7', function_getid('Мотоциклы'), 3),
    ('CREATED', NOW(), 'Mileage 2', 'Пробег 2', 'Kilometr 2', function_getid('Мотоциклы'), 2),
    ('CREATED', NOW(), 'Year of issue 2', 'Год выпуска 2', 'Chiqarilgan yili 2', function_getid('Мотоциклы'), 1),
    ('CREATED', NOW(), 'Engine capacity 2', 'Объем двигателя 2', 'Dvigatel quvvati 2', function_getid('Мотоциклы'), 2),
    ('CREATED', NOW(), 'Type', 'Тип', 'Turi', function_getid('Мотоциклы'), 2);

-- tsx_add_group default 1.5.2
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Product type', 'Вид товара', 'Mahsulot turi', function_getid('Мотоэкипировка'), 3);

-- tsx_add_group default 1.7.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Brand_8', 'Марка_8', 'Brend_8', function_getid('Автобусы'), 3);

-- tsx_add_group default 1.8.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Brand_9', 'Марка_9', 'Brend_9', function_getid('Грузовые автомобили'), 3),
    ('CREATED', NOW(), 'Body type', 'Тип кузова', 'Tana turi', function_getid('Грузовые автомобили'), 3);

-- tsx_add_group default 2.2.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of business 24', 'Вид бизнеса 24', 'Biznes turi 24', function_getid('Недвижимость'), 0),
    ('CREATED', NOW(), 'State 2', 'Состояние 2', 'Davlat 2', function_getid('Посуточная аренда'), 0),
    ('CREATED', NOW(), 'Number of beds', 'Количества спалных мест', 'To''shaklar soni', function_getid('Посуточная аренда'), 3),
    ('CREATED', NOW(), 'Regulations', 'Парвила', 'Qoidalar', function_getid('Посуточная аренда'), 3),
    ('CREATED', NOW(), 'Facilities', 'Удобства', 'Imkoniyatlar', function_getid('Посуточная аренда'), 3),
    ('CREATED', NOW(), 'Additional benefits', 'Дополнительные преимущества', 'Qo''shimcha imtiyozlar', function_getid('Посуточная аренда'), 3),
    ('CREATED', NOW(), 'Infrastructure', 'Инфраструктура', 'Infratuzilma', function_getid('Посуточная аренда'), 3),
    ('CREATED', NOW(), 'Питание', 'Питание', 'Oziqlanish', function_getid('Посуточная аренда'), 0);

-- tsx_add_group default  2.2.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Number of rooms', 'Количества комнат', 'Xonalar soni', function_getid('Квартиры'), 1),
    ('CREATED', NOW(), 'Living space', 'Жилая площадь', 'Yashash maydoni', function_getid('Квартиры'), 2),
    ('CREATED', NOW(), 'Total area', 'Общая площадь', 'umumiy maydoni', function_getid('Квартиры'), 2),
    ('CREATED', NOW(), 'Kitchen area', 'Площадь кухни', 'Oshxona maydoni', function_getid('Квартиры'), 2),
    ('CREATED', NOW(), 'Floor', 'Этаж', 'Qavat', function_getid('Квартиры'), 1),
    ('CREATED', NOW(), 'Number of floors of the house', 'Этажность дома', 'Uyning qavatlar soni', function_getid('Квартиры'), 1),
    ('CREATED', NOW(), 'Type of building', 'Тип строения', 'Bino turi', function_getid('Квартиры'), 3),
    ('CREATED', NOW(), 'Layout', 'Планировка', 'Tartib', function_getid('Квартиры'), 3),
    ('CREATED', NOW(), 'Year of construction', 'Год постройки', 'Qurilish yili', function_getid('Квартиры'), 2),
    ('CREATED', NOW(), 'Furnished', 'Меблирован', 'Mebel bilan jihozlangan', function_getid('Квартиры'), 0),
    ('CREATED', NOW(), 'Ceiling height', 'Высота потолков', 'Shift balandligi', function_getid('Квартиры'), 1),
    ('CREATED', NOW(), 'The apartment has', 'В квартире есть', 'Kvartira bor', function_getid('Квартиры'), 3),
    ('CREATED', NOW(), 'There is a nearby', 'Рядом есть', 'Yaqin atrofda bor', function_getid('Квартиры'), 3),
    ('CREATED', NOW(), 'Repair', 'Ремонт', 'Ta''mirlash', function_getid('Квартиры'), 0),
    ('CREATED', NOW(), 'Commission', 'Комиссионные', 'komissiya', function_getid('Квартиры'), 0);

-- tsx_add_group default  2.2.2,2.3
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Housing type', 'Тип жилья', 'Uy-joy turi', function_getid('Продожа'), 3),
    ('CREATED', NOW(), 'Bathroom', 'Санузел', 'Hammom', function_getid('Продожа'), 3),
    ('CREATED', NOW(), 'Bathroom 2', 'Санузел 2', 'Hammom 2', function_getid('Обмен'), 3);

-- tsx_add_group default  2.3.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Number of rooms 2', 'Количества комнат 2', 'Xonalar soni 2', function_getid('Дома'), 1),
    ('CREATED', NOW(), 'Living space 2', 'Жилая площадь 2', 'Yashash maydoni 2', function_getid('Дома'), 2),
    ('CREATED', NOW(), 'Total area 2', 'Общая площадь 2', 'umumiy maydoni 2', function_getid('Дома'), 2),
    ('CREATED', NOW(), 'Floor 2', 'Этаж 2', 'Qavat 2', function_getid('Дома'), 1),
    ('CREATED', NOW(), 'Number of floors of the house 2', 'Этажность дома 2', 'Uyning qavatlar soni 2', function_getid('Дома'), 1),
    ('CREATED', NOW(), 'Year of construction 2', 'Год постройки 2', 'Qurilish yili 2', function_getid('Дома'), 2),
    ('CREATED', NOW(), 'Furnished 2', 'Меблирован 2', 'Mebel bilan jihozlangan 2', function_getid('Дома'), 0),
    ('CREATED', NOW(), 'Ceiling height 2', 'Высота потолков 2', 'Shift balandligi 2', function_getid('Дома'), 1),
    ('CREATED', NOW(), 'There is a nearby 2', 'Рядом есть 2', 'Yaqin atrofda bor 2', function_getid('Дома'), 3),
    ('CREATED', NOW(), 'Repair 2', 'Ремонт 2', 'Ta''mirlash 2', function_getid('Дома'), 0),
    ('CREATED', NOW(), 'Commission 2', 'Комиссионные 2', 'komissiya 2', function_getid('Дома'), 0),
    ('CREATED', NOW(), 'Bathroom 3', 'Санузел 3', 'Hammom 3', function_getid('Продожа'), 3),
    ('CREATED', NOW(), 'Land area', 'Площадь участка', 'Yer maydoni', function_getid('Дома'), 2),
    ('CREATED', NOW(), 'House type', 'Тип дома', 'Uy turi', function_getid('Дома'), 0),
    ('CREATED', NOW(), 'Wall type', 'Тип стен', 'Devor turi', function_getid('Дома'), 0),
    ('CREATED', NOW(), 'Electricity', 'Электричество', 'Elektr', function_getid('Дома'), 0),
    ('CREATED', NOW(), 'Gas', 'Газ', 'Gaz', function_getid('Дома'), 0),
    ('CREATED', NOW(), 'Water', 'Вода', 'Suv', function_getid('Дома'), 0),
    ('CREATED', NOW(), 'In the house/on the plot there is', 'В доме / на участка есть', 'Uyda/uchastkada bor', function_getid('Дома'), 3),
    ('CREATED', NOW(), 'Mood', 'Расположение', 'Kayfiyat', function_getid('Дома'), 3);

-- tsx_add_group default  2.4.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Plaza', 'Площад', 'Plaza', function_getid('Земля'), 0),
    ('CREATED', NOW(), 'Purpose', 'Назначение', 'Maqsad', function_getid('Земля'), 3),
    ('CREATED', NOW(), 'Mood 2', 'Расположение 2', 'Kayfiyat 2', function_getid('Земля'), 0),
    ('CREATED', NOW(), 'Communications', 'Коммуникации', 'Aloqa', function_getid('Земля'), 3),
    ('CREATED', NOW(), 'Commission 3', 'Комиссионные 3', 'komissiya 3', function_getid('Земля'), 0),
    ('CREATED', NOW(), 'Site type', 'Тип участка', 'Sayt turi', function_getid('Продожа'), 0);

-- tsx_add_group default  2.5.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type 2', 'Тип 2', 'Turi 2', function_getid('Гаражи / стоянки'), 0),
    ('CREATED', NOW(), 'Purpose 2', 'Назначение 2', 'Maqsad 2', function_getid('Гаражи / стоянки'), 3),
    ('CREATED', NOW(), 'Parking spaces', 'Машиномест', 'Avtoturargohlar', function_getid('Гаражи / стоянки'), 3),
    ('CREATED', NOW(), 'Square', 'Площадь', 'Kvadrat', function_getid('Гаражи / стоянки'), 2),
    ('CREATED', NOW(), 'Commission 4', 'Комиссионные 4', 'komissiya 4', function_getid('Гаражи / стоянки'), 0);

-- tsx_add_group default  2.6.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Total area 3', 'Общая площадь 3', 'umumiy maydoni 3', function_getid('Коммерческие помешения'), 2),
    ('CREATED', NOW(), 'Mood 3', 'Расположение 3', 'Kayfiyat 3', function_getid('Коммерческие помешения'), 0),
    ('CREATED', NOW(), 'Plot', 'Участок', 'ta’riflar', function_getid('Коммерческие помешения'), 3),
    ('CREATED', NOW(), 'Floor 3', 'Этаж 3', 'Qavat 3', function_getid('Коммерческие помешения'), 1),
    ('CREATED', NOW(), 'Number of floors of the house 3', 'Этажность дома 3', 'Uyning qavatlar soni 3', function_getid('Коммерческие помешения'), 1),
    ('CREATED', NOW(), 'Ceiling height 3', 'Высота потолков 3', 'Shift balandligi 3', function_getid('Коммерческие помешения'), 1),
    ('CREATED', NOW(), 'Repair 3', 'Ремонт 3', 'Ta''mirlash 3', function_getid('Коммерческие помешения'), 0),
    ('CREATED', NOW(), 'Property type*', 'Тип недвижимости*', 'Mulk turi*', function_getid('Коммерческие помешения'), 0),
    ('CREATED', NOW(), 'Indoors there is', 'В помещении есть', 'Ichkarida bor', function_getid('Коммерческие помешения'), 3),
    ('CREATED', NOW(), 'Availability of parking', 'Наличие парковки', 'Avtoturargohning mavjudligi', function_getid('Коммерческие помешения'), 0),
    ('CREATED', NOW(), 'Commission 5', 'Комиссионные 5', 'komissiya 5', function_getid('Коммерческие помешения'), 0),
    ('CREATED', NOW(), 'State 3', 'Состояние 3', 'Holati 3', function_getid('Мебель для гостиной'), 0),
    ('CREATED', NOW(), 'Types of furniture', 'Типы мебели', 'Mebel turlari', function_getid('Мебель для гостиной'), 3),
    ('CREATED', NOW(), 'Types of furniture 2', 'Типы мебели 2', 'Mebel turlari 2', function_getid('Мебель для спальни'), 3),
    ('CREATED', NOW(), 'State 4', 'Состояние 4', 'Holati 4', function_getid('Мебель для спальни'), 0),
    ('CREATED', NOW(), 'State 5', 'Состояние 5', 'Holati 5', function_getid('Мебель для прихожей'), 0),
    ('CREATED', NOW(), 'State 6', 'Состояние 6', 'Holati 6', function_getid('Кухонная мебель'), 0),
    ('CREATED', NOW(), 'State 7', 'Состояние 7', 'Holati 7', function_getid('Мебель для ванной комнаты'), 0),
    ('CREATED', NOW(), 'State 8', 'Состояние 8', 'Holati 8', function_getid('Офисная мебель'), 0),
    ('CREATED', NOW(), 'State 9', 'Состояние 9', 'Holati 9', function_getid('Мебель на заказ'), 0),
    ('CREATED', NOW(), 'State 10', 'Состояние 10', 'Holati 10', function_getid('Садовая мебель'), 0),
    ('CREATED', NOW(), 'State 11', 'Состояние 11', 'Holati 11', function_getid('Специализированная мебель'), 0),
    ('CREATED', NOW(), 'Types of furniture 3', 'Типы мебели 3', 'Mebel turlari 3', function_getid('Офисная мебель'), 3);

-- tsx_add_group default  2.7.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of business 2', 'Вид бизнеса 2', 'Biznes turi 2', function_getid('Транспорт'), 0),
    ('CREATED', NOW(), 'Subcategories 2', 'Подкатегории 2', 'Pastki toifalar 2', function_getid('Декор окон'), 3),
    ('CREATED', NOW(), 'State 12', 'Состояние 12', 'Holati 12', function_getid('Предметы интерьера'), 0);

-- tsx_add_group default  3.1.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of business 3', 'Вид бизнеса 3', 'Biznes turi 3', function_getid('Электроника'), 0),
    ('CREATED', NOW(), 'Subcategories 3', 'Подкатегории 3', 'Pastki toifalar 3', function_getid('Аксессуары и  комплектуюшие'), 3),
    ('CREATED', NOW(), 'Subcategories 4', 'Подкатегории 4', 'Pastki toifalar 4', function_getid('Мобильные телефоны'), 3),
    ('CREATED', NOW(), 'Subcategories 5', 'Подкатегории 5', 'Pastki toifalar 5', function_getid('Стационарные телефоны'), 3),
    ('CREATED', NOW(), 'Screen diagonal', 'Другое', 'Ekran diagonali', function_getid('Ноутбуки'), 3),
    ('CREATED', NOW(), 'Laptop brand*', 'Марка ноутбука*', 'Noutbuk brendi*', function_getid('Ноутбуки'), 3),
    ('CREATED', NOW(), 'Types of Accessories', 'Типы аксесуаров', 'Aksessuarlar turlari', function_getid('Аксессуары2'), 3),
    ('CREATED', NOW(), 'Types of components', 'Типы комплектующих', 'Komponentlar turlari', function_getid('Комплектующие'), 3),
    ('CREATED', NOW(), 'Monitor brand', 'Марка монитора', 'Monitor brendi', function_getid('Мониторы'), 3),
    ('CREATED', NOW(), 'Types of consumables', 'Типы расходников', 'Sarf materiallari turlari', function_getid('Внешние накопители'), 3),
    ('CREATED', NOW(), 'Types of consumables 2', 'Типы расходников 2', 'Sarf materiallari turlari 2', function_getid('Расходные материалы'), 3),
    ('CREATED', NOW(), 'Camera brand', 'Марка фотоапаратаов', 'Kamera brendi', function_getid('Цифровые фотоаппараты'), 3),
    ('CREATED', NOW(), 'Camcorder brand', 'Марка видеокамеры', 'Videokamera brendi', function_getid('Видеокамеры'), 3),
    ('CREATED', NOW(), 'Camcorder brand 2', 'Марка видеокамеры 2', 'Videokamera brendi 2', function_getid('Объективы'), 3),
    ('CREATED', NOW(), 'Types of accessories', 'Виды аксесуаров', 'Aksessuarlar turlari', function_getid('Аксессуары для фото видеокамер'), 3),
    ('CREATED', NOW(), 'Player brand ', 'Марка плеера', 'O''yinchi brendi', function_getid('Медиа проигрыватели'), 3),
    ('CREATED', NOW(), 'Player brand 2', 'Марка плеера 2', 'O''yinchi brendi 2', function_getid('Телевизоры'), 3),
    ('CREATED', NOW(), 'Type 3', 'Тип 3', 'Turi 3', function_getid('Телевизоры'), 0),
    ('CREATED', NOW(), 'Player brand 3', 'Марка плеера 3', 'O''yinchi brendi 3', function_getid('MP3 плееры'), 3),
    ('CREATED', NOW(), 'Music center brand', 'Марка музыкального центра', 'Musiqa markazi brendi', function_getid('Музыкалные центры'), 3),
    ('CREATED', NOW(), 'Brand 10', 'Марка 10', 'Brand 10', function_getid('Усилители / ресиверы'), 3),
    ('CREATED', NOW(), 'Brand 11', 'Марка 11', 'Brand 11', function_getid('Cd / md / виниловые проигрыватели'), 3),
    ('CREATED', NOW(), 'Brand 12', 'Марка 12', 'Brand 12', function_getid('Техника для дома'), 3),
    ('CREATED', NOW(), 'Brand 13', 'Марка 13', 'Brand 13', function_getid('Техника для кухни'), 3),
    ('CREATED', NOW(), 'State 13', 'Состояние 13', 'Holati 13', function_getid('Электроника'), 0);

-- tsx_add_group default  4.1.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа розничная торговля / Продажи'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа розничная торговля / Продажи'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа розничная торговля / Продажи'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа транспорт / логистика'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа транспорт / логистика'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа транспорт / логистика'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа строительство'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа строительство'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа строительство'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа юриспруденция и бухгалтерия'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа юриспруденция и бухгалтерия'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа юриспруденция и бухгалтерия'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа охрана / безопасность'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа охрана / безопасность'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа охрана / безопасность'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа домашний персонал'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа домашний персонал'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа домашний персонал'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа красота / фитнес / спорт'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа красота / фитнес / спорт'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа красота / фитнес / спорт'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа туризм / отдых / развлечения'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа туризм / отдых / развлечения'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа туризм / отдых / развлечения'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа образование'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа образование'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа образование'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа культура / искусство'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа культура / искусство'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа культура / искусство'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа медицина / фармация'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа медицина / фармация'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа медицина / фармация'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа "IТ" / телеком / компьютеры'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа "IТ" / телеком / компьютеры'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа "IТ" / телеком / компьютеры'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа недвижимость'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа недвижимость'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа недвижимость'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа маркетинг / реклама / дизайн'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа маркетинг / реклама / дизайн'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа маркетинг / реклама / дизайн'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа производство / энергетика'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа производство / энергетика'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа производство / энергетика'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа секретариат / АХО'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа секретариат / АХО'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа секретариат / АХО'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа начало карьеры / Студенты'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа начало карьеры / Студенты'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа начало карьеры / Студенты'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа сервис и быт'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа сервис и быт'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа сервис и быт'), 0),
    ('CREATED', NOW(), 'Kind of work*', 'Тип работы*', 'Ish turi*', function_getid('Работа другие сферы занятий'), 0),
    ('CREATED', NOW(), 'Employment type*', 'Тип занятости*', 'Bandlik turi*', function_getid('Работа другие сферы занятий'), 0),
    ('CREATED', NOW(), 'Link to resume submission form', 'Ссылка на форму подачи резюме', 'Taqdim etish shaklini davom ettirish uchun havola', function_getid('Работа другие сферы занятий'), 0),

    ('CREATED', NOW(), 'Type of business 4', 'Вид бизнеса 4', 'Biznes turi 4', function_getid('Услуги строительство / ремонт / уборка'), 0),
    ('CREATED', NOW(), 'Type of business 5', 'Вид бизнеса 5', 'Biznes turi 5', function_getid('Услуги обслуживание, ремонт техники'), 0),
    ('CREATED', NOW(), 'Type of business 6', 'Вид бизнеса 6', 'Biznes turi 6', function_getid('Услуги финансовые услуги / партнерство'), 0),
    ('CREATED', NOW(), 'Type of business 7', 'Вид бизнеса 7', 'Biznes turi 7', function_getid('Услуги перевозки / аренда транспорта'), 0),
    ('CREATED', NOW(), 'Type of business 8', 'Вид бизнеса 8', 'Biznes turi 8', function_getid('Услуги перевозки / аренда транспорта'), 0),
    ('CREATED', NOW(), 'Type of business 9', 'Вид бизнеса 9', 'Biznes turi 9', function_getid('Услуги реклама / полиграфия / маркетинг / интернет'), 0),
    ('CREATED', NOW(), 'Type of business 10', 'Вид бизнеса 10', 'Biznes turi 10', function_getid('Услуги няни / сиделки'), 0),
    ('CREATED', NOW(), 'Type of business 11', 'Вид бизнеса 11', 'Biznes turi 11', function_getid('Услуги сырьё / материалы'), 0),
    ('CREATED', NOW(), 'Type of business 12', 'Вид бизнеса 12', 'Biznes turi 12', function_getid('Услуги красота / здоровье'), 0),
    ('CREATED', NOW(), 'Type of business 13', 'Вид бизнеса 13', 'Biznes turi 13', function_getid('Услуги оборудование'), 0),
    ('CREATED', NOW(), 'Type of business 14', 'Вид бизнеса 14', 'Biznes turi 14', function_getid('Услуги образование / Спорт'), 0),
    ('CREATED', NOW(), 'Type of business 15', 'Вид бизнеса 15', 'Biznes turi 15', function_getid('Услуги для животных'), 0),
    ('CREATED', NOW(), 'Type of business 16', 'Вид бизнеса 16', 'Biznes turi 16', function_getid('Услуги продажа бизнеса'), 0),
    ('CREATED', NOW(), 'Type of business 17', 'Вид бизнеса 17', 'Biznes turi 17', function_getid('Услуги развлечения / Искусство / Фото / Видео'), 0),
    ('CREATED', NOW(), 'Type of business 18', 'Вид бизнеса 18', 'Biznes turi 18', function_getid('Услуги туризм'), 0),
    ('CREATED', NOW(), 'Type of business 19', 'Вид бизнеса 19', 'Biznes turi 19', function_getid('Услуги переводчиков / набор текста'), 0),
    ('CREATED', NOW(), 'Type of business 20', 'Вид бизнеса 20', 'Biznes turi 20', function_getid('Авто / мото услуги'), 0),
    ('CREATED', NOW(), 'Type of business 21', 'Вид бизнеса 21', 'Biznes turi 21', function_getid('Юридические услуги'), 0),
    ('CREATED', NOW(), 'Type of business 22', 'Вид бизнеса 22', 'Biznes turi 22', function_getid('Услуги прокат товаров'), 0),
    ('CREATED', NOW(), 'Type of business 23', 'Вид бизнеса 23', 'Biznes turi 23', function_getid('Прочие услуги'), 0),
('CREATED', NOW(), 'Subcategories 6', 'Подкатегории 6', 'Pastki toifalar 6', function_getid('Уборка / Вывоз мусора / Дезинфекция'), 3);

-- tsx_add_group default  5.1.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of business 25', 'Вид бизнеса 25', 'Biznes turi 25', function_getid('Одежда и текстил'), 0),
    ('CREATED', NOW(), 'State 14', 'Состояние 14', 'Holati 14', function_getid('Одежда и текстил'), 0),
    ('CREATED', NOW(), 'Size', 'Размер', 'Hajmi', function_getid('Женская обувь'), 2),
    ('CREATED', NOW(), 'Size 1', 'Размер 1', 'Hajmi 1', function_getid('Обувь для мальчиков'), 2),
    ('CREATED', NOW(), 'Size 2', 'Размер 2', 'Hajmi 2', function_getid('Обувь для девочек'), 2),
    ('CREATED', NOW(), 'Size 3', 'Размер 3', 'Hajmi 3', function_getid('Обувь для малышей'), 2),
    ('CREATED', NOW(), 'Size 4', 'Размер 4', 'Hajmi 4', function_getid('Головные уборы'), 2),
    ('CREATED', NOW(), 'Subcategories 8', 'Подкатегории 8', 'Pastki toifalar 8', function_getid('Ювелирные изделия'), 3),
    ('CREATED', NOW(), 'Subcategories 7', 'Подкатегории 7', 'Pastki toifalar 7', function_getid('Текстиль'), 3);


-- tsx_add_group default  6.1.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of business 25', 'Вид бизнеса 25', 'Biznes turi 25', function_getid('Животные'), 0),
    ('CREATED', NOW(), 'State 15', 'Состояние 15', 'Holati 15', function_getid('Собаки'), 0),
    ('CREATED', NOW(), 'Dog breed*', 'Порода собаки*', 'It zodi', function_getid('Собаки'), 3),
    ('CREATED', NOW(), 'State 16', 'Состояние 16', 'Holati 16', function_getid('Кошки'), 0),
    ('CREATED', NOW(), 'Cat breed*', 'Порода кошки*', 'Mushik zodi', function_getid('Кошки'), 3);

-- tsx_add_group default  7.1.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of business 26', 'Вид бизнеса 26', 'Biznes turi 26', function_getid('Строительства'), 0),
    ('CREATED', NOW(), 'State 16', 'Состояние 16', 'Holati 16', function_getid('Строительства'), 0);

-- tsx_add_group default  8.1.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of business 27', 'Вид бизнеса 27', 'Biznes turi 27', function_getid('Продукты питания'), 0),
('CREATED', NOW(), 'Types', 'Типы ', 'Turlari', function_getid('Продукты питания'), 3);

-- tsx_add_group default  9.1.1
INSERT INTO tsx_add_group(status, created_date, name_en, name_ru, name_uz, category_id, type)
VALUES
    ('CREATED', NOW(), 'Type of business 28', 'Вид бизнеса 28', 'Biznes turi 28', function_getid('Развлечение'), 0),
    ('CREATED', NOW(), 'State 17', 'Состояние 17', 'Holati 17', function_getid('Музыкальные инструменты'), 0),
    ('CREATED', NOW(), 'State 18', 'Состояние 18', 'Holati 18', function_getid('Спорт/отдых'), 0),
    ('CREATED', NOW(), 'State 19', 'Состояние 19', 'Holati 19', function_getid('Книги/журналы'), 0),
    ('CREATED', NOW(), 'State 20', 'Состояние 20', 'Holati 20', function_getid('CD/DVD/пластинки/кассеты'), 0),
    ('CREATED', NOW(), 'Subcategories 9', 'Подкатегории 9', 'Pastki toifalar 9', function_getid('Коллекционирование'), 3),
    ('CREATED', NOW(), 'Brand 14', 'Марка 14', 'Brand 14', function_getid('Гитары'), 3),
    ('CREATED', NOW(), 'Brand 15', 'Марка 15', 'Brand 15', function_getid('Синтезаторы'), 3),
    ('CREATED', NOW(), 'Subcategories 10', 'Подкатегории 10', 'Pastki toifalar 10', function_getid('Студийное оборудование'), 3),
    ('CREATED', NOW(), 'Subcategories 11', 'Подкатегории 11', 'Pastki toifalar 11', function_getid('Вело'), 3),
    ('CREATED', NOW(), 'Subcategories 12', 'Подкатегории 12', 'Pastki toifalar 12', function_getid('Лыжи/сноуборды'), 3),
    ('CREATED', NOW(), 'Subcategories 13', 'Подкатегории 13', 'Pastki toifalar 13', function_getid('Коньки'), 3),
    ('CREATED', NOW(), 'Subcategories 14', 'Подкатегории 14', 'Pastki toifalar 14', function_getid('Атлетика/фитнес'), 3),
    ('CREATED', NOW(), 'Types of tourism', 'Виды туризма', 'Turizm turlari', function_getid('Товары для туризма'), 3);


