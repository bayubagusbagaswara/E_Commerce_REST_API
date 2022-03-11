-- TOTAL SAMPLE DATA = 8
-- ELECTRONIC (Telephone, CCTV, Camera)
insert into categories (id, name, description, created_by, created_date, status_record)
values ('tv', 'Television', 'Television description', 'User', current_timestamp, 'ACTIVE'),
        ('camera', 'Camera', 'Camera description', 'User', current_timestamp, 'ACTIVE');

-- COMPUTER AND ACCESSORIES (Laptop, Hard disk, Monitor, Keyboard, Mouse)
insert into categories (id, name, description, created_by, created_date, status_record)
values ('laptop', 'Laptop', 'Laptop description', 'User', current_timestamp, 'ACTIVE'),
        ('monitor', 'Monitor', 'Monitor description', 'User', current_timestamp, 'ACTIVE'),
        ('keyboard', 'Keyboard', 'Keyboard description', 'User', current_timestamp, 'ACTIVE'),
        ('mouse', 'Mouse', 'Mouse description', 'User', current_timestamp, 'ACTIVE');

-- HAND PHONE AND ACCESSORIES (Tablet, Hand phone)
insert into categories (id, name, description, created_by, created_date, status_record)
values ('tablet', 'Tablet', 'Tablet description', 'User', current_timestamp, 'ACTIVE'),
        ('phone', 'Hand Phone', 'Hand phone description', 'User', current_timestamp, 'ACTIVE');
