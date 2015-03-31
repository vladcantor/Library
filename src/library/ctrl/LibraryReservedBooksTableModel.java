package library.ctrl;

import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Book;
import model.Reserved;

public class LibraryReservedBooksTableModel extends AbstractTableModel {

	List<Book> libRepo;
	String[] cols = {"Id", "Title"};
	
	public LibraryReservedBooksTableModel(List<Book> l)
	{
		this.libRepo = l;
	}
	
	@Override
	public String getColumnName(int column) {
	   return cols[column];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.libRepo.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex){
        case 0: return libRepo.get(rowIndex).getId();
        case 1: return libRepo.get(rowIndex).getTitle();
     }
     return null;  
	}
	
	 public void setReservedBooks(List<Book> notReserved){
	        this.libRepo=notReserved;
	        fireTableDataChanged();
	    }
	 
	 
	 public Book get(int index) {
	        return libRepo.get(index);
	    }



}
