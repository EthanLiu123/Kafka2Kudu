package `trait`

import org.apache.kudu.spark.kudu.KuduContext
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SQLContext}

trait ProcessData {
  def process(sqlContext: SQLContext, sparkContext: SparkContext, kuduContext: KuduContext,dataFrame:DataFrame)
}
