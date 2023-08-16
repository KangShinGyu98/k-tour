
Create database ktour;
use ktour;
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

drop table Tourlist;

CREATE Table Tourlist(
	list_id bigInt primary key auto_increment,
	tour_list text not null,
    passed boolean,
    user_email Varchar(50),
    FOREIGN KEY (user_email) REFERENCES user(user_email)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

