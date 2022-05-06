package index;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.ClientDAO;



public class Main implements ActionListener {

	IndexFrame indexF;
	JoinFrame joinF;
	AdminFrame adminF;
	ClientFrame clientF;
	ClientDAO dao;
	
	String id;
	String pass;

	boolean isCheckedId = false;

	public Main() {
		indexF = new IndexFrame();
		joinF = new JoinFrame();
		
		dao = new ClientDAO();
		
		indexF.setVisible(true);

		indexF.btnLogin.addActionListener(this);
		indexF.btnJoin.addActionListener(this);
		joinF.idCheck.addActionListener(this);
		joinF.join.addActionListener(this);
		joinF.cancel.addActionListener(this);

		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == indexF.btnLogin) {
			String id = indexF.tf_id.getText().trim();
			String pass = indexF.tf_pw.getText().trim();
			if (id.length() == 0) {
				JOptionPane.showMessageDialog(indexF, "아이디 입력");
				indexF.tf_pw.setText(null);
				indexF.tf_id.requestFocus();
			} else if (pass.length() == 0) {
		 		 JOptionPane.showMessageDialog(indexF, "비밀번호 입력");
		 		 indexF.tf_pw.requestFocus();
			} else {
				switch (dao.loginCheck(indexF)) {
				case 0:
					adminF = new AdminFrame(indexF);
					adminF.setVisible(true); 		break;
				case 1:	
					clientF = new ClientFrame(indexF, id);
					clientF.setVisible(true);		break;

				case 2:
					indexF.tf_pw.setText("");
					indexF.tf_id.requestFocus();	break;
				case 3:	
					indexF.tf_id.setText("");
					indexF.tf_pw.setText("");
					indexF.tf_id.requestFocus();	break;
				}
			}
		} else if (e.getSource() == indexF.btnJoin) {
			joinF.setVisible(true);
			indexF.setVisible(false);
		} else if (e.getSource() == joinF.idCheck) {
			id = joinF.tf_id.getText().trim();
			if (id.contentEquals("")) {
				JOptionPane.showMessageDialog(joinF, "아이디 입력");
				joinF.tf_id.requestFocus();
				return;
			}
			if (dao.checkID(id)) {
				isCheckedId = true;
				JOptionPane.showMessageDialog(joinF, "사용가능한 아이디입니다.");
				joinF.tf_pw1.requestFocus();
			} else {
				JOptionPane.showMessageDialog(joinF, "등록된 아이디입니다. 다시 입력하세요.");
				joinF.tf_id.setText(null);
				joinF.tf_id.requestFocus();
				return;
			}
		} else if (e.getSource() == joinF.join) {

			if (!isCheckedId) {
				JOptionPane.showMessageDialog(joinF, "아이디 중복체크가 필요합니다.");
				return;
			}

			boolean isEqualPass = joinF.tf_pw1.getText().equals(joinF.tf_pw2.getText());

			if (!isEqualPass) {
				JOptionPane.showMessageDialog(joinF, "비밀번호가 일치하지 않습니다.");
				joinF.tf_pw1.setText(""); 	joinF.tf_pw2.setText("");
				return;
			}
			
			int joinResult = dao.join(joinF);

			if (joinResult != 1) {
				JOptionPane.showMessageDialog(joinF, "가입 실패");

			} else if (joinResult == 1) {
				JOptionPane.showMessageDialog(joinF, "가입 성공");
				joinF.tf_id.setText("");	joinF.tf_pw1.setText("");	joinF.tf_pw2.setText("");	joinF.tf_name.setText("");
				joinF.tf_birth.setText("");	joinF.tf_addr.setText("");	joinF.tf_tel.setText("");	joinF.tf_mail.setText("");
				joinF.dispose();
				indexF.setVisible(true);
				indexF.tf_id.requestFocus();
			}
			
		} else if(e.getSource() == joinF.cancel) {
			joinF.dispose();
			indexF.setVisible(true);
				
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
	}
}
