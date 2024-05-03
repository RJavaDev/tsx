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

-- tsx_add_value default 4.1
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 1'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 1'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 1'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 1'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 1'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 1'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 1'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 2'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 2'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 2'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 2'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 2'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 2'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 2'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 3'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 3'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 3'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 3'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 3'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 3'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 3'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 4'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 4'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 4'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 4'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 4'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 4'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 4'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 5'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 5'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 5'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 5'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 5'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 5'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 5'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 6'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 6'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 6'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 6'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 6'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 6'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 6'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 7'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 7'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 7'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 7'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 7'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 7'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 7'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 8'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 8'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 8'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 8'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 8'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 8'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 8'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 9'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 9'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 9'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 9'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 9'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 9'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 9'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 10'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 10'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 10'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 10'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 10'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 10'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 10'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 11'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 11'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 11'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 11'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 11'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 11'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 11'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 12'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 12'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 12'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 12'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 12'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 12'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 12'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 13'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 13'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 13'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 13'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 13'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 13'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 13'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 14'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 14'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 14'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 14'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 14'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 14'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 14'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 15'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 15'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 15'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 15'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 15'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 15'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 15'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 16'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 16'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 16'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 16'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 16'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 16'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 16'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 17'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 17'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 17'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 17'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 17'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 17'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 17'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 18'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 18'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 18'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 18'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 18'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 18'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 18'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 19'), 'Full-time job', 'Полная занятость', 'To''liq ish'),
    ('CREATED', NOW(),     function_get_add_group_id('Ish turi* 19'), 'Temporary job', 'Временная занятость', 'Vaqtincha ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 19'), 'Full-time employment', 'Работа на полную ставку', 'To''liq vaqtli ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 19'), 'Part-time employment', 'Неполная занятость', 'Ishsizlik'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 19'), 'Remote work', 'Удалённая работа', 'Masofaviy ish'),
    ('CREATED', NOW(), function_get_add_group_id('Bandlik turi* 19'), 'Online recruiting', 'Рекрутинг онлайн', 'Onlayn ishga qabul qilish'),
    ('CREATED', NOW(), function_get_add_group_id('Taqdim etish shaklini davom ettirish uchun havola 19'), 'http:www.tezsotish.uz', 'http:www.tezsotish.uz', 'http:www.tezsotish.uz');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 3'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 3'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 4'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 4'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 5'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 5'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 6'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 6'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 7'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 7'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 8'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 8'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 9'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 9'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 10'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 10'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 11'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 11'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 12'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 12'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 13'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 13'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 14'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 14'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 15'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 15'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 16'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 16'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 17'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 17'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 18'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 18'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 19'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 19'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 20'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 20'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 21'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 21'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 22'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 22'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 23'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 23'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 24'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 24'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 25'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 25'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 26'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 26'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 27'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 27'), 'Business', 'Бизнес', 'Biznes'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 28'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса 28'), 'Business', 'Бизнес', 'Biznes');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние 14'), 'New', 'Новый', 'Yangi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 14'), 'Used', 'Б/У', 'Ishlatilgan'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 15'), 'New', 'Новый', 'Yangi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 15'), 'Used', 'Б/У', 'Ishlatilgan'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 16'), 'New', 'Новый', 'Yangi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 16'), 'Used', 'Б/У', 'Ishlatilgan'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 17'), 'New', 'Новый', 'Yangi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 17'), 'Used', 'Б/У', 'Ishlatilgan'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 18'), 'New', 'Новый', 'Yangi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 18'), 'Used', 'Б/У', 'Ishlatilgan'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 19'), 'New', 'Новый', 'Yangi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 19'), 'Used', 'Б/У', 'Ishlatilgan'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 20'), 'New', 'Новый', 'Yangi'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние 20'), 'Used', 'Б/У', 'Ishlatilgan');
