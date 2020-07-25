/*
 * Generated by JasperReports - 8/30/19 1:57 PM
 */

import net.sf.jasperreports.engine.fill.JREvaluator
import net.sf.jasperreports.engine.fill.JRFillField
import net.sf.jasperreports.engine.fill.JRFillParameter
import net.sf.jasperreports.engine.fill.JRFillVariable

import java.text.SimpleDateFormat

/**
 *
 */
class rekon45report_1567148251237_336684 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_REPORT_VIRTUALIZER = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_REPORT_FILE_RESOLVER = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_REPORT_URL_HANDLER_FACTORY = null;
    private JRFillParameter parameter_IS_IGNORE_PAGINATION = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillParameter parameter_Date = null;
    private JRFillParameter parameter_Page = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillField field_localDate1 = null;
    private JRFillField field_acquirer1 = null;
    private JRFillField field_localDate2 = null;
    private JRFillField field_acquirer2 = null;
    private JRFillField field_id2 = null;
    private JRFillField field_id1 = null;
    private JRFillField field_idService2 = null;
    private JRFillField field_localTime1 = null;
    private JRFillField field_idService1 = null;
    private JRFillField field_localTime2 = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;
    private JRFillVariable variable_no = null;


    /**
     *
     */
    void customizedInit(
        Map pm,
        Map fm,
        Map vm
        )
    {
        initParams(pm);
        initFields(fm);
        initVars(vm);
    }


    /**
     *
     */
    void initParams(Map pm)
    {
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_REPORT_VIRTUALIZER = (JRFillParameter)pm.get("REPORT_VIRTUALIZER");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_REPORT_FILE_RESOLVER = (JRFillParameter)pm.get("REPORT_FILE_RESOLVER");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_REPORT_URL_HANDLER_FACTORY = (JRFillParameter)pm.get("REPORT_URL_HANDLER_FACTORY");
        parameter_IS_IGNORE_PAGINATION = (JRFillParameter)pm.get("IS_IGNORE_PAGINATION");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
        parameter_Date = (JRFillParameter)pm.get("Date");
        parameter_Page = (JRFillParameter)pm.get("Page");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
    }


    /**
     *
     */
    void initFields(Map fm)
    {
        field_localDate1 = (JRFillField)fm.get("localDate1");
        field_acquirer1 = (JRFillField)fm.get("acquirer1");
        field_localDate2 = (JRFillField)fm.get("localDate2");
        field_acquirer2 = (JRFillField)fm.get("acquirer2");
        field_id2 = (JRFillField)fm.get("id2");
        field_id1 = (JRFillField)fm.get("id1");
        field_idService2 = (JRFillField)fm.get("idService2");
        field_localTime1 = (JRFillField)fm.get("localTime1");
        field_idService1 = (JRFillField)fm.get("idService1");
        field_localTime2 = (JRFillField)fm.get("localTime2");
    }


    /**
     *
     */
    void initVars(Map vm)
    {
        variable_PAGE_NUMBER = (JRFillVariable)vm.get("PAGE_NUMBER");
        variable_COLUMN_NUMBER = (JRFillVariable)vm.get("COLUMN_NUMBER");
        variable_REPORT_COUNT = (JRFillVariable)vm.get("REPORT_COUNT");
        variable_PAGE_COUNT = (JRFillVariable)vm.get("PAGE_COUNT");
        variable_COLUMN_COUNT = (JRFillVariable)vm.get("COLUMN_COUNT");
        variable_no = (JRFillVariable)vm.get("no");
    }


    /**
     *
     */
    Object evaluate(int id)
    {
        Object value = null;

        if (id == 0)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 1)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 2)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 3)
        {
            value = (java.lang.Integer)(new Integer(0));
        }
        else if (id == 4)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 5)
        {
            value = (java.lang.Integer)(new Integer(0));
        }
        else if (id == 6)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 7)
        {
            value = (java.lang.Integer)(new Integer(0));
        }
        else if (id == 8)
        {
            value = (java.lang.Object)(1);
        }
        else if (id == 9)
        {
            value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getValue()));
        }
        else if (id == 10)
        {
            value = (java.lang.String)((((java.lang.String)parameter_Date.getValue())==null || ((java.lang.String)parameter_Date.getValue()).equals("")) ? new SimpleDateFormat("dd-MMM-yyyy").format(new Date()) : ((java.lang.String)parameter_Date.getValue()));
        }
        else if (id == 11)
        {
            value = (java.lang.String)(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        }
        else if (id == 12)
        {
            value = (java.lang.Integer)(((java.lang.Integer)variable_no.getValue()));
        }
        else if (id == 13)
        {
            value = (java.lang.String)(((java.lang.String)field_acquirer1.getValue()));
        }
        else if (id == 14)
        {
            value = (java.lang.String)(((java.lang.String)field_localTime1.getValue()));
        }
        else if (id == 15)
        {
            value = (java.lang.String)(((java.lang.String)field_localDate1.getValue()));
        }
        else if (id == 16)
        {
            value = (java.lang.String)(((java.lang.String)field_acquirer1.getValue()));
        }
        else if (id == 17)
        {
            value = (java.lang.String)(((java.lang.String)field_localTime1.getValue()));
        }
        else if (id == 18)
        {
            value = (java.lang.String)(((java.lang.String)field_localDate1.getValue()));
        }

        return value;
    }


    /**
     *
     */
    Object evaluateOld(int id)
    {
        Object value = null;

        if (id == 0)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 1)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 2)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 3)
        {
            value = (java.lang.Integer)(new Integer(0));
        }
        else if (id == 4)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 5)
        {
            value = (java.lang.Integer)(new Integer(0));
        }
        else if (id == 6)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 7)
        {
            value = (java.lang.Integer)(new Integer(0));
        }
        else if (id == 8)
        {
            value = (java.lang.Object)(1);
        }
        else if (id == 9)
        {
            value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getOldValue()));
        }
        else if (id == 10)
        {
            value = (java.lang.String)((((java.lang.String)parameter_Date.getValue())==null || ((java.lang.String)parameter_Date.getValue()).equals("")) ? new SimpleDateFormat("dd-MMM-yyyy").format(new Date()) : ((java.lang.String)parameter_Date.getValue()));
        }
        else if (id == 11)
        {
            value = (java.lang.String)(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        }
        else if (id == 12)
        {
            value = (java.lang.Integer)(((java.lang.Integer)variable_no.getOldValue()));
        }
        else if (id == 13)
        {
            value = (java.lang.String)(((java.lang.String)field_acquirer1.getOldValue()));
        }
        else if (id == 14)
        {
            value = (java.lang.String)(((java.lang.String)field_localTime1.getOldValue()));
        }
        else if (id == 15)
        {
            value = (java.lang.String)(((java.lang.String)field_localDate1.getOldValue()));
        }
        else if (id == 16)
        {
            value = (java.lang.String)(((java.lang.String)field_acquirer1.getOldValue()));
        }
        else if (id == 17)
        {
            value = (java.lang.String)(((java.lang.String)field_localTime1.getOldValue()));
        }
        else if (id == 18)
        {
            value = (java.lang.String)(((java.lang.String)field_localDate1.getOldValue()));
        }

        return value;
    }


    /**
     *
     */
    Object evaluateEstimated(int id)
    {
        Object value = null;

        if (id == 0)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 1)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 2)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 3)
        {
            value = (java.lang.Integer)(new Integer(0));
        }
        else if (id == 4)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 5)
        {
            value = (java.lang.Integer)(new Integer(0));
        }
        else if (id == 6)
        {
            value = (java.lang.Integer)(new Integer(1));
        }
        else if (id == 7)
        {
            value = (java.lang.Integer)(new Integer(0));
        }
        else if (id == 8)
        {
            value = (java.lang.Object)(1);
        }
        else if (id == 9)
        {
            value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue()));
        }
        else if (id == 10)
        {
            value = (java.lang.String)((((java.lang.String)parameter_Date.getValue())==null || ((java.lang.String)parameter_Date.getValue()).equals("")) ? new SimpleDateFormat("dd-MMM-yyyy").format(new Date()) : ((java.lang.String)parameter_Date.getValue()));
        }
        else if (id == 11)
        {
            value = (java.lang.String)(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        }
        else if (id == 12)
        {
            value = (java.lang.Integer)(((java.lang.Integer)variable_no.getEstimatedValue()));
        }
        else if (id == 13)
        {
            value = (java.lang.String)(((java.lang.String)field_acquirer1.getValue()));
        }
        else if (id == 14)
        {
            value = (java.lang.String)(((java.lang.String)field_localTime1.getValue()));
        }
        else if (id == 15)
        {
            value = (java.lang.String)(((java.lang.String)field_localDate1.getValue()));
        }
        else if (id == 16)
        {
            value = (java.lang.String)(((java.lang.String)field_acquirer1.getValue()));
        }
        else if (id == 17)
        {
            value = (java.lang.String)(((java.lang.String)field_localTime1.getValue()));
        }
        else if (id == 18)
        {
            value = (java.lang.String)(((java.lang.String)field_localDate1.getValue()));
        }

        return value;
    }


}
