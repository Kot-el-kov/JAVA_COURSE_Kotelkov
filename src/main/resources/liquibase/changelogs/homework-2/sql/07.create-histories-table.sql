CREATE TABLE "histories" (
	"product_id" serial NOT NULL,
	"user_id" serial NOT NULL,
	CONSTRAINT "histories_pk" PRIMARY KEY ("user_id","product_id")
);