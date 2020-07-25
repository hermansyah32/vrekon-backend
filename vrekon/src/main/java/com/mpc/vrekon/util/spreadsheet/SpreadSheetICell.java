package com.mpc.vrekon.util.spreadsheet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellReference;

import java.math.BigDecimal;
import java.util.Date;

public class SpreadSheetICell {
    private Cell cell;
    private CellReference reference;

    public SpreadSheetICell(Cell cell, CellReference reference){
        this.cell = cell;
        this.reference = reference;
    }

    public String getStringValue(){
        try {
            if (cell == null)
                return null;
            if (cell.getCellTypeEnum() == CellType.BLANK)
                return null;
            else {
                return cell.getStringCellValue();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public RichTextString getRichTextValue(){
        try {
            if (cell == null)
                return null;
            if (cell.getCellTypeEnum() == CellType.BLANK)
                return null;
            else {
                return cell.getRichStringCellValue();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public double getDoubleValue(){
        try {
            if (cell == null)
                return 0d;
            if (cell.getCellTypeEnum() == CellType.BLANK)
                return 0d;
            else {
                return cell.getNumericCellValue();
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0d;
        }
    }

    public BigDecimal getBigDecimalValue(){
        try {
            if (cell == null)
                return null;
            if (cell.getCellTypeEnum() == CellType.BLANK)
                return null;
            else {
                return BigDecimal.valueOf(cell.getNumericCellValue());
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Integer getIntegerValue(){
        try {
            if (cell == null)
                return null;
            if (cell.getCellTypeEnum() == CellType.BLANK)
                return null;
            else {
                return (int) cell.getNumericCellValue();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean getBooleanValue(){
        try {
            if (cell == null)
                return false;
            if (cell.getCellTypeEnum() == CellType.BLANK)
                return false;
            else {
                return cell.getBooleanCellValue();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Date getDateValue(){
        try {
            if (cell == null)
                return null;
            if (cell.getCellTypeEnum() == CellType.BLANK)
                return null;
            else {
                return cell.getDateCellValue();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getValue(){
        try
        {
            if (cell == null)
                return "";
            switch (cell.getCellTypeEnum())
            {
                case STRING:
                    return cell.getStringCellValue();

                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());

                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());

                case BLANK:
                    return "";

                case ERROR:
                    return String.valueOf(cell.getErrorCellValue());

                default:
                    return "";
            }
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public void setValue(String value){
        try {
            if (cell == null)
                throw new Exception("null cell");
            cell.setCellValue(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setValue(boolean value){
        try {
            if (cell == null)
                throw new Exception("null cell");
            cell.setCellValue(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setValue(Date value){
        try {
            if (cell == null)
                throw new Exception("null cell");
            cell.setCellValue(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setValue(RichTextString value){
        try {
            if (cell == null)
                throw new Exception("null cell");
            cell.setCellValue(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setValue(double value){
        try {
            if (cell == null)
                throw new Exception("null cell");
            cell.setCellValue(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setValue(BigDecimal value){
        try {
            if (cell == null)
                throw new Exception("null cell");
            cell.setCellValue(value.toPlainString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setValue(Integer value){
        try {
            if (cell == null)
                throw new Exception("null cell");
            cell.setCellValue(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Cell getCell(){
        return cell;
    }
}
