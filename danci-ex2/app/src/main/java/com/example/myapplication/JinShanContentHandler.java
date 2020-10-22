package com.example.myapplication;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class JinShanContentHandler extends DefaultHandler {

    public WordValue wordValue=null;
    private String tagName=null;
    private String interpret="";       //防止空指针异常
    private String orig="";
    private String trans="";
    private boolean isChinese=false;
    public JinShanContentHandler(){
        wordValue=new WordValue();
        isChinese=false;
    }

    public WordValue getWordValue(){

        if (wordValue!=null){
            return wordValue;}
        return null;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        if(length<=0)
            return;
        for(int i=start; i<start+length; i++){
            if(ch[i]=='\n')
                return;
        }

        //去除莫名其妙的换行！

        String str=new String(ch,start,length);
        if(tagName=="key"){
            wordValue.setWord(str);
        }else if(tagName=="ps"){
            if(wordValue.getPsE().length()<=0){
                wordValue.setPsE(str);
            }else{
                wordValue.setPsA(str);
            }
        }else if(tagName=="pron"){
            if(wordValue.getPronE().length()<=0){
                wordValue.setPronE(str);
            }else{
                wordValue.setPronA(str);
            }
        }else if(tagName=="pos"){
            isChinese=false;
            interpret=interpret+str+" ";
        }else if(tagName=="acceptation"){
            interpret=interpret+str+"\n";
            interpret=wordValue.getInterpret()+interpret;
            wordValue.setInterpret(interpret);
            interpret=""; //初始化操作，预防有多个释义
        }else if(tagName=="orig"){


            orig=wordValue.getSentOrig();
            wordValue.setSentOrig(orig+str+"\n");


        }else if(tagName=="trans"){
            String temp=wordValue.getSentTrans()+str+"\n";
            wordValue.setSentTrans(temp);

        }else if(tagName=="fy"){
            isChinese=true;
            wordValue.setInterpret(str);
        }


    }



    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
        tagName=null;


    }


    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        super.startElement(uri, localName, qName, attributes);
        tagName=localName;
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.endDocument();
        if(isChinese)
            return;
        String interpret=wordValue.getInterpret();
        if(interpret!=null && interpret.length()>0){
            char[] strArray=interpret.toCharArray();
            wordValue.setInterpret(new String(strArray,0,interpret.length()-1));
            //去掉解释的最后一个换行符
        }

    }




}