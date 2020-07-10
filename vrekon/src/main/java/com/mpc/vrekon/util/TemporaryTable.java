package com.mpc.vrekon.util;

import com.google.gson.Gson;
import com.mpc.vrekon.util.UtilHelper;
import org.apache.log4j.Logger;
import org.hibernate.cfg.DefaultNamingStrategy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Entity
public class TemporaryTable extends DefaultNamingStrategy {

    Logger log = Logger.getLogger(getClass());

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String acquirer;
    private String issuer;
    private String transferee;
    private String originator;
    private Integer txnsrc;
    private Integer txndest;
    private String alternate_acquirer;
    private String product;
    private String invoice_number;
    private Integer respcode;
    private Integer reason_code;
    private String revcode;
    private String orig_msg;
    private Date orig_date;
    private Date orig_time;
    private String merchant_type;
    private String acq_country;
    private String refnum;
    private String termid;
    private String termloc;
    private String account1;
    private String account2;
    private String account3;
    private String branch;
    private String supervisor;
    private String terminal_trace;
    private String batch_id;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private String issuer_data;
    private String acquirer_data;
    private String avail_balance;
    private String currency_code = "ID";
    private double txn_fee;
    private double device_fee;
    private String node;
    private String version;
    private Integer trace;
    private Date localdate;
    private Date localtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(String acquirer) {
        this.acquirer = acquirer;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getTransferee() {
        return transferee;
    }

    public void setTransferee(String transferee) {
        this.transferee = transferee;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public int getTxnsrc() {
        return txnsrc;
    }

    public void setTxnsrc(int txnsrc) {
        this.txnsrc = txnsrc;
    }

    public int getTxndest() {
        return txndest;
    }

    public void setTxndest(int txndest) {
        this.txndest = txndest;
    }

    public String getAlternate_acquirer() {
        return alternate_acquirer;
    }

    public void setAlternate_acquirer(String alternate_acquirer) {
        this.alternate_acquirer = alternate_acquirer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public int getRespcode() {
        return respcode;
    }

    public void setRespcode(int respcode) {
        this.respcode = respcode;
    }

    public int getReason_code() {
        return reason_code;
    }

    public void setReason_code(int reason_code) {
        this.reason_code = reason_code;
    }

    public String getRevcode() {
        return revcode;
    }

    public void setRevcode(String revcode) {
        this.revcode = revcode;
    }

    public String getOrig_msg() {
        return orig_msg;
    }

    public void setOrig_msg(String orig_msg) {
        this.orig_msg = orig_msg;
    }

    public Date getOrig_date() {
        return orig_date;
    }

    public void setOrig_date(Date orig_date) {
        this.orig_date = orig_date;
    }

    public Date getOrig_time() {
        return orig_time;
    }

    public void setOrig_time(Date orig_time) {
        this.orig_time = orig_time;
    }

    public String getMerchant_type() {
        return merchant_type;
    }

    public void setMerchant_type(String merchant_type) {
        this.merchant_type = merchant_type;
    }

    public String getAcq_country() {
        return acq_country;
    }

    public void setAcq_country(String acq_country) {
        this.acq_country = acq_country;
    }

    public String getRefnum() {
        return refnum;
    }

    public void setRefnum(String refnum) {
        this.refnum = refnum;
    }

    public String getTermid() {
        return termid;
    }

    public void setTermid(String termid) {
        this.termid = termid;
    }

    public String getTermloc() {
        return termloc;
    }

    public void setTermloc(String termloc) {
        this.termloc = termloc;
    }

    public String getAccount1() {
        return account1;
    }

    public void setAccount1(String account1) {
        this.account1 = account1;
    }

    public String getAccount2() {
        return account2;
    }

    public void setAccount2(String account2) {
        this.account2 = account2;
    }

    public String getAccount3() {
        return account3;
    }

    public void setAccount3(String account3) {
        this.account3 = account3;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getTerminal_trace() {
        return terminal_trace;
    }

    public void setTerminal_trace(String terminal_trace) {
        this.terminal_trace = terminal_trace;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getData4() {
        return data4;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }

    public String getIssuer_data() {
        return issuer_data;
    }

    public void setIssuer_data(String issuer_data) {
        this.issuer_data = issuer_data;
    }

    public String getAcquirer_data() {
        return acquirer_data;
    }

    public void setAcquirer_data(String acquirer_data) {
        this.acquirer_data = acquirer_data;
    }

    public String getAvail_balance() {
        return avail_balance;
    }

    public void setAvail_balance(String avail_balance) {
        this.avail_balance = avail_balance;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public double getTxn_fee() {
        return txn_fee;
    }

    public void setTxn_fee(double txn_fee) {
        this.txn_fee = txn_fee;
    }

    public double getDevice_fee() {
        return device_fee;
    }

    public void setDevice_fee(double device_fee) {
        this.device_fee = device_fee;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getTrace() {
        return trace;
    }

    public void setTrace(int trace) {
        this.trace = trace;
    }

    public Date getLocaldate() {
        return localdate;
    }

    public void setLocaldate(Date localdate) {
        this.localdate = localdate;
    }

    public Date getLocaltime() {
        return localtime;
    }

    public void setLocaltime(Date localtime) {
        this.localtime = localtime;
    }

    @Override
    public String tableName(String tableName) {
        return super.tableName(tableName);
    }

    public void translateField(String[] temporaryField, String[] originalField, Map<String, Object> sourceRecord){
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (int indexField = 0; indexField < temporaryField.length; indexField++) {
                if(sourceRecord.containsKey(originalField[indexField])){
                    for(Field field: fields){
                        field.setAccessible(true);
                        String fieldDataType = field.getType().getSimpleName();
                        if (field.getName().equals(temporaryField[indexField])){
                            if (fieldDataType.equals("String")){
                                field.set(this, sourceRecord.get(originalField[indexField]).toString());
                            }

                            if (fieldDataType.equals("double")){
                                field.set(this, Double.valueOf(sourceRecord.get(originalField[indexField]).toString()));
                            }

                            if (fieldDataType.equals("Integer")){
                                field.set(this, Integer.valueOf(sourceRecord.get(originalField[indexField]).toString()));
                            }

                            if (fieldDataType.equals("Date")){
                                field.set(this, UtilHelper.convertStringToDate(sourceRecord.get(originalField[indexField]).toString()));
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public String toString() {
        return "TemporaryTable{" +
                "acquirer='" + acquirer + '\'' +
                ", issuer='" + issuer + '\'' +
                ", transferee='" + transferee + '\'' +
                ", originator='" + originator + '\'' +
                ", txnsrc=" + txnsrc +
                ", txndest=" + txndest +
                ", alternate_acquirer='" + alternate_acquirer + '\'' +
                ", product='" + product + '\'' +
                ", invoice_number='" + invoice_number + '\'' +
                ", respcode=" + respcode +
                ", reason_code=" + reason_code +
                ", revcode='" + revcode + '\'' +
                ", orig_msg='" + orig_msg + '\'' +
                ", orig_date=" + orig_date +
                ", orig_time=" + orig_time +
                ", merchant_type='" + merchant_type + '\'' +
                ", acq_country='" + acq_country + '\'' +
                ", refnum='" + refnum + '\'' +
                ", termid='" + termid + '\'' +
                ", termloc='" + termloc + '\'' +
                ", account1='" + account1 + '\'' +
                ", account2='" + account2 + '\'' +
                ", account3='" + account3 + '\'' +
                ", branch='" + branch + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", terminal_trace='" + terminal_trace + '\'' +
                ", batch_id='" + batch_id + '\'' +
                ", data1='" + data1 + '\'' +
                ", data2='" + data2 + '\'' +
                ", data3='" + data3 + '\'' +
                ", data4='" + data4 + '\'' +
                ", issuer_data='" + issuer_data + '\'' +
                ", acquirer_data='" + acquirer_data + '\'' +
                ", avail_balance='" + avail_balance + '\'' +
                ", currency_code='" + currency_code + '\'' +
                ", txn_fee=" + txn_fee +
                ", device_fee=" + device_fee +
                ", node='" + node + '\'' +
                ", version='" + version + '\'' +
                ", trace=" + trace +
                ", localdate=" + localdate +
                ", localtime=" + localtime +
                '}';
    }
}
