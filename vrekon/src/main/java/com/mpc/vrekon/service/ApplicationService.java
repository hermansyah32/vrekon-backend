package com.mpc.vrekon.service;

import com.mpc.vrekon.model.RekonCompareRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface ApplicationService {
    Map<String, List<?>> application (RekonCompareRequest rekonCompareRequest, HttpServletResponse response,
                                     HttpServletRequest request, HttpSession session);
    Map<String, List<?>> applicationDetail (RekonCompareRequest rekonCompareRequest, HttpServletResponse response,
                                      HttpServletRequest request, HttpSession session);
    Map<String, List<?>> applicationGetByID (RekonCompareRequest rekonCompareRequest, HttpServletResponse response,
                                      HttpServletRequest request, HttpSession session);
    Map<String, List<?>> applicationAdd (RekonCompareRequest rekonCompareRequest, HttpServletResponse response,
                                      HttpServletRequest request, HttpSession session);
    Map<String, List<?>> applicationEdit (RekonCompareRequest rekonCompareRequest, HttpServletResponse response,
                                      HttpServletRequest request, HttpSession session);
    Map<String, List<?>> applicationDelete (RekonCompareRequest rekonCompareRequest, HttpServletResponse response,
                                      HttpServletRequest request, HttpSession session);
}
