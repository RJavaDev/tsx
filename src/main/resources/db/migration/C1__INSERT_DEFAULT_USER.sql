-- create default user
INSERT INTO tsx_user(status, created_date, firstname,lastname, password, role_enum_list, email_or_phone)
VALUES
      ('CREATED', now(), 'admin','admin','$2a$10$U2vm/KOlGwVaqXW551e.zOlaAgrJ/PflMDlxNjBEapDvl2m4fljYW','{ADMIN}', '+9918')
