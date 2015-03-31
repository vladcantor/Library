package library.ctrl;
import java.util.List;

import model.Subscriber;

import javax.swing.table.AbstractTableModel;




public class LibrarySubscriberTableModel extends AbstractTableModel{
	
	private List<Subscriber> libRep;
	private String[] cols = {"ID", "Name" };
	
	
	public LibrarySubscriberTableModel(List<Subscriber> l)
	{
		this.libRep = l;
	}

	@Override
	public String getColumnName(int column) {
	   return cols[column];
	}
	
	@Override
	public int getColumnCount() {
		
		return cols.length;
	}

	@Override
	public int getRowCount() {
		
		return this.libRep.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex){
        case 0: return libRep.get(rowIndex).getId();
        case 1: return libRep.get(rowIndex).getName();
     }
     return null;  
	}

	 public void setSubscribers(List<Subscriber> parts){
	        this.libRep=parts;
	        fireTableDataChanged();
	    }
	 
	 public void addSubscriber(Subscriber s)
	 {
		 this.libRep.add(s);
	 }
	 
	 public Subscriber get(int index) {
	        return libRep.get(index);
	    }
}
