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
				JOptionPane.showMessageDialog(indexF, "���̵� �Է�");
				indexF.tf_pw.setText(null);
				indexF.tf_id.requestFocus();
			} else if (pass.length() == 0) {
		 		 JOptionPane.showMessageDialog(indexF, "��й�ȣ �Է�");
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
				JOptionPane.showMessageDialog(joinF, "���̵� �Է�");
				joinF.tf_id.requestFocus();
				return;
			}
			if (dao.checkID(id)) {
				isCheckedId = true;
				JOptionPane.showMessageDialog(joinF, "��밡���� ���̵��Դϴ�.");
				joinF.tf_pw1.requestFocus();
			} else {
				JOptionPane.showMessageDialog(joinF, "��ϵ� ���̵��Դϴ�. �ٽ� �Է��ϼ���.");
				joinF.tf_id.setText(null);
				joinF.tf_id.requestFocus();
				return;
			}
		} else if (e.getSource() == joinF.join) {

			if (!isCheckedId) {
				JOptionPane.showMessageDialog(joinF, "���̵� �ߺ�üũ�� �ʿ��մϴ�.");
				return;
			}

			boolean isEqualPass = joinF.tf_pw1.getText().equals(joinF.tf_pw2.getText());

			if (!isEqualPass) {
				JOptionPane.showMessageDialog(joinF, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				joinF.tf_pw1.setText(""); 	joinF.tf_pw2.setText("");
				return;
			}
			
			int joinResult = dao.join(joinF);

			if (joinResult != 1) {
				JOptionPane.showMessageDialog(joinF, "���� ����");

			} else if (joinResult == 1) {
				JOptionPane.showMessageDialog(joinF, "���� ����");
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
