package com.lin.infrastructure.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient

import java.util


/**
 *
 * Scala的注解BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
@Configuration
class ReactiveR2dbcConfig {
  val log = org.slf4j.LoggerFactory.getLogger(classOf[ReactiveR2dbcConfig])

  def initDatabase(client: DatabaseClient) = {
    val statements = util.Arrays.asList("DROP TABLE IF EXISTS USERS;",
      """create table `user_info` (
        |	`id` int (11),
        |	`account` varchar (600),
        |	`password` varchar (600),
        |	`nickname` varchar (600),
        |	`signature` text ,
        |	`sex` tinyint (4),
        |	`telephone` varchar (600),
        |	`real_name` varchar (600),
        |	`email` varchar (600),
        |	`intro` text ,
        |	`head_img` text ,
        |	`age` int (11),
        |	`constellation` varchar (600),
        |	`shengxiao` varchar (600),
        |	`school_tag` varchar (600),
        |	`vocation` varchar (600),
        |	`nation` varchar (600),
        |	`province_name` varchar (600),
        |	`province_code` varchar (600),
        |	`city_name` varchar (600),
        |	`city_code` varchar (600),
        |	`area_code` varchar (600),
        |	`area_name` varchar (600),
        |	`create_time` datetime ,
        |	`update_time` datetime
        |); """.stripMargin)
    statements.forEach((sql) => {
      executeSql(client, sql)
        .doOnSuccess((count) => log.info("Schema created, rows updated: {}", count))
        .doOnError((error) => log.error("got error : {}", error.getMessage, error))
        .subscribe()

    })
  }

  //private def getUser = Flux.just(new Nothing(null, "John", "Doe"), new Nothing(null, "Jane", "Doe"))

  private def executeSql(client: DatabaseClient, sql: String) = client.execute(sql).fetch.rowsUpdated

}
