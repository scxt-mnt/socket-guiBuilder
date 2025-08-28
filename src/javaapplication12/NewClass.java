/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

import java.net.Socket;
import java.sql.*;
import java.io.*;
import java.net.ServerSocket;

/**
 *
 * @author borag
 */
public class NewClass {

    public NewClass() {
        try {
            int PORT = 8080;
            ServerSocket listener = new ServerSocket(PORT);
            System.out.println("listening to port " + PORT);

            while (true) {
                Connection connect = null;
                Socket socket = listener.accept();
                InputStream stream = socket.getInputStream();
                InputStreamReader read = new InputStreamReader(stream);
                BufferedReader lineReader = new BufferedReader(read);
                String line;
                
                while ((line = lineReader.readLine()) != null && !line.isEmpty()) {
                    try {
                        connect = DriverManager.getConnection(
                                "jdbc:oracle:thin:@localhost:1521:xe",
                                "c##myuser",
                                "myaccount123"
                        );

                        String query = "SELECT * FROM myTable WHERE name = ?";
                        PreparedStatement post = connect.prepareStatement(query);
                        post.setString(1, line);
                        ResultSet approved = post.executeQuery();
                        boolean hasRes = approved.next();

                        OutputStream out = socket.getOutputStream();
                        PrintWriter writer = new PrintWriter(out, true);
                        writer.println(hasRes);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            connect.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    };
                }
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
