package SalesBill;

import java.sql.*;
import java.util.logging.*;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.event.*;

public class Sales {

	private JFrame frmBillingSection;
	private JTextField txtpcode;
	private JTextField txtpname;
	private JTextField txtprice;
	private JTextField txtamount;
	private JTextField txtotal;
	private JTextField txtpay;
	private JTextField txtbal;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales window = new Sales();
					window.frmBillingSection.setLocationRelativeTo(null);
					window.frmBillingSection.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sales() {
		initialize();
	}
	
	/*
	*Declaring JDBC variables
	*/
	
	Connection con;
	PreparedStatement st;
	ResultSet rs;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmBillingSection = new JFrame();
		frmBillingSection.setTitle("Billing Section");
		frmBillingSection.setBounds(100, 100, 1316, 743);
		frmBillingSection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBillingSection.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sales", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(38, 41, 712, 260);
		frmBillingSection.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product code");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(15, 48, 127, 20);
		panel.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Product Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(186, 48, 139, 20);
		panel.add(lblName);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantity.setBounds(370, 48, 108, 20);
		panel.add(lblQuantity);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrice.setBounds(475, 48, 69, 20);
		panel.add(lblPrice);
		
		JLabel lblTotal = new JLabel("Amount");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotal.setBounds(597, 48, 69, 20);
		panel.add(lblTotal);
		
		txtpcode = new JTextField();
		txtpcode.setForeground(Color.MAGENTA);
		txtpcode.setBackground(new Color(124, 252, 0));
		txtpcode.addKeyListener(new KeyAdapter() {
			
			/*
			*Fetching records from database 'Product' table, when the product Id is entered
			*/
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					String pcode=txtpcode.getText();
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","welcome");
						st=con.prepareStatement("select * from product where id = ?");
						st.setString(1,pcode);
						rs=st.executeQuery();
						
						if(rs.next() == false)
						{
							JOptionPane.showMessageDialog(null,"Product code not found");
						}
						
						else
						{
							String pname=rs.getString("prodname");
							String price=rs.getString("price");
							
							txtpname.setText(pname.trim());
							txtprice.setText(price.trim());
							
						}
						
						
						
					}
					catch (ClassNotFoundException ex) {
						// TODO Auto-generated catch block
						Logger.getLogger(Sales.class.getName()).log(Level.SEVERE,null,ex);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		/*
		 * Initializing text fields
		 */
		txtpcode.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpcode.setBounds(15, 98, 146, 38);
		panel.add(txtpcode);
		txtpcode.setColumns(10);
		
		txtpname = new JTextField();
		txtpname.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpname.setColumns(10);
		txtpname.setBounds(186, 98, 146, 38);
		panel.add(txtpname);
		
		txtprice = new JTextField();
		txtprice.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtprice.setColumns(10);
		txtprice.setBounds(475, 98, 90, 38);
		panel.add(txtprice);
		
		txtamount = new JTextField();
		txtamount.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtamount.setColumns(10);
		txtamount.setBounds(597, 98, 100, 38);
		panel.add(txtamount);
		
		JSpinner txtqty = new JSpinner();
		txtqty.addChangeListener(new ChangeListener() {
			
			/*
			*Calculating the value of amount based on the quantity purchased.
			*/
			
			public void stateChanged(ChangeEvent e) {
				int qty = Integer.parseInt(txtqty.getValue().toString());
				int price=Integer.parseInt(txtprice.getText());
				
				int tot=qty*price;
				
				txtamount.setText(String.valueOf(tot));
				
				
				
				
			}
		});
		
		 
		
		
		txtqty.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtqty.setBounds(370, 98, 52, 32);
		panel.add(txtqty);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		/*
		*Action required for "Add" button
		*/
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				if(txtpcode.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Select the product to add");
					txtpcode.requestFocus(); 
					
				}
				else if(txtamount.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Select the Quantity of the product");
					txtqty.requestFocus(); 
				
			
				}
				else
				{
				DefaultTableModel model = new DefaultTableModel();
				model = (DefaultTableModel)table_2.getModel();
				model.addRow(new Object[]
						{
								txtpcode.getText(),
								txtpname.getText(),
								txtqty.getValue().toString(),
								txtprice.getText(),
								txtamount.getText(),
						
						
						});
				
				
						int sum = 0;
				
						for(int i = 1;i<table_2.getRowCount();i++)
							{ 
					
								sum = sum + Integer.parseInt(table_2.getValueAt(i,4).toString().trim());
					
							}
				
						txtotal.setText(Integer.toString(sum));
				
						txtpcode.setText("");
						txtpname.setText("");
						txtprice.setText("");
						txtamount.setText("");
				
						txtpcode.requestFocus(); 
					}
				}
				
			
		});
		btnNewButton.setBounds(569, 187, 115, 29);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(127, 255, 212));
		panel_1.setBounds(832, 16, 373, 279);
		frmBillingSection.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Total");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(179, 16, 69, 20);
		panel_1.add(lblNewLabel_1);
		
		txtotal = new JTextField();
		txtotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtotal.setBounds(130, 52, 146, 26);
		panel_1.add(txtotal);
		txtotal.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pay");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(179, 94, 69, 20);
		panel_1.add(lblNewLabel_1_1);
		
		txtpay = new JTextField();
		txtpay.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpay.setColumns(10);
		txtpay.setBounds(130, 130, 146, 26);
		panel_1.add(txtpay);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Balance");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(179, 184, 69, 20);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtbal = new JTextField();
		txtbal.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtbal.setColumns(10);
		txtbal.setBounds(130, 220, 146, 26);
		panel_1.add(txtbal);
		
		table_2 = new JTable();
		table_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"Product Id", "Product Name", "Quantity", "Price", "amount"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.setBackground(new Color(255, 215, 0));
		table_2.setBounds(38, 368, 712, 303);
		frmBillingSection.getContentPane().add(table_2);
		
		JTextArea txtbill = new JTextArea();
		txtbill.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtbill.setBounds(832, 347, 373, 324);
		frmBillingSection.getContentPane().add(txtbill);
		
		
		JButton btnNewButton_1 = new JButton("Print Bill");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			/*
			*This code determines the action required, when "print bill" button is pressed.
			*/
			
			public void actionPerformed(ActionEvent e) {
				
				if(txtpay.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Enter the paid Amount");
					txtpay.requestFocus(); 
				}
				else
				{
				balance();
				String total = txtotal.getText();
				String pay = txtpay.getText();
				String bal = txtbal.getText();
				
				
				DefaultTableModel model = new DefaultTableModel();
				
				model = (DefaultTableModel)table_2.getModel();
				
				txtbill.setText(txtbill.getText() + "**************************************************************\n");
				txtbill.setText(txtbill.getText() + "*****                Sales Bill                          *****\n");
				txtbill.setText(txtbill.getText() + "**************************************************************\n");
				
				txtbill.setText(txtbill.getText() + "Product" + "\t" + "Price" + "\t" + "Amount" + "\n");
				
				for(int i = 1;i<table_2.getRowCount();i++)
				{
					
					String pname = (String)model.getValueAt(i, 1);
					String price = (String)model.getValueAt(i, 3);
					String amount = (String)model.getValueAt(i, 4);
					
					txtbill.setText(txtbill.getText() + pname + "\t" + price + "\t" + amount + "\n");
					
				}
				
				txtbill.setText(txtbill.getText() + "\n");
				txtbill.setText(txtbill.getText() + "\n");
				txtbill.setText(txtbill.getText() + "\t" + "\t" + "Subtotal :" + total + "\n");
				txtbill.setText(txtbill.getText() + "\t" + "\t" + "Pay :" + pay + "\n");
				txtbill.setText(txtbill.getText() + "\t" + "\t" + "Balance :" + bal + "\n");
				
				txtbill.setText(txtbill.getText() + "**************************************************************\n");
				txtbill.setText(txtbill.getText() + "*****      Thank you come again                          *****\n");
			}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(972, 302, 115, 29);
		frmBillingSection.getContentPane().add(btnNewButton_1);
		
		
	}
	
	/*
	 * Method to calculate the balance Amount.
	 */
	
	public void balance() {
		
		int total = Integer.parseInt(txtotal.getText());
		int pay = Integer.parseInt(txtpay.getText());
		
		int balance = pay - total;
		
		txtbal.setText(String.valueOf(balance));
	}
	
	
}
