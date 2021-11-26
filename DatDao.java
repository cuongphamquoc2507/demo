/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Data.DatabaseHelper;
import Model.Dat;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author 54321
 */
public class DatDao {
     public boolean insert(Dat g) throws Exception{
        String sql = "INSERT INTO dbo.DAT (LoaiDat  ,DonGia  ,DienTich, TrangThai, Mota ) values(?,?,?,?,?)";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
          pstmt.setInt(1,g.getMaDat());
            pstmt.setString (1,g.getLoaiDat());
            pstmt.setFloat(2,g.getDonGia());
            pstmt.setFloat(3,g.getDienTich());
            pstmt.setString(4,g.getTrangThai());
             if(g.getMota()!= null)
            {
                Blob hinh = new SerialBlob ( g.getMota());
                pstmt.setBlob(5, hinh);
            }
            else{
                Blob hinh = null;
                pstmt.setBlob(5, hinh);
            }
            return pstmt.executeUpdate()>0;
            
        }
           
    }   
    public boolean update(Dat g) throws Exception
    {
        String sql = "UPDATE dbo.DAT "
                + " SET LoaiDat =? ,DonGia=?  ,DienTich=? , TrangThai=?, Mota=?"
                + " WHERE MaDat = ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(6,g.getMaDat());
            pstmt.setString (1,g.getLoaiDat());
          pstmt.setFloat(2,g.getDonGia());
            pstmt.setFloat(3,g.getDienTich());
            pstmt.setString(4,g.getTrangThai());
             if(g.getMota()!= null)
            {
                Blob hinh = new SerialBlob ( g.getMota());
                pstmt.setBlob(5, hinh);
            }
            else{
                Blob hinh = null;
                pstmt.setBlob(5, hinh);
            }
           
            return pstmt.executeUpdate()>0;
        }
           
    }    
    public  boolean  delete (Integer ma) throws Exception
    {
        String sql = "delete from DAT where MaDat= ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,ma);
           
          return pstmt.executeUpdate()>0;
        }
    }
    public  Dat  findDat1(Integer ma) throws Exception
    {
        String sql = "select *  from DAT where MaDat= ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,ma);
            try(ResultSet rs = pstmt.executeQuery(); )
            { 
               
                if(rs.next())
                {
                    Dat d = TimKiem(rs);
                    return  d;
                }
            }
        return null;
        }
    }
    public  List<Dat>findAll1() throws Exception{
         String sql = "select *  from DAT";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
            { 
            try(ResultSet rs = pstmt.executeQuery();)
            { 
                List<Dat> list = new ArrayList<>();
                while(rs.next())
                {
                        Dat d = TimKiem(rs);
                         list.add(d);
                }
                return list;
            }
        }
        
    }

    private Dat TimKiem(final ResultSet rs) throws SQLException {
        Dat sv = new Dat();
        sv.setMaDat(rs.getInt("MaDat"));
        sv.setLoaiDat(rs.getString("LoaiDat"));
        sv.setDienTich(rs.getFloat("DienTich"));
        sv.setTrangThai(rs.getString("TrangThai"));
        Blob blob =rs.getBlob("Mota");
        if(blob!=null)
            sv.setMota(blob.getBytes(1, (int) blob.length()));
        return sv;
       
    }
    
}
