package programm;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class DataBase {
    public static void insertStudents() {
        String url = "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";
        Scanner sc = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            System.out.println("Имя: ");
            String name = sc.nextLine();
            System.out.println("Введите номер группы студента: ");
            int groupNum = scInt.nextInt();

            String query = "insert into students (name, groupName) values (?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, groupNum);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Запись успешно добавлена");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertGroups() {
        String url = "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";
        Scanner sc = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            System.out.print("Введите название группы: ");
            String name = sc.nextLine();

            String query = "insert into groups (name) values (?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Запись успешно добавлена");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewStudents() {
        String url= "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            // Установка соединения с базой данных


            // Создание SQL-запроса для выборки записей из таблицы
            String sql = "SELECT * FROM students";

            // Создание объекта Statement для выполнения запроса
            Statement statement = connection.createStatement();

            // Выполнение запроса и получение результирующего набора данных
            ResultSet resultSet = statement.executeQuery(sql);

            // Обход результатов запроса
            while (resultSet.next()) {
                // Получение значений столбцов по их индексу или имени
                String name = resultSet.getString("name");
                String groupName = resultSet.getString("groupName");

                // Вывод значений на консоль
                System.out.println("Name: " + name + ", Group Name: " + groupName);
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewGroups() {
        String url= "jdbc:postgresql://localhost:5432/books";
        String userName = "postgres";
        String password = "kstupoks2021";

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            // Установка соединения с базой данных


            // Создание SQL-запроса для выборки записей из таблицы
            String sql = "SELECT * FROM groups";

            // Создание объекта Statement для выполнения запроса
            Statement statement = connection.createStatement();

            // Выполнение запроса и получение результирующего набора данных
            ResultSet resultSet = statement.executeQuery(sql);

            // Обход результатов запроса
            while (resultSet.next()) {
                // Получение значений столбцов по их индексу или имени
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");

                // Вывод значений на консоль
                System.out.println("Group name: " + name + ", Group id: " + id);
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
