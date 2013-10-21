-- Database: "commments.db"

-- DROP DATABASE "commments.db";

CREATE DATABASE "commments.db"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'French_France.1252'
       LC_CTYPE = 'French_France.1252'
       CONNECTION LIMIT = 2;


-- Table: comments

-- DROP TABLE comments;

CREATE TABLE comments
(
  comments_id integer NOT NULL,
  comments_description text NOT NULL,
  CONSTRAINT "Comments_pkey" PRIMARY KEY (comments_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE comments
  OWNER TO postgres;