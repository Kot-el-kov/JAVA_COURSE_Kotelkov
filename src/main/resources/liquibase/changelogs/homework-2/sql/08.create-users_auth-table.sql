CREATE TABLE "users_auth" (
	"id" bigint NOT NULL,
	"role_id" bigint NOT NULL,
	"login" varchar(255) NOT NULL UNIQUE,
	"password" varchar(255) NOT NULL,
	CONSTRAINT "users_auth_pk" PRIMARY KEY ("id")
);