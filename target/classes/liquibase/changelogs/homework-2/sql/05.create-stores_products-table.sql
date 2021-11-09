CREATE TABLE "stores_products" (
	"product_id" bigint NOT NULL,
	"store_id" bigint NOT NULL,
	CONSTRAINT "stores_products_pk" PRIMARY KEY ("product_id","store_id")
);