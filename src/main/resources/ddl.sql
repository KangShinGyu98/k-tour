use ktour;
# drop table tourlist;
drop table user;


#객체
#User(email[PK], nickname, age_range,gender ,picture);
Create Table User(
	user_email Varchar(50) primary key,
    user_nickname varchar(30),
    user_age_range varchar(15),
    user_picture text
);

#Tourlist
# email - tour_list = 1:n
#Tourlist(list_number[PK], email,tour_list)


Create Table Plan(
	plan_id bigInt primary key auto_increment,
	plan_name varchar(30) not null,
	plan_note varchar(200),
	user_email varchar(50) not null,
	passed boolean,
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
    FOREIGN KEY (plan_id) REFERENCES Plan(plan_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

