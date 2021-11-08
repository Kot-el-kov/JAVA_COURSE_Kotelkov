CREATE TABLE "stores" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"address" varchar(255) NOT NULL,
	CONSTRAINT "stores_pk" PRIMARY KEY ("id")
);