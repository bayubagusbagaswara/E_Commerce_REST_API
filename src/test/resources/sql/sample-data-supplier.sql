-- TOTAL SAMPLE DATA = 9
-- supplier address from Singonegaran, Kediri
insert into suppliers (id, name, email, gender, mobile_phone, id_address, created_by, created_date, status_record)
values ('bayu', 'Bayu Bagaswara', 'bayu@mail.com', 'MALE', '085899551103', 'singonegaran-kdr', 'User Bayu', current_timestamp, 'ACTIVE'),
        ('bagus', 'Bagus Putra', 'bagus@mail.com', 'MALE', '082211211209', 'singonegaran-kdr', 'User Bagus', current_timestamp, 'ACTIVE');

-- supplier address from Pesantren, Kediri
insert into suppliers (id, name, email, gender, mobile_phone, id_address, created_by, created_date, status_record)
values ('albert', 'Albert Einstein', 'albert@mail.com', 'MALE', '08124568934', 'pesantren-kdr', 'User Albert', current_timestamp, 'ACTIVE'),
        ('newton', 'Issac Newton', 'newton@mail.com', 'MALE', '085112112345', 'pesantren-kdr', 'User Newton', current_timestamp, 'ACTIVE');

-- supplier address from Keputih, Surabaya
insert into suppliers (id, name, email, gender, mobile_phone, id_address, created_by, created_date, status_record)
values ('gosling', 'James Gosling', 'gosling@mail.com', 'MALE', '085855777321', 'keputih-sby', 'User Gosling', current_timestamp, 'ACTIVE'),
        ('faraday', 'Michael Faraday', 'faraday@mail.com', 'MALE', '085678111990', 'keputih-sby', 'User Faraday', current_timestamp, 'ACTIVE');

-- supplier address from Tebet Timur, Jakarta Selatan
insert into suppliers (id, name, email, gender, mobile_phone, id_address, created_by, created_date, status_record)
values ('thomas', 'Thomas Alfa Edison', 'thomas@mail.com', 'MALE', '085631457893', 'tebet-jaksel', 'User Thomas', current_timestamp, 'ACTIVE'),
        ('tesla', 'Nikola Tesla', 'tesla@mail.com', 'MALE', '081736976626', 'tebet-jaksel', 'User Tesla', current_timestamp, 'ACTIVE');

-- supplier address from Gandaria Utara, Jakarta Selatan
insert into suppliers (id, name, email, gender, mobile_phone, id_address, created_by, created_date, status_record)
values ('watt', 'James Watt', 'watt@mail.com', 'MALE', '089222583423', 'gandaria-jaksel', 'User Watt', current_timestamp, 'ACTIVE');
