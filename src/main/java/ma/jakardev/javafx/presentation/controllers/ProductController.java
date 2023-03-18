package ma.jakardev.javafx.presentation.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.jakardev.javafx.dao.CategoryDAO;
import ma.jakardev.javafx.dao.Impl.CategoryDaoImpl;
import ma.jakardev.javafx.dao.Impl.ProductDaoImpl;
import ma.jakardev.javafx.dao.ProductDAO;
import ma.jakardev.javafx.dao.entities.Category;
import ma.jakardev.javafx.dao.entities.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    private CategoryDAO categoryDAO = new CategoryDaoImpl();
    private ProductDAO productDAO = new ProductDaoImpl();
    private ObservableList data = FXCollections.observableArrayList();
    @FXML
    TextField name;
    @FXML
    TextField reference;
    @FXML
    TextField price;
    @FXML
    ComboBox<Category> category;
    @FXML
    TextField search;

    @FXML
    TableView<Product> tableDataProduct;
    @FXML TableColumn<Long, Product> columnID;
    @FXML TableColumn<String, Product> columnNom;
    @FXML TableColumn<String, Product> columnRef;
    @FXML TableColumn<Float, Product> columnPrice;
    @FXML TableColumn<Category, Product> columnCat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnCat.setCellValueFactory(new PropertyValueFactory<>("category"));

        category.getItems().addAll(categoryDAO.findAll());
        tableDataProduct.setItems(data);
        loadDataProducts();
    }

    private void loadDataProducts(){
        data.clear();
        data.addAll(productDAO.findAll());
    }
    public void addProduct(){
        CharSequence nameP = name.getCharacters();
        CharSequence refrenceP = reference.getCharacters();
        CharSequence priceP = price.getCharacters();
        Category category1 = category.getSelectionModel().getSelectedItem();

        Product product = new Product(nameP.toString(), refrenceP.toString(), Float.valueOf(priceP.toString()), category1);
        productDAO.save(product);
        loadDataProducts();
    }

    public void updateProduct(){
        System.out.println("update");
    }

    public void deleteProduct(){
        System.out.println("delete");
    }
}
