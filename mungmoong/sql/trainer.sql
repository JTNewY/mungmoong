TRUNCATE trainer;


 SELECT t.user_id
              ,t.name
              ,t.address
              ,t.mail
              ,t.phone
        FROM trainer t
        WHERE user_id = 'user'
        ;

        SELECT *
        FROM users;

update users set role = 0 where role = 1;  

truncate trainer;