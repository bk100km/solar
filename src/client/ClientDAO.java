package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dbconn.DBConnection;
import index.IndexFrame;
import index.JoinFrame;

public class ClientDAO {
	Connection con;
	Statement st;	PreparedStatement ps; 	ResultSet rs;
	String sql;
	
	public ClientDAO() {
		con = DBConnection.getConnection();
	}
	
	public void dbClose() {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (ps != null) ps.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}
	
	public int join(JoinFrame joinF) {
		
		int result = 0;
		
		try {

			sql = "insert into client (id, pw, cname, birth, addr, tel, mail) "
					+ " values(?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, joinF.tf_id.getText());
			ps.setString(2, joinF.tf_pw1.getText());
			ps.setString(3, joinF.tf_name.getText());
			ps.setString(4, joinF.tf_birth.getText());
			ps.setString(5, joinF.tf_addr.getText());
			ps.setString(6, joinF.tf_tel.getText());
			ps.setString(7, joinF.tf_mail.getText());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e + "=> join fail");
		} finally {	dbClose(); }
		return result;
	}
	
	public boolean checkID(String id) {
		sql = "select id from client where id = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {return false;}
		
		} catch (SQLException e) {
			System.out.println(e + "=> checkID fail");
		}
		return true;
	}
	
	public int loginCheck(IndexFrame indexF) {
		int checkWho = 3;
		String id = indexF.tf_id.getText().trim();
		String pass = indexF.tf_pw.getText().trim();
	      
	    sql = "select id, pw from client where id = ?";
	    try {
	    	ps = con.prepareStatement(sql);
	    	ps.setString(1, id);
	    	rs = ps.executeQuery();
	    	if (rs.next()) {
	    		if(pass.equals(rs.getString(2))) {
	    			JOptionPane.showMessageDialog(indexF, "환영합니다.");
	    			indexF.setVisible(false);
	    				if(rs.getString(1).equals("admin")) { checkWho = 0;}
	    				else { checkWho = 1; }
	    		} else {
	    			JOptionPane.showMessageDialog(indexF, "비밀번호가 일치하지 않습니다");
	    			indexF.tf_pw.setText("");
	    			indexF.tf_pw.requestFocus();
	    			checkWho = 2;
	    	  	}
	    	} else {
	    		JOptionPane.showMessageDialog(indexF, "존재하지 않는 아이디입니다.");	
	    	}
	    } catch (Exception e) {
	    	System.out.println(e + "=> loginCheck fail");
	    } finally { dbClose(); }	
	    return checkWho;
	}
	
	public  void selectAllByAdmin(DefaultTableModel dTable) {
		try {
			sql = "select * from client order by jdate";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			for (int i = 0; i < dTable.getRowCount();) {
				dTable.removeRow(0);
			}
			
			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(3), rs.getString(4).substring(0, 4) + "년 " 
						+ rs.getString(4).substring(4, 6) + "월 " + rs.getString(4).substring(6, 8) + "일",
						rs.getString(5), "010 - " + rs.getString(6).substring(0, 4) + " - " 
						+ rs.getString(6).substring(4, 8), rs.getString(7), rs.getString(8) };
				dTable.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "=> selectAllByAdmin fail");
		} finally {	dbClose(); }
	}
	
	public void searchByAdmin(DefaultTableModel dTable, String comboName, String word) {
		String columnName = null;
		if (comboName.trim() == "아이디") { columnName = "id";}
		else if (comboName.trim() == "성명") { columnName = "cname";}
		else if (comboName.trim() == "주소") { columnName = "addr";}
		else if (comboName.trim() == "연락처") { columnName = "tel";}
		else if (comboName.trim() == "이메일") { columnName = "mail";}
	
		try {
			sql = "SELECT * FROM client WHERE "
					+ columnName + " LIKE '%" + word.trim() + "%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			for (int i = 0; i < dTable.getRowCount();) {
				dTable.removeRow(0);
			}
			
			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(3), rs.getString(4).substring(0, 4) + "년 " 
						+ rs.getString(4).substring(4, 6) + "월 " + rs.getString(4).substring(6, 8) + "일",
						rs.getString(5), "010 - " + rs.getString(6).substring(0, 4) + " - " 
						+ rs.getString(6).substring(4, 8), rs.getString(7), rs.getString(8) };
				dTable.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "=> searchByAdmin fail");
		} finally { dbClose(); }
	}
	
	public void clientShow(ClientUserFrame cuf, String id) {
		try {
			sql = "select * from client where id= ?";
			ps = con.prepareStatement(sql);
	    	ps.setString(1, id);
	    	rs = ps.executeQuery();

			if (rs.next()) {
				cuf.infoId.setText(rs.getString(1));
				cuf.infoPw.setText(rs.getString(2));
				cuf.infoCName.setText(rs.getString(3));
				cuf.infoBirth.setText(rs.getString(4).substring(0, 4) + "년 " 
						+ rs.getString(4).substring(4, 6) + "월 " + rs.getString(4).substring(6, 8) + "일");
				cuf.infoAddr.setText(rs.getString(5));
				cuf.infoTel.setText("010 - " + rs.getString(6).substring(0, 4) + " - " 
						+ rs.getString(6).substring(4, 8));
				cuf.infoMail.setText(rs.getString(7));
				cuf.infoJDate.setText(rs.getString(8));
			}
		} catch (SQLException e) {
			System.out.println(e + "=> clientShow fail");
		} finally {
			dbClose();
		}
	}
	
	public void updateShowByAdmin(AdminUserFrame auf, String id) {
		try {
			sql = "select * from client where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				return;
		    }
			AdminUserDialog aud = new AdminUserDialog(auf);
			aud.tf_id.setText(rs.getString(1));				
			aud.tf_id.setEditable(false);				aud.idCheck.setEnabled(false);
			aud.tf_pw1.setText(rs.getString(2));
			aud.tf_pw2.setText(rs.getString(2));
			aud.tf_name.setText(rs.getString(3));		aud.tf_name.setEditable(false);
			aud.tf_birth.setText(rs.getString(4));		aud.tf_birth.setEditable(false);
			aud.tf_addr.setText(rs.getString(5));
			aud.tf_tel.setText(rs.getString(6));
			aud.tf_mail.setText(rs.getString(7));
			aud.setVisible(true);
		} catch (SQLException e) {
			System.out.println(e + "=> updateShowByAdmin fail");
		} finally { dbClose(); }
	}
	
	public void updateShowByClient(ClientUserFrame cuf, String id) {
		try {
			sql = "select * from client where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				return;
		    }
			ClientUserDialog cud = new ClientUserDialog(cuf);
			cud.tf_id.setText(rs.getString(1));				
			cud.tf_id.setEditable(false);				cud.idCheck.setEnabled(false);
			cud.tf_pw1.setText(rs.getString(2));
			cud.tf_pw2.setText(rs.getString(2));
			cud.tf_name.setText(rs.getString(3));		cud.tf_name.setEditable(false);
			cud.tf_birth.setText(rs.getString(4));		cud.tf_birth.setEditable(false);
			cud.tf_addr.setText(rs.getString(5));
			cud.tf_tel.setText(rs.getString(6));
			cud.tf_mail.setText(rs.getString(7));
			cud.setVisible(true);
		} catch (SQLException e) {
			System.out.println(e + "=> updateShowByClient fail");
		} finally { dbClose(); }
	}
	
	public int updateByAdmin(AdminUserDialog aud) {
		int result = 0;		
		if (!aud.tf_pw1.getText().trim().equals(aud.tf_pw2.getText().trim())) {
			JOptionPane.showMessageDialog(aud, "비밀번호를 확인해주세요.");
			aud.tf_pw1.setText("");		aud.tf_pw2.setText("");
			return 0;
		}
		try {
			sql = "update client set pw = ?, addr = ?, tel = ?, mail = ? where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, aud.tf_pw1.getText());
			ps.setString(2, aud.tf_addr.getText());
			ps.setString(3, aud.tf_tel.getText());
			ps.setString(4, aud.tf_mail.getText());
			ps.setString(5, aud.tf_id.getText());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> updateByAdmin fail");
		} finally { dbClose(); }
		return result;
	}
	
	public int updateByClient(ClientUserDialog cud) {
		int result = 0;		
		if (!cud.tf_pw1.getText().trim().equals(cud.tf_pw2.getText().trim())) {
			JOptionPane.showMessageDialog(cud, "비밀번호를 확인해주세요.");
			cud.tf_pw1.setText("");		cud.tf_pw2.setText("");
			return 0;
		}
		try {
			sql = "update client set pw = ?, addr = ?, tel = ?, mail = ? where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cud.tf_pw1.getText());
			ps.setString(2, cud.tf_addr.getText());
			ps.setString(3, cud.tf_tel.getText());
			ps.setString(4, cud.tf_mail.getText());
			ps.setString(5, cud.tf_id.getText());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> updateByClient fail");
		} finally { dbClose(); }
		return result;
	}
	
	public int deleteByAdmin(String id) {
		int result = 0;
		try {
			sql = "delete from client where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e + "=> deleteByAdmin fail");
		} finally { dbClose(); }
		return result;
	}
	
	public int deleteByClient(String id) {
		int result = 0;
		try {
			sql = "delete from client where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e + "=> deleteByAdmin fail");
		} finally { dbClose(); }
		return result;
	}
	
}
