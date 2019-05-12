package com.aroratimus.models

import java.util.UUID
import play.api.libs.json.JsPath
import play.api.libs.json.Reads
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class TraitJsonModel(id: UUID, traitName: String, userId: String, accountId: Int, coverage: Double,
                          threshold: Double, actionType: String, affinities: Seq[AffinityJsonModel])

case class AffinityJsonModel(id: String, name: String, affinityType: Option[String], affinityWeight: Double, totalAffinityScore: Double,
                             averageAffinityScore: Double)
object AffinityJsonModel {

  implicit val AffinityJsonModelReads: Reads[AffinityJsonModel] = (
    (JsPath \ "id").read[String] and
    (JsPath \ "name").read[String] and
    (JsPath \ "affinityType").readNullable[String] and
    (JsPath \ "affinityWeight").read[Double] and
    (JsPath \ "totalAffinityScore").read[Double] and
    (JsPath \ "averageAffinityScore").read[Double])(AffinityJsonModel.apply _)

}
object TraitJsonModel {

  //  implicit val TraitAffinityJsonFormat = Json.format[AffinityJsonModel]
  implicit val TraitJsonModelReads: Reads[TraitJsonModel] = (
    (JsPath \ "id").read[UUID] and
    (JsPath \ "traitName").read[String] and
    (JsPath \ "userId").read[String] and
    (JsPath \ "accountId").read[Int] and
    (JsPath \ "coverage").read[Double] and
    (JsPath \ "threshold").read[Double] and
    (JsPath \ "actionType").read[String] and
    (JsPath \ "affinities").read[Seq[AffinityJsonModel]])(TraitJsonModel.apply _)

}

class Entities {

}