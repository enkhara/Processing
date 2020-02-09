import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, from_json}
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}



object jsonKafkaFiltrado {

  def main(array: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("Filtrado").master("local[*]").getOrCreate()


    val df = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers","localhost:9092")
      .option("subscribe", "topicjson")
      .option("startingOffsets","earliest")
      .load()

    df.printSchema()

    //cuando hay que leer un json, hay que hacer un casteo para que lo que se recibe de kafka  tenga un formato legible y convertirlo en strings
    val personalStingDF = df.selectExpr("CAST(value AS STRING)")

    val schema = new StructType()
      .add("id", IntegerType)
      .add("first_name", StringType)
      .add("last_name", StringType)
      .add("email",StringType)
      .add("gender", StringType)
      .add("ip_address", StringType)

    print("consulta")

    //hacemos casteo y sobre casteo aplicar schema y darle un alias
    val personalDF = personalStingDF.select(from_json(col("value"), schema).as ("data"))
      //hacemos la consulta
      .select("data.*")
      .filter("data.gender != 'Male' AND data.last_name != 'Bea'")

    //mostramos por consola el resultado
    personalDF.writeStream
      .format("console")
      .outputMode(("append"))
      .start()
      .awaitTermination()

  }

}
