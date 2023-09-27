INSERT INTO items(item_answer, item_image_path, concept_id) values ('5', 'https://ibb.co/pdzjTSW', 3821);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('5', 'https://ibb.co/s25zyfP', 9171);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('2', 'https://ibb.co/MCXNBZp', 6801);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('3', 'https://ibb.co/McgKHt3', 8894);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('2', 'https://ibb.co/zQbnHLg', 8903);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('67.08', 'https://ibb.co/NCSttmX', 9124);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('2', 'https://ibb.co/7QN195K', 4661);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('3', 'https://ibb.co/mhsysdN', 2852);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('60°C', 'https://ibb.co/QQ6GhjD', 2868);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('x=4, y=3', 'https://ibb.co/v18rkTG', 4979);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('3', 'https://ibb.co/stWRDhH', 1117);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('4', 'https://ibb.co/F4SgDtc', 8404);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('2', 'https://ibb.co/G3f2Nst', 8426);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('1', 'https://ibb.co/fCWZf61', 4210);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('4', 'https://ibb.co/k4hgNyF', 4212);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('1', 'https://ibb.co/my6Tn4q', 10182);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('4', 'https://ibb.co/q5dyZ4F', 10180);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('x=-3, (-3. -4)', 'https://ibb.co/ZGs8DCJ', 1251);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('1/2', 'https://ibb.co/L96NNpL', 2496);
INSERT INTO items(item_answer, item_image_path, concept_id) values ('1', 'https://ibb.co/9vmW0hJ', 9924);

select * from concepts where concept_id in (3821, 9171, 6801, 8894, 8903, 9124, 4661, 2852, 2868, 4979, 1117, 8404, 8426, 4210, 4212, 10182, 10180, 1251, 2496, 9924);

-- 컨셉에 따른 문항이 4893개 들어가 있음
-- 4894
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4894, 1);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4895, 2);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4896, 3);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4897, 4);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4898, 5);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4899, 6);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4900, 7);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4901, 8);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4902, 9);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4903, 10);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4904, 11);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4905, 12);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4906, 13);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4907, 14);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4908, 15);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4909, 16);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4910, 17);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4911, 18);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4912, 19);
INSERT INTO tests_items(test_id, item_id, test_item_number) VALUES (5, 4913, 20);


-- depth 1 선수지식 문항 엽데이트
use mmt;
select * from items where concept_id = 9796;
select * from items where concept_id = 8790 and item_id = (select min(item_id) from items where concept_id=8790);
-- 총 45문항
UPDATE items AS i1
JOIN (SELECT MIN(item_id) AS min_item_id FROM items WHERE concept_id = 8790) AS i2
ON i1.item_id = i2.min_item_id
SET i1.item_answer = '(가)-(B), (나)-(A)', i1.item_image_path = 'https://ibb.co/bXqCHzB'
WHERE i1.concept_id = 8790;

UPDATE items AS i1
JOIN (
  SELECT concept_id, MIN(item_id) AS min_item_id
  FROM items
  WHERE concept_id IN (116, 3793, 3806, 7665, 5620, 5653, 9162, 9161, 5809, 1259, 5810, 6784, 9166, 6795, 1263, 9168, 1266)
  GROUP BY concept_id
) AS i2
ON i1.concept_id = i2.concept_id AND i1.item_id = i2.min_item_id
SET 
  i1.item_answer = CASE 
                    WHEN i1.concept_id = 116 THEN '4'
                    WHEN i1.concept_id = 3793 THEN '3'
                    WHEN i1.concept_id = 3806 THEN '5'
                    WHEN i1.concept_id = 7665 THEN '3'
                    WHEN i1.concept_id = 5620 THEN '5'
                    WHEN i1.concept_id = 5653 THEN '2'
                    WHEN i1.concept_id = 9162 THEN '2x+3x, 4, -1/7y^2'
                    WHEN i1.concept_id = 9161 THEN '4'
                    WHEN i1.concept_id = 5809 THEN '3'
                    WHEN i1.concept_id = 1259 THEN '2'
                    WHEN i1.concept_id = 5810 THEN '2'
                    WHEN i1.concept_id = 6784 THEN '4'
                    WHEN i1.concept_id = 9166 THEN '4'
                    WHEN i1.concept_id = 6795 THEN '1'
                    WHEN i1.concept_id = 1263 THEN '3x+2=5x-3, x=5/2'
                    WHEN i1.concept_id = 9168 THEN '2'
                    WHEN i1.concept_id = 1266 THEN '수직선 확인'
                  END,
  i1.item_image_path = CASE 
                          WHEN i1.concept_id = 116 THEN 'https://ibb.co/j38tYWx'
                          WHEN i1.concept_id = 3793 THEN 'https://ibb.co/DrC7xMk'
                          WHEN i1.concept_id = 3806 THEN 'https://ibb.co/DG3bCKM'
                          WHEN i1.concept_id = 7665 THEN 'https://ibb.co/LPSP4Ht'
                          WHEN i1.concept_id = 5620 THEN 'https://ibb.co/TLFZmFr'
                          WHEN i1.concept_id = 5653 THEN 'https://ibb.co/JB3qx65'
                          WHEN i1.concept_id = 9162 THEN 'https://ibb.co/gJ90ZYk'
                          WHEN i1.concept_id = 9161 THEN 'https://ibb.co/b2ZqtWd'
                          WHEN i1.concept_id = 5809 THEN 'https://ibb.co/TgpF263'
                          WHEN i1.concept_id = 1259 THEN 'https://ibb.co/SnKsXJn'
                          WHEN i1.concept_id = 5810 THEN 'https://ibb.co/NN5YqQC'
                          WHEN i1.concept_id = 6784 THEN 'https://ibb.co/F7Z9KQx'
                          WHEN i1.concept_id = 9166 THEN 'https://ibb.co/K7S6xKN'
                          WHEN i1.concept_id = 6795 THEN 'https://ibb.co/Q60v2s1'
                          WHEN i1.concept_id = 1263 THEN 'https://ibb.co/74nW6KP'
                          WHEN i1.concept_id = 9168 THEN 'https://ibb.co/BLfM9WM'
                          WHEN i1.concept_id = 1266 THEN 'https://ibb.co/Yty1yFk'
                        END;


ALTER TABLE items MODIFY item_answer VARCHAR(100);
ALTER TABLE tests MODIFY test_name VARCHAR(50);
COMMIT;

UPDATE items AS i1
JOIN (
  SELECT concept_id, MIN(item_id) AS min_item_id
  FROM items
  WHERE concept_id IN (6800, 8882, 8893, 9796, 4662, 4668, 4672, 2666, 4785, 4786, 4803, 4961, 4972, 4975, 1110, 8420, 78, 4699, 4709, 5261, 5308, 9728, 971, 1009, 1010, 1011, 2643)
  GROUP BY concept_id
) AS i2
ON i1.concept_id = i2.concept_id AND i1.item_id = i2.min_item_id
SET 
  i1.item_answer = CASE 
                    WHEN i1.concept_id = 6800 THEN '4'
                    WHEN i1.concept_id = 8882 THEN '예각'
                    WHEN i1.concept_id = 8893 THEN '3'
                    WHEN i1.concept_id = 9796 THEN '60분 이상 90분 미만'
                    WHEN i1.concept_id = 4662 THEN '4'
                    WHEN i1.concept_id = 4668 THEN '1'
                    WHEN i1.concept_id = 4672 THEN '-6'
                    WHEN i1.concept_id = 2666 THEN '3'
                    WHEN i1.concept_id = 4785 THEN '3'
                    WHEN i1.concept_id = 4786 THEN '3'
                    WHEN i1.concept_id = 4803 THEN 'x+y=5, 1500x+1200y=6900'
                    WHEN i1.concept_id = 4961 THEN '4'
                    WHEN i1.concept_id = 4972 THEN 'y=0.2x+20'
                    WHEN i1.concept_id = 4975 THEN 'x=2, y=1'
                    WHEN i1.concept_id = 1110 THEN '2'
                    WHEN i1.concept_id = 8420 THEN '4'
                    WHEN i1.concept_id = 78 THEN '5'
                    WHEN i1.concept_id = 4699 THEN '-10'
                    WHEN i1.concept_id = 4709 THEN '2'
                    WHEN i1.concept_id = 5261 THEN '1'
                    WHEN i1.concept_id = 5308 THEN '5'
                    WHEN i1.concept_id = 9728 THEN '5'
                    WHEN i1.concept_id = 971 THEN '2a^2+ab-6b^2+a+2b'
                    WHEN i1.concept_id = 1009 THEN '3'
                    WHEN i1.concept_id = 1010 THEN 'x=4±3sqrt{2}'
                    WHEN i1.concept_id = 1011 THEN 'x=(3±sqrt{5})/2'
                    WHEN i1.concept_id = 2643 THEN '(1)8 (2)7.5 (3)7,8'
                  END,
  i1.item_image_path = CASE 
                          WHEN i1.concept_id = 6800 THEN 'https://ibb.co/xzVb9yZ'
                          WHEN i1.concept_id = 8882 THEN 'https://ibb.co/D9WdV9K'
                          WHEN i1.concept_id = 8893 THEN 'https://ibb.co/C6ZCVyV'
                          WHEN i1.concept_id = 9796 THEN 'https://ibb.co/yWpySc8'
                          WHEN i1.concept_id = 4662 THEN 'https://ibb.co/99jMRcD'
                          WHEN i1.concept_id = 4668 THEN 'https://ibb.co/ssqgrXW'
                          WHEN i1.concept_id = 4672 THEN 'https://ibb.co/sP6dtLG'
                          WHEN i1.concept_id = 2666 THEN 'https://ibb.co/KjMyVtP'
                          WHEN i1.concept_id = 4785 THEN 'https://ibb.co/Wf2LPYx'
                          WHEN i1.concept_id = 4786 THEN 'https://ibb.co/zFm4zwQ'
                          WHEN i1.concept_id = 4803 THEN 'https://ibb.co/SJWFXhc'
                          WHEN i1.concept_id = 4961 THEN 'https://ibb.co/84b1cpY'
                          WHEN i1.concept_id = 4972 THEN 'https://ibb.co/HCwZ6HW'
                          WHEN i1.concept_id = 4975 THEN 'https://ibb.co/HpKK5g9'
                          WHEN i1.concept_id = 1110 THEN 'https://ibb.co/cgHX3d6'
                          WHEN i1.concept_id = 8420 THEN 'https://ibb.co/qRcR9LJ'
                          WHEN i1.concept_id = 78 THEN 'https://ibb.co/Nx47BQ6'
                          WHEN i1.concept_id = 4699 THEN 'https://ibb.co/jTvMNtn'
                          WHEN i1.concept_id = 4709 THEN 'https://ibb.co/8d7WXZz'
                          WHEN i1.concept_id = 5261 THEN 'https://ibb.co/X4WDVdB'
                          WHEN i1.concept_id = 5308 THEN 'https://ibb.co/jD19MS4'
                          WHEN i1.concept_id = 9728 THEN 'https://ibb.co/9gLmRQS'
                          WHEN i1.concept_id = 971 THEN 'https://ibb.co/6JzTHhG'
                          WHEN i1.concept_id = 1009 THEN 'https://ibb.co/s5WL4cG'
                          WHEN i1.concept_id = 1010 THEN 'https://ibb.co/wQchRtx'
                          WHEN i1.concept_id = 1011 THEN 'https://ibb.co/s9bzV43'
                          WHEN i1.concept_id = 2643 THEN 'https://ibb.co/z5M1jPP'
                        END;

