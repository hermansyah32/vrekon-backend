package com.mpc.vrekon.util.sourceconfig.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class DBHelper {
    @PersistenceContext
    EntityManager entityManager;
    private Logger log = Logger.getLogger(getClass());
    private String table = "";
    private String select = "*";
    private String where = "where ";
    private String order = "order by id desc ";
    private String leftJoin = "";
    private String rightJoin = "";
    private Map<String, Object> param = new HashMap<>();

    private void resetParam(){
        this.table = "";
        this.select = "*";
        this.where = "where ";
        this.order = "order by id desc ";
        this.leftJoin = "";
        log.info("Param Clear");
        this.param.clear();
        this.logParam();
    }

    private void logParam(){
        log.info("[table="+table+","+
                "select="+select+","+
                "where="+where+","+
                "order="+order+","+
                "leftJoin="+leftJoin+","+
                "param="+param+"]"
        );
    }

    public DBHelper select(String select){
        this.select = select;
        return this;
    }

    public DBHelper pluck(String name, String id){
        this.select = name + "," + id;
        return this;
    }

    public  DBHelper table(String table){
        this.table = table;
        log.info(table);
        return this;
    }

    public DBHelper where(String field, String value){
        this.where += field + "=:" + field + " and ";
        param.put(new StringFormatHelper().strConvertCU(field),value);
        return this;
    }

    public DBHelper where(String field, String operator, String value){
        this.where += field + operator + ":" + field + " and ";
        param.put(new StringFormatHelper().strConvertCU(field),value);
        return this;
    }

    public DBHelper where(String sqlWhere, String[] paramWhere){
        String tmpSql[] = sqlWhere.split("\\?");
        sqlWhere = "";
        for(Integer i = 0; i< tmpSql.length; i++){
            if (tmpSql[i].contains("=") || tmpSql[i].contains("<>") || tmpSql[i].contains("like")){
                sqlWhere += tmpSql[i] + ":where" + i + " ";
            }else{
                sqlWhere += tmpSql[i];
            }
        }

        for (Integer i = 0; i < paramWhere.length; i++){
            param.put("where"+i, paramWhere[i]);
        }

        this.where += sqlWhere + " and ";
        return this;
    }

    public DBHelper orderBy(String field, String sortBy){
        order = "order by " + field + " " + sortBy;
        return this;
    }

    public DBHelper leftJoin(String table, String field1, String filed2){
        leftJoin = "left join " + table + " on " + field1 + "=" + filed2;
        return this;
    }
    public DBHelper rightJoin(String table, String field1, String filed2){
        leftJoin = "right join " + table + " on " + field1 + "=" + filed2;
        return this;
    }

    public List<Map<String, Object>> get() throws Exception{
        String sql = "";
        if (where.equals("where ")){
            sql = "select " + select + " from "+ table + " " + leftJoin + " " + order;
        }else{
            sql = "select " + select + " from " + table + " " + leftJoin + " " + where + "= " + order;
            sql = sql.replace(" and =", "");
        }

        sql = sql.replace("  ", " ");
//        this.logParam();
//        log.info(table+"---"+sql);

        Query persistanceQuery = entityManager.createNativeQuery(sql);
        Session session = (Session) persistanceQuery;
        org.hibernate.Query query = ((org.hibernate.Query) persistanceQuery);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        for (Map.Entry<String, Object> mapString : param.entrySet()){
            query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
        }

        this.resetParam();
        List<Map<String, Object>> result = query.getResultList();

        return result;
    }

    public List<Map<String, Object>> get(Boolean hideDeletedAt) throws Exception{
        String sql = "";

        if (hideDeletedAt){
            where += table + ".deleted_at is null and ";
        }else{
            where += table + ".deleted_at is not null and ";
        }

        if (where.equals("where ")){
            sql = "select " + select + " from "+ table + " " + leftJoin + " " + order;
        }else{
            sql = "select " + select + " from " + table + " " + leftJoin + " " + where + "= " + order;
            sql = sql.replace(" and =", "");
        }

        sql = sql.replace("  ", " ");
//        this.logParam();
//        log.info(table+"---"+sql);

        Query persistanceQuery = entityManager.createNativeQuery(sql);
        org.hibernate.Query query = ((org.hibernate.Query) persistanceQuery);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        for (Map.Entry<String, Object> mapString : param.entrySet()){
            query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
        }

        this.resetParam();
        List<Map<String, Object>> result = query.getResultList();

        return result;
    }

    public Map<String, Object> first(){
        try{
            String sql = "";
            if (where.equals("where ")){
                sql = "select " + select + " from "+ table + " " + leftJoin + " " + order;
            }else{
                sql = "select " + select + " from " + table + " " + leftJoin + " " + where + "= " + order;
                sql = sql.replace(" and =", "");
            }

            sql = sql.replace("  ", " ");
//            this.logParam();
//            log.info(table+"---"+sql);

            Query persistanceQuery = entityManager.createNativeQuery(sql);
            org.hibernate.Query query = ((org.hibernate.Query) persistanceQuery);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            for (Map.Entry<String, Object> mapString : param.entrySet()){
                query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
            }

            this.resetParam();

            Map<String, Object> result = (Map<String, Object>) query.getSingleResult();
            return result;
        }catch (NoResultException e){
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public Map<String, Object> first(Boolean hideDeletedAt){
        try{
            String sql = "";

            if (hideDeletedAt){
                where += table + ".deleted_at is null and ";
            }else{
                where += table + ".deleted_at is not null and ";
            }

            if (where.equals("where ")){
                sql = "select " + select + " from "+ table + " " + leftJoin + " " + order;
            }else{
                sql = "select " + select + " from " + table + " " + leftJoin + " " + where + "= " + order;
                sql = sql.replace(" and =", "");
            }

            sql = sql.replace("  ", " ");
            Query persistanceQuery = entityManager.createNativeQuery(sql);
            org.hibernate.Query query = ((org.hibernate.Query) persistanceQuery);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            for (Map.Entry<String, Object> mapString : param.entrySet()){
                query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
            }

            this.resetParam();

            Map<String, Object> result = (Map<String, Object>) query.getSingleResult();
            return result;
        }catch (NoResultException e){
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public Map<String, Object> paginate(Integer showData, Integer page) throws Exception{
        Map<String, Object> result = new TreeMap<>();
        Integer offset = page.equals(1) ? offset = 0 :  (offset = showData * (page - 1));
        Integer dataCount = 0;
        Integer maxPage = 0;
        String sql = "";
        String sqlCount = "";

        if (where.equals("where ")){
            sql = "select " + this.select + " from " + this.table + " " + this.leftJoin + " " + this.order + " limit " + showData + " offset "+offset;
            sqlCount = "select count(*) as count from " + this.table + " " + this.leftJoin  + " limit " + showData + " offset "+offset;
        }else{
            sql = "select " + this.select + " from " + this.table + " " + this.leftJoin + " " + this.where  + "= " + this.order + " limit " + showData + " offset "+offset;
            sqlCount = "select count(*) as count from " + this.table + " " + this.leftJoin + " " + this.where  + "= " + " limit " + showData + " offset "+offset;
        }

        sql = sql.replace(" and =","");
        sql = sql.replace("  ", " ");

        sqlCount = sqlCount.replace(" and =","");
        sqlCount = sqlCount.replace("  ", " ");

        Query persistanceQuery = entityManager.createNativeQuery(sql);
        org.hibernate.Query query = ((org.hibernate.Query) persistanceQuery);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        for (Map.Entry<String, Object> mapString : param.entrySet()){
            query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
        }

        List<Map<String, Object>> dataObject = query.getResultList();

        Query queryCount = entityManager.createNativeQuery(sqlCount);
        for (Map.Entry<String, Object> mapString : param.entrySet()){
            queryCount.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
        }

        this.resetParam();

        dataCount = Integer.parseInt(queryCount.getSingleResult().toString());

        if (dataCount < showData) {
            maxPage = 1;
        }else if (dataCount % showData == 0){
            maxPage = dataCount / showData;
        }else{
            maxPage = ((dataCount - (dataCount % showData)) / showData) + 1;
        }

        result.put("countData", dataCount);
        result.put("currentPage", page);
        result.put("countMaximalPage", maxPage);
        result.put("countdataPerPage", showData);
        result.put("dataList",dataObject);

        return result;
    }

    public Map<String, Object> paginate(Integer showData, Integer page, Boolean hideDeletedAt) throws Exception{
        Map<String, Object> result = new TreeMap<>();
        Integer offset = page.equals(1) ? offset = 0 :  (offset = showData * (page - 1));
        Integer dataCount = 0;
        Integer maxPage = 0;
        String sql = "";
        String sqlCount = "";

        if (hideDeletedAt){
            where += table + ".deleted_at is null and ";
        }else{
            where += table + ".deleted_at is not null and ";
        }

        if (where.equals("where ")){
            sql = "select " + this.select + " from " + this.table + " " + this.leftJoin + " " + this.order + " limit " + showData + " offset "+offset;
            sqlCount = "select count(*) as count from " + this.table + " " + this.leftJoin  + " limit " + showData + " offset "+offset;
        }else{
            sql = "select " + this.select + " from " + this.table + " " + this.leftJoin + " " + this.where  + "= " + this.order + " limit " + showData + " offset "+offset;
            sqlCount = "select count(*) as count from " + this.table + " " + this.leftJoin + " " + this.where  + "= " + " limit " + showData + " offset "+offset;
        }

        sql = sql.replace(" and =","");
        sql = sql.replace("  ", " ");

        sqlCount = sqlCount.replace(" and =","");
        sqlCount = sqlCount.replace("  ", " ");

        Query persistanceQuery = entityManager.createNativeQuery(sql);
        org.hibernate.Query query = ((org.hibernate.Query) persistanceQuery);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        for (Map.Entry<String, Object> mapString : param.entrySet()){
            query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
        }

        List<Map<String, Object>> dataObject = query.getResultList();

        Query queryCount = entityManager.createNativeQuery(sqlCount);
        for (Map.Entry<String, Object> mapString : param.entrySet()){
            queryCount.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
        }

        this.resetParam();

        dataCount = Integer.parseInt(queryCount.getSingleResult().toString());

        if (dataCount < showData) {
            maxPage = 1;
        }else if (dataCount % showData == 0){
            maxPage = dataCount / showData;
        }else{
            maxPage = ((dataCount - (dataCount % showData)) / showData) + 1;
        }

        result.put("countData", dataCount);
        result.put("currentPage", page);
        result.put("countMaximalPage", maxPage);
        result.put("countdataPerPage", showData);
        result.put("dataList",dataObject);

        return result;
    }

    public Integer count() throws Exception{
        String sql = "";
        if (where.equals("where ")){
            sql = "select count(*) as count from "+ table + " " + leftJoin;
        }else{
            sql = "select count(*) as count from " + table + " " + leftJoin + " " + where + "=";
            sql = sql.replace(" and =", "");
        }

        sql = sql.replace("  ", " ");
        Query query = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> mapString : param.entrySet()){
            query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
        }

        this.resetParam();
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public Integer count(Boolean hideDeletedAt) throws Exception{
        String sql = "";

        if (hideDeletedAt){
            where += table + ".deleted_at is null and ";
        }else{
            where += table + ".deleted_at is not null and ";
        }

        if (where.equals("where ")){
            sql = "select count(*) as count from "+ table + " " + leftJoin;
        }else{
            sql = "select count(*) as count from " + table + " " + leftJoin + " " + where + "=";
            sql = sql.replace(" and =", "");
        }

        sql = sql.replace("  ", " ");
        log.info(sql);
        Query query = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> mapString : param.entrySet()){
            query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
        }

        this.resetParam();
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public Boolean insert(Object object) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapObject = objectMapper.convertValue(object, Map.class);

        String sql = "insert into "+table;
        String field = "(";
        String values = "values(";

        for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
            field += new StringFormatHelper().strConvertCU(mapString.getKey()) + ",";
            values += ":" + new StringFormatHelper().strConvertCU(mapString.getKey()) + ",";
        }

        field += ")";
        field = field.replace(",)",")");
        values += ")";
        values = values.replace(",)",")");

        sql = sql + field + " " + values;

        sql = sql.replace("  ", " ");
        Query query = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
            query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
        }

        if (query.executeUpdate() == 1){
            return true;
        }else{
            return false;
        }
    }

    public Integer insertGetId(Object object) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapObject = objectMapper.convertValue(object, Map.class);

        String sql = "insert into "+table;
        String field = "(";
        String values = "values(";

        String sqlSelect = "select id from " + table + "where ";
        String whereSelect = "";

        for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
            field += new StringFormatHelper().strConvertCU(mapString.getKey()) + ",";
            values += ":" + new StringFormatHelper().strConvertCU(mapString.getKey()) + ",";
            whereSelect += new StringFormatHelper().strConvertCU(mapString.getKey()) + "=:" + new StringFormatHelper().strConvertCU(mapString.getKey()) + " and ";
        }

        field += ")";
        field = field.replace(",)",")");
        values += ")";
        values = values.replace(",)",")");

        sql = sql + field + " " + values;

        sqlSelect = sqlSelect + whereSelect + "=";
        sqlSelect = sqlSelect.replace(" and =", "");

        sql = sql.replace("  ", " ");
        sqlSelect = sqlSelect.replace("  ", " ");
        Query query = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
            query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
        }

        if (query.executeUpdate() == 1){
            Query querySelect = entityManager.createNativeQuery(sqlSelect);
            Integer id = Integer.parseInt(querySelect.getSingleResult().toString());
            if (id.equals(null) || id.equals(0)){
                return 0;
            }else{
                return id;
            }
        }else{
            return 0;
        }
    }

    public Boolean insert(Object object, Boolean validation) throws Exception{
        if (validation){
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> mapObject = objectMapper.convertValue(object, Map.class);

            String sql = "insert into "+table;
            String field = "(";
            String values = "values(";

            String sqlCount = "select count(*) as count from " + table + " where ";
            String whereCount = "";

            for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
                String tmpMapValue = mapString.getValue() == null ? "" : mapString.getValue().toString();
                if (!( tmpMapValue.equals("") || mapString.getKey().equals("createdAt") || mapString.getKey().equals("updatedAt") || mapString.getKey().equals("deletedAt") )) {
                    whereCount += new StringFormatHelper().strConvertCU(mapString.getKey()) + "=:" + new StringFormatHelper().strConvertCU(mapString.getKey()) + " and ";
                    field += new StringFormatHelper().strConvertCU(mapString.getKey()) + ",";
                    values += ":" + new StringFormatHelper().strConvertCU(mapString.getKey()) + ",";
                }
            }

            field += ")";
            field = field.replace(",)",")");
            values += ")";
            values = values.replace(",)",")");

            whereCount += "=";
            whereCount = whereCount.replace(" and =", "");
            sqlCount = sqlCount + whereCount;

            sql = sql + field + " " + values;
            sql = sql.replace("  ", " ");
            sqlCount = sqlCount.replace("  ", " ");
            Query query = entityManager.createNativeQuery(sqlCount);
            for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
                String tmpMapValue = mapString.getValue() == null ? "" : mapString.getValue().toString();
                if (!( tmpMapValue.equals("") || mapString.getKey().equals("createdAt") || mapString.getKey().equals("updatedAt") || mapString.getKey().equals("deletedAt") )) {
                    query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
                }
            }

            Integer countData = Integer.parseInt(query.getSingleResult().toString());

            if (countData == 0){
                Query queryInsert = entityManager.createNativeQuery(sql);
                for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
                    String tmpMapValue = mapString.getValue() == null ? "" : mapString.getValue().toString();
                    if (!( tmpMapValue.equals("") || mapString.getKey().equals("createdAt") || mapString.getKey().equals("updatedAt") || mapString.getKey().equals("deletedAt") )) {
                        queryInsert.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
                    }
                }

                if (queryInsert.executeUpdate() == 1){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else {
            return this.insert(object);
        }
    }

    public Boolean insert(List<Object> values, Boolean validation) throws Exception{
        for (Object object : values){
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> mapObject = objectMapper.convertValue(object, Map.class);
            mapObject.remove("id");

            if (validation){
                String sqlCount = "select count(*) as count from " + table + " where ";
                String whereCount = "";

                String sqlInsert = "insert into " + table + "(";
                String sqlInsertValue = " values(";

                for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
                    String tmpMapValue = mapString.getValue() == null ? "" : mapString.getValue().toString();
                    if (!( tmpMapValue.equals("") || mapString.getKey().equals("createdAt") || mapString.getKey().equals("updatedAt") || mapString.getKey().equals("deletedAt") )){
                        whereCount += new StringFormatHelper().strConvertCU(mapString.getKey()) + "=:" + new StringFormatHelper().strConvertCU(mapString.getKey()) + " and ";
                        sqlInsert += new StringFormatHelper().strConvertCU(mapString.getKey()) + ",";
                        sqlInsertValue += ":" + new StringFormatHelper().strConvertCU(mapString.getKey()) + ",";
                    }
                }

                sqlInsert += ")";
                sqlInsert = sqlInsert.replace(",)",")");
                sqlInsertValue += ")";
                sqlInsertValue = sqlInsertValue.replace(",)",")");
                sqlInsert = sqlInsert + sqlInsertValue;

                whereCount += "=";
                whereCount = whereCount.replace(" and =", "");
                sqlCount = sqlCount + whereCount;

                Query query = entityManager.createNativeQuery(sqlCount);

                for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
                    String tmpMapValue = mapString.getValue() == null ? "" : mapString.getValue().toString();
                    if (!( tmpMapValue.equals("") || mapString.getKey().equals("createdAt") || mapString.getKey().equals("updatedAt") || mapString.getKey().equals("deletedAt") )){
                        query.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
                    }
                }

                Integer countData = Integer.parseInt(query.getSingleResult().toString());

                if (countData == 0){
                    Query queryInsert = entityManager.createNativeQuery(sqlInsert);
                    for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
                        String tmpMapValue = mapString.getValue() == null ? "" : mapString.getValue().toString();
                        if (!( tmpMapValue.equals("") || mapString.getKey().equals("createdAt") || mapString.getKey().equals("updatedAt") || mapString.getKey().equals("deletedAt") )){
                            queryInsert.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
                        }
                    }
                    queryInsert.executeUpdate();
                }
            }else{
                String sqlInsert = "insert into " + table + "(";
                String sqlInsertValue = " values(";

                for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
                    sqlInsert += new StringFormatHelper().strConvertCU(mapString.getKey()) + ",";
                    sqlInsertValue += ":" + new StringFormatHelper().strConvertCU(mapString.getKey()) + ",";
                }

                sqlInsert += ")";
                sqlInsert = sqlInsert.replace(",)",")");
                sqlInsertValue += ")";
                sqlInsertValue = sqlInsertValue.replace(",)",")");
                sqlInsert = sqlInsert + sqlInsertValue;

                Query queryInsert = entityManager.createNativeQuery(sqlInsert);
                for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
                    queryInsert.setParameter(new StringFormatHelper().strConvertCU(mapString.getKey()), mapString.getValue());
                }
                queryInsert.executeUpdate();
            }

        }
        return true;
    }

    public Boolean update(Object object) throws  Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapObject = objectMapper.convertValue(object, Map.class);
        String sql = "update " + table + " set ";
        for (Map.Entry<String, Object> mapString : mapObject.entrySet()){
            sql += new StringFormatHelper().strConvertCU( mapString.getKey() ) + "=:" +  new StringFormatHelper().strConvertCU( mapString.getKey() ) + ",";
            param.put(new StringFormatHelper().strConvertCU( mapString.getKey() ), mapString.getValue());
        }
        sql += "= " + where + "= ";
        sql = sql.replace(",="," ");
        sql = sql.replace(" and =","");
        sql = sql.replace("  ", " ");

        Query query = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> mapString : param.entrySet()){
            query.setParameter(new StringFormatHelper().strConvertCU( mapString.getKey() ), mapString.getValue());
        }

        this.resetParam();

        if (query.executeUpdate() == 1){
            return true;
        }else{
            return false;
        }
    }

    public Boolean update(String sqlUpdate, String[] paramUpdate) throws  Exception{
        String tmpSql[] = sqlUpdate.split("\\?");

        sqlUpdate = "";
        for(Integer i = 0; i< tmpSql.length; i++){
            if (tmpSql[i].contains("=") || tmpSql[i].contains("<>") || tmpSql[i].contains("like")){
                sqlUpdate += tmpSql[i] + ":update" + i + " ";
            }else{
                sqlUpdate += tmpSql[i];
            }
        }

        for (Integer i = 0; i < paramUpdate.length; i++){
           param.put("update"+i, paramUpdate[i]);
        }

        String sql = "update " + table + " set " + sqlUpdate + " " + where + "=";
        sql = sql.replace(" and =", "");
        sql = sql.replace("  ", " ");

        Query query = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> mapString : param.entrySet()){
            query.setParameter(new StringFormatHelper().strConvertCU( mapString.getKey() ), mapString.getValue());
        }

        this.resetParam();

        if (query.executeUpdate() == 1){
            return true;
        }else{
            return false;
        }
    }
}
