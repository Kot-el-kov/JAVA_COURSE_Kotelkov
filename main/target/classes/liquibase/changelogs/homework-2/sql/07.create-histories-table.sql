CREATE TABLE "histories" (
	"product_id" serial NOT NULL,
	"user_id" bigint NOT NULL,
	CONSTRAINT "histories_pk" PRIMARY KEY ("user_id")
);