ALTER TABLE bin
ADD COLUMN height_mm INT NOT NULL DEFAULT 1000,
ADD COLUMN fill_level_percent DECIMAL(5, 2) DEFAULT 0.00,
ADD COLUMN last_measured_at DATETIME NULL;


UPDATE bin SET height_mm = 1200 WHERE capacity_liters = 600.00;
UPDATE bin SET height_mm = 1000 WHERE capacity_liters = 240.00;
UPDATE bin SET height_mm = 1400 WHERE capacity_liters = 1000.00;