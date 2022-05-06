package rental;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dbconn.DBConnection;

public class RentalDAO {
	Connection con;
	Statement st;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;

	public RentalDAO() {
		con = DBConnection.getConnection();
	}

	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}

	public void SelectAll(DefaultTableModel t_model) {
		try {
			pstmt = con.prepareStatement("select r.rcode, c.id, c.cname, "
					+ "p.pcode, p.pname, TO_CHAR(r.rreserv, 'YY-MM-DD'), TO_CHAR(r.rstart, 'YYYY-MM-DD'), TO_CHAR(r.rstart + (r.rday*30), 'YYYY-MM-DD'), "
					+ "r.rday || '개월', case when rstart > sysdate then TRUNC(R.Rstart + R.rday * 30 - r.rstart, 0) || '일' when rstart <= sysdate then TRUNC(R.Rstart + R.rday * 30 - sysdate, 0) || '일' end, TO_CHAR(p.pprice, '999,999,999') || '원', TO_CHAR(r.rday * p.pprice, '999,999,999') || '원', c.tel, c.addr "
					+ "from rental r join client c on r.ID = c.ID "
					+ "join product p on r.PCODE = p.PCODE order by r.rcode");
			rs = pstmt.executeQuery();

			for (int i = 0; i < t_model.getRowCount();) {
				t_model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12), "010 - " + rs.getString(13).substring(0, 4) + " - " 
								+ rs.getString(13).substring(4, 8), rs.getString(14) };
				t_model.addRow(data);

			}
		} catch (SQLException e) {
			System.out.println(e + "=> SelectAll 실패");

		} finally {
			dbClose();
		}
	}

	public void keySearch(DefaultTableModel dt, String fieldName, String word) {
		if (fieldName.trim() == "대여번호") {
			fieldName = "r.rcode";
		} else if (fieldName.trim() == "아이디") {
			fieldName = "r.id";
		} else if (fieldName.trim() == "제품 코드") {
			fieldName = "p.pcode";
		}
		String sql = "select r.rcode, c.id, c.cname, p.pcode,"
				+ " p.pname, TO_CHAR(r.rreserv, 'YY-MM-DD'), TO_CHAR(r.rstart, 'YYYY-MM-DD'), TO_CHAR(r.rstart + (r.rday*30), 'YYYY-MM-DD'),"
				+ "r.rday || '개월', case when rstart > sysdate then TRUNC(R.Rstart + R.rday * 30 - r.rstart, 0) || '일' when rstart <= sysdate then TRUNC(R.Rstart + R.rday * 30 - sysdate, 0) || '일' end, "
				+ "TO_CHAR(p.pprice, '999,999,999') || '원', TO_CHAR(r.rday * p.pprice, '999,999,999') || '원', c.tel, c.addr "
				+ "from rental r join client c on r.ID = c.ID " + "join product p on r.PCODE = p.PCODE " + "where "
				+ fieldName.trim() + " LIKE '%" + word.trim() + "%' order by r.rcode";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);

			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12), "010 - " + rs.getString(13).substring(0, 4) + " - " 
								+ rs.getString(13).substring(4, 8), rs.getString(14) };
				dt.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "=> keySearch 실패");
		} finally {
			dbClose();
		}
	}

	public int rentalUpdateByAdmin(AdminRentalDialog RentalDTO) {
		int result = 0;
		String sql = "update rental set pcode=?, rstart=?, rday=? " + "where rcode=?";
		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, RentalDTO.Pcode.getText());
			pstmt.setString(2, RentalDTO.Rstart.getText());
			pstmt.setString(3, RentalDTO.Rday.getText());
			pstmt.setString(4, RentalDTO.Rcode.getText().trim());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e + "=> Update 실패");
		} finally {
			dbClose();
		}
		return result;
	}

	public void rentalSearch(DefaultTableModel dt, String fieldName, String word) {
		if (fieldName.equals("제품 분류")) {
			fieldName = "Pcate";
		} else {
			fieldName = "Pname";
		}
		String sql = "select R.RCODE, P.PCODE, P.PCATE, P.PNAME, P.PPRICE, "
				+ "R.RDAY * 30, P.PPRICE * R.RDAY, TO_CHAR(R.RSTART, 'YYYY-MM-DD'), "
				+ "To_CHAR(R.Rstart + R.rday * 30, 'YYYY-MM-DD'), TRUNC(R.Rstart + R.rday * 30 - sysdate, 0), RSTATE "
				+ "from rental R join client C on c.ID = R.ID join " + "product p on P.PCODE = R.PCODE WHERE "
				+ fieldName.trim() + " LIKE '%" + word.trim() + "%' " + "order by R.RCODE";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);

			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11) };
				dt.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "=> getRentalSearch fail");
		} finally {
			dbClose();
		}
	}

	public int rentalDelete(String rcode) {
		int result = 0;
		try {
			pstmt = con.prepareStatement("delete RENTAL where rcode = ? ");
			pstmt.setString(1, rcode.trim());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> rentalDelete fail");
		} finally {
			dbClose();
		}
		return result;
	}
	
	public void clientRentalSearch(DefaultTableModel dt, String fieldName, String word, String id) {
		if (fieldName.equals("제품분류")) {
			fieldName = "Pcate";
		} else {
			fieldName = "Pname";
		}
		try {
			pstmt = con.prepareStatement("select R.RCODE, P.PCODE, P.PCATE, P.PNAME, to_char(P.PPRICE, '9,999,999') || '원', "
					+ "R.RDAY || '개월', to_char(P.PPRICE * R.RDAY, '99,999,999') || '원', TO_CHAR(R.RSTART, 'YYYY-MM-DD'), "
					+ "To_CHAR(R.Rstart + R.rday * 30, 'YYYY-MM-DD'), TRUNC(R.Rstart + R.rday * 30 - sysdate, 0) || '일' "
					+ "from rental R join client C on c.ID = R.ID join " + "product p on P.PCODE = R.PCODE WHERE "
					+ fieldName.trim() + " LIKE '%" + word.trim() + "%' and R.ID = ? " + "order by R.RCODE");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)};
				dt.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "=> getRentalSearch fail");
		} finally {
			dbClose();
		}
	}
	
	public void clientRentalSelectAll(DefaultTableModel t_model, String id) {
		try {
			pstmt = con.prepareStatement("select R.RCODE, P.PCODE, P.PCATE, P.PNAME, TO_CHAR(P.PPRICE, '9,999,999') || '원', "
					+ "R.RDAY || '개월', TO_CHAR(P.PPRICE * R.RDAY, '9,999,999') || '원', TO_CHAR(R.RSTART, 'YYYY-MM-DD'), "
					+ "TO_CHAR(R.Rstart + R.rday * 30, 'YYYY-MM-DD'), CASE WHEN RSTART > SYSDATE THEN TRUNC(Rstart + rday * 30 - Rstart, 0) || '일' "
					+ "WHEN RSTART <= SYSDATE THEN TRUNC(Rstart + rday * 30 - sysdate, 0) || '일' end "
					+ "from rental R join client C on c.ID = R.ID join "
					+ "product p on P.PCODE = R.PCODE Where R.ID = ? order by R.RCODE");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			for (int i = 0; i < t_model.getRowCount();) {
				t_model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)};
				t_model.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "=> rentalSelectAll fail");
		} finally {
			dbClose();
		}
	}

	public int clientRentalUpdate(ClientRentalFrame rental) {

		int result = 0;
		String sql = "UPDATE RENTAL SET RSTART=?, RDAY=? " + " WHERE RCODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rental.Rstart.getText());
			pstmt.setString(2, rental.Rday.getText().trim());
			pstmt.setString(3, rental.Rcode.getText().trim());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e + "=> rentalUpdate fail");
		} finally {
			dbClose();
		}
		return result;
	}

	public int clientRentalDelete(String rcode) {
		int result = 0;
		try {
			pstmt = con.prepareStatement("delete RENTAL where rcode = ?");
			pstmt.setString(1, rcode.trim());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> rentalDelete fail");
		} finally {
			dbClose();
		}
		return result;
	}

	public void updateShowByAdmin(AdminRentalFrame auf, String rcode) {
		try {
			sql = "select r.rcode, c.id, c.cname, p.pcode, p.pname, "
				+ "TO_CHAR(r.rstart, 'YYYY-MM-DD'), TO_CHAR(r.rstart + (r.rday*30), 'YYYY-MM-DD'),"
				+ "r.rday || '개월', case when rstart > sysdate then TRUNC(R.Rstart + R.rday * 30 - r.rstart, 0) || '일' when rstart <= sysdate then TRUNC(R.Rstart + R.rday * 30 - sysdate, 0) || '일' end, "
				+ "TO_CHAR(p.pprice, '999,999,999') || '원', TO_CHAR(r.rday * p.pprice, '999,999,999') || '원', c.tel, c.addr "
				+ "from rental r join client c on r.ID = c.ID join product p on r.PCODE = p.PCODE where rcode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rcode);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return;
		    }
			AdminRentalDialog ard = new AdminRentalDialog(auf);
			ard.Rcode.setText(rs.getString(1));				ard.Rcode.setEnabled(false);
			ard.ID.setText(rs.getString(2));				ard.ID.setEnabled(false);
			ard.Cname.setText(rs.getString(3));				ard.Cname.setEnabled(false);
			ard.Pcode.setText(rs.getString(4));
			ard.Pname.setText(rs.getString(5));				ard.Pname.setEnabled(false);	
			ard.Rstart.setText(rs.getString(6));			
			ard.Rend.setText(rs.getString(7));				ard.Rend.setEnabled(false);
			ard.Rday.setText(rs.getString(8).replaceAll("[^0-9]",""));
			ard.Rleft.setText(rs.getString(9));				ard.Rleft.setEnabled(false);
			ard.Pprice.setText(rs.getString(10));			ard.Pprice.setEnabled(false);
			ard.Rtotal.setText(rs.getString(11));			ard.Rtotal.setEnabled(false);
			ard.addr.setText(rs.getString(13));				ard.addr.setEnabled(false);
			ard.Pprice.setHorizontalAlignment(JTextField.RIGHT);
			ard.Rtotal.setHorizontalAlignment(JTextField.RIGHT);
			ard.setVisible(true);
			ard.setLocation(350,150);
		} catch (SQLException e) {
			System.out.println(e + "=> updateShowByAdmin fail");
		} finally { dbClose(); }
	}
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
}
