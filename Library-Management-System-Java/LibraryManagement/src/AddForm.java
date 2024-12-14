import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldId;
    private JTextField textFieldName;
    private String tableName;

    public AddForm(String tableName) {
        this.tableName = tableName;
        setTitle("Add " + tableName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        setLocationRelativeTo(null); // Center the dialog on the screen
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblHeading = new JLabel("Add new " + tableName);
        lblHeading.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblHeading.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblId = new JLabel("ID:");
        lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));

        textFieldId = new JTextField();
        textFieldId.setColumns(10);
        textFieldId.setEditable(false);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));

        textFieldName = new JTextField();
        textFieldName.setColumns(10);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToDatabase();
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(30)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblId)
                        .addComponent(lblName))
                    .addGap(30)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(textFieldId)
                        .addComponent(textFieldName, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addGap(30)
                            .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(30, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.CENTER, gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblHeading, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(10)
                    .addComponent(lblHeading)
                    .addGap(20)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblId)
                        .addComponent(textFieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(30)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave)
                        .addComponent(btnCancel))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        initializeDatabase();
    }

    private void initializeDatabase() {
        Connection con = DBInfo.conn();
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                                  "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                  "name VARCHAR(255) NOT NULL)";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(createTableQuery);

            String countQuery = "SELECT COUNT(*) FROM " + tableName;
            try (PreparedStatement ps = con.prepareStatement(countQuery); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    textFieldId.setText(String.valueOf(count + 1));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error initializing database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToDatabase() {
        String name = textFieldName.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name field must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection con = DBInfo.conn();
        String query = "INSERT INTO " + tableName + " (name) VALUES (?)";

        try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    textFieldId.setText(String.valueOf(generatedKeys.getInt(1)));
                }
                JOptionPane.showMessageDialog(this, tableName + " added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving to database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new AddForm("Author").setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
