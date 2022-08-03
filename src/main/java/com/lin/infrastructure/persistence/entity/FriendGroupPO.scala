package com.nova.felixchat.infrastructure.persistence.entity

import com.nova.felixchat.infrastructure.commons.constants.MongoConstant
import org.springframework.data.mongodb.core.mapping.{DBRef, Document, MongoId}

import scala.beans.BeanProperty

/**
 * 好友分组表
 */
@Document(MongoConstant.FRIEND_GROUPS)
class FriendGroupPO {

  /**
   * 分组id
   */
  @MongoId
  @BeanProperty var id: String = _
  /**
   * 分组名字
   */
  @BeanProperty var name: String = _
  /**
   * 谁创建的这个分组
   */
  @DBRef
  @BeanProperty var user: UserInfoPO = _


}
