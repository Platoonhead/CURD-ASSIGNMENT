package com.edu.knoldus

import java.sql.{Connection, DriverManager}

class ConnectToDb {

  def establishConnection: Connection = {

    Class.forName("com.mysql.jdbc.Driver")
    DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "platoonhead")

  }

}
