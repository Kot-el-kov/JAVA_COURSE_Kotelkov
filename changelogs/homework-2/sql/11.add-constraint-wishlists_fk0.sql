ALTER TABLE "wishlists" ADD CONSTRAINT "wishlists_fk0" FOREIGN KEY ("product_id") REFERENCES "products"("id");