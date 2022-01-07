CREATE TABLE "users_auth" (
	"id" serial NOT NULL,
	"role_id" serial NOT NULL,
	"login" varchar(255) NOT NULL UNIQUE,
	"password" varchar(255) NOT NULL,
	CONSTRAINT "users_auth_pk" PRIMARY KEY ("id")
);