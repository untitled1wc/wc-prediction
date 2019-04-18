package com.wc.predictor.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.wc.predictor.entity.GameData;

@Service
public class ScheduleServiceImpl {

	public List<GameData> getSchedule(String filePath) throws EncryptedDocumentException, InvalidFormatException, IOException {
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		
		File file = ResourceUtils.getFile("classpath:static/iccwc2019schedule.xlsx");
        //InputStream in = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(file);

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        List<GameData> matchList = new ArrayList<GameData>();
        sheet.forEach(row -> {
        	GameData gData = new GameData();
            row.forEach(cell -> {
                int index = cell.getColumnIndex();
            	switch(index) {
	            	case 0: gData.setMatchDate(dataFormatter.formatCellValue(cell));
	            		break;
	            	case 1: setTeams(gData, dataFormatter.formatCellValue(cell));
	            		break;
	            	case 2: gData.setMatchTime(dataFormatter.formatCellValue(cell));
	            		break;
	            	case 3: gData.setVenue(dataFormatter.formatCellValue(cell));
	            		break;
	            	default:
	            		break;
            	}
                
            });
            if(null != gData.getMatchNumber()) {
            	matchList.add(gData);
            }
        });

        // Closing the workbook
        workbook.close();
        return matchList;
	}
	
	private void setTeams(GameData gData, String formatCellValue) {
		if(null != formatCellValue) {
			formatCellValue.split(",");
			String[] teams = formatCellValue.split("vs");
			if(teams.length == 2) {
				gData.setTeam1(teams[0].trim());
				teams = teams[1].trim().split(",");
				gData.setTeam2(teams[0].trim());
				if(null != teams[1]) {
					gData.setMatchNumber(teams[1].trim());
				}
			}
		}
		
	}	
}
