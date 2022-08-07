package com.lin.domain.repository

import com.lin.domain.model.BaseDO
import reactor.core.publisher.{Flux, Mono}


/**
 * repository不对应于某个数据库，用来屏蔽底层数据库操作的差异
 *
 * @author linzihao
 */
trait IRepository[T <: BaseDO] {
  /**
   * 保存
   *
   * @param t
   */
  def save(t: T): Mono[T]

  /**
   * 更新
   *
   * @param t
   * @return
   */
  def update(t: T): Mono[T]

  /**
   * 单个查询
   *
   * @param t
   * @return
   */
  def findOne(t: T): Mono[T]

  /**
   * 查询所有
   *
   * @param t
   * @return
   */
  def findAll(t: T): Flux[T]

}
