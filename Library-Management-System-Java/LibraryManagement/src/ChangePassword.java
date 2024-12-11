import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import java.awt.Window.Type;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;


//Xong
public class ChangePassword extends JFrame {

	private JPanel contentPane;
	JLabel lblNewLabel;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JButton btnNewButton;
	private JButton btnCancel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
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
	public ChangePassword() {
		setTitle("Change Password");
		setBackground(Color.white);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(new Color(240, 240, 240));

		contentPane.setLayout(new BorderLayout());


		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(Color.white);

		JLabel changeLogo = new JLabel();
		Image changeIcon = new ImageIcon("img/icons8-change-password-30.png").getImage();
		changeLogo.setIcon(new ImageIcon(changeIcon));
		changeLogo.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(changeLogo,BorderLayout.NORTH);

		JLabel changeText = new JLabel("Change Password");
		changeText.setFont(new Font("Roboto",Font.BOLD,15));
		changeText.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(changeText,BorderLayout.CENTER);


		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(Color.white);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Roboto", Font.PLAIN, 15));
		passwordField.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"Current Password",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 14),
				Color.GRAY
		));
		passwordField.setColumns(20);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0; gbc.gridy = 0;
		centerPanel.add(passwordField,gbc);

		// JCheckBox Show/Hide Password
		ImageIcon hideIcon = new ImageIcon("img/icons8-hide-password-30.png");
		ImageIcon showIcon = new ImageIcon("img/icons8-show-password-30.png");

		JButton showPassButton = new JButton(hideIcon);
		showPassButton.setFocusPainted(false);
		showPassButton.setBorderPainted(false);
		showPassButton.setContentAreaFilled(false);
		// Bắt sự kiện bấm nút
		showPassButton.addActionListener(new ActionListener() {
			private boolean isHidden = true;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isHidden) {
					passwordField.setEchoChar((char) 0); // Hiện mật khẩu
					showPassButton.setIcon(showIcon);
				} else {
					passwordField.setEchoChar('*'); // Ẩn mật khẩu
					showPassButton.setIcon(hideIcon);
				}
				isHidden = !isHidden;
			}
		});
		gbc.gridx = 1; gbc.gridy = 0;
		centerPanel.add(showPassButton,gbc);

		passwordField_1 = new JPasswordField();
		passwordField_1.setEchoChar('*');
		passwordField_1.setFont(new Font("Roboto", Font.PLAIN, 15));
		passwordField_1.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"New Password",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 14),
				Color.GRAY
		));
		passwordField_1.setColumns(20);
		passwordField_1.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0; gbc.gridy = 1;
		centerPanel.add(passwordField_1,gbc);

		JButton showPassButton1 = new JButton(hideIcon);
		showPassButton1.setFocusPainted(false);
		showPassButton1.setBorderPainted(false);
		showPassButton1.setContentAreaFilled(false);
		// Bắt sự kiện bấm nút
		showPassButton1.addActionListener(new ActionListener() {
			private boolean isHidden = true;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isHidden) {
					passwordField.setEchoChar((char) 0); // Hiện mật khẩu
					showPassButton1.setIcon(showIcon);
				} else {
					passwordField.setEchoChar('*'); // Ẩn mật khẩu
					showPassButton1.setIcon(hideIcon);
				}
				isHidden = !isHidden;
			}
		});
		gbc.gridx = 1; gbc.gridy = 1;
		centerPanel.add(showPassButton1,gbc);

		passwordField_2 = new JPasswordField();
		passwordField_2.setEchoChar('*');
		passwordField_2.setFont(new Font("Roboto", Font.PLAIN, 15));
		passwordField_2.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"Confirm New Password",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 14),
				Color.GRAY
		));
		passwordField_2.setColumns(20);
		passwordField_2.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0; gbc.gridy = 2;
		centerPanel.add(passwordField_2,gbc);

		JButton showPassButton2 = new JButton(hideIcon);
		showPassButton2.setFocusPainted(false);
		showPassButton2.setBorderPainted(false);
		showPassButton2.setContentAreaFilled(false);
		// Bắt sự kiện bấm nút
		showPassButton2.addActionListener(new ActionListener() {
			private boolean isHidden = true;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isHidden) {
					passwordField.setEchoChar((char) 0); // Hiện mật khẩu
					showPassButton2.setIcon(showIcon);
				} else {
					passwordField.setEchoChar('*'); // Ẩn mật khẩu
					showPassButton2.setIcon(hideIcon);
				}
				isHidden = !isHidden;
			}
		});
		gbc.gridx = 1; gbc.gridy = 2;
		centerPanel.add(showPassButton2,gbc);

		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setBackground(Color.white);

		JPanel buttonChange = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonChange.setBackground(Color.white);
		btnNewButton = new JButton("Change Password");
		btnNewButton.setPreferredSize(new Dimension(200,40));
		btnNewButton.setFont(new Font("Roboto", Font.BOLD, 15));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBorderPainted(false);  // Tắt viền nút
		btnNewButton.setFocusPainted(false);   // Tắt viền khi có focus
		buttonChange.add(btnNewButton);
		buttonPanel.add(buttonChange,BorderLayout.NORTH);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String id=lblNewLabel.getText();
				String password_old=String.copyValueOf(passwordField.getPassword());
				String password_new=String.copyValueOf(passwordField_1.getPassword());
				String password_current = String.copyValueOf(passwordField_2.getPassword());

				Connection con=DBInfo.conn();
				String checkSql="SELECT * FROM registration WHERE id=?";
				String oldPassword="";
				try {
					PreparedStatement checkPs=con.prepareStatement(checkSql);
					checkPs.setString(1, id);
					ResultSet checkRes=checkPs.executeQuery();
					while(checkRes.next())
					{
						oldPassword=checkRes.getString(6);
					}
					System.out.println(oldPassword+":::"+password_old);
					if(password_old.equals(oldPassword))
					{
						if(password_current.equals(password_new))
						{
							String query="UPDATE registration SET password=? WHERE id='"+id+"'";
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1, password_new);
							int flag=0;
							flag=ps.executeUpdate();
							if(flag==1)
							{
								JOptionPane.showMessageDialog(getParent(), "Password successfully updated","Success",JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(getParent(), "New password and re-entered password do not match.","Invalid Input",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Incorrect previous old password.","Invalid Input",JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cancelPanel.setBackground(Color.white);

		btnCancel = new JButton("Cancel");
		btnCancel.setBorderPainted(false);
		btnCancel.setFocusPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setFont(new Font("Roboto",Font.PLAIN,15));
		btnCancel.setForeground(Color.BLACK);  // Màu chữ ban đầu

		// Thay đổi màu văn bản khi di chuột qua nút
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancel.setForeground(Color.BLUE);  // Màu chữ khi di chuột
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCancel.setForeground(Color.BLACK);  // Trả lại màu chữ khi không di chuột
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnCancel.setForeground(Color.RED);   // Màu chữ khi bấm
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnCancel.setForeground(Color.BLUE);  // Màu chữ khi thả nút
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		cancelPanel.add(btnCancel);
		buttonPanel.add(cancelPanel,BorderLayout.CENTER);

		contentPane.add(headerPanel,BorderLayout.NORTH);
		contentPane.add(centerPanel,BorderLayout.CENTER);
		contentPane.add(buttonPanel,BorderLayout.SOUTH);

	}
}
