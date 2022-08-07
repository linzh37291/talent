package com.lin.domain.assembler


import com.lin.domain.model.UserInfoDO
import com.lin.infrastructure.persistence.doc.UserInfoDoc
import com.lin.infrastructure.utils.BeanCopyUtil
import org.springframework.stereotype.Component

/**
 *
 * Scala的BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
@Component
class UserDocAssembler extends IAssembler[UserInfoDO, UserInfoDoc] {


  override def toDoList(entities: List[UserInfoDoc]): List[UserInfoDO] = {
    entities.map(toDO(_))
  }

  override def toDO(persistentObj: UserInfoDoc): UserInfoDO = {
    var userInfoDO = new UserInfoDO
    BeanCopyUtil.copyProperties(persistentObj, userInfoDO)
    userInfoDO
  }

  override def toPoList(domains: List[UserInfoDO]): List[UserInfoDoc] = {
    domains.map(toPO(_))
  }

  override def toPO(domain: UserInfoDO): UserInfoDoc = {
    var persistentObj = new UserInfoDoc
    BeanCopyUtil.copyProperties(domain, persistentObj)
    persistentObj
  }
}
