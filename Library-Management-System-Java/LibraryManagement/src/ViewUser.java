import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ViewUser extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUser frame = new ViewUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewUser() {
		// Cấu hình cơ bản cho frame
		setResizable(false);
		setTitle("View Users");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 628, 400); // Tăng chiều cao để chứa nút Cancel
		setLocationRelativeTo(this);

		// Content Pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(240, 248, 255)); // Màu nền xanh nhạt
		setContentPane(contentPane);

		// Tiêu đề chính
		JLabel lblNewLabel = new JLabel("View Users");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(25, 25, 112)); // Màu chữ đậm

		// Tiêu đề phụ
		JLabel lblNewLabel_2 = new JLabel("Account Type");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblNewLabel_2.setForeground(new Color(70, 130, 180)); // Màu chữ

		// Nút Admin
		JButton btnAdmin = createStyledButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openUserFrame(e.getActionCommand());
			}
		});

		// Nút Faculty
		JButton btnFaculty = createStyledButton("Faculty");
		btnFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openUserFrame(e.getActionCommand());
			}
		});

		// Nút Student
		JButton btnStudent = createStyledButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openUserFrame(e.getActionCommand());
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Verdana", Font.PLAIN, 13)); // Font giống các nút khác
		btnCancel.setFocusPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setForeground(Color.BLACK);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Đóng cửa sổ hiện tại
			}
		});
		// Hiệu ứng hover cho Cancel - đổi màu chữ sang xanh dương
		btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCancel.setForeground(new Color(30, 144, 255)); // Đổi màu chữ sang xanh dương
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCancel.setForeground(Color.BLACK); // Màu chữ trở lại đen
			}
		});
		btnCancel.setBounds(270, 280, 80, 30); // Chỉnh kích thước và khoảng cách

		// Group Layout cho Content Pane
		contentPane.setLayout(null);
		lblNewLabel.setBounds(10, 20, 594, 30);
		lblNewLabel_2.setBounds(10, 60, 594, 20);
		btnAdmin.setBounds(70, 120, 140, 60);
		btnFaculty.setBounds(240, 120, 140, 60);
		btnStudent.setBounds(410, 120, 140, 60);
		btnCancel.setBounds(270, 280, 80, 30); // Vị trí nút Cancel

		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_2);
		contentPane.add(btnAdmin);
		contentPane.add(btnFaculty);
		contentPane.add(btnStudent);
		contentPane.add(btnCancel);
	}

	// Phương thức mở Frame hiển thị dữ liệu
	private void openUserFrame(String accountType) {
		JFrame frame = new JFrame(accountType + " Users");
		frame.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		try {
			DBInfo.viewLibrarians(accountType);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		table = new JTable(DBInfo.outerVector2, DBInfo.colsName2);
		JScrollPane pane = new JScrollPane(table);
		frame.getContentPane().add(pane);
	}

	// Phương thức tạo các nút với style cải tiến
	private JButton createStyledButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Verdana", Font.PLAIN, 15));
		button.setFocusPainted(false);
		button.setBackground(new Color(34, 139, 34)); // Màu nền xanh lá
		button.setForeground(Color.WHITE); // Màu chữ trắng
		button.setBorderPainted(false);
		button.setOpaque(true);

		// Hiệu ứng hover
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0, 128, 0)); // Màu hover (xanh lá đậm hơn)
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(34, 139, 34)); // Màu nền ban đầu
			}
		});
		return button;
	}
}
