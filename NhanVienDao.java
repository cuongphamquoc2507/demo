/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import Data.DatabaseHelper;
import Model.NguoiDung;

/**
 *
 * @author 54321
 */
public class NhanVienDao {
     public boolean insert(NguoiDung nd) throws Exception{
        String sql = "INSERT INTO dbo.NGUOIDUNG(TenND  ,NgaySinh ,SoDT ,GioiTinh ,DiaChi,ChucVu,UserName, Pass, Hinh) values(?,?,?,?,?,?,?,?,?)";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
//            pstmt.setString (1,nd.getMaND());
            pstmt.setString (1,nd.getTenND());
            pstmt.setString(2, nd.getNgaySinh());
            pstmt.setString(3,nd.getSoDT());
            pstmt.setInt(4,nd.getGioiTinh());
            pstmt.setString (5,nd.getDiaChi());
            pstmt.setString(6,nd.getChucVu());
            pstmt.setString(7,nd.getUserName());
            pstmt.setString(8,nd.getPass());
           
            if(nd.getHinh() != null)
            {
                Blob hinh = new SerialBlob ( nd.getHinh());
                pstmt.setBlob(9, hinh);
            }
            else{
                Blob hinh = null;
                pstmt.setBlob(9, hinh);
            }
            return pstmt.executeUpdate()>0;
        }
           
    }   
    public boolean update(NguoiDung nd) throws Exception
    {
        String sql = "UPDATE dbo.NGUOIDUNG "
                + " SET TenND =?, NgaySinh=?, SoDT =? ,GioiTinh =?,DiaChi=? ,ChucVu=? ,UserName=?, Pass=?, Hinh=?"
                +"  where MaND =? ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(10,nd.getMaND());
            pstmt.setString (1,nd.getTenND());
            pstmt.setString(2,nd.getNgaySinh());
            pstmt.setString(3,nd.getSoDT());
            pstmt.setInt(4,nd.getGioiTinh());
            pstmt.setString (5,nd.getDiaChi());
            pstmt.setString(6,nd.getChucVu());
            pstmt.setString(7,nd.getUserName());
            pstmt.setString(8,nd.getPass());
            if(nd.getHinh() != null)
            {
                Blob hinh = new SerialBlob ( nd.getHinh());
                pstmt.setBlob(9, hinh);
            }
            else{
                Blob hinh = null;
                pstmt.setBlob(9, hinh);
            }
            return pstmt.executeUpdate()>0;
        }
           
    }    
    public  boolean  delete (Integer MaNV) throws Exception
    {
        String sql = "delete from NGUOIDUNG where MaND = ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,MaNV);
           
          return pstmt.executeUpdate()>0;
        }
    }
    public  NguoiDung  findDung( Integer MaND) throws Exception
    {
        String sql = "select * from NGUOIDUNG where MaND = ? ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,MaND);
            try(ResultSet rs = pstmt.executeQuery(); )
            { 
               
                if(rs.next())
                {
                   NguoiDung nd = TimKiem(rs);
                    return  nd;
                }
            }
        return null;
        }
    }
    public  NguoiDung  findTen( String TenND) throws Exception
    {
        String sql = "select * from NGUOIDUNG where TenND like ? ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setString(1, TenND);
            try(ResultSet rs = pstmt.executeQuery(); )
            { 
               
                if(rs.next())
                {
                   NguoiDung nd = TimKiem(rs);
                    return  nd;
                }
            }
        return null;
        }
    }
    public  List<NguoiDung>findAll() throws Exception{
         String sql = "select *  from NGUOIDUNG ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
            { 
            try(ResultSet rs = pstmt.executeQuery();)
            { 
                List<NguoiDung> list = new ArrayList<>();
                while(rs.next())
                {
                    NguoiDung sv = TimKiem(rs);
                    list.add(sv);
                }
                return list;
            }
        }
        
    }

    private NguoiDung TimKiem(final ResultSet rs) throws SQLException {
        NguoiDung nd = new NguoiDung();
        nd.setMaND(rs.getInt("MaND"));
        nd.setTenND(rs.getString("TenND"));
        nd.setNgaySinh(rs.getString("NgaySinh"));
        nd.setSoDT(rs.getString("SoDT"));
        nd.setGioiTinh(rs.getInt("GioiTinh"));
        nd.setDiaChi(rs.getString("DiaChi"));
        nd.setChucVu(rs.getString("ChucVu"));
        nd.setUserName(rs.getString("UserName"));
        nd.setPass(rs.getString("Pass"));
        Blob blob =rs.getBlob("Hinh");
        if(blob!=null)
            nd.setHinh(blob.getBytes(1, (int) blob.length()));
        return nd;
    }

    
}
