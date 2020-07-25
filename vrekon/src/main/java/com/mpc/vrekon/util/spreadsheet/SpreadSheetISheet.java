package com.mpc.vrekon.util.spreadsheet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;

public class SpreadSheetISheet {
    private Sheet sheet;

    public SpreadSheetISheet(Sheet sheet){
        this.sheet = sheet;
    }

    public Sheet getSheet(){
        return sheet;
    }

    public SpreadSheetICell cell(String column, int row, boolean createIfNotExists){
        CellReference reference = new CellReference(column + String.valueOf(row));
        try{
            Row iRow = sheet.getRow(reference.getRow());
            if (iRow == null)
                if (createIfNotExists)
                    iRow = sheet.createRow(reference.getRow());
                else
                    throw new Exception("Row doesn't exists");
            Cell cell = iRow.getCell(reference.getCol(), Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
            if (cell == null)
                if (createIfNotExists)
                    cell = iRow.createCell(reference.getCol());
                else
                    throw new Exception("Cell doesn't exists");
             return new SpreadSheetICell(cell,reference);
        } catch (Exception e) {
            e.printStackTrace();
            return new SpreadSheetICell(null, reference);
        }
    }

    public SpreadSheetICell cell(Integer column, int row, boolean createIfNotExists){
        CellReference reference = new CellReference(row, column);
        try{
            Row iRow = sheet.getRow(reference.getRow());
            if (iRow == null)
                if (createIfNotExists)
                    iRow = sheet.createRow(reference.getRow());
                else
                    throw new Exception("Row doesn't exists");
            Cell cell = iRow.getCell(reference.getCol(), Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
            if (cell == null)
                if (createIfNotExists)
                    cell = iRow.createCell(reference.getCol());
                else
                    throw new Exception("Cell doesn't exists");
            return new SpreadSheetICell(cell,reference);
        } catch (Exception e) {
            e.printStackTrace();
            return new SpreadSheetICell(null, reference);
        }
    }

    public SpreadSheetICell cell(String address, boolean createIfNotExists){
        CellReference reference = new CellReference(address);
        try{
            Row iRow = sheet.getRow(reference.getRow());
            if (iRow == null)
                if (createIfNotExists)
                    iRow = sheet.createRow(reference.getRow());
                else
                    throw new Exception("Row doesn't exists");
            Cell cell = iRow.getCell(reference.getCol(), Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
            if (cell == null)
                if (createIfNotExists)
                    cell = iRow.createCell(reference.getCol());
                else
                    throw new Exception("Cell doesn't exists");
            return new SpreadSheetICell(cell,reference);
        } catch (Exception e) {
            e.printStackTrace();
            return new SpreadSheetICell(null, reference);
        }
    }
}
