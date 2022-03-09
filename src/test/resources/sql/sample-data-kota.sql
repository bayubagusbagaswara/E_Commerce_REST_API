-- KOTA DI PROVINSI JAKARTA
insert into kota (id, name, code, created_by, created_at, status_record, id_provinsi)
values ('3171', 'KOTA ADM. JAKARTA PUSAT', '3171', 'Current User', current_timestamp, 'ACTIVE', '31'),
        ('3174', 'KOTA ADM. JAKARTA SELATAN', '3174', 'Current User', current_timestamp, 'ACTIVE', '31');

-- KOTA DI PROVINSI JAWA TIMUR
insert into kota (id, name, code, created_by, created_at, status_record, id_provinsi)
values ('3571', 'KOTA KEDIRI', '3571', 'Current User', current_timestamp, 'ACTIVE', '35'),
        ('3506', 'KAB. KEDIRI', '3506', 'Current User', current_timestamp, 'ACTIVE', '35'),
        ('3578', 'KOTA SURABAYA', '3578', 'Current User', current_timestamp, 'ACTIVE', '35');

-- KOTA DI PROVINSI BALI
INSERT INTO kota ( id, name, code, created_by, created_at, status_record, id_provinsi)
VALUES ('5103', 'KAB. BADUNG', '5103', 'Current User', current_timestamp, 'ACTIVE', '51'),
        ('5171', 'KOTA DENPASAR', '5171', 'Current User', current_timestamp, 'ACTIVE', '51');
