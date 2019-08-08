package com.mpc.vrekon.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Inheritance;

@Inheritance
public class ShcLog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2547275786287787545L;
	//Report Page
	public static final String rTransIncoming = "transfer-incoming";
	public static final String rTransOnUs = "transfer-on-us";
	public static final String rTransOutgoing = "transfer-outgoing";
	public static final String rPaymentPurchase = "payment-purchase";
	
	public static final String rWithdrawalOffUs = "withdrawal-off-us";
	public static final String rWithdrawalOnUs = "withdrawal-on-us";
	public static final String rBalanceInqOnUs = "balance-inq-on-us";
	public static final String rBalanceInqOffUs = "balance-inq-off-us";
	
	//PCODE page
	public static final BigDecimal pCodeInqTrans = new BigDecimal(39); //IBFT inq
	public static final BigDecimal pCodeExeTrans = new BigDecimal(40); //IBFT exe
	public static final BigDecimal pCodeTransOnUs_1 = new BigDecimal(31); //equ-equ inq, QVA-equ inq
	public static final BigDecimal pCodeTransOnUs_2 = new BigDecimal(47); //equ-equ exe, equ-qva debet, qva-equ credit
	public static final BigDecimal pCodeTransOnUs_3 = new BigDecimal(39); //equ-qva inq, qva-qva inq
	public static final BigDecimal pCodeTransOnUs_4 = new BigDecimal(40); //equ-qva credit, qva-equ debet,qva-qva exe
	public static final BigDecimal pCodeInqPaymentPurchase = new BigDecimal(38); //payment inq
	public static final BigDecimal pCodeExePaymentPurchase = new BigDecimal(18); //paymen exe
	
	public static final BigDecimal pCodeWithdrawal = new BigDecimal(1); //tarik tunai
	public static final BigDecimal pCodeBalance = new BigDecimal(30); //info saldo
	

	@EmbeddedId
	private ShcLogKey 							shcLogKey;
	@Column(name="ACCEPTORNAME")				private String acceptorname;
	@Column(name="ACQ_AVAL_BALANCE")			private BigDecimal acqAvalBalance;
	@Column(name="ACQ_CONV_DATE")				private Date acqConvDate;
	@Column(name="ACQ_CONV_RATE")				private BigDecimal acqConvRate;
	@Column(name="ACQ_COUNTRY")					private BigDecimal acqCountry;
	@Column(name="ACQ_CURRENCY_CODE")			private BigDecimal acqCurrencyCode;
	@Column(name="ACQ_LEDGER_BALANCE")			private BigDecimal acqLedgerBalance;
	@Column(name="ACQUIRER")					private String acquirer;
	@Column(name="ACQUIRER_DATA")				private String acquirerData;
	@Column(name="ADDRESPONE")					private String addresponse;
	@Column(name="ALPHARESPONSECODE")			private String alpharesponsecode;
	@Column(name="ALTERNATEACQUIRER")			private String alternateacquirer;
	@Column(name="ACCTNUM")						private String acctnum;
	@Column(name="AMOUNT")						private BigDecimal amount;
	@Column(name="AMOUNT_EQUIV")				private BigDecimal amountEquiv;
	@Column(name="AUTH_DEVCAP")					private BigDecimal authDevcap;
	@Column(name="AUTHNUM")						private String authnum;
	@Column(name="AVAL_BALANCE")				private BigDecimal avalBalance;
	@Column(name="AVAL_BALANCE_TYPE")			private BigDecimal avalBalanceType;
	@Column(name="BATCH_ID")					private BigDecimal batchId;
	@Column(name="BRANCH")						private BigDecimal branch;
	@Column(name="CAP_DATE")					private Date capDate;
	@Column(name="CARD_SEQNO")					private BigDecimal cardSeqno;
	@Column(name="CARDPRODUCT")					private String cardproduct;
	@Column(name="CASH_BACK")					private BigDecimal cashBack;
	@Column(name="CH_AMOUNT")					private BigDecimal chAmount;
	@Column(name="CH_CONV_DATE")				private Date chConvDate;
	@Column(name="CH_CONV_RATE")				private BigDecimal chConvRate;
	@Column(name="CH_CURRENCY_CODE")			private BigDecimal chCurrencyCode;
	@Column(name="CH_NEW_AMOUNT")				private BigDecimal chNewAmount;
	@Column(name="CHECKER_ID")					private BigDecimal checkerId;
	@Column(name="CHIP_INDEX")					private String chipIndex;
	@Column(name="CHIP_TYPE")					private String chipType;
	@Column(name="DEVICE_CAP")					private BigDecimal deviceCap;
	@Column(name="DEVICE_DEVCAP")				private BigDecimal deviceDevcap;
	@Column(name="DEVICE_FEE")					private BigDecimal deviceFee;
	@Column(name="ENTITYID")					private String entityid;
	@Column(name="F_ID")						private String fId;
	@Column(name="FEE")							private BigDecimal fee;
	@Column(name="FILLER1")						private String filler1;
	@Column(name="FILLER2")						private String filler2;
	@Column(name="FILLER3")						private String filler3;
	@Column(name="FILLER4")						private String filler4;
	@Column(name="FLIPPED_MSGTYPE")				private BigDecimal flippedMsgtype;
	@Column(name="FORMATTER_DEVCAP")			private BigDecimal formatterDevcap;
	@Column(name="FPI")							private String fpi;
	@Column(name="INVOICE_NUMBER")				private String invoiceNumber;
	@Column(name="ISS_CONV_DATE")				private Date issConvDate;
	@Column(name="ISS_CONV_RATE")				private BigDecimal issConvRate;
	@Column(name="ISS_CURRENCY_CODE")			private BigDecimal issCurrencyCode;
	@Column(name="ISS_ENTITYID")				private String issEntityid;
	@Column(name="ISSUER")						private String issuer;
	@Column(name="ISSUER_DATA")					private String issuerData;
	@Column(name="LANE")						private BigDecimal lane;
	@Column(name="LEDGER_BALANCE")				private BigDecimal ledgerBalance;
	@Column(name="LEDGER_BALANCE_TYPE")			private BigDecimal ledgerBalanceType;
	@Column(name="LIFE_CYCLE")					private BigDecimal lifeCycle;
	@Column(name="LOCAL_DATE")					private Date localDate;
	@Column(name="LOCAL_TIME")					private BigDecimal localTime;
	@Column(name="MASK_PAN")					private String maskPan;
	@Column(name="MEMBER_ID")					private String memberId;
	@Column(name="MERCHANT_TYPE")				private BigDecimal merchantType;
	@Column(name="MSGTYPE")						private BigDecimal msgtype;
	@Column(name="mvv")							private String mvv;
	@Column(name="NEW_AMOUNT")					private BigDecimal newAmount;
	@Column(name="NEW_AMOUNT_EQUIV")			private BigDecimal newAmountEquiv;
	@Column(name="NEW_FEE")						private BigDecimal newFee;
	@Column(name="NEW_SETL_AMOUNT")				private BigDecimal newSetlAmount;
	@Column(name="NEW_SETL_FEE")				private BigDecimal newSetlFee;
	@Column(name="ORIGDATE")					private Date origdate;
	@Column(name="ORIGFLIPPEDMSG")				private BigDecimal origflippedmsg;
	@Column(name="ORIGINATOR")					private String originator;
	@Column(name="ORIGMSG")						private BigDecimal origmsg;
	@Column(name="ORIGTIME")					private BigDecimal origtime;
	@Column(name="ORIGTRACE")					private BigDecimal origtrace;
	@Column(name="POS_CAP_CODE")				private BigDecimal posCapCode;
	@Column(name="POS_CONDITION_CODE")			private BigDecimal posConditionCode;
	@Column(name="POS_ENTRY_CODE")				private BigDecimal posEntryCode;
	@Column(name="POS_PIN_CAP_CODE")			private String posPinCapCode;
	@Column(name="PROCESSOR_BUSDAY")			private Date processorBusday;
	@Column(name="REASON_CODE")					private BigDecimal reasonCode;
	@Column(name="RESPCODE")					private BigDecimal respcode;
	@Column(name="REVCODE")						private BigDecimal revcode;
	@Column(name="SAF")							private BigDecimal saf;
	@Column(name="SEQ_TRACE_NO")				private BigDecimal seqTraceNo;
	@Column(name="SERIAL_1")					private BigDecimal serial1;
	@Column(name="SERIAL_2")					private BigDecimal serial2;
	@Column(name="SETL_AVAL_BALANCE")			private BigDecimal setlAvalBalance;
	@Column(name="SETL_LEDGER_BALANCE")			private BigDecimal setlLedgerBalance;
	@Column(name="SETTLEMENT_AMOUNT")			private BigDecimal settlementAmount;
	@Column(name="SETTLEMENT_CODE")				private BigDecimal settlementCode;
	@Column(name="SETTLEMENT_DATE")				private Date settlementDate;
	@Column(name="SETTLEMENT_FEE")				private BigDecimal settlementFee;
	@Column(name="SETTLEMENT_RATE")				private BigDecimal settlementRate;
	@Column(name="SHC_DATA_BUFFER")				private String shcDataBuffer;
	@Column(name="SHC_DEVCAP")					private BigDecimal shcDevcap;
	@Column(name="SHCERROR")					private BigDecimal shcerror;
	@Column(name="SHIFT_NUMBER")				private BigDecimal shiftNumber;
	@Column(name="SLOT_NUM")					private BigDecimal slotNum;
	@Column(name="STOREID")						private BigDecimal storeid;
	@Column(name="SUPERVISOR")					private BigDecimal supervisor;
	@Column(name="TERMID")						private String termid;
	@Column(name="TERMINAL_TRACE")				private BigDecimal terminalTrace;
	@Column(name="TERMLOC")						private String termloc;
	@Column(name="TRA_AMOUNT")					private BigDecimal traAmount;
	@Column(name="TRA_CONV_DATE")				private Date traConvDate;
	@Column(name="TRA_CONV_RATE")				private BigDecimal traConvRate;
	@Column(name="TRA_CURRENCY_CODE")			private BigDecimal traCurrencyCode;
	@Column(name="TRACE")						private BigDecimal trace;
	@Column(name="PCODE")						private BigDecimal pcode;
	@Column(name="TRANDATE")					private Date trandate;
	@Column(name="TRANS_ID")					private String transId;
	@Column(name="TRANSFEREE") 					private String transferee;
	@Column(name="TRANTIME") 					private BigDecimal trantime;
	@Column(name="TXN_AMOUNT")					private BigDecimal txnAmount;
	@Column(name="TXN_CONV_DATE")				private Date txnConvDate;
	@Column(name="TXN_CONV_RATE")				private BigDecimal txnConvRate;
	@Column(name="TXN_CURRENCY_CODE")			private BigDecimal txnCurrencyCode;
	@Column(name="TXN_END_TIME")				private BigDecimal txnEndTime;
	@Column(name="TXN_NEW_AMOUNT")				private BigDecimal txnNewAmount;
	@Column(name="TXN_START_TIME")				private BigDecimal txnStartTime;
	@Column(name="TXNDEST")						private String txndest;
	@Column(name="TXNSRC")						private String txnsrc;
	@Column(name="TXNTYPE")						private BigDecimal txntype;

	public ShcLog() {
	}

	public String getAcceptorname() {
		return acceptorname;
	}

	public void setAcceptorname(String acceptorname) {
		this.acceptorname = acceptorname;
	}

	public BigDecimal getAcqAvalBalance() {
		return acqAvalBalance;
	}

	public void setAcqAvalBalance(BigDecimal acqAvalBalance) {
		this.acqAvalBalance = acqAvalBalance;
	}

	public Date getAcqConvDate() {
		return acqConvDate;
	}

	public void setAcqConvDate(Date acqConvDate) {
		this.acqConvDate = acqConvDate;
	}

	public BigDecimal getAcqConvRate() {
		return acqConvRate;
	}

	public void setAcqConvRate(BigDecimal acqConvRate) {
		this.acqConvRate = acqConvRate;
	}

	public BigDecimal getAcqCountry() {
		return acqCountry;
	}

	public void setAcqCountry(BigDecimal acqCountry) {
		this.acqCountry = acqCountry;
	}

	public BigDecimal getAcqCurrencyCode() {
		return acqCurrencyCode;
	}

	public void setAcqCurrencyCode(BigDecimal acqCurrencyCode) {
		this.acqCurrencyCode = acqCurrencyCode;
	}

	public BigDecimal getAcqLedgerBalance() {
		return acqLedgerBalance;
	}

	public void setAcqLedgerBalance(BigDecimal acqLedgerBalance) {
		this.acqLedgerBalance = acqLedgerBalance;
	}

	public String getAcquirer() {
		return acquirer;
	}

	public void setAcquirer(String acquirer) {
		this.acquirer = acquirer;
	}

	public String getAcquirerData() {
		return acquirerData;
	}

	public void setAcquirerData(String acquirerData) {
		this.acquirerData = acquirerData;
	}

	public String getAddresponse() {
		return addresponse;
	}

	public void setAddresponse(String addresponse) {
		this.addresponse = addresponse;
	}

	public String getAlpharesponsecode() {
		return alpharesponsecode;
	}

	public void setAlpharesponsecode(String alpharesponsecode) {
		this.alpharesponsecode = alpharesponsecode;
	}

	public String getAlternateacquirer() {
		return alternateacquirer;
	}

	public void setAlternateacquirer(String alternateacquirer) {
		this.alternateacquirer = alternateacquirer;
	}

	public String getAcctnum() {
		return acctnum;
	}

	public void setAcctnum(String acctnum) {
		this.acctnum = acctnum;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountEquiv() {
		return amountEquiv;
	}

	public void setAmountEquiv(BigDecimal amountEquiv) {
		this.amountEquiv = amountEquiv;
	}

	public BigDecimal getAuthDevcap() {
		return authDevcap;
	}

	public void setAuthDevcap(BigDecimal authDevcap) {
		this.authDevcap = authDevcap;
	}

	public String getAuthnum() {
		return authnum;
	}

	public void setAuthnum(String authnum) {
		this.authnum = authnum;
	}

	public BigDecimal getAvalBalance() {
		return avalBalance;
	}

	public void setAvalBalance(BigDecimal avalBalance) {
		this.avalBalance = avalBalance;
	}

	public BigDecimal getAvalBalanceType() {
		return avalBalanceType;
	}

	public void setAvalBalanceType(BigDecimal avalBalanceType) {
		this.avalBalanceType = avalBalanceType;
	}

	public BigDecimal getBatchId() {
		return batchId;
	}

	public void setBatchId(BigDecimal batchId) {
		this.batchId = batchId;
	}

	public BigDecimal getBranch() {
		return branch;
	}

	public void setBranch(BigDecimal branch) {
		this.branch = branch;
	}

	public Date getCapDate() {
		return capDate;
	}

	public void setCapDate(Date capDate) {
		this.capDate = capDate;
	}

	public BigDecimal getCardSeqno() {
		return cardSeqno;
	}

	public void setCardSeqno(BigDecimal cardSeqno) {
		this.cardSeqno = cardSeqno;
	}

	public String getCardproduct() {
		return cardproduct;
	}

	public void setCardproduct(String cardproduct) {
		this.cardproduct = cardproduct;
	}

	public BigDecimal getCashBack() {
		return cashBack;
	}

	public void setCashBack(BigDecimal cashBack) {
		this.cashBack = cashBack;
	}

	public BigDecimal getChAmount() {
		return chAmount;
	}

	public void setChAmount(BigDecimal chAmount) {
		this.chAmount = chAmount;
	}

	public Date getChConvDate() {
		return chConvDate;
	}

	public void setChConvDate(Date chConvDate) {
		this.chConvDate = chConvDate;
	}

	public BigDecimal getChConvRate() {
		return chConvRate;
	}

	public void setChConvRate(BigDecimal chConvRate) {
		this.chConvRate = chConvRate;
	}

	public BigDecimal getChCurrencyCode() {
		return chCurrencyCode;
	}

	public void setChCurrencyCode(BigDecimal chCurrencyCode) {
		this.chCurrencyCode = chCurrencyCode;
	}

	public BigDecimal getChNewAmount() {
		return chNewAmount;
	}

	public void setChNewAmount(BigDecimal chNewAmount) {
		this.chNewAmount = chNewAmount;
	}

	public BigDecimal getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(BigDecimal checkerId) {
		this.checkerId = checkerId;
	}

	public String getChipIndex() {
		return chipIndex;
	}

	public void setChipIndex(String chipIndex) {
		this.chipIndex = chipIndex;
	}

	public String getChipType() {
		return chipType;
	}

	public void setChipType(String chipType) {
		this.chipType = chipType;
	}

	public BigDecimal getDeviceCap() {
		return deviceCap;
	}

	public void setDeviceCap(BigDecimal deviceCap) {
		this.deviceCap = deviceCap;
	}

	public BigDecimal getDeviceDevcap() {
		return deviceDevcap;
	}

	public void setDeviceDevcap(BigDecimal deviceDevcap) {
		this.deviceDevcap = deviceDevcap;
	}

	public BigDecimal getDeviceFee() {
		return deviceFee;
	}

	public void setDeviceFee(BigDecimal deviceFee) {
		this.deviceFee = deviceFee;
	}

	public String getEntityid() {
		return entityid;
	}

	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}

	public String getFiller3() {
		return filler3;
	}

	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}

	public String getFiller4() {
		return filler4;
	}

	public void setFiller4(String filler4) {
		this.filler4 = filler4;
	}

	public BigDecimal getFlippedMsgtype() {
		return flippedMsgtype;
	}

	public void setFlippedMsgtype(BigDecimal flippedMsgtype) {
		this.flippedMsgtype = flippedMsgtype;
	}

	public BigDecimal getFormatterDevcap() {
		return formatterDevcap;
	}

	public void setFormatterDevcap(BigDecimal formatterDevcap) {
		this.formatterDevcap = formatterDevcap;
	}

	public String getFpi() {
		return fpi;
	}

	public void setFpi(String fpi) {
		this.fpi = fpi;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getIssConvDate() {
		return issConvDate;
	}

	public void setIssConvDate(Date issConvDate) {
		this.issConvDate = issConvDate;
	}

	public BigDecimal getIssConvRate() {
		return issConvRate;
	}

	public void setIssConvRate(BigDecimal issConvRate) {
		this.issConvRate = issConvRate;
	}

	public BigDecimal getIssCurrencyCode() {
		return issCurrencyCode;
	}

	public void setIssCurrencyCode(BigDecimal issCurrencyCode) {
		this.issCurrencyCode = issCurrencyCode;
	}

	public String getIssEntityid() {
		return issEntityid;
	}

	public void setIssEntityid(String issEntityid) {
		this.issEntityid = issEntityid;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getIssuerData() {
		return issuerData;
	}

	public void setIssuerData(String issuerData) {
		this.issuerData = issuerData;
	}

	public BigDecimal getLane() {
		return lane;
	}

	public void setLane(BigDecimal lane) {
		this.lane = lane;
	}

	public BigDecimal getLedgerBalance() {
		return ledgerBalance;
	}

	public void setLedgerBalance(BigDecimal ledgerBalance) {
		this.ledgerBalance = ledgerBalance;
	}

	public BigDecimal getLedgerBalanceType() {
		return ledgerBalanceType;
	}

	public void setLedgerBalanceType(BigDecimal ledgerBalanceType) {
		this.ledgerBalanceType = ledgerBalanceType;
	}

	public BigDecimal getLifeCycle() {
		return lifeCycle;
	}

	public void setLifeCycle(BigDecimal lifeCycle) {
		this.lifeCycle = lifeCycle;
	}

	public Date getLocalDate() {
		return localDate;
	}

	public void setLocalDate(Date localDate) {
		this.localDate = localDate;
	}

	public BigDecimal getLocalTime() {
		return localTime;
	}

	public void setLocalTime(BigDecimal localTime) {
		this.localTime = localTime;
	}

	public String getMaskPan() {
		return maskPan;
	}

	public void setMaskPan(String maskPan) {
		this.maskPan = maskPan;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(BigDecimal merchantType) {
		this.merchantType = merchantType;
	}

	public BigDecimal getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(BigDecimal msgtype) {
		this.msgtype = msgtype;
	}

	public String getMvv() {
		return mvv;
	}

	public void setMvv(String mvv) {
		this.mvv = mvv;
	}

	public BigDecimal getNewAmount() {
		return newAmount;
	}

	public void setNewAmount(BigDecimal newAmount) {
		this.newAmount = newAmount;
	}

	public BigDecimal getNewAmountEquiv() {
		return newAmountEquiv;
	}

	public void setNewAmountEquiv(BigDecimal newAmountEquiv) {
		this.newAmountEquiv = newAmountEquiv;
	}

	public BigDecimal getNewFee() {
		return newFee;
	}

	public void setNewFee(BigDecimal newFee) {
		this.newFee = newFee;
	}

	public BigDecimal getNewSetlAmount() {
		return newSetlAmount;
	}

	public void setNewSetlAmount(BigDecimal newSetlAmount) {
		this.newSetlAmount = newSetlAmount;
	}

	public BigDecimal getNewSetlFee() {
		return newSetlFee;
	}

	public void setNewSetlFee(BigDecimal newSetlFee) {
		this.newSetlFee = newSetlFee;
	}

	public Date getOrigdate() {
		return origdate;
	}

	public void setOrigdate(Date origdate) {
		this.origdate = origdate;
	}

	public BigDecimal getOrigflippedmsg() {
		return origflippedmsg;
	}

	public void setOrigflippedmsg(BigDecimal origflippedmsg) {
		this.origflippedmsg = origflippedmsg;
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public BigDecimal getOrigmsg() {
		return origmsg;
	}

	public void setOrigmsg(BigDecimal origmsg) {
		this.origmsg = origmsg;
	}

	public BigDecimal getOrigtime() {
		return origtime;
	}

	public void setOrigtime(BigDecimal origtime) {
		this.origtime = origtime;
	}

	public BigDecimal getOrigtrace() {
		return origtrace;
	}

	public void setOrigtrace(BigDecimal origtrace) {
		this.origtrace = origtrace;
	}

	public ShcLogKey getShcLogKey() {
		return shcLogKey;
	}

	public void setShcLogKey(ShcLogKey shcLogKey) {
		this.shcLogKey = shcLogKey;
	}

	public BigDecimal getPcode() {
		return pcode;
	}

	public void setPcode(BigDecimal pcode) {
		this.pcode = pcode;
	}

	public BigDecimal getPosCapCode() {
		return posCapCode;
	}

	public void setPosCapCode(BigDecimal posCapCode) {
		this.posCapCode = posCapCode;
	}

	public BigDecimal getPosConditionCode() {
		return posConditionCode;
	}

	public void setPosConditionCode(BigDecimal posConditionCode) {
		this.posConditionCode = posConditionCode;
	}

	public BigDecimal getPosEntryCode() {
		return posEntryCode;
	}

	public void setPosEntryCode(BigDecimal posEntryCode) {
		this.posEntryCode = posEntryCode;
	}

	public String getPosPinCapCode() {
		return posPinCapCode;
	}

	public void setPosPinCapCode(String posPinCapCode) {
		this.posPinCapCode = posPinCapCode;
	}

	public Date getProcessorBusday() {
		return processorBusday;
	}

	public void setProcessorBusday(Date processorBusday) {
		this.processorBusday = processorBusday;
	}

	public BigDecimal getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(BigDecimal reasonCode) {
		this.reasonCode = reasonCode;
	}

	public BigDecimal getRespcode() {
		return respcode;
	}

	public void setRespcode(BigDecimal respcode) {
		this.respcode = respcode;
	}

	public BigDecimal getRevcode() {
		return revcode;
	}

	public void setRevcode(BigDecimal revcode) {
		this.revcode = revcode;
	}

	public BigDecimal getSaf() {
		return saf;
	}

	public void setSaf(BigDecimal saf) {
		this.saf = saf;
	}

	public BigDecimal getSeqTraceNo() {
		return seqTraceNo;
	}

	public void setSeqTraceNo(BigDecimal seqTraceNo) {
		this.seqTraceNo = seqTraceNo;
	}

	public BigDecimal getSerial1() {
		return serial1;
	}

	public void setSerial1(BigDecimal serial1) {
		this.serial1 = serial1;
	}

	public BigDecimal getSerial2() {
		return serial2;
	}

	public void setSerial2(BigDecimal serial2) {
		this.serial2 = serial2;
	}

	public BigDecimal getSetlAvalBalance() {
		return setlAvalBalance;
	}

	public void setSetlAvalBalance(BigDecimal setlAvalBalance) {
		this.setlAvalBalance = setlAvalBalance;
	}

	public BigDecimal getSetlLedgerBalance() {
		return setlLedgerBalance;
	}

	public void setSetlLedgerBalance(BigDecimal setlLedgerBalance) {
		this.setlLedgerBalance = setlLedgerBalance;
	}

	public BigDecimal getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public BigDecimal getSettlementCode() {
		return settlementCode;
	}

	public void setSettlementCode(BigDecimal settlementCode) {
		this.settlementCode = settlementCode;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public BigDecimal getSettlementFee() {
		return settlementFee;
	}

	public void setSettlementFee(BigDecimal settlementFee) {
		this.settlementFee = settlementFee;
	}

	public BigDecimal getSettlementRate() {
		return settlementRate;
	}

	public void setSettlementRate(BigDecimal settlementRate) {
		this.settlementRate = settlementRate;
	}

	public String getShcDataBuffer() {
		return shcDataBuffer;
	}

	public void setShcDataBuffer(String shcDataBuffer) {
		this.shcDataBuffer = shcDataBuffer;
	}

	public BigDecimal getShcDevcap() {
		return shcDevcap;
	}

	public void setShcDevcap(BigDecimal shcDevcap) {
		this.shcDevcap = shcDevcap;
	}

	public BigDecimal getShcerror() {
		return shcerror;
	}

	public void setShcerror(BigDecimal shcerror) {
		this.shcerror = shcerror;
	}

	public BigDecimal getShiftNumber() {
		return shiftNumber;
	}

	public void setShiftNumber(BigDecimal shiftNumber) {
		this.shiftNumber = shiftNumber;
	}

	public BigDecimal getSlotNum() {
		return slotNum;
	}

	public void setSlotNum(BigDecimal slotNum) {
		this.slotNum = slotNum;
	}

	public BigDecimal getStoreid() {
		return storeid;
	}

	public void setStoreid(BigDecimal storeid) {
		this.storeid = storeid;
	}

	public BigDecimal getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(BigDecimal supervisor) {
		this.supervisor = supervisor;
	}

	public String getTermid() {
		return termid;
	}

	public void setTermid(String termid) {
		this.termid = termid;
	}

	public BigDecimal getTerminalTrace() {
		return terminalTrace;
	}

	public void setTerminalTrace(BigDecimal terminalTrace) {
		this.terminalTrace = terminalTrace;
	}

	public String getTermloc() {
		return termloc;
	}

	public void setTermloc(String termloc) {
		this.termloc = termloc;
	}

	public BigDecimal getTraAmount() {
		return traAmount;
	}

	public void setTraAmount(BigDecimal traAmount) {
		this.traAmount = traAmount;
	}

	public Date getTraConvDate() {
		return traConvDate;
	}

	public void setTraConvDate(Date traConvDate) {
		this.traConvDate = traConvDate;
	}

	public BigDecimal getTraConvRate() {
		return traConvRate;
	}

	public void setTraConvRate(BigDecimal traConvRate) {
		this.traConvRate = traConvRate;
	}

	public BigDecimal getTraCurrencyCode() {
		return traCurrencyCode;
	}

	public void setTraCurrencyCode(BigDecimal traCurrencyCode) {
		this.traCurrencyCode = traCurrencyCode;
	}

	public BigDecimal getTrace() {
		return trace;
	}

	public void setTrace(BigDecimal trace) {
		this.trace = trace;
	}

	public Date getTrandate() {
		return trandate;
	}

	public void setTrandate(Date trandate) {
		this.trandate = trandate;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getTransferee() {
		return transferee;
	}

	public void setTransferee(String transferee) {
		this.transferee = transferee;
	}

	public BigDecimal getTrantime() {
		return trantime;
	}

	public void setTrantime(BigDecimal trantime) {
		this.trantime = trantime;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public Date getTxnConvDate() {
		return txnConvDate;
	}

	public void setTxnConvDate(Date txnConvDate) {
		this.txnConvDate = txnConvDate;
	}

	public BigDecimal getTxnConvRate() {
		return txnConvRate;
	}

	public void setTxnConvRate(BigDecimal txnConvRate) {
		this.txnConvRate = txnConvRate;
	}

	public BigDecimal getTxnCurrencyCode() {
		return txnCurrencyCode;
	}

	public void setTxnCurrencyCode(BigDecimal txnCurrencyCode) {
		this.txnCurrencyCode = txnCurrencyCode;
	}

	public BigDecimal getTxnEndTime() {
		return txnEndTime;
	}

	public void setTxnEndTime(BigDecimal txnEndTime) {
		this.txnEndTime = txnEndTime;
	}

	public BigDecimal getTxnNewAmount() {
		return txnNewAmount;
	}

	public void setTxnNewAmount(BigDecimal txnNewAmount) {
		this.txnNewAmount = txnNewAmount;
	}

	public BigDecimal getTxnStartTime() {
		return txnStartTime;
	}

	public void setTxnStartTime(BigDecimal txnStartTime) {
		this.txnStartTime = txnStartTime;
	}

	public String getTxndest() {
		return txndest;
	}

	public void setTxndest(String txndest) {
		this.txndest = txndest;
	}

	public String getTxnsrc() {
		return txnsrc;
	}

	public void setTxnsrc(String txnsrc) {
		this.txnsrc = txnsrc;
	}

	public BigDecimal getTxntype() {
		return txntype;
	}

	public void setTxntype(BigDecimal txntype) {
		this.txntype = txntype;
	}

	@Override
	public String toString() {
		return "{shclog:[" + this.shcLogKey.getPan() + ","+ this.shcLogKey.getRefnum() + ","
				+ acctnum +"," + acquirer + "," + fId +"," + termid + "," + termloc + "," + respcode + "," + this.acqCurrencyCode + ","+amount + "," + merchantType +"," + msgtype
				+ "]}";
	}			
}