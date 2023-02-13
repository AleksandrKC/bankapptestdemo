insert into users(username, password, email) values ( 'user','$2a$10$1QTRfI4oDghjc/YQmUvb7u.uku.OUMbOt0ySl1ChclpiGQVoSNQUG','someemailu@mailaa.ru');
insert into users(username, password, email) values ( 'manager','$2a$10$1QTRfI4oDghjc/YQmUvb7u.uku.OUMbOt0ySl1ChclpiGQVoSNQUG','manager@gmailaa.com' );
insert into users(username, password, email) values ( 'admin','$2a$10$1QTRfI4oDghjc/YQmUvb7u.uku.OUMbOt0ySl1ChclpiGQVoSNQUG','admin@mailaa.com' );
insert into roles (name) values ('ROLE_USER');
insert into roles (name) values ('ROLE_MANAGER');
insert into roles (name) values ('ROLE_ADMIN');

insert into users_roles (user_id, role_id)
select id, (select id from roles where name='ROLE_USER')
from users where username = 'user';

insert into users_roles (user_id, role_id)
select id, (select id from roles where name='ROLE_MANAGER')
from users where username = 'manager';

insert into users_roles (user_id, role_id)
select id, (select id from roles where name='ROLE_ADMIN')
from users where username = 'admin';