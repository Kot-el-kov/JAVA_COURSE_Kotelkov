CREATE TABLE "products" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"price" bigint NOT NULL,
	"description" TEXT NOT NULL,
	CONSTRAINT "products_pk" PRIMARY KEY ("id")
);