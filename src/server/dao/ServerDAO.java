package server.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.vo.SelectedRowVO;
import server.vo.DetailBCVO;
import server.vo.InsertVO;
import server.vo.TableDataVO;

public class ServerDAO {
	
	private static ServerDAO s_dao;
	
	private ServerDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ServerDAO getInstance() { 
		if (s_dao == null) {
			s_dao = new ServerDAO();
		}
		return s_dao;
	}
	
	public Connection getConn() throws SQLException {
		Connection con = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "tiger";
		
		con = DriverManager.getConnection(url, user, password);
		
		return con;
	}
	
	public void insertBC(InsertVO ivo) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConn();
			
			String insertBC = "insert into business_card(memo, file_name) values(?,?)";
			pstmt = con.prepareStatement(insertBC);
			pstmt.setString(1, ivo.getMemo());
			pstmt.setString(2, ivo.getFileName());
			
			pstmt.executeUpdate();
			
			System.out.println("데이터 추가");
			
		} finally {
			if (pstmt != null) { pstmt.close(); }
			if (con != null) { con.close(); }
		}
	}
	
	public List<TableDataVO> selectAll() throws SQLException { 
		List<TableDataVO> list = new ArrayList<TableDataVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = getConn();
			String selectQuery = "SELECT input_date, memo FROM business_card";
			pstmt = con.prepareStatement(selectQuery);
			
			rs = pstmt.executeQuery();
			
			TableDataVO tdvo = null;
			while(rs.next()) {
				tdvo = new TableDataVO(rs.getString(1), rs.getString(2));
				list.add(tdvo);
			}
			
		} finally {
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
			if (con != null) { con.close(); }
		}
		
		return list;
	}
	
	public DetailBCVO selectDetail(SelectedRowVO srvo) throws SQLException {
		DetailBCVO dbcvo = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println(srvo);
		try {
			
			con = getConn();
			String selectDetail = "SELECT file_name, memo FROM business_card WHERE memo=? AND input_date=?";
			pstmt = con.prepareStatement(selectDetail);
			pstmt.setString(1, srvo.getMemo());
			pstmt.setDate(2, Date.valueOf(srvo.getInputDate()));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbcvo = new DetailBCVO(rs.getString("file_name"),rs.getString("memo"));
			}
			
		} finally {
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
			if (con != null) { con.close(); }
		}
		
		return dbcvo;
	}
	
	/*public static void main(String[] args) {
		try {
			ServerDAO.s_dao.getInstance().selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

}
