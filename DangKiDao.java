/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Data.DatabaseHelper;
import Model.NguoiDung;


/**
 *
 * @author 54321
 */
public class DangKiDao {
    public boolean update(NguoiDung nguoi) throws Exception
    {
        String sql = "UPDATE dbo.NGUOIDUNG "
                + " SET UserName = ?,Pass = ? "
                + " WHERE MaND = ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(3,nguoi.getMaND());
            pstmt.setString (1,nguoi.getUserName());
             pstmt.setString (2,nguoi.getPass());
            return pstmt.executeUpdate()>0;
        }
    }
}
