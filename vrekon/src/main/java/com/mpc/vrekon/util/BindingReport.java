package com.mpc.vrekon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author yovi.putra
 * 
 */
public class BindingReport {
	private final static Logger log = Logger.getLogger(BindingReport.class);	
	private static final Integer CHARACTER_WIDTH = 7;
	private static final Integer CHARACTER_HEIGHT = 15;
	
	/***
	 * 
	 * @param collection
	 * @param paramater
	 * @param template
	 * @param fileName
	 * @param response
	 * @param session
	 * @param isDownload
	 */
	public static void createTextFile(Collection<?> collection,Map<String,Object> paramater, 
			String template,String fileName,HttpServletResponse response,HttpSession session, boolean isDownload) {
		try {
			log.debug("Creating file text report("+ fileName +","+collection.size()+" record)...");
			ServletContext context = session.getServletContext();
			String pathOutput= context.getRealPath("") +"/"+ fileName;
			String tmplate =session.getServletContext().getRealPath("/WEB-INF/reports/"+ template +".jasper");
			InputStream input= new FileInputStream(new File(tmplate));
		    JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input);
		    JRDataSource jrDataSource = new JRBeanCollectionDataSource(collection);
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramater, jrDataSource);
		    
		    JRTextExporter exporter = new JRTextExporter();
		    exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, Float.parseFloat(""+CHARACTER_WIDTH));
		    exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, Float.parseFloat(""+CHARACTER_HEIGHT));
		    exporter.setParameter(JRTextExporterParameter.LINE_SEPARATOR, "\r\n");
		    
		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(new File(pathOutput)));
		    
		    exporter.exportReport();
		    log.debug("GENERATE REPORT "+ fileName + " SELESAI, Items: " + collection.size()+", : " + pathOutput);
		    
		    if(isDownload) downloadFile(fileName,session,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
	/***
	 * 
	 * @param collection
	 * @param parameter
	 * @param template
	 * @param filename
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	public static void createExcelFile(Collection<?> collection,Map<String,Object> parameter,String template, 
			String filename,HttpServletResponse response,HttpSession session, boolean isDownload) throws Exception{
		log.debug("Creating file xlsx report("+filename+","+collection.size()+" record)...");
		ServletContext context = session.getServletContext();
		String pathOutput= context.getRealPath("") +"/"+ filename;
		String tmplate =context.getRealPath("/WEB-INF/reports/"+ template +".jasper");
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(tmplate);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(collection);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, jrDataSource);
		
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(new File(pathOutput)));
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,true);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,true);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,false);
		exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,true);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,true);
		try{
			exporter.exportReport();
			
			 log.debug("GENERATE REPORT "+ filename + " SELESAI, Items: " + collection.size()+", : " + pathOutput);
			 if(isDownload) downloadFile(filename,session,response);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		
	}
	
	/***
	 * 
	 * @param session
	 * @param response
	 * @param template
	 * @param filename
	 * @param collection
	 * @param parameter
	 * @throws Exception
	 */
	public static File createPDFFile(Collection<?> collection,Map<String,Object> parameter,String template, 
			String filename,HttpServletResponse response,HttpSession session, boolean isDownload) throws Exception{
		log.debug("Creating file pdf ("+filename+","+collection.size()+" record)...");
		ServletContext context = session.getServletContext();
		String pathOutput= context.getRealPath("") +"/"+ filename;
		String tmplate = context.getRealPath("/WEB-INF/reports/"+ template +".jasper");
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(tmplate);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(collection);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, jrDataSource);
		
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, context.getRealPath("") +"/"+filename);
		try{
			exporter.exportReport();
			log.debug("GENERATE REPORT "+ filename + " SELESAI, Items: " + collection.size()+", : " + pathOutput);
			 if(isDownload) downloadFile(filename,session,response);
			return new File(context.getRealPath("") +"/"+filename);
		}catch(Exception e){
			log.equals(e);
		}
		return null;
	}
	
	/***
	 * 
	 * @param dirFile
	 * @param fileName
	 * @param files
	 * @return
	 */
	public static String createZipFile(String dirFile, String fileName, List<File> files){
		log.debug("Creating zip file ("+fileName+","+files.size()+" file)...");
		byte[] buffer = new byte[4096];
		try {
			  String fileDir = dirFile + "/" + fileName;
		      FileOutputStream fos = new FileOutputStream(fileDir);
		      ZipOutputStream zos = new ZipOutputStream(fos);
		      
		      for (File file : files) {
		    	  String fn = file.getName();
		    	  ZipEntry ze= new ZipEntry(fn);
		    	  zos.putNextEntry(ze);
		    	  FileInputStream in = new FileInputStream(file);
		    	  int len;
		    	  while ((len = in.read(buffer)) > 0) {
		    		  zos.write(buffer, 0, len);
		    	  }
		    	  
		    	  in.close();
		    	  zos.closeEntry();
		    	  if(file.delete()) log.debug("DELETE TEMPORARY FILE " + fn);
		      }
		      zos.close();
		      return fileDir;
		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		return null;
	}
	
	//============================== DOWNLOAD VIA STREAM ===================================
	/***
	 * 
	 * @param fileName
	 * @param session
	 * @param response
	 */
	public static void downloadFileAndSave( String fileName,HttpSession session, HttpServletResponse response){
		ServletContext context = session.getServletContext();
		downloadFileBase(context.getRealPath(""), fileName, session, response);
	}
	
	/***
	 * 
	 * @param fileName
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	public static void downloadFile(String fileName,HttpSession session,HttpServletResponse response) throws Exception{
		ServletContext context = session.getServletContext();
		File file = downloadFileBase(context.getRealPath(""), fileName, session,response);
		if(file.delete()) log.debug("DOWNLOAD BERHASIL, DAN DELETE FILE REPORT TEMPORARY");
	}
	
	/***
	 * 
	 * @param fileLoc
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static boolean downloadFileSpecificLoc(String fileLoc, HttpSession session, HttpServletResponse response) throws Exception{
		int divider = fileLoc.lastIndexOf("/");
		
		String dir;
		String filename;
		if(divider != -1){
			dir = fileLoc.substring(0, divider);
			filename = fileLoc.substring(divider + 1);
		}else{
			dir = "";
			filename = fileLoc;
		}
		if(downloadFileBase(dir, filename, session, response)!= null){
			return true;
		}else return false;
	}
	
	/***
	 * 
	 * @param dir
	 * @param fileName
	 * @param session
	 * @param response
	 * @return
	 */
	private static File downloadFileBase(String dir, String fileName, HttpSession session,HttpServletResponse response){
		File file;
		file = new File(dir + "/" + fileName);
		log.info("Download file "+ file.getName());

		InputStream is;
		try {
			is = new FileInputStream(file);
			ServletOutputStream out = response.getOutputStream();
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName);
			response.setHeader(headerKey, headerValue);
			response.setContentType("Content-type : text/zip");
			IOUtils.copy(is, out);
			is.close();
			out.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
			response.flushBuffer(); // ready to download
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return null;
	}
	
	/***
	 * @param entityManager
	 * @param sql
	 * @param obj
	 * @param isPaging
	 * @param rowOfPage
	 * @param pageNumber
	 * @return
	 */
	
	public static BigDecimal NativeQueryCount(EntityManager entityManager,String sql){
		log.debug(sql);
		BigDecimal count = new BigDecimal(0);
		try{
			Query queryData = entityManager.createNativeQuery(sql);
			count = (BigDecimal) queryData.getSingleResult();
			log.debug("Total record: "+ count);
		}catch(Exception e){
			log.error("Error get result query: dbm_err " + e.getMessage());
		}
		return count;
	}
	
	/***
	 * @param file
	 * @return
	 * @throws Exception
	 */

	
	public static File saveRemoteFile(MultipartFile multipart,String pathFile,String extentionAuth0) throws IOException{
		if(multipart.getOriginalFilename().endsWith(extentionAuth0)){
			//read file multipart
			byte[] bytes = multipart.getBytes();
			File file = new File(pathFile);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.close();
			return file;
		}
		return null;
	}
}