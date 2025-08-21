/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

import java.sql.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author borag
 */
public class NewClass {

    public NewClass() {
        try {
            int port = 8080;
            ServerSocket portListener = new ServerSocket(port);
            System.out.println(port > 0 ? "running on " + port : "no port");
            while (true) {
                Socket socks = portListener.accept();
                System.out.println("request accepted");
                InputStream stream = socks.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line;
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    System.out.println(line);
                    try (Connection connect = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:xe",
                            "c##myuser",
                            "myaccount123"
                    )) {
                        connect.setAutoCommit(true);
                        String queries = "INSERT INTO myTable(name) VALUES(?)";
                        PreparedStatement ps = connect.prepareStatement(queries);

                        if (line != null) {
                            ps.setString(1, line);
                        }
                        ps.executeUpdate();
                        System.out.println("inserted: " + line);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    };
                }

                socks.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
