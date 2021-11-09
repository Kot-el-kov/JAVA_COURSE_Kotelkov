CREATE TABLE "users_profiles" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"surname" varchar(255) NOT NULL,
	"email" varchar(255) NOT NULL,
	CONSTRAINT "users_profiles_pk" PRIMARY KEY ("id")
);