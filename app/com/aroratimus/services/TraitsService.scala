package com.aroratimus.services

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class TraitsService @Inject()() (implicit exec: ExecutionContext){
 
  def recomputeSparkJob(jsonBody: String) = {
    
    val action = for{
      
      affinityTableName<-namedLocationDao.findAffinitiesByAccountId(accountId)
      
      
    } yield()
    
    db.run(action.transactionally)
    
    
  }
  
}