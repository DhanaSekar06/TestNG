package readExcellData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcellRead {

	public static List<HashMap<String, String>> getValueFromExcel(int row, String filelocation) throws IOException {

		List<HashMap<String, String>> mapDatasList = new ArrayList();

		FileInputStream f = new FileInputStream(filelocation);
		XSSFWorkbook w = new XSSFWorkbook(f);
		XSSFSheet sheet = w.getSheet("Data");
		XSSFRow headerRow = sheet.getRow(0);

		HashMap<String, String> mapDatas = new HashMap<String, String>();

		for (int i = row; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row currentRow = sheet.getRow(i);

			for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {

				Cell currentCell = currentRow.getCell(j);

				DataFormatter de = new DataFormatter();
				String CellValue = de.formatCellValue(currentCell);
				Cell cell = headerRow.getCell(j);

				String HeaderCellValue = de.formatCellValue(cell);

				mapDatas.put(HeaderCellValue, CellValue);

			}

			mapDatasList.add(mapDatas);
		}

		f.close();

		return mapDatasList;
	}

}
