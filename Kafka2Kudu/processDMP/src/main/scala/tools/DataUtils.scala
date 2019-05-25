package tools

import java.util.{Calendar, Date}

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.time.FastDateFormat

object DataUtils {
  def NowDate(): String = {
    val now = new Date()
    //TODO SimpleDateFormat 线程不安全的实现20181010  2018101
    //    val simpleDateFormat:SimpleDateFormat
    val fastDateFormat: FastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")
    val formatTime = fastDateFormat.format(now)
    //yyyy-MM-dd HH:mm:ss ----> yyyyMMdd
    val date: String = fmtDate(formatTime).getOrElse("sorry , no time")
    date
  }


  /**
    * 格式化日期
    *
    * @param formatTime
    * @return yyyyMMdd
    **/
  def fmtDate(formatTime: String): Option[String] = {
    try {
      if (StringUtils.isNotEmpty(formatTime)) {
        val fields: Array[String] = formatTime.split(" ")
        if (fields.length > 1) {
          Some(fields(0).replace("-", ""))
        } else {
          None
        }
      } else {
        None
      }
    } catch {
      case _: Exception => None
    }
  }
}
