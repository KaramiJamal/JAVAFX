package ma.jakardev.javafx.services.impl;

import ma.jakardev.javafx.dao.CategoryDAO;
import ma.jakardev.javafx.dao.ProductDAO;
import ma.jakardev.javafx.dao.entities.Category;
import ma.jakardev.javafx.dao.entities.Product;
import ma.jakardev.javafx.services.CatalogService;

import java.util.List;

public class CatalogServiceImpl implements CatalogService{
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public CatalogServiceImpl(ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    public void addProduct(Product p) {
        productDAO.save(p);
    }

    @Override
    public void updateProduct(Product p) {
        productDAO.update(p);
    }

    @Override
    public void deleteProduct(Product p) {
        productDAO.delete(p);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    @Override
    public void addCategory(Category c) {
        categoryDAO.save(c);
    }

    @Override
    public void deleteCategory(Category c) {
        categoryDAO.delete(c);
    }

    public List<Product> searchProductByQuery(String query){
        return productDAO.findByQuery(query);
    }
}
