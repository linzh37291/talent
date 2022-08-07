package com.lin.infrastructure.persistence.doc

import com.lin.infrastructure.commons.constants.MongoConstant
import org.springframework.data.mongodb.core.mapping.{DBRef, Document, Field, MongoId}

import scala.beans.BeanProperty

/**
 *
 * Scala的注解BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
@Document(MongoConstant.ADDRESS)
class AddressDoc {

  @MongoId
  @BeanProperty
  var id: String = _

  /**
   * 地区编码
   */
  @BeanProperty
  var code: String = _
  /**
   * 地区名称
   */
  @BeanProperty
  var name: String = _
  /**
   * 上一级地区
   */
  @DBRef
  @BeanProperty
  var parent: AddressDoc = _

  @Field("full_path")
  @BeanProperty
  var fullPath: String = _
}
