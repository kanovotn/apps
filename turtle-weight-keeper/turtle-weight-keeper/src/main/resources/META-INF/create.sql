CREATE TABLE TURTLE ("ID" INTEGER not null primary key, "NAME" VARCHAR(20) not null, "YEAR_OF_BIRTH" YEAR not null)
CREATE TABLE TURTLE_WEIGHT ("ID" INTEGER not null primary key, "DATE" DATE not null, "WEIGHT" INT not null, "TURTLE_ID" INT references TURTLE (ID))