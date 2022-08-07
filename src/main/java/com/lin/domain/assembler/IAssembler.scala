package com.lin.domain.assembler

import com.lin.domain.model.BaseDO

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
