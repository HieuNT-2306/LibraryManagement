import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddNewUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewUser frame = new AddNewUser();
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
	public void reset() 
	{
		textField.setText(null);
		textField_1.setText(null);
		textField_2.setText(null);
		textField_3.setText(null);
		passwordField.setText(null);
		passwordField_1.setText(null);
		comboBox.setSelectedIndex(0);
	}
	public String studentId()
	{
		String studentNum = "";
		for(int i=0;i<6;i++) 
		{
			int num=(int)(Math.random()*9)+1;
			studentNum=studentNum+num;
		}
		System.out.println(studentNum);
		return studentNum;
	}
	public AddNewUser() {
		setResizable(false);
		setTitle("Add User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);

		JPanel headerLabel = new JPanel(new BorderLayout());
		headerLabel.setBackground(Color.white);

		JLabel lblNewLabel = new JLabel("");
		Image img=new ImageIcon("img/icons8-add-user-48.png").getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.add(lblNewLabel,BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("Library Management Add User");
		lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.add(lblNewLabel_1,BorderLayout.CENTER);

		JLabel lblNewLabel_2 = new JLabel("Fill out the form carefully for registration");
		lblNewLabel_2.setFont(new Font("Roboto", Font.ITALIC, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.add(lblNewLabel_2,BorderLayout.SOUTH);


		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(Color.white);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel lblNewLabel_3 = new JLabel("");
		Image imgname=new ImageIcon("img/icons8-name-30 (1).png").getImage();
		lblNewLabel_3.setIcon(new ImageIcon(imgname));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;gbc.gridy=0;
		centerPanel.add(lblNewLabel_3,gbc);

		textField = new JTextField();
		textField.setFont(new Font("Roboto", Font.PLAIN, 15));
		textField.setColumns(20);
		textField.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"Name of Applicant: ",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 15),
				Color.GRAY
		));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 1;gbc.gridy=0;
		centerPanel.add(textField,gbc);

		JLabel lblNewLabel_3_1 = new JLabel("");
		Image imgPhone=new ImageIcon("img/icons8-phone-number-30.png").getImage();
		lblNewLabel_3_1.setIcon(new ImageIcon(imgPhone));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;gbc.gridy=1;
		centerPanel.add(lblNewLabel_3_1,gbc);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Roboto", Font.PLAIN, 15));
		textField_1.setColumns(20);
		textField_1.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"Phone Number: ",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 15),
				Color.GRAY
		));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 1;gbc.gridy=1;
		centerPanel.add(textField_1,gbc);

		JLabel lblNewLabel_3_1_1 = new JLabel("");
		Image imgEmail=new ImageIcon("img/icons8-email-30.png").getImage();
		lblNewLabel_3_1_1.setIcon(new ImageIcon(imgEmail));
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;gbc.gridy=2;
		centerPanel.add(lblNewLabel_3_1_1,gbc);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField_2.setColumns(20);
		textField_2.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"Email Address: ",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 15),
				Color.GRAY
		));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 1;gbc.gridy=2;
		centerPanel.add(textField_2,gbc);

		JLabel lblNewLabel_3_1_1_1 = new JLabel("");
		Image imgUser=new ImageIcon("img/icons8-username-30.png").getImage();
		lblNewLabel_3_1_1_1.setIcon(new ImageIcon(imgUser));
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;gbc.gridy=3;
		centerPanel.add(lblNewLabel_3_1_1_1,gbc);


		textField_3 = new JTextField();
		textField_3.setFont(new Font("Roboto", Font.PLAIN, 15));
		textField_3.setColumns(20);
		textField_3.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"Username:",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 15),
				Color.GRAY
		));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 1;gbc.gridy=3;
		centerPanel.add(textField_3,gbc);



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

		Image imgPassword=new ImageIcon("img/icons8-password-30.png").getImage();
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("");
		lblNewLabel_3_1_1_1_1.setIcon(new ImageIcon(imgPassword));
		lblNewLabel_3_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;gbc.gridy=4;
		centerPanel.add(lblNewLabel_3_1_1_1_1,gbc);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Roboto", Font.PLAIN, 15));
		passwordField.setColumns(20);
		passwordField.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"Enter password:",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 15),
				Color.GRAY
		));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 1;gbc.gridy=4;
		centerPanel.add(passwordField,gbc);

		gbc.gridx = 2; gbc.gridy = 4;
		centerPanel.add(showPassButton,gbc);

		JLabel lblNewLabel_3_1_1_1_1_1 = new JLabel("");
		lblNewLabel_3_1_1_1_1_1.setIcon(new ImageIcon(imgPassword));
		lblNewLabel_3_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;gbc.gridy=5;
		centerPanel.add(lblNewLabel_3_1_1_1_1_1,gbc);

		passwordField_1 = new JPasswordField();
		passwordField_1.setEchoChar('*');
		passwordField_1.setFont(new Font("Roboto", Font.PLAIN, 15));
		passwordField_1.setColumns(20);
		passwordField_1.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				"Retype password:",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 15),
				Color.GRAY
		));
		passwordField_1.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 1;gbc.gridy = 5;
		centerPanel.add(passwordField_1,gbc);

		ImageIcon hideIcon2 = new ImageIcon("img/icons8-hide-password-30.png");
		ImageIcon showIcon2 = new ImageIcon("img/icons8-show-password-30.png");

		JButton showPassButton2 = new JButton(hideIcon2);
		showPassButton2.setFocusPainted(false);
		showPassButton2.setBorderPainted(false);
		showPassButton2.setContentAreaFilled(false);
		// Bắt sự kiện bấm nút
		showPassButton2.addActionListener(new ActionListener() {
			private boolean isHidden = true;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isHidden) {
					passwordField_1.setEchoChar((char) 0); // Hiện mật khẩu
					showPassButton2.setIcon(showIcon2);
				} else {
					passwordField_1.setEchoChar('*'); // Ẩn mật khẩu
					showPassButton2.setIcon(hideIcon2);
				}
				isHidden = !isHidden;
			}
		});
		gbc.gridx = 2; gbc.gridy = 5;
		centerPanel.add(showPassButton2,gbc);

		JLabel userSelect = new JLabel("User Type:");
		userSelect.setHorizontalAlignment(SwingConstants.CENTER);
		userSelect.setFont(new Font("Roboto",Font.PLAIN,15));
		gbc.gridx = 0; gbc.gridy = 6;
		centerPanel.add(userSelect,gbc);

		String usertype[]= {"Select","Admin","Faculty","Student"};
		comboBox = new JComboBox(usertype);
		comboBox.setFont(new Font("Roboto",Font.PLAIN,15));
		gbc.gridx = 1;gbc.gridy = 6;
		centerPanel.add(comboBox,gbc);


		JPanel finalPanel = new JPanel(new BorderLayout());
		finalPanel.setBackground(Color.white);

		JPanel panelAddUser = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelAddUser.setBackground(Color.white);
		JButton btnNewButton = new JButton("Add User");
		btnNewButton.setFont(new Font("Roboto",Font.PLAIN,15));
		btnNewButton.setPreferredSize(new Dimension(200,30));
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBorderPainted(false);  // Tắt viền nút
		btnNewButton.setFocusPainted(false);   // Tắt viền khi có focus
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String name=textField.getText();
				String mobile=textField_1.getText();
				String email=textField_2.getText();
				String username=textField_3.getText();
				String password=String.copyValueOf(passwordField.getPassword());
				String re_password=String.copyValueOf(passwordField_1.getPassword());
				String userType=comboBox.getSelectedItem().toString();
				
				if(name.length()==0 || mobile.length()==0 || email.length()==0 || username.length()==0 ||password.length()==0 || re_password.length()==0 || userType.equalsIgnoreCase("select"))				
				{
					JOptionPane.showMessageDialog(getParent(), "Field was left empty, a value must be provided.","Field is required",JOptionPane.ERROR_MESSAGE);
				}else
				{
					if (!password.equals(re_password)) 
					{
						JOptionPane.showMessageDialog(getParent(), "Password does not match. Please re-enter your password","Invalid Password",JOptionPane.ERROR_MESSAGE);
					} else {
						Connection con=DBInfo.conn();
						String query="INSERT INTO registration(id,name,mobile,email,username,password,usertype) values(?,?,?,?,?,?,?)";
						int i=0;
						try 
						{
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1, studentId());
							ps.setString(2, name);
							ps.setString(3, mobile);
							ps.setString(4, email);
							ps.setString(5, username);
							ps.setString(6, password);
							ps.setString(7, userType);
							i=ps.executeUpdate();
						} catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
						if (i==1) 
						{
							JOptionPane.showMessageDialog(getParent(), "Your registration has been successfully completed","Registration Successful",JOptionPane.INFORMATION_MESSAGE);
							reset();
						}
					}
				}
			}
		});
		panelAddUser.add(btnNewButton);
		finalPanel.add(panelAddUser,BorderLayout.NORTH);

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
		finalPanel.add(cancelPanel,BorderLayout.CENTER);

		contentPane.add(headerLabel,BorderLayout.NORTH);
		contentPane.add(centerPanel,BorderLayout.CENTER);
		contentPane.add(finalPanel,BorderLayout.SOUTH);
	}
}
