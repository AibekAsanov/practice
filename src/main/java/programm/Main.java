package programm;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            System.out.println("Выберите операцию: ");
            System.out.println("1. Создать отчёт");
            System.out.println("2. Создать группу");
            System.out.println("3. Добавить студента");
            System.out.println("4. Просмотреть список групп");
            System.out.println("5. Просмотреть список студентов");
            System.out.println("0. Выход из программы");

            System.out.print("Выбор: ");
            String choose = sc.nextLine();

            switch (choose) {
                case "1":
                    FileConnection.writingToFile(FileConnection.readingFromFile(), FileConnection.takeDate());
                    break;
                case "2":
                    DataBase.insertGroups();
                    break;
                case "3":
                    DataBase.insertStudents();
                    break;
                case "4":
                    DataBase.viewStudents();
                    break;
                case "5":
                    DataBase.viewGroups();
                    break;
                case "0":
                    flag = false;
                    break;
            }
        }
    }
}

