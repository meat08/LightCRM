--доступы к действиям с данными
--дополнить при добавлении новых сущностей
INSERT INTO priorities(name)
VALUES
('FULL_ACCESS'),
--
('ADD_PROFILE'),
('READ_PROFILE'),
('CHANGE_PROFILE'),
('DELETE_PROFILE'),
--
('ADD_TASK'),
('READ_TASK'),
('CHANGE_TASK'),
('DELETE_TASK'),
--
('ADD_PROJECT'),
('READ_PROJECT'),
('CHANGE_PROJECT'),
('DELETE_PROJECT'),
--
('ADD_TASK_STATE'),
('READ_TASK_STATE'),
('CHANGE_TASK_STATE'),
('DELETE_TASK_STATE'),
--
('ADD_STAFF_UNIT'),
('READ_STAFF_UNIT'),
('CHANGE_STAFF_UNIT'),
('DELETE_STAFF_UNIT'),
--
('ADD_DEPARTMENT'),
('READ_DEPARTMENT'),
('CHANGE_DEPARTMENT'),
('DELETE_DEPARTMENT'),
--
('ADD_CONTACT'),
('READ_CONTACT'),
('CHANGE_CONTACT'),
('DELETE_CONTACT'),
--
('ADD_COMPANY'),
('READ_COMPANY'),
('CHANGE_COMPANY'),
('DELETE_COMPANY'),
--
('ADD_COMMENT'),
('READ_COMMENT'),
('CHANGE_COMMENT'),
('DELETE_COMMENT');

--роли
INSERT INTO roles(name)
VALUES
('ROLE_ADMINISTRATOR'),
('ROLE_GUEST'),--неавторизованный пользователь
('ROLE_ONLY_READ'),--зарегистрированный только чтение
('ROLE_MANAGER'),
('ROLE_PROJECT_MANAGER'),
('ROLE_HEAD_OF_DEPARTMENT'),
('ROLE_HEAD_OF_COMPANY');

--роли и доступы
INSERT INTO roles_priorities(role_id, priority_id)
VALUES
--администратор
(1, 1),
--глава компании
(7, 1),
--гость
(2, 2),
--пользователь только чтение
(3, 3),
(3, 4),
(3, 7),
(3, 11),
(3, 15),
(3, 19),
(3, 23),
(3, 27),
(3, 31),
(3, 35),
--менеджер
(4, 3),
(4, 4),
(4, 6),
(4, 7),
(4, 8),
(4, 11),
(4, 15),
(4, 19),
(4, 23),
(4, 26),
(4, 27),
(4, 28),
(4, 29),
(4, 31),
(4, 32),
(4, 33),
(4, 35),
(4, 36),
(4, 37),
--руководитель проекта
(5, 3),
(5, 4),
(5, 6),
(5, 7),
(5, 8),
(5, 10),
(5, 11),
(5, 12),
(5, 15),
(5, 19),
(5, 23),
(5, 26),
(5, 27),
(5, 28),
(5, 29),
(5, 31),
(5, 32),
(5, 33),
(5, 35),
(5, 36),
(5, 37),
--руководитель отдела
(6, 3),
(6, 4),
(6, 6),
(6, 7),
(6, 8),
(6, 10),
(6, 11),
(6, 12),
(6, 15),
(6, 18),
(6, 19),
(6, 20),
(6, 23),
(6, 24),
(6, 26),
(6, 27),
(6, 28),
(6, 29),
(6, 31),
(6, 32),
(6, 33),
(6, 35),
(6, 36),
(6, 37);

--должность/штатные единицы
INSERT INTO staff_units(name)
VALUES
('Генеральный директор'),
('Заместитель генерального директора'),
('Начальник IT-департамента'),
('Начальник отдела продаж'),
('Главный бухгалтер'),
('Старший администратор'),
('Администратор'),
('Руководитель проекта'),
('Старший менеджер'),
('Менеджер'),
('Бухгалтер');

--роли должностей
INSERT INTO staff_units_roles(staff_unit_id, role_id)
VALUES
(1, 7),
(2, 7),
(3, 1),
(4, 6),
(5, 6),
(6, 1),
(7, 1),
(8, 5),
(9, 4),
(10, 4),
(11, 4);

--статусы задач
INSERT INTO task_states(id,name)
VALUES
('Новая'),
('Назначена'),
('В Работе'),
('Решена'),
('Закрыта'),
('Отменена');

--пользователи пароли 123
INSERT INTO users(login, password)
VALUES
('admin', '$2a$10$L2.xqqPpj/w8Jzy30yVfNu.e6iDiztQgxY6swxSShaWuXx1bovc5.'),
('director', '$2a$10$L2.xqqPpj/w8Jzy30yVfNu.e6iDiztQgxY6swxSShaWuXx1bovc5.'),
('manager1', '$2a$10$L2.xqqPpj/w8Jzy30yVfNu.e6iDiztQgxY6swxSShaWuXx1bovc5.'),
('manager2', '$2a$10$L2.xqqPpj/w8Jzy30yVfNu.e6iDiztQgxY6swxSShaWuXx1bovc5.');

--профили
INSERT INTO
profiles(firstname, lastname, middlename, sex, phone, email, birthdate, employment_date, user_id, staff_unit_id)
VALUES
('Петр', 'Петров','Петрович', 'мужской', '79009009090', 'petr@comp.com', '1990-01-01', '2010-10-01', 1, 6),
('Виктор', 'Викторов','Викторович', 'мужской', '78008008080', 'dir@comp.com', '1980-09-01', '2010-10-01', 2, 1),
('Анна', 'Петрова','Петровна', 'женский', '77007008080', 'manager1@comp.com', '2000-05-01', '2010-10-01', 3, 9),
('Василий', 'Сидоров','Федорович', 'мужской', '79007008080', 'manager2@comp.com', '2001-05-01', '2010-10-01', 4, 10);

--департаменты
INSERT INTO departments(name, description, leader_id)
VALUES
('IT-отдел', 'Админы, прогеры и прочее', 2),
('Отдел продаж', 'манагеры', 2);

--работники отделов
INSERT INTO departments_profiles(profile_id, department_id)
VALUES
(1, 1),
(3, 2),
(4, 2);

--компании и клиенты
INSERT INTO companies(name, type, inn, bill_number, phone_number, email)
VALUES
('ООО Вектор', true, 58758758964, 89544852485548, '79185185618', 'sales@vector.su'),
('ИП Федоров Виктор Акакиевич', true, 456789123123, 88445662211333, '79185185000', 'akaki@gmail.com'),
('Василий Ермак', false, 0, 0, '79185186006', 'ermak@gmail.com');

--контактные лица
INSERT INTO contacts(name, post, phone, email, description, company_id)
VALUES
('Васечкин Петр', 'Москва, ул. Панфиловцев, 111', '89457894512', 'was@vector.su', 'будни с 10 до 18', 1),
('Федоров Виктор', 'Москва, ул. Где то, 122', '79185185000', 'akaki@gmail.com', 'будни с 10 до 19', 2),
('Василий Ермак', 'Москва, ул. Там то, 123', '79185186006', 'ermak@gmail.com', 'будни с 10 до 19', 3);

--менеджеры компаний
INSERT INTO companies_managers(profile_id, company_id)
VALUES
(3, 1),
(4, 2),
(4, 3);

--проекты
INSERT INTO projects(name, description, manager_id)
VALUES
('Проект 1', 'пример проекта', 2);

--исполнители проекта
INSERT INTO employees_projects(profile_id, project_id)
VALUES
(3,1),
(4,1);

--задачи
INSERT INTO tasks(title, description, producer_id, responsible_id, start_date, task_state_id, allow_change_deadline, project_id)
VALUES
('Задача 1', 'Написать письмо клиенту', 2, 3, '2021-02-10', 1, false, null),
('Задача 2', 'Провести анализ по проекту', 2, 4, '2021-03-10', 1, true, 1),
('Задача 3', 'Посчитать затраты', 2, 4, '2021-03-10', 1, true, 1);

--соисполнители
INSERT INTO tasks_coexecutors(task_id, profile_id)
VALUES
(1, 4);

--наблюдатели задачи
INSERT INTO tasks_spectators(task_id, profile_id)
VALUES
(1, 1),
(2, 1),
(3, 1);

--комментарии
INSERT INTO comments(author_id, created_date, text)
VALUES
(1, '2021-02-10 10:12:00', 'Комментарий к задаче'),
(1, '2021-02-10 10:12:00', 'Комментарий к задаче новый'),
(1, '2021-02-10 10:12:00', 'Комментарий к компании');

--комментарий к задаче
INSERT INTO tasks_comments(task_id, comment_id)
VALUES
(2, 1),
(2, 2);

--комментарий компании
INSERT INTO companies_comments(company_id, comment_id)
VALUES
(1, 3);
