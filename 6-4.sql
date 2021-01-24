CREATE DATABASE ssmtest CHARACTER SET utf8;
CREATE TABLE test(
id INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(25)
);
-- 多条件课程列表查询
SELECT id,
course_name,
price,
sort_num,
STATUS
FROM course
SELECT LAST_INSERT_ID();
 SELECT
         c.*,
         t.teacher_name    teacher_name,
         t.position        POSITION,
         t.description     description
        FROM course c
         LEFT JOIN teacher t
         ON c.id = t.course_id
        WHERE c.id = 16;
-- 查询课程ID为7的章节和课时信息
SELECT
  cs.*,
  cl.id lessId,
  cl.`course_id`,
  cl.`section_id`,
  cl.`theme`,
  cl.`duration`,
  cl.`create_time`,
  cl.`update_time`,
  cl.`is_del`,
  cl.`order_num`,
  cl.`status`
FROM
  course_section cs
  LEFT JOIN course_lesson cl
    ON cs.`id` = cl.`section_id`
WHERE cs.`course_id` = 7
ORDER BY cs.`order_num`;
SELECT id,course_name FROM course WHERE id=15;
UPDATE course_section SET STATUS=1,update_time= WHERE id=13;
SELECT * FROM promotion_space;
INSERT INTO promotion_space VALUES(?,?,?,?,?,?);
SELECT NAME FROM promotion_space WHERE id=1;
SELECT * FROM promotion_ad;
UPDATE promotion_ad SET STATUS=0,update_time= WHERE id=1074;
SELECT * FROM USER WHERE NAME=15321919577;
UPDATE USER SET STATUS=1,update_time= WHERE id=?;
SELECT * FROM roles WHERE NAME='超级管理员';
SELECT * FROM roles;
SELECT * FROM menu WHERE parent_id=-1;
SELECT m.id
            FROM roles r INNER JOIN role_menu_relation rm ON r.id = rm.role_id
	                     INNER JOIN menu m ON m.id = rm.menu_id WHERE r.id =4;
SELECT menu_id FROM role_menu_relation WHERE role_id=4;
DELETE FROM role_menu_relation WHERE role_id=2;
INSERT INTO role_menu_relation VALUES();
SELECT * FROM menu WHERE id=1;
SELECT * FROM resource_category;
SELECT * FROM USER WHERE phone=15321919577;
INSERT INTO USER VALUES(NULL,tom,NULL,);
SELECT * FROM USER u INNER JOIN user_role_relation ur WHERE u.`id`=100030011;
SELECT * FROM roles r INNER JOIN user_role_relation ur ON r.`id`=ur.`role_id` WHERE ur.`user_id`=100030011;
-- 根据角色id 集合，查询角色所拥有的顶级菜单
SELECT
   DISTINCT m.*
FROM roles r INNER JOIN role_menu_relation rm ON r.`id`=rm.`role_id`
             INNER JOIN menu m ON rm.`menu_id`=m.`id` WHERE m.`parent_id`=-1 AND r.`id` IN(1,2);
 -- 查询子菜单
 SELECT * FROM menu WHERE parent_id=7;            
-- 根据角色id集合，查询角色拥有的资源
SELECT
    DISTINCT r.*
FROM resource r INNER JOIN role_resource_relation rr ON r.`id`=rr.`resource_id`
                INNER JOIN roles ro ON ro.`id`=rr.`role_id` WHERE ro.`id` IN(1,2);
-- 添加和修改资源分类
INSERT INTO resource_category VALUES();
UPDATE resource_category SET  WHERE id=?;
-- 回显资源分类信息
SELECT * FROM resource_category WHERE id=1;
-- 根据资源分类id删除资源信息
DELETE FROM resource WHERE category_id =?;
-- 根据资源分类id删除资源分类信息
SELECT * FROM resource WHERE category_id =1;
DELETE FROM resource_category WHERE id=?; 
-- 根据id查询资源信息
SELECT r.* FROM role_resource_relation rr INNER JOIN resource r ON rr.`resource_id`=r.`id`
                                      INNER JOIN roles ro ON rr.`role_id`=ro.`id` WHERE ro.`id`=1;
 -- 分配资源回显
SELECT r.`id`,r.`name`,rc.`id`,rc.`name` FROM role_resource_relation rr 
                                      INNER JOIN resource r ON r.`id`=rr.`resource_id`
                                      INNER JOIN resource_category rc ON r.`category_id`=rc.`id`
                                      INNER JOIN roles ro ON ro.`id`=rr.`role_id` WHERE ro.`id`=1;  
-- 删除资源
DELETE FROM role_resource_relation WHERE role_id=?;                              
                 