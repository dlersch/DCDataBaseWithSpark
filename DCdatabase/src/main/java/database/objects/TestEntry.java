/*  +__^_________,_________,_____,________^-.-------------------,
 *  | |||||||||   `--------'     |          |                   O
 *  `+-------------USMC----------^----------|___________________|
 *    `\_,---------,---------,--------------'
 *      / X MK X /'|       /'
 *     / X MK X /  `\    /'
 *    / X MK X /`-------'
 *   / X MK X /
 *  / X MK X /
 * (________(                @author m.c.kunkel
 *  `------'
*/
package database.objects;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import spark.utils.SparkManager;

public class TestEntry {
	private static final String MYSQL_CONNECTION_URL = SparkManager.jdbcOptions().get("url") + "?user="
			+ SparkManager.jdbcOptions().get("user") + "&password=" + SparkManager.jdbcOptions().get("password");

	public static void main(String[] args) {
		System.out.println(MYSQL_CONNECTION_URL);
		try {

			SparkSession spSession = SparkManager.getSession();
			Dataset<Row> testDF = SparkManager.mySqlDataset();
			testDF.createOrReplaceTempView("DataView");

			Dataset<Row> dataDF = spSession.sql(
					"SELECT COLUMN_NAME AS `Field`, COLUMN_TYPE AS `Type`, IS_NULLABLE AS `NULL`,         COLUMN_KEY AS `Key`, COLUMN_DEFAULT AS `Default`, EXTRA AS `Extra` FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = 'test' and TABLE_NAME = 'status_change' and COLUMN_NAME = 'problem_type' FROM DataView");
			dataDF.show();
			// List<StatusChangeDB> list = new ArrayList<StatusChangeDB>();
			// StatusChangeDB statusChangeDB = new StatusChangeDB();
			// statusChangeDB.setProblem_type("lvfuse");
			// statusChangeDB.setRunno(762);
			// statusChangeDB.setRegion("6");
			// statusChangeDB.setSector("2");
			// statusChangeDB.setStatus_change_type("broke");
			// statusChangeDB.setSuperlayer("2");
			// statusChangeDB.setLoclayer("3");
			// statusChangeDB.setLocwire("10");
			// list.add(statusChangeDB);
			//
			// Encoder<StatusChangeDB> StatusChangeDBEncoder =
			// Encoders.bean(StatusChangeDB.class);
			// Dataset<Row> changeDF =
			// SparkManager.getSession().createDataset(list,
			// StatusChangeDBEncoder).toDF();
			// changeDF.write().mode(SaveMode.Append).jdbc(SparkManager.jdbcAppendOptions(),
			// "status_change",
			// new java.util.Properties());

		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

}
