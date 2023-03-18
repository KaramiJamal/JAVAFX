package ma.jakardev.javafx.dao.Impl;

import ma.jakardev.javafx.dao.ProductDAO;
import ma.jakardev.javafx.dao.entities.Product;
import ma.jakardev.javafx.services.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDAO {
    private Connection connection = DBConnection.getConnection();

    @Override
    public Product find(long id) {
        Product product = null;
        try {
            PreparedStatement ps =  connection.prepareStatement("select * from Products where id=?");
            ps.setLong(1, id);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setReference(rs.getString("refrence"));
                product.setPrice(rs.getFloat("price"));
                product.setCategory(new CategoryDaoImpl().find(rs.getLong("category")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = null;
        try {
            PreparedStatement ps =  connection.prepareStatement("select * from Products");
            products = getProducts(ps);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void save(Product o) {
        try {
            PreparedStatement ps =  connection.prepareStatement("insert into Products(name, refrence, price, category) values(?,?,?,?)");
            ps.setString(1, o.getName());
            ps.setString(2, o.getReference());
            ps.setFloat( 3, o.getPrice());
            ps.setLong(  4, o.getCategory().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Product o) {
        try {
            PreparedStatement ps =  connection.prepareStatement("delete from Product where id=?");
            ps.setLong(1, o.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product o) {
        try {
            PreparedStatement ps =  connection.prepareStatement("update Products set name=?, refrence=?, price=?, category=? where id=?");
            ps.setString(1, o.getName());
            ps.setString(2, o.getReference());
            ps.setFloat( 3, o.getPrice());
            ps.setLong(  4, o.getCategory().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findByQuery(String query) {
        List<Product> products = null;
        try {
            PreparedStatement ps =  connection.prepareStatement("select * from Products where name like %?% or refrence like %?%");
            ps.setString(1, query);
            ps.setString(2, query);
            products = getProducts(ps);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    private List<Product> getProducts(PreparedStatement ps) throws SQLException {
        List<Product> products;
        ResultSet rs =  ps.executeQuery();
        products = new ArrayList<>();
        while(rs.next()){
            products.add(
                    new Product(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("refrence"),
                            rs.getFloat("price"),
                            new CategoryDaoImpl().find(rs.getLong("category"))
                    )
            );
        }
        return products;
    }
}
