use ktplaceour;
# drop table tourlist;

drop table place;
drop table Plan;
drop table user;

#객체
#User(email[PK], nickname, age_range,gender ,picture);
Create Table User(
	user_email Varchar(50) primary key,
    user_nickname varchar(30),
    user_age_range varchar(15),
    user_picture text,
	create_at TIMESTAMP,
    modified_at TIMESTAMP
);

#Tourlist
# email - tour_list = 1:n
#Tourlist(list_number[PK], email,tour_list)


Create Table Plan(
	plan_id bigInt primary key auto_increment,
	plan_name varchar(30) null,
	plan_note varchar(200),
	user_email varchar(50) not null,
	passed boolean,
	create_at TIMESTAMP,
    modified_at TIMESTAMP,
    FOREIGN KEY (user_email) REFERENCES user(user_email)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE Table Place(
	Place_id bigInt primary key auto_increment,
	Place_name varchar(30) not null,
    place_note varchar(200),
    passed boolean,
    x_pos double,
    y_pos double,
	plan_id bigint not null,
	create_at TIMESTAMP,
    modified_at TIMESTAMP,
    FOREIGN KEY (plan_id) REFERENCES Plan(plan_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


-- User 테이블 더미 데이터
INSERT INTO User (user_email, user_nickname, user_age_range, user_picture, create_at, modified_at)
VALUES
    ('user1@example.com', 'User 1', '20-30', 'user1_picture.jpg', NOW(), NOW()),
    ('user2@example.com', 'User 2', '30-40', 'user2_picture.jpg', NOW(), NOW()),
    ('user3@example.com', 'User 3', '40-50', 'user3_picture.jpg', NOW(), NOW()),
    ('user4@example.com', 'User 4', '20-30', 'user4_picture.jpg', NOW(), NOW()),
    ('user5@example.com', 'User 5', '30-40', 'user5_picture.jpg', NOW(), NOW());

-- Plan 테이블 더미 데이터
INSERT INTO Plan (plan_name, plan_note, user_email, passed, create_at, modified_at)
VALUES
    ('Plan 1', 'Plan 1 Note', 'user1@example.com', true, NOW(), NOW()),
    ('Plan 2', 'Plan 2 Note', 'user2@example.com', false, NOW(), NOW()),
    ('Plan 3', 'Plan 3 Note', 'user3@example.com', true, NOW(), NOW()),
    ('Plan 4', 'Plan 4 Note', 'user4@example.com', true, NOW(), NOW()),
    ('Plan 5', 'Plan 5 Note', 'user5@example.com', false, NOW(), NOW());

-- Place 테이블 더미 데이터
INSERT INTO Place (Place_name, place_note, passed, x_pos, y_pos, plan_id, create_at, modified_at)
VALUES
    ('Place 1', 'Place 1 Note', true, 12.345, 67.890, 1, NOW(), NOW()),
    ('Place 2', 'Place 2 Note', false, 34.567, 89.012, 1, NOW(), NOW()),
    ('Place 3', 'Place 3 Note', true, 56.789, 12.345, 2, NOW(), NOW()),
    ('Place 4', 'Place 4 Note', true, 78.901, 23.456, 3, NOW(), NOW()),
    ('Place 5', 'Place 5 Note', false, 90.123, 45.678, 4, NOW(), NOW());