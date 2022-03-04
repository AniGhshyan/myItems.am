package manager;

import db.DBConnectionProvider;
import model.Item;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();


    public void add(Item item) {
        String sql = "insert into item(title,price,description,user_id,category_id) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getTitle());
            statement.setDouble(2, item.getPrice());
            statement.setString(3, item.getDescription());
            statement.setInt(4, item.getUser().getId());
            statement.setInt(5, item.getCategory().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                item.setId(anInt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item getItemById(int id) {
        String sql = "SELECT * FROM item WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setTitle(resultSet.getString(2));
                item.setPrice(resultSet.getDouble(3));
                item.setDescription(resultSet.getString(4));
                item.setUser(userManager.getUserById(resultSet.getInt(5)));
                item.setCategory(categoryManager.getCategoryById(resultSet.getInt(6)));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getLast20Items() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item ORDER BY id  DESC LIMIT 20";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                items.add(getItemFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getLast20ItemsByCategory(int categoryId) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item where category_id = " + categoryId + " order by id desc limit 20";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                items.add(getItemFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getAllUserItems(int userId) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item where user_id = " + userId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                items.add(getItemFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private Item getItemFromResulSet(ResultSet resultSet) {
        try {
            return Item.builder()
                    .id(resultSet.getInt(1))
                    .title(resultSet.getString(2))
                    .price(resultSet.getDouble(3))
                    .description(resultSet.getString(4))
                    .user(userManager.getUserById(resultSet.getInt(5)))
                    .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteById(int id) {
        String sql = "delete FROM item WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
