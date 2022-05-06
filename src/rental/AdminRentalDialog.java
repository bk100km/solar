package rental;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminRentalDialog extends JDialog implements ActionListener {
	private static final Color Color = null;
	JPanel pa = new JPanel(new GridLayout(1, 2));
	JPanel pw = new JPanel(new GridLayout(13, 1, 10, 5));
	JPanel pc = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
	JPanel ps = new JPanel();
		
	JLabel lable_Rcode = new JLabel("대여번호");
	JLabel lable_ID = new JLabel("아이디");
	JLabel lable_Cname = new JLabel("성명");
	JLabel lable_Pcode = new JLabel("제품코드");
	JLabel lable_Pname = new JLabel("제품명");
	JLabel lable_Rstart = new JLabel("대여시작일");
	JLabel lable_Rend = new JLabel("대여반납일");
	JLabel lable_Rday = new JLabel("대여기간");
	JLabel lable_Rleft = new JLabel("잔여일");
	JLabel lable_Pprice = new JLabel("월 대여료");
	JLabel lable_Rtotal = new JLabel("총 대여료");
	JLabel lable_addr = new JLabel("주소");
	JLabel month = new JLabel("개월");
	
	JTextField Rcode = new JTextField(13);
	JTextField ID = new JTextField(13);
	JTextField Cname = new JTextField(13);
	JTextField Pcode = new JTextField(13);
	JTextField Pname = new JTextField(13);
	JTextField Rstart = new JTextField(13);
	JTextField Rend = new JTextField(13);
	JTextField Rday = new JTextField(10);
	JTextField Rleft = new JTextField(13);
	JTextField Pprice = new JTextField(13);
	JTextField addr = new JTextField(13);
	JTextField Rtotal = new JTextField(13);
	
	JButton update = new JButton("수정");
	JButton cancel = new JButton("취소");

	RentalDAO dao = new RentalDAO();
	AdminRentalFrame arf;

	public AdminRentalDialog(AdminRentalFrame arf) {
		super(arf, "대여정보 수정", true);
		this.arf = arf;	
			
			pw.add(lable_Rcode);	pc.add(Rcode);	
			pw.add(lable_ID);		pc.add(ID);
			pw.add(lable_Cname);	pc.add(Cname);	
			pw.add(lable_Pcode);	pc.add(Pcode);
			pw.add(lable_Pname);	pc.add(Pname);
			pw.add(lable_Rstart);	pc.add(Rstart);
			pw.add(lable_Rend);		pc.add(Rend);	
			pw.add(lable_Rday);		pc.add(Rday);	pc.add(month);
			pw.add(lable_Rleft);	pc.add(Rleft);
			pw.add(lable_Pprice);	pc.add(Pprice);	
			pw.add(lable_Rtotal);	pc.add(Rtotal);
			pw.add(lable_addr);		pc.add(addr);	
			
			ps.add(update);			ps.add(cancel);
			pa.add(pw);				pa.add(pc);
			add(ps, "South");
			add(pa, "Center");
			
			setSize(350, 500);	setLocation(1400,250);
			setVisible(false);
			setResizable(false);
			
			update.addActionListener(this);
			cancel.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == update) {
			if (dao.rentalUpdateByAdmin(this) > 0) {
				JOptionPane.showMessageDialog(this, "수정 완료");
				dispose();
				dao.SelectAll(arf.dt);
				if (arf.dt.getRowCount() > 0)
				arf.jt.setRowSelectionInterval(0, 0);	
		} else {
			JOptionPane.showMessageDialog(this, "수정 실패");
		}
		} else if (e.getSource() == cancel) {
			dispose();
		}
	}
}
	