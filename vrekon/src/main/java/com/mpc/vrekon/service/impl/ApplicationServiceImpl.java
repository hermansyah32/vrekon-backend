package com.mpc.vrekon.service.impl;

import com.mpc.vrekon.model.RekonCompareRequest;
import com.mpc.vrekon.repository.ApplicationRepository;
import com.mpc.vrekon.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;

    public Map<String, List<?>> application(RekonCompareRequest rekonCompareRequest, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        return null;
    }

    public Map<String, List<?>> applicationDetail(RekonCompareRequest rekonCompareRequest, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        return null;
    }

    public Map<String, List<?>> applicationGetByID(RekonCompareRequest rekonCompareRequest, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        return null;
    }

    public Map<String, List<?>> applicationAdd(RekonCompareRequest rekonCompareRequest, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        return null;
    }

    public Map<String, List<?>> applicationEdit(RekonCompareRequest rekonCompareRequest, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        return null;
    }

    public Map<String, List<?>> applicationDelete(RekonCompareRequest rekonCompareRequest, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        return null;
    }
}
