package com.lin.business.assembler

import com.lin.business.domain.UserInfoDO
import com.lin.infrastructure.persistence.entity.UserInfoPO
import com.lin.infrastructure.utils.BeanCopyUtil
import org.springframework.stereotype.Component

/**
 *
 * Scala的BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
@Component
class UserPOAssembler extends IAssembler[UserInfoDO, UserInfoPO] {


  override def toDoList(entities: List[UserInfoPO]): List[UserInfoDO] = {
    entities.map(toDO(_))
  }

  override def toDO(persistentObj: UserInfoPO): UserInfoDO = {
    var userInfoDO = new UserInfoDO
    BeanCopyUtil.copyProperties(persistentObj, userInfoDO)
    userInfoDO
  }

  override def toPoList(domains: List[UserInfoDO]): List[UserInfoPO] = {
    domains.map(toPO(_))
  }

  override def toPO(domain: UserInfoDO): UserInfoPO = {
    var persistentObj = new UserInfoPO
    BeanCopyUtil.copyProperties(domain, persistentObj)
    persistentObj
  }
}
