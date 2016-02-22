/*
 * auteur : Jordy CABANNES
 */

import java.sql.*;
public class JDBC {
public static void setDriver(String driver) throws ClassNotFoundException {
Class.forName(driver);
}
public static Connection getConnection(String url, String username,
String password) throws SQLException {
return DriverManager.getConnection(url, username, password);
}
public static void main(String[] args) {
Connection conn = null;
//Statement stmt=null;
try {
setDriver("com.mysql.jdbc.Driver");
conn = getConnection("jdbc:mysql://localhost:3306/baseTP2", "root", "root");
System.out.println("Bravo! vous avez réussi à établir une connexion avec le SGBD");
/*stmt= conn.createStatement();
String sql = "CREATE DATABASE " + "baseTP2";
stmt.executeUpdate(sql);
System.out.println("Base de données créée avec succès!");*/
} catch (SQLException e) {
System.out.println("problème de connexion !");
} catch (ClassNotFoundException ee) {
System.out.println("la classe du pilote n‘a pas été retrouvée !");
} finally {
if (conn != null) {
try {
conn.close();
} catch (Exception e) {
e.printStackTrace();
}
}
}}}
