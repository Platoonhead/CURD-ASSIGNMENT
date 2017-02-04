package com.edu.knoldus

import java.sql.Connection

import org.scalatest.FlatSpec

class TasteTheCurd extends FlatSpec {

  val perform = new Operations
  val invoke = new CustomQueries
  val connectionString = new ConnectToDb
  val connectionObject: Connection = connectionString.establishConnection
  val id = 56
  val pid = 1
  val did = 2

  it should " return a connection object" in {

    assert(connectionString.establishConnection.isInstanceOf[Connection] == true
      || connectionString.establishConnection.isInstanceOf[Connection] == false
    )

  }


  //===============================================================================
  it should "throw an exception(wrong query syntax ) and hence return false during table creation" in {

    assert(perform.createTable(connectionObject, " department (id Int(11) Primary key NOT NULL AUTO_INCREMENT," +
      "name Varchar(24))") == false)

  }
  it should "create department table if already exist should return false" in {
    val query = "CREATE TABLE department (id Int(11) Primary key NOT NULL AUTO_INCREMENT,name Varchar(24))"
    assert(perform.createTable(connectionObject, query).isInstanceOf[Boolean] == true)

  }
  it should "create client table if already exist should return false" in {
    val query = "CREATE TABLE client (id Int(11) Primary key NOT NULL AUTO_INCREMENT," +
      "proid Int(11),name Varchar(24),address Varchar(25))"
    assert(perform.createTable(connectionObject, query).isInstanceOf[Boolean] == true)

  }
  it should "create employee table if already exist should return false" in {
    val query = "CREATE TABLE employee (id Int(11) Primary key NOT NULL AUTO_INCREMENT,name Varchar(24)," +
      "address Varchar(25),phone Varchar(12),depid Int(11),proid Int(11))"
    assert(perform.createTable(connectionObject, query).isInstanceOf[Boolean] == true)

  }
  it should "create project table if already exist should return false" in {
    val query = "CREATE TABLE project (id Int(11) Primary key NOT NULL AUTO_INCREMENT," +
      "depid Int(11),name Varchar(24),cliid Int(11))"
    assert(perform.createTable(connectionObject, query).isInstanceOf[Boolean] == true)

  }

  //=======================================================================================

  it should "be true if record not exist , false if record already exists/table no exist  in department " in {
    assert(perform.insertIntoTable(connectionObject, Department(1, "name")).isInstanceOf[Boolean] == true
    )
  }

  it should "be true if record not exist , false if record already exists/table no exist  in client " in {

    assert(perform.insertIntoTable(connectionObject, Client(1, 2, "name", "delhi")).isInstanceOf[Boolean] == true
    )

  }
  it should "be true if record not exist , false if record already exists/table no exist  in employee " in {

    assert(perform.insertIntoTable(connectionObject, Employee(id, "yy", "yy", "7896541236", pid, did)).isInstanceOf[Boolean] == true
    )

  }
  it should "be true if record not exist , false if record already exists/table no exist  in project" in {

    assert(perform.insertIntoTable(connectionObject, Project(id, did, "name", pid)).isInstanceOf[Boolean] == true
    )

  }

  //==========================================================================================================

  it should "throw an exception and as no such table  so nothing to read or empty list" in {

    assert(perform.readFromTable(connectionObject, "ankit") == List())

  }

  it should "fetch data from employee table" in {

    assert(perform.readFromTable(connectionObject, "employee").size >= 0)

  }
  it should "fetch data from employee project" in {

    assert(perform.readFromTable(connectionObject, "project").size >= 0)

  }
  it should "fetch data from employee department" in {

    assert(perform.readFromTable(connectionObject, "department").size >= 0)

  }
  it should "fetch data from employee client" in {

    assert(perform.readFromTable(connectionObject, "client").size >= 0)

  }

  //==================================================================================================

  it should "update client table " in {

    assert(perform.updateIntoTable(connectionObject, Client(pid, id, "guest", "bombay"), id).isInstanceOf[Boolean] == true)

  }
  it should "update employee table " in {

    assert(perform.updateIntoTable(connectionObject, Employee(id, "yy", "yy", "7896541236", pid, did), id).isInstanceOf[Boolean] == true)

  }
  it should "update project  table " in {

    assert(perform.updateIntoTable(connectionObject, Project(id, did, "name", pid), id).isInstanceOf[Boolean] == true)

  }
  it should "update department table " in {

    assert(perform.updateIntoTable(connectionObject, Department(pid, "name"), id).isInstanceOf[Boolean] == true)

  }

  //==========================================================================================================


  it should "throw exception as no such table exists" in {

    assert(perform.deleteFromTable(connectionObject, "nosuchtable", id).isInstanceOf[Boolean] == true)

  }

  it should " go through normal execution" in {

    assert(perform.deleteFromTable(connectionObject, "employee", 56).isInstanceOf[Boolean] == true)

  }

  //**************************************************************************************************************

  it should " yields list of 0 or more employees of same department id" in {
    val id = 98;
    val id2 = 97;
    val depid = 5
    perform.insertIntoTable(connectionObject, Employee(id, "yy", "yy", "7896541236", depid, did))
    perform.insertIntoTable(connectionObject, Employee(id2, "yy", "yy", "7896541236", depid, did))
    assert(invoke.getEmployeeWithDepartmentId(connectionObject, depid).size >= 0)

  }
  it should "yields list of 0 or more employees of same project id" in {

    assert(invoke.getEmployeeWithProjectId(connectionObject, pid).size >= 0)

  }

  it should "yields list of 0 or more client by project id" in {
    val id = 98;
    val id2 = 97;
    val projid = 5
    perform.insertIntoTable(connectionObject, Client(id, projid, "yy", "mumbai"))
    perform.insertIntoTable(connectionObject, Client(id2, projid, "yy", "delhi"))
    assert(invoke.getClientWithProjectId(connectionObject, pid).size >= 0)

  }


}
