package com.lin.domain.repository

import com.lin.domain.assembler.{UserDocAssembler, UserPOAssembler}
import com.lin.domain.model.UserInfoDO
import com.lin.infrastructure.persistence.docmapper.IUserDocMapper
import com.lin.infrastructure.persistence.entity.UserInfoPO
import com.lin.infrastructure.persistence.mapper.IUserInfoMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.{DatabaseClient, R2dbcEntityTemplate}
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query.query
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import reactor.core.publisher.{Flux, Mono}

/**
 *
 * Repository层的作用是屏蔽所有持久化层的细节,可以同时操作多个数据库
 *
 * @author linzihao
 */
@Repository
class UserRepository extends IUserRepository {

  @Autowired
  var userDocMapper: IUserDocMapper = _

  @Autowired
  var userDocAssembler: UserDocAssembler = _

  @Autowired
  var userPOAssembler: UserPOAssembler = _

  @Autowired
  var userInfoMapper: IUserInfoMapper = _

  @Autowired
  var r2dbcEntityTemplate: R2dbcEntityTemplate = _;

  @Autowired
  var databaseClient: DatabaseClient = _;


  /**
   * 保存
   *
   * @param t
   */
  override def save(t: UserInfoDO): Mono[UserInfoDO] = {
    //    userDocMapper.insert(userDocAssembler.toPO(t)).map( userDocAssembler.toDO)
    userInfoMapper.save(userPOAssembler.toPO(t)).map(userPOAssembler.toDO)

  }

  /**
   * 更新
   *
   * @param t
   * @return
   */
  override def update(t: UserInfoDO): Mono[UserInfoDO] = {
    //    userDocMapper.save(userDocAssembler.toPO(t)).map( userDocAssembler.toDO)
    userInfoMapper.save(userPOAssembler.toPO(t)).map(userPOAssembler.toDO);
  }

  /**
   * 单个查询
   *
   * @param t
   * @return
   */
  override def findOne(t: UserInfoDO): Mono[UserInfoDO] = {
    //    var example = Example.of(userDocAssembler.toPO(t))
    //    userDocMapper.findOne(example).map( userDocAssembler.toDO)
    r2dbcEntityTemplate.selectOne(query(getCriteria(t)), classOf[UserInfoPO]).map(userPOAssembler.toDO);
  }

  def getCriteria(t: UserInfoDO): Criteria = {
    var crit = Criteria.where("account").is(t.getAccount)
    if (t.getAge != 0) {
      crit.and("age").is(t.getAge)
    }
    if (StringUtils.hasText(t.getProvinceName)) {
      crit.and("province_name").is(t.getProvinceName)
    }
    crit
  }

  /**
   * 查询所有
   *
   * @param t
   * @return
   */
  override def findAll(t: UserInfoDO): Flux[UserInfoDO] = {
    //    var example = Example.of(userDocAssembler.toPO(t))
    //    userDocMapper.findAll(example).map( userDocAssembler.toDO)

    r2dbcEntityTemplate.select(query(getCriteria(t)), classOf[UserInfoPO]).map(userPOAssembler.toDO)
  }

  /**
   *
   * @param account
   * @return
   */
  override def findByAccount(account: String): Mono[UserInfoDO] = {
    //userDocMapper.findByAccount(account).map( userDocAssembler.toDO)
    userInfoMapper.findByAccount(account).map(userPOAssembler.toDO)
  }


}
