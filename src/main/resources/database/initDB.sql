CREATE DATABASE module_2_1_db;
USE module_2_1_db;

CREATE TABLE developers(
  id INT AUTO_INCREMENT PRIMARY KEY,
  lname VARCHAR(50) NOT NULL,
  name VARCHAR(50) NOT NULL,
  salary INT NOT NULL
);

CREATE TABLE skills(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);


CREATE TABLE developer_skills(
  developer_id INT NOT NULL,
  skill_id INT NOT NULL,
  PRIMARY KEY (developer_id, skill_id),
  FOREIGN KEY (developer_id) REFERENCES developers (id),
  FOREIGN KEY (skill_id) REFERENCES skills (id)
);

CREATE TABLE projects(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  cost INT NOT NULL
);

CREATE TABLE project_developers(
  developer_id INT NOT NULL,
  project_id INT NOT NULL,
  PRIMARY KEY (developer_id, project_id),
  FOREIGN KEY (developer_id) REFERENCES developers (id),
  FOREIGN KEY (project_id) REFERENCES projects (id)
);

CREATE TABLE companies(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL);

CREATE TABLE company_projects(
  company_id INT NOT NULL,
  project_id INT NOT NULL,
  PRIMARY KEY (company_id, project_id),
  FOREIGN KEY (company_id) REFERENCES companies (id),
  FOREIGN KEY (project_id) REFERENCES projects (id)
);

CREATE TABLE customers(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE customer_projects(
  customer_id INT NOT NULL,
  project_id INT NOT NULL,
  PRIMARY KEY (customer_id, project_id),
  FOREIGN KEY (customer_id) REFERENCES customers (id),
  FOREIGN KEY (project_id) REFERENCES projects (id)
);

