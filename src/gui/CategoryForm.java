package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import share.Request;
import share.RequestHead;

/*
Author: Ziqi Tan
*/
public class CategoryForm extends JFrame {
	
	private int courseID;
	
	public CategoryForm(int _courseID) {
		this.courseID = _courseID;
		System.out.println("A form is creating.");
		setTitle("Course form");
					
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = (int)(screenSize.getWidth() * 0.36);
		int frameHeight = (int)(screenSize.getHeight() * 0.7);
		setSize(frameWidth, frameHeight);
		      		
		int hGap = 20;
		int vGap = 50;
		
		setLocationRelativeTo(null);   // this will center your frame
		
		setVisible(true);
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(null);
		
		int labelX = (int)(frameWidth*0.2);
		int labelY = (int)(frameHeight*0.1);
		
		int labelWidth = 100;
		int labelHeight = 25;
		JLabel nameLabel = new JLabel("Category name: ");
		nameLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
		formPanel.add(nameLabel);
		
		JLabel weightLabel = new JLabel("Weight: ");
		weightLabel.setBounds(labelX, labelY + vGap, labelWidth, labelHeight);
		// formPanel.add(weightLabel);
		
		JLabel despLabel = new JLabel("Description: ");
		despLabel.setBounds(labelX, labelY + vGap * 3, labelWidth, labelHeight);
		formPanel.add(despLabel);
		
		int textFieldWidth = 130;
		JTextField nameField = new JTextField(20);
		nameField.setBounds(labelX + nameLabel.getWidth() + hGap, nameLabel.getY(), textFieldWidth, labelHeight);
		formPanel.add(nameField);	
		
		JTextField weightField = new JTextField(20);
		weightField.setBounds(labelX + weightLabel.getWidth() + hGap, weightLabel.getY(), textFieldWidth, labelHeight);
		// formPanel.add(weightField);
		
		JLabel weightInputSample = new JLabel("Input: 10 or 10.00, which means 10% of weight.");
		weightInputSample.setBounds(labelX + weightLabel.getWidth() + hGap, weightField.getY() + weightField.getHeight(), 300, labelHeight);
		// formPanel.add(weightInputSample);
		
		JTextArea despArea = new JTextArea();
		despArea.setLineWrap(true);
		JScrollPane textAreaScrollPane = new JScrollPane(despArea);
		textAreaScrollPane.setBounds(labelX + despLabel.getWidth() + hGap, despLabel.getY(), (int) (textFieldWidth * 2.3), 180);
		formPanel.add(textAreaScrollPane);
		
		int buttonWidth = 80;
		JButton submit = new JButton("Submit");
		submit.setBounds(labelX, despLabel.getY() + vGap, buttonWidth, labelHeight);
		formPanel.add(submit);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String cateName = nameField.getText();
				String cateWeight = weightField.getText();
				String cateDesp = despArea.getText();				
								
				if( cateName.length() == 0 || cateDesp.length() == 0 ) {
					System.out.println("Please fill the form.");
					JOptionPane.showMessageDialog(null, "Please fill the form.");
					return ;
				}
				
				// Request
				Request request = new Request(RequestHead.ADD_CATEGORY);
				request.addIds(courseID);
				request.addIds(null);
				request.addIds(null);
				request.addIds(null);
				
				request.addParams(cateName);
				request.addParams(cateDesp);
				
				FrontController.getInstance().dispatchRequest(request);
				
				JOptionPane.showMessageDialog(null, "Add/Updae successfully.");
				dispose();
			}
		});
					
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(labelX, despLabel.getY() + vGap * 2, buttonWidth, labelHeight);
		formPanel.add(cancel);
		cancel.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						dispose();
					}
				}					
		);
					
		add(formPanel);
		
	}
	
}
