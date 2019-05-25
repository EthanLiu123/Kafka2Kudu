package tools

import java.util

import org.apache.kudu.Schema
import org.apache.kudu.client.CreateTableOptions
import org.apache.kudu.client.KuduClient.KuduClientBuilder
import org.apache.kudu.spark.kudu.KuduContext
import org.apache.spark.sql.{DataFrame, SaveMode}
import org.apache.kudu.spark.kudu._

//将数据落地到kudu
object DBUtils {

  def process(
               kuduContext: KuduContext,
               data: DataFrame,
               TO_TABLENAME: String, //kudu表名称
               KUDU_MASTER: String, //kudu的master
               schema: Schema,
               partitionID: String //kudu分区的id
             ): Unit = {
    //todo 1）如果表不存在 则创建表
    if (!kuduContext.tableExists(TO_TABLENAME)) {
      //1.创建kudu客户端
      val kuduClient = new KuduClientBuilder(KUDU_MASTER).build()
      //2.定义表的分区方式
      val tableOptions: CreateTableOptions = {
        //需要一个链表用来携带kudu的分区ID
        val parcols: util.LinkedList[String] = new util.LinkedList[String]()
        parcols.add(partitionID)
        //制定kudu的分区方式
        new CreateTableOptions()
          .addHashPartitions(parcols, 6)
          .setNumReplicas(3)
      }
      //调用API创建表
      kuduClient.createTable(TO_TABLENAME, schema, tableOptions)
    }
    //todo 2） 存在则将数据写入到kudu
    data.write
      .mode(SaveMode.Append)
      .option("kudu.table", TO_TABLENAME)
      .option("kudu.master", KUDU_MASTER)
      .kudu
  }
}