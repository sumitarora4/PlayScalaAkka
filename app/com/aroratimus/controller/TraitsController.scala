package com.aroratimus.controller

import scala.concurrent.ExecutionContext
import com.aroratimus.models.TraitJsonModel
import javax.inject.Inject
import play.api.libs.json.JsError
import play.api.libs.json.JsResult
import play.api.libs.json.JsSuccess
import play.api.libs.json.Json
import play.api.mvc.AbstractController
import play.api.mvc.ControllerComponents
import com.aroratimus.services.TraitsService

class TraitsController @Inject() (cc: ControllerComponents, traitsService: TraitsService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def recompute = Action(parse.json) {
    req =>
      {
        val requestJson = req.body

        val TraitJsonModelResult: JsResult[TraitJsonModel] = requestJson.validate[TraitJsonModel]

        TraitJsonModelResult match {
          case s: JsSuccess[TraitJsonModel] => {
            
            traitsService.recomputeSparkJob(requestJson.toString())
            
            println("result found"+s.get)
            Ok(Json.obj("status" -> "recompute submitted", "data" -> "Recompute has begun"))
          }
          case e: JsError => {
            println("error found" + JsError.toJson(e).toString())
            PreconditionFailed(Json.obj("status" -> "recompute error", "data" -> JsError.toJson(e).toString()))
          }
        }
      }
  }

}