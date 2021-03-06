/*  +__^_________,_________,_____,________^-.-------------------,
 *  | |||||||||   `--------'     |          |                   O
 *  `+-------------USMC----------^----------|___________________|
 *    `\_,---------,---------,--------------'
 *      / X MK X /'|       /'
 *     / X MK X /  `\    /'
 *    / X MK X /`-------'
 *   / X MK X /
 *  / X MK X /
 * (________(                @author m.c.kunkel
 *  `------'
*/
package database.ui.panels;

import java.awt.BorderLayout;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import database.objects.StatusChangeDB;
import database.ui.SQLTableModel;
import database.utils.NumberConstants;
import database.utils.StringConstants;

public class SQLPanel extends JPanel {

	private JTable aTable;
	private SQLTableModel tableModel;

	final int space = NumberConstants.BORDER_SPACING;
	Border spaceBorder = null;
	Border titleBorder = null;

	public SQLPanel() {
		initializeVariables();
		constructLayout();
		initializeTableAlignment();
		initializeHeaderAlignment();
		// setJTableColumnsWidth(this.aTable, 480, 10, 5, 7, 5, 3, 20, 20, 1);
	}

	private void initializeTableAlignment() {
		DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
		tableCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		// int gapWidth = 10;
		// int gapHeight = 5;
		// this.aTable.setIntercellSpacing(new Dimension(gapWidth, gapHeight));

		this.aTable.getColumnModel().getColumn(0).setCellRenderer(tableCellRenderer);
		this.aTable.getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		this.aTable.getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		this.aTable.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		this.aTable.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		this.aTable.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		this.aTable.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);

	}

	private void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
		double total = 0;
		System.out.println("preferred width = " + tablePreferredWidth);
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			total += percentages[i];
		}

		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
		}
	}

	private void initializeHeaderAlignment() {
		DefaultTableCellRenderer headerCellRenderer = new DefaultTableCellRenderer();
		headerCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		this.aTable.getTableHeader().setDefaultRenderer(headerCellRenderer);
	}

	private void constructLayout() {
		setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		setLayout(new BorderLayout());
		add(new JScrollPane(aTable), BorderLayout.CENTER);
	}

	private void initializeVariables() {
		this.spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		this.titleBorder = BorderFactory.createTitledBorder(StringConstants.SQL_FORM_LABEL);
		this.tableModel = new SQLTableModel();
		this.aTable = new JTable(tableModel);
	}

	public void setTableModel(TreeSet<StatusChangeDB> wireList) {
		this.tableModel.setWireSet(wireList);
	}

	public void updateTable() {
		this.tableModel.updateTable();
	}
}
