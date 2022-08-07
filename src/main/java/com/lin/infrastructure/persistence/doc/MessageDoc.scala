package com.lin.infrastructure.persistence.doc

import com.fasterxml.jackson.annotation.JsonFormat
import com.lin.infrastructure.commons.constants.MongoConstant
import org.springframework.data.mongodb.core.mapping.{Document, Field, MongoId}

import java.time.LocalDateTime
import scala.beans.BeanProperty

/**
 *
 * Scala的注解BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
@Document(MongoConstant.MESSAGE)
class MessageDoc {
  /**
   * 消息id
   */
  @MongoId
  @BeanProperty var id: String = _
  /**
   * 消息内容
   */
  @BeanProperty var content: String = _
  /**
   * 接收状态
   */
  @BeanProperty var status: Int = _

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @BeanProperty var time: LocalDateTime = _

  @Field("message_type")
  @BeanProperty var messageType: String = _

  @BeanProperty var from: UserInfoDoc = _

  @BeanProperty var to: List[UserInfoDoc] = _
}
