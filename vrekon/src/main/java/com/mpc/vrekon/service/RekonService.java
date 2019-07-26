package com.mpc.vrekon.service;

import java.util.List;

import com.mpc.vrekon.model.RekonCompareResponse;
import com.mpc.vrekon.model.RekonCompareRequest;

public interface RekonService {
	
	List<RekonCompareResponse> compareData(RekonCompareRequest rekonCompareRequest);
}
