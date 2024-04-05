-- tsx_add_value default 1.1
INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса'), 'Private Person', 'Частное лицо', 'Jismoniy shaxs'),
    ('CREATED', NOW(), function_get_add_group_id('Вид бизнеса'), 'Business', 'Бизнес', 'Biznes');

INSERT INTO tsx_add_value(status, created_date, group_id, name_en, name_ru, name_uz)
VALUES
    ('CREATED', NOW(), function_get_add_group_id('Состояние'), 'Excellent', 'Отличное', 'Ajoyib'),
    ('CREATED', NOW(), function_get_add_group_id('Состояние'), 'Good', 'Хорошее', 'Yaxshi'),
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