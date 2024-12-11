import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Xong

public class Settings extends JFrame {

	private JPanel contentPane;
	JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
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
	public Settings() {
		setResizable(false);
		setTitle("Setting");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);

		lblNewLabel = new JLabel("Id Number");
		lblNewLabel.setForeground(new Color(240, 240, 240));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 13));

		
		JLabel settingIconLabel = new JLabel();
		Image settingLogo = new ImageIcon("img/icons8-setting-50.png").getImage();
		settingIconLabel.setIcon(new ImageIcon(settingLogo));
		settingIconLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(Color.white);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10,0,10,0);

		ImageIcon iconEditProfile = new ImageIcon(new ImageIcon("img/user-profile_5003738 (2).png").getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH));
		JButton editProfileButton = new JButton("Edit Profile",iconEditProfile);
		editProfileButton.setBackground(Color.GREEN);
		editProfileButton.setPreferredSize(new Dimension(200,40));
		editProfileButton.setBorderPainted(false);
		editProfileButton.setFocusPainted(false);
		editProfileButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		editProfileButton.setVerticalTextPosition(SwingConstants.CENTER);
		editProfileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id=lblNewLabel.getText();
				EditProfile editProfile=new EditProfile();
				editProfile.lblNewLabel_3_2_1.setText(id);
				editProfile.setVisible(true);
			}
		});
		buttonPanel.add(editProfileButton,gbc);

		ImageIcon iconChangePass = new ImageIcon(new ImageIcon("img/reset-password_11135322.png").getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH));
		JButton changePassButton = new JButton("Change Password",iconChangePass);
		changePassButton.setBackground(Color.GREEN);
		changePassButton.setPreferredSize(new Dimension(200,40));
		changePassButton.setBorderPainted(false);
		changePassButton.setFocusPainted(false);
		changePassButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		changePassButton.setVerticalTextPosition(SwingConstants.CENTER);
		changePassButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String id=lblNewLabel.getText();
				ChangePassword changePassword=new ChangePassword();
				changePassword.lblNewLabel.setText(id);
				changePassword.setVisible(true);
			}
		});
		buttonPanel.add(changePassButton,gbc);

		ImageIcon deleteUserIcon = new ImageIcon(new ImageIcon("img/icons8-delete-user-32.png").getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH));
		JButton deleteUserButton = new JButton("Delete User",deleteUserIcon);
		deleteUserButton.setBackground(Color.GREEN);
		deleteUserButton.setPreferredSize(new Dimension(200,40));
		deleteUserButton.setBorderPainted(false);
		deleteUserButton.setFocusPainted(false);
		deleteUserButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		deleteUserButton.setVerticalTextPosition(SwingConstants.CENTER);
		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String id=lblNewLabel.getText();
				DeleteUser deleteUser=new DeleteUser();
				deleteUser.lblNewLabel.setText(id);
				deleteUser.setVisible(true);
			}
		});
		buttonPanel.add(deleteUserButton,gbc);

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
		buttonPanel.add(btnCancel,gbc);

		contentPane.setLayout(new BorderLayout(10,10));
		contentPane.add(settingIconLabel,BorderLayout.NORTH);
		contentPane.add(buttonPanel,BorderLayout.CENTER);

	}
}
