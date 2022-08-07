package com.lin.infrastructure.utils

import java.io.FileInputStream
import java.util.Properties

/**
 *
 * Scala的注解BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
object PropertyUtil {

  def getProperty(name: String): String = {
    val properties = new Properties()
    val path = Thread
      .currentThread()
      .getContextClassLoader
      .getResource("application.properties")
      .getPath //文件要放到resource文件夹下
    properties.load(new FileInputStream(path))

    val propVal = properties.getProperty(name)
    if (null == propVal) {
      throw new NullPointerException("read properties key={" + name + "}  is null!")
    }
    propVal
  }
}
