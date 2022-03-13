-- TOTAL SAMPLE DATA = 11
-- ELECTRONIC - TELEVISION
insert into product_detail(id, sku, description, created_by, created_date, status_record)
values ('pd-redmibook', 'SKU-RedmiBook15', 'RedmiBook 15 description', 'User', current_timestamp, 'ACTIVE');

-- ELECTRONIC - CAMERA
insert into product_detail(id, sku, description, created_by, created_date, status_record)
values ('pd-canon', 'SKU-CanonEOS', 'Canon EOS600D description', 'User', current_timestamp, 'ACTIVE');

-- COMPUTER AND ACCESSORIES - LAPTOP
insert into product_detail(id, sku, description, created_by, created_date, status_record)
values ('pd-macbook', 'SKU-AppleMacBook', 'Apple MacBook Pro 2021 description', 'User', current_timestamp, 'ACTIVE'),
        ('pd-legion', 'SKU-Legion5', 'Lenovo Legion 5 description', 'User', current_timestamp, 'ACTIVE');

-- COMPUTER AND ACCESSORIES - MONITOR
insert into product_detail(id, sku, description, created_by, created_date, status_record)
values ('pd-lg24', 'SKU-LG24', 'LG 24MK430 description', 'User', current_timestamp, 'ACTIVE');

-- COMPUTER AND ACCESSORIES - MOUSE
insert into product_detail(id, sku, description, created_by, created_date, status_record)
values ('pd-apple-magic', 'SKU-AppleMagic', 'Apple Magic description', 'User', current_timestamp, 'ACTIVE');

-- HAND PHONE AND ACCESSORIES - PHONE
insert into product_detail(id, sku, description, created_by, created_date, status_record)
values ('pd-samsungz', 'SKU-SamsungZ', 'Samsung Z description', 'User', current_timestamp, 'ACTIVE'),
        ('pd-iphone', 'SKU-iPhone13', 'Apple iPhone 13 description', 'User', current_timestamp, 'ACTIVE'),
        ('pd-redmi9', 'SKU-Redmi9', 'Xiaomi Redmi 9 description', 'User', current_timestamp, 'ACTIVE');

-- HAND PHONE AND ACCESSORIES - TABLET
insert into product_detail(id, sku, description, created_by, created_date, status_record)
values ('pd-tab-s6', 'SKU-SamsungTabS6', 'Samsung Tab S6 description', 'User', current_timestamp, 'ACTIVE'),
        ('pd-ipad', 'SKU-iPadPro', 'Apple iPad description', 'User', current_timestamp, 'ACTIVE');