--доступы к действиям с данными
--дополнить при добавлении новых сущностей
INSERT INTO priorities(id, name)
VALUES
(1, 'FULL_ACCESS'),
--
(2, 'ADD_PROFILE'),
(3, 'READ_PROFILE'),
(4, 'CHANGE_PROFILE'),
(5, 'DELETE_PROFILE'),
--
(6, 'ADD_TASK'),
(7, 'READ_TASK'),
(8, 'CHANGE_TASK'),
(9, 'DELETE_TASK'),
--
(10, 'ADD_PROJECT'),
(11, 'READ_PROJECT'),
(12, 'CHANGE_PROJECT'),
(13, 'DELETE_PROJECT'),
--
(14, 'ADD_TASK_STATE'),
(15, 'READ_TASK_STATE'),
(16, 'CHANGE_TASK_STATE'),
(17, 'DELETE_TASK_STATE'),
--
(18, 'ADD_STAFF_UNIT'),
(19, 'READ_STAFF_UNIT'),
(20, 'CHANGE_STAFF_UNIT'),
(21, 'DELETE_STAFF_UNIT'),
--
(22, 'ADD_DEPARTMENT'),
(23, 'READ_DEPARTMENT'),
(24, 'CHANGE_DEPARTMENT'),
(25, 'DELETE_DEPARTMENT'),
--
(26, 'ADD_CONTACT'),
(27, 'READ_CONTACT'),
(28, 'CHANGE_CONTACT'),
(29, 'DELETE_CONTACT'),
--
(30, 'ADD_COMPANY'),
(31, 'READ_COMPANY'),
(32, 'CHANGE_COMPANY'),
(33, 'DELETE_COMPANY'),
--
(34, 'ADD_COMMENT'),
(35, 'READ_COMMENT'),
(36, 'CHANGE_COMMENT'),
(37, 'DELETE_COMMENT');

--роли
INSERT INTO roles(id, name)
VALUES
(1, 'ROLE_ADMINISTRATOR'),
(2, 'ROLE_GUEST'),--неавторизованный пользователь
(3, 'ROLE_ONLY_READ'),--зарегистрированный только чтение
(4, 'ROLE_MANAGER'),
(5, 'ROLE_PROJECT_MANAGER'),
(6, 'ROLE_HEAD_OF_DEPARTMENT'),
(7, 'ROLE_HEAD_OF_COMPANY');

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
INSERT INTO staff_units(id, name)
VALUES
(1, 'Генеральный директор'),
(2, 'Заместитель генерального директора'),
(3, 'Начальник IT-департамента'),
(4, 'Начальник отдела продаж'),
(5, 'Главный бухгалтер'),
(6, 'Старший администратор'),
(7, 'Администратор'),
(8, 'Руководитель проекта'),
(9, 'Старший менеджер'),
(10,'Менеджер'),
(11,'Бухгалтер');

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
(1,'Новая'),
(2,'Назначена'),
(3,'В Работе'),
(4,'Решена'),
(5,'Закрыта'),
(6,'Отменена');

--пользователи пароли 123
INSERT INTO users(id, login, password)
VALUES
(1, 'admin', '$2a$10$L2.xqqPpj/w8Jzy30yVfNu.e6iDiztQgxY6swxSShaWuXx1bovc5.'),
(2, 'director', '$2a$10$L2.xqqPpj/w8Jzy30yVfNu.e6iDiztQgxY6swxSShaWuXx1bovc5.'),
(3, 'manager1', '$2a$10$L2.xqqPpj/w8Jzy30yVfNu.e6iDiztQgxY6swxSShaWuXx1bovc5.'),
(4, 'manager2', '$2a$10$L2.xqqPpj/w8Jzy30yVfNu.e6iDiztQgxY6swxSShaWuXx1bovc5.');

--профили
INSERT INTO
profiles(id, firstname, lastname, middlename, sex, phone, email, birthdate, employment_date, user_id, staff_unit_id)
VALUES
(1, 'Петр', 'Петров','Петрович', 'мужской', '79009009090', 'petr@comp.com', '1990-01-01', '2010-10-01', 1, 6),
(2, 'Виктор', 'Викторов','Викторович', 'мужской', '78008008080', 'dir@comp.com', '1980-09-01', '2010-10-01', 2, 1),
(3, 'Анна', 'Петрова','Петровна', 'женский', '77007008080', 'manager1@comp.com', '2000-05-01', '2010-10-01', 3, 9),
(4, 'Василий', 'Сидоров','Федорович', 'мужской', '79007008080', 'manager2@comp.com', '2001-05-01', '2010-10-01', 4, 10);

--департаменты
INSERT INTO departments(id, name, description, leader_id)
VALUES
(1, 'IT-отдел', 'Админы, прогеры и прочее', 1),
(2, 'Отдел продаж', 'манагеры', 2);

--работники отделов
INSERT INTO departments_profiles(profile_id, department_id)
VALUES
(1, 1),
(3, 2),
(4, 2);

--компании и клиенты
INSERT INTO companies(id, name, type, inn, bill_number, phone_number, email)
VALUES
(1, 'ООО Вектор', true, 58758758964, 89544852485548, '79185185618', 'sales@vector.su'),
(2, 'ИП Федоров Виктор Акакиевич', true, 456789123123, 88445662211333, '79185185000', 'akaki@gmail.com'),
(3, 'Василий Ермак', false, 0, 0, '79185186006', 'ermak@gmail.com');

--контактные лица
INSERT INTO contacts(id, name, post, phone, email, description, company_id)
VALUES
(1, 'Васечкин Петр', 'Москва, ул. Панфиловцев, 111', '89457894512', 'was@vector.su', 'будни с 10 до 18', 1),
(2, 'Федоров Виктор', 'Москва, ул. Где то, 122', '79185185000', 'akaki@gmail.com', 'будни с 10 до 19', 2),
(3, 'Василий Ермак', 'Москва, ул. Там то, 123', '79185186006', 'ermak@gmail.com', 'будни с 10 до 19', 3);

--менеджеры компаний
INSERT INTO companies_managers(profile_id, company_id)
VALUES
(3, 1),
(4, 2),
(4, 3);

--проекты
INSERT INTO projects(id, name, description, manager_id)
VALUES
(1, 'Проект 1', 'пример проекта', 2);

--исполнители проекта
INSERT INTO employees_projects(profile_id, project_id)
VALUES
(3,1),
(4,1);

--задачи
INSERT INTO tasks(id, title, description, producer_id, responsible_id, start_date, task_state_id, allow_change_deadline, project_id)
VALUES
(1, 'Задача 1', 'Написать письмо клиенту', 2, 3, '2021-02-10', 1, false, null),
(2, 'Задача 2', 'Провести анализ по проекту', 2, 4, '2021-03-10', 1, true, 1),
(3, 'Задача 3', 'Посчитать затраты', 2, 4, '2021-03-10', 1, true, 1);

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
INSERT INTO comments(id, author_id, created_date, text)
VALUES
(1, 1, '2021-02-10 10:12:00', 'Комментарий к задаче'),
(2, 1, '2021-02-10 10:12:00', 'Комментарий к задаче новый'),
(3, 1, '2021-02-10 10:12:00', 'Комментарий к компании');

--комментарий к задаче
INSERT INTO tasks_comments(task_id, comment_id)
VALUES
(2, 1),
(2, 2);

--комментарий компании
INSERT INTO companies_comments(company_id, comment_id)
VALUES
(1, 3);
