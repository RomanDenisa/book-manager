package repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private Properties jdbcProps;
    private static final Logger logger= LogManager.getLogger();
    private Connection instance=null;

    public JdbcUtils(Properties props){
        jdbcProps=props;
    }

    private Connection getNewConnection(){
        logger.traceEntry();
        String url=jdbcProps.getProperty("jdbc.url");
        logger.info("Trying to connect to database ... {}",url);
        Connection con=null;
        try {
            con=DriverManager.getConnection(url);
        }catch (SQLException ex) {
            logger.error(ex);
            System.out.println("Error getting connection "+ ex);
        }
        logger.traceExit("Connected successfully!");
        return con;
    }

    public Connection getConnection(){
        logger.traceEntry();
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException ex) {
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit(instance);
        return instance;
    }
}
