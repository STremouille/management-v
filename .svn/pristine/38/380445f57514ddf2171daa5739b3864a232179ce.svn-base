package view.cases;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import start.ManagementVinci;
import model.Case;
import model.Factory;

public class CaseDeletedView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Case> cList;
	private Factory factory;
	private CaseTableModel ctm;


	/**
	 * Create the frame.
	 */
	public CaseDeletedView(Factory f) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CaseDeletedView.class.getResource("/img/logo.png")));
		this.factory=f;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 1500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		cList = factory.getDeletedCases();
		
		ctm = new CaseTableModel(factory, cList, true);
		
		table = new JTable(ctm);
		table.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, 12));
		table.getTableHeader().setFont(new Font(ManagementVinci.fontName, Font.BOLD, 12));
		
		table.addMouseListener(new CaseDeletedMouseAdaptater());
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}
	
	class CaseDeletedMouseAdaptater extends MouseAdapter{

		@Override
		public void mouseReleased(MouseEvent e) {
			JTable tableC = table;
			int rC = tableC.rowAtPoint(e.getPoint());
	        if (rC >= 0 && rC < tableC.getRowCount()) {
	            tableC.setRowSelectionInterval(rC, rC);
	        } else {
	            tableC.clearSelection();
	        }

	        final int rowindexC = tableC.getSelectedRow();
	        if (rowindexC < 0)
	            return;
	        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
	            final JPopupMenu popup = new JPopupMenu();
	            JMenuItem mh = new JMenuItem("Annuler la suppression");
	            mh.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						factory.unDeleteCaseId(ctm.getCaseList().get(rowindexC));
						ctm.fireTableDataChangedForDeletedCase();
					}
				});
	            popup.add(mh);
	            JMenuItem mDelete = new JMenuItem("Supprimer définitivement "+ctm.getCaseList().get(rowindexC));
	            mDelete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(popup, "Êtes vous sur de supprimer définitivement l'affaire ?")==JOptionPane.OK_OPTION){
							factory.deleteDefinitely(ctm.getCaseList().get(rowindexC));
							ctm.fireTableDataChangedForDeletedCase();
						} 
						
					}
				});
	            popup.add(mDelete);
	            popup.show(e.getComponent(), e.getX(), e.getY());
	        }
		}
		
	}

}
