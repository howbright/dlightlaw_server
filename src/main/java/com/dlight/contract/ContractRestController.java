package com.dlight.contract;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractRestController implements ApplicationContextAware{
	
	@Autowired
	ContractService contractService;
	
	private String fileName;
	
	private ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
	
	@CrossOrigin("*")
	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> test() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "Hello world!");
		return map;

	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/file-list", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> getFileList(HttpServletRequest request) throws Exception {
		
		String path="/Users/hyun/Dlight/contracts";
		File dirFile=new File(path);
		File []fileList=dirFile.listFiles();
		
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		
		for(File tempFile : fileList) {
		  if(tempFile.isFile()) {
			  if(tempFile.getName().indexOf("docx") >=0){
				  Map<String, String> item = new HashMap<String, String>();
				  item.put("realname", tempFile.getName());
				  String tempFileName=tempFile.getName().substring(0, tempFile.getName().indexOf("."));
				  item.put("text", tempFileName);
				  item.put("value", tempFileName);
				  result.add(item);
			  }
		  }
		}
		return result;
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/read-contract", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> readFile(@RequestParam("filename") String fileName, HttpServletRequest request) throws Exception {
		this.fileName = fileName;
		return contractService.readDocx(fileName);
	}
	
	
	
	@CrossOrigin("*")
	@RequestMapping(value="/change-contract", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> changeContract(@RequestBody Condition[] conditions, HttpServletRequest request) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("filename", contractService.completeDocx(this.fileName, conditions));
		
		return map;
	}
	
	
	@CrossOrigin("*")
	@RequestMapping(value="/contract-part", method = RequestMethod.GET)
	@ResponseBody
	public Article getContractPart(@RequestParam("filename") String fileName, @RequestParam("paramindex") int paramIndex, HttpServletRequest request) throws Exception {
		this.fileName = fileName;
		Article result = contractService.getContractPartData(fileName, paramIndex);
		
		return result;
	}
	
	
	@CrossOrigin("*")
	@RequestMapping(value="/test11", method = RequestMethod.GET)
	@ResponseBody
	public void test(HttpServletRequest request) throws Exception {
		
//		String text = "# 제 112 조 그러하다.";
//		Pattern p = Pattern.compile("(제)\\s*[0-9]+\\s*(조)");
//		Matcher m = p.matcher(text);
		
		String text = "ssdsdsd{ON_OFF} fkfkfkfk";
		Pattern p = Pattern.compile("(\\{ON_OFF})");
		Matcher m = p.matcher(text);
		while (m.find()) {
			System.out.println(m.group());
			
		}
		
		
		String result = text.replaceAll("(\\{ON_OFF})", "");
		System.out.println(result);
	}
}
