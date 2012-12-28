/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

/**
 *
 * @author Sergio
 */
public class ExportUtils {

    public static Sheet createSheet(Workbook wb, String nome) {
        return wb.createSheet(WorkbookUtil.createSafeSheetName(nome));
    }

    public static void save(Workbook wb, String filename) throws Exception {
        String file = CronosFiles.getCronosExportDir() + filename + ".xlsx";
        try (FileOutputStream fo = new FileOutputStream(file)) {
            wb.write(fo);
        }
    }
    
    public static void paintCell(Workbook wb, Cell cell, IndexedColors background) {
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

    public static void alignCell(Cell cell) {
        CellStyle cs = cell.getCellStyle();
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cell.setCellStyle(cs);
    }
    
    public static IndexedColors[] getColors() {
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
        
        return cores;
    }
}
