USE module_2_1_db;
/*INSERT INTO DEVELOPERS */
INSERT INTO developers VALUES (NULL, 'Popovich', 'Stas'), (NULL,'Popovich','Alina'),
(NULL, 'Popovich', 'Oleg'), (NULL,'Pasko','Evgenii'), (NULL,'Kudrtavtsev','Maksim'),
(NULL,'Gorbatenko','Anton') , (NULL,'User1','User1'), (NULL,'User2','User2'),
  (NULL,'User3','User3'), (NULL,'User4','User4'), (NULL,'User5','User5'), (NULL,'User6','User6'),
  (NULL,'User7','User7'), (NULL,'User8','User8'), (NULL,'User9','User9'), (NULL,'User10','User10');

/*------------------------------------------------------------------------------------------*/
/*INSERT INTO SKILLS */
INSERT INTO skills VALUES (NULL, 'Java'), (NULL,'Java Script'),
(NULL, 'C++'), (NULL,'Kotlin'), (NULL,'C#');

/*------------------------------------------------------------------------------------------*/
/*INSERT INTO DEVELOPER_SKILLS */
INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='Popovich' AND d.name='Stas') AND (s.skill_name='Java' OR s.skill_name='Kotlin'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='Popovich' AND d.name='Alina') AND (s.skill_name='Kotlin' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='Popovich' AND d.name='Oleg') AND (s.skill_name='Java Script' OR s.skill_name='Java'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='Pasko' AND d.name='Evgenii') AND (s.skill_name='C++' OR s.skill_name='C#' OR s.skill_name='Java'))
    AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='Kudrtavtsev' AND d.name='Maksim') AND (s.skill_name='Java' OR s.skill_name='Java Script'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='Gorbatenko' AND d.name='Anton') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='User1' AND d.name='User1') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='User2' AND d.name='User2') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='User3' AND d.name='User3') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='User4' AND d.name='User4') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='User5' AND d.name='User5') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='User6' AND d.name='User6') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='User7' AND d.name='User7') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='User8' AND d.name='User8') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='User9' AND d.name='User9') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

INSERT INTO developer_skills (developer_id, skill_id) SELECT d_id, s_id FROM
  (SELECT d.id AS d_id, s.id AS s_id FROM developers d, skills s
  WHERE (d.lname='User10' AND d.name='User10') AND (s.skill_name='Java' OR s.skill_name='C#'))AS result;

/*------------------------------------------------------------------------------------------*/
/*INSERT INTO PROJECTS */
INSERT INTO projects VALUES (NULL, 'Project_1'), (NULL,'Project_2'),
  (NULL, 'Project_3'), (NULL,'Project_4'), (NULL,'Project_5'),
  (NULL, 'Project_6'), (NULL,'Project_7'), (NULL,'Project_8'),
  (NULL, 'Project_9'), (NULL,'Project_10');

/*------------------------------------------------------------------------------------------*/
/*INSERT INTO DEVELOPER_PROJECTS */
INSERT INTO project_developers (developer_id, project_id) SELECT d_id, p_id FROM
  (SELECT d.id AS d_id, p.id AS p_id FROM developers d, projects p
  WHERE((d.lname = 'Popovich' AND d.name = 'Stas') OR (d.lname = 'Popovich' AND d.name = 'Alina'))
       AND (project_name = 'Project_1')) AS result;

INSERT INTO project_developers (developer_id, project_id) SELECT d_id, p_id FROM
  (SELECT d.id AS d_id, p.id AS p_id FROM developers d, projects p
  WHERE((d.lname = 'Popovich' AND d.name = 'Oleg') OR (d.lname = 'Pasko' AND d.name = 'Evgenii')
        OR (d.lname = 'Kudrtavtsev' AND name = 'Maksim')) AND (project_name = 'Project_2')) AS result;

INSERT INTO project_developers (developer_id, project_id) SELECT d_id, p_id FROM
  (SELECT d.id AS d_id, p.id AS p_id FROM developers d, projects p
  WHERE((d.lname = 'Gorbatenko' AND d.name = 'Anton') OR (d.lname = 'User1' AND d.name = 'User1'))
       AND (project_name = 'Project_3')) AS result;

INSERT INTO project_developers (developer_id, project_id) SELECT d_id, p_id FROM
  (SELECT d.id AS d_id, p.id AS p_id FROM developers d, projects p
  WHERE((d.lname = 'User2' AND d.name = 'User2') OR (d.lname = 'User3' AND d.name = 'User3'))
       AND (project_name = 'Project_4')) AS result;

INSERT INTO project_developers (developer_id, project_id) SELECT d_id, p_id FROM
  (SELECT d.id AS d_id, p.id AS p_id FROM developers d, projects p
  WHERE((d.lname = 'User4' AND d.name = 'User4') OR (d.lname = 'User5' AND d.name = 'User5'))
       AND (project_name = 'Project_5')) AS result;

INSERT INTO project_developers (developer_id, project_id) SELECT d_id, p_id FROM
  (SELECT d.id AS d_id, p.id AS p_id FROM developers d, projects p
  WHERE(d.lname = 'User6' AND d.name = 'User6') AND (project_name = 'Project_6')) AS result;

INSERT INTO project_developers (developer_id, project_id) SELECT d_id, p_id FROM
  (SELECT d.id AS d_id, p.id AS p_id FROM developers d, projects p
  WHERE(d.lname = 'User7' AND d.name = 'User7') AND (project_name = 'Project_7')) AS result;

INSERT INTO project_developers (developer_id, project_id) SELECT d_id, p_id FROM
  (SELECT d.id AS d_id, p.id AS p_id FROM developers d, projects p
  WHERE(d.lname = 'User8' AND d.name = 'User8') AND (project_name = 'Project_8')) AS result;

INSERT INTO project_developers (developer_id, project_id) SELECT d_id, p_id FROM
  (SELECT d.id AS d_id, p.id AS p_id FROM developers d, projects p
  WHERE(d.lname = 'User9' AND d.name = 'User9') AND (project_name = 'Project_9')) AS result;

INSERT INTO project_developers (developer_id, project_id) SELECT d_id, p_id FROM
  (SELECT d.id AS d_id, p.id AS p_id FROM developers d, projects p
  WHERE(d.lname = 'User10' AND d.name = 'User10') AND (project_name = 'Project_10')) AS result;

/*------------------------------------------------------------------------------------------*/
/*INSERT INTO COMPANIES */
INSERT INTO companies VALUES (NULL, 'Company_1'), (NULL,'Company_2'),
  (NULL, 'Company_3'), (NULL,'Company_4'), (NULL,'Company_5'),
  (NULL, 'Company_6'), (NULL,'Company_7'), (NULL,'Company_8'),
  (NULL, 'Company_9'), (NULL,'Company_10');

/*------------------------------------------------------------------------------------------*/
/*INSERT INTO COMPANY_PROJECTS */
INSERT INTO company_projects VALUES (
  (SELECT id FROM companies WHERE company_name = 'Company_1'),
  (SELECT id FROM projects WHERE project_name = 'Project_1')),
  ((SELECT id FROM companies WHERE company_name = 'Company_1'),
   (SELECT id FROM projects WHERE project_name = 'Project_3'));

INSERT INTO company_projects VALUES (
  (SELECT id FROM companies WHERE company_name = 'Company_2'),
  (SELECT id FROM projects WHERE project_name = 'Project_2'));

INSERT INTO company_projects VALUES (
  (SELECT id FROM companies WHERE company_name = 'Company_3'),
  (SELECT id FROM projects WHERE project_name = 'Project_4'));

INSERT INTO company_projects VALUES (
  (SELECT id FROM companies WHERE company_name = 'Company_4'),
  (SELECT id FROM projects WHERE project_name = 'Project_5'));

INSERT INTO company_projects VALUES (
  (SELECT id FROM companies WHERE company_name = 'Company_5'),
  (SELECT id FROM projects WHERE project_name = 'Project_6'));

INSERT INTO company_projects VALUES (
  (SELECT id FROM companies WHERE company_name = 'Company_6'),
  (SELECT id FROM projects WHERE project_name = 'Project_7'));

INSERT INTO company_projects VALUES (
  (SELECT id FROM companies WHERE company_name = 'Company_7'),
  (SELECT id FROM projects WHERE project_name = 'Project_8'));

INSERT INTO company_projects VALUES (
  (SELECT id FROM companies WHERE company_name = 'Company_8'),
  (SELECT id FROM projects WHERE project_name = 'Project_9'));

INSERT INTO company_projects VALUES (
  (SELECT id FROM companies WHERE company_name = 'Company_9'),
  (SELECT id FROM projects WHERE project_name = 'Project_10'));

/*------------------------------------------------------------------------------------------*/
/*INSERT INTO CUSTOMERS */
INSERT INTO customers VALUES (NULL, 'Customer_1'), (NULL,'Customer_2'),
  (NULL, 'Customer_3'), (NULL,'Customer_4'), (NULL,'Customer_5');

/*------------------------------------------------------------------------------------------*/
/*INSERT INTO CUSTOMER_PROJECTS */
INSERT INTO customer_projects VALUES (
  (SELECT id FROM customers WHERE customer_name = 'Customer_1'),
  (SELECT id FROM projects WHERE project_name = 'Project_1')),
  ((SELECT id FROM customers WHERE customer_name = 'Customer_1'),
   (SELECT id FROM projects WHERE project_name = 'Project_2'));

INSERT INTO customer_projects VALUES (
  (SELECT id FROM customers WHERE customer_name = 'Customer_2'),
  (SELECT id FROM projects WHERE project_name = 'Project_3')),
  ((SELECT id FROM customers WHERE customer_name = 'Customer_2'),
   (SELECT id FROM projects WHERE project_name = 'Project_4'));

INSERT INTO customer_projects VALUES (
  (SELECT id FROM customers WHERE customer_name = 'Customer_3'),
  (SELECT id FROM projects WHERE project_name = 'Project_5')),
  ((SELECT id FROM customers WHERE customer_name = 'Customer_3'),
   (SELECT id FROM projects WHERE project_name = 'Project_6'));

INSERT INTO customer_projects VALUES (
  (SELECT id FROM customers WHERE customer_name = 'Customer_4'),
  (SELECT id FROM projects WHERE project_name = 'Project_7')),
  ((SELECT id FROM customers WHERE customer_name = 'Customer_4'),
   (SELECT id FROM projects WHERE project_name = 'Project_8'));

INSERT INTO customer_projects VALUES (
  (SELECT id FROM customers WHERE customer_name = 'Customer_5'),
  (SELECT id FROM projects WHERE project_name = 'Project_9')),
  ((SELECT id FROM customers WHERE customer_name = 'Customer_5'),
   (SELECT id FROM projects WHERE project_name = 'Project_10'));