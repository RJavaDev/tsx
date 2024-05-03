INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Tashkent Sh.', 'Ташкент Ш.', 'Toshkent Shahar', NULL),
    ('CREATED', now(), 'Samarkand', 'Самарканд', 'Samarqand', NULL),
    ('CREATED', now(), 'Navoi', 'Навои', 'Navoiy', NULL),
    ('CREATED', now(), 'Bukhara', 'Бухара', 'Buxoro', NULL),
    ('CREATED', now(), 'Jizzakh', 'Джиззак', 'Jizzax', NULL),
    ('CREATED', now(), 'Syr Darya', 'Сыр-Дарья', 'Sirdaryo', NULL),
    ('CREATED', now(), 'Surkhandarya', 'Сурхандарья', 'Surxondaryo', NULL),
    ('CREATED', now(), 'Kashkadarya', 'Кашкадарья', 'Qashqadaryo', NULL),
    ('CREATED', now(), 'Khorezm', 'Хорезм', 'Xorazm', NULL),
    ('CREATED', now(), 'Tashkent V.', 'Ташкент В.', 'Toshkent Viloyati', NULL),
    ('CREATED', now(), 'Namangan', 'Наманган', 'Namangan', NULL),
    ('CREATED', now(), 'Ferghana', 'Фергана', 'Fargʻona', NULL),
    ('CREATED', now(), 'Andijan', 'Андижан', 'Andijon', NULL),
    ('CREATED', now(), 'Karakalpak', 'Каракалпак', 'Qoraqalpogʻiston Respublikasi', NULL);


INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Bektemir district', 'Бектемир район', 'Bektemir tumani', function_get_region_id('Ташкент Ш.')),
    ('CREATED', now(), 'Chilonzor district', 'Чиланзар район', 'Chilonzor tumani', function_get_region_id('Ташкент Ш.')),
    ('CREATED', now(), 'Mirabad district', 'Миробод район', 'Mirobod tumani', function_get_region_id('Ташкент Ш.')),
    ('CREATED', now(), 'Mirzo Ulugbek district', 'Мирзо Улугбек район', 'Mirzo Ulug''bek tumani', function_get_region_id('Ташкент Ш.')),
    ('CREATED', now(), 'Almazor district', 'Алмазар район', 'Olmazor tumani', function_get_region_id('Ташкент Ш.')),
    ('CREATED', now(), 'Sergeli district', 'Сергели район', 'Sergeli tumani', function_get_region_id('Ташкент Ш.')),
    ('CREATED', now(), 'Shaikhontohur district', 'Шайхантахур район', 'Shayhontohur tumani', function_get_region_id('Ташкент Ш.')),
    ('CREATED', now(), 'Uchtepa district', 'Учтепа район', 'Uchtepa tumani', function_get_region_id('Ташкент Ш.')),
    ('CREATED', now(), 'Yakkasaray district', 'Яккасарой район', 'Yakkasaroy tumani', function_get_region_id('Ташкент Ш.')),
    ('CREATED', now(), 'Yashnaabad district', 'Яшнабад район', 'Yashnaobod tumani', function_get_region_id('Ташкент Ш.')),
    ('CREATED', now(), 'Yunusabad district', 'Юнусабад район', 'Yunusobod tumani', function_get_region_id('Ташкент Ш.'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Bulungur district', 'Район Булунгур', 'Bulungur tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Ishtikhan district', 'Район Иштихон', 'Ishtikhan tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Jomboy district', 'Район Джамбай', 'Jomboy tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Kattakurgan city', 'Город Каттакурган', 'Kattakurgan shahri', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Kattakurgan district', 'Район Каттакурган', 'Kattakurgan tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Narpay district', 'Район Нарпай', 'Narpay tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Nurabad district', 'Район Нурабад', 'Nurabad tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Akdarya district', 'Район Акдарья', 'Akdarya tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Bas dargham district', 'Район Бас-Дархам', 'Bas-darham tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Pakhtachi district', 'Район Пахтачи', 'Pakhtachi tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Poyarik district', 'Район Поярик', 'Poyarik tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Khoshrabot district', 'Район Хошработ', 'Khoshrabot tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Samarkand city', 'Город Самарканд', 'Samarqand shahri', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Samarkand district', 'Район Самарканд', 'Samarqand tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Toylok district', 'Район Тойлок', 'Toylok tumani', function_get_region_id('Самарканд')),
    ('CREATED', now(), 'Urgut district', 'Район Ургут', 'Urgut tumani', function_get_region_id('Самарканд'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Karmana district', 'Район Кармана', 'Karmana tumani', function_get_region_id('Навои')),
    ('CREATED', now(), 'Conimex district', 'Район Конимехс', 'Konimex tumani', function_get_region_id('Навои')),
    ('CREATED', now(), 'Navbahor district', 'Район Навбахор', 'Navbahor tumani', function_get_region_id('Навои')),
    ('CREATED', now(), 'Navoi city', 'Город Навои', 'Navoi shahri', function_get_region_id('Навои')),
    ('CREATED', now(), 'Nurota district', 'Район Нурота', 'Nurota tumani', function_get_region_id('Навои')),
    ('CREATED', now(), 'Kyziltepa district', 'Район Кызылтепа', 'Kyziltepa tumani', function_get_region_id('Навои')),
    ('CREATED', now(), 'Tomdi district', 'Район Томди', 'Tomdi tumani', function_get_region_id('Навои')),
    ('CREATED', now(), 'Uzguduk district', 'Район Узгудук', 'Uzguduk tumani', function_get_region_id('Навои')),
    ('CREATED', now(), 'Khatirchi district', 'Район Хатирчи', 'Khatirchi tumani', function_get_region_id('Навои')),
    ('CREATED', now(), 'Zarafshan city', 'Город Зарафшан', 'Zarafshan shahri', function_get_region_id('Навои'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Bukhara city', 'Город Бухара', 'Buxoro shahri', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Bukhara district', 'Район Бухара', 'Buxoro tumani', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Gijduvan district', 'Район Гиждуван', 'G‘ijduvon tumani', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Jondor district', 'Район Жондор', 'Jondor tumani', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Kogon city', 'Город Когон', 'Kogon shahri', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Kogon district', 'Район Когон', 'Kogon tumani', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Olot district', 'Район Олот', 'Olot tumani', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Peshku district', 'Район Пешку', 'Peshku tumani', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Karakol district', 'Район Каракол', 'Karakul tumani', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Qarovulbazar district', 'Район Каровульбозор', 'Qorovulbozor tumani', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Romitan district', 'Район Ромитан', 'Romitan tumani', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Shafirkon district', 'Район Шафиркон', 'Shafirkon tumani', function_get_region_id('Бухара')),
    ('CREATED', now(), 'Vobkent district', 'Район Вобкент', 'Vobkent tumani', function_get_region_id('Бухара'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Arnasoy district', 'Район Арнасай', 'Arnasoy tumani',  function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Velvet district', 'Район Вельвет', 'Velvet tumani', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Friendship District', 'Район Дружба', 'Do‘stlik tumani', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Forish district', 'Район Фориш', 'Forish tumani', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Gallaorol district', 'Район Галлаорол', 'Gallaorol tumani', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'The city of Jizzakh', 'Город Джизак', 'Jizzax shahri', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Jizzakh district', 'Район Джизак', 'Jizzax tumani', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Mirzachol district', 'Район Мирзачуль', 'Mirzachul tumani', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Pakhtakor district', 'Район Пахтакор', 'Paxtakor tumani', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Yangiabad district', 'Район Янгиабад', 'Yangiobod tumani', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Zafarabad district', 'Район Зафарабад', 'Zafarobod tumani', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Zarband district', 'Район Зарбанд', 'Zarband tumani', function_get_region_id('Джиззак')),
    ('CREATED', now(), 'Zomin district', 'Район Зомин', 'Zomin tumani', function_get_region_id('Джиззак'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Boyovut district', 'Район Бойовут', 'Boyovut tumani', function_get_region_id('Сыр-Дарья')),
    ('CREATED', now(), 'Gulistan city', 'Город Гулистан', 'Guliston shahri', function_get_region_id('Сыр-Дарья')),
    ('CREATED', now(), 'Gulistan district', 'Район Гулистан', 'Guliston tumani', function_get_region_id('Сыр-Дарья')),
    ('CREATED', now(), 'Aqoltin district', 'Район Аколтин', 'Aqoltin tumani', function_get_region_id('Сыр-Дарья')),
    ('CREATED', now(), 'Sardoba district', 'Район Сардоба', 'Sardoba tumani', function_get_region_id('Сыр-Дарья')),
    ('CREATED', now(), 'Saykhunabad district', 'Район Сайхунабад', 'Sayhunobod tumani', function_get_region_id('Сыр-Дарья')),
    ('CREATED', now(), 'City of Shirin', 'Город Ширин', 'Shirin shahri', function_get_region_id('Сыр-Дарья')),
    ('CREATED', now(), 'Syrdarya district', 'Район Сырдарья', 'Sirdaryo tumani', function_get_region_id('Сыр-Дарья')),
    ('CREATED', now(), 'Khavos district', 'Район Хавос', 'Xavos tumani', function_get_region_id('Сыр-Дарья')),
    ('CREATED', now(), 'Yangiye city', 'Город Янгиёз', 'Yangiyo‘l shahri', function_get_region_id('Сыр-Дарья'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Angor district', 'Район Ангор', 'Angor tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Bandikhan district', 'Район Бандихан', 'Bandikhan tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Boysun District', 'Район Бойсун', 'Boysun tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Denov district', 'Район Денов', 'Denov tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Jarkurgan district', 'Район Жаркурган', 'Jarkurgan tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Muzrobot district', 'Район Музробот', 'Muzrobod tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Altinsoy district', 'Район Алтинсой', 'Altinsoy tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Qiziriq district', 'Район Кизирик', 'Qiziriq tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Kumkurgan district', 'Район Кумкурган', 'Kumkurgan tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Sariosia district', 'Район Сариосия', 'Sariosiya tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Sherabad district', 'Район Шерабад', 'Sherobod tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Shorchi district', 'Район Шорчи', 'Shorchi tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'The city of Termiz', 'Город Термез', 'Termiz shahri', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Termiz district', 'Район Термез', 'Termiz tumani', function_get_region_id('Сурхандарья')),
    ('CREATED', now(), 'Uzun district', 'Район Узун', 'Uzun tumani', function_get_region_id('Сурхандарья'));


INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Chirakchi district', 'Район Чиракчи', 'Chirakchi tumani', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Dehkanabad district', 'Район Дехканабад', 'Dehqonobod tumani', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Guzor district', 'Район Гузор', 'G''uzor tumani', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Kasbi district', 'Район Касби', 'Kasbi tumani', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Book district', 'Район Бук', 'Kitob tumani	', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Koson district', 'Район Косон', 'Koson tumani', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Mirishkor district', 'Район Миришкор', 'Mirishkor tumani', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Mubarak district', 'Район Муборак', 'Muborak tumani', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Target district', 'Район Таргет', 'Target tumani', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Qamashi district', 'Район Камаши', 'Qamashi tumani', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Karshi city', 'Город Карши', 'Qarshi shahri', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Karshi district', 'Район Карши', 'Qarshi tumani', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Shahrisabz city', 'Город Шахрисабз', 'Shahrisabz shahri', function_get_region_id('Кашкадарья')),
    ('CREATED', now(), 'Yakkabog district', 'Район Яккабог', 'Yakkabog tumani', function_get_region_id('Кашкадарья'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Bogot district', 'Район Богот', 'Bogot tumani', function_get_region_id('Хорезм')),
    ('CREATED', now(), 'Gurlan district', 'Район Гурлан', 'Gurlan tumani', function_get_region_id('Хорезм')),
    ('CREATED', now(), 'Koshkopir district', 'Район Кошкопир', 'Qo‘shkopir tumani', function_get_region_id('Хорезм')),
    ('CREATED', now(), 'Shavat district', 'Район Шават', 'Shavat tumani', function_get_region_id('Хорезм')),
    ('CREATED', now(), 'Urganch city', 'Город Урганч', 'Urganch shahri', function_get_region_id('Хорезм')),
    ('CREATED', now(), 'Urganch district', 'Район Урганч', 'Urganch tumani', function_get_region_id('Хорезм')),
    ('CREATED', now(), 'Khazorasp district', 'Район Хазорасп', 'Hazorasp tumani', function_get_region_id('Хорезм')),
    ('CREATED', now(), 'Khiva district', 'Район Хива', 'Xiva tumani', function_get_region_id('Хорезм')),
    ('CREATED', now(), 'Khanka district', 'Район Ханка', 'Xonqa tumani', function_get_region_id('Хорезм')),
    ('CREATED', now(), 'Yangariq district', 'Район Янгарик', 'Yangariq tumani', function_get_region_id('Хорезм')),
    ('CREATED', now(), 'Yangibozor district', 'Район Янгибозор', 'Yangibozor tumani', function_get_region_id('Хорезм'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'The city of Angren', 'Город Ангрен', 'Angren shahri', function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Bekobad city', 'Город Бекабад', 'Bekobod shahri',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Bekobad district', 'Район Бекабад', 'Bekobod tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Boka district', 'Район Бока', 'Boka tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Bostanliq district', 'Район Бостанлик', 'Bostanlik tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Chinoz district', 'Район Чиноз', 'Chinoz tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Chirchik city', 'Город Чирчик', 'Chirchiq shahri',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Middle Chirchik district', 'Район Чирчик ортаси', 'Chirchiq markazi tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Ohangaron district', 'Район Оҳангарон', 'Ohangaron tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Almalyk city', 'Город Алмалык', 'Almalyk shahri',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Akkurgan district', 'Район Аккурган', 'Akkurgan tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Parkent district', 'Район Паркент', 'Parkent tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Piskent district', 'Район Пискент', 'Piskent tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Qibray district', 'Район Қибрай', 'Qibray tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Lower Chirchik district', 'Район Чирчик паста', 'Chirchiq pastki tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Yangiyol district', 'Район Янгиёл', 'Yangiyul tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Upper Chirchik district', 'Район Чирчик уста', 'Chirchiq yuqori tumani',  function_get_region_id('Ташкент В.')),
    ('CREATED', now(), 'Zangiota District', 'Район Зангиота', 'Zangiota tumani',  function_get_region_id('Ташкент В.'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Chortok district', 'Район Чорток', 'Chortoq tumani',  function_get_region_id('Наманган')),
    ('CREATED', now(), 'Chust district', 'Район Чуст', 'Chust tumani', function_get_region_id('Наманган')),
    ('CREATED', now(), 'Kosonsoy district', 'Район Косонсой', 'Kosonsoy tumani', function_get_region_id('Наманган')),
    ('CREATED', now(), 'Mingbulok district', 'Район Мингбулок', 'Mingbuloq tumani', function_get_region_id('Наманган')),
    ('CREATED', now(), 'Namangan city', 'Город Наманган', 'Namangan shahri', function_get_region_id('Наманган')),
    ('CREATED', now(), 'Namangan district', 'Район Наманган', 'Namangan tumani', function_get_region_id('Наманган')),
    ('CREATED', now(), 'Norin district', 'Район Норин', 'Norin tumani', function_get_region_id('Наманган')),
    ('CREATED', now(), 'Pop district', 'Район Поп', 'Pop tumani', function_get_region_id('Наманган')),
    ('CREATED', now(), 'Torakurgan district', 'Район Торакурган', 'Torakurgan tumani', function_get_region_id('Наманган')),
    ('CREATED', now(), 'Uchkurgan district', 'Район Учкурган', 'Uchkurgan tumani', function_get_region_id('Наманган')),
    ('CREATED', now(), 'Uychi district', 'Район Уйчи', 'Uychi tumani', function_get_region_id('Наманган')),
    ('CREATED', now(), 'Yangigorgon district', 'Район Янгигоргон', 'Yangig‘orgon tumani', function_get_region_id('Наманган'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Beshariq district', 'Район Бешарик', 'Beshariq tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Baghdad district', 'Район Багдад', 'Bag‘dod tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Buvaida district', 'Район Бувайда', 'Buvaydo tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Dangara district', 'Район Дангара', 'Dangara tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Fergana city', 'Город Фергана', 'Farg‘ona shahri', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Fergana district', 'Район Фергана', 'Farg‘ona tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Furqat district', 'Район Фуркат', 'Furqat tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Margilan city', 'Город Маргилан', 'Marg‘ilon shahri', function_get_region_id('Фергана')),
    ('CREATED', now(), 'District of Uzbekistan', 'Район Узбекистан', 'O‘zbekiston tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Altiariq district', 'Район Алтиарик', 'Oltiariq tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Kokan city', 'Город Коканд', 'Qo‘qon shahri', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Koshtepa district', 'Район Коштепа', 'Qo‘stipa tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Kuva district', 'Район Кува', 'Qo‘va tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Kuvasoy city', 'Город Кувасой', 'Qo‘vasoy shahri', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Rishton district', 'Район Риштан', 'Rishton tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Sokh district', 'Район Сох', 'So‘x tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Toshloq district', 'Район Тошлок', 'Toshloq tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Uchkoprik district', 'Район Учкоприк', 'Uchko‘prik tumani', function_get_region_id('Фергана')),
    ('CREATED', now(), 'Yozhiovon district', 'Район Ёжиовон', 'Yo‘jiovon tumani', function_get_region_id('Фергана'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Andijan city', 'Город Андижан', 'Andijon shahri', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Andijan district', 'Район Андижан', 'Andijon tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Asaka District', 'Район Асака', 'Asaka tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Fisherman district', 'Район Рыбак', 'Baliqchi tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Boz district', 'Район Боз', 'Bo‘z tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Bulagboshi district', 'Район Булагбоши', 'Bulog‘bo‘shi tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Izboskan district', 'Район Избоскан', 'Izboskan tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Jalalquduk district', 'Район Жалалкудук', 'Jalolkuduk tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Merhamat district', 'Район Мерхамат', 'Marhamat tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Oltinkol district', 'Район Олтинкол', 'Oltinko‘l tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Pakhtaabad district', 'Район Пахтаабад', 'Paxtaobod tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Korgontepa district', 'Район Коргонтепа', 'Qo‘rg‘ontepa tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Shahrikhan district', 'Район Шахрихан', 'Shahrihon tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Ulughnor district', 'Район Улугнор', 'Ulug‘nor tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Khojaabad district', 'Район Ходжаабад', 'Xo‘jaobod tumani', function_get_region_id('Андижан')),
    ('CREATED', now(), 'Khanabad city', 'Город Ханабад', 'Xonobod shahri', function_get_region_id('Андижан'));

INSERT INTO tsx_region(status, created_date, name_en, name_ru, name_uz, parent_id)
VALUES
    ('CREATED', now(), 'Amudarya district', 'Район Амударья', 'Amudaryo tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Beruni district', 'Район Беруни', 'Beruniy tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Chimboy district', 'Район Чимбой', 'Chimboy tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Ellikkala district', 'Район Элликала', 'Ellikala tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Kegeili district', 'Район Кегейли', 'Kegeyli tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Moynak district', 'Район Мойнак', 'Mo‘ynoq tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'The city of Nukus', 'Город Нукус', 'Nukus shahri', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Nukus district', 'Район Нукус', 'Nukus tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Konlikol district', 'Район Конликол', 'Qonliko‘l tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Karauzak district', 'Район Караузак', 'Qorao‘zak tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Kungirot district', 'Район Кунгирот', 'Qo‘ng‘irot tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Shumanay district', 'Район Шуманай', 'Shumanay tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Takhiatosh city', 'Город Тахиаташ', 'To‘ytepa shahri', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Takhtakorpir district', 'Район Тахтакупыр', 'To‘xtako‘pir tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Tortkol district', 'Район Торткол', 'Tortko‘l tumani', function_get_region_id('Каракалпак')),
    ('CREATED', now(), 'Khojaly district', 'Район Ходжали', 'Xo‘jali tumani', function_get_region_id('Каракалпак'));