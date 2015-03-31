package library.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Book;
import model.Rented;
import model.Reserved;

public class UnReserveTableModel extends AbstractTableModel {
	private HashMap<Integer, Reserved> reservedRepo;
	private String[] cols = {"bid", "Title", "sid", "Name"};
	public UnReserveTableModel(HashMap<Integer, Reserved> data) {
		this.reservedRepo = data;
	}
	@Override
	public String getColumnName(int column) {
	   return this.cols[column];
	}

	@Override
	public int getColumnCount() {
		
		return this.cols.length;
	}

	@Override
	public int getRowCount() {
		
		return this.reservedRepo.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int coloumnIndex) {
		List<Reserved> list = new ArrayList<Reserved>(this.reservedRepo.values());
		switch (coloumnIndex) {
		case 0:
			
			return list.get(rowIndex).getReservedBook().getId();
			
		case 1:
			return list.get(rowIndex).getReservedBook().getTitle();
			
		case 2:
			return list.get(rowIndex).gerReserver().getId();
		case 3:
			return list.get(rowIndex).gerReserver().getName();

		default:
			break;
		}
		return null;
	}
	 public void setUnReservedBooks(HashMap<Integer, Reserved> parts){
	        this.reservedRepo=parts;
	        fireTableDataChanged();
	    }
	 public Reserved get(int index)
	 {
		 List<Reserved> list = new ArrayList<Reserved>(this.reservedRepo.values()); 
		 return list.get(index);
	 }
	 public void addBook(Reserved r)
	 {
		 this.reservedRepo.put(r.getReservedBook().getId(), r);
	 }

}
