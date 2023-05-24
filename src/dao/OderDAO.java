package dao;

import db.DBConnection;
import model.OrderDetailDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public interface OderDAO {

    public String generateNewOrderId() throws SQLException, ClassNotFoundException;

    public boolean save(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;

    boolean save(String orderId, List<OrderDetailDTO> orderDetails);
}
