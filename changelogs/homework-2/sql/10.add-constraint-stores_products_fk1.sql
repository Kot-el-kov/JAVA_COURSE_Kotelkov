ALTER TABLE "stores_products" ADD CONSTRAINT "stores_products_fk1" FOREIGN KEY ("store_id") REFERENCES "stores"("id");