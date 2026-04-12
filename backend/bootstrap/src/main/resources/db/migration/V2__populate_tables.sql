-- Kiitti Gemini
-- 1. Employees (Työntekijät)
INSERT INTO employee (name, email, phone) VALUES
                                              ('Matti Meikäläinen', 'matti.meikalainen@roskis.fi', '+358401234567'),
                                              ('Sanna Suomalainen', 'sanna.suomalainen@roskis.fi', '+358407654321'),
                                              ('Pekka Puupää', 'pekka.puupaa@roskis.fi', '+358509998887');

-- 2. Sites (Kohteet)
INSERT INTO site (name, location) VALUES
                                      ('Kuopion Tori', 'Katu 1'),
                                      ('Matkus Shopping Center', 'Katu 2'),
                                      ('Puijo Tower', 'Katu 3'),
                                      ('University of Eastern Finland', 'Katu 4'),
                                      ('Kuopio Railway Station', 'Katu 5');

-- 3. Bins (Säiliöt) - One bin per pilot site
INSERT INTO bin (site_id, name, capacity_liters) VALUES
                                                     (1, 'BIN-001', 600.00),
                                                     (2, 'BIN-002', 600.00),
                                                     (3, 'BIN-003', 240.00),
                                                     (4, 'BIN-004', 240.00),
                                                     (5, 'BIN-005', 1000.00);

-- 4. Measurement Data (Mittausdata) - Historical samples
INSERT INTO measurement (bin_id, distance_mm, measured_at) VALUES
                                                               (1, 1500, '2026-04-01 08:00:00'),
                                                               (2, 400, '2026-04-01 08:05:00'),
                                                               (3, 800, '2026-04-01 08:10:00'),
                                                               (4, 200, '2026-04-01 08:15:00'),
                                                               (5, 1200, '2026-04-01 08:20:00');

-- 5. Alerts (Hälytykset)
-- Note: Mapped to bins based on simulated fill levels
INSERT INTO alert (bin_id, alert_type, created_at, state) VALUES
                                                              (2, 'HÄLYTYS', '2026-04-01 09:00:00', 'AVOIN'),
                                                              (4, 'VAROITUS', '2026-04-01 09:15:00', 'KUITATTU'),
                                                              (1, 'HÄLYTYS', '2026-03-31 15:00:00', 'SULJETTU');

-- 6. Tasks (Tehtävät)
-- Note: alert_id 3 is CLOSED, alert_id 1 is OPEN
INSERT INTO task (alert_id, created_at, status, assigned_employee_id, assigned_at, done_at) VALUES
                                                                                                (1, '2026-04-01 09:05:00', 'KESKEN', 1, '2026-04-01 09:10:00', NULL),
                                                                                                (2, '2026-04-01 09:20:00', 'AVOIN', NULL, NULL, NULL),
                                                                                                (3, '2026-03-31 15:10:00', 'VALMIS', 2, '2026-03-31 15:15:00', '2026-03-31 16:30:00');

-- 7. Messages (Viestit)
INSERT INTO message (task_id, employee_id, sent_time, status) VALUES
                                                                  (1, 1, '2026-04-01 09:12:00', 'LÄHETETTY'),
                                                                  (3, 2, '2026-03-31 15:20:00', 'LÄHETETTY'),
                                                                  (2, 1, '2026-04-01 09:25:00', 'EPÄONNISTUNUT');
