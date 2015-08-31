package com.xc.financial.test;

import javax.swing.CellEditor;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class test {
  public static void main(String[] argv) throws Exception {
    MyTable table = new MyTable();
    DefaultTableModel model = (DefaultTableModel) table.getModel();

    model.addColumn("A", new Object[1]);
    model.addColumn("B", new Object[1]);

    String[] values = {"a","b"};
   table.setComboCell(0, 1, new MyComboBoxEditor(values));
 
 //   TableColumn col = table.getColumnModel().getColumn(0);
 //   col.setCellEditor(new MyComboBoxEditor(values));
 //   col.setCellEditor(new MyComboBoxRenderer(values));
  //  table.setModel(model);
  //  table.updateUI();
    JScrollPane jp=new JScrollPane(table);
    jp.setViewportView(table);
    jp.setSize(400,300);
    JFrame jf=new JFrame();
    jf.getContentPane().add(jp);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setSize(400,300);
    jf.setVisible(true);

  }
}

class MyTable extends JTable{
    int myRow=-1,myCol=-1;
    TableCellEditor myEditor;
    public void setComboCell(int r,int c,TableCellEditor ce){
        this.myRow=r;
        this.myCol=c;
        this.myEditor=ce;
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        System.out.println(row+","+column+";"+myRow+","+myCol+","+myEditor);
        if(row==myRow&&column==myCol&&myEditor!=null)
            return myEditor;
        return super.getCellEditor(row, column);
    }
    
}

class MyComboBoxEditor extends DefaultCellEditor {
  public MyComboBoxEditor(String[] items) {
    super(new JComboBox(items));
  }
}