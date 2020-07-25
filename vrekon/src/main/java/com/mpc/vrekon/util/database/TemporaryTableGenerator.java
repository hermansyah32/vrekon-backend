package com.mpc.vrekon.util.database;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;

public class TemporaryTableGenerator {

    private ApplicationContext context;
    private Session session;
    private Logger log = Logger.getLogger(getClass());

    String tableSQL = "CREATE TABLE IF NOT EXISTS `{table_name}` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "  `id_source_config` int(11) NOT NULL, \n" +
            "  `account1` varchar(255) DEFAULT NULL,\n" +
            "  `account2` varchar(255) DEFAULT NULL,\n" +
            "  `account3` varchar(255) DEFAULT NULL,\n" +
            "  `acq_country` varchar(255) DEFAULT NULL,\n" +
            "  `acquirer` varchar(255) DEFAULT NULL,\n" +
            "  `acquirer_data` varchar(255) DEFAULT NULL,\n" +
            "  `alternate_acquirer` varchar(255) DEFAULT NULL,\n" +
            "  `avail_balance` varchar(255) DEFAULT NULL,\n" +
            "  `batch_id` varchar(255) DEFAULT NULL,\n" +
            "  `branch` varchar(255) DEFAULT NULL,\n" +
            "  `currency_code` varchar(255) DEFAULT NULL,\n" +
            "  `data1` varchar(255) DEFAULT NULL,\n" +
            "  `data2` varchar(255) DEFAULT NULL,\n" +
            "  `data3` varchar(255) DEFAULT NULL,\n" +
            "  `data4` varchar(255) DEFAULT NULL,\n" +
            "  `device_fee` double DEFAULT NULL,\n" +
            "  `invoice_number` varchar(255) DEFAULT NULL,\n" +
            "  `issuer` varchar(255) DEFAULT NULL,\n" +
            "  `issuer_data` varchar(255) DEFAULT NULL,\n" +
            "  `loc_date` datetime DEFAULT NULL,\n" +
            "  `loc_time` datetime DEFAULT NULL,\n" +
            "  `merchant_type` varchar(255) DEFAULT NULL,\n" +
            "  `node` varchar(255) DEFAULT NULL,\n" +
            "  `orig_date` datetime DEFAULT NULL,\n" +
            "  `orig_msg` varchar(255) DEFAULT NULL,\n" +
            "  `orig_time` datetime DEFAULT NULL,\n" +
            "  `originator` varchar(255) DEFAULT NULL,\n" +
            "  `product` varchar(255) DEFAULT NULL,\n" +
            "  `reason_code` int(11) DEFAULT NULL,\n" +
            "  `refnum` varchar(255) DEFAULT NULL,\n" +
            "  `resp_code` int(11) DEFAULT NULL,\n" +
            "  `rev_code` varchar(255) DEFAULT NULL,\n" +
            "  `supervisor` varchar(255) DEFAULT NULL,\n" +
            "  `term_id` varchar(255) DEFAULT NULL,\n" +
            "  `term_loc` varchar(255) DEFAULT NULL,\n" +
            "  `terminal_trace` varchar(255) DEFAULT NULL,\n" +
            "  `trace` int(11) DEFAULT NULL,\n" +
            "  `transferee` varchar(255) DEFAULT NULL,\n" +
            "  `txn_dest` int(11) DEFAULT NULL,\n" +
            "  `txn_fee` double DEFAULT NULL,\n" +
            "  `txn_src` int(11) DEFAULT NULL,\n" +
            "  `version` varchar(255) DEFAULT NULL\n" +
            ");";

    public TemporaryTableGenerator(ApplicationContext context, Session session) {
        this.context = context;
        this.session = session;
    }

    public boolean generateTable(String tableName){
        try{
            Transaction transaction = session.beginTransaction();
            tableSQL = tableSQL.replace("{table_name}", tableName);
            int result = session.createSQLQuery(tableSQL).executeUpdate();
            log.debug("generateResult : " + result);
            transaction.commit();
            if (result >0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
