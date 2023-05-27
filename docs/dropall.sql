--- database personen
DROP TABLE IF EXISTS databasechangelog;
DROP TABLE  IF EXISTS databasechangeloglock;
DROP TABLE  IF EXISTS person_grants;
DROP TABLE  IF EXISTS person;
DROP TABLE  IF EXISTS person_grants;
DROP TABLE  IF EXISTS role;

DROP SEQUENCE  IF EXISTS person_grants_id_seq;
DROP SEQUENCE  IF EXISTS person_grants_id_seq;
DROP SEQUENCE  IF EXISTS person_id_seq;
DROP SEQUENCE  IF EXISTS role_id_seq;
DROP SEQUENCE  IF EXISTS role_seq;


--- database items
DROP TABLE IF EXISTS databasechangelog;
DROP TABLE  IF EXISTS databasechangeloglock;
DROP TABLE IF EXISTS item;
DROP SEQUENCE  IF EXISTS item_id_seq;