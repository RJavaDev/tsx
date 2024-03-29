--create default attach
INSERT INTO tsx_attach (id, content_type, created_date, origin_name, path, size, type)
VALUES ('7d5ade1d-0950-4785-bd73-d85430080a46', 'image/jpeg', now(), 'Image from wp-s.ru (1).jpg', 'images/category',
        308630, 'jpg');

-- create default 1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, attach_id)
VALUES
       ('CREATED', now(), 'Transportation', 'Транспорт', 'Transport', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Real estate', 'Недвижимость', 'Ko''chmas mulk', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Children''s', 'Детиский мир', 'Bolalar uchun', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Job', 'Работа', 'Ish', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Animals', 'Животные', 'Hayvonlar', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'House and garden', 'дом и сад', 'Uy va bog''', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Electronics', 'Электроника', 'Elektronika', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Business and services', 'Бизнес и услуги', 'Biznes va xizmatlar', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Fashion and Style', 'Мода и стиль', 'Moda va uslub', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Hobbies, recreation and sports', 'Хобби, отдых и спорт', 'Хevimli mashg''ulotlar, dam olish va sport', '7d5ade1d-0950-4785-bd73-d85430080a46'),
       ('CREATED', now(), 'Agricultural products', 'Сельхоз продукты', 'Qishloq xo''jaligi mahsulotlari','7d5ade1d-0950-4785-bd73-d85430080a46');

-- create default 1.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
      ('CREATED', now(), 'Cars', 'Легковые автомобили', 'Avtomobillar', function_getId('Transportation')),
      ('CREATED', now(), 'Auto parts and accessories', 'Автозапчасти и аксессуары','Avto ehtiyot qismlar va aksessuarlar', function_getId('Transportation')),
     ('CREATED', now(), 'Tires, rims and wheels', 'Шины, диски и колёса', 'Shinalar, jantlar va g''ildiraklar', function_getId('Transportation')),
      ('CREATED', now(), 'Moto', 'Moto', 'Moto', function_getId('Transportation')),
      ('CREATED', now(), 'Motorcycle parts and accessories', 'Мотозапчасти и аксессуары', 'Mototsikl qismlari va aksessuarlari', function_getId('Transportation')),
     ('CREATED', now(), 'Buses', 'Автобусы', 'Avtobuslar', function_getId('Transportation'));

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
      ('CREATED', now(), 'Car recorders', 'Авторегистраторы', 'Avtomobil yozuvchilari', function_getid('Auto parts and accessories'));

-- create default 1.1.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
       ('CREATED', now(), 'Motorcycle tires', 'Мотошины', 'Mototsikl shinalari', function_getid('Tires, rims and wheels')),
       ('CREATED', now(), 'Discs', 'Диски', 'Disklar', function_getid('Tires, rims and wheels')),
       ('CREATED', now(), 'Wheel assembly', 'Колесо в сборе', 'G''ildirak yig''ish', function_getid('Tires, rims and wheels')),
       ('CREATED', now(), 'Caps', 'Колпаки', 'Qopqoqlar', function_getid('Tires, rims and wheels'));

-- create default 1.1.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Mopeds/scooters', 'Мопеды / скутеры', 'Mopedlar/skuterlar', function_getid('Moto')),
       ('CREATED', now(), 'ATVs', 'Квадроциклы', 'ATVlar', function_getid('Moto')),
       ('CREATED', now(), 'Motorcycles', 'Мотоциклы', 'Mototsikllar', function_getid('Moto')),
       ('CREATED', now(), 'Moto - other', 'Мото -прочее', 'Moto - boshqa', function_getid('Moto'));

-- create default 1.1.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Motorcycle parts and accessories', 'Мотозапчасти и аксессуары','Mototsikl qismlari va aksessuarlari',
        function_getid('Motorcycle parts and accessories')),
       ('CREATED', now(), 'Motorcycle parts', 'Мотозапчасти', 'Mototsikl qismlari',   function_getid('Motorcycle parts and accessories')),
       ('CREATED', now(), 'Moto equipment', 'Мотоэкипировка', 'Motor uskunalari',   function_getid('Motorcycle parts and accessories')),
       ('CREATED', now(), 'Moto accessories', 'Мото аксессуары', 'Moto aksessuarlar',   function_getid('Motorcycle parts and accessories'));

-- create default 1.2.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
       ('CREATED', now(), 'Daily rental', 'Посуточная аренда', 'Kunlik ijara',  function_getid('Real estate')),
       ('CREATED', now(), 'Apartments', 'Квартиры', 'Kvartiralar',  function_getid('Real estate')),
       ('CREATED', now(), 'At home', 'Дома', 'Uy',  function_getid('Real estate')),
       ('CREATED', now(), 'Earth', 'Земля', 'Yer',  function_getid('Real estate')),
       ('CREATED', now(), 'Garages/parking lots', 'Гаражи / стоянки', 'Garajlar / to''xtash joylari',  function_getid('Real estate')),
       ('CREATED', now(), 'Commercial premises', 'Коммерческие помешения', 'Tijorat binolari',  function_getid('Real estate'));

-- create default 1.2.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Hostels', 'Хостелы', 'Mehmonxonalar',  function_getid('Daily rental')),
       ('CREATED', now(), 'Hotels / Hotels', 'Гостиницы / Отели', 'Mehmonxonalar / mehmonxonalar',  function_getid('Daily rental')),
       ('CREATED', now(), 'Apartments', 'Квартиры', 'Kvartiralar',  function_getid('Daily rental')),
       ('CREATED', now(), 'Houses / Cottages', 'Дома / Дачи', 'Uylar / kottejlar',  function_getid('Daily rental')),
       ('CREATED', now(), 'Sanatoriums / Holiday homes', 'Санатории / Дома отдыха', 'Sanatoriylar / Dam olish uylari',
        function_getid('Daily rental'));

-- create default 1.2.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
      ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara',  function_getid('Apartments')),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', function_getid('Apartments'));

-- create default 1.2.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
       ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', function_getid('At home')),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', function_getid('At home'));

-- create default 1.2.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', function_getid('Earth')),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', function_getid('Earth'));

-- create default 1.2.6 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', function_getid('Garages/parking lots')),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', function_getid('Garages/parking lots'));

-- create default 1.2.6 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Long-term rent', 'Аренда долгосрочня', 'Uzoq muddatli ijara', function_getid('Commercial premises')),
       ('CREATED', now(), 'Sale', 'Продожа', 'Sotish', function_getid('Commercial premises'));

-- create default 1.3.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Baby clothes', 'Детская одежда', 'Bolalar kiyimi',  function_getid('Children''s')),
       ('CREATED', now(), 'Children''s shoes', 'Детская обув', 'Bolalar poyabzali', function_getid('Children''s')),
       ('CREATED', now(), 'Child car seats', 'Детские автокресла', 'Bolalar uchun avtomobil o''rindiqlari', function_getid('Children''s')),
       ('CREATED', now(), 'Baby strollers', 'Детские коляски', 'Bolalar aravachalari', function_getid('Children''s')),
       ('CREATED', now(), 'Children''s furniture', 'Детские мебель', 'Bolalar mebellari', function_getid('Children''s')),
       ('CREATED', now(), 'Children''s transport', 'Детский транспорт', 'Bolalar transporti', function_getid('Children''s')),
       ('CREATED', now(), 'Toys', 'Игрушки', 'O''yinchoqlar', function_getid('Children''s')),
       ('CREATED', now(), 'Feeding', 'Кормление', 'Oziqlantirish', function_getid('Children''s')),
       ('CREATED', now(), 'Baby Clothing', 'Одежда для новорождённых', 'Chaqaloq kiyimlari', function_getid('Children''s')),
       ('CREATED', now(), 'Other children''s products', 'Прочие детские товары', 'Boshqa bolalar mahsulotlari', function_getid('Children''s')),
       ('CREATED', now(), 'Goods for schoolchildren', 'Товары для школьников', 'Maktab o''quvchilari uchun tovarlar',
        function_getid('Children''s'));

-- create default 1.3.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Clothes for boys', 'Одежда для малчиков', 'O''g''il bolalar uchun kiyimlar', function_getid('Baby clothes')),
       ('CREATED', now(), 'Girls clothes', 'Одежда для девочек', 'Qizlar kiyimlari', function_getid('Baby clothes')),
       ('CREATED', now(), 'Baby Clothing', 'Одежда для  новорожденных', 'Chaqaloq kiyimlari', function_getid('Baby clothes'));

-- create default 1.3.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Shoes for girls', 'Обувь для девочек', 'Qizlar uchun poyabzal', function_getid('Children''s shoes')),
       ('CREATED', now(), 'Shoes for babies', 'Обувь для малышей', 'Chaqaloqlar uchun poyabzal', function_getid('Children''s shoes')),
       ('CREATED', now(), 'Shoes for boys', 'Обувь для мальчиков', 'O''g''il bolalar uchun poyabzal', function_getid('Children''s shoes'));

-- create default 1.4.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), '"IT" / Telecom / Computers', '"IT" / Телеком / Компьютеры', '"IT" / Telekom / Kompyuterlar', function_getid('Job')),
       ('CREATED', now(), 'Bars/restaurants', 'Бары / рестораны', 'Barlar/restoranlar', function_getid('Job')),
       ('CREATED', now(), 'Household staff', 'Домашный персонал', 'Uy xodimlari', function_getid('Job')),
       ('CREATED', now(), 'Other areas of occupation', 'Другие сферы занятий', 'Boshqa kasbiy sohalar', function_getid('Job')),
       ('CREATED', now(), 'Beauty/fitness/sports', 'Красота / фитнес /спорт', 'Go''zallik / fitnes / sport', function_getid('Job')),
       ('CREATED', now(), 'Culture/art', 'Култура / искусство', 'Madaniyat/san''at', function_getid('Job')),
       ('CREATED', now(), 'Marketing / Advertising / Design', 'Маркетинг / реклама / дизай', 'Marketing / Reklama / Dizayn', function_getid('Job')),
       ('CREATED', now(), 'Medicine/pharmacy', 'Медицина / фармация', 'Tibbiyot/dorixona', function_getid('Job')),
       ('CREATED', now(), 'Starting a Career / Students', 'Начало карьеры / Студенты',  'Ishga kirishish / Talabalar', function_getid('Job')),
       ('CREATED', now(), 'Real estate', 'Недвижимость', 'Ko''chmas mulk', function_getid('Job')),
       ('CREATED', now(), 'Education', 'Образование', 'Ta''lim', function_getid('Job')),
       ('CREATED', now(), 'Security/Security', 'Охрана / безопастность', 'O''g''il bolalar uchun poyabzal', function_getid('Job')),
       ('CREATED', now(), 'Manufacturing/energy', 'Производство / энергетика', 'O''g''il bolalar uchun poyabzal', function_getid('Job')),
       ('CREATED', now(), 'Retail / Sales', 'Розничная торговля / Продажа', 'O''g''il bolalar uchun poyabzal', function_getid('Job')),
       ('CREATED', now(), 'Secretariat / ACS', 'Секретариат / АХО', 'O''g''il bolalar uchun poyabzal', function_getid('Job')),
       ('CREATED', now(), 'Service and life', 'Сервис и быт', 'O''g''il bolalar uchun poyabzal', function_getid('Job')),
       ('CREATED', now(), 'Transport/logistics', 'Транспорт / логистика', 'O''g''il bolalar uchun poyabzal', function_getid('Job')),
       ('CREATED', now(), 'Construction', 'Строительство', 'O''g''il bolalar uchun poyabzal', function_getid('Job')),
       ('CREATED', now(), 'Part-time employment', 'Частичная занятость', 'O''g''il bolalar uchun poyabzal', function_getid('Job')),
       ('CREATED', now(), 'Law and Accounting', 'Юриспруденция и бухгалтерия', 'O''g''il bolalar uchun poyabzal', function_getid('Job'));

-- create default 1.5.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Dogs', 'Собаки', 'Itlar', function_getid('Animals')),
       ('CREATED', now(), 'Cow', 'Корова', 'Sigir', function_getid('Animals')),
       ('CREATED', now(), 'Fish', 'Рыба', 'Baliq', function_getid('Animals')),
       ('CREATED', now(), 'Cats', 'Кошки', 'Mushuklar', function_getid('Animals')),
       ('CREATED', now(), 'Rooster', 'Петух', 'Xo''roz', function_getid('Animals')),
       ('CREATED', now(), 'Rodents', 'Грызуны', 'Kemiruvchilar', function_getid('Animals')),
       ('CREATED', now(), 'Birds', 'Птицы', 'Qushlar', function_getid('Animals')),
       ('CREATED', now(), 'Pet supplies', 'Зоотовары', 'Uy hayvonlari uchun materiallar', function_getid('Animals')),
       ('CREATED', now(), 'Animals for free', 'Животные даром', 'Hayvonlar bepul', function_getid('Animals')),
       ('CREATED', now(), 'Mating', 'Вязка', 'Juftlash', function_getid('Animals')),
       ('CREATED', now(), 'Aquarium', 'Аквариумистика', 'Akvarium', function_getid('Animals')),
       ('CREATED', now(), 'Lost and found', 'Бюро находок', 'Yo''qolgan va topilgan', function_getid('Animals')),
       ('CREATED', now(), 'Farm animals', 'Сельхоз животные', 'Ferma hayvonlari', function_getid('Animals')),
       ('CREATED', now(), 'Other animals', 'Другие животные', 'Boshqa hayvonlar', function_getid('Animals'));

-- create default 1.6.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Tools', 'Инструменты', 'Asboblar', function_getid('House and garden')),
       ('CREATED', now(), 'Products for construction/repair', 'Товары для строительства /ремонта',
        'Qurilish/ta''mirlash uchun mahsulotlar',  function_getid('House and garden')),
       ('CREATED', now(), 'Interior items', 'Предметы интерьера', 'Ichki buyumlar',  function_getid('House and garden')),
       ('CREATED', now(), 'Furniture', 'Мебель', 'Mebel',  function_getid('House and garden')),
       ('CREATED', now(), 'Houseplants', 'Комнатные растения', 'Uy o''simliklari',  function_getid('House and garden')),
       ('CREATED', now(), 'Food/Drinks', 'Продукты питания / Напитки', 'Oziq-ovqat/ichimliklar',  function_getid('House and garden')),
       ('CREATED', now(), 'Other household goods', 'Прочие товары для дома', 'Boshqa uy-ro''zg''or buyumlari',  function_getid('House and garden')),
       ('CREATED', now(), 'Garden Garden', 'Сад / огород', 'Bog ''',  function_getid('House and garden')),
       ('CREATED', now(), 'garden tools', 'Садовый инвентарь', 'Bog '' asboblari',  function_getid('House and garden')),
       ('CREATED', now(), 'Household products', 'Товары для дома', 'Uy-ro''zg''or buyumlari',  function_getid('House and garden')),
       ('CREATED', now(), 'Tires, rims and wheels', 'Шины, диски и колёса', 'Shinalar, jantlar va g''ildiraklar',  function_getid('House and garden')),
       ('CREATED', now(), 'Crockery/kitchenware', 'Посуда / кухонная утварь', 'Idishlar/oshxona anjomlari',  function_getid('House and garden')),
       ('CREATED', now(), 'Household equipment / household chemicals', 'Хозяйственный инвентарь / бытовая химия',
        'Uy jihozlari / uy kimyoviy moddalari',  function_getid('House and garden')),
       ('CREATED', now(), 'Stationery/consumables', 'Канцтовары /расходные материалы',
        'Kantselyariya buyumlari/sarflanadigan materiallar',  function_getid('House and garden'));

-- create default 1.6.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Hand tool', 'Ручной инструмент', 'Qo''l asbobi',  function_getid('Tools')),
       ('CREATED', now(), 'Gasoline tools', 'Бензоинструмент', 'Benzinli asboblar',  function_getid('Tools')),
       ('CREATED', now(), 'Power tools', 'Электроинструмент', 'Elektr asboblari',  function_getid('Tools')),
       ('CREATED', now(), 'Pneumatic tools', 'Пневмоинструмент', 'Pnevmatik asboblar',  function_getid('Tools')),
       ('CREATED', now(), 'Other tool', 'Прочий инструмент', 'Boshqa vosita',  function_getid('Tools'));

-- create default 1.6.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Plumbing', 'Сантехника', 'Santexnika',  function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Vintelation', 'Винтеляция', 'Vintelatsiya', function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Heating', 'Отопления', 'Isitish', function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Electrics', 'Электрика', 'Elektr', function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Lumber', 'Пиломатериалы', 'Yog''och', function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Finishing and facing materials', 'Отделочные и  облицовочные материалы',
        'Tugatish va qoplama materiallari', function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Windows/doors/glass/mirrors', 'Окна/двери/стекло/ зеркала',
        'Derazalar / eshiklar / oynalar / oynalar', function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Paints and varnishes', 'Лакокрасочные материалы', 'Bo''yoqlar va laklar', function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Rolled metal / fittings', 'Металлопрокат / арматура', 'Metall prokat / armatura', function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Elements / fasteners', 'Элементы / крепежа', 'Elementlar / mahkamlagichlar', function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Brick/concrete/foam blocks', 'Кирпич/бетон/пеноблоки', 'G''isht / beton / ko''pikli bloklar',
        function_getid('Products for construction/repair')),
       ('CREATED', now(), 'Other construction materials', 'Прочие стройматерилаы', 'Boshqa qurilish materiallari ',
        function_getid('Products for construction/repair'));

-- create default 1.6.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Lamps', 'Светильники', 'Yoritgichlar', function_getid('Interior items')),
       ('CREATED', now(), 'Textile', 'Текстиль', 'To''qimachilik', function_getid('Interior items')),
       ('CREATED', now(), 'Window decor', 'Декор окон', 'Deraza dekoratsiyasi', function_getid('Interior items')),
       ('CREATED', now(), 'Other interior items', 'Иные предметы интерьера', 'Boshqa ichki buyumlar', function_getid('Interior items'));

-- create default 1.6.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Living room furniture', 'Мебель для гостиной', 'Yashash xonasi uchun mebel', function_getid('Furniture')),
       ('CREATED', now(), 'Bedroom furniture', 'Мебель для спальни', 'Yotoq xonasi mebellari', function_getid('Furniture')),
       ('CREATED', now(), 'Hallway furniture', 'Мебель для прихожей', 'Koridor mebellari', function_getid('Furniture')),
       ('CREATED', now(), 'Kitchen furniture', 'Кухонная мебель', 'Oshxona mebellari', function_getid('Furniture')),
       ('CREATED', now(), 'Bathroom furniture', 'Мебель для ванной  комнаты', 'Hammom uchun mebel', function_getid('Furniture')),
       ('CREATED', now(), 'Office furniture', 'Офисная мебель', 'Ofis mebellari', function_getid('Furniture')),
       ('CREATED', now(), 'Custom-made furniture', 'Мебель на заказ', 'Buyurtma qilingan mebel', function_getid('Furniture')),
       ('CREATED', now(), 'garden furniture', 'Садовая мебель', 'bog ''mebellari', function_getid('Furniture')),
       ('CREATED', now(), 'Specialized furniture', 'Специализированная  мебель', 'Maxsus mebel', function_getid('Furniture'));

-- create default 1.7.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
       ('CREATED', now(), 'Audio equipment', 'Аудиотехника', 'Audio uskunalar', function_getid('Electronics')),
       ('CREATED', now(), 'Games and game consoles', 'Игры и игровые приставки', 'O''yinlar va o''yin konsollari', function_getid('Electronics')),
       ('CREATED', now(), 'Computers', 'Компьютеры', 'Kompyuterlar', function_getid('Electronics')),
       ('CREATED', now(), 'Individual care', 'Индивидуальный уход', 'Shaxsiy g''amxo''rlik', function_getid('Electronics')),
       ('CREATED', now(), 'TV/video equipment', 'Тв / видеотехника', 'Televizor/video uskunalari', function_getid('Electronics')),
       ('CREATED', now(), 'Phones', 'Телефоны', 'Telefonlar', function_getid('Electronics')),
       ('CREATED', now(), 'Home Appliances', 'Техника для дома', 'Maishiy texnika', function_getid('Electronics')),
       ('CREATED', now(), 'Kitchen appliances', 'Техника для кухни', 'Oshxona jihozlari', function_getid('Electronics')),
       ('CREATED', now(), 'Photo/video', 'Фото / видео', 'Foto/video', function_getid('Electronics')),
       ('CREATED', now(), 'Accessories and components', 'Аксессуары и  комплектуюшие', 'Aksessuarlar va komponentlar', function_getid('Electronics')),
       ('CREATED', now(), 'Climatic equipment', 'Климатическое  оборудовование', 'Iqlim uskunalari', function_getid('Electronics')),
       ('CREATED', now(), 'Other electronics', 'Прочая электроника', 'Boshqa elektronika', function_getid('Electronics'));

-- create default 1.7.1 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'MP3 players', 'MP3 плееры', 'MP3 pleerlar', function_getid('Audio equipment')),
       ('CREATED', now(), 'Radio tape recorders', 'Магнитолы', 'Radio magnitafonlari',  function_getid('Audio equipment')),
       ('CREATED', now(), 'Music centers', 'Музыкалные центры', 'Musiqa markazlari',  function_getid('Audio equipment')),
       ('CREATED', now(), 'Acustic systems', 'Акустические системы', 'Akustik tizimlar',  function_getid('Audio equipment')),
       ('CREATED', now(), 'Radios', 'Радиоприемники', 'Radiolar',  function_getid('Audio equipment')),
       ('CREATED', now(), 'Portable speakers', 'Портативная акустика', 'Portativ dinamiklar',  function_getid('Audio equipment')),
       ('CREATED', now(), 'Amplifiers/receivers', 'Усилители / ресиверы', 'Kuchaytirgichlar/qabul qiluvchilar',  function_getid('Audio equipment')),
       ('CREATED', now(), 'CD/MD/Vinyl players', 'CD / md / виниловы проигрыватели', 'CD/MD/vinil pleyerlar',  function_getid('Audio equipment')),
       ('CREATED', now(), 'Other', 'Другие', 'Boshqa',  function_getid('Audio equipment')),
       ('CREATED', now(), 'Other audio equipment', 'Прочая аудиотехника', 'Boshqa audio uskunalar',  function_getid('Audio equipment'));

-- create default 1.7.2 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Console games', 'Игры для приставок', 'Konsol o''yinlar', function_getid('MP3 players')),
       ('CREATED', now(), 'Consoles', 'Приставки', 'Konsollar', function_getid('MP3 players')),
       ('CREATED', now(), 'PC Games', 'Игры для PC', 'Kompyuter o''yinlari', function_getid('MP3 players')),
       ('CREATED', now(), 'Other', 'Другие', 'Boshqa', function_getid('MP3 players')),
       ('CREATED', now(), 'Accessories', 'Аксессуары', 'Aksessuarlar', function_getid('MP3 players'));

-- create default 1.7.3 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Razors, epilators, hair clippers', 'Бритвы, эпиляторы, машинки для стрижки',
        'Usra, epilator, soch kesgich', function_getid('Radio tape recorders')),
       ('CREATED', now(), 'Hair dryers, hair styling', 'Фены, укладка волос', 'Soch quritgichlari, soch turmagi', function_getid('Radio tape recorders')),
       ('CREATED', now(), 'Scales', 'Весы', 'Tarozilar', function_getid('Radio tape recorders')),
       ('CREATED', now(), 'Other personal care equipment', 'Прочая техника для  индивидуального ухода',
        'Boshqa shaxsiy parvarish uskunalari', function_getid('Radio tape recorders'));

-- create default 1.7.4 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Tabletop', 'Настольные', 'Stol usti', function_getid('Radio tape recorders')),
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

-- create default 1.7.5 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Accessories for TV/Video Equipment', 'Аксессуары для  ТВ/Видеотехники','TV/Video uskunalari uchun aksessuarlar', 168),
       ('CREATED', now(), 'TVs', 'Телевизоры', 'televizorlar', 168),
       ('CREATED', now(), 'Media players', 'Медиа проигрыватели', 'Media pleerlar', 168),
       ('CREATED', now(), 'Satellite TV', 'Спутниковое тв', 'Sun''iy yo''ldosh televideniesi', 168),
       ('CREATED', now(), 'Other TV/video equipment', 'Прочая тв /  видеотехника', 'Boshqa televizor/video uskunalari',
        168);

-- create default 1.7.6 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Accessories', 'Аксессуары', 'Aksessuarlar', 169),
       ('CREATED', now(), 'Cell phones', 'Мобильные телефоны', 'Mobil telefonlar', 169),
       ('CREATED', now(), 'SIM cards / tariffs / numbers', 'СSIM-kartalar / tariflar / raqamlar','СSIM-kartalar / tariflar / raqamlar', 169),
       ('CREATED', now(), 'Landline phones''', 'Стационарные  телефоны', 'Statsionar telefonlar', 169),
       ('CREATED', now(), 'Other phones', 'Прочие телефоны', 'Boshqa telefonlar', 169);

-- create default 1.7.7 category
INSERT INTO tsx_category(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES ('CREATED', now(), 'Vacuum cleaners', 'Пылисосы', 'Chang yutgichlar', 170),
       ('CREATED', now(), 'Irons', 'Утюги', 'Dazmollar', 170),
       ('CREATED', now(), 'Washing machines', 'Стиральные машины', 'Kir yuvish mashinalari', 170),
       ('CREATED', now(), 'Sewing machines and overlockers', 'Швейные машины  и оверлоки',
        'Tikuv mashinalari va overloklar', 170),
       ('CREATED', now(), 'Knitting machines', 'Вязалные машины', 'Trikotaj mashinalari', 170),
       ('CREATED', now(), 'Water filters', 'Филтры для воды', 'Suv filtrlari', 170),
       ('CREATED', now(), 'Other home appliances', 'Прочая техника  для дома', 'Boshqa maishiy texnika', 170);

-- create default 1.7.8 category
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

-- create default 1.7.9 category
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

