package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class JdbcProductRepository implements ProductRepository {

    @Autowired
    private final Connection connection;

    @Override
    public void createProduct(Product product) throws Exception {
        try(final PreparedStatement statement = connection.
                prepareStatement("insert into products (id,name,price,description) values (?, ?, ?, ?)")){
            statement.setInt(1,product.getId());
            statement.setString(2,product.getName());
            statement.setInt(3,product.getPrice());
            statement.setString(4,product.getDescription());
            statement.executeUpdate();
        }
    }

    @Override
    public Product getProductById(int id) throws Exception {
        try(final PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM products where id = ?")){
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setDescription(rs.getString("description"));
            return product;
        }
    }


    @Override
    public List<Product> getAllProducts() throws Exception{
        try(final PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM products")){
            ArrayList<Product> productArrayList = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));
                productArrayList.add(product);
            }
            return productArrayList;
        }
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        try(final PreparedStatement statement = connection.
                prepareStatement("update products set \"name\" = ?,price = ?,description = ? where id = ?")) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteProductById(int id) throws Exception {
        try(final PreparedStatement statement = connection.
                prepareStatement("delete from products where id = ?")){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
    }
}
