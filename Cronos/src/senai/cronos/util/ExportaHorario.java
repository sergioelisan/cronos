/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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
public class ExportaHorario {

    private Turma turma;
    private Workbook wb;

    public ExportaHorario() {
    }

    public void exportarHorarioTurma(Turma turma) throws Exception {
        this.turma = turma;
        wb = new XSSFWorkbook();

        Sheet sheet1 = createSheet(turma.getNome() + " - 1º semestre");
        createStructure(sheet1);
        paintStructure(sheet1);
        setCellsData(sheet1, turma.getHorario().getPrimeiroSemestre());
        
        Sheet sheet2 = createSheet(turma.getNome() + " - 2º semestre");
        createStructure(sheet2);
        paintStructure(sheet2);
        setCellsData(sheet2, turma.getHorario().getSegundoSemestre());

        save(turma.getNome());
    }

    private void createStructure(Sheet sheet) {
        Row[] rows = new Row[22];

        for (short r = 0; r < 21; r++) {
            rows[r] = sheet.createRow(r);
            for (short c = 0; c < 31; c++) {
                rows[r].createCell(c);
                rows[r].setHeight((short) 380);
            }
        }

        adjustColumnsWidth(sheet);
        adjustMergedCells(sheet);

        Cell logo = rows[0].createCell((short) 0);
        logo.setCellValue("Escola Técnica SENAI Areias");

        Cell nomeTurma = rows[0].createCell((short) 26);
        nomeTurma.setCellValue(turma.getTurno().toString());

        Cell nucleo = rows[1].createCell((short) 0);
        nucleo.setCellValue(turma.getNucleo().getNome());
    }

    private void adjustMergedCells(Sheet sheet) {
        // header
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 26, 30));

        // nucleo
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 24));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 1));

        // mes 1
        sheet.addMergedRegion(new CellRangeAddress(4, 5, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));

        // mes 2
        sheet.addMergedRegion(new CellRangeAddress(7, 8, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 1));

        // mes 3
        sheet.addMergedRegion(new CellRangeAddress(10, 11, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 0, 1));

        // mes 4
        sheet.addMergedRegion(new CellRangeAddress(13, 14, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(15, 15, 0, 1));

        // mes 5
        sheet.addMergedRegion(new CellRangeAddress(16, 17, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(18, 18, 0, 1));
        
        // mes 6
        sheet.addMergedRegion(new CellRangeAddress(19, 20, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(21, 21, 0, 1));
    }

    private void adjustColumnsWidth(Sheet sheet) {
        sheet.setColumnWidth(0, (short) 3400);
        sheet.setColumnWidth(1, (short) 3400);

        short[] gradeColumns = {2, 3, 4, 5, 6,
            8, 9, 10, 11, 12,
            14, 15, 16, 17, 18,
            20, 21, 22, 23, 24,
            26, 27, 28, 29, 30};

        short[] spaceColumns = {7, 13, 19, 25};

        for (short i : gradeColumns) {
            sheet.setColumnWidth(i, (short) 800);
        }

        for (short i : spaceColumns) {
            sheet.setColumnWidth(i, (short) 300);
        }
    }

    private void paintStructure(Sheet sheet) {
        // imprime as barras cinzas q voa conter os dias do mes
        for (short r : new short[]{2, 3, 6, 9, 12, 15, 18}) {
            for (short c = 0; c < 31; c++) {
                paintCell(sheet.getRow(r).getCell(c), IndexedColors.GREY_25_PERCENT);
            }
        }

        // imprime as barras cinzas que separam as semanas
        for (short r = 2; r < 21; r++) {
            for (short c : new short[]{7, 13, 19, 25}) {
                paintCell(sheet.getRow(r).getCell(c), IndexedColors.GREY_25_PERCENT);
            }
        }

        // imprime os dias da semana
        String[] diasSemana = new String[]{"S", "T", "Q", "Q", "S"};
        for (short[] semana : new short[][]{
                    {2, 3, 4, 5, 6},
                    {8, 9, 10, 11, 12},
                    {14, 15, 16, 17, 18},
                    {20, 21, 22, 23, 24},
                    {26, 27, 28, 29, 30} }) {
            int i = 0;
            for (short dia : semana) {
                Cell cell = sheet.getRow(2).getCell(dia);
                cell.setCellValue(diasSemana[i]);
                alignCell(cell);
                ++i;
            }
        }
    }

    private void paintCell(Cell cell, IndexedColors background) {
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(background.getIndex());
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cell.setCellStyle(cs);
    }

    private void alignCell(Cell cell) {
        CellStyle cs = cell.getCellStyle();
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cell.setCellStyle(cs);
    }

    private void setCellsData(Sheet sheet, Map<Integer, Map<Date, Tupla<Aula, Aula>>> horario) {
        boolean flag = true;
        for (short row : new short[]{4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 19, 20}) {
            Cell cell = sheet.getRow(row).getCell(1);            
            alignCell(cell);

            if (flag) {
                cell.setCellValue(turma.getTurno().getHorario().getPrimeiro());
                flag = false;
            } else {
                cell.setCellValue(turma.getTurno().getHorario().getSegundo());
                flag = true;
            }

        }

        Iterator<Integer> meses = new TreeSet<>(horario.keySet()).iterator();
        for (short row : new short[]{4, 7, 10, 13, 16, 19}) {
            Cell cell = sheet.getRow(row).getCell(0);
            alignCell(cell);
            cell.setCellValue(DateUtil.getNomeMes(meses.next() ) );            
        }

    }

    private Sheet createSheet(String nome) {
        return wb.createSheet(WorkbookUtil.createSafeSheetName(nome));
    }

    private void save(String filename) throws Exception {
        String file = CronosFiles.getCronosExportDir() + filename + ".xlsx";
        try (FileOutputStream fo = new FileOutputStream(file)) {
            wb.write(fo);
        }
    }
}
