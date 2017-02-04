package com.edu.knoldus

import java.sql._

import scala.annotation.tailrec

abstract class CaseClass

class Operations {

  def createTable(connectionObject: Connection, query: String): Boolean = {

    val onStatement = connectionObject.createStatement
    try {
      val querySays = onStatement.executeUpdate(query)
      if (querySays == 0) {
        true
      }
      else {
        false
      }
    }
    catch {
      case ex: Exception => false
    }

  }

  def insertIntoTable(connectionObject: Connection, insertInto: CaseClass): Boolean = {

    val onStatement = connectionObject.createStatement
    try {
      insertInto match {
        case Client(id, proid, name, address) => onStatement.executeUpdate(s"INSERT INTO client " +
          s"VALUES ($id,$proid,'$name','$address');");
          true
        case Department(id, name) => onStatement.executeUpdate(s"INSERT INTO department " +
          s"VALUES ($id,'$name');");
          true
        case Employee(id, name, address, phone, depid, proid) => onStatement.executeUpdate(s"INSERT INTO employee" +
          s" VALUES ($id,'$name','$address','$phone',$depid,$proid);");
          true
        case Project(id, depid, name, cliid) => onStatement.executeUpdate(s"INSERT INTO project " +
          s"VALUES ($id,$depid,'$name',$cliid);");
          true
        case _ => false
      }
    }
    catch {
      case ex: Exception => false
    }

  }

  def readFromTable(connectionObject: Connection, tableName: String): List[CaseClass] = {

    try {
      val onStatement = connectionObject.createStatement
      val tableResultSet = onStatement.executeQuery(s"SELECT * FROM $tableName")
      getRecords(tableResultSet, tableName, List())
    }
    catch {
      case ex: Exception => List()
    }

  }

  def updateIntoTable(connectionObject: Connection, UpdateThis: CaseClass, whereId: Int): Boolean = {
    val onStatement = connectionObject.createStatement
    try {
      UpdateThis match {
        case Client(id, proid, name, address) => onStatement.executeUpdate(s"UPDATE client SET id=$id," +
          s"proid=$proid,name='$name',address='$address' where id=$whereId");
          true
        case Department(id, name) => onStatement.executeUpdate(s"UPDATE department SET id=$id,name='$name' " +
          s"where id=$whereId");
          true
        case Employee(id, name, address, phone, depid, proid) => onStatement.executeUpdate(s"UPDATE employee SET" +
          s"id=$id,name='$name',address='$address'," +
          s"phone='$phone',depid=$depid,proid=$proid " +
          s"where id=$whereId");
          true
        case Project(id, depid, name, cliid) => onStatement.executeUpdate(s"UPDATE project SET " +
          s"id=$id,depid=$depid,name='$name',cliid=$cliid " +
          s"where id=$whereId");
          true
        case _ => false
      }
    }
    catch {
      case ex: Exception => false
    }

  }

  def deleteFromTable(connectionObject: Connection, tableName: String, deleteId: Int): Boolean = {
    val onStatement = connectionObject.createStatement
    try {
      if (onStatement.executeUpdate(s"DELETE FROM $tableName Where id=$deleteId") > 0) true else false
    }
    catch {
      case ex: Exception => false
    }
  }

  @tailrec
  private def getRecords(resultSet: ResultSet, tableName: String, result: List[CaseClass]): List[CaseClass] = {

    if (!resultSet.next) {
      result
    }
    else {

      tableName match {

        case "client" => getRecords(resultSet, tableName, result :+ Client(resultSet.getInt("id"),
          resultSet.getInt("proid"), resultSet.getString("name"), resultSet.getString("address")))

        case "department" => getRecords(resultSet, tableName, result :+ Department(resultSet.getInt("id"),
          resultSet.getString("name")))

        case "project" => getRecords(resultSet, tableName, result :+ Project(resultSet.getInt("id"),
          resultSet.getInt("depid"), resultSet.getString("name"), resultSet.getInt("cliid")))

        case "employee" => getRecords(resultSet, tableName, result :+ new Employee(resultSet.getInt("id"),
          resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("phone"),
          resultSet.getInt("depid"), resultSet.getInt("proid")))

        case _ => List()
      }

    }

  }


}
