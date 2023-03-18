package ma.jakardev.javafx.services;

import ma.jakardev.javafx.dao.entities.Category;
import ma.jakardev.javafx.dao.entities.Product;

import java.util.List;

public interface CatalogService {
    List<Product> getAllProducts();
    void addProduct(Product p);
    void updateProduct(Product p);
    void deleteProduct(Product p);
    List<Category> getAllCategories();
    void addCategory(Category c);
    void deleteCategory(Category c);

    List<Product> searchProductByQuery(String query);
}
