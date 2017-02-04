package com.edu.knoldus

import java.sql._

class CustomQueries {

  def getEmployeeWithDepartmentId(connectionObject: Connection, departmentId: Int): List[CaseClass] = {

    val getOperations = new Operations
    val allEmployeeRecords = getOperations.readFromTable(connectionObject: Connection, "employee")
    allEmployeeRecords.filter(_.asInstanceOf[Employee].depid == departmentId)

  }

  def getEmployeeWithProjectId(connectionObject: Connection, projectId: Int): List[CaseClass] = {

    val getOperations = new Operations
    val allEmployeeRecords = getOperations.readFromTable(connectionObject: Connection, "employee")
    allEmployeeRecords.filter(_.asInstanceOf[Employee].proid == projectId)

  }

  def getClientWithProjectId(connectionObject: Connection, projectId: Int): List[CaseClass] = {

    val getOperations = new Operations
    val allClientRecords = getOperations.readFromTable(connectionObject: Connection, "client")
    allClientRecords.filter(_.asInstanceOf[Client].proid == projectId)

  }


}
