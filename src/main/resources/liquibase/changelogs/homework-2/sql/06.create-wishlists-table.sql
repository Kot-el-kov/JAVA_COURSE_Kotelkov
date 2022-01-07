CREATE TABLE "wishlists" (
	"product_id" serial NOT NULL,
	"user_id" serial NOT NULL,
	CONSTRAINT "wishlists_pk" PRIMARY KEY ("user_id","product_id")
);