-- TOTAL SAMPLE DATA = 11
-- ELECTRONIC - TELEVISION
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('redmibook-15', 'Xiaomi RedmiBook 15', 6729000, 20, 'User', current_timestamp, 'ACTIVE', 'tv', 'pd-redmibook-15');

-- ELECTRONIC - CAMERA
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('canon-eos', 'Canon EOS 600D', 4050000, 12, 'User', current_timestamp, 'ACTIVE', 'camera', 'pd-canon-eos');

-- COMPUTER AND ACCESSORIES - LAPTOP
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('macbook-pro-2021', 'Apple MacBook Pro 14-inch 2021', 33390000, 10, 'User', current_timestamp, 'ACTIVE', 'laptop', 'pd-macbook-pro-2021'),
        ('lenovo-legion-5', 'Lenovo Legion 5 Pro', 18199000, 15, 'User', current_timestamp, 'ACTIVE', 'laptop', 'pd-lenovo-legion-5');

-- COMPUTER AND ACCESSORIES - MONITOR
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('lg-24mk430h', 'LG 24MK430H-B LED Monitor', 1459000, 8, 'User', current_timestamp, 'ACTIVE', 'monitor', 'pd-lg-24mk430h');

-- COMPUTER AND ACCESSORIES - MOUSE
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('apple-magic', 'Apple Magic Mouse 2', 1172000, 123, 'User', current_timestamp, 'ACTIVE', 'mouse', 'pd-apple-magic');

-- HAND PHONE AND ACCESSORIES - PHONE
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('samsung-z', 'Samsung Galaxy Z Flip', 9350000, 50, 'User', current_timestamp, 'ACTIVE', 'phone', 'pd-samsung-z'),
        ('iphone', 'Apple iPhone 13', 18170000, 45, 'User', current_timestamp, 'ACTIVE', 'phone', 'pd-iphone'),
        ('redmi-9', 'Xiaomi Redmi 9', 1560000, 60, 'User', current_timestamp, 'ACTIVE', 'phone', 'pd-redmi-9');

-- HAND PHONE AND ACCESSORIES - TABLET
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('tab-s6', 'Samsung Galaxy Tab S6', 5937000, 45, 'User', current_timestamp, 'ACTIVE', 'tablet', 'pd-tab-s6'),
        ('ipad', 'Apple iPad Pro 2021', 12900000, 67, 'User', current_timestamp, 'ACTIVE', 'tablet', 'pd-ipad');