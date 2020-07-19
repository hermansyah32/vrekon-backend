package com.mpc.vrekon.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Temporary2Table {

    /***
     * This table generate will give error below
     * 1118 - Row size too large (> 8126). Changing some columns to TEXT or BLOB may help. In current row format, BLOB prefix of 0 bytes is stored inline.
     *
     * #Solution
     * remove mysql limit per row
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String acquirer;
    private String issuer;
    private String transferee;
    private String originator;
    private Integer txnSrc;
    private Integer txnDest;
    private String alternateAcquirer;
    private String product;
    private String invoiceNumber;
    private Integer respCode;
    private Integer reasonCode;
    private String revCode;
    private String origMsg;
    private Date origDate;
    private Date origTime;
    private String merchantType;
    private String acqCountry;
    private String refnum;
    private String termId;
    private String termLoc;
    private String account1;
    private String account2;
    private String account3;
    private String branch;
    private String supervisor;
    private String terminalTrace;
    private String batchId;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private String issuerData;
    private String acquirerData;
    private String availBalance;
    private String currencyCode = "ID";
    private double txnFee;
    private double deviceFee;
    private String node;
    private String version;
    private Integer trace;
    private Date locDate;
    private Date locTime;

    public Temporary2Table() {
    }

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

    public Integer getTxnSrc() {
        return txnSrc;
    }

    public void setTxnSrc(Integer txnSrc) {
        this.txnSrc = txnSrc;
    }

    public Integer getTxnDest() {
        return txnDest;
    }

    public void setTxnDest(Integer txnDest) {
        this.txnDest = txnDest;
    }

    public String getAlternateAcquirer() {
        return alternateAcquirer;
    }

    public void setAlternateAcquirer(String alternateAcquirer) {
        this.alternateAcquirer = alternateAcquirer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public Integer getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(Integer reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getRevCode() {
        return revCode;
    }

    public void setRevCode(String revCode) {
        this.revCode = revCode;
    }

    public String getOrigMsg() {
        return origMsg;
    }

    public void setOrigMsg(String origMsg) {
        this.origMsg = origMsg;
    }

    public Date getOrigDate() {
        return origDate;
    }

    public void setOrigDate(Date origDate) {
        this.origDate = origDate;
    }

    public Date getOrigTime() {
        return origTime;
    }

    public void setOrigTime(Date origTime) {
        this.origTime = origTime;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getAcqCountry() {
        return acqCountry;
    }

    public void setAcqCountry(String acqCountry) {
        this.acqCountry = acqCountry;
    }

    public String getRefnum() {
        return refnum;
    }

    public void setRefnum(String refnum) {
        this.refnum = refnum;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getTermLoc() {
        return termLoc;
    }

    public void setTermLoc(String termLoc) {
        this.termLoc = termLoc;
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

    public String getTerminalTrace() {
        return terminalTrace;
    }

    public void setTerminalTrace(String terminalTrace) {
        this.terminalTrace = terminalTrace;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
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

    public String getIssuerData() {
        return issuerData;
    }

    public void setIssuerData(String issuerData) {
        this.issuerData = issuerData;
    }

    public String getAcquirerData() {
        return acquirerData;
    }

    public void setAcquirerData(String acquirerData) {
        this.acquirerData = acquirerData;
    }

    public String getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(String availBalance) {
        this.availBalance = availBalance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getTxnFee() {
        return txnFee;
    }

    public void setTxnFee(double txnFee) {
        this.txnFee = txnFee;
    }

    public double getDeviceFee() {
        return deviceFee;
    }

    public void setDeviceFee(double deviceFee) {
        this.deviceFee = deviceFee;
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

    public Integer getTrace() {
        return trace;
    }

    public void setTrace(Integer trace) {
        this.trace = trace;
    }

    public Date getLocDate() {
        return locDate;
    }

    public void setLocDate(Date locDate) {
        this.locDate = locDate;
    }

    public Date getLocTime() {
        return locTime;
    }

    public void setLocTime(Date locTime) {
        this.locTime = locTime;
    }

    @Override
    public String toString() {
        return "Temporary2Table{" +
                "id=" + id +
                ", acquirer='" + acquirer + '\'' +
                ", issuer='" + issuer + '\'' +
                ", transferee='" + transferee + '\'' +
                ", originator='" + originator + '\'' +
                ", txnSrc=" + txnSrc +
                ", txnDest=" + txnDest +
                ", alternateAcquirer='" + alternateAcquirer + '\'' +
                ", product='" + product + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", respCode=" + respCode +
                ", reasonCode=" + reasonCode +
                ", revCode='" + revCode + '\'' +
                ", origMsg='" + origMsg + '\'' +
                ", origDate=" + origDate +
                ", origTime=" + origTime +
                ", merchantType='" + merchantType + '\'' +
                ", acqCountry='" + acqCountry + '\'' +
                ", refnum='" + refnum + '\'' +
                ", termId='" + termId + '\'' +
                ", termLoc='" + termLoc + '\'' +
                ", account1='" + account1 + '\'' +
                ", account2='" + account2 + '\'' +
                ", account3='" + account3 + '\'' +
                ", branch='" + branch + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", terminalTrace='" + terminalTrace + '\'' +
                ", batchId='" + batchId + '\'' +
                ", data1='" + data1 + '\'' +
                ", data2='" + data2 + '\'' +
                ", data3='" + data3 + '\'' +
                ", data4='" + data4 + '\'' +
                ", issuerData='" + issuerData + '\'' +
                ", acquirerData='" + acquirerData + '\'' +
                ", availBalance='" + availBalance + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", txnFee=" + txnFee +
                ", deviceFee=" + deviceFee +
                ", node='" + node + '\'' +
                ", version='" + version + '\'' +
                ", trace=" + trace +
                ", locDate=" + locDate +
                ", locTime=" + locTime +
                '}';
    }
}
