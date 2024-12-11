import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


//Xong

public class PasswordCheck extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordCheck frame = new PasswordCheck();
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
	public PasswordCheck() {
		setTitle("Log In");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);

		// Panel trung tâm chứa Welcome và form
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(Color.white);

		JLabel libraryLogo = new JLabel("");
		Image img=new ImageIcon("img/icons8-library-70.png").getImage();
		libraryLogo.setIcon(new ImageIcon(img));
		libraryLogo.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(libraryLogo,BorderLayout.NORTH);

		JLabel labelWelcome = new JLabel("Welcome Library Management System!");
		labelWelcome.setFont(new Font("Roboto", Font.BOLD, 20));
		labelWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(labelWelcome,BorderLayout.SOUTH);

		// Phần thông tin đăng nhập
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(Color.white);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel usernameLogo = new JLabel();
		ImageIcon userIcon = new ImageIcon("img/icons8-username-30.png");
		Image scaledUserIcon = userIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		usernameLogo.setIcon(new ImageIcon(scaledUserIcon));
		gbc.gridx = 0; gbc.gridy = 0;
		centerPanel.add(usernameLogo, gbc);

		textField = new JTextField();
		textField.setFont(new Font("Roboto", Font.PLAIN, 15));
		textField.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"Username",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 14),
				Color.GRAY
		));
		textField.setColumns(20);
		gbc.gridx = 1; gbc.gridy = 0;
		centerPanel.add(textField, gbc);

		JLabel passwordLogo = new JLabel();
		ImageIcon passwordIcon = new ImageIcon("img/icons8-password-30.png");
		Image scaledPassIcon = passwordIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		passwordLogo.setIcon(new ImageIcon(scaledPassIcon));
		gbc.gridx = 0; gbc.gridy = 1;
		centerPanel.add(passwordLogo, gbc);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Roboto", Font.PLAIN, 15));
		passwordField.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"Password",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 14),
				Color.GRAY
		));
		passwordField.setColumns(20);
		gbc.gridx = 1; gbc.gridy = 1;
		centerPanel.add(passwordField, gbc);

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
		gbc.gridx = 3;
		gbc.gridy = 1;
		centerPanel.add(showPassButton,gbc);

		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setBackground(Color.white);

		JPanel buttonLogin = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonLogin.setBackground(Color.white);
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.setPreferredSize(new Dimension(100,30));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBorderPainted(false);  // Tắt viền nút
		btnNewButton.setFocusPainted(false);   // Tắt viền khi có focus
		btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
		buttonLogin.add(btnNewButton);
		buttonPanel.add(buttonLogin,BorderLayout.NORTH);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String username1=textField.getText();
				System.out.println("main username "+username1);
				String password=String.copyValueOf(passwordField.getPassword());
				System.out.println("main password "+password);

				Connection con=DBInfo.conn();
				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String sql="SELECT * FROM registration WHERE username='"+username1+"' AND password='"+password+"'";
				ResultSet res = null;
				try {
					res = stmt.executeQuery(sql);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					if (res.next()) 
					{
						id=res.getString(1);
						System.out.println(id);
						Settings setting=new Settings();
						setting.lblNewLabel.setText(id);
						setting.setVisible(true);
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Incorrect username or password, please check.","Invalid Input",JOptionPane.ERROR_MESSAGE);
					}

				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cancelPanel.setBackground(Color.white);

		JButton btnCancel = new JButton("Cancel");
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

		BorderLayout boderLayout = new BorderLayout(5,5);
		contentPane.setLayout(boderLayout);
		contentPane.add(headerPanel,BorderLayout.NORTH);
		contentPane.add(centerPanel,BorderLayout.CENTER);
		contentPane.add(buttonPanel,BorderLayout.SOUTH);
	}

}
