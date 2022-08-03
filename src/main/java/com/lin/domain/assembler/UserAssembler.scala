package com.lin.domain.assembler


import com.lin.domain.model.UserInfoDO
import com.lin.infrastructure.persistence.entity.UserInfoPO
import com.lin.infrastructure.utils.BeanUtils
import org.springframework.stereotype.Component
/**
 * @author linzihao
 */
@Component
class UserAssembler extends IAssembler[UserInfoDO, UserInfoPO] {
  override def toDO(persistentObj: UserInfoPO): UserInfoDO = {
    var userInfoDO = new UserInfoDO
    BeanUtils.copyProperties(persistentObj, userInfoDO)
    userInfoDO
  }

  override def toPO(domain: UserInfoDO): UserInfoPO = {
    var persistentObj = new UserInfoPO
    BeanUtils.copyProperties(domain, persistentObj)
    persistentObj
  }

  override def toDOs(entities: List[UserInfoPO]): List[UserInfoDO] = {
    entities.map(toDO(_))
  }

  override def toPOs(domains: List[UserInfoDO]): List[UserInfoPO] = {
    domains.map(toPO(_))
  }
}
