/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Data.DatabaseHelper;
import Model.Dat;
import Model.GiaoDichDat;
import Model.KhachHang;
import Model.NguoiDung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 54321
 */
public class GiaoDichDatDao {
     public boolean insert(GiaoDichDat g ) throws Exception{
        String sql = "INSERT INTO dbo.GDDAT ( MaND  ,MaDat ,MaKH ,NgayGD ,ThanhToan ) values(?,?,?,?,?)";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            //pstmt.setInt(1,g.getMaGDD());
            pstmt.setString (1,g.getMaND());
            pstmt.setString(2,g.getMaDat());
            pstmt.setString(3,g.getMaKH());
            pstmt.setString(4,g.getNgayGD());
            pstmt.setFloat(5,g.getThanhToan());
            return pstmt.executeUpdate()>0;
        }
           
    }   
    public boolean update(GiaoDichDat g) throws Exception
    {
        String sql = "UPDATE dbo.GDDAT"
                + " SET MaND=?  ,MaDat=? ,MaKH=? ,NgayGD =?, ThanhToan=?"
                + " WHERE MaGDD = ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
          
            pstmt.setInt(6,g.getMaGDD());
            pstmt.setString (1,g.getMaND());
            pstmt.setString(2,g.getMaDat());
            pstmt.setString(3,g.getMaKH());
            pstmt.setString(4,g.getNgayGD());
              pstmt.setFloat(5,g.getThanhToan());
            return pstmt.executeUpdate()>0;
        }
           
    }    
    public  boolean  delete (  Integer ma) throws Exception
    {
        String sql = "delete from GDDAT  where MaGDD= ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,ma);
           
          return pstmt.executeUpdate()>0;
        }
    }
    public  GiaoDichDat  findDat( Integer ma) throws Exception
    {
        String sql = "select *  from GDDAT  where MaGDD= ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,ma);
            try(ResultSet rs = pstmt.executeQuery(); )
            { 
               
                if(rs.next())
                {
                    GiaoDichDat d = TimKiem(rs);
                    return  d ;
                }
            }
        return null;
        }
    }
    public  List<GiaoDichDat>findAll() throws Exception{
         String sql = "SELECT * from GDDAT    ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
            { 
            try(ResultSet rs = pstmt.executeQuery();)
            { 
                List<GiaoDichDat> list = new ArrayList<>();
                while(rs.next())
                {
                         GiaoDichDat d = TimKiem(rs);
                         list.add(d);
                }
                return list;
            }
        }
        
    }
    public  ArrayList<GiaoDichDat> findbyDate(String Date)throws Exception{
        ArrayList<GiaoDichDat> list =new ArrayList<>();
         String sql = "SELECT * from GDDAT  Where NgayGD like ?  ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setString(1,"%"+Date+"%");
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                GiaoDichDat g=new GiaoDichDat();
                g.setMaGDD(rs.getInt("MaGDD"));
                g.setMaND(rs.getString("MaND"));
                g.setMaDat(rs.getString("MaDat"));
                g.setMaKH(rs.getString("MaKH"));
                g.setNgayGD(rs.getString("NgayGD"));
                g.setThanhToan(Float.parseFloat(rs.getString("ThanhToan")));
                
                list.add(g);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
        
    }
    private GiaoDichDat TimKiem(final ResultSet rs) throws SQLException {
        GiaoDichDat sv = new GiaoDichDat();
        sv.setMaGDD(rs.getInt("MaGDD"));
        sv.setMaND(rs.getString("MaND"));
        sv.setMaDat(rs.getString("MaDat"));
        sv.setMaKH(rs.getString("MaKH"));
        sv.setNgayGD(rs.getString("NgayGD"));
        
        sv.setThanhToan(Float.parseFloat(rs.getString("ThanhToan")));
        
        return sv;
    }
    public  GiaoDichDat  findDung(  Integer MaGD) throws Exception
    {
        String sql = "select * from GDDAT where MaGDD= ? ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,MaGD);
            try(ResultSet rs = pstmt.executeQuery(); )
            { 
               
                if(rs.next())
                {
                   GiaoDichDat sv = TimKiem(rs);
                   sv.setMaGDD(rs.getInt("MaGDD"));
                   sv.setNgayGD(rs.getString("NgayGD"));
                   sv.setMaDat(rs.getString("MaDat"));     
                    sv.setThanhToan(Float.parseFloat(rs.getString("ThanhToan")));
                    return  sv;
                }
            }
        return null;
        }
    }
    
    
    
     public  List<GiaoDichDat> findTim(String name) throws SQLException, Exception
     {
         ResultSet rs = null;
         Statement  sttm =null;
         
         List<GiaoDichDat> d = new ArrayList<>();
         try
         {
               String sql = "select * from GDDAT where MaGDD = '"+name+"'";
               Connection con = DatabaseHelper.openConnection();
               sttm = con.createStatement();
               rs = sttm.executeQuery(sql);
               while ( rs.next())
               {
                   GiaoDichDat sv = new GiaoDichDat();
                   sv.setMaGDD(rs.getInt("MaGDD"));
                   sv.setNgayGD(rs.getString("NgayGD"));
                   sv.setMaDat(rs.getString("MaDat"));     
                   sv.setThanhToan(Float.parseFloat(rs.getString("ThanhToan")));
                   d.add(sv);
                   con.close();
               }
               
         }
         catch (Exception e)
         {
             System.out.println("Error:" + e.toString());
         }
         finally 
         {
             try
             {
                  rs.close(); sttm.close(); 
                  
             }
             catch(Exception e)
             {
             }
         }
         return  d;
     }
}

    


     