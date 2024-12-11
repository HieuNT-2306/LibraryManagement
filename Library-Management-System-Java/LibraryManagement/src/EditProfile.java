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

//Xong

public class EditProfile extends JFrame {
	
	private JPanel contentPane;
	JTextField textField;
	JTextField textField_1;
	JTextField textField_2;
	JTextField textField_3;
	String name, phone,email, username;
	JLabel lblNewLabel_3_2_1 ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfile frame = new EditProfile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	
	public EditProfile() {
		setTitle("Edit Profile");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 550);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);

		JPanel headerLabel = new JPanel(new BorderLayout());
		headerLabel.setBackground(Color.white);

		JLabel lblNewLabel = new JLabel("");
		Image img=new ImageIcon("img/icons8-registration-64.png").getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.add(lblNewLabel,BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("Edit Profile");
		lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.add(lblNewLabel_1,BorderLayout.CENTER);

		JLabel lblNewLabel_2 = new JLabel("Complete all required fields.");
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
				new Font("Arial", Font.PLAIN, 14),
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
				new Font("Arial", Font.PLAIN, 14),
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
				new Font("Arial", Font.PLAIN, 14),
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
				new Font("Arial", Font.PLAIN, 14),
				Color.GRAY
		));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 1;gbc.gridy=3;
		centerPanel.add(textField_3,gbc);


		JPanel finalPanel = new JPanel(new BorderLayout());
		finalPanel.setBackground(Color.white);

		JPanel buttonSave = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonSave.setBackground(Color.white);
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Roboto",Font.PLAIN,15));
		btnNewButton.setPreferredSize(new Dimension(200,30));
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBorderPainted(false);  // Tắt viền nút
		btnNewButton.setFocusPainted(false);// Tắt viền khi có focus
		buttonSave.add(btnNewButton);
		finalPanel.add(buttonSave,BorderLayout.NORTH);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				name=textField.getText();
				phone=textField_1.getText();
				email = textField_2.getText();
				username=textField_3.getText();
				String id=lblNewLabel_3_2_1.getText();
				
				if(name.length()==0 || phone.length()==0 || email.length() == 0 || username.length()==0)
				{
					JOptionPane.showMessageDialog(getParent(), "Field was left empty, a value must be provided.","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}else
				{
					int j=JOptionPane.showConfirmDialog(getParent(), "Are you sure? The user info will be update","Confirm",JOptionPane.INFORMATION_MESSAGE);
					if(j==0)
					{
						Connection con=DBInfo.conn();
						String query="UPDATE registration SET name='"+name+"', mobile='"+phone+"', email='"+email+"', username='"+username+"' WHERE id='"+id+"'";
						int i=0;
						try {
							PreparedStatement ps=con.prepareStatement(query);
							i=ps.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (i==1) {
							JOptionPane.showMessageDialog(getParent(), "User succesfully updated.","Success",JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(getParent(), "User not updated.","Error",JOptionPane.ERROR_MESSAGE);
						}
					}

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
		finalPanel.add(cancelPanel,BorderLayout.CENTER);

		lblNewLabel_3_2_1 = new JLabel("Student Id Number");
		lblNewLabel_3_2_1.setForeground(new Color(240, 240, 240));
		lblNewLabel_3_2_1.setFont(new Font("Verdana", Font.PLAIN, 13));

		contentPane.setLayout(new BorderLayout());
		contentPane.add(headerLabel,BorderLayout.NORTH);
		contentPane.add(centerPanel,BorderLayout.CENTER);
		contentPane.add(finalPanel,BorderLayout.SOUTH);
	}
}
