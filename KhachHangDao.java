/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Data.DatabaseHelper;
import Model.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 54321
 */
public class KhachHangDao {
     public boolean insert(KhachHang kh) throws Exception{
        String sql = "INSERT INTO dbo.KHACHHANG(HoTen,NgaySinh,SoDT,CMDN,Email,DiaChi) VALUES(?,?,?,?,?,?)";
        try( Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
//            pstmt.setString (1,kh.getMakh());
            pstmt.setString (1,kh.getHoten());
            pstmt.setString(2,kh.getNgaysinh());
            pstmt.setString(3,kh.getSdt());
            pstmt.setString(4,kh.getCmdn());
            pstmt.setString (5,kh.getEmail());
            pstmt.setString(6,kh.getDiachi());
           
          
            return pstmt.executeUpdate()>0;
        }
           
    }   
    public boolean update(KhachHang kh) throws Exception
    {
        String sql = "UPDATE dbo.KHACHHANG "
                + " SET HoTen = ?, NgaySinh = ?,SoDT = ?,CMDN = ?,Email = ?,DiaChi = ? "
                + "  WHERE MaKH= ? ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(7,kh.getMakh());
            pstmt.setString (1,kh.getHoten());
            pstmt.setString(2,kh.getNgaysinh());
            pstmt.setString(3,kh.getSdt());
            pstmt.setString(4,kh.getCmdn());
            pstmt.setString (5,kh.getEmail());
            pstmt.setString(6,kh.getDiachi());
            
            return pstmt.executeUpdate()>0;
        }
           
    }    
    public  boolean  delete (Integer MaKH) throws Exception
    {
        String sql = "delete from KHACHHANG where MaKH = ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,MaKH);
           
          return pstmt.executeUpdate()>0;
        }
    }
    public  KhachHang  findDung( Integer MaKH) throws Exception
    {
        String sql = "select * from KHACHHANG "
                + " where MaKH = ? ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,MaKH);
            try(ResultSet rs = pstmt.executeQuery(); )
            { 
               
                if(rs.next())
                {
                   KhachHang kh = TimKiem(rs);
                    return  kh;
                }
            }
        return null;
        }
    }
    public  ArrayList<KhachHang>  findTenKH( String HoTen) throws Exception
    {
        ArrayList<KhachHang> list=new ArrayList<>();
        String sql = "select * from KHACHHANG "
                + " where HoTen like ? ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setString(1,"%"+HoTen+"%");
            ResultSet rs = pstmt.executeQuery(); 
            while(rs.next()){
                   KhachHang kh=new KhachHang();
        kh.setMakh(rs.getInt("MaKH"));
        kh.setHoten(rs.getString("HoTen"));
        kh.setNgaysinh(rs.getString("NgaySinh"));
        kh.setSdt(rs.getString("SoDT"));
        kh.setCmdn(rs.getString("CMDN"));
        kh.setEmail(rs.getString("Email"));
        kh.setDiachi(rs.getString("DiaChi"));
        
        list.add(kh);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        return list;
        }
    
    public  List<KhachHang>findAll() throws Exception{
         String sql = "select *  from KHACHHANG ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
            { 
            try(ResultSet rs = pstmt.executeQuery();)
            { 
                List<KhachHang> list = new ArrayList<KhachHang>();
                while(rs.next())
                {
                    KhachHang sv = TimKiem(rs);
                    list.add(sv);
                }
                return list;
            }
        }
        
    }

    private KhachHang TimKiem(final ResultSet rs) throws SQLException {
        KhachHang kh = new KhachHang();
        kh.setMakh(rs.getInt("MaKH"));
        kh.setHoten(rs.getString("HoTen"));
        kh.setNgaysinh(rs.getString("NgaySinh"));
        kh.setSdt(rs.getString("SoDT"));
        kh.setCmdn(rs.getString("CMDN"));
        kh.setEmail(rs.getString("Email"));
        kh.setDiachi(rs.getString("DiaChi"));
        
        return kh;
    }
}
