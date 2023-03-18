package ma.jakardev.javafx.dao;

import ma.jakardev.javafx.dao.entities.Product;

import java.util.List;

public interface ProductDAO extends Dao<Product> {
    List<Product> findByQuery(String query);
}
