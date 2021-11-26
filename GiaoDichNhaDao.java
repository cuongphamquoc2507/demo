/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Data.DatabaseHelper;
import Model.GiaoDichNha;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author This PC
 */
public class GiaoDichNhaDao {
   
    public boolean insert(GiaoDichNha g ) throws Exception{
        String sql = "INSERT INTO dbo.GDNHA ( MaND,MaNha ,MaKH ,NgayGD , ThanhToan) values(?,?,?,?,?)";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
//            pstmt.setString(1,g.getMaGDN());
            pstmt.setString (1,g.getMaND());
            pstmt.setString(2,g.getMaNha());
            pstmt.setString(3,g.getMaKH());
            pstmt.setString(4,g.getNgayGD());
            pstmt.setFloat(5,g.getThanhToan());
            return pstmt.executeUpdate()>0;
        }
           
    }   
    public boolean update(GiaoDichNha g) throws Exception
    {
        String sql = "UPDATE dbo.GDNHA"
                + " SET MaND=?  ,MaNha=? ,MaKH=? ,NgayGD =?, ThanhToan=?"
                + " WHERE MaGDN = ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
          
            pstmt.setInt(6,g.getMaGDN());
            pstmt.setString (1,g.getMaND());
            pstmt.setString(2,g.getMaNha());
            pstmt.setString(3,g.getMaKH());
            pstmt.setString(4,g.getNgayGD());
            pstmt.setFloat(5,g.getThanhToan());
            return pstmt.executeUpdate()>0;
        }
           
    }    
    public  boolean  delete ( Integer ma) throws Exception
    {
        String sql = "delete from GDNHA  where MaGDN= ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,ma);
           
          return pstmt.executeUpdate()>0;
        }
    }
    public  GiaoDichNha  findNha( Integer ma) throws Exception
    {
        String sql = "select *  from GDNHA  where MaGDN= ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,ma);
            try(ResultSet rs = pstmt.executeQuery(); )
            { 
               
                if(rs.next())
                {
                    GiaoDichNha d = TimKiem(rs);
                    return  d ;
                }
            }
        return null;
        }
    }
    public  List<GiaoDichNha>findAll() throws Exception{
         String sql = "select *  from GDNHA";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
            { 
            try(ResultSet rs = pstmt.executeQuery();)
            { 
                List<GiaoDichNha> list = new ArrayList<>();
                while(rs.next())
                {
                         GiaoDichNha d = TimKiem(rs);
                         list.add(d);
                }
                return list;
            }
        }
        
    }
    
 public  ArrayList<GiaoDichNha> findbyDate(String Date)throws Exception{
        ArrayList<GiaoDichNha> list =new ArrayList<>();
         String sql = "SELECT * from GDNHA  Where NgayGD like ?  ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setString(1,"%"+Date+"%");
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                GiaoDichNha g=new GiaoDichNha();
                g.setMaGDN(rs.getInt("MaGDN"));
                g.setMaND(rs.getString("MaND"));
                g.setMaNha(rs.getString("MaNha"));
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
    private GiaoDichNha TimKiem(final ResultSet rs) throws SQLException {
        GiaoDichNha sv = new GiaoDichNha();
        sv.setMaGDN(rs.getInt("MaGDN"));
        sv.setMaND(rs.getString("MaND"));
        sv.setMaNha(rs.getString("MaNha"));
        sv.setMaKH(rs.getString("MaKH"));
        sv.setNgayGD(rs.getString("NgayGD"));
        sv.setThanhToan(Float.parseFloat(rs.getString("ThanhToan")));
        return sv;
    }
}
