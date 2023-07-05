package programm;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class FileConnection {
    public static int[] readingFromFile() {

        int totalCount = 0, mondayCount=  0, tuesdayCount = 0, wednesdayCount = 0, thursdayCount = 0, fridayCount = 0, fine = 0;

        int monday = 4, tuesday = 7, wednesday = 12, thursday = 18, friday = 23;

        int tempMax = -1, tempSum = 0, iter = 0, tempIndex = 0;

        String tempName = null;

        System.out.print("Введите название файла: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();

        //int sumOfLessons = monday + tuesday + wednesday + thursday + friday;

        try {
            FileInputStream file = new FileInputStream("C:\\Users\\hp\\Desktop\\Files\\" + fileName + ".xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {

                if (tempSum > tempMax) {
                    tempMax = tempSum;
                }

                if (iter > 2) {
                    System.out.println(tempName + ": " + tempSum);
                }


                int i = 0;

                tempSum = 0;
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            //System.out.print(cell.getNumericCellValue());
                            totalCount++;

                            tempSum++;

                            if (i < monday) {
                                mondayCount++;
                            } else if (i < tuesday) {
                                tuesdayCount++;
                            } else if (i < wednesday) {
                                wednesdayCount++;
                            } else if (i < thursday) {
                                thursdayCount++;
                            } else if (i < friday) {
                                fridayCount++;
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            if (iter > 1) {
                                if (!Objects.equals(cell.getStringCellValue(), "н\\б") && !Objects.equals(cell.getStringCellValue(), "нб")) {
                                    tempName = cell.getStringCellValue();
                                }

                                if (Objects.equals(cell.getStringCellValue(), "н\\б") || Objects.equals(cell.getStringCellValue(), "нб"))
                                {
                                    fine++;
                                }
                            }
                            break;
                    }

                    if (friday <= i) {
                        break;
                    }
                    i++;
                }

                iter++;

            }

            System.out.println("\n\nTotal: " + totalCount);
            System.out.println("Monday: " + mondayCount);
            System.out.println("Tuesday: " + tuesdayCount);
            System.out.println("Wednesday: " + wednesdayCount);
            System.out.println("Thursday: " + thursdayCount);
            System.out.println("Friday: " + fridayCount);
            System.out.println("Пропуски по уважительной причине: " + fine);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int arr[] = {totalCount, mondayCount, tuesdayCount, wednesdayCount, thursdayCount, fridayCount, tempMax, fine};
        return arr;
    }

    public static void writingToFile(int[] arr, String date) {
        Workbook workbook = new XSSFWorkbook();
        Sheet newSheet = workbook.createSheet("New list");

        Row row1 = newSheet.createRow(0);
        row1.createCell(0).setCellValue("Понедельник: ");
        row1.createCell(1).setCellValue(arr[1]);

        Row row2 = newSheet.createRow(1);
        row2.createCell(0).setCellValue("Вторник: ");
        row2.createCell(1).setCellValue(arr[2]);

        Row row3 = newSheet.createRow(2);
        row3.createCell(0).setCellValue("Среда: ");
        row3.createCell(1).setCellValue(arr[3]);

        Row row4 = newSheet.createRow(3);
        row4.createCell(0).setCellValue("Четверг: ");
        row4.createCell(1).setCellValue(arr[4]);

        Row row5 = newSheet.createRow(4);
        row5.createCell(0).setCellValue("Пятница: ");
        row5.createCell(1).setCellValue(arr[5]);

        Row row6 = newSheet.createRow(5);
        row6.createCell(0).setCellValue("Total: ");
        row6.createCell(1).setCellValue(arr[0]);

        Row row7 = newSheet.createRow(8);
        row7.createCell(0).setCellValue("Дата: ");
        row7.createCell(1).setCellValue(date);

        Row row8 = newSheet.createRow(10);
        row8.createCell(0).setCellValue("Максимальное количество пропусков: ");
        row8.createCell(1).setCellValue(arr[6]);

        Row row9 = newSheet.createRow(12);
        row9.createCell(0).setCellValue("Количество пропусков по уважительной причине: ");
        row9.createCell(1).setCellValue(arr[7]);

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите название файла для создания: ");
        String fileName = sc.nextLine();
        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\hp\\Desktop\\Files\\Отчёты\\" + fileName + ".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("\nФайл готов!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String takeDate() {
        // Создаем объект Date для текущей даты
        Date currentDate = new Date();

        // Создаем объект SimpleDateFormat для форматирования даты
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Получаем строковое представление текущей даты
        String formattedDate = dateFormat.format(currentDate);

        // Выводим текущую дату в консоль
        System.out.println("Текущая дата: " + formattedDate);

        return formattedDate;
    }
}

