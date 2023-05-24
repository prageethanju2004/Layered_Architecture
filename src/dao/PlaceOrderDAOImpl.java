package dao;

import db.DBConnection;
import model.ItemDTO;
import model.OrderDetailDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderDAOImpl {
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) {
        /*Transaction*/
        Connection connection = null;
        try {
            connection.setAutoCommit(false);
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            boolean isSave = orderDAO.save(orderId,orderDate,customerId);

            if (!isSave) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            connection.setAutoCommit(false);
            OderDAO orderDetailDAO = new OrderDetailDAOImpl();
            boolean isSave1 = orderDetailDAO.save(orderId,orderDetails);

            if (!isSave1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO detail : orderDetails) {

                ItemDAOImpl itemDAO  = new ItemDAOImpl();

                connection.setAutoCommit(false);
                boolean isUpdateQty = itemDAO.updateQty(orderDetails);

                if (!isUpdateQty) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
