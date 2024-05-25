--create default attach
INSERT INTO tsx_attach (id, content_type, created_date, origin_name, path, size, type)
VALUES
    ('80e2f6e1-7b3f-4ee7-adca-786fef1aac45', 'image/png', now(), 'medium_news_size_duncan-git-header.png', 'category/',308630, 'png'),
    ('6ffe1f5e-ed80-4977-b489-40fa6fea694f', 'image/png', now(), '7d092ea2c340b2ceac32524fab56158f.png', 'category/',231741, 'png'),
    ('2a28166a-815e-4025-a70c-f8d77514b98d', 'image/png', now(), '7849964466a5e1bb1beb6b6186e4255b.png', 'category/',273717, 'png'),
    ('c743e2f6-c8d9-459a-8429-88e3b3d81371', 'image/png', now(), 'a98d12c5908c3210ba7c79db812b0bce.png', 'category/',581255, 'png'),
    ('f2e13a4e-a3b6-45f4-a9f1-0636b375c5ab', 'image/png', now(), '531bcfdac9797fa6b8ed0b3e184f52be.png', 'category/',572156, 'png'),
    ('c8516ed2-cfad-49a3-9c9f-32f5f5881e9a', 'image/png', now(), '6ef6a46dda03a4471a9acf3c1f63a4a1.png', 'category/',612490, 'png'),
    ('b6b85ff9-d89d-4060-b386-e1c478a04d21', 'image/png', now(), '5365ef97882b8cfee5366757a72be0cc.png', 'category/',133128, 'png'),
    ('7d847954-6702-4c33-8697-1065f0e4182b', 'image/png', now(), '223ab3bab211e729ff69dbd5e0b05c3e.png', 'category/',91288, 'png'),
    ('69c12a4c-35b6-4764-b9fe-e0110880542c', 'image/png', now(), 'b78ab39bc70d80d00422359ab680ec1a.png', 'category/',357811, 'png');

-- create default 1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, attach_id)
VALUES
       ('CREATED', now(), 'Transportation', 'Транспорт', 'Transport', '80e2f6e1-7b3f-4ee7-adca-786fef1aac45'),
       ('CREATED', now(), 'Real estate', 'Недвижимость', 'Ko''chmas mulk', 'c743e2f6-c8d9-459a-8429-88e3b3d81371'),
       ('CREATED', now(), 'Work and services', 'Работа и услуги', 'Ish va xizmatlar', '69c12a4c-35b6-4764-b9fe-e0110880542c'),
       ('CREATED', now(), 'Animals', 'Животные', 'Hayvonlar', '7d847954-6702-4c33-8697-1065f0e4182b'),
       ('CREATED', now(), 'Electronics', 'Электроника', 'Elektronika', 'b6b85ff9-d89d-4060-b386-e1c478a04d21'),
       ('CREATED', now(), 'Clothing and textiles', 'Одежда и текстил', 'Kiyim va to''qimachilik', '6ffe1f5e-ed80-4977-b489-40fa6fea694f'),
       ('CREATED', now(), 'Construction', 'Строительства', 'Qurilish', '2a28166a-815e-4025-a70c-f8d77514b98d'),
       ('CREATED', now(), 'Food', 'Продукты питания', 'Oziq-ovqat maxsulotlari', 'c8516ed2-cfad-49a3-9c9f-32f5f5881e9a'),
       ('CREATED', now(), 'Entertainment', 'Развлечение', 'O''yin-kulgi','f2e13a4e-a3b6-45f4-a9f1-0636b375c5ab');

-- create default 1.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
      ('CREATED', now(), 'Cars', 'Легковые автомобили', 'Avtomobillar', function_getId('Transportation')),
      ('CREATED', now(), 'Auto parts and accessories', 'Автозапчасти и аксессуары','Avto ehtiyot qismlar va aksessuarlar', function_getId('Transportation')),
     ('CREATED', now(), 'Tires, rims and wheels', 'Шины, диски и колёса', 'Shinalar, jantlar va g''ildiraklar', function_getId('Transportation')),
      ('CREATED', now(), 'Moto', 'Moto', 'Moto', function_getId('Transportation')),
      ('CREATED', now(), 'Motorcycle parts and accessories', 'Мотозапчасти и аксессуары', 'Mototsikl qismlari va aksessuarlari', function_getId('Transportation')),
     ('CREATED', now(), 'Spare parts for / agricultural technology', 'Запчасти для / с.х. техники', 'Qishloq xo''jaligi uchun ehtiyot qismlar texnologiya', function_getId('Transportation')),
     ('CREATED', now(), 'Buses', 'Автобусы', 'Avtobuslar', function_getId('Transportation')),
     ('CREATED', now(), 'Trucks', 'Грузовые автомобили', 'Yuk mashinalari', function_getId('Transportation')),
     ('CREATED', now(), 'Trailers', 'Прицепы', 'Treylerlar', function_getId('Transportation')),
     ('CREATED', now(), 'Other spare parts', 'Прочие запчасти', 'Boshqa ehtiyot qismlar', function_getId('Transportation')),
     ('CREATED', now(), 'Agricultural machinery', 'Сельхозтехника', 'Qishloq xo''jaligi texnikasi', function_getId('Transportation')),
     ('CREATED', now(), 'Special equipment', 'Спецтехника', 'Maxsus jihozlar', function_getId('Transportation')),
     ('CREATED', now(), 'Water transport', 'Водный транспорт', 'Suv transporti', function_getId('Transportation')),
     ('CREATED', now(), 'Other transport', 'Другой транспорт', 'Boshqa transport', function_getId('Transportation'));

-- create default 1.1.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
       ('CREATED', now(), 'Chevrolet', 'Chevrolet', 'Chevrolet', function_getid('Cars')),
       ('CREATED', now(), 'Audi', 'Audi', 'Audi',  function_getid('Cars')),
       ('CREATED', now(), 'BMV', 'BMV', 'BMV',  function_getid('Cars')),
       ('CREATED', now(), 'Jac', 'Jac', 'Jac',  function_getid('Cars')),
       ('CREATED', now(), 'Kia', 'Kia', 'Kia',  function_getid('Cars')),
       ('CREATED', now(), 'Mazda', 'Mazda', 'Mazda',  function_getid('Cars')),
       ('CREATED', now(), 'Mercedes', 'Mercedes', 'Mercedes',  function_getid('Cars')),
       ('CREATED', now(), 'Lexus', 'Lexus', 'Lexus',  function_getid('Cars')),
       ('CREATED', now(), 'Tesla', 'Tesla', 'Tesla',  function_getid('Cars')),
       ('CREATED', now(), 'Toyota', 'Toyota', 'Toyota',  function_getid('Cars'));
--
-- create default 1.1.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
      ('CREATED', now(), 'Auto parts', 'Автозапчасти', 'Avto ehtiyot qismlar',  function_getid('Auto parts and accessories')),
      ('CREATED', now(), 'Accessories', 'Аксессуары', 'Aksessuarlar', function_getid('Auto parts and accessories')),
      ('CREATED', now(), 'Car audio', 'Автозвук', 'Avtomobil uchun audio', function_getid('Auto parts and accessories')),
      ('CREATED', now(), 'Transport for spare parts', 'Транспорт на запчасти', 'Ehtiyot qismlar uchun transport', function_getid('Auto parts and accessories')),
      ('CREATED', now(), 'GPS navigators_car recorders', 'GPS-навигаторы_авторегистраторы', 'GPS navigatorlari_avtomobil magnitafonlari', function_getid('Auto parts and accessories'));

-- create default 1.1.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
       ('CREATED', now(), 'Tires', 'Автошины', 'Shinalar',  function_getid('Шины, диски и колёса')),
       ('CREATED', now(), 'Motorcycle tires', 'Мотошины', 'Mototsikl shinalari', function_getid('Шины, диски и колёса')),
       ('CREATED', now(), 'Discs', 'Диски', 'Disklar', function_getid('Шины, диски и колёса')),
       ('CREATED', now(), 'Wheel assembly', 'Колесо в сборе', 'G''ildirak yig''ish', function_getid('Шины, диски и колёса')),
       ('CREATED', now(), 'Caps', 'Колпаки', 'Qopqoqlar', function_getid('Шины, диски и колёса'));

-- create default 1.1.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Mopeds/scooters', 'Мопеды / скутеры', 'Mopedlar/skuterlar', function_getid('Moto')),
       ('CREATED', now(), 'ATVs', 'Квадроциклы', 'ATVlar', function_getid('Moto')),
       ('CREATED', now(), 'Motorcycles', 'Мотоциклы', 'Mototsikllar', function_getid('Moto')),
       ('CREATED', now(), 'Moto - other', 'Мото -прочее', 'Moto - boshqa', function_getid('Moto'));

-- create default 1.1.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
       ('CREATED', now(), 'Motorcycle parts', 'Мотозапчасти', 'Mototsikl qismlari',   function_getid('Motorcycle parts and accessories')),
       ('CREATED', now(), 'Moto equipment', 'Мотоэкипировка', 'Motor uskunalari',   function_getid('Motorcycle parts and accessories')),
       ('CREATED', now(), 'Moto accessories', 'Мото аксессуары', 'Moto aksessuarlar',   function_getid('Motorcycle parts and accessories'));

-- create default 1.2.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
       ('CREATED', now(), 'Daily rental', 'Посуточная аренда', 'Kunlik ijara',  function_getid('Real estate')),
       ('CREATED', now(), 'Apartments_', 'Квартиры', 'Kvartiralar',  function_getid('Real estate')),
       ('CREATED', now(), 'At home', 'Дома', 'Uy',  function_getid('Real estate')),
       ('CREATED', now(), 'Earth', 'Земля', 'Yer',  function_getid('Real estate')),
       ('CREATED', now(), 'Garages/parking lots', 'Гаражи / стоянки', 'Garajlar / to''xtash joylari',  function_getid('Real estate')),
       ('CREATED', now(), 'Commercial premises', 'Коммерческие помешения', 'Tijorat binolari',  function_getid('Real estate')),
       ('CREATED', now(), 'Furnished', 'Меблированный', 'Jihozlangan',  function_getid('Real estate')),
       ('CREATED', now(), 'Interior items', 'Предметы интерьера', 'Ichki buyumlar',  function_getid('Real estate'));

-- create default 1.2.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Hostels', 'Хостелы', 'Mehmonxonalar',  function_getid('Daily rental')),
       ('CREATED', now(), 'Hotels / Hotels', 'Гостиницы / Отели', 'Mehmonxonalar / mehmonxonalar',  function_getid('Daily rental')),
       ('CREATED', now(), 'Apartments1', 'Квартиры1', 'Kvartiralar1',  function_getid('Daily rental')),
       ('CREATED', now(), 'Houses / Cottages', 'Дома / Дачи', 'Uylar / kottejlar',  function_getid('Daily rental')),
       ('CREATED', now(), 'Sanatoriums / Holiday homes', 'Санатории / Дома отдыха', 'Sanatoriylar / Dam olish uylari',
        function_getid('Daily rental'));

-- create default 1.2.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
      ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara',  function_getid('Apartments_')),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', function_getid('Apartments_')),
       ('CREATED', now(), 'Exchange', 'Обмен', 'Ayirboshlash', function_getid('Apartments_'));

-- create default 1.2.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
       ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', function_getid('At home')),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', function_getid('At home')),
       ('CREATED', now(),  'Exchange', 'Обмен', 'Ayirboshlash', function_getid('At home'));

-- create default 1.2.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', function_getid('Earth')),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', function_getid('Earth'));

-- create default 1.2.6 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', function_getid('Garages/parking lots')),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', function_getid('Garages/parking lots'));

-- create default 1.2.7 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', function_getid('Commercial premises')),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', function_getid('Commercial premises'));

-- create default 1.2.8 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Living room furniture', 'Мебель для гостиной', 'Yashash xonasi uchun mebel', function_getid('Меблированный')),
    ('CREATED', now(), 'Bedroom furniture', 'Мебель для спальни', 'Yotoq xonasi mebellari', function_getid('Меблированный')),
    ('CREATED', now(), 'Hallway furniture', 'Мебель для прихожей', 'Koridor mebellari', function_getid('Меблированный')),
    ('CREATED', now(), 'Kitchen furniture', 'Кухонная мебель', 'Oshxona mebellari', function_getid('Меблированный')),
    ('CREATED', now(), 'Bathroom furniture', 'Мебель для ванной комнаты', 'Hammom uchun mebel', function_getid('Меблированный')),
    ('CREATED', now(), 'Office furniture', 'Офисная мебель', 'Ofis mebellari', function_getid('Меблированный')),
    ('CREATED', now(), 'Custom-made furniture', 'Мебель на заказ', 'Buyurtma asosida tayyorlangan mebel', function_getid('Меблированный')),
    ('CREATED', now(), 'garden furniture', 'Садовая мебель', 'bog ''mebellari', function_getid('Меблированный')),
    ('CREATED', now(), 'Specialized furniture', 'Специализированная мебель', 'Maxsus mebel', function_getid('Меблированный'));
-- create default 1.2.9 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Lamps', 'Светильники', 'Yoritgichlar', function_getid('Предметы интерьера')),
    ('CREATED', now(), 'Window decor', 'Декор окон', 'Deraza dekoratsiyasi', function_getid('Предметы интерьера')),
    ('CREATED', now(), 'Other interior items', 'Иные предметы интерьера', 'Boshqa ichki buyumlar', function_getid('Предметы интерьера'));

-- create default 1.3.0 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Retail / Sales jobs', 'Работа розничная торговля / Продажи', 'Chakana savdo/sotish bo''yicha ishlar', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Work transport/logistics', 'Работа транспорт / логистика', 'Ish transporti/logistikasi', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Construction work', 'Работа строительство', 'Qurilish ishlari', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Jobs in law and accounting', 'Работа юриспруденция и бухгалтерия', 'Huquq va buxgalteriya bo''yicha ishlar', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Job security/security', 'Работа охрана / безопасность', 'Ish xavfsizligi/xavfsizlik', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Work domestic staff', 'Работа домашний персонал', 'Uy xodimlari bilan ishlash', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Work beauty / fitness / sports', 'Работа красота / фитнес / спорт', 'Ish go''zalligi / fitnes / sport', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Work tourism / leisure / entertainment', 'Работа туризм / отдых / развлечения', 'Ish turizmi / dam olish / o''yin-kulgi', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Work education', 'Работа образование',  'Mehnat ta''limi', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Work culture/art', 'Работа культура / искусство', 'Mehnat madaniyati/san''ati', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Jobs medicine/pharmacy', 'Работа медицина / фармация', 'Ishlar tibbiyot/dorixona', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Work "IT" / telecom / computers', 'Работа "IТ" / телеком / компьютеры', 'Ish "IT" / telekom / kompyuterlar', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Real estate work', 'Работа недвижимость', 'Ko''chmas mulk ishi', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Job marketing/advertising/design', 'Работа маркетинг / реклама / дизайн', 'Ish marketingi/reklama/dizayn', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Manufacturing/energy jobs', 'Работа производство / энергетика', 'Ishlab chiqarish/energiya ishlari', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Secretariat / Administrative Office work', 'Работа секретариат / АХО', 'Kotibiyat / Ma''muriy ofis ishi', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Jobs early career / Students', 'Работа начало карьеры / Студенты', 'Ishlar erta martaba / Talabalar', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Work, service and life', 'Работа сервис и быт', 'Ish, xizmat va hayot', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Work other areas of occupation', 'Работа другие сферы занятий', 'Kasbning boshqa sohalarida ishlash', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Construction / repair / cleaning services', 'Услуги строительство / ремонт / уборка', 'Qurilish / ta''mirlash / tozalash xizmatlari', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Maintenance services, equipment repair', 'Услуги обслуживание, ремонт техники', 'Texnik xizmat ko''rsatish, uskunalarni ta''mirlash', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Services financial services/partnership', 'Услуги финансовые услуги / партнерство', 'Xizmatlar moliyaviy xizmatlar/sheriklik', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Transportation services / transport rental', 'Услуги перевозки / аренда транспорта', 'Transport xizmatlari / transport ijarasi', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Services advertising / printing / marketing / internet', 'Услуги реклама / полиграфия / маркетинг / интернет', 'Xizmatlar reklama / bosib chiqarish / marketing / internet', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Nanny/nurse services', 'Услуги няни / сиделки', 'Enaga/hamshira xizmatlari', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Services raw materials / materials', 'Услуги сырьё / материалы', 'Xizmatlar xom ashyo / materiallar', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Beauty/health services', 'Услуги красота / здоровье', 'Go''zallik / sog''liqni saqlash xizmatlari', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Services equipment', 'Услуги оборудование', 'Xizmat uskunalari', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Services Education / Sports', 'Услуги образование / Спорт', 'Xizmatlar Ta''lim / Sport', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Animal services', 'Услуги для животных', 'Hayvonlarga xizmat ko''rsatish', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Services business sale', 'Услуги продажа бизнеса', 'Biznes xizmatlarini sotish', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Entertainment Services / Art / Photo / Video', 'Услуги развлечения / Искусство / Фото / Видео', 'Ko''ngilochar xizmatlar / San''at / Foto / Video', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Tourism services', 'Услуги туризм', 'Turizm xizmatlari', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Translation services/typing', 'Услуги переводчиков / набор текста', 'Tarjima xizmatlari / yozish', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Auto / motorcycle services', 'Авто / мото услуги', 'Avto / mototsikl xizmatlari', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Legal services', 'Юридические услуги', 'Yuridik xizmatlar', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Services rental of goods', 'Услуги прокат товаров', 'Tovarlarni ijaraga berish xizmatlari', function_getid('Работа и услуги')),
       ('CREATED', now(), 'Other services', 'Прочие услуги', 'Boshqa xizmatlar', function_getid('Работа и услуги'));

-- create default 1.3.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Construction services', 'Cтроительные услуги', 'Qurilish xizmatlari', function_getid('Услуги строительство / ремонт / уборка')),
       ('CREATED', now(), 'Design/architecture', 'Дизайн / архитектура', 'Dizayn/arxitektura', function_getid('Услуги строительство / ремонт / уборка')),
       ('CREATED', now(), 'Decoration / renovation', 'Отделка / ремонт', 'Bezatish / ta''mirlash', function_getid('Услуги строительство / ремонт / уборка')),
       ('CREATED', now(), 'Manufacturing and installation of windows and doors', 'Изготовление и установка окон и дверей', 'Deraza va eshiklarni ishlab chiqarish va o''rnatish', function_getid('Услуги строительство / ремонт / уборка')),
       ('CREATED', now(), 'Plumbing/communications', 'Сантехника / коммуникации', 'Santexnika / aloqa', function_getid('Услуги строительство / ремонт / уборка')),
       ('CREATED', now(), 'Cleaning / Garbage removal / Disinfection', 'Уборка / Вывоз мусора / Дезинфекция', 'Tozalash / Chiqindilarni olib tashlash / Dezinfeksiya', function_getid('Услуги строительство / ремонт / уборка')),
       ('CREATED', now(), 'Ventilation/air conditioning', 'Вентиляция / кондиционирование', 'Shamollatish / konditsioner', function_getid('Услуги строительство / ремонт / уборка')),
       ('CREATED', now(), 'Electrics', 'Электрика', 'Elektr', function_getid('Услуги строительство / ремонт / уборка')),
       ('CREATED', now(), 'Ready-made designs', 'Готовые конструкции', 'Tayyor dizaynlar', function_getid('Услуги строительство / ремонт / уборка'));


-- create default 1.3.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Repair and installation of satellite TV', 'Ремонт и установка спутникового ТВ', 'Sun''iy yo''ldosh televideniesini ta''mirlash va o''rnatish', function_getid('Услуги обслуживание, ремонт техники')),
    ('CREATED', now(), 'Appliances', 'Бытовая техника', 'Maishiy texnika', function_getid('Услуги обслуживание, ремонт техники')),
    ('CREATED', now(), 'Mobile devices/telephony', 'Мобильные устройства / телефония', 'Mobil qurilmalar/telefoniya', function_getid('Услуги обслуживание, ремонт техники')),
    ('CREATED', now(), 'Computer equipment/game consoles', 'Компьютерная техника / игровые приставки', 'Kompyuter uskunalari / o''yin konsollari', function_getid('Услуги обслуживание, ремонт техники')),
    ('CREATED', now(), 'Photo / video / audio equipment', 'Фото / видео / аудио техника', 'Foto / video / audio uskunalari', function_getid('Услуги обслуживание, ремонт техники')),
    ('CREATED', now(), 'Air conditioning equipment', 'Климатическая техника', 'Konditsioner uskunalari', function_getid('Услуги обслуживание, ремонт техники')),
    ('CREATED', now(), 'Supply/repair/maintenance of equipment', 'Поставка / ремонт / обслуживание оборудования', 'Uskunalarni etkazib berish / ta''mirlash / texnik xizmat ko''rsatish', function_getid('Услуги обслуживание, ремонт техники'));


-- create default 1.4.0 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Dogs', 'Собаки', 'Itlar', function_getid('Animals')),
       ('CREATED', now(), 'Cats', 'Кошки', 'Mushuklar', function_getid('Animals')),
       ('CREATED', now(), 'Aquarium', 'Аквариумистика', 'Akvarium', function_getid('Animals')),
       ('CREATED', now(), 'Birds', 'Птицы', 'Qushlar', function_getid('Animals')),
       ('CREATED', now(), 'Rodents', 'Грызуны', 'Kemiruvchilar', function_getid('Animals')),
       ('CREATED', now(), 'Farm animals', 'Сельхоз животные', 'Ferma hayvonlari', function_getid('Animals')),
       ('CREATED', now(), 'Pet supplies', 'Зоотовары', 'Uy hayvonlari uchun materiallar', function_getid('Animals')),
       ('CREATED', now(), 'Mating', 'Вязка', 'Juftlash', function_getid('Animals')),
       ('CREATED', now(), 'Lost and found', 'Бюро находок', 'Yo''qolgan va topilgan', function_getid('Animals')),
       ('CREATED', now(), 'Other animals', 'Другие животные', 'Boshqa hayvonlar', function_getid('Animals'));

-- create default 1.5.0 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Audio equipment', 'Аудиотехника', 'Audio uskunalar', function_getid('Электроника')),
    ('CREATED', now(), 'Games and game consoles', 'Игры и игровые приставки', 'O''yinlar va o''yin konsollari', function_getid('Электроника')),
    ('CREATED', now(), 'Computers', 'Компьютеры', 'Kompyuterlar', function_getid('Электроника')),
    ('CREATED', now(), 'Individual care', 'Индивидуальный уход', 'Shaxsiy g''amxo''rlik', function_getid('Электроника')),
    ('CREATED', now(), 'TV/video equipment', 'Тв / видеотехника', 'Televizor/video uskunalari', function_getid('Электроника')),
    ('CREATED', now(), 'Phones', 'Телефоны', 'Telefonlar', function_getid('Электроника')),
    ('CREATED', now(), 'Home Appliances', 'Техника для дома', 'Maishiy texnika1', function_getid('Электроника')),
    ('CREATED', now(), 'Kitchen appliances', 'Техника для кухни', 'Oshxona jihozlari', function_getid('Электроника')),
    ('CREATED', now(), 'Photo/video', 'Фото / видео', 'Foto/video', function_getid('Электроника')),
    ('CREATED', now(), 'PTV video equipment', 'Тв видеотехника', 'Televizion video uskunalar', function_getid('Электроника')),
    ('CREATED', now(), 'Accessories and components', 'Аксессуары и  комплектуюшие', 'Aksessuarlar va komponentlar', function_getid('Электроника')),
    ('CREATED', now(), 'Climatic equipment', 'Климатическое  оборудовование', 'Iqlim uskunalari', function_getid('Электроника')),
    ('CREATED', now(), 'Other electronics', 'Прочая электроника', 'Boshqa elektronika', function_getid('Электроника'));

-- create default 1.5.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'MP3 players', 'MP3 плееры', 'MP3 pleerlar', function_getid('Audio equipment')),
    ('CREATED', now(), 'Radio tape recorders', 'Магнитолы', 'Radio magnitafonlari',  function_getid('Audio equipment')),
    ('CREATED', now(), 'Music centers', 'Музыкалные центры', 'Musiqa markazlari',  function_getid('Audio equipment')),
    ('CREATED', now(), 'Acustic systems', 'Акустические системы', 'Akustik tizimlar',  function_getid('Audio equipment')),
    ('CREATED', now(), 'Radios', 'Радиоприемники', 'Radiolar',  function_getid('Audio equipment')),
    ('CREATED', now(), 'Portable speakers', 'Портативная акустика', 'Portativ dinamiklar',  function_getid('Audio equipment')),
    ('CREATED', now(), 'Amplifiers/receivers', 'Усилители / ресиверы', 'Kuchaytirgichlar/qabul qiluvchilar',  function_getid('Audio equipment')),
    ('CREATED', now(), 'CD/MD/Vinyl players', 'Cd / md / виниловые проигрыватели', 'CD/MD/vinil pleyerlar',  function_getid('Audio equipment')),
    ('CREATED', now(), 'Other audio equipment', 'Прочая аудиотехника', 'Boshqa audio uskunalar',  function_getid('Audio equipment')),
    ('CREATED', now(), 'Headphones', 'Наушники', 'Naushniklar',  function_getid('Audio equipment'));

-- create default 1.5.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Console games', 'Игры для приставок', 'Konsol o''yinlar', function_getid('Игры и игровые приставки')),
       ('CREATED', now(), 'Consoles', 'Приставки', 'Konsollar', function_getid('Игры и игровые приставки')),
       ('CREATED', now(), 'PC Games', 'Игры для PC', 'Kompyuter o''yinlari', function_getid('Игры и игровые приставки')),
       ('CREATED', now(), 'Accessories1', 'Аксессуары1', 'Aksessuarlar1', function_getid('Игры и игровые приставки'));

-- create default 1.5.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Tabletop', 'Настольные', 'Stol usti', function_getid('Компьютеры')),
    ('CREATED', now(), 'Laptops', 'Ноутбуки', 'Noutbuklar',  function_getid('Компьютеры')),
    ('CREATED', now(), 'Tablet computers', 'Планшетные компютеры', 'Planshet kompyuterlar',  function_getid('Компьютеры')),
    ('CREATED', now(), 'Accessories2', 'Аксессуары2', 'Aksessuarlar2',  function_getid('Компьютеры')),
    ('CREATED', now(), 'Servers', 'Серверы', 'Serverlar',  function_getid('Компьютеры')),
    ('CREATED', now(), 'Accessories3', 'Комплектующие', 'Aksessuarlar3',  function_getid('Компьютеры')),
    ('CREATED', now(), 'Peripherals', 'Периферийные  устройства', 'Periferik qurilmalar',  function_getid('Компьютеры')),
    ('CREATED', now(), 'Monitors', 'Мониторы', 'Monitorlar',  function_getid('Компьютеры')),
    ('CREATED', now(), 'External drives', 'Внешние накопители', 'Tashqi drayvlar',  function_getid('Компьютеры')),
    ('CREATED', now(), 'Consumables', 'Расходные материалы', 'Sarf materiallari',  function_getid('Компьютеры')),
    ('CREATED', now(), 'Other', 'Другое', 'Boshqa',  function_getid('Компьютеры'));

-- create default 1.5.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Razors, epilators, hair clippers', 'Бритвы, эпиляторы, машинки для стрижки',
        'Usra, epilator, soch kesgich', function_getid('Индивидуальный уход')),
       ('CREATED', now(), 'Hair dryers, hair styling', 'Фены, укладка волос', 'Soch quritgichlari, soch turmagi', function_getid('Индивидуальный уход')),
       ('CREATED', now(), 'Scales', 'Весы', 'Tarozilar', function_getid('Индивидуальный уход')),
       ('CREATED', now(), 'Other personal care equipment', 'Прочая техника для  индивидуального ухода',
        'Boshqa shaxsiy parvarish uskunalari', function_getid('Индивидуальный уход'));


-- create default 1.5.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Accessories for TV/Video Equipment', 'Аксессуары для  ТВ/Видеотехники','TV/Video uskunalari uchun aksessuarlar',  function_getid('Тв / видеотехника')),
       ('CREATED', now(), 'TVs', 'Телевизоры', 'televizorlar', function_getid('Тв / видеотехника')),
       ('CREATED', now(), 'Media players', 'Медиа проигрыватели', 'Media pleerlar', function_getid('Тв / видеотехника')),
       ('CREATED', now(), 'Satellite TV', 'Спутниковое тв', 'Sun''iy yo''ldosh televideniesi', function_getid('Тв / видеотехника')),
       ('CREATED', now(), 'Other TV/video equipment', 'Прочая тв /  видеотехника', 'Boshqa televizor/video uskunalari',
        function_getid('Тв / видеотехника'));

-- create default 1.5.6 category
INSERT INTO tsx_category (status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Accessories', 'Аксессуары', 'Aksessuarlar', function_getid('Телефоны')),
    ('CREATED', NOW(), 'Mobile Phones', 'Мобильные телефоны', 'Mobil telefonlar', function_getid('Телефоны')),
    ('CREATED', NOW(), 'SIM Cards Tariffs Numbers', 'Сим карты тарифы номера', 'SIM kartlar tariflari raqamlari', function_getid('Телефоны')),
    ('CREATED', NOW(), 'Landline Phones', 'Стационарные телефоны', 'Qo''ng''iroq telefonlari', function_getid('Телефоны')),
    ('CREATED', NOW(), 'Other Phones', 'Прочие телефоны', 'Boshqa telefonlar', function_getid('Телефоны'));

-- create default 1.5.7 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Computers', 'Компьютеры', 'Kompyuterlar', function_getid('Фото / видео')),
    ('CREATED', NOW(), 'Film Cameras', 'Пленочные фотоаппараты', 'Plenachnye fotoapparaty', function_getid('Фото / видео')),
    ('CREATED', NOW(), 'Digital Cameras', 'Цифровые фотоаппараты', 'Tsifrovye fotoapparaty', function_getid('Фото / видео')),
    ('CREATED', NOW(), 'Video Cameras', 'Видеокамеры', 'Videokamery', function_getid('Фото / видео')),
    ('CREATED', NOW(), 'Lenses', 'Объективы', 'Ob''yektivy', function_getid('Фото / видео')),
    ('CREATED', NOW(), 'Tripods Monopods', 'Штативы моноподы', 'Shtativy monopody', function_getid('Фото / видео')),
    ('CREATED', NOW(), 'Camera Flashes', 'Фотовспышки', 'Fotovspyshki', function_getid('Фото / видео')),
    ('CREATED', NOW(), 'Camera Video Accessories', 'Аксессуары для фото видеокамер', 'Foto videokamerlar uchun aksessuarlar', function_getid('Фото / видео')),
    ('CREATED', NOW(), 'Telescopes Binoculars', 'Телескопы бинокли', 'Teleskopy binokli', function_getid('Фото / видео'));

-- create default 1.5.7 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Media Players', 'Медиа проигрыватели', 'Media pleery', function_getid('Тв видеотехника')),
    ('CREATED', NOW(), 'Televisions', 'Телевизоры', 'Televizorlar', function_getid('Тв видеотехника')),
    ('CREATED', NOW(), 'TV Video Accessories', 'Аксессуары для ТВ Видеотехники', 'TV va video texnikasi uchun aksessuarlar', function_getid('Тв видеотехника')),
    ('CREATED', NOW(), 'Satellite TV', 'Спутниковое тв', 'Orbita TV', function_getid('Тв видеотехника')),
    ('CREATED', NOW(), 'Other TV Video Equipment', 'Прочая тв видеотехника', 'Boshqa TV video texnikasi', function_getid('Тв видеотехника'));

-- create default 1.6.0 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Clothes/shoes', 'Одежда /обув', 'Kiyim / poyabzal', function_getid('Одежда и текстил')),
    ('CREATED', now(), 'Products for children', 'Товары для дети', 'Bolalar uchun mahsulotlar', function_getid('Одежда и текстил')),
    ('CREATED', now(), 'For Wedding', 'Для свадьбы', 'To''y uchun', function_getid('Одежда и текстил')),
    ('CREATED', now(), 'Accessories4', 'Аксессуары4', 'Aksessuarlar4', function_getid('Одежда и текстил')),
    ('CREATED', now(), 'Beauty/health', 'Красота / здоровье', 'Go''zallik / salomatlik', function_getid('Одежда и текстил')),
    ('CREATED', now(), 'Goods for schoolchildren', 'Товары для школьников', 'Maktab o''quvchilari uchun tovarlar', function_getid('Одежда и текстил')),
    ('CREATED', now(), 'Fashion miscellaneous', 'Мода разное', 'Har xil moda', function_getid('Одежда и текстил')),
    ('CREATED', now(), 'Wrist watch', 'Наручные часы', 'Qo''l soati', function_getid('Одежда и текстил')),
    ('CREATED', now(), 'Present', 'Подарки', 'Hozirgi', function_getid('Одежда и текстил')),
    ('CREATED', now(), 'Feeding', 'Кормление', 'Oziqlantirish', function_getid('Одежда и текстил')),
    ('CREATED', now(), 'Textile', 'Текстиль', 'To''qimachilik', function_getid('Одежда и текстил'));

-- create default 1.6.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Men''s Clothing', 'Мужская одежда', 'Erkak kiyimlari',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Men''s Underwear', 'Мужское белье', 'Erkak ichki kiyimi',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Women''s Clothing', 'Женская одежда', 'Ayol kiyimlari',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Women''s Lingerie, Swimwear', 'Женское белье, купальники', 'Ayol ichki kiyimi, suzuvchi kiyimlar',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Maternity Clothing', 'Одежда для беременных', 'Homiladorlar kiyimi',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Boys'' Clothing', 'Одежда для мальчиков', 'O''g''il bola kiyimi',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Girls'' Clothing', 'Одежда для девочек', 'Qiz bola kiyimi',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Newborn Clothing', 'Одежда для новорожденных', 'Yangi tug''ilganlar kiyimi',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Men''s Shoes', 'Мужская обувь', 'Erkak poyafzali',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Women''s Shoes', 'Женская обувь', 'Ayol poyafzali',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Boys'' Shoes', 'Обувь для мальчиков', 'O''g''il bola poyafzali',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Girls'' Shoes', 'Обувь для девочек', 'Qiz bola poyafzali',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Baby Shoes', 'Обувь для малышей', 'Bola poyafzali',  function_getid('Одежда /обув')),
    ('CREATED', NOW(), 'Headwear', 'Головные уборы', 'Bosh kiyimlar',  function_getid('Одежда /обув'));

-- create default 1.6.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Child Car Seats', 'Детские автокресла', 'Bolalar avtokreslolar', function_getid('Товары для дети')),
    ('CREATED', NOW(), 'Strollers', 'Детские коляски', 'Bolalar kochirlari', function_getid('Товары для дети')),
    ('CREATED', NOW(), 'Children''s Furniture', 'Детская мебель', 'Bolalar mebellari', function_getid('Товары для дети')),
    ('CREATED', NOW(), 'Children''s Transport', 'Детский транспорт', 'Bolalar transporti', function_getid('Товары для дети')),
    ('CREATED', NOW(), 'Toys', 'Игрушки', 'O''yinchoqlar', function_getid('Товары для дети')),
    ('CREATED', NOW(), 'Other Children''s Products', 'Прочие детские товары', 'Boshqa bolalar mahsulotlari', function_getid('Товары для дети'));

-- create default 1.6.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Wedding Accessories', 'Свадебные аксессуары', 'To''y aksessuary', function_getid('Для свадьбы')),
    ('CREATED', NOW(), 'Wedding Dresses', 'Свадебные платья', 'To''y libosi', function_getid('Для свадьбы'));

-- create default 1.6.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Jewelry', 'Ювелирные изделия', 'Zargarlik mahsulotlar',  function_getid('Аксессуары4')),
    ('CREATED', NOW(), 'Bags', 'Сумки', 'Sumkalar', function_getid('Аксессуары4')),
    ('CREATED', NOW(), 'Costume Jewelry', 'Бижутерия', 'Bijouteriya', function_getid('Аксессуары4')),
    ('CREATED', NOW(), 'Other Accessories', 'Другие аксессуары', 'Boshqa aksessuarlar', function_getid('Аксессуары4'));

-- create default 1.6.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Cosmetics', 'Косметика', 'Kosmetika',  function_getid('Красота / здоровье')),
       ('CREATED', now(), 'Perfumery', 'Парфюмерия', 'Parfyumeriya', function_getid('Красота / здоровье')),
       ('CREATED', now(), 'Care products', 'Средства по уходу', 'G''amxo''rlik mahsulotlari', function_getid('Красота / здоровье')),
       ('CREATED', now(), 'Products for the disabled', 'Товары для инвалидов', 'Nogironlar uchun mahsulotlar', function_getid('Красота / здоровье')),
       ('CREATED', now(), 'Other beauty and health products', 'Прочие товары для  красоты и здоровья',
        'Boshqa go''zallik va sog''liq uchun mahsulotlar', function_getid('Красота / здоровье'));

-- create default 1.7.0 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Building/Construction Supplies', 'Товары для строительства/ремонта', 'Qurilish/tuzatish tovarlari', function_getid('Строительства')),
    ('CREATED', NOW(), 'Tools', 'Инструменты', 'Instrumentlar',  function_getid('Строительства')),
    ('CREATED', NOW(), 'Indoor Plants', 'Комнатные растения', 'Ichki oʻsimliklar',  function_getid('Строительства')),
    ('CREATED', NOW(), 'Tableware/Kitchenware', 'Посуда/кухонная утварь', 'Stolovyeprinadlezhnosti/kuxonnayaradost',  function_getid('Строительства')),
    ('CREATED', NOW(), 'Garden', 'Сад/огород', 'Bog/ogʻirlik',  function_getid('Строительства')),
    ('CREATED', NOW(), 'Gardening Tools', 'Садовый инвентарь', 'Bogʻ inshooti',  function_getid('Строительства')),
    ('CREATED', NOW(), 'Household Supplies/Cleaning Products', 'Хозяйственный инвентарь/бытовая химия', 'Buyumlar/chistka mahsulotlari',  function_getid('Строительства')),
    ('CREATED', NOW(), 'Stationery/Consumables', 'Канцтовары/расходные материалы', 'Qogʻozlar/sarflanadigan materiallar',  function_getid('Строительства')),
    ('CREATED', NOW(), 'Food/Beverages', 'Продукты питания/Напитки', 'Oziq-ovqat/Ichimliklar',  function_getid('Строительства')),
    ('CREATED', NOW(), 'Other Home Goods', 'Прочие товары для дома', 'Boshqa uy mahsulotlari',  function_getid('Строительства'));

-- create default 1.7.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Plumbing', 'Сантехника', 'Santexnika', function_getid('Товары для строительства/ремонта')),
    ('CREATED', NOW(), 'Ventilation', 'Вентиляция', 'Ventilyatsiya', function_getid('Товары для строительства/ремонта')),
    ('CREATED', NOW(), 'Heating', 'Отопление', 'Isitish', function_getid('Товары для строительства/ремонта')),
--     ('CREATED', NOW(), 'Electrical', 'Электрика', 'Elektrika', function_getid('Товары для строительства/ремонта')),
    ('CREATED', NOW(), 'Lumber', 'Пиломатериалы', 'Yogʻoch', function_getid('Товары для строительства/ремонта')),
    ('CREATED', NOW(), 'Finishing and Facing Materials', 'Отделочные и облицовочные материалы', 'Qalbaki va qoʻyish materiallari', function_getid('Товары для строительства/ремонта')),
    ('CREATED', NOW(), 'Windows/Doors/Glass/Mirrors', 'Окна/двери/стекла/зеркала', 'Darcha/eshiklar/shisha/ayoqlar', function_getid('Товары для строительства/ремонта')),
    ('CREATED', NOW(), 'Painting Materials', 'Лакокрасочные материалы', 'Lakrang materiallar', function_getid('Товары для строительства/ремонта')),
    ('CREATED', NOW(), 'Metal Products/Rebar', 'Металлопрокат/арматура', 'Metall mahsulotlar/rebar', function_getid('Товары для строительства/ремонта')),
    ('CREATED', NOW(), 'Fasteners', 'Элементы крепежа', 'Biriktirgich elementlar', function_getid('Товары для строительства/ремонта')),
    ('CREATED', NOW(), 'Bricks/Concrete/Blocks', 'Кирпич/бетон/пеноблоки', 'Gʻisht/beton/penobloklar', function_getid('Товары для строительства/ремонта')),
    ('CREATED', NOW(), 'Other Building Materials', 'Прочие стройматериалы', 'Boshqa qurilish materiallari', function_getid('Товары для строительства/ремонта'));

-- create default 1.7.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Hand Tools', 'Ручной инструмент', 'Qoʻl uskunalari', function_getid('Инструменты')),
    ('CREATED', NOW(), 'Gas-Powered Tools', 'Бензоинструмент', 'Benzinli uskunalari', function_getid('Инструменты')),
    ('CREATED', NOW(), 'Power Tools', 'Электроинструмент', 'Elektr uskunalari', function_getid('Инструменты')),
    ('CREATED', NOW(), 'Pneumatic Tools', 'Пневмоинструмент', 'Pnevmo uskunalari', function_getid('Инструменты')),
    ('CREATED', NOW(), 'Other Tools', 'Прочий инструмент', 'Boshqa uskunalar', function_getid('Инструменты'));

-- create default 1.8.0 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Vegetables', 'Овощи', 'Sabzavotlar', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Fruits', 'Фрукты', 'Mevalar', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Herbs', 'Травы', 'Oʻsimliklar', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Greens', 'Зелень', 'Yashilliklar', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Berries', 'Ягоды', 'Bogʻcha mevalari', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Mushrooms', 'Грибы', 'Quloqxonalar', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Spices', 'Специи', 'Meva', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Cereals', 'Крупы', 'Daryo qorilari', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Nuts', 'Орехи', 'Yongʻoqlar', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Dried Fruits', 'Сухофрукты', 'Quru mevalar', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Meat', 'Мясо', 'Goʻsht', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Poultry', 'Птица', 'Burung', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Fish', 'Рыба', 'Baliq', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Eggs', 'Яйца', 'Tuxumlar', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Seafood', 'Морепродукты', 'Dengizmahsulotlari', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Dairy', 'Молочные', 'Sut mahsulotlari', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Oils', 'Масла', 'Yaglar', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Honey', 'Мёд', 'Asal', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Beverages', 'Напитки', 'Ichimliklar', function_getid('Продукты питания')),
    ('CREATED', NOW(), 'Other Food', 'Другая пища', 'Boshqa ovqat', function_getid('Продукты питания'));

-- create default 1.9.0 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Antiques/Collectibles', 'Антиквариат/коллекции', 'Antikvariyat/kolleksiyalar',  function_getid('Развлечение')),
    ('CREATED', NOW(), 'Musical Instruments', 'Музыкальные инструменты', 'Musiqiy uskunalar', function_getid('Развлечение')),
    ('CREATED', NOW(), 'Sports/Leisure', 'Спорт/отдых', 'Sport/Dam olish', function_getid('Развлечение')),
    ('CREATED', NOW(), 'Tickets', 'Билеты', 'Biletlar', function_getid('Развлечение')),
    ('CREATED', NOW(), 'Books/Magazines', 'Книги/журналы', 'Kitoblar/jurnal', function_getid('Развлечение')),
    ('CREATED', NOW(), 'CD/DVD/Records/Cassettes', 'CD/DVD/пластинки/кассеты', 'CD/DVD/Plastinka/Kassetlar', function_getid('Развлечение'));

-- create default 1.9.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Antique Furniture', 'Антикварная мебель', 'Antikvar mebel', function_getid('Антиквариат/коллекции')),
    ('CREATED', NOW(), 'Secondhand Books', 'Букинистика', 'Ikkinchi qoʻllanma', function_getid('Антиквариат/коллекции')),
    ('CREATED', NOW(), 'Paintings', 'Живопись', 'Rasmlar', function_getid('Антиквариат/коллекции')),
    ('CREATED', NOW(), 'Art Objects', 'Предметы искусства', 'Sanʼat asboblar', function_getid('Антиквариат/коллекции')),
    ('CREATED', NOW(), 'Collectibles', 'Коллекционирование', 'Kolleksionlashtirish', function_getid('Антиквариат/коллекции')),
    ('CREATED', NOW(), 'Crafts/Handmade', 'Поделки/рукоделие', 'Qogʻoz ishlari/Qoʻl ishi', function_getid('Антиквариат/коллекции'));

-- create default 1.9.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Guitars', 'Гитары', 'Gitara', function_getid('Музыкальные инструменты')),
    ('CREATED', NOW(), 'Pianos/Keyboards', 'Пианино/фортепиано/рояли', 'Piano/fortepiano/Royal', function_getid('Музыкальные инструменты')),
    ('CREATED', NOW(), 'Violins', 'Скрипки', 'Skripka', function_getid('Музыкальные инструменты')),
    ('CREATED', NOW(), 'Wind Instruments', 'Духовые инструменты', 'Puflama  asboblar', function_getid('Музыкальные инструменты')),
    ('CREATED', NOW(), 'Combo Amplifiers', 'Комбоусилители', 'Combo oʻsishlar', function_getid('Музыкальные инструменты')),
    ('CREATED', NOW(), 'Synthesizers', 'Синтезаторы', 'Sintezatorlar', function_getid('Музыкальные инструменты')),
    ('CREATED', NOW(), 'Percussion Instruments', 'Ударные инструменты', 'Zadavlon asboblar', function_getid('Музыкальные инструменты')),
    ('CREATED', NOW(), 'Studio Equipment', 'Студийное оборудование', 'Studio uskunalari', function_getid('Музыкальные инструменты')),
    ('CREATED', NOW(), 'Musical Instrument Accessories', 'Аксессуары для музыкальных инструментов', 'Musiqiy uskunalar aksessuarlari', function_getid('Музыкальные инструменты'));

-- create default 1.9.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', NOW(), 'Cycling', 'Вело', 'Velosiped',  function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Skiing/Snowboarding', 'Лыжи/сноуборды', 'Shayxanish/laydiboarding', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Ice Skating', 'Коньки', 'Buzlayish', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Roller Skating', 'Роликовые коньки', 'Roller konki', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Athletics/Fitness', 'Атлетика/фитнес', 'Atletika/Fitness', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'RC Models', 'Радиоуправляемые модели', 'Radio boshqariladigan modellar', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Sports Nutrition', 'Спортивное питание', 'Sport ovqatlari', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Outdoor Equipment', 'Товары для туризма', 'Sayohat uchun buyumlar', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Hunting/Fishing', 'Охота/рыбалка', 'Baliq ovlash/oʻtkash', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Racket Games', 'Игры с ракеткой', 'Raket oʻyinlari', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Water Sports', 'Водные виды спорта', 'Suv sporti', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Football', 'Футбол', 'Futbol', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Combat Sports/Boxing', 'Единоборства/бокс', 'Birlashuv sportlari/Boks', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Board Games', 'Настольные игры', 'Taxti oʻyinlar', function_getid('Спорт/отдых')),
    ('CREATED', NOW(), 'Other Sports', 'Прочие виды спорта', 'Boshqa sport turlari', function_getid('Спорт/отдых'));