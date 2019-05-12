package com.aroratimus.dao

import slick.lifted.TableQuery
import slick.lifted.Tag
import slick.model.Table

import slick.ast.BaseTypedType

class NamedLocationDao { 
  
}

object NamedLocationDao {
  
  val tableQuery = TableQuery[NamedLocationsTable]
  
  class NamedLocationsTable(tag:Tag) extends Table[NamedLocations](tag,"named_locations"){
    
    def * =(id, clientId, name, location) <>((NamedLocations.apply _).tupled, NamedLocations.unapply)
    
    val id = column[Int]("id")
    val clientId = column[Int]("client_id")
    val name = column[String]("name")
    val location = column[String]("location")
    
  }
  
}