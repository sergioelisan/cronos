/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
    private static final int ROW_LENGHT = 33;
    private static final int COLUMN_LENGHT = 42;
    private Map<Aula, IndexedColors> dicAulaCor;

    public ExportaHorario() {
    }

    public void exportarHorarioTurma(Turma turma) throws Exception {
        this.turma = turma;
        wb = new XSSFWorkbook();

        createDicionarioDeCores();

        Sheet sheet1 = createSheet(turma.getNome() + " - 1º semestre");
        createStructure(sheet1);
        paintStructure(sheet1);
        setCellsData(sheet1, turma.getHorario().getPrimeiroSemestre(), 1);

        Sheet sheet2 = createSheet(turma.getNome() + " - 2º semestre");
        createStructure(sheet2);
        paintStructure(sheet2);
        setCellsData(sheet2, turma.getHorario().getSegundoSemestre(), 2);

        save(turma.getNome());
    }

    private void createDicionarioDeCores() {
        dicAulaCor = new HashMap<>();
        Set<Aula> aulas = turma.getHorario().getAulas();
        IndexedColors[] cores = new IndexedColors[19];
        cores[0] = IndexedColors.AQUA;
        cores[1] = IndexedColors.LIGHT_BLUE;
        cores[2] = IndexedColors.LIGHT_CORNFLOWER_BLUE;
        cores[3] = IndexedColors.BRIGHT_GREEN;
        cores[4] = IndexedColors.YELLOW;
        cores[5] = IndexedColors.CORAL;
        cores[6] = IndexedColors.CORNFLOWER_BLUE;
        cores[7] = IndexedColors.LIGHT_GREEN;
        cores[8] = IndexedColors.LIGHT_ORANGE;
        cores[9] = IndexedColors.LIGHT_YELLOW;
        cores[10] = IndexedColors.LIGHT_TURQUOISE;
        cores[11] = IndexedColors.SEA_GREEN;
        cores[12] = IndexedColors.ORANGE;
        cores[13] = IndexedColors.GOLD;
        cores[14] = IndexedColors.GREEN;
        cores[15] = IndexedColors.INDIGO;
        cores[16] = IndexedColors.LAVENDER;
        cores[17] = IndexedColors.LEMON_CHIFFON;
        cores[18] = IndexedColors.PINK;

        int i = 0;
        for (Aula aula : aulas) {
            dicAulaCor.put(aula, cores[i]);
            ++i;
        }
        
        dicAulaCor.put(Aula.VAZIA, IndexedColors.WHITE);
    }

    private void createStructure(Sheet sheet) {
        Row[] rows = new Row[COLUMN_LENGHT + 1];

        for (short r = 0; r <= COLUMN_LENGHT; r++) {
            rows[r] = sheet.createRow(r);
            for (short c = 0; c < ROW_LENGHT; c++) {
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
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 26, ROW_LENGHT - 1));

        // nucleo
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 24));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 1));

        // separadores
        sheet.addMergedRegion(new CellRangeAddress(2, 20,  7,  7));
        sheet.addMergedRegion(new CellRangeAddress(2, 20, 13, 13));
        sheet.addMergedRegion(new CellRangeAddress(2, 20, 19, 19));
        sheet.addMergedRegion(new CellRangeAddress(2, 20, 25, 25));
        sheet.addMergedRegion(new CellRangeAddress(2, 20, 31, 31));
        
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(15, 15, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(18, 18, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(21, 21, 0, 1));

        // legendas
        for (int i = 23; i <= COLUMN_LENGHT; i++) {
            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, ROW_LENGHT - 1));
        }
    }

    private void adjustColumnsWidth(Sheet sheet) {
        sheet.setColumnWidth(0, (short) 3400);
        sheet.setColumnWidth(1, (short) 3400);

        short[] gradeColumns = {2, 3, 4, 5, 6,
            8, 9, 10, 11, 12,
            14, 15, 16, 17, 18,
            20, 21, 22, 23, 24,
            26, 27, 28, 29, 30, 32};

        short[] spaceColumns = {7, 13, 19, 25, 31};

        for (short i : gradeColumns) {
            sheet.setColumnWidth(i, (short) 800);
        }

        for (short i : spaceColumns) {
            sheet.setColumnWidth(i, (short) 300);
        }
    }

    private void paintStructure(Sheet sheet) {
        // imprime as barras cinzas q vao conter os dias do mes
        for (short r : new short[]{2, 3, 6, 9, 12, 15, 18}) {
            for (short c = 0; c < ROW_LENGHT; c++) {
                paintCell(sheet.getRow(r).getCell(c), IndexedColors.GREY_25_PERCENT);
            }
        }

        // imprime as barras cinzas que separam as semanas
        for (short r = 2; r < 21; r++) {
            for (short c : new short[]{7, 13, 19, 25, 31}) {
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
                    {26, 27, 28, 29, 30},
                    {32}}) {
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
        alignCell(cell);
    }

    private void alignCell(Cell cell) {
        CellStyle cs = cell.getCellStyle();
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cell.setCellStyle(cs);
    }

    private void setCellsData(Sheet sheet, Map<Integer, Map<Date, Tupla<Aula, Aula>>> horario, int semestre) {
        // coloca o nome da turma
        sheet.getRow((short) 2).getCell(0).setCellValue(turma.getNome());

        // celulas que conterão os numeros dos dias do mes
        short[][] slots = new short[][]{
            {2, 3, 4, 5, 6},
            {8, 9, 10, 11, 12},
            {14, 15, 16, 17, 18},
            {20, 21, 22, 23, 24},
            {26, 27, 28, 29, 30},
            {32}};

        // as linhas que tem as celulas com os numeros dos dias do mes
        short[] meses_rows = {3, 6, 9, 12, 15, 18};

        int i = 0;
        for (Integer mes : horario.keySet()) {
            // coloca o nome do mes
            Cell cell = sheet.getRow(meses_rows[i] + 1).getCell(0);
            alignCell(cell);
            cell.setCellValue(DateUtil.getNomeMes(mes));

            // coloca as horas das aulas do primeiro horario
            cell = sheet.getRow(meses_rows[i] + 1).getCell(1);
            alignCell(cell);
            cell.setCellValue(turma.getTurno().getHorario().getPrimeiro());

            // coloca as horas das aulas do segundo horario
            cell = sheet.getRow(meses_rows[i] + 2).getCell(1);
            alignCell(cell);
            cell.setCellValue(turma.getTurno().getHorario().getSegundo());

            // aloca os dias nas celulas devidas
            int dia = 0, semana = 0;
            for (Date date : horario.get(mes).keySet()) {
                semana = (DateUtil.getDiaSemana(date) <= dia) ? (++semana) : semana;
                dia = DateUtil.getDiaSemana(date);
                short r = meses_rows[i];
                short c = slots[semana][dia - 2];
                sheet.getRow(r).getCell(c).setCellValue(DateUtil.getDia(date));

                Aula a1 = horario.get(mes).get(date).getPrimeiro();
                Aula a2 = horario.get(mes).get(date).getSegundo();
                paintCell(sheet.getRow(r + 1).getCell(c), dicAulaCor.get(a1));
                paintCell(sheet.getRow(r + 2).getCell(c), dicAulaCor.get(a2));
            }
            ++i;
        }
        
        // cria as legendas
        Set<Aula> aulas = turma.getHorario().getAulasSemestre(semestre);
        aulas.remove(Aula.VAZIA);
        int legendaRow = 23;
        for (Aula aula : aulas) {
            Cell cell = sheet.getRow(legendaRow).getCell(0);
            cell.setCellValue("[" + aula.getDisciplina().getCargaHoraria() + "h] " 
                                  + aula.getDisciplina().getNome() + " - "
                                  + aula.getDocente().getNome() + " ("
                                  + aula.getLab().getNome() + ")"
            );
            paintCell(cell, dicAulaCor.get(aula));
            CellStyle cs = cell.getCellStyle();
            cs.setAlignment(CellStyle.ALIGN_LEFT);
            cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            cell.setCellStyle(cs);
            ++legendaRow;
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
