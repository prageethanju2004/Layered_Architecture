package dao;

import db.DBConnection;
import model.ItemDTO;
import model.OrderDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO {

    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException;

    public boolean delete(String code) throws SQLException, ClassNotFoundException;

    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean exit(String code) throws SQLException, ClassNotFoundException;

    public String generateNewId();

    public ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException;

    public ItemDTO findItem(String code);

    public boolean updateQty(List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;

    private boolean update1(ItemDTO item, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }
}
