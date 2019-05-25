package realTimeInsert

import java.io.{InputStream, OutputStream}

import `trait`.ProcessData
import org.apache.kudu.Schema
import org.apache.kudu.spark.kudu.KuduContext
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SQLContext}
import tools.{ContantsSQL, ContantsSchema, DBUtils, DataUtils, GlobalConfigUtils}

//用来将数据实时插入到kudu的工具类
object ImproveData extends ProcessData {
  val TO_TABLENAME = GlobalConfigUtils.odsPrefix + DataUtils.NowDate()
  //  val DATA_PATH = GlobalConfigUtils.dataPath
  val KUDU_MASTER = GlobalConfigUtils.kuduMaster
  val schema: Schema = ContantsSchema.odsSchema
  val partitionId = "ip"

  override def process(sqlContext: SQLContext, sparkContext: SparkContext, kuduContext: KuduContext,dataFrame:DataFrame): Unit = {

    /**
      * def process(
      * kuduContext: KuduContext,
      * data: DataFrame,
      * TO_TABLENAME: String, //kudu表名称
      * KUDU_MASTER: String, //kudu的master
      * schema: Schema,
      * partitionID: String //kudu分区的id
      */


    DBUtils.process(kuduContext, dataFrame, TO_TABLENAME, KUDU_MASTER, schema, partitionId)
  }

}
