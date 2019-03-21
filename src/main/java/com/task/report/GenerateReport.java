package com.task.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.log4j.Logger;

import com.task.form.LoginForm;
import com.task.form.TaskSheetForm;
import com.task.utils.DateUtils;
import com.task.utils.UIConstants;

/**
 * Servlet implementation class GenerateReport
 */
public class GenerateReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass()); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doTask(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doTask(request,response);
	}
	
	
	protected void doTask(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//To get Task List
		TaskSheetForm taskSheetForm = (TaskSheetForm) request.getSession().getAttribute("taskSheetForm");
		 //To get Employee Id and Full name
        LoginForm loginForm = (LoginForm) request.getSession().getAttribute("loginForm");        
        
		try {						
			if(loginForm == null && taskSheetForm ==null) {
				throw new Exception("Session Expried");
			}
			
			//fill it in JRBeanCollectionDS
			JRDataSource dataSource = new JRBeanCollectionDataSource(taskSheetForm.getTaskList());	        
	        
	        //Mapping the report jasper file
	        ServletContext context = this.getServletConfig().getServletContext();
	        logger.info("Report context Path " + context.getRealPath("/"));        

	        //For Full Path of Jasper file
	        File reportFile = new File(context.getRealPath("/")+"/Report/TaskReport.jasper");
	        String reportPath = reportFile.getAbsolutePath();
	        logger.info("Report Absolute Path ="+reportFile.getAbsolutePath());
	        
	        String report_title = DateUtils.reportDate(taskSheetForm.getFromDate(),taskSheetForm.getToDate(),UIConstants.reportName);
	        String sheet_title = DateUtils.reportDate(taskSheetForm.getFromDate(),taskSheetForm.getToDate(),UIConstants.sheetName);
	        
	        //To pass Parameters to the Map
	        Map param = new HashMap();
            //param.put("user_id",taskSheetForm.getUserId());
            //param.put("from_date",taskSheetForm.getFromDate());
            //param.put("to_date",taskSheetForm.getToDate());
            param.put("report_title",UIConstants.reportTitle+report_title);
            param.put("emp_id", loginForm.getEmpID());
            param.put("full_name", loginForm.getFullName());
            
            
            JasperPrint jasperPrint =  JasperFillManager.fillReport(reportPath, param , dataSource);
            
            // To generate Report as Excel
            JRXlsExporter exporter = new JRXlsExporter();
            String[] sheetNames = {"Time Sheet "+sheet_title,"Sheet2"};
            
            ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, xlsReport);
            
            //exporter.setParameter(JRExporterParameter.OUTPUT_FILE, "c:\\");
           // exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "\\Report\\report.xls");
            
            exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetNames);
            exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            //exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
           // exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);            
            exporter.exportReport();

            logger.info("Size of byte array :"+xlsReport.size());
            //bytes = xlsReport.toByteArray();
            String filename = UIConstants.reportFileName+getFullEmpId(loginForm.getEmpID(), loginForm.getFullName())+".xls";
            String attachment = "attachment;filename=\""+filename+"\"";            
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition",attachment);
            OutputStream outputStream = response.getOutputStream();
            xlsReport.close();
            
            xlsReport.writeTo(outputStream); 
            outputStream.flush();
            outputStream.close();	        
		} catch (Exception e) {
			logger.error("Error : ",e);
		}  
	}
	
	public String getFullEmpId(Integer empId, String fullName) {
		return fullName.substring(0, 4).toUpperCase()+empId.toString();
	}

}
