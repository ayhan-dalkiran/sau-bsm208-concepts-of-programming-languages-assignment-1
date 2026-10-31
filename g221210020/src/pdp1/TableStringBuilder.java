/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * SimulationWriter sınıfı tarafında çıktıyı tablo olarak yazdırmayı kolaylaştırmak
 * için oluşturulan sınıf.
 * </p>
 */
package pdp1;

import java.util.ArrayList;

public class TableStringBuilder {
	protected final ArrayList<ArrayList<String>> columnRowMatrix = new ArrayList<ArrayList<String>>(0);
	protected final ArrayList<Integer> columnWidthList = new ArrayList<Integer>(0);
	protected final StringBuilder builder = new StringBuilder();
	protected int rowsActive = 0;
	protected int columnsActive = 0;
	protected String verticalCellDivider = "";
	
	public String getVerticalCellDivider() {
		return verticalCellDivider;
	}

	public void setVerticalCellDivider(String verticalCellDivider) {
		this.verticalCellDivider = verticalCellDivider;
	}

	protected void ensureActiveColumnWidths() {
		this.columnWidthList.ensureCapacity(columnsActive);
		
		for (int i = columnWidthList.size(); i <= this.columnsActive; i++)
			this.columnWidthList.add(0);
	}
	
	protected void ensureActiveCells() {
		this.columnRowMatrix.ensureCapacity(this.columnsActive);
		
		for (int i = columnRowMatrix.size(); i <= this.columnsActive; i++)
			this.columnRowMatrix.add(new ArrayList<String>(this.rowsActive));
		
		for (var column : this.columnRowMatrix) {
			column.ensureCapacity(this.rowsActive);
			for (int i = column.size(); i <= this.rowsActive; i++)
				column.add("");
		}
	}
	
	protected void resetBuilder() {
		this.builder.setLength(0);
	}
	
	protected void buildString() {
		this.resetBuilder();
		
		for (int i = 0; i < this.rowsActive; i++) {
			for (int j = 0; j < this.columnsActive; j++) {
				var value = this.columnRowMatrix.get(j).get(i);
				this.builder.append(value);
				this.builder.append(" ".repeat(this.columnWidthList.get(j) - value.length()));
				this.builder.append(this.verticalCellDivider);
			}
			this.builder.setLength(this.builder.length() - this.verticalCellDivider.length());
			this.builder.append(System.lineSeparator());
		}
		this.builder.setLength(this.builder.length() - System.lineSeparator().length());
	}
	
	protected void calculateColumnWidth(int column) {
		int maxWidth = 0;
		
		for (var value : this.columnRowMatrix.get(column)) {
			if (value.length() > maxWidth) {
				maxWidth = value.length();
			}
		}
		
		this.columnWidthList.set(column, maxWidth);
	}
	
	public TableStringBuilder(String verticalCellDivider) {
		this.verticalCellDivider = verticalCellDivider;
	}
	
	public void setActiveSize(int rowSize, int columnSize) {
		this.rowsActive = rowSize;
		this.columnsActive = columnSize;
		this.ensureActiveColumnWidths();
		this.ensureActiveCells();
	}
	
	public void setCellValue(int row, int column, String value) {
		this.columnRowMatrix.get(column).set(row, value);
		
		this.calculateColumnWidth(column);
	}
	
	@Override
	public String toString() {
		this.buildString();
		return this.builder.toString();
	}
}
