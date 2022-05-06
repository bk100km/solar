package product;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import dbconn.DBConnection;

public class ProductDAO {
	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	String sql;
	
	public ProductDAO(){
		con = DBConnection.getConnection();
	}
	
	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();

		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}

	public void userSelectAll(DefaultTableModel t_model) {
		try {
			ps = con.prepareStatement("select pcode, pcate, pname, ptype, to_char(pprice,'9,999,999')|| '원', pas from product order by pcode");
			rs = ps.executeQuery();
			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < t_model.getRowCount();) {
				t_model.removeRow(0);
			}
			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) };
				t_model.addRow(data);// DefaultTableModel에 레코드 추가
			}
		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}

	}

	public void getUserSearch(DefaultTableModel dt, String fieldName, String word) {
	      String columnName = null;
	      if (fieldName.trim() == "제품 분류") { columnName = "pcate";}
	      else if (fieldName.trim() == "제품명") { columnName = "pname";}
	      else if (fieldName.trim() == "제품 유형") { columnName = "ptype";}
	      else if (fieldName.trim() == "월 대여료") { columnName = "pprice";}
	      sql = "select pcode, pcate, pname, ptype, to_char(pprice,'9,999,999')|| '원', pas from product WHERE "
	         + columnName + " LIKE '%" + word.trim() + "%'";
		try {
			st = con.createStatement();
			rs= st.executeQuery(sql);
			
			//DefaultTableModel에 있는 기본 데이터 지우기
			for(int i=0;i<dt.getRowCount();) {
				dt.removeRow(0);
			}
			while(rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
				dt.addRow(data);
			}
		}catch(SQLException e) {
			System.out.println(e + "=>getUserSearch fail");
		}finally {dbClose();}
	}
	
	 public int userListInsert(ClientProductDialog user) {
	        int result = 0;
	        try {
	        	sql = "insert into rental values (rcode_seq.nextval,?,?,sysdate,?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, user.tf_id.getText());
	            ps.setString(2, user.pcode.getText());
	            ps.setString(3, user.rstart.getText());
	            ps.setInt(4, Integer.parseInt(user.rday.getText()));
	 
	            result = ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e + "=> userListInsert fail");
	        } finally {
	            dbClose();
	        }
	        return result;
	    }
	 public int userListInsert(AdminProductDialog user) {
			int result = 0;
			try {
				sql = "insert into product values(?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, user.pcode.getText());
				ps.setString(2, user.pcate.getText());
				ps.setString(3, user.pname.getText());
				ps.setString(4, user.ptype.getText());
				ps.setInt(5, Integer.parseInt(user.pprice.getText()));
				ps.setString(6, user.pas.getText()+"개월");

				result = ps.executeUpdate();

			} catch (SQLException e) {
				System.out.println(e + "=> userListInsert fail");
			} finally {
				dbClose();
			}
			return result;
		}

		public void userSelectAllByAdmin(DefaultTableModel dm) {
			try {
				sql = "select pcode, pcate, pname, ptype, to_char(pprice,'9,999,999')|| '원', pas from product order by pcode";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				for (int i = 0; i < dm.getRowCount();) {
					dm.removeRow(0);
				}
				while (rs.next()) {
					Object data[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
					dm.addRow(data);
				}
			} catch (SQLException e) {
				System.out.println(e + "=> userSelectAll fail");
			} finally {
				dbClose();
			}

		}

		public int userDelete(String pcode) {
			int result = 0;
			try {
				sql = "delete from product where pcode =?";
				ps = con.prepareStatement(sql);
				ps.setString(1, pcode.trim());
				result = ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e + "=> userDelete fail");

			} finally {
				dbClose();
			}
			return result;
		}

		public int userUpdate(AdminProductDialog user) {
			int result = 0;
			sql = "update product set pcate=?,pname=?,ptype=?,pprice=?,pas=? " + " where pcode =?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, user.pcate.getText());
				ps.setString(2, user.pname.getText());
				ps.setString(3, user.ptype.getText());
				ps.setString(4, user.pprice.getText());
				ps.setString(5, user.pas.getText()+"개월");
				ps.setString(6, user.pcode.getText());

				result = ps.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println(e + "=> userUpdate fail");
			} finally {
				dbClose();
			}
			return result;
		}

		public void getUserSearchByAdmin(DefaultTableModel dt, String fieldName,
				String word) {
			String columnName = null;
			if (fieldName.trim() == "제품 코드") { columnName = "pcode";}
	        else if (fieldName.trim() == "제품 분류") { columnName = "pcate";}
	        else if (fieldName.trim() == "제품명") { columnName = "pname";}
	        else if (fieldName.trim() == "제품 유형") { columnName = "ptype";}
			sql = "select pcode, pcate, pname, ptype, to_char(pprice,'9,999,999')|| '원', pas from product where " + columnName + " like '%"+ word.trim() +"%'";
			try {
				st = con.createStatement();
				rs= st.executeQuery(sql);
				
				for(int i=0;i<dt.getRowCount();) {
					dt.removeRow(0);
				}
				while(rs.next()) {
					Object data[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
					dt.addRow(data);
				}
			}catch(SQLException e) {
				System.out.println(e + "=>getUserSearch fail");
			}finally {dbClose();}
		}

	}
