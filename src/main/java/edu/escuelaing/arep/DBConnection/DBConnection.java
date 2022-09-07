package edu.escuelaing.arep.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    private static String urlDB = "jdbc:postgresql://postgres://epkqskokosplms:89c5410b6ccaad02fa7a732059ac5177dac7c8fefa4b1a85669523e9ac87f555@ec2-44-205-63-142.compute-1.amazonaws.com:5432/d1jvbdiritvj6c";
    private static String usuarioDB = "epkqskokosplms";
    private static String passwordDB = "89c5410b6ccaad02fa7a732059ac5177dac7c8fefa4b1a85669523e9ac87f555";
    private static Connection connection = null;

    public DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(urlDB, usuarioDB, passwordDB);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(){
        String CREATE_TABLE="CREATE TABLE Personas ("
                + "Cedula Numeric(15) NOT NULL,"
                + "Nombres VARCHAR(60) NOT NULL,"
                + "Apellidos VARCHAR(60) NOT NULL,"
                + "PRIMARY KEY (Cedula))";

        try {
            Statement stmnt = connection.createStatement();
            stmnt.executeQuery(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getInformation(){
        ArrayList<String[]> list = new ArrayList<>();
        String select = "SELECT * FROM Information;";
        try {

            Statement statement = connection.createStatement();
            ResultSet rs =statement.executeQuery(select);
            while(rs.next()){
                String usern = rs.getString("usern");
                String address = rs.getString("address");
                String[] tmp = {usern,address};
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}