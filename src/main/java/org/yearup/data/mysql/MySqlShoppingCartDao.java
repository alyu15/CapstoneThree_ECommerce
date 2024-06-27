package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component

public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {

        ShoppingCart shoppingCart = new ShoppingCart();

        String query = "SELECT * FROM shopping_cart AS s " +
                "JOIN products AS p " +
                "ON p.product_id = s.product_id " +
                "WHERE user_id = ?";

        try(Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                ShoppingCartItem item = new ShoppingCartItem();

                item.setProduct(MySqlProductDao.mapRow(resultSet));

                shoppingCart.add(item);

            }

        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return shoppingCart;
    }

    @Override
    public void addProductToCart(int userId, int productId) {

        String query = "INSERT INTO shopping_cart(user_id, product_id, quantity) VALUES(?, ?, ?)";

        try(Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, 1);

            preparedStatement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void incrementProductInCart(int userId, int productId, ShoppingCartItem item) {

        ShoppingCart shoppingCart = getByUserId(userId);

        String query = "UPDATE shopping_cart SET quantity = quantity + 1 WHERE user_id = ? AND product_id = ?";

        try(Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void emptyCart(int userId) {

        String query = "DELETE FROM shopping_cart WHERE user_Id = ?";

        try(Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
