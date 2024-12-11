import java.awt.EventQueue;
import java.awt.*;

import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


//Xong

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setTitle("Library Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 40, 0));
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


		// Phần nút bấm
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setBackground(Color.white);

		JPanel buttonLogin = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonLogin.setBackground(Color.white);
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setPreferredSize(new Dimension(200,30));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBorderPainted(false);  // Tắt viền nút
		btnNewButton.setFocusPainted(false);   // Tắt viền khi có focus
		buttonLogin.add(btnNewButton);
		buttonPanel.add(buttonLogin,BorderLayout.NORTH);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String username=textField.getText();
				String password=String.copyValueOf(passwordField.getPassword());
				Connection con=DBInfo.conn();
				int i=0;
				String usertype="";
				
				String query="SELECT * FROM registration WHERE username=? and password=?";
				try 
				{
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, username);
					ps.setString(2, password);
					ResultSet res=ps.executeQuery();
					while(res.next()) 
					{
						i=1;
						usertype=res.getString(7);
					}
				} catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				if (i==1 && usertype.equalsIgnoreCase("Admin")) 
				{
					AdminPage ap=new AdminPage();
					ap.setVisible(true);
					dispose();
				}
				if (i==1 && usertype.equalsIgnoreCase("Faculty"))
				{
					FacultyPage fp =new FacultyPage() ;
					fp.setVisible(true);
					dispose();
				}
				if (i==1 && usertype.equalsIgnoreCase("Student")) {
					StudentPage sp = new StudentPage();
					sp.setVisible(true);
					dispose();
				}
				if(i==0) 
				{
					JOptionPane.showMessageDialog(getParent(), "Incorrect username or password","Sign In Failed",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Roboto", Font.PLAIN, 10));

		JPanel sigupLabel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		sigupLabel.setBackground(Color.white);
		JButton btnSignUp = new JButton("Sign Up");

		// Loại bỏ viền và nền
		btnSignUp.setBorderPainted(false);  // Không vẽ viền
		btnSignUp.setFocusPainted(false);   // Không vẽ viền khi có focus
		btnSignUp.setContentAreaFilled(false);  // Không tô nền cho nút

		// Tùy chỉnh font chữ
		btnSignUp.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnSignUp.setForeground(Color.BLACK);  // Màu chữ ban đầu

		// Thay đổi màu văn bản khi di chuột qua nút
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSignUp.setForeground(Color.BLUE);  // Màu chữ khi di chuột
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSignUp.setForeground(Color.BLACK);  // Trả lại màu chữ khi không di chuột
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnSignUp.setForeground(Color.RED);   // Màu chữ khi bấm
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnSignUp.setForeground(Color.BLUE);  // Màu chữ khi thả nút
			}
		});

		sigupLabel.add(btnSignUp);
		buttonPanel.add(sigupLabel,BorderLayout.CENTER);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Registration regis=new Registration();
				regis.setVisible(true);
				setVisible(false);
			}
		});
		btnSignUp.setFont(new Font("Roboto", Font.PLAIN, 13));

		BorderLayout boderLayout = new BorderLayout(1,1);
		contentPane.setLayout(boderLayout);
		contentPane.add(headerPanel,BorderLayout.NORTH);
		contentPane.add(centerPanel,BorderLayout.CENTER);
		contentPane.add(buttonPanel,BorderLayout.SOUTH);

	}
}
