create table Book
(
	sn varchar(50), 
	title varchar(50) not null, 
    author varchar(50) not null,
	publisher varchar(50) not null, 
	price float(5, 2) not null, 
	quantity int default 0, 
	issued int, 
	addedDate Date,
    PRIMARY KEY (sn)
)

insert into Book(sn, title, author, publisher, price, quantity, issued, addedDate) values('9780124179','The Fault In Our Stars', 'John Green', 'Penguin Books', 20.49, 4, 0, date('now'));
insert into Book(sn, title, author, publisher, price, quantity, issued, addedDate) values('0670532576','The Outsiders', 'S. E. Hinton', 'Viking Press, Dell Publishing', 16.00, 3, 0,date('now'));
insert into Book(sn, title, author, publisher, price, quantity, issued, addedDate) values('1619635180','A Court of Thorns and Roses', 'Sarah J. Maas', 'Penguin Books', 25.00, 5, 0,date('now'));
insert into Book(sn, title, author, publisher, price, quantity, issued, addedDate) values('9781406751','After You', 'Jojo Moyes', 'Pamela Dorman Books', 24.95, 2, 0, date('now'));
insert into Book(sn, title, author, publisher, price, quantity, issued, addedDate) values('8025046876','Dune', 'Frank Herbert', 'Chilton Books', 13.92, 6, 0,date('now'));
insert into Book(sn, title, author, publisher, price, quantity, issued, addedDate) values('4700115866','1984', 'George Orwell', 'Secker & Warburg', 11.99, 3, 0, date('now'));
insert into Book(sn, title, author, publisher, price, quantity, issued, addedDate) values('2824723329','Inferno', 'Dan Brown', '	Doubleday', 201.99, 4, 0,date('now'));
