-- Luodaan tietokanta

-- Työntekijät
CREATE TABLE employee (
  employee_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  phone VARCHAR(50)
  );

-- Säiliöt
CREATE TABLE site (
  site_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  capacity_liters DECIMAL(10, 2) NOT NULL,
  location VARCHAR(200) NOT NULL
  );

-- Mittausdata
CREATE TABLE measurement (
  measurement_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  site_id BIGINT NOT NULL,
  fill_percent DECIMAL(5, 2) NOT NULL,
  measured_at DATETIME NOT NULL,
  FOREIGN KEY (site_id) REFERENCES site(site_id),
  CHECK (fill_percent BETWEEN 0 AND 100)
  );

-- Hälytykset
CREATE TABLE alert (
  alert_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  site_id BIGINT NOT NULL,
  alert_type ENUM('VAROITUS', 'HÄLYTYS') NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  state ENUM('AVOIN', 'KUITATTU', 'SULJETTU') NOT NULL DEFAULT 'AVOIN',
  closed_at DATETIME NULL,
  FOREIGN KEY (site_id) REFERENCES site(site_id)
  );

-- Tehtävät
CREATE TABLE task (
  task_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  alert_id BIGINT NOT NULL UNIQUE,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status ENUM('AVOIN', 'KESKEN', 'VALMIS') NOT NULL DEFAULT 'AVOIN',
  assigned_employee_id BIGINT NULL,
  assigned_at DATETIME NULL,
  done_at DATETIME NULL,
  FOREIGN KEY (alert_id) REFERENCES alert(alert_id),
  FOREIGN KEY (assigned_employee_id) REFERENCES employee(employee_id)
  );

-- Viestit
CREATE TABLE message (
  message_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  task_id BIGINT NOT NULL,
  employee_id BIGINT NOT NULL,
  sent_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status ENUM('LÄHETETTY', 'EPÄONNISTUNUT') NOT NULL,
  FOREIGN KEY (task_id) REFERENCES task(task_id),
  FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
  );
