package Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Data.DatabaseHelper;
import Model.NguoiDung;

public class NguoiDungDao {
    public NguoiDung checkLogin(String username, String matkhau)
            throws  Exception{
        String sql="select UserName, Pass,TenND, ChucVu  from NGUOIDUNG"
                +" where UserName =? and Pass = ?";
        try(
                Connection con=DatabaseHelper.openConnection();
            PreparedStatement patmt=con.prepareStatement(sql);
        ){
            patmt.setString(1, username);
            patmt.setString(2,matkhau);
            try(ResultSet rs=patmt.executeQuery();){
                if(rs.next()){
                    NguoiDung nd=new NguoiDung();
                    nd.setUserName(username);
                    nd.setPass(matkhau);
                   nd.setTenND(rs.getString("TenND"));
                    nd.setChucVu(rs.getString("ChucVu"));
                    
                    return nd;
                }
            }
        }
        return null;
    }
    
}
