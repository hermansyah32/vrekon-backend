package com.mpc.vrekon.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface TemporaryRepository {
    @Insert(CREATE)
    @Options(useGeneratedKeys = true, keyProperty = "object.id", flushCache = true)
    public int write(@Param("tablename") String tablename,
                     @Param("object") Object object) throws Exception;
}
