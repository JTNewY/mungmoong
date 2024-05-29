insert into orders (order_no, date_no,user_id,order_id)
values(123,123,123,123);

INSERT INTO `ordersdetail` (`order_id`, `card_no`, `order_price`, `price_check`, `order_date`, `order_check`, `order_no`)
VALUES (123, '1234567890123456', '50.00', TRUE, '2024-05-28 12:00:00', TRUE, 123);

commit;

SELECT *
FROM OrdersDetail;

