package com.longti.upjc.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import net.sf.json.JSONObject;

public class NetUtils {
	protected final static transient Log logger = LogFactory.getLog(NetUtils.class);
public static void JSONResponse(HashMap<String,Object> rv,HttpServletResponse response){
	response.setCharacterEncoding("UTF-8");
	PrintWriter out=null;
	try {
		out = response.getWriter();
		JSONObject json=JSONObject.fromObject(rv);
		out.println(json.toString());
		out.flush();
		out.close();	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		logger.error(e.getMessage());
	}		
}
}
