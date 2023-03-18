package ma.jakardev.javafx.dao.Impl;

import ma.jakardev.javafx.dao.CategoryDAO;
import ma.jakardev.javafx.dao.entities.Category;
import ma.jakardev.javafx.services.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDAO {
    private Connection connection = DBConnection.getConnection();

    @Override
    public Category find(long id) {
        Category category = null;
        try {
            PreparedStatement ps =  connection.prepareStatement("select * from Category where id=?");
            ps.setLong(1, id);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                category = new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> category = null;
        try {
            PreparedStatement ps =  connection.prepareStatement("select * from Category");
            ResultSet rs =  ps.executeQuery();
            category = new ArrayList<>();
            while(rs.next()){
                category.add(new Category(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public void save(Category o) {
        try {
            PreparedStatement ps =  connection.prepareStatement("insert into Category(name) values(?)");
            ps.setString(1, o.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Category o) {
        try {
            PreparedStatement ps =  connection.prepareStatement("delete from Category where id=?");
            ps.setLong(1, o.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Category o) {
        try {
            PreparedStatement ps =  connection.prepareStatement("update Category set name=? where id=?");
            ps.setString(1, o.getName());
            ps.setLong(2, o.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
