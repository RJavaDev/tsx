-- tsx_add_value default 1.1
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса'), 'Business', 'Бизнес', 'Biznes');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');


INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Вид топлива'), 'Petrol', 'Бензин', 'Benzin'),
    ('CREATED', NOW(), function_get_add_group_id('Вид топлива'), 'Diesel', 'Дизель', 'Dizel'),
    ('CREATED', NOW(), function_get_add_group_id('Вид топлива'), 'Hybrid', 'Гибрид', 'Gibrid'),
    ('CREATED', NOW(), function_get_add_group_id('Вид топлива'), 'Gasoline/LPG', 'Газ/Бензин', 'Gaz/Benzin'),
    ('CREATED', NOW(), function_get_add_group_id('Вид топлива'), 'Electric', 'Электро', 'Elektr'),
    ('CREATED', NOW(), function_get_add_group_id('Вид топлива'), 'Other', 'Другой', 'Boshqa');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Коробка передач'), 'Manual', 'Механическая', 'Mexanik'),
    ('CREATED', NOW(), function_get_add_group_id('Коробка передач'), 'Automatic', 'Автоматическая', 'Avtomatik');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Сезонность'), 'Summer', 'Летние', 'Yozgi'),
    ('CREATED', NOW(), function_get_add_group_id('Сезонность'), 'Winter', 'Зимние', 'Qish'),
    ('CREATED', NOW(), function_get_add_group_id('Сезонность'), 'All-season', 'Всесезонные', 'Hammasi fasl');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Тип дисков'), 'Stamped/Steel', 'Штампованные/Сталные', 'Shampovlangan/Steellik'),
    ('CREATED', NOW(), function_get_add_group_id('Тип дисков'), 'Aluminum/Alloy', 'Алюминиевые/Легкосплавные', 'Alyuminiy/Legkospalv');

-- tsx_add_value default 2.1
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса'), 'Business', 'Бизнес', 'Biznes');
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Питание'), 'Breakfast', 'Завтрак', 'Nonushta'),
    ('CREATED', NOW(), function_get_add_group_id('Питание'), 'Half board', 'Полупансион', 'Yarim pensiya'),
    ('CREATED', NOW(), function_get_add_group_id('Питание'), 'Pension', 'Пансион', 'Pensiya'),
    ('CREATED', NOW(), function_get_add_group_id('Питание'), 'All inclusive', 'Все включено', 'Hammasi tark etilgan'),
    ('CREATED', NOW(), function_get_add_group_id('Питание'), 'Self-catering', 'С собственной кухней', 'O''z oshxonasi bilan');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Меблирован'), 'Yes', 'Да', 'Ha'),
    ('CREATED', NOW(), function_get_add_group_id('Меблирован'), 'No', 'Нет', 'Yo''q');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Ремонт'), 'Author''s project', 'Авторский проект', 'Muallifning proyekti'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт'), 'European-quality renovation', 'Ремонт европейского качества', 'Yevro sifatli ta''mirlash'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт'), 'Average', 'Средний уровень', 'O''rta darajali'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт'), 'Needs renovation', 'Требуется ремонт', 'Ta''mirlash kerak'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт'), 'Rough finish', 'Грубая отделка', 'Qat''iy tashqi ish'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт'), 'Pre-finishing', 'Предчистовая отделка', 'Oldingi tashqi ish');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Комиссионные'), 'Yes', 'Да', 'Ha'),
    ('CREATED', NOW(), function_get_add_group_id('Комиссионные'), 'No', 'Нет', 'Yo''q');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Меблирован 2'), 'Yes', 'Да', 'Ha'),
    ('CREATED', NOW(), function_get_add_group_id('Меблирован 2'), 'No', 'Нет', 'Yo''q');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 2'), 'Author''s project', 'Авторский проект', 'Muallifning proyekti'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 2'), 'European-quality renovation', 'Ремонт европейского качества', 'Yevro sifatli ta''mirlash'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 2'), 'Average', 'Средний уровень', 'O''rta darajali'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 2'), 'Needs renovation', 'Требуется ремонт', 'Ta''mirlash kerak'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 2'), 'Rough finish', 'Грубая отделка', 'Qat''iy tashqi ish'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 2'), 'Pre-finishing', 'Предчистовая отделка', 'Oldingi tashqi ish');


INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Комиссионные 2'), 'Yes', 'Да', 'Ha'),
    ('CREATED', NOW(), function_get_add_group_id('Комиссионные 2'), 'No', 'Нет', 'Yo''q');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Тип дома'), 'House', 'Дом', 'Uy'),
    ('CREATED', NOW(), function_get_add_group_id('Тип дома'), 'Wing', 'Флигель', 'Fligel'),
    ('CREATED', NOW(), function_get_add_group_id('Тип дома'), 'Cottage', 'Коттедж', 'Kottedj'),
    ('CREATED', NOW(), function_get_add_group_id('Тип дома'), 'Part of a house', 'Часть дома', 'Uyning qismi'),
    ('CREATED', NOW(), function_get_add_group_id('Тип дома'), 'Country house', 'Дача', 'Dacha'),
    ('CREATED', NOW(), function_get_add_group_id('Тип дома'), 'Townhouse', 'Таунхаус', 'Taunxaus');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Тип стен'), 'Brick', 'Кирпичный', 'G''ishtli'),
    ('CREATED', NOW(), function_get_add_group_id('Тип стен'), 'Panel', 'Панельный', 'Panel'),
    ('CREATED', NOW(), function_get_add_group_id('Тип стен'), 'Monolithic', 'Монолитный', 'Monolitli'),
    ('CREATED', NOW(), function_get_add_group_id('Тип стен'), 'Slag block', 'Шлакоблочный', 'Shlakoblochniy'),
    ('CREATED', NOW(), function_get_add_group_id('Тип стен'), 'Wooden', 'Деревянный', 'Yog''och'),
    ('CREATED', NOW(), function_get_add_group_id('Тип стен'), 'Gas block', 'Газоблок', 'Gazoblok'),
    ('CREATED', NOW(), function_get_add_group_id('Тип стен'), 'SIP panel', 'СИП панель', 'SIP panel'),
    ('CREATED', NOW(), function_get_add_group_id('Тип стен'), 'Other', 'Другое', 'Boshqa');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Электричество'), 'Available', 'Есть', 'Mavjud'),
    ('CREATED', NOW(), function_get_add_group_id('Электричество'), 'Connection possible', 'Есть возможность подключения', 'Ulashingiz mumkin'),
    ('CREATED', NOW(), function_get_add_group_id('Электричество'), 'Not available', 'Нет', 'Mavjud emas');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Газ'), 'Mainline', 'Магистральный', 'Asosiy'),
    ('CREATED', NOW(), function_get_add_group_id('Газ'), 'Autonomous', 'Автономный', 'Avtomatik'),
    ('CREATED', NOW(), function_get_add_group_id('Газ'), 'Connection possible', 'Есть возможность подключения', 'Ulashingiz mumkin'),
    ('CREATED', NOW(), function_get_add_group_id('Газ'), 'Not available', 'Нет', 'Mavjud emas');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Вода'), 'Central water supply', 'Центральное водоснабжение', 'Markaziy suv ta''minoti'),
    ('CREATED', NOW(), function_get_add_group_id('Вода'), 'Possible connection', 'Возможно подведение', 'Ulashingiz mumkin'),
    ('CREATED', NOW(), function_get_add_group_id('Вода'), 'Well on the plot', 'Колодец на участке', 'Uy maydonida suv chorishtasi'),
    ('CREATED', NOW(), function_get_add_group_id('Вода'), 'At the boundary', 'На границе', 'Chegarada'),
    ('CREATED', NOW(), function_get_add_group_id('Вода'), 'Well', 'Скважина', 'Chorixona'),
    ('CREATED', NOW(), function_get_add_group_id('Вода'), 'Not available', 'Нет', 'Mavjud emas');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Расположение'), 'In the city', 'В городе', 'Shaharda'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение'), 'In the suburbs', 'В пригороде', 'Shahar qishlog''ida'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение'), 'In rural area', 'В сельской местности', 'Qishloq hududi'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение'), 'Along the highway', 'Вдоль трассы', 'Yo''l boyicha'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение'), 'Near a reservoir, river', 'Возле водоема, реки', 'Suv ombori, daryo yaqinida'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение'), 'In the foothills', 'В предгорьях', 'Tepalikda'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение'), 'In the cottage area', 'В дачном массиве', 'Dachalik hududida'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение'), 'In a gated community', 'На закрытой территории', 'Xavfsiz hududida');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Расположение 2'), 'In the city', 'В городе', 'Shaharda'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 2'), 'In the suburbs', 'В пригороде', 'Shahar qishlog''ida'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 2'), 'In rural area', 'В сельской местности', 'Qishloq hududi'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 2'), 'Along the highway', 'Вдоль трассы', 'Yo''l boyicha'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 2'), 'Near a reservoir, river', 'Возле водоема, реки', 'Suv ombori, daryo yaqinida'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 2'), 'In the foothills', 'В предгорьях', 'Tepalikda'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 2'), 'In the cottage area', 'В дачном массиве', 'Dachalik hududida'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 2'), 'In a gated community', 'На закрытой территории', 'Xavfsiz hududida');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Комиссионные 3'), 'Yes', 'Да', 'Ha'),
    ('CREATED', NOW(), function_get_add_group_id('Комиссионные 3'), 'No', 'Нет', 'Yo''q');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Комиссионные 4'), 'Yes', 'Да', 'Ha'),
    ('CREATED', NOW(), function_get_add_group_id('Комиссионные 4'), 'No', 'Нет', 'Yo''q');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Тип участка'), 'Divisible', 'Делимый', 'Bo''linadigan'),
    ('CREATED', NOW(), function_get_add_group_id('Тип участка'), 'Indivisible', 'Неделимый', 'Bo''linmas');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Тип 2'), 'Garage', 'Гараж', 'Garaj'),
    ('CREATED', NOW(), function_get_add_group_id('Тип 2'), 'Parking space', 'Место на паркинге', 'Parking joyi'),
    ('CREATED', NOW(), function_get_add_group_id('Тип 2'), 'Parking spot', 'Место на парковке', 'Parking joyi');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Расположение 3'), 'Administrative building', 'Административное здание', 'Administrativ binolar'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 3'), 'Residential building', 'Жилой дом', 'Yashash uyi'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 3'), 'Cottage', 'Коттедж', 'Kottedj'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 3'), 'Shopping center', 'Торговый центр', 'Savdo markazi'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 3'), 'Industrial zone', 'Промзона', 'Sanoat zonasi'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 3'), 'Market', 'Рынок', 'Bozor'),
    ('CREATED', NOW(), function_get_add_group_id('Расположение 3'), 'Stand-alone buildings', 'Отдельно стоящие здания', 'Muxim binolar');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 3'), 'Author''s project', 'Авторский проект', 'Muallifning proyekti'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 3'), 'European-quality renovation', 'Ремонт европейского качества', 'Yevro sifatli ta''mirlash'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 3'), 'Average', 'Средний уровень', 'O''rta darajali'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 3'), 'Needs renovation', 'Требуется ремонт', 'Ta''mirlash kerak'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 3'), 'Rough finish', 'Грубая отделка', 'Qat''iy tashqi ish'),
    ('CREATED', NOW(), function_get_add_group_id('Ремонт 3'), 'Pre-finishing', 'Предчистовая отделка', 'Oldingi tashqi ish');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Shops/Boutiques', 'Магазины/бутики', 'Do''konlar/Butiklar'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Salons', 'Салоны', 'Salonlar'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Restaurants/Cafes/Bars', 'Рестораны/кафе/бары', 'Restoranlar/kafe/barlar'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Offices', 'Офисы', 'Ofislar'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Warehouses', 'Склады', 'Omborxonalari'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Stand-alone buildings', 'Отдельно стоящие здания', 'Muxim binolar'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Recreation bases', 'Базы отдыха', 'Dam olish bazalari'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Industrial premises', 'Помещения промышленного назначения', 'Sanoat xususiyati bo''lgan xonalar'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Premises for general use', 'Помещения свободного назначения', 'Umumiy foydalanish uchun xonalar'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Small architectural form (MAF)', 'МАФ (малая архитектурная форма)', 'Kichik arxitektura shakli (MAF)'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Part of a building', 'Часть здания', 'Bino qismi'),
    ('CREATED', NOW(), function_get_add_group_id('Тип недвижимости*'), 'Other', 'Другое', 'Boshqa');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Наличие парковки'), 'Yes', 'Да', 'Ha'),
    ('CREATED', NOW(), function_get_add_group_id('Наличие парковки'), 'No', 'Нет', 'Yo''q');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Комиссионные 5'), 'Yes', 'Да', 'Ha'),
    ('CREATED', NOW(), function_get_add_group_id('Комиссионные 5'), 'No', 'Нет', 'Yo''q');
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 3'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 3'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 3'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 3'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 4'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 4'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 4'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 4'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 5'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 5'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 5'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 5'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 6'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 6'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 6'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 6'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 7'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 7'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 7'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 7'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 8'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 8'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 8'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 8'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 9'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 9'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 9'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 9'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 10'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 10'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 10'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 10'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 11'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 11'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 11'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 11'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 2'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 2'), 'Business', 'Бизнес', 'Biznes');
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 3'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 3'), 'Business', 'Бизнес', 'Biznes');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 12'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 12'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 12'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 12'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Тип 3'), 'Liquid crystal (LCD)', 'Жидкокристаллические', 'Suyuqlik kristal (LCD)'),
    ('CREATED', NOW(), function_get_add_group_id('Тип 3'), 'Plasma', 'Плазменные', 'Plazma'),
    ('CREATED', NOW(), function_get_add_group_id('Тип 3'), 'Other TVs', 'Прочие телевизоры', 'Boshqa televizorlar');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 13'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 13'), 'Good', 'Хорошее',  'Yaxshi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 13'), 'Average', 'Среднее', 'Oʻrtacha'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 13'), 'Needs Repair', 'Требует ремонта', 'Tuzatishga muhtoj');