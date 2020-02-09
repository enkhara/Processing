# Ejercicio Zeppelin

## Calcular el número de registros que tenemos en amigos.csv

### 1.- Código de ejecución

```
        val spark = SparkSession.builder().appName("miapp").master("local").getOrCreate()
         
         val schema = new StructType()
            .add("ID", IntegerType)
            .add("Name", StringType)
            .add("Age", IntegerType)
            .add("Number_of_Friends", IntegerType)
        
         val df = spark.read.format("csv")
            .schema(schema)
            .option("delimiter",",")
            .load("c:/Users/Georgina/Documents/amigos.csv")
        
        df.show()
        
        println("Tenemos "+df.count()+" registros en el archivo amigos.csv\n")
```
### 2.- Resultado de la ejecución del código

[pantallazo del resultado](ejecucion.png)
