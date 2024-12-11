import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

//Xong

public class DeleteUser extends JFrame {

	private JPanel contentPane;
	JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeleteUser() {
		setTitle("Delete Account");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 372);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		
		lblNewLabel = new JLabel("Student Id Number");
		lblNewLabel.setForeground(new Color(240, 240, 240));

		JPanel headPanel = new JPanel(new BorderLayout());
		headPanel.setBackground(Color.white);

		JLabel labelDeleteUser = new JLabel("");
		labelDeleteUser.setHorizontalAlignment(SwingConstants.CENTER);
		Image logoDeleteUser = new ImageIcon("img/icons8-delete-user-66.png").getImage();
		labelDeleteUser.setIcon(new ImageIcon(logoDeleteUser));
		headPanel.add(labelDeleteUser,BorderLayout.CENTER);

		lblNewLabel_2 = new JLabel("Delete my account");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Roboto", Font.BOLD, 16));
		headPanel.add(lblNewLabel_2,BorderLayout.SOUTH);


		JPanel centerPanel = new JPanel(new BorderLayout(5,5));
		centerPanel.setBackground(Color.white);

		JTextArea txtrAreYouSure = new JTextArea();
		txtrAreYouSure.setWrapStyleWord(true);
		txtrAreYouSure.setBackground(Color.white);
		txtrAreYouSure.setFont(new Font("Roboto", Font.ITALIC, 13));
		txtrAreYouSure.setLineWrap(true);
		txtrAreYouSure.setEditable(false);
		txtrAreYouSure.setText("Are you sure you want to remove your account from the library management system? " +
				"Keep in mind that you won't be able to access your account again or retrieve any of the book's " +
				"data added in Library Management System.");
		centerPanel.add(txtrAreYouSure,BorderLayout.CENTER);

		lblNewLabel_3 = new JLabel("If you would still like your account deleted, click \"Delete My Account\".");
		lblNewLabel_3.setFont(new Font("Roboto", Font.ITALIC, 13));
		centerPanel.add(lblNewLabel_3,BorderLayout.SOUTH);


		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setBackground(Color.white);

		JPanel buttonDelete = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonDelete.setBackground(Color.white);
		JButton btnNewButton = new JButton("Delete Account");
		btnNewButton.setPreferredSize(new Dimension(150,40));
		btnNewButton.setFont(new Font("Roboto", Font.BOLD, 15));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBorderPainted(false);  // Tắt viền nút
		btnNewButton.setFocusPainted(false);   // Tắt viền khi có focus
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String id=lblNewLabel.getText();
				int x=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to delete ","Confirm delete",JOptionPane.INFORMATION_MESSAGE);
				if(x==0)
				{
					Connection con=DBInfo.conn();
					String query="DELETE FROM registration WHERE id=?";
					int flag=0;
					try {
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, id);
						flag=ps.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (flag==0) {
						JOptionPane.showMessageDialog(getParent(), "User not deleted please contact your librarian", "Error", JOptionPane.ERROR_MESSAGE);
					}else
					{
							JOptionPane.showMessageDialog(getParent(), "User successfully deleted","Success",JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
					}
				}
			}
		});
		buttonDelete.add(btnNewButton);
		buttonPanel.add(buttonDelete,BorderLayout.NORTH);

		JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cancelPanel.setBackground(Color.white);
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFont(new Font("Roboto",Font.PLAIN,15));
		btnNewButton_1.setForeground(Color.BLACK);// Màu chữ ban đầu
		// Thay đổi màu văn bản khi di chuột qua nút
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLUE);  // Màu chữ khi di chuột
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLACK);  // Trả lại màu chữ khi không di chuột
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnNewButton_1.setForeground(Color.RED);   // Màu chữ khi bấm
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLUE);  // Màu chữ khi thả nút
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
		cancelPanel.add(btnNewButton_1);
		buttonPanel.add(cancelPanel,BorderLayout.CENTER);

		contentPane.setLayout(new BorderLayout());
		contentPane.add(headPanel,BorderLayout.NORTH);
		contentPane.add(centerPanel,BorderLayout.CENTER);
		contentPane.add(buttonPanel,BorderLayout.SOUTH);
	}
}
