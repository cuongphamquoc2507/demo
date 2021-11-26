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
import Model.Nha;

/**
 *
 * @author This PC
 */
public class NhaDao {
    public boolean insert(Nha g) throws Exception{
        String sql = "INSERT INTO dbo.NHA(LoaiNha,DonGia,DienTich,DiaChi,TrangThai,Mota)VALUES(?,?,?,?,?,?)";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
//            pstmt.setString(1,g.getMaNha());
            pstmt.setString (1,g.getLoaiNha());
            pstmt.setFloat(2,g.getDonGia());
            pstmt.setFloat(3,g.getDienTich());
            pstmt.setString(4, g.getDiaChi());
            pstmt.setString(5,g.getTrangThai());
             if(g.getMota()!= null)
            {
                Blob hinh = new SerialBlob ( g.getMota());
                pstmt.setBlob(6, hinh);
            }
            else{
                Blob hinh = null;
                pstmt.setBlob(6, hinh);
            }
            return pstmt.executeUpdate()>0;
            
        }
           
    }   
    public boolean update(Nha g) throws Exception
    {
        String sql = "UPDATE dbo.NHA SET LoaiNha = ?, DonGia = ?,DienTich = ?,DiaChi = ?,TrangThai = ?,Mota = ? WHERE MaNha = ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(7,g.getMaNha());
            pstmt.setString (1,g.getLoaiNha());
            pstmt.setFloat(2,g.getDonGia());
            pstmt.setFloat(3,g.getDienTich());
            pstmt.setString(4, g.getDiaChi());
            pstmt.setString(5,g.getTrangThai());
             if(g.getMota()!= null)
            {
                Blob hinh = new SerialBlob ( g.getMota());
                pstmt.setBlob(6, hinh);
            }
            else{
                Blob hinh = null;
                pstmt.setBlob(6, hinh);
            }
            return pstmt.executeUpdate()>0;
        }
           
    }    
    public  boolean  delete ( String ma) throws Exception
    {
        String sql = "delete from NHA where MaNha= ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setString(1,ma);
           
          return pstmt.executeUpdate()>0;
        }
    }
    public  Nha  findNha( Integer ma) throws Exception
    {
        String sql = "select *  from NHA where MaNha= ?";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
        {
            pstmt.setInt(1,ma);
            try(ResultSet rs = pstmt.executeQuery(); )
            { 
               
                if(rs.next())
                {
                    Nha d = TimKiem(rs);
                    return  d;
                }
            }
        return null;
        }
    }
    public  List<Nha>findAll() throws Exception{
         String sql = "select *  from NHA";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
            { 
            try(ResultSet rs = pstmt.executeQuery();)
            { 
                List<Nha> list = new ArrayList<>();
                while(rs.next())
                {
                        Nha d = TimKiem(rs);
                         list.add(d);
                }
                return list;
            }
        }
        
    }
public  ArrayList<Nha>findDiaChi(String Diachi) throws Exception{
    ArrayList<Nha> list =new ArrayList<>();
         String sql = "select *  from NHA where DiaChi like ? ";
        try( Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);)  
            { 
                pstmt.setString(1,"%"+Diachi+"%");
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                 Nha d = TimKiem(rs);
                         list.add(d);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
        
    }
    private Nha TimKiem(final ResultSet rs) throws SQLException {
        Nha n = new Nha();
        n.setMaNha(rs.getInt("MaNha"));
        n.setLoaiNha(rs.getString("LoaiNha"));
        
        n.setDienTich(rs.getFloat("DienTich"));
        n.setDiaChi(rs.getString("DiaChi"));
        n.setTrangThai(rs.getString("TrangThai"));
        Blob blob =rs.getBlob("Mota");
        if(blob!=null)
            n.setMota(blob.getBytes(1, (int) blob.length()));
        return n;
       
    }

    
}
