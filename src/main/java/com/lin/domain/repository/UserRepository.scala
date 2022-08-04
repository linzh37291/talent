package com.lin.domain.repository

import com.lin.infrastructure.persistence.entity.UserInfoPO
import com.lin.infrastructure.persistence.mapper.IUserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Repository
import reactor.core.publisher.{Flux, Mono}
/**
 *
 * Scala的BeanProperty可以取代Lombok
 * @author linzihao
 */
@Repository
class UserRepository extends IUserRepository {

  @Autowired
  var userMapper: IUserMapper = _

  /**
   * 保存
   *
   * @param t
   */
  override def save(t: UserInfoPO): Mono[UserInfoPO] = {
    userMapper.insert(t)
  }

  /**
   * 更新
   *
   * @param t
   * @return
   */
  override def update(t: UserInfoPO): Mono[UserInfoPO] = {
    userMapper.save(t)
  }

  /**
   * 单个查询
   *
   * @param t
   * @return
   */
  override def findOne(t: UserInfoPO): Mono[UserInfoPO] = {
    var example = Example.of(t)
    userMapper.findOne(example)
  }

  /**
   * 查询所有
   *
   * @param t
   * @return
   */
  override def findAll(t: UserInfoPO): Flux[UserInfoPO] = {
    var example = Example.of(t)
    userMapper.findAll(example)
  }

  /**
   *
   * @param account
   * @return
   */
  override def findByAccount(account: String): Mono[UserInfoPO] = {
    userMapper.findByAccount(account)
  }


}
