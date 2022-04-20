create table restaurants (
	id SERIAL NOT NULL,  
	kitchen_id BIGINT NOT NULL, 
	name varchar(100) NOT NULL, 
	tax_freight numeric(19, 2) NOT NULL, 
	creation_date timestamp NOT NULL, 
	update_date timestamp NOT NULL, 
	
	address_cep varchar(9), 
	address_complement varchar(60), 
	address_district varchar(150), 
	address_number varchar(20), 
	address_place varchar(150),
	address_city_id BIGINT, 
	
	primary key (id)
	);
	
alter table restaurants add constraint FK_restaurants_address_city foreign key (address_city_id) references cities (id);
alter table restaurants add constraint FK_restaurants_kitchen foreign key (kitchen_id) references kitchens (id);