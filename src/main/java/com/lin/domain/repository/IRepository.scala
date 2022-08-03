package com.nova.felixchat.domain.repository

import reactor.core.publisher.{Flux, Mono}

/**
 *
 * @author linzihao
 */
trait IRepository[T] {
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
