package tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entities.Usuario;

public class UsuariosTableModel extends AbstractTableModel{
	private List<Usuario> lista;
	private String[] cabecalho = new String[1];
	
	public UsuariosTableModel(List<Usuario> lista, String cabecalho) {
		this.lista = lista;
		this.cabecalho[0] = cabecalho;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return cabecalho.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {	
		Usuario user = lista.get(rowIndex);
		
		switch (columnIndex) {
		case 0: {
			return user.getNome();
		}
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return cabecalho[column];
	}
}
