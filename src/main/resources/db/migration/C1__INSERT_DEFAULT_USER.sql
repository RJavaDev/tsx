-- create default user
INSERT INTO tsx_user(status, created_date, firstname,lastname, password, role_enum_list, email_or_phone)
VALUES
      ('CREATED', now(), 'admin','admin','$2a$10$U2vm/KOlGwVaqXW551e.zOlaAgrJ/PflMDlxNjBEapDvl2m4fljYW','{ADMIN}', '+9918'),
      ('CREATED', now(), 'admin','admin','$2a$10$U2vm/KOlGwVaqXW551e.zOlaAgrJ/PflMDlxNjBEapDvl2m4fljYW','{ADMIN}', '+998997558142')
ON CONFLICT(email_or_phone)
DO UPDATE
    SET status = excluded.status, created_date = now(), firstname = excluded.firstname,
        lastname = excluded.lastname, password = excluded.password, role_enum_list = excluded.role_enum_list,
        email_or_phone = excluded.email_or_phone
