package com.ensah.core.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

public class Helper {



    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }



    public static String getAnnees(InputStream is){
        String Annee=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 0) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if(cellsNumber==1 ) {

                            Annee = cell.getStringCellValue();
                            return Annee;
                        }
                        cellsNumber++;
                    }

                }


                }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return Annee;
    }



    public static String getNiveau(InputStream is){
        String Niveau=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 1) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if (cellsNumber == 1) {
                            Niveau = cell.getStringCellValue();
                            return Niveau;
                        }
                        cellsNumber++;
                    }
                }
                rowNumber++;


            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return Niveau;
    }





    public static List<String> getEnseignant(InputStream is){
        List<String> list = new ArrayList<>();
        String cel=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 2) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if(cellsNumber>=4 ){
                            cel=cell.getStringCellValue();
                            if("".equals(cel)){
                                continue;
                            }
                            list.add(cel.split("\\n")[1]);
                        }


                        cellsNumber++;
                    }
                    break;
                }
                rowNumber++;

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static List<Integer> getCells(InputStream is){
        List<Integer> list = new ArrayList<>();
        String cel=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 2) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if(cellsNumber>=4 ){
                            cel=cell.getStringCellValue();
                            if("".equals(cel)){
                                cellsNumber++;
                                continue;
                            }
                            list.add(cellsNumber);
                        }


                        cellsNumber++;
                    }
                    break;
                }
                rowNumber++;

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static List<String> getModule(InputStream is){
        List<String> list = new ArrayList<>();
        String cel=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 2) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if(cellsNumber>=4 ){
                            cel=cell.getStringCellValue();
                            if("".equals(cel)){
                                continue;
                            }
                            list.add(cel.split("\\n")[0]);
                        }


                        cellsNumber++;
                    }
                    break;
                }
                rowNumber++;

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static Map<String, Double> getMoyenne(InputStream is,int idStudent,List<String> elements,String module,int CellN) {
        Map<String, Double> map = new HashMap<>();
        double cel = 0;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0, cellsNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == (idStudent + 4)) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();

                        if (cellsNumber == (CellN+elements.size())) {
                            cel = (double) cell.getNumericCellValue();
                            // System.out.println(cel);


                            map.put(module, cel);
                            return map;


                        }
                        cellsNumber++;


                    }


                }
                rowNumber++;



            }


    } catch (Exception e) {
        e.printStackTrace();
    }
        return map;
    }


    public static Map<String, String> getValidationModule(InputStream is,int idStudent,List<String> elements,String module,int CellN) {
        Map<String, String> map = new HashMap<>();
        String cel = null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0, cellsNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == (idStudent + 4)) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();

                        if (cellsNumber == (CellN+elements.size()+1)) {
                            cel = cell.getStringCellValue();
                            // System.out.println(cel);


                            map.put(module, cel);
                            return map;


                        }
                        cellsNumber++;


                    }


                }
                rowNumber++;



            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static int getAllCells(InputStream is){
        int cl = 0;
        String cel=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 3) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        cel=cell.getStringCellValue();
                        if("Rang".equals(cel)){
                                return cl;
                        }
                        cl++;
                    }
                    break;
                }
                rowNumber++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }



    public static double AllMoyenne(InputStream is,int idStudent,int nbrCell){
        double cl = 0;

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0,cellNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == (idStudent+4)) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if(cellNumber==(nbrCell-1)){
                            cl=(double) cell.getNumericCellValue();
                            return cl;
                        }


                        cellNumber++;
                    }
                    break;
                }
                rowNumber++;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }



    public static int getRang(InputStream is,int idStudent,int nbrCell){
        int cl = 0;

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0,cellNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == (idStudent+4)) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if(cellNumber==(nbrCell)){
                            cl=(int) cell.getNumericCellValue();
                            return cl;
                        }


                        cellNumber++;
                    }
                    break;
                }
                rowNumber++;

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }



    public static List<String> getElements(InputStream is,int nbrCell){
        List<String> list = new ArrayList<>();
        String cel=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 3) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if(cellsNumber>=nbrCell) {

                            cel = cell.getStringCellValue();
                            if("Moyenne".equals(cel)){
                                break;
                            }
                            list.add(cel);
                        }


                      cellsNumber++;

                    }
                    break;
                }
                rowNumber++;

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



    public static Map<String, Map<String,Double>> getNotes(InputStream is,int idStudent,List<String> elements,String module,int CellN){
        Map<String, Map<String,Double>> map = new HashMap<>();
        map.put(module,new HashMap<String,Double>());
        double cel=0;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0,cellsNumber=0,cellNum=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == (idStudent+4)) {
                    while (cells.hasNext() ) {
                        Cell cell = cells.next();

                        if(cellsNumber>=CellN) {

                              if(cellNum<elements.size()){
                                  cel=(double) cell.getNumericCellValue();
                                 // System.out.println(cel);


                                  map.get(module).put(elements.get(cellNum),cel);
                              }else{
                                  break;
                              }

                            cellNum++;
                        }
                        cellsNumber++;

                    }
                    break;
                }
                rowNumber++;

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static int countStudents(InputStream is){
        int etu=0;
        String cel=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber >= 4) {
                    while (cells.hasNext() ) {
                        Cell cell = cells.next();
                            cel=cell.getStringCellValue();

                            if(cel.equals("")){
                                return etu;
                            }
                        etu++;
                        break;

                    }

                }
                rowNumber++;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return etu;

    }


}
