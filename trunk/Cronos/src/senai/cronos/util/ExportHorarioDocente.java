/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Turno;
import senai.cronos.horario.Horario;
import senai.cronos.horario.HorarioDocente;
import senai.util.Tupla;
import senai.util.date.DateUtil;

/**
 *
 * @author Sergio
 */
public class ExportHorarioDocente {

    private Docente docente;
    private Workbook wb;
    private Map<Aula, IndexedColors> dicAulaCor;
    HorarioDocente horarioDocente;
    private static final int COLUMN_LENGHT = 10 * 20;
    private static final int ROWS_LENGHT = 15;

    public ExportHorarioDocente(Docente docente) {
        this.docente = docente;
    }

    public void exportarHorarioDocente() throws Exception {
        wb = new XSSFWorkbook();
        horarioDocente = docente.getHorarioDocente();
        createDicionarioDeCores();

        Sheet sheet1 = ExportUtils.createSheet(wb, docente.getPrimeiroTurno().name());
        Sheet sheet2 = ExportUtils.createSheet(wb, docente.getSegundoTurno().name());

        createCelulas(sheet1, docente.getPrimeiroTurno());
        createCelulas(sheet2, docente.getSegundoTurno());

        if (docente.getPrimeiroTurno().equals(docente.getSegundoTurno())) {
            wb.removeSheetAt(1);
        }

        ExportUtils.save(wb, docente.getNome());
    }

    private void createDicionarioDeCores() {
        dicAulaCor = new HashMap<>();
        Set<Aula> aulas = horarioDocente.getAulas();
        IndexedColors[] cores = ExportUtils.getColors();

        int i = 0;
        for (Aula aula : aulas) {
            dicAulaCor.put(aula, cores[i]);
            ++i;
        }

        dicAulaCor.put(Aula.VAZIA, IndexedColors.WHITE);
    }

    /**
     *
     * @param sheet
     * @param turno
     * @throws Exception
     */
    private void createCelulas(Sheet sheet, Turno turno) throws Exception {
        Row[] rows = new Row[COLUMN_LENGHT + 1];

        for (short r = 0; r <= COLUMN_LENGHT; r++) {
            rows[r] = sheet.createRow(r);
            for (short c = 0; c < ROWS_LENGHT; c++) {
                rows[r].createCell(c);
                sheet.setColumnWidth(c, 800);
            }
        }

        sheet.setColumnWidth(9, 7000);
        sheet.setColumnWidth(10, 3000);
        sheet.setColumnWidth(11, 2000);

        Map<Date, Tupla<Aula, Aula>> horario = new TreeMap<>();
        for (Date dia : horarioDocente.getHorario().keySet()) {
            horario.put(dia, horarioDocente.getHorario().get(dia).get(turno));
        }

        short row = 3;
        Map<Integer, Map<Date, Tupla<Aula, Aula>>> horariomes = new Horario(horario).separaHorarioEmMeses();
        for (Integer mes : horariomes.keySet()) {
            createBlocoDoMes(sheet, mes, horariomes.get(mes), row);
            row += 10;
        }
    }

    /**
     *
     * @param sheet
     * @param horario
     * @param block
     */
    private void createBlocoDoMes(Sheet sheet, int mes, Map<Date, Tupla<Aula, Aula>> horario, short row) {
        sheet.addMergedRegion(new CellRangeAddress(row, row, 0, 6));
        sheet.getRow(row).getCell((short)  0).setCellValue(DateUtil.getNomeMes(mes));
        ExportUtils.paintCell(wb, sheet.getRow(row).getCell((short)  0), IndexedColors.GREY_25_PERCENT);
        sheet.getRow(row).getCell((short)  9).setCellValue("Unidade Curricular");
        ExportUtils.paintCell(wb, sheet.getRow(row).getCell((short)  9), IndexedColors.GREY_25_PERCENT);
        sheet.getRow(row).getCell((short) 10).setCellValue("Turma");
        ExportUtils.paintCell(wb, sheet.getRow(row).getCell((short)  10), IndexedColors.GREY_25_PERCENT);
        sheet.getRow(row).getCell((short) 11).setCellValue("Sala");
        ExportUtils.paintCell(wb, sheet.getRow(row).getCell((short)  11), IndexedColors.GREY_25_PERCENT);

        // preenche o mini calendario
        int c = 0;
        for (String dia : new String[]{"D", "S", "T", "Q", "Q", "S", "S"}) {
            Cell cell = sheet.getRow(row + 1).getCell(c++);
            cell.setCellValue(dia);
            ExportUtils.paintCell(wb, cell, IndexedColors.GREY_25_PERCENT);
        }

        int dia = 0, semana = 0;
        for (Date date : horario.keySet()) {
            semana = (DateUtil.getDiaSemana(date) <= dia) ? (++semana) : semana;
            dia = DateUtil.getDiaSemana(date) - 1;
            Cell cell = sheet.getRow((row + 2) + semana).getCell(dia);
            cell.setCellValue(DateUtil.getDia(date));
            ExportUtils.paintCell(wb, cell, dicAulaCor.get(horario.get(date).getPrimeiro() ) );

        }

        // preenche a tabela de ocupacao
        int i = 1;
        for (Aula aula : new Horario(horario).getAulas()) {
            Cell cell = sheet.getRow(row + i).getCell((short) 9);
            cell.setCellValue(aula.getDisciplina().getNome());
            ExportUtils.paintCell(wb, cell, dicAulaCor.get(aula));
            
            cell = sheet.getRow(row + i).getCell((short) 10);
            cell.setCellValue(aula.getTurma().getNome());
            ExportUtils.paintCell(wb, cell, dicAulaCor.get(aula));
            
            cell = sheet.getRow(row + i).getCell((short) 11);
            cell.setCellValue(aula.getLab().getNome());
            ExportUtils.paintCell(wb, cell, dicAulaCor.get(aula));
            
            ++i;
        }
    }
}
