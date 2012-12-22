/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Turma;
import senai.util.Tupla;
import senai.util.date.DateUtil;

/**
 *
 * @author Sergio
 */
public class Export {

    private Turma turma;
    
    public Export(Turma t) {
        turma = t;
    }
    
    public void generate() throws Exception {
        Workbook wb = new XSSFWorkbook();
        
        Map<Integer, Map<Date, Tupla<Aula,Aula>>> meses = turma.getHorario().separaHorarioEmMeses();
        
        for (Integer mes : meses.keySet()) {
            createSheet(wb, DateUtil.getNomeMes(mes));
        }
        
        save(wb);
    }
    
    private Sheet createSheet(Workbook wb, String mes) {
        return wb.createSheet(WorkbookUtil.createSafeSheetName(mes) );
    }
    
    private void createHeaderCell(Workbook wb, Row row, short column, String diaSemana) {
        Cell celula = row.createCell(column);
        CellStyle estilo = wb.createCellStyle();
        estilo.setAlignment(CellStyle.ALIGN_CENTER);
        estilo.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        
        celula.setCellValue(diaSemana);
        celula.setCellStyle(estilo);
    }
    
    private void createAulaCell(Workbook wb, Row row, short column, IndexedColors cor) {
        Cell celula = row.createCell(column);
        CellStyle estilo = wb.createCellStyle();
        estilo.setFillBackgroundColor(cor.getIndex() );
        estilo.setFillPattern(CellStyle.BIG_SPOTS);
        celula.setCellStyle(estilo);
    }
    
    private void save(Workbook wb) throws Exception {
        String dir = System.getProperty("user.home") + "\\Desktop\\";
        String file = dir + turma.getNome() + ".xlsx";
        
        try (FileOutputStream fo = new FileOutputStream(file)) {
            wb.write(fo);
        }
    }
    
    
}
