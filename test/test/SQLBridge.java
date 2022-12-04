package test;
import java.sql.*;

/**
 *
 * @author theli
 */
public class SQLBridge {
    //Variables:
    private final String DBname ;
    public Connection conn;
    public Statement querier;
    
    //Constructor:
    public SQLBridge(String db){
        this.DBname = db;
    }
    
    //Métodos:
    public void startConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + this.DBname);
            
            this.querier = conn.createStatement();
            String startQuery = "SELECT * FROM sqlite_master WHERE NAME = 'Resource'";
            ResultSet result = this.querier.executeQuery(startQuery);
            
            if(result.next() == false){
                this.createDB();
            }
            else{
                System.out.println("'cooperativa' database successfully connected.");
            }
            
            result.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("ERROR "+e.getMessage());
        }
        catch (SQLException e) {
            System.out.println("ERROR "+e.getMessage());
        }
    }
    
    private void createDB(){
        try{
            System.out.println("Creation of the selected database started");
            
            String resTable = "CREATE TABLE Resource ("
                            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + "type VARCHAR(15) NOT NULL,"
                            + "stock BIGINT NOT NULL"
                            + ")";
            
            String donorTable = "CREATE TABLE Donor ("
                              + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                              + "name VARCHAR(20) NOT NULL,"
                              + "cRate DECIMAL(5, 4) NOT NULL"
                              + ")";
            
            String donaTable = "CREATE TABLE Donation ("
                             + "cuantity BIGINT NOT NULL,"
                             + "minPrice INTEGER NOT NULL,"
                             + "sendTime DATETIME,"
                             + "resID INTEGER UNSIGNED NOT NULL,"
                             + "donorID INTEGER UNSIGNED NOT NULL,"
                             + "CONSTRAINT resDonaID_FK FOREIGN KEY (resID) REFERENCES Resource(ID),"
                             + "CONSTRAINT donorDonaID_FK FOREIGN KEY (donorID) REFERENCES Donor(ID)"
                             + ")";
            
            String saleTable = "CREATE TABLE Sale ("
                             + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                             + "cantidad BIGINT,"
                             + "precio INTEGER,"
                             + "sellTime DATETIME,"
                             + "resID INTEGER UNSIGNED NOT NULL,"
                             + "CONSTRAINT resIDSale_FK FOREIGN KEY (resID) REFERENCES Resource(ID)"
                             + ")";
            
            this.querier.executeUpdate("DROP TABLE IF EXISTS Resource");
            this.querier.executeUpdate("DROP TABLE IF EXISTS Donor");
            this.querier.executeUpdate("DROP TABLE IF EXISTS Donation");
            this.querier.executeUpdate("DROP TABLE IF EXISTS Sale");
            
            this.querier.executeUpdate(resTable);
            this.querier.executeUpdate("INSERT INTO Resource VALUES (1, 'Petroleo', 1000000000)");
            System.out.println("Resource table created.");
            
            this.querier.executeUpdate(donorTable);
            this.querier.executeUpdate("INSERT INTO Donor VALUES (1, 'Kaekias', 0.0075)");
            System.out.println("Donor table created.");
            
            this.querier.executeUpdate(donaTable);
            this.querier.executeUpdate("INSERT INTO Donation VALUES (1000000000, 140, '2022-10-16 17:54:42', 1, 1)");
            System.out.println("Donation table created.");
            
            this.querier.executeUpdate(saleTable);
            this.querier.executeUpdate("INSERT INTO Sale VALUES (1, 10000, 145, '2022-10-25 05:45:12', 1)");
            System.out.println("Sale table created.");
            
            System.out.println("'cooperativa' database successfully created & connected.");
        }
        catch (SQLException e){
             System.out.println("ERROR "+e.getMessage());   
        }
    }
    
    public void resetDB(){
        System.out.println("");
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void close(){
        try{
            querier.close();
            conn.close();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
