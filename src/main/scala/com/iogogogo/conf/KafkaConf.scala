package com.iogogogo.conf

import java.util.concurrent.ConcurrentHashMap

import com.iogogogo.util.ClientConf

/**
  * Created by tao.zeng on 2019/1/27.
  */
class KafkaConf extends ClientConf[KafkaConf] {

  override val configs: ConcurrentHashMap[String, Any] = getConfigs("kafka")

  def bootstrapServer(): Option[String] = get[String]("bootstrap.servers")

  def zookeeperConnect(): Option[String] = get[String]("zookeeper.connect")

  def keySerializer(): Option[String] = get[String]("key.serializer")

  def valueSerializer(): Option[String] = get[String]("value.serializer")

  def keyDeserializer(): Option[String] = get[String]("key.deserializer")

  def valueDeserializer(): Option[String] = get[String]("value.deserializer")

}

object KafkaConf extends KafkaConf
