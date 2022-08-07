package com.lin.business.assembler

import com.lin.business.domain.BaseDO

/**
 *
 * Scala的注解BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
trait IAssembler[DO <: BaseDO, PO] {


  def toDO(persistentObj: PO): DO

  def toPO(domain: DO): PO

  def toDoList(persistentObjs: List[PO]): List[DO]

  def toPoList(domains: List[DO]): List[PO]
}
