-- create default user
INSERT INTO tsx_user(status, created_date, firstname,lastname,phone_number, password, role_enum_list, username)
VALUES
      ('CREATED', now(), 'admin','admin','+998993189918','$2a$10$U2vm/KOlGwVaqXW551e.zOlaAgrJ/PflMDlxNjBEapDvl2m4fljYW','{ADMIN}', 'admin')
