# CURD-ASSIGNMENT

A output.odt file is attached to demostrate outputs in the form of screenshots:

**database client used : mysql (phpmyadmin)
  name of database used : company

** log4j not working hence not used 

--------------------------------------------------------------------------------------
**CODE FILES Includes 
main -----------------------------
   
   1)4 case classes
      a) CaseClient.scala
      b) CaseProject.scala       
      c) CaseEmployee.scala
      d) CaseDepartment.scala
2)ConnectToDb.scala 
      a) def establishConnection
3)Operations.scala
      0) abstract class CaseClases (extended by each case class,for match pattern)
      a) def createTable 
      b) def insertIntoTable
      c) def readFromTable
      d) def updateIntoTable
      e) def getRecords (tail recursive supports def readFromTable)
4)CustomQueries.scala
      a) def getEmployeeWithDepartmentId
      b) def getEmployeeWithProjectId
      c) def getClientWithProjectId
**Test---------------------------------- 
 
5)TasteTheCurd.scala     

      
