/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Docente;

/**
 *
 * @author Sergio
 */
public class ExportHorarioDocente {
    
    private Docente docente;
    private Workbook wb;
    
    private static final int COLUMN_LENGHT = 15;
    private static final int ROWS_LENGHT = (10 * 12);
    
    public ExportHorarioDocente(Docente docente) {
        this.docente = docente;
    }
    
    public void exportarHorarioDocente() throws Exception {
        wb = new XSSFWorkbook();
        
        Sheet sheet1 = ExportUtils.createSheet(wb, docente.getPrimeiroTurno().name() );        
        Sheet sheet2 = ExportUtils.createSheet(wb, docente.getSegundoTurno().name() );
        
        createCelulas(sheet1);
        createCelulas(sheet2);
        
        if (docente.getPrimeiroTurno().equals(docente.getSegundoTurno()))
            wb.removeSheetAt(1);
        
        ExportUtils.save(wb, docente.getNome() );
    }
    
    private void createCelulas(Sheet sheet) {
        Row[] rows = new Row[COLUMN_LENGHT + 1];

        for (short r = 0; r <= COLUMN_LENGHT; r++) {
            rows[r] = sheet.createRow(r);
            for (short c = 0; c < ROWS_LENGHT; c++) {
                rows[r].createCell(c);
            }
        }
        
        sheet.setColumnWidth(9, 9000);
        sheet.setColumnWidth(10, 9000);
        sheet.setColumnWidth(11, 9000);
        
        short block = 0;
        
        int i = 0;
        sheet.addMergedRegion(new CellRangeAddress(block + i, block + i, 0, 6));
        sheet.getRow(block + i).getCell(0).setCellValue("Janeiro");
        
        sheet.getRow(block + i).getCell(9).setCellValue("Unidade Curricular");
        sheet.getRow(block + i).getCell(10).setCellValue("Turma");
        sheet.getRow(block + i).getCell(11).setCellValue("Sala");
        
    }
}
