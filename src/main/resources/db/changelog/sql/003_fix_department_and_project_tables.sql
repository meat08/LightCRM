ALTER TABLE departments DROP COLUMN sub_department_id;

ALTER TABLE projects ADD COLUMN company_id BIGINT CONSTRAINT company_id REFERENCES companies(id);