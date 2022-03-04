package manager;

import db.DBConnectionProvider;
import model.Picture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PictureManager {
    private  static Connection connection = DBConnectionProvider.getInstance().getConnection();
    private ItemManager itemManager=new ItemManager();

    public void add(Picture picture,int id) {

        String sql = "insert into picture(picture_url,item_id) VALUES(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, picture.getPictureUrl());
            statement.setInt(2,id);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                picture.setId(anInt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Picture getPictureFromResultSet(ResultSet resultSet) {
        try {
            return Picture.builder()
                    .id(resultSet.getInt(1))
                    .pictureUrl(resultSet.getString(2))
                    .item(itemManager.getItemById(resultSet.getInt(3)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Picture> getAllPicturesByItemId (int id) {
        List<Picture> pictures = new ArrayList<>();
        String sql = "SELECT * FROM picture WHERE item_id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
               Picture picture = new Picture();
                picture.setId(resultSet.getInt(1));
                picture.setPictureUrl(resultSet.getString(2));
                picture.setItem(itemManager.getItemById(resultSet.getInt(3)));
                pictures.add(picture);
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return pictures;
    }
}
