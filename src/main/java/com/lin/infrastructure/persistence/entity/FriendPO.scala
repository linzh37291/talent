package com.nova.felixchat.infrastructure.persistence.entity

import com.nova.felixchat.infrastructure.commons.constants.MongoConstant
import org.springframework.data.mongodb.core.mapping.{DBRef, Document, Field, MongoId}

import scala.beans.BeanProperty

@Document(MongoConstant.FRIENDS)
class FriendPO {

  @MongoId
  @BeanProperty var id: String = _

  @BeanProperty var user: UserInfoPO = _

  @DBRef
  @BeanProperty var friend: UserInfoPO = _
  /**
   * 备注名
   */
  @BeanProperty var name: String = _
  /**
   * 好友类型
   */
  @Field("friend_type")
  @BeanProperty var friendType: String = _
  /**
   * 所属分组
   */
  @Field("friend_group")
  @BeanProperty var friendGroup: FriendGroupPO = _


}
