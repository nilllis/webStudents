package test;

import db.DBServices;

/**
 * Created by пк on 21.07.2015.
 */
public class test {

    public static void main(String[] args) {

        DBServices services = new DBServices();

        System.out.println(services.getAllDisciplines().toString());

    }
}





// TODO close PreparedStatements
//public void close() {
//    DBConnection conn = getDBConnection();
//    conn.closePreparedStatements();
//    this.putDBConnection(conn);
//}