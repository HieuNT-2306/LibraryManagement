import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import javax.swing.SwingConstants;
public class AddNewBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	//private JComboBox comboBox,comboBox_1,comboBox_2,comboBox_3;
	private AutoComboBox comboBox,comboBox_1,comboBox_2,comboBox_3;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewBook frame = new AddNewBook();
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
	
	
	public String codeNum() 
	{
		String bookId="";
		for(int i=0;i<8;i++) 
		{
			int num=(int)(Math.random()*9)+1;
			bookId=bookId+num;
		}
		System.out.println("QR Number is : "+bookId);
		String url=bookId;
		String path = System.getProperty("user.home") + "\\Desktop\\" + url + ".png";
		String charset="UTF-8";
		try {
			generateQRCode(url, path, charset, 100, 200);
		} catch (WriterException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("QR Code Generated Successfully...");
		return bookId;
	}
	
	
	//Generate QR-BarCode
	public static void generateQRCode(String data,String path,String charset,int h,int w) throws WriterException, IOException 
	{
		BitMatrix bitMatrix=new MultiFormatWriter().encode(data, BarcodeFormat.UPC_E, w, h);
		MatrixToImageWriter.writeToPath(bitMatrix, path.substring(path.lastIndexOf('.')+1), Paths.get(path));
	}
	
	
	//Add New Names of Author,Subject,Publisher,Category in DB;
	public void addNames(String name, String str) 
	{
		Connection con=DBInfo.conn();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String sql="SELECT * FROM "+name+" WHERE name='"+str+"'";
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
				JOptionPane.showMessageDialog(getParent(), str+" already exist.","Invalid Input",JOptionPane.ERROR_MESSAGE);
			}else
			{
				String query="INSERT INTO "+name+"(name) VALUE(?)";
				int i=0;
				try {
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, str);
					i=ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (i==0) {
					JOptionPane.showMessageDialog(getParent(), "Record not inserted.","Error",JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(getParent(), "Recode succesfully inserted.","Success",JOptionPane.INFORMATION_MESSAGE);
					dispose();
					AddNewBook anb=new AddNewBook();
					anb.setVisible(true);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	
	//Reset All the Boxes;
	public void reset() 
	{
		textField.setText(codeNum());
		String url=textField.getText();
		String path = System.getProperty("user.home") + "\\Desktop\\" + url + ".png";
		String charset="UTF-8";
		try {
			generateQRCode(url, path, charset, 100, 200);
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("QR Code Generated Successfully...");
		String imgPath = System.getProperty("user.home") + "\\Desktop\\" + url + ".png";
		ImageIcon imgIcon=new ImageIcon(imgPath);
		//lblNewLabel_2.setIcon(imgIcon);
		Image img=imgIcon.getImage();
		Image resize=img.getScaledInstance(200, 68, Image.SCALE_DEFAULT);
		ImageIcon resizedImg=new ImageIcon(resize);
		lblNewLabel_2.setIcon(resizedImg);
		textField_1.setText(null);
		comboBox.setSelectedIndex(0);
		comboBox_1.setSelectedIndex(0);
		comboBox_2.setSelectedIndex(0);
		comboBox_3.setSelectedIndex(0);
		textField_2.setText(null);
		textField_3.setText(null);
		textField_4.setText(null);
	}
	
	
	//Constructor;
	public AddNewBook() {
		setResizable(false);
		setTitle("Add New Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 801, 511);
		setLocationRelativeTo(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Add New");
		mnNewMenu.setFont(new Font("Verdana", Font.PLAIN, 13));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Author");
		mntmNewMenuItem.setFont(new Font("Verdana", Font.PLAIN, 13));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String name=e.getActionCommand();
				System.out.println(name);
				String str=JOptionPane.showInputDialog("Enter "+name+"'s name");
				System.out.println(str);
				if (str.length()==0) {
					JOptionPane.showMessageDialog(getParent(), "The Author name cannot be empty.","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}else 
				{
					addNames(name, str);	
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Publisher");
		mntmNewMenuItem_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String name=e.getActionCommand();
				System.out.println(name);
				String str=JOptionPane.showInputDialog("Enter "+name+"'s name");
				if (str.length()==0) {
					JOptionPane.showMessageDialog(getParent(), "Publisher name cannot be empty.","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}else {
					System.out.println(str);
					addNames(name, str);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Subject");
		mntmNewMenuItem_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String name=e.getActionCommand();
				System.out.println(name);
				String str=JOptionPane.showInputDialog("Enter "+name);
				System.out.println(str);
				if (str.length()==0) {
					JOptionPane.showMessageDialog(getParent(), "Subject name cannot be empty.","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}else {
					addNames(name, str);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Category");
		mntmNewMenuItem_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String name=e.getActionCommand();
				System.out.println(name);
				String str=JOptionPane.showInputDialog("Enter "+name);
				System.out.println(str);
				if (str.length()==0) {
					JOptionPane.showMessageDialog(getParent(), "Category cannot be empty.","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}else {
					addNames(name, str);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Add New Book");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Book Id");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField = new JTextField(codeNum());
		textField.setEditable(false);
		textField.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Title");
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		
	    List<String> authors = DBInfo.getValue("author");
	    comboBox = new AutoComboBox();
	    String[] authorsArray = authors.toArray(new String[0]);
	    comboBox.setKeyWord(authorsArray);
	    comboBox.setFont(new Font("Verdana", Font.PLAIN, 13));

	    // Subjects
	    List<String> subjects = DBInfo.getValue("subject");
	    String[] subjectsArray = subjects.toArray(new String[0]);
	    AutoComboBox comboBox_1 = new AutoComboBox();
	    comboBox_1.setKeyWord(subjectsArray);
	    comboBox_1.setFont(new Font("Verdana", Font.PLAIN, 13));

	    // Publishers
	    List<String> publishers = DBInfo.getValue("publisher");
	    String[] publishersArray = publishers.toArray(new String[0]);
	    AutoComboBox comboBox_2 = new AutoComboBox();
	    comboBox_2.setKeyWord(publishersArray);
	    comboBox_2.setFont(new Font("Verdana", Font.PLAIN, 13));

	    // Categories
	    List<String> categories = DBInfo.getValue("category");
	    String[] categoriesArray = categories.toArray(new String[0]);
	    AutoComboBox comboBox_3 = new AutoComboBox();
	    comboBox_3.setKeyWord(categoriesArray);
	    comboBox_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Author");
		lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Subject");
		lblNewLabel_1_1_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Publisher");
		lblNewLabel_1_1_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1_1_4 = new JLabel("Category");
		lblNewLabel_1_1_4.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("ISBN Number");
		lblNewLabel_1_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1_1_4_1 = new JLabel("Edition");
		lblNewLabel_1_1_4_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1_1_4_1_1 = new JLabel("Shelf No.");
		lblNewLabel_1_1_4_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton_1 = new JButton("Save");
		Image img1=new ImageIcon(this.getClass().getResource("save-icon--1.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnNewButton_1.setIcon(new ImageIcon(img1));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String id=textField.getText();
				String title=textField_1.getText();
				String author=comboBox.getSelectedItem().toString();
				String subject=comboBox_1.getSelectedItem().toString();
				String publisher=comboBox_2.getSelectedItem().toString();
				String category=comboBox_3.getSelectedItem().toString();
				String isbn=textField_2.getText();
				String edi=textField_3.getText();
				String shelfNo=textField_4.getText();
				
				if (id.length()==0 || title.length()==0 || author=="Select" || subject=="Select" || publisher=="Select" || category=="Select" || isbn.length()==0 || edi.length()==0 || shelfNo.length()==0) 
				{
					JOptionPane.showMessageDialog(getParent(), "Field was left empty, a value must be provided.","Field is required",JOptionPane.ERROR_MESSAGE);
				} else 
				{
					int j=JOptionPane.showConfirmDialog(getParent(), "Are you sure? The Book will be added","Confirm",JOptionPane.INFORMATION_MESSAGE);
					if(j==0) 
					{
						Connection con=DBInfo.conn();
						int i=0;
						String query="INSERT INTO book(bookid,title,author,subject,publisher,category,isbn,edition,shelfNo) VALUES(?,?,?,?,?,?,?,?,?)";
						try {
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1, id);
							ps.setString(2, title);
							ps.setString(3, author);
							ps.setString(4, subject);
							ps.setString(5, publisher);
							ps.setString(6, category);
							ps.setString(7, isbn);
							ps.setString(8, edi);
							ps.setString(9, shelfNo);
							i=ps.executeUpdate();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if (i==1) 
						{
							String url=textField.getText();
							String path=System.getProperty("user.home") + "\\Desktop\\" + url + ".png";
							String charset="UTF-8";
							try {
								generateQRCode(url, path, charset, 100, 200);
							} catch (WriterException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.out.println("QR Code Generated Successfully...");
							JOptionPane.showMessageDialog(getParent(), "Book succesfully added.","Success",JOptionPane.INFORMATION_MESSAGE);		
							reset();
					}
					} else {
						JOptionPane.showMessageDialog(getParent(), "Book not Added.","Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton_1_1 = new JButton("Reset");
		Image resetIcon=new ImageIcon(this.getClass().getResource("resetIcon.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnNewButton_1_1.setIcon(new ImageIcon(resetIcon));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				reset();
			}
		});
		btnNewButton_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton_1_2 = new JButton("Cancel");
		Image img=new ImageIcon(this.getClass().getResource("red-x-mark-transparent-background-3.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnNewButton_1_2.setIcon(new ImageIcon(img));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnNewButton_1_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btn_add_author = new JButton("New");
		btn_add_author.setFont(new Font("Verdana", Font.PLAIN, 13));
		btn_add_author.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new AddForm("author").setVisible(true);
			}
		});
		
		
		JButton btn_add_subject = new JButton("New");
		btn_add_subject.setFont(new Font("Verdana", Font.PLAIN, 13));
		btn_add_subject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new AddForm("subject").setVisible(true);
			}
		});
		
		
		JButton btn_add_publisher = new JButton("New");
		btn_add_publisher.setFont(new Font("Verdana", Font.PLAIN, 13));
		btn_add_publisher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new AddForm("publisher").setVisible(true);
			}
		});
		
		
		JButton btn_add_category = new JButton("New");
		btn_add_category.setFont(new Font("Verdana", Font.PLAIN, 13));
		btn_add_category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new AddForm("category").setVisible(true);
			}
		});
		
	//Waste QR-BarCode;	
		lblNewLabel_2 = new JLabel("");
		String id=textField.getText();
		ImageIcon imgIcon=new ImageIcon(System.getProperty("user.home") + "\\Desktop\\"+id+".png");
		//lblNewLabel_2.setIcon(imgIcon);
		Image img11=imgIcon.getImage();
		Image resize=img11.getScaledInstance(200, 68, Image.SCALE_DEFAULT);
		ImageIcon resizedImg=new ImageIcon(resize);
		lblNewLabel_2.setIcon(resizedImg);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_2)
						.addComponent(lblNewLabel_1_1_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1_1_4))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(textField_1, Alignment.LEADING)
							.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(comboBox_1, Alignment.LEADING, 0, 182, Short.MAX_VALUE)
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, 182, 182, 182)
							)
		            .addGap(30) 
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btn_add_author, Alignment.LEADING)
								.addComponent(btn_add_subject, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_add_publisher, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_add_category, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								)
								)

//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
//								.addComponent(btn_test))
//							.addComponent(textField, 182, 182, 182)
//								)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1_2)
								.addComponent(lblNewLabel_1_1_4_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_4_1_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
								)
							)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(118)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)))
					.addGap(86))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(338, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(335))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(116)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
					.addComponent(btnNewButton_1_1)
					.addGap(141)
					.addComponent(btnNewButton_1_2)
					.addGap(127))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(lblNewLabel)
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btn_add_author)
								.addComponent(lblNewLabel_1_2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btn_add_subject)
						.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_4_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btn_add_publisher)
						.addComponent(lblNewLabel_1_1_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1_4_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btn_add_category)
						.addComponent(lblNewLabel_1_1_4, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(133)
					)
		);
		contentPane.setLayout(gl_contentPane);
	}
}
