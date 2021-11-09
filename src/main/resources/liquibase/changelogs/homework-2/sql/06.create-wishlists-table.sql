CREATE TABLE "wishlists" (
	"product_id" bigint NOT NULL,
	"user_id" bigint NOT NULL,
	CONSTRAINT "wishlists_pk" PRIMARY KEY ("user_id")
);