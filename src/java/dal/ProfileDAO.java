/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.IConstant;
import entity.profile.AccAddress;
import entity.profile.AccDetail;
import entity.OrderDetail;
import entity.OrderStatus;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProfileDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean changePassword(String username, String password) {
        String query = "UPDATE ACCOUNT SET [password] = ? "
                + "WHERE username = ? ";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, username);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean insertProfile(String usernameDe, String nickname, String email, String phone, String gender, Date dob) {
        String query = "INSERT INTO ACCOUNT_DETAILS (usernameDe, nickname, email, phone, gender, dateOfBirth)\n"
                + "VALUES ( ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usernameDe);
            ps.setString(2, nickname);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, gender);
            ps.setDate(6, dob);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean updateProfile(String usernameDe, String nickname, String email, String phone, String gender, Date dob) {
        String query = "UPDATE ACCOUNT_DETAILS SET nickname = ?, email = ?, phone = ?, gender = ?, dateOfBirth = ?\n"
                + "WHERE usernameDe = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nickname);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, gender);
            ps.setDate(5, dob);
            ps.setString(6, usernameDe);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     *
     * @param username
     * @return
     */
    public boolean isAccDetailExist(String username) {
        String query = "Select * from ACCOUNT_DETAILS where usernameDe = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean isAccDetailExist(int accid) {
        String query = "Select * from ACCOUNT_DETAILS where accID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    public AccDetail getAccountDetail(String username) {
        String query = "Select * from ACCOUNT_DETAILS where usernameDe = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new AccDetail(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean isAccAddressExist(String username) {
        String query = "Select * from ACCOUNT_ADDRESS where usernameAdd = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    public AccAddress getAccountAdress(String username) {
        String query = "Select * from ACCOUNT_Address where usernameAdd = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new AccAddress(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean insertAddress(String usernameAdd, String nickname, String phone_addr, String address, String address_detail, String status) {
        String query = "INSERT INTO ACCOUNT_Address (usernameAdd, nickname, phone_addr, address, address_detail, status)\n"
                + "VALUES ( ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usernameAdd);
            ps.setString(2, nickname);
            ps.setString(3, phone_addr);
            ps.setString(4, address);
            ps.setString(5, address_detail);
            ps.setString(6, status);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean updateAddress(String usernameAdd, String nickname, String phone_addr, String address, String address_detail, String status) {
        String query = "UPDATE ACCOUNT_Address SET nickname = ?, phone_addr = ?, address = ?, address_detail = ?, status = ?\n"
                + "WHERE usernameAdd = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nickname);
            ps.setString(2, phone_addr);
            ps.setString(3, address);
            ps.setString(4, address_detail);
            ps.setString(5, status);
            ps.setString(6, usernameAdd);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        Date dobFrom = Date.valueOf("2013-11-11");
        ProfileDAO PDAO = new ProfileDAO();

        boolean o = PDAO.updateProfile("admin","ád", "s", "", "s", dobFrom);
        if (o) { 
            System.out.println("ok");
        } else {
            System.out.println("none");
        }
//        AccAddress ac = pdao.getAccountAdress("Phạm Hoan");
     
    }

}
