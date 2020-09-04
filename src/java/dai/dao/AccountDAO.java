/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dai.dao;

import dai.dto.AccountDTO;
import dai.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author AD
 */
public class AccountDAO implements Serializable {

//    public boolean checkLogin(String username, String password)
//            throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//
//        try {
//            //1.Make Connection
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                //2.Create Sql String
//                String sql = "Select Username "
//                        + "From Account "
//                        + "Where Username = ? and Password = ?";
//                //3.Create statement
//                stm = con.prepareStatement(sql);
//                stm.setString(1, username);
//                stm.setString(2, password);
//                //4.Excute data
//                rs = stm.executeQuery();
//                //5.Process
//                if (rs.next()) {
//                    return true;
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return false;
//    }
    public AccountDTO checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create Sql String
                String sql = "SELECT Username, Password, Fullname, IsAdmin"
                        + "  FROM Account "
                        + "Where Username = ? and Password = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Excute data
                rs = stm.executeQuery();
                //5.Process
                if (rs.next()) {
                    String fullName = rs.getString("Fullname");
                    boolean role = rs.getBoolean("IsAdmin");
                    AccountDTO dto = new AccountDTO(username, password, fullName, role);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    private List<AccountDTO> listAccounts;

    public List<AccountDTO> getListAccounts() {
        return listAccounts;
    }

    public boolean searchLastName(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create Sql String
                String sql = "Select Username, Password, Fullname, IsAdmin "
                        + "From Account "
                        + "Where Fullname like ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4.Excute data
                rs = stm.executeQuery();
                //5.Process
                while (rs.next()) {
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String fullname = rs.getString("Fullname");
                    boolean role = rs.getBoolean("IsAdmin");
                    AccountDTO dto = new AccountDTO(username, password, fullname, role);
                    if (this.listAccounts == null) {
                        this.listAccounts = new ArrayList<>();
                    }
                    this.listAccounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deleteAccount(String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create Sql String
                String sql = "Delete From Account Where username = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.Excute data
                int row = stm.executeUpdate();
                //5.Process
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateAccount(AccountDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create Sql String
                String sql = "Update Account "
                        + "Set Password=?, IsAdmin=? "
                        + "Where username = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getPassword());
                stm.setBoolean(2, dto.isRole());
                stm.setString(3, dto.getUsername());
                //4.Excute data
                int row = stm.executeUpdate();
                //5.Process
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean createNewAccount(String username, String password, String fullname, boolean role)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create Sql String
                String sql = "Insert into Account(Username, Password, Fullname, IsAdmin) "
                        + "Values(?,?,?,?)";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
                //4.Excute data
                int row = stm.executeUpdate();
                //5.Process
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public ArrayList<String> loadProduct()
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        ArrayList<String> listProduct = new ArrayList<>();
        try {
            //1.Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create Sql String
                String sql = "SELECT ProductName "
                        + "From Products";
                //3.Create statement
                stm = con.prepareStatement(sql);
                //4.Excute data
                rs = stm.executeQuery();
                //5.Process
                while (rs.next()) {
                    String productName = rs.getString("ProductName");
                    listProduct.add(productName);                  
                }
                return listProduct;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean insertOrder(String cusName, String address, int phone)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create Sql String
                String sql = "Insert into Orders(CusName, Address, Phone) "
                        + "Values(?,?,?)";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, cusName);
                stm.setString(2, address);
                stm.setInt(3, phone);
                //4.Excute data
                int row = stm.executeUpdate();
                //5.Process
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public int getOrderID(String cusName, String address, int phone)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create Sql String
                String sql = "Select ID "
                        + "From Orders "
                        + "Where CusName = ? and Address = ? and Phone = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, cusName);
                stm.setString(2, address);
                stm.setInt(3, phone);
                //4.Excute data
                rs = stm.executeQuery();
                //5.Process
                if (rs.next()) {
                    return rs.getInt("ID");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }
    
    public boolean insertOrderDetail(int orderID, String title, int quantity)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create Sql String
                String sql = "Insert into OrderDetail(OrderID, Title, Quantity) "
                        + "Values(?,?,?)";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                stm.setString(2, title);
                stm.setInt(3, quantity);
                //4.Excute data
                int row = stm.executeUpdate();
                //5.Process
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
   
}
