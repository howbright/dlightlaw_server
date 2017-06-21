package com.dlight.contract;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.springframework.stereotype.Service;

import com.dlight.util.KoreanUtil;

@Service
public class ContractService {

	public List<Map<String, String>> readDocx(String fileName) {

		try {
			FileInputStream fis = new FileInputStream("/Users/hyun/Dlight/contracts/" + fileName + ".docx");
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			List<XWPFParagraph> paragraphList = xdoc.getParagraphs();

			int index = 0;
			Set<String> tokenSet = new HashSet<String>();
			List<Map<String, String>> result = new ArrayList<Map<String, String>>();

			for (XWPFParagraph paragraph : paragraphList) {
				System.out.println(paragraph.getText());

				List<XWPFRun> runs = paragraph.getRuns();
				if (runs != null) {
					String temp = "";
					boolean insertMode = false;
					for (XWPFRun run : runs) {
						String text = run.getText(0);
						if (text != null) {
							if (text.contains("{")) {
								insertMode = true;
							} else if (text.contains("}")) {
								insertMode = false;
								if (!tokenSet.contains(temp)) {
									tokenSet.add(temp);
									Map<String, String> tokenMap = new HashMap<String, String>();
									tokenMap.put("title", temp);
									tokenMap.put("index", String.valueOf(index++));
									result.add(tokenMap);
								}
								temp = "";
							} else if (insertMode) {
								temp = temp + text;
							}
						}
					}
				}
			}

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Article getContractPartData(String fileName, int paramIndex) {
		List<XWPFParagraph> pList = getContractPart(fileName, paramIndex);
		Article article = getArticle(pList, paramIndex);
		return article;
	}

	@SuppressWarnings("resource")
	public List<XWPFParagraph> getContractPart(String fileName, int paramIndex) {

		try {
			FileInputStream fis = new FileInputStream("/Users/hyun/Dlight/contracts/" + fileName);
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			List<XWPFParagraph> paragraphList = xdoc.getParagraphs();

			int index = -1;
			List<XWPFParagraph> originData = new ArrayList<XWPFParagraph>();
			for (XWPFParagraph paragraph : paragraphList) {
				if (paragraph.getText().indexOf("#") >= 0) {
					index++;
					if (index == paramIndex) {
						originData.add(paragraph);
					}
					if (paramIndex == index - 1) {
						break;
					}
				} else if (paramIndex == index) {
					originData.add(paragraph);
				}
			}
			return originData;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private Article getArticle(List<XWPFParagraph> paragraphList, int paramIndex) {
		if(paragraphList == null || paragraphList.isEmpty()){
			return null;
		}
		Article.Builder articleBuilder = new Article.Builder();
		StringBuilder originData = new StringBuilder();
		StringBuilder userData = new StringBuilder();
		Set<String> tokenSet = new HashSet<String>();
		boolean isOptional = false;
		String title = "";
		int articleIndex = 0;
		Pattern conditionPttn = Pattern.compile("(\\{)[^\\{]*(})");
		Pattern articlePttn = Pattern.compile("(제)\\s*[0-9]+\\s*(조)");
		Pattern numberPttn = Pattern.compile("[0-9]+");
		List<Condition> conditions = new ArrayList<Condition>();
		for (XWPFParagraph p : paragraphList) {
			
			originData.append(p.getText() + "<br>");
			String replaceText = p.getText();
			
			if (p.getText().indexOf("#") >= 0) {
				replaceText = replaceText.replaceAll("(#)", "");
				Matcher articleMa = articlePttn.matcher(p.getText());
				if (articleMa.find()){
					Matcher indexMa = numberPttn.matcher(articleMa.group());
					if (indexMa.find()) {
						articleIndex = Integer.parseInt(indexMa.group());
					}
				}
				title = replaceText.replaceAll("(제)\\s*[0-9]+\\s*(조)", "");
				replaceText = "<span style='font-weight:bold'>" + replaceText + "</span>";
			}
			
			if (p.getText().indexOf("{ON_OFF}") >= 0) {
				isOptional = true;
				replaceText = replaceText.replaceAll("(\\{ON_OFF})", "");
			}
			
			Matcher m = conditionPttn.matcher(p.getText());
			int conditionIdx =0;
			while(m.find()) {
				String matchStr = m.group().substring(1, m.group().length()-1);
				String[] splitedStrs = matchStr.split("@",3);
				if(splitedStrs.length >=3){
					String token = splitedStrs[2].trim();
					if(!tokenSet.contains(token)){
						tokenSet.add(token);
						Condition condition = new Condition();
						condition.setType(splitedStrs[0].trim());
						String replaceWord = m.group();
						if(replaceWord.indexOf("{")>=0){
							replaceWord = replaceWord.replaceAll("\\{", "\\\\{");
						}
						if(replaceWord.indexOf("[")>=0){
							replaceWord = replaceWord.replaceAll("\\[", "\\\\[");
						}
						if(replaceWord.indexOf("(")>=0){
							replaceWord = replaceWord.replaceAll("\\(", "\\\\(");
						}
						if(replaceWord.indexOf(")")>=0){
							replaceWord = replaceWord.replaceAll("\\)", "\\\\)");
						}
						if(splitedStrs[0].equals("SELECT") || splitedStrs[0].equals("MULTI_SELECT")){
							String[] optionsStrs = splitedStrs[2].split(";");
							replaceText = "<blockquote>";
							String[] kOrder = new String[]{"가. ","나. ","다. ","라. ", "마. ","바. ", "사. ", "아. ", "자. "};
							String order = "⦁";
							for(int i=0; i< optionsStrs.length ; i++){
								if(splitedStrs[1].equals("가")){
									order = kOrder[i];
								}
								replaceText = replaceText + "<span style='color:red'>" + order + optionsStrs[i] + "</span><br></br>";
							}
							replaceText = replaceText + "</blockquote>";
						}else{
							replaceText = replaceText.replaceAll(replaceWord, "<span style='color:red'>" +splitedStrs[1]+"</span>");
						}
						condition.setQuestionKey(splitedStrs[2]);
						condition.setConId(condition.getType()+"."+condition.getQuestionKey());
						conditions.add(condition);
					}
				}
			}
			userData.append(replaceText + "<br>");
		}
		Article article = articleBuilder.originData(originData.toString())
										.userData(userData.toString())
										.conditions(conditions)
										.orderIndex(paramIndex)
										.articleIndex(articleIndex)
										.title(title)
										.isOptional(isOptional)
										.build();

		return article;
	}



	
	public String completeDocx(String fileName, Condition[] conditions) {

		try {
			XWPFDocument doc = new XWPFDocument(OPCPackage.open("/Users/hyun/Dlight/contracts/" + fileName));
			
			List<XWPFParagraph> paragraphList = doc.getParagraphs();
			Pattern conditionPttn = Pattern.compile("(\\{)[^\\{]*(})");
			Pattern articlePttn = Pattern.compile("(제)\\s*[0-9]+\\s*(조)");
			Pattern numberPttn = Pattern.compile("[0-9]+");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat resultFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
			int articleIndex = 0; 
		    int decreaceNum = 0;
		    boolean deleteMode = false;
		    List<XWPFParagraph> deleteList = new ArrayList<XWPFParagraph>();
			for (XWPFParagraph p : paragraphList) {
				String replaceText = p.getText();
				if (p.getText().indexOf("#") >= 0) { // 각 조항들 
					deleteMode =false;
					replaceWord(p,"#", "");
					Matcher articleMa = articlePttn.matcher(p.getText());
					if (articleMa.find()){
						Matcher indexMa = numberPttn.matcher(articleMa.group());
						if (indexMa.find()) {
							articleIndex = Integer.parseInt(indexMa.group());
						}
					}
					if(p.getText().indexOf("{ON_OFF}") >= 0){
						if(isDeletedArticle(conditions, articleIndex)){
							deleteMode = true;
							decreaceNum--;
						}else{
							replaceText = replaceText.replace("#", "");
							replaceText = replaceText.replace("{ON_OFF}", "");
							replaceParagraph(p, replaceText);
						}
					}
					int newIdx = articleIndex + decreaceNum;
					replaceWord(p,String.valueOf(articleIndex), String.valueOf(newIdx));
//					replaceText = replaceText.replaceAll("[0-9]+", String.valueOf(articleIndex));
				}
				if(deleteMode){
					deleteList.add(p);
				}
				
				
				//TEXT
				Matcher m = conditionPttn.matcher(p.getText());
				int conditionIdx =0;
				while(m.find()) {
					String matchStr = m.group().substring(1, m.group().length()-1);
					String[] splitedStrs = matchStr.split("@",3);
					if(splitedStrs.length >=3){
						String type = splitedStrs[0].trim();
						String token = splitedStrs[2].trim();
						String value = "";
						if(type.equals("TEXT") || type.equals("SELECT") || type.equals("NUMBER")){
							value = getUserInsertValue(type, token, conditions);
							replaceText = replaceText.replace(m.group(), value);
							replaceParagraph(p, replaceText);
						}
						else if(type.equals("DEFINITION")){
							value = getUserInsertValue(type, token, conditions);
							value = value.replace("\n", " ");
							Pattern koreaPttn = Pattern.compile("[가-힣]{1}");
							String lastKo = "";
							String fix = "를";
							Matcher koMa = koreaPttn.matcher(value);
							while(koMa.find()){
								lastKo = koMa.group();
							}
							if(lastKo != ""){
								fix=KoreanUtil.getComleteWordByJongsung(lastKo,"을","를");
							}
							String suffix = fix + "  말한다.";
							value = "'" + value + " ' " + suffix;
							replaceText = replaceText.replace(m.group(), value);
							replaceParagraph(p, replaceText);
						}else if(type.equals("MULTI_SELECT")){
							value = getUserInsertValue(type, token, conditions);
							String[] values = value.split(";");
							String[] kOrder = new String[]{"가. ","나. ","다. ","라. ", "마. ","바. ", "사. ", "아. ", "자. "};
							String order = "";
							value = "";
							for(int i=0; i< values.length ; i++){
								if(splitedStrs[1].equals("가")){
									value = value + kOrder[i] +values[i];
									if(i < values.length-1){
										value = value + "\n";
									}
								}
							}
							replaceText = replaceText.replace(m.group(), value);
							replaceParagraph(p, replaceText);
							
						}
						else if(type.equals("DATE")){
							value = getUserInsertValue(type, token, conditions);
							Date x = new Date();
							if(!value.equals(""))
								x = format.parse(value);
							replaceText = replaceText.replace(m.group(), resultFormat.format(x));
							replaceParagraph(p, replaceText);
						}
					}
				}
				
//				String[] paragraphs = new String[1];
//				paragraphs[0] = replaceText;
//				createParagraphs(p, paragraphs);
			}
			int size = deleteList.size();
			for (int i=0; i<size; i++) {
				deleteParagraph(deleteList.get(i));
			}
			

			String contractName = "result"+ ".docx";
			String fileLocation = "/Users/hyun/Dlight/contracts/" + contractName;
			doc.write(new FileOutputStream(fileLocation));
			return contractName;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	private void replaceParagraph(XWPFParagraph p, String replaceText) {
		List<XWPFRun> runs = p.getRuns();
		int firstIdx = 0;
		boolean firstCheck = false;
		int i=0;
		if (runs != null) {
			for (XWPFRun r : runs) {
				if(r.text() != null && !r.equals("") && !firstCheck){
					firstIdx = i;
					firstCheck = true;
				}
				r.setText("", 0);
				i++;
			} 
		}
		
		XWPFRun r = runs.get(firstIdx);
		 if (replaceText.contains("\n")) {
             String[] lines = replaceText.split("\n");
             r.setText(lines[0], 0); // set first line into XWPFRun
             for(int j=1;j<lines.length;j++){
                 // add break and insert new text
                 r.addBreak();
                 r.setText(lines[j],j);
             }
         } else {
             r.setText(replaceText, 0);
         }
//		runs.get(firstIdx).setText(replaceText, 0);
	}

	private String getUserInsertValue(String type, String token, Condition[] conditions) {
		for(Condition con : conditions){
			if(con.getType().equals(type) && con.getQuestionKey().equals(token))
			{
				return con.getUserInsertValue();
			}
		}
		return "";
	}

	public static void deleteParagraph(XWPFParagraph p) {
		  XWPFDocument doc = p.getDocument();
		  int pPos = doc.getPosOfParagraph(p);
		  //doc.getDocument().getBody().removeP(pPos);
		  doc.removeBodyElement(pPos);
	 }
	
	private void replaceWord(XWPFParagraph p ,String oldWord, String newWord){
		List<XWPFRun> runs = p.getRuns();
		if (runs != null) {
			for (XWPFRun r : runs) {
				String text = r.getText(0);
				if (text.contains(oldWord)) {
					text = text.replace(oldWord, newWord);
					r.setText(text, 0);
				} 
			}
		}
	}
	
	private boolean isDeletedArticle(Condition[] conditions, int articleIndex) {
		for(Condition con : conditions) {
			if(con.getType().equals("OPTIONAL") && articleIndex == Integer.parseInt(con.getUserInsertValue())){
				return true;
			}
		}
		return false;
	}

	private void createParagraphs(XWPFParagraph p, String paragraphs) {
		String[] newP = new String[1];
		createParagraphs(p, newP);
	}
	private void createParagraphs(XWPFParagraph p, String[] paragraphs) {
	    if (p != null) {
	        XWPFDocument doc = p.getDocument();
	        XmlCursor cursor = p.getCTP().newCursor();
	        for (int i = 0; i < paragraphs.length; i++) {
	            XWPFParagraph newP = doc.createParagraph();
	            newP.getCTP().setPPr(p.getCTP().getPPr());
	            XWPFRun newR = newP.createRun();
	            newR.getCTR().setRPr(p.getRuns().get(0).getCTR().getRPr());
	            newR.setText(paragraphs[i]);
	            XmlCursor c2 = newP.getCTP().newCursor();
	            c2.moveXml(cursor);
	            c2.dispose();
	        }
	        cursor.removeXml(); // Removes replacement text paragraph
	        cursor.dispose();
	    }
	}

	private String getReplaceValue(String title, Condition[] conditions) {
		String replaceStr = "";
		for (Condition replaceValue : conditions) {
			if (replaceValue.getQuestionKey().equals(title)) {
				replaceStr = replaceValue.getUserInsertValue();
				return replaceStr;
			}
		}
		return replaceStr;
	}
	
	public void replaeBracesToUserInsertValue( XWPFParagraph p, Condition[] conditions ){
		List<XWPFRun> runs = p.getRuns();
		boolean keywordMode = false;
		String keyword = "";
		String replaceWord = "";
		if (runs != null) {
			for (XWPFRun r : runs) {
				String text = r.getText(0);
				if (!keywordMode && text != null && text.contains("{")) {
					keywordMode = true;
					text = text.replace("{", "");
					r.setText(text, 0);
				} else if (keywordMode && text != null && text.contains("}")) {
					replaceWord = getReplaceValue(keyword, conditions);
					text = text.replace("}", replaceWord);
					r.setText(text, 0);
					keyword = "";
					keywordMode = false;
				} else if (keywordMode) {
					keyword = keyword + text;
					text = text.replace(text, "");
					r.setText(text, 0);
				}
			}
		}
	}
	
	public String changeDocx(String fileName, Condition[] conditions) {

		try {
			XWPFDocument doc = new XWPFDocument(OPCPackage.open("/Users/hyun/Dlight/contracts/" + fileName + ".docx"));
			for (XWPFParagraph p : doc.getParagraphs()) {
				List<XWPFRun> runs = p.getRuns();
				boolean keywordMode = false;
				String keyword = "";
				String replaceWord = "";
				if (runs != null) {
					for (XWPFRun r : runs) {
						String text = r.getText(0);
						if (!keywordMode && text != null && text.contains("{")) {
							keywordMode = true;
							text = text.replace("{", "");
							r.setText(text, 0);
						} else if (keywordMode && text != null && text.contains("}")) {
							replaceWord = getReplaceValue(keyword, conditions);
							text = text.replace("}", replaceWord);
							r.setText(text, 0);
							keyword = "";
							keywordMode = false;
						} else if (keywordMode) {
							keyword = keyword + text;
							text = text.replace(text, "");
							r.setText(text, 0);
						}
					}
				}
			}

			String contractName = "result_" + Calendar.getInstance().getTimeInMillis() + ".docx";
			String fileLocation = "/Users/hyun/Dlight/contracts/" + contractName;
			doc.write(new FileOutputStream(fileLocation));
			return contractName;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

}
