-- TOTAL SAMPLE DATA = 11
-- ELECTRONIC - TELEVISION
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('redmibook', 'Xiaomi RedmiBook 15', 6729000, 20, 'User', current_timestamp, 'ACTIVE', 'tv', 'pd-redmibook');

-- ELECTRONIC - CAMERA
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('canon', 'Canon EOS 600D', 4050000, 12, 'User', current_timestamp, 'ACTIVE', 'camera', 'pd-canon');

-- COMPUTER AND ACCESSORIES - LAPTOP
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('macbook', 'Apple MacBook Pro', 33390000, 10, 'User', current_timestamp, 'ACTIVE', 'laptop', 'pd-macbook'),
        ('legion', 'Lenovo Legion', 18199000, 15, 'User', current_timestamp, 'ACTIVE', 'laptop', 'pd-legion');

-- COMPUTER AND ACCESSORIES - MONITOR
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('lg24', 'LG 24MK430H-B LED Monitor', 1459000, 8, 'User', current_timestamp, 'ACTIVE', 'monitor', 'pd-lg24');

-- COMPUTER AND ACCESSORIES - MOUSE
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('apple-magic', 'Apple Magic Mouse 2', 1172000, 123, 'User', current_timestamp, 'ACTIVE', 'mouse', 'pd-apple-magic');

-- HAND PHONE AND ACCESSORIES - PHONE
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('samsungz', 'Samsung Galaxy Z Flip', 9350000, 50, 'User', current_timestamp, 'ACTIVE', 'phone', 'pd-samsungz'),
        ('iphone', 'Apple iPhone 13', 18170000, 45, 'User', current_timestamp, 'ACTIVE', 'phone', 'pd-iphone'),
        ('redmi9', 'Xiaomi Redmi 9', 1560000, 60, 'User', current_timestamp, 'ACTIVE', 'phone', 'pd-redmi9');

-- HAND PHONE AND ACCESSORIES - TABLET
insert into products (id, name, price, quantity, created_by, created_date, status_record, id_category, id_product_detail)
values ('tab-s6', 'Samsung Galaxy Tab S6', 5937000, 45, 'User', current_timestamp, 'ACTIVE', 'tablet', 'pd-tab-s6'),
        ('ipad', 'Apple iPad Pro 2021', 12900000, 67, 'User', current_timestamp, 'ACTIVE', 'tablet', 'pd-ipad');