package importer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exceptions.SingletonException;

public class ImporterGUI implements ActionListener{

	int instanceOf;
	File fi;
	Importer imp;
	
	/* UI objects */
	private JFrame frm;
	private JLabel lblheader;
	private JLabel lblstatus;
	private JPanel pnlcontrol;
	private JFileChooser fc;
	
	public ImporterGUI(Importer imp){
		instanceOf++;
		if(instanceOf > 1)
			throw new SingletonException();

		this.imp = imp;
		
		/* GUI Operations */
		frm = new JFrame("Game of Life : File Selection");
		frm.setPreferredSize(new Dimension(400,400));
		frm.setLayout(new GridLayout(3,1));
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setResizable(false);
		
		lblheader = new JLabel("", JLabel.CENTER);
		lblstatus = new JLabel("", JLabel.CENTER);
		lblstatus.setSize(350,100);
		
		pnlcontrol = new JPanel();
		pnlcontrol.setPreferredSize(new Dimension(400,400));
		pnlcontrol.setLayout(new FlowLayout());
		
		frm.add(lblheader);
		frm.add(pnlcontrol);
		frm.add(lblstatus);
		
		lblheader.setText("Select a file.");
		
		fc = new JFileChooser();
		JButton btnchoose = new JButton("Open File");
		
		btnchoose.addActionListener(this);
		
		pnlcontrol.add(btnchoose);
		
		frm.pack();
		frm.setVisible(true);
			
	}
	
	public JFrame getFrame(){ return frm; }
	
	public JFileChooser getFileChooser(){ return fc; }
	
	public JLabel getStatusLabel(){return lblstatus;}
	
	public int getStatus(){ return fc.showOpenDialog(frm); }

	public void actionPerformed(ActionEvent e) {
		
		System.out.print("ImportGUI:\tAction Event 00% complete.\n");
		
		int status = fc.showOpenDialog(frm);		
		if(status == JFileChooser.APPROVE_OPTION){
			fi = fc.getSelectedFile();
			lblheader.setText("File Selected : " + fi.getName());
			frm.setVisible(false);
			frm.dispose();
		} else {
			lblheader.setText("Open command cancelled.");
		}
		
		System.out.print("ImportGUI:\tAction Event 50% complete.\n");
		imp.setSelectedFile(fi);
	}
}
