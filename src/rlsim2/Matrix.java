/*
 */
package rlsim2;

import javax.swing.table.AbstractTableModel;

/**
 * Custom table model for use in gui.MainFrame. 
 * It is used in both reward and Q matrices of gui.MainFrame. The first column is used
 * as the x-axis for specifying states.
 * Specialized behavior in this version:
 *   -getStates() returns the list of states in the order they are displayed.
 *   -getData() returns the double[][] containing matrix data.
 *   -setStates(String[] states) sets the state space. 
 *   -setData(String[][] data) sets matrix content.
 *   -findRow(String rowName) returns the index of the specified row.
 *   -getDoubleAt(int rowIndex, int columnIndex) returns a value from the matrix parsed to Double. 
 * @author Alessandro Bisiani
 * @version v1.0 - 1 May 2015
 */
public class Matrix extends AbstractTableModel{
    
    //Refactor to be initialized through the new experiment functionality. Initializing this way will prevent the table being used off the bat.
    private String[] states = {"state1","state2","state3","state4","state5","state6","state7","state8","state9"};
    private String[][] matrix =                            {{"state1","0","0","0","0","0","0","0","0","0"}, 
                                                            {"state2","0","0","0","0","0","0","0","0","0"}, 
                                                            {"state3","0","0","0","0","0","0","0","0","0"},
                                                            {"state4","0","0","0","0","0","0","0","0","0"},
                                                            {"state5","0","0","0","0","0","0","0","0","0"},
                                                            {"state6","0","0","0","0","0","0","0","0","0"},
                                                            {"state7","0","0","0","0","0","0","0","0","0"},
                                                            {"state8","0","0","0","0","0","0","0","0","0"},
                                                            {"state9","0","0","0","0","0","0","0","0","0"}};
    
    
    /**
     * Constructs an instance of Matrix setting its internal state to reflect the 
     * data and states parameters.
     * @param data
     * @param states 
     */
    public Matrix(String[][] data, String[] states){
        matrix = data;
        this.states = states;
        //TEST
        //System.out.println("Custom model created");
    }
    
    /*
    public double getValueAt(int x, int y){
        double v = 0;
        try {
            v = matrix[y][x];
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(new JFrame(), "Matrix. getValueAt() trying to get values out of bounds.");
            throw ex;
        }
        return v;
    
    }
    */
    /**
     * Gets an internal representation of the state space.
     * @return  A representation of the state space.
     */
    public String[] getStates(){
        return states;
    }
    /**
     * Gets current model data, equivalent to the state of the matrix.
     * @return  All matrix data excluding state names.
     */
    public String[][] getData(){
        return matrix;
    }
    /**
     * Sets the internal states field.
     * @param states    An array of states matching that of the displayed columns in content and order.
     */
    public void setStates(String[] states){
        this.states = states;
    }
    /**
     * Sets the internal data field.
     * @param data  The matrix content.
     */
    public void setData(String[][] data){
        matrix = data;
    }
    /**
     * Gets the number of rows in this model.
     * @return  The number of rows in this model.
     */
    @Override
    public int getRowCount() {
        return matrix.length;
    }
    /**
     * Gets the number of columns in this model. 
     * Note: This includes the first column populated by row headers.
     * @return  The number of columns.
     */
    @Override
    public int getColumnCount() {
        return matrix[0].length;
    }
    /**
     * Gets the name of the column at the specified index.
     * @param columnIndex   Index of the desired column.
     * @return              Name of the desired column.
     */
    @Override
    public String getColumnName(int columnIndex){
        return states[columnIndex];
    }
    /**
     * Checks whether a cell is editable.
     * Returns true for all cells not in the first column.
     * @param rowIndex      Index of the cell row.
     * @param columnIndex   Index of the cell column.
     * @return  true for editable cells.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        if(columnIndex==0){
            return false;
        } else{
            return true;
        }
    }
    /**
     * Gets the Object stored at the specified indices.
     * @param rowIndex      Index of the desired row.
     * @param columnIndex   Index of the desired column.
     * @return  The Object at (rowIndex,columnIndex)
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex];
    }
    /**
     * Sets the Object at the specified indices.
     * @param o             Object to set at (rowIndex, columnIndex).
     * @param rowIndex      Index of the desired row.
     * @param columnIndex   Index of the desired column.
     */
    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex){
            String s = String.valueOf(o);
            matrix[rowIndex][columnIndex] = s;
    }
    /**
     * Gets the column index of the column with the specified name.
     * @param columnName    Name of the desired column.
     * @return              Index of the desired column.
     */
    @Override
    public int findColumn(String columnName){
        int count = getColumnCount();
        for(int i=0;i<count;i++){
            String name = getColumnName(i);
            if(columnName.equals(name)){
                return i;
            }
        }
        return -1;
    }
    /**
     * Returns the index of the specified row.
     * Used for implementations of a JTable as a matrix where the first column contains row headers.
     * @param rowName   Name of the row header found in column 0.
     * @return          Index of the specified row, or -1 if that row does not exist.
     */
    public int findRow(String rowName){
        for(int i=0;i<getRowCount();i++){
            String name = (String)getValueAt(i,0);
            if(rowName.equals(name)){
                return i;
            }
        }
        return -1;
    }
    /**
     * Gets the value at coordinates in the matrix as a double.
     * @param rowIndex      Index of the matrix row.
     * @param columnIndex   Index of the matrix column.
     * @return              The value of the matrix at the indeces passed.
     */
    public double getDoubleAt(int rowIndex, int columnIndex){
        return Double.parseDouble(matrix[rowIndex][columnIndex]);
    }
    
}
