package oscar.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oscar.utils.Constants;
import oscar.utils.DbHelpers;

/**
 *
 * @author Oscar
 */
public class UserDAO implements Serializable {

    private static final String SQL_AUTH = "SELECT FullName, Role "
            + "FROM Users"
            + "WHERE Email = ? AND Password = ? ";

    private static final String SQL_SEARCH_USER = "SELECT Email, FullName,"
            + " Address, Country "
            + "FROM Users"
            + "WHERE Role = ? AND Password = ?";

    public UserDTO authentication(String email, String password)
            throws ClassNotFoundException, SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DbHelpers.getConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_AUTH);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("FullName");
                    String role = rs.getString("Role");
                    user = new UserDTO(0, "", "", fullName, "", "", role);
                }
            }
        } finally {
            DbHelpers.closeConnection(rs, stm, con);
        }
        return user;
    }

    public List<UserDTO> getUser(String searchValue) throws ClassNotFoundException, SQLException {
        List<UserDTO> users = null;
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DbHelpers.getConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_SEARCH_USER);
                stm.setString(1, Constants.CUSTOMER_ROLE);
                stm.setString(2, "%" + searchValue + "%");
                rs = stm.executeQuery();

                while (rs.next()) {
                    String email = rs.getString("Email");
                    String fullName = rs.getString("FullName");
                    String address = rs.getString("Address");
                    String country = rs.getString("Country");

                    user = new UserDTO(0, email, "", fullName, address, country, "");
                    if (user == null) {
                        users = new ArrayList<>();
                    }
                    users.add(user);

                }
            }
        } finally {
            DbHelpers.closeConnection(rs, stm, con);
        }
        return users;
    }
    
    public boolean deleteUser(String email){
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        return false;
        
    }
}
