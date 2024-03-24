--create default attach
INSERT INTO tsx_attach (id, content_type, created_date, origin_name, path, size, type)
VALUES ('7d5ade1d-0950-4785-bd73-d85430080a46', 'image/jpeg', now(), 'Image from wp-s.ru (1).jpg', 'images/category',
        308630, 'jpg');

-- create default 1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, attach_id)
VALUES ('CREATED', now(), 'Transportation', 'Транспорт', 'Transport', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Real estate', 'Недвижимость', 'Ko''chmas mulk', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Children''s', 'Детиский мир', 'Bolalar uchun', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Job', 'Работа', 'Ish', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Animals', 'Животные', 'Hayvonlar', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'House and garden', 'дом и сад', 'Uy va bog''', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Electronics', 'Электроника', 'Elektronika', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Business and services', 'Бизнес и услуги', 'Biznes va xizmatlar',
        '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Fashion and Style', 'Мода и стиль', 'Moda va uslub', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Hobbies, recreation and sports', 'Хобби, отдых и спорт',
        'Хevimli mashg''ulotlar, dam olish va sport', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Agricultural products', 'Сельхоз продукты', 'Qishloq xo''jaligi mahsulotlari',
        '7d5ade1d-0950-4785-bd73-d85430080a46');

-- create default 1.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Cars', 'Легковые автомобили', 'Avtomobillar', 1),
       ('CREATED', now(), 'Auto parts and accessories', 'Автозапчасти и аксессуары',
        'Avto ehtiyot qismlar va aksessuarlar', 1),
       ('CREATED', now(), 'Tires, rims and wheels', 'Шины, диски и колёса', 'Shinalar, jantlar va g''ildiraklar', 1),
       ('CREATED', now(), 'Moto', 'Moto', 'Moto', 1),
       ('CREATED', now(), 'Motorcycle parts and accessories', 'Мотозапчасти и аксессуары',
        'Mototsikl qismlari va aksessuarlari', 1),
       ('CREATED', now(), 'Buses', 'Автобусы', 'Avtobuslar', 1),
       ('CREATED', now(), 'Moto', 'Moto', 'Moto', 1);

-- create default 1.1.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Chevrolet', 'Chevrolet', 'Chevrolet', 12),
       ('CREATED', now(), 'Audi', 'Audi', 'Audi', 12),
       ('CREATED', now(), 'BMV', 'BMV', 'BMV', 12),
       ('CREATED', now(), 'Jac', 'Jac', 'Jac', 12),
       ('CREATED', now(), 'Kia', 'Kia', 'Kia', 12),
       ('CREATED', now(), 'Mazda', 'Mazda', 'Mazda', 12),
       ('CREATED', now(), 'Mercedes', 'Mercedes', 'Mercedes', 12),
       ('CREATED', now(), 'Mercedes', 'Mercedes', 'Mercedes', 12),
       ('CREATED', now(), 'Mercedes', 'Mercedes', 'Mercedes', 12),
       ('CREATED', now(), 'Lexus', 'Lexus', 'Lexus', 12),
       ('CREATED', now(), 'Tesla', 'Tesla', 'Tesla', 12),
       ('CREATED', now(), 'Toyota', 'Toyota', 'Toyota', 12);
--
-- create default 1.1.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Auto parts', 'Автозапчасти', 'Avto ehtiyot qismlar', 13),
       ('CREATED', now(), 'Accessories', 'Аксессуары', 'Aksessuarlar', 13),
       ('CREATED', now(), 'Car audio', 'Автозвук', 'Avtomobil uchun audio', 13),
       ('CREATED', now(), 'Transport for spare parts', 'Транспорт на запчасти', 'Ehtiyot qismlar uchun transport', 13),
       ('CREATED', now(), 'Car recorders', 'Авторегистраторы', 'Avtomobil yozuvchilari', 13);

-- create default 1.1.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Motorcycle tires', 'Мотошины', 'Mototsikl shinalari', 14),
       ('CREATED', now(), 'Discs', 'Диски', 'Disklar', 14),
       ('CREATED', now(), 'Wheel assembly', 'Колесо в сборе', 'G''ildirak yig''ish', 14),
       ('CREATED', now(), 'Caps', 'Колпаки', 'Qopqoqlar', 14);

-- create default 1.1.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Mopeds/scooters', 'Мопеды / скутеры', 'Mopedlar/skuterlar', 15),
       ('CREATED', now(), 'ATVs', 'Квадроциклы', 'ATVlar', 15),
       ('CREATED', now(), 'Motorcycles', 'Мотоциклы', 'Mototsikllar', 15),
       ('CREATED', now(), 'Moto - other', 'Мото -прочее', 'Moto - boshqa', 15);

-- create default 1.1.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Motorcycle parts and accessories', 'Мотозапчасти и аксессуары',
        'Mototsikl qismlari va aksessuarlari', 16),
       ('CREATED', now(), 'Motorcycle parts', 'Мотозапчасти', 'Mototsikl qismlari', 16),
       ('CREATED', now(), 'Moto equipment', 'Мотоэкипировка', 'Motor uskunalari', 16),
       ('CREATED', now(), 'Moto accessories', 'Мото аксессуары', 'Moto aksessuarlar', 16);

-- create default 1.2.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Daily rental', 'Посуточная аренда', 'Kunlik ijara', 2),
       ('CREATED', now(), 'Apartments', 'Квартиры', 'Kvartiralar', 2),
       ('CREATED', now(), 'At home', 'Дома', 'Uy', 2),
       ('CREATED', now(), 'Earth', 'Земля', 'Yer', 2),
       ('CREATED', now(), 'Garages/parking lots', 'Гаражи / стоянки', 'Garajlar / to''xtash joylari', 2),
       ('CREATED', now(), 'Commercial premises', 'Коммерческие помешения', 'Tijorat binolari', 2);

-- create default 1.2.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Hostels', 'Хостелы', 'Mehmonxonalar', 48),
       ('CREATED', now(), 'Hotels / Hotels', 'Гостиницы / Отели', 'Mehmonxonalar / mehmonxonalar', 48),
       ('CREATED', now(), 'Apartments', 'Квартиры', 'Kvartiralar', 48),
       ('CREATED', now(), 'Houses / Cottages', 'Дома / Дачи', 'Uylar / kottejlar', 48),
       ('CREATED', now(), 'Sanatoriums / Holiday homes', 'Санатории / Дома отдыха', 'Sanatoriylar / Dam olish uylari',
        48);

-- create default 1.2.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', 49),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', 49);

-- create default 1.2.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', 50),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', 50);

-- create default 1.2.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', 51),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', 51);

-- create default 1.2.6 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', 52),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', 52);

-- create default 1.2.6 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', 53),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', 53);

-- create default 1.3.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Baby clothes', 'Детская одежда', 'Bolalar kiyimi', 3),
       ('CREATED', now(), 'Children''s shoes', 'Детская обув', 'Bolalar poyabzali', 3),
       ('CREATED', now(), 'Child car seats', 'Детские автокресла', 'Bolalar uchun avtomobil o''rindiqlari', 3),
       ('CREATED', now(), 'Baby strollers', 'Детские коляски', 'Bolalar aravachalari', 3),
       ('CREATED', now(), 'Children''s furniture', 'Детские мебель', 'Bolalar mebellari', 3),
       ('CREATED', now(), 'Children''s transport', 'Детский транспорт', 'Bolalar transporti', 3),
       ('CREATED', now(), 'Toys', 'Игрушки', 'O''yinchoqlar', 3),
       ('CREATED', now(), 'Feeding', 'Кормление', 'Oziqlantirish', 3),
       ('CREATED', now(), 'Baby Clothing', 'Одежда для новорождённых', 'Chaqaloq kiyimlari', 3),
       ('CREATED', now(), 'Other children''s products', 'Прочие детские товары', 'Boshqa bolalar mahsulotlari', 3),
       ('CREATED', now(), 'Goods for schoolchildren', 'Товары для школьников', 'Maktab o''quvchilari uchun tovarlar',
        3);

-- create default 1.3.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Clothes for boys', 'Одежда для малчиков', 'O''g''il bolalar uchun kiyimlar', 69),
       ('CREATED', now(), 'Girls clothes', 'Одежда для девочек', 'Qizlar kiyimlari', 69),
       ('CREATED', now(), 'Baby Clothing', 'Одежда для  новорожденных', 'Chaqaloq kiyimlari', 69);

-- create default 1.3.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Shoes for girls', 'Обувь для девочек', 'Qizlar uchun poyabzal', 70),
       ('CREATED', now(), 'Shoes for babies', 'Обувь для малышей', 'Chaqaloqlar uchun poyabzal', 70),
       ('CREATED', now(), 'Shoes for boys', 'Обувь для мальчиков', 'O''g''il bolalar uchun poyabzal', 70);

-- create default 1.4.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), '"IT" / Telecom / Computers', '"IT" / Телеком / Компьютеры', 'Qizlar uchun poyabzal', 4),
       ('CREATED', now(), 'Bars/restaurants', 'Бары / рестораны', 'Chaqaloqlar uchun poyabzal', 4),
       ('CREATED', now(), 'Household staff', 'Домашный персонал', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Other areas of occupation', 'Другие сферы занятий', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Beauty/fitness/sports', 'Красота / фитнес /спорт', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Culture/art', 'Култура / искусство', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Marketing / Advertising / Design', 'Маркетинг / реклама / дизай',
        'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Medicine/pharmacy', 'Медицина / фармация', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Starting a Career / Students', 'Начало карьеры / Студенты',
        'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Real estate', 'Недвижимость', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Education', 'Образование', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Security/Security', 'Охрана / безопастность', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Manufacturing/energy', 'Производство / энергетика', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Retail / Sales', 'Розничная торговля / Продажа', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Secretariat / ACS', 'Секретариат / АХО', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Service and life', 'Сервис и быт', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Transport/logistics', 'Транспорт / логистика', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Construction', 'Строительство', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Part-time employment', 'Частичная занятость', 'O''g''il bolalar uchun poyabzal', 4),
       ('CREATED', now(), 'Law and Accounting', 'Юриспруденция и бухгалтерия', 'O''g''il bolalar uchun poyabzal', 4);

-- create default 1.5.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Dogs', 'Собаки', 'Itlar', 5),
       ('CREATED', now(), 'Cow', 'Корова', 'Sigir', 5),
       ('CREATED', now(), 'Fish', 'Рыба', 'Baliq', 5),
       ('CREATED', now(), 'Cats', 'Кошки', 'Mushuklar', 5),
       ('CREATED', now(), 'Rooster', 'Петух', 'Xo''roz', 5),
       ('CREATED', now(), 'Rodents', 'Грызуны', 'Kemiruvchilar', 5),
       ('CREATED', now(), 'Birds', 'Птицы', 'Qushlar', 5),
       ('CREATED', now(), 'Pet supplies', 'Зоотовары', 'Uy hayvonlari uchun materiallar', 5),
       ('CREATED', now(), 'Animals for free', 'Животные даром', 'Hayvonlar bepul', 5),
       ('CREATED', now(), 'Mating', 'Вязка', 'Juftlash', 5),
       ('CREATED', now(), 'Aquarium', 'Аквариумистика', 'Akvarium', 5),
       ('CREATED', now(), 'Lost and found', 'Бюро находок', 'Yo''qolgan va topilgan', 5),
       ('CREATED', now(), 'Farm animals', 'Сельхоз животные', 'Ferma hayvonlari', 5),
       ('CREATED', now(), 'Other animals', 'Другие животные', 'Boshqa hayvonlar', 5);

-- create default 1.6.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Tools', 'Инструменты', 'Asboblar', 6),
       ('CREATED', now(), 'Products for construction/repair', 'Товары для строительства /ремонта',
        'Qurilish/ta''mirlash uchun mahsulotlar', 6),
       ('CREATED', now(), 'Interior items', 'Предметы интерьера', 'Ichki buyumlar', 6),
       ('CREATED', now(), 'Furniture', 'Мебель', 'Mebel', 6),
       ('CREATED', now(), 'Houseplants', 'Комнатные растения', 'Uy o''simliklari', 6),
       ('CREATED', now(), 'Food/Drinks', 'Продукты питания / Напитки', 'Oziq-ovqat/ichimliklar', 6),
       ('CREATED', now(), 'Other household goods', 'Прочие товары для дома', 'Boshqa uy-ro''zg''or buyumlari', 6),
       ('CREATED', now(), 'Garden Garden', 'Сад / огород', 'Bog ''', 6),
       ('CREATED', now(), 'garden tools', 'Садовый инвентарь', 'Bog '' asboblari', 6),
       ('CREATED', now(), 'Household products', 'Товары для дома', 'Uy-ro''zg''or buyumlari', 6),
       ('CREATED', now(), 'Tires, rims and wheels', 'Шины, диски и колёса', 'Shinalar, jantlar va g''ildiraklar', 6),
       ('CREATED', now(), 'Crockery/kitchenware', 'Посуда / кухонная утварь', 'Idishlar/oshxona anjomlari', 6),
       ('CREATED', now(), 'Household equipment / household chemicals', 'Хозяйственный инвентарь / бытовая химия',
        'Uy jihozlari / uy kimyoviy moddalari', 6),
       ('CREATED', now(), 'Stationery/consumables', 'Канцтовары /расходные материалы',
        'Kantselyariya buyumlari/sarflanadigan materiallar', 6);

-- create default 1.6.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Hand tool', 'Ручной инструмент', 'Qo''l asbobi', 120),
       ('CREATED', now(), 'Gasoline tools', 'Бензоинструмент', 'Benzinli asboblar', 120),
       ('CREATED', now(), 'Power tools', 'Электроинструмент', 'Elektr asboblari', 120),
       ('CREATED', now(), 'Pneumatic tools', 'Пневмоинструмент', 'Pnevmatik asboblar', 120),
       ('CREATED', now(), 'Other tool', 'Прочий инструмент', 'Boshqa vosita', 120);

-- create default 1.6.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Plumbing', 'Сантехника', 'Santexnika', 121),
       ('CREATED', now(), 'Vintelation', 'Винтеляция', 'Vintelatsiya', 121),
       ('CREATED', now(), 'Heating', 'Отопления', 'Isitish', 121),
       ('CREATED', now(), 'Electrics', 'Электрика', 'Elektr', 121),
       ('CREATED', now(), 'Lumber', 'Пиломатериалы', 'Yog''och', 121),
       ('CREATED', now(), 'Finishing and facing materials', 'Отделочные и  облицовочные материалы',
        'Tugatish va qoplama materiallari', 121),
       ('CREATED', now(), 'Windows/doors/glass/mirrors', 'Окна/двери/стекло/ зеркала',
        'Derazalar / eshiklar / oynalar / oynalar', 121),
       ('CREATED', now(), 'Paints and varnishes', 'Лакокрасочные материалы', 'Bo''yoqlar va laklar', 121),
       ('CREATED', now(), 'Rolled metal / fittings', 'Металлопрокат / арматура', 'Metall prokat / armatura', 121),
       ('CREATED', now(), 'Elements / fasteners', 'Элементы / крепежа', 'Elementlar / mahkamlagichlar', 121),
       ('CREATED', now(), 'Brick/concrete/foam blocks', 'Кирпич/бетон/пеноблоки', 'G''isht / beton / ko''pikli bloklar',
        121),
       ('CREATED', now(), 'Other construction materials', 'Прочие стройматерилаы', 'Boshqa qurilish materiallari ',
        121);

-- create default 1.6.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Lamps', 'Светильники', 'Yoritgichlar', 122),
       ('CREATED', now(), 'Textile', 'Текстиль', 'To''qimachilik', 122),
       ('CREATED', now(), 'Window decor', 'Декор окон', 'Deraza dekoratsiyasi', 122),
       ('CREATED', now(), 'Other interior items', 'Иные предметы интерьера', 'Boshqa ichki buyumlar', 122);

-- create default 1.6.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Living room furniture', 'Мебель для гостиной', 'Yashash xonasi uchun mebel', 123),
       ('CREATED', now(), 'Bedroom furniture', 'Мебель для спальни', 'Yotoq xonasi mebellari', 123),
       ('CREATED', now(), 'Hallway furniture', 'Мебель для прихожей', 'Koridor mebellari', 123),
       ('CREATED', now(), 'Kitchen furniture', 'Кухонная мебель', 'Oshxona mebellari', 123),
       ('CREATED', now(), 'Bathroom furniture', 'Мебель для ванной  комнаты', 'Hammom uchun mebel', 123),
       ('CREATED', now(), 'Office furniture', 'Офисная мебель', 'Ofis mebellari', 123),
       ('CREATED', now(), 'Custom-made furniture', 'Мебель на заказ', 'Buyurtma qilingan mebel', 123),
       ('CREATED', now(), 'garden furniture', 'Садовая мебель', 'bog ''mebellari', 123),
       ('CREATED', now(), 'Specialized furniture', 'Специализированная  мебель', 'Maxsus mebel', 123);

-- create default 1.7.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Audio equipment', 'Аудиотехника', 'Audio uskunalar', 7),
       ('CREATED', now(), 'Games and game consoles', 'Игры и игровые приставки', 'O''yinlar va o''yin konsollari', 7),
       ('CREATED', now(), 'Computers', 'Компьютеры', 'Kompyuterlar', 7),
       ('CREATED', now(), 'Individual care', 'Индивидуальный уход', 'Shaxsiy g''amxo''rlik', 7),
       ('CREATED', now(), 'TV/video equipment', 'Тв / видеотехника', 'Televizor/video uskunalari', 7),
       ('CREATED', now(), 'Phones', 'Телефоны', 'Telefonlar', 7),
       ('CREATED', now(), 'Home Appliances', 'Техника для дома', 'Maishiy texnika', 7),
       ('CREATED', now(), 'Kitchen appliances', 'Техника для кухни', 'Oshxona jihozlari', 7),
       ('CREATED', now(), 'Photo/video', 'Фото / видео', 'Foto/video', 7),
       ('CREATED', now(), 'Accessories and components', 'Аксессуары и  комплектуюшие', 'Aksessuarlar va komponentlar',
        7),
       ('CREATED', now(), 'Climatic equipment', 'Климатическое  оборудовование', 'Iqlim uskunalari', 7),
       ('CREATED', now(), 'Other electronics', 'Прочая электроника', 'Boshqa elektronika', 7);

-- create default 1.7.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'MP3 players', 'MP3 плееры', 'MP3 pleerlar', 164),
       ('CREATED', now(), 'Radio tape recorders', 'Магнитолы', 'Radio magnitafonlari', 164),
       ('CREATED', now(), 'Music centers', 'Музыкалные центры', 'Musiqa markazlari', 164),
       ('CREATED', now(), 'Acustic systems', 'Акустические системы', 'Akustik tizimlar', 164),
       ('CREATED', now(), 'Radios', 'Радиоприемники', 'Radiolar', 164),
       ('CREATED', now(), 'Portable speakers', 'Портативная акустика', 'Portativ dinamiklar', 164),
       ('CREATED', now(), 'Amplifiers/receivers', 'Усилители / ресиверы', 'Kuchaytirgichlar/qabul qiluvchilar', 164),
       ('CREATED', now(), 'CD/MD/Vinyl players', 'CD / md / виниловы проигрыватели', 'CD/MD/vinil pleyerlar', 164),
       ('CREATED', now(), 'Other', 'Другие', 'Boshqa', 164),
       ('CREATED', now(), 'Other audio equipment', 'Прочая аудиотехника', 'Boshqa audio uskunalar', 164);

-- create default 1.7.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Console games', 'Игры для приставок', 'Konsol o''yinlar', 165),
       ('CREATED', now(), 'Consoles', 'Приставки', 'Konsollar', 165),
       ('CREATED', now(), 'PC Games', 'Игры для PC', 'Kompyuter o''yinlari', 165),
       ('CREATED', now(), 'Other', 'Другие', 'Boshqa', 165),
       ('CREATED', now(), 'Accessories', 'Аксессуары', 'Aksessuarlar', 165);

-- create default 1.7.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Razors, epilators, hair clippers', 'Бритвы, эпиляторы, машинки для стрижки',
        'Usra, epilator, soch kesgich', 167),
       ('CREATED', now(), 'Hair dryers, hair styling', 'Фены, укладка волос', 'Soch quritgichlari, soch turmagi', 167),
       ('CREATED', now(), 'Scales', 'Весы', 'Tarozilar', 167),
       ('CREATED', now(), 'Other personal care equipment', 'Прочая техника для  индивидуального ухода',
        'Boshqa shaxsiy parvarish uskunalari', 167);

-- create default 1.6.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Tabletop', 'Настольные', 'Stol usti', 166),
       ('CREATED', now(), 'Laptops', 'Ноутбуки', 'Noutbuklar', 166),
       ('CREATED', now(), 'Tablet computers', 'Планшетные компютеры', 'Planshet kompyuterlar', 166),
       ('CREATED', now(), 'Accessories', 'Аксессуары', 'Aksessuarlar', 166),
       ('CREATED', now(), 'Servers', 'Серверы', 'Serverlar', 166),
       ('CREATED', now(), 'Accessories', 'Комплектующие', 'Aksessuarlar', 166),
       ('CREATED', now(), 'Peripherals', 'Периферийные  устройства', 'Periferik qurilmalar', 166),
       ('CREATED', now(), 'Monitors', 'Мониторы', 'Monitorlar', 166),
       ('CREATED', now(), 'External drives', 'Внешние накопители', 'Tashqi drayvlar', 166),
       ('CREATED', now(), 'Consumables', 'Расходные материалы', 'Sarf materiallari', 166),
       ('CREATED', now(), 'Other', 'Другое', 'Boshqa', 166);

-- create default 1.6.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Accessories for TV/Video Equipment', 'Аксессуары для  ТВ/Видеотехники','TV/Video uskunalari uchun aksessuarlar', 168),
       ('CREATED', now(), 'TVs', 'Телевизоры', 'televizorlar', 168),
       ('CREATED', now(), 'Media players', 'Медиа проигрыватели', 'Media pleerlar', 168),
       ('CREATED', now(), 'Satellite TV', 'Спутниковое тв', 'Sun''iy yo''ldosh televideniesi', 168),
       ('CREATED', now(), 'Other TV/video equipment', 'Прочая тв /  видеотехника', 'Boshqa televizor/video uskunalari',
        168);

-- create default 1.6.6 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Accessories', 'Аксессуары', 'Aksessuarlar', 169),
       ('CREATED', now(), 'Cell phones', 'Мобильные телефоны', 'Mobil telefonlar', 169),
       ('CREATED', now(), 'SIM cards / tariffs / numbers', 'СSIM-kartalar / tariflar / raqamlar','СSIM-kartalar / tariflar / raqamlar', 169),
       ('CREATED', now(), 'Landline phones''', 'Стационарные  телефоны', 'Statsionar telefonlar', 169),
       ('CREATED', now(), 'Other phones', 'Прочие телефоны', 'Boshqa telefonlar', 169);

-- create default 1.6.7 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Vacuum cleaners', 'Пылисосы', 'Chang yutgichlar', 170),
       ('CREATED', now(), 'Irons', 'Утюги', 'Dazmollar', 170),
       ('CREATED', now(), 'Washing machines', 'Стиральные машины', 'Kir yuvish mashinalari', 170),
       ('CREATED', now(), 'Sewing machines and overlockers', 'Швейные машины  и оверлоки',
        'Tikuv mashinalari va overloklar', 170),
       ('CREATED', now(), 'Knitting machines', 'Вязалные машины', 'Trikotaj mashinalari', 170),
       ('CREATED', now(), 'Water filters', 'Филтры для воды', 'Suv filtrlari', 170),
       ('CREATED', now(), 'Other home appliances', 'Прочая техника  для дома', 'Boshqa maishiy texnika', 170);

-- create default 1.6.8 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'microwaves', 'Микроволновые печи', 'mikroto''lqinli pechlar', 171),
       ('CREATED', now(), 'Refrigerators', 'Холодильники', 'Muzlatgichlar', 171),
       ('CREATED', now(), 'Cookers / ovens', 'Плиты / печи', 'Pishiriqlar / pechlar', 171),
       ('CREATED', now(), 'Coffee makers/coffee grinders', 'Кофеварки / кофемолки',
        'Kofe qaynatgichlar/kofe maydalagichlar', 171),
       ('CREATED', now(), 'Food processors and choppers', 'Кухонные комбайны и  измельчители',
        'Oziq-ovqat protsessorlari va maydalagichlar', 171),
       ('CREATED', now(), 'Steamers, multicookers', 'Пароварки,  мультиварки',
        'Bug''da pishirgichlar, ko''p pishirgichlar', 171),
       ('CREATED', now(), 'Other home appliances', 'Прочая техника  для дома', 'Boshqa maishiy texnika', 171),
       ('CREATED', now(), 'Bread makers', 'Хлебопечки', 'Non pishiruvchilar', 171),
       ('CREATED', now(), 'Dishwashers', 'Посудомоечные машины', 'Idish yuvish mashinalari', 171),
       ('CREATED', now(), 'Hoods', 'Вытяжки', 'Davlumbazlar', 171),
       ('CREATED', now(), 'Other kitchen appliances', 'Прочая техника для кухни', 'Boshqa oshxona jihozlari', 171);

-- create default 1.6.9 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Film cameras', 'Пленочные фотоаппараты', 'Kino kameralar', 169),
       ('CREATED', now(), 'Digital cameras', 'Цифровые фотоаппараты', 'Raqamli kameralar', 169),
       ('CREATED', now(), 'Video cameras', 'Видеокамеры', 'Video kameralar', 169),
       ('CREATED', now(), 'Lenses', 'Объективы', 'Linzalar', 169),
       ('CREATED', now(), 'Tripods/monopods', 'Штативы / моноподы', 'Tripodlar/monopodlar', 169),
       ('CREATED', now(), 'Flashlights', 'Фотовспышки', 'Chiroqlar', 169),
       ('CREATED', now(), 'Accessories for photo/video cameras', 'Аксессуары для фото /  видеокамер',
        'Foto/video kameralar uchun aksessuarlar', 169),
       ('CREATED', now(), 'Telescopes/binoculars', 'Телескопы / бинокли', 'Teleskoplar / durbinlar', 169);

-- create default 1.8.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Construction/renovation/cleaning', 'Строительство / ремонт/ уборка',
        'Qurilish / ta''mirlash / tozalash', 8),
       ('CREATED', now(), 'Equipment maintenance and repair', 'Обслуживание,  ремонт техники',
        'Uskunalarga texnik xizmat ko''rsatish va ta''mirlash', 8),
       ('CREATED', now(), 'Auto / motorcycle services', 'Авто / мото услуги', 'Avto / mototsikl xizmatlari', 8),
       ('CREATED', now(), 'Nannies/caregivers', 'Няни / сиделки', 'Enagalar/tarbiyachilar', 8),
       ('CREATED', now(), 'Education / Sports', 'Образование / Спорт', 'Ta''lim / Sport', 8),
       ('CREATED', now(), 'Transportation / rental of transport', 'Перевозки / аренда транспорта',
        'Transportni tashish / ijaraga olish', 8),
       ('CREATED', now(), 'Selling a business', 'Продажа бизнеса', 'Biznesni sotish', 8),
       ('CREATED', now(), 'Beauty/health', 'Красота / здоровье', 'Go''zallik / salomatlik', 8),
       ('CREATED', now(), 'Entertainment / Art / Photo / Video', 'Развлечения / Искусство / Фото / Видео',
        'Ko''ngilochar / San''at / Foto / Video', 8),
       ('CREATED', now(), 'Equipment', 'Оборудование', 'Uskunalar', 8),
       ('CREATED', now(), 'Rental of goods', 'Прокат товаров', 'Tovarlarni ijaraga berish', 8),
       ('CREATED', now(), 'Advertising / printing / marketing / internet',
        'Реклама / полиграфия /  маркетинг / интернет', 'Reklama / matbaa / marketing / internet', 8),
       ('CREATED', now(), 'Raw materials/materials', 'Сырьё / материалы', 'Xom ashyo/materiallar', 8),
       ('CREATED', now(), 'Tourism', 'Туризм', 'Turizm', 8),
       ('CREATED', now(), 'Animal services', 'Услуги для животных', 'Hayvonlarga xizmat ko''rsatish', 8),
       ('CREATED', now(), 'Translation services/typing', 'Услуги переводчиков / набор текста',
        'Tarjima xizmatlari / yozish', 8),
       ('CREATED', now(), 'Financial services/partnership', 'Финансовые услуги /  партнерство',
        'Moliyaviy xizmatlar/sheriklik', 8),
       ('CREATED', now(), 'Legal services', 'Юридические услуги', 'Yuridik xizmatlar', 8),
       ('CREATED', now(), 'Other services', 'Прочие услуги', 'Boshqa xizmatlar', 8);

-- create default 1.8.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Construction services', 'Строительные услуги', 'Qurilish xizmatlari', 243),
       ('CREATED', now(), 'Finishing/repair', 'Отделка /ремонт', 'Tugatish / ta''mirlash', 243),
       ('CREATED', now(), 'Design/architecture', 'Дизайн / архитектура', 'Dizayn/arxitektura', 243),
       ('CREATED', now(), 'Manufacturing and installation of windows and doors',
        'Изготовление и  установка окон и дверей', 'Deraza va eshiklarni ishlab chiqarish va o''rnatish', 243),
       ('CREATED', now(), 'Plumbing/communications', 'Сантехника / коммуникации', 'Santexnika / aloqa', 243),
       ('CREATED', now(), 'Cleaning / Garbage removal /', 'Уборка / Вывоз мусора /',
        'Tozalash / Chiqindilarni olib tashlash /', 243),
       ('CREATED', now(), 'Ventilation/air conditioning', 'Вентиляция /  кондиционирование',
        'Shamollatish / konditsioner', 243),
       ('CREATED', now(), 'Electrics', 'Электрика', 'Elektr', 243),
       ('CREATED', now(), 'Disinfection', 'Дезинфекция', 'Dezinfektsiya', 243);

-- create default 1.8.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Appliances', 'Бытовая техника', 'Maishiy texnika', 244),
       ('CREATED', now(), 'Repair and installation of satellite TV', 'Ремонт и установка спутникового ТВ',
        'Sun''iy yo''ldosh televideniesini ta''mirlash va o''rnatish', 244),
       ('CREATED', now(), 'Mobile devices/telephony', 'Мобильные устройства / телефония', 'Mobil qurilmalar/telefoniya',
        244),
       ('CREATED', now(), 'Computer equipment/game consoles', 'Компьютерная техника / игровые приставки',
        'Kompyuter uskunalari / o''yin konsollari', 244),
       ('CREATED', now(), 'Photo / video / audio equipment', 'Фото / видео / аудио техника',
        'Foto / video / audio uskunalari', 244),
       ('CREATED', now(), 'Air conditioning equipment', 'Климатическая техника', 'Konditsioner uskunalari', 244),
       ('CREATED', now(), 'Supply, repair, maintenance of equipment', 'Поставка, ремонт,  обслуживание оборудование',
        'Uskunalarni etkazib berish, ta''mirlash, texnik xizmat ko''rsatish', 244);

-- create default 1.9.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Accessories', 'Аксессуары', 'Aksessuarlar', 9),
       ('CREATED', now(), 'For a wedding', 'Для свадбы', 'To''y uchun', 9),
       ('CREATED', now(), 'Beauty/health', 'Красота / здоровье', 'Go''zallik / salomatlik', 9),
       ('CREATED', now(), 'For Wedding', 'Для свадьбы', 'To''y uchun', 9),
       ('CREATED', now(), 'Wrist watch', 'Наручные часы', 'Qo''l soati', 9),
       ('CREATED', now(), 'Present', 'Подарки', 'Hozirgi', 9),
       ('CREATED', now(), 'Fashion miscellaneous', 'Мода разное', 'Har xil moda', 9);

-- create default 1.9.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Jewelry products', 'Ювилерные изделия', 'Zargarlik buyumlari', 278),
       ('CREATED', now(), 'Bags', 'Сумки', 'Sumkalar', 278),
       ('CREATED', now(), 'Bijouterie', 'Бижутерия', 'Bijuteriya', 278),
       ('CREATED', now(), 'Other accessories', 'Другие аксессуары', 'Boshqa aksessuarlar', 278);

-- create default 1.9.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Women''s clothing', 'Женская одежда', 'Ayollar kiyimi', 279),
       ('CREATED', now(), 'Women''s shoes', 'Женское обувь', 'Ayollar poyabzali', 279),
       ('CREATED', now(), 'Lingerie, swimwear', 'Женское белье, купалники', 'Ichki kiyim, suzish kiyimlari', 279),
       ('CREATED', now(), 'Clothes for pregnant women', 'Одежда для беременных', 'Homilador ayollar uchun kiyimlar',
        279),
       ('CREATED', now(), 'Men''s clothing', 'Мужская одежда', 'Erkaklar kiyimi', 279),
       ('CREATED', now(), 'Men''s underwear', 'Мужское белье', 'Erkaklar ichki kiyimlari', 279),
       ('CREATED', now(), 'Men''s footwear', 'Мужская обувь', 'Erkaklar poyabzali', 279),
       ('CREATED', now(), 'Hats', 'Головные уборы', 'Shlyapalar', 279);

-- create default 1.9.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Cosmetics', 'Косметика', 'Kosmetika', 280),
       ('CREATED', now(), 'Perfumery', 'Парфюмерия', 'Parfyumeriya', 280),
       ('CREATED', now(), 'Care products', 'Средства по уходу', 'G''amxo''rlik mahsulotlari', 280),
       ('CREATED', now(), 'Products for the disabled', 'Товары для инвалидов', 'Nogironlar uchun mahsulotlar', 280),
       ('CREATED', now(), 'Other beauty and health products', 'Прочие товары для  красоты и здоровья',
        'Boshqa go''zallik va sog''liq uchun mahsulotlar', 280);

-- create default 1.9.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Wedding accessories', 'Свадебные аксессуары', 'To''y aksessuarlari', 281),
       ('CREATED', now(), 'Wedding Dresses', 'Свадебные платья', 'To''y libosi', 281);

-- create default 1.10.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Antiques/collections', 'Антиквариат / коллекции', 'Antikvarlar/kollektsiyalar', 10),
       ('CREATED', now(), 'Musical instruments', 'Музыкальные инструменты', 'Musiqa asboblari', 10),
       ('CREATED', now(), 'Sports/leisure', 'Спорт / отдых', 'Sport/dam olish', 10),
       ('CREATED', now(), 'Books/magazines', 'Книги / журналы', 'Kitoblar / jurnallar', 10),
       ('CREATED', now(), 'CD / DVD / records / cassettes', 'CD / DVD / пластинки / кассеты',
        'CD / DVD / yozuvlar / kassetalar', 10),
       ('CREATED', now(), 'Tickets', 'Билеты', 'Chiptalar', 10),
       ('CREATED', now(), 'Other', 'Другое', 'Boshqa', 10);

-- create default 1.10.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Antique furniture', 'Антикварная мебель', 'Antiqa mebel', 304),
       ('CREATED', now(), 'Second-hand books', 'Букинистика', 'Ikkinchi qo''l kitoblar', 304),
       ('CREATED', now(), 'Painting', 'Живопись', 'Rasm', 304),
       ('CREATED', now(), 'Art objects', 'Предметы искусства', 'San''at ob''ektlari', 304),
       ('CREATED', now(), 'Collecting', 'Коллекционирование', 'Yig''ish', 304),
       ('CREATED', now(), 'Crafts/handicrafts', 'Поделки / рукоделие', 'Hunarmandchilik / hunarmandchilik', 304);

-- create default 1.10.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Piano/piano/grand pianos', 'Пианино / фортепиано / рояли',
        'Pianino / pianino / katta pianino', 305),
       ('CREATED', now(), 'Guitars', 'Гитары', 'Gitaralar', 305),
       ('CREATED', now(), 'Violins', 'Скрипки', 'Skripkalar', 305),
       ('CREATED', now(), 'Wind instruments', 'Духовые инструменты', 'Puflama asboblari', 305),
       ('CREATED', now(), 'Combo amplifiers', 'Комбоусилители', 'Kombo kuchaytirgichlar', 305),
       ('CREATED', now(), 'Synthesizers', 'Синтезаторы', 'Sintezatorlar', 305),
       ('CREATED', now(), 'Percussion instruments', 'Ударные инструменты', 'Perkussiya asboblari', 305),
       ('CREATED', now(), 'Studio equipment', 'Студийное оборудование', 'Studiya jihozlari', 305),
       ('CREATED', now(), 'Accessories for musical instruments', 'Аксессуары для  музыкальных инструментов',
        'Musiqa asboblari uchun aksessuarlar', 305),
       ('CREATED', now(), 'Other', 'Другое', 'Boshqa', 305);

-- create default 1.10.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Rocket Games', 'Игры с ракетой', 'Raketa o''yinlari', 306),
       ('CREATED', now(), 'Hunting Fishing', 'Охота / рыбалка', 'Ovchilik Baliq ovlash', 306),
       ('CREATED', now(), 'Tourism goods', 'Товары для туризма', 'Turizm tovarlari', 306),
       ('CREATED', now(), 'Sports nutrition', 'Спортивное питание', 'Sport ovqatlanishi', 306),
       ('CREATED', now(), 'Radio controlled models', 'Радиоуправляемые модели', 'Radio boshqariladigan modellar', 306),
       ('CREATED', now(), 'Athletics/fitness', 'Атлетика / фитнес', 'Yengil atletika/fitness', 306),
       ('CREATED', now(), 'Roller Skates', 'Роликовые коньки', 'Rolikli konki', 306),
       ('CREATED', now(), 'Skates', 'Коньки', 'Konki', 306),
       ('CREATED', now(), 'Skis/snowboards', 'Лыжи / сноуборды', 'Kayaklar/snoubordlar', 306),
       ('CREATED', now(), 'Velo', 'Вело', 'Velo', 306);

-- create default 1.11.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Dry fruit', 'Сухое фрукты', 'Quruq meva', 11),
       ('CREATED', now(), 'Juicy fruits', 'Сочные фрукты', 'Suvli mevalar', 11),
       ('CREATED', now(), 'Field products', 'Полевые продукты', 'Dala mahsulotlari', 11),
       ('CREATED', now(), 'Citrus', 'Цитрусы', 'sitrus', 11);

-- create default 1.11.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Almond', 'Миндаль', 'Bodom', 337),
       ('CREATED', now(), 'Semichka', 'Семичка', 'Semichka', 337),
       ('CREATED', now(), 'Chickpeas', 'Нут', 'No''xat', 337),
       ('CREATED', now(), 'Other', 'Другое', 'Boshqalar', 337);

-- create default 1.11.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Grape', 'Виноград', 'Uzum', 338),
       ('CREATED', now(), 'Apple', 'Яблока', 'olma', 338),
       ('CREATED', now(), 'Pomegranate', 'Гранат', 'Anor', 338),
       ('CREATED', now(), 'Pear', 'Груша', 'Nok', 338),
       ('CREATED', now(), 'Other', 'Другое', 'Boshqa', 338);

-- create default 1.11.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Potatofilter', 'Картофил', 'Kartoshka filtri', 339),
       ('CREATED', now(), 'Markovka', 'Марковка', 'Markovka', 339),
       ('CREATED', now(), 'Onion', 'Лук', 'Piyoz', 339),
       ('CREATED', now(), 'Wheat', 'Пшиница', 'Bug''doy', 339),
       ('CREATED', now(), 'Beans', 'Фасол', 'Dukkaklilar', 339),
       ('CREATED', now(), 'Watermelon', 'Арбуз', 'Tarvuz', 339),
       ('CREATED', now(), 'Other', 'Другое', 'Boshqa', 339);

-- create default 1.11.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Orange', 'Апелсин', 'apelsin', 340),
       ('CREATED', now(), 'Avocado', 'Авокадо', 'Avokado', 340),
       ('CREATED', now(), 'Lemon', 'Лимон', 'Limon', 340),
       ('CREATED', now(), 'Mandarin', 'Мандарин', 'mandarin', 340),
       ('CREATED', now(), 'Friend', 'Другое', 'Do''stim', 340);

