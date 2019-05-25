package tools

import java.io.File

import com.typesafe.config.ConfigFactory

class GlobalConfigUtils {
  val parsedConfig = ConfigFactory.parseFile(new File("D:\\workSpace\\Kafka2Kudu\\processDMP\\src\\main\\resources\\test\\application.conf"))
  val conf = ConfigFactory.load(parsedConfig)

  //加载spark相关的配置参数
  def sparkWorkTimeout = conf.getString("spark.worker.timeout")
  def sparkRpcTimeout = conf.getString("spark.rpc.askTimeout")
  def sparkNetWorkTimeout = conf.getString("spark.network.timeout")
  def sparkMaxCores = conf.getString("spark.cores.max")
  def sparkTaskMaxFailures = conf.getString("spark.task.maxFailures")
  def sparkSpeculation = conf.getString("spark.speculation")
  def sparkDriverAllowMutilpleContext = conf.getString("spark.driver.allowMutilpleContext")
  def sparkSerializer = conf.getString("spark.serializer")
  def sparkBufferPageSize = conf.getString("spark.buffer.pageSize")

  //加载kafka相关配置参数
  def kafkaServer=conf.getString("kafka.server")
  def groupId=conf.getString("groupId")
  def  kafkaTopic=conf.getString("kafka.topic")

  //获取ods前缀
  def odsPrefix = conf.getString("DB.ODS.PREFIX")

  //获取kudu master
  def kuduMaster=conf.getString("kudu.master")
}
object GlobalConfigUtils extends GlobalConfigUtils


