package com.perfect.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baizz on 2014-10-10.
 */
public abstract class RowMapper extends DefaultHandler {

    private SharedStringsTable sst;
    private Map<Integer, String> strMap;
    private int sheetIndex = -1, rowIndex = -1;
    private List<Object> row;
    private String cells;
    private String cellType;
    private boolean valueFlag;
    private StringBuilder value;

    protected abstract void mapRow(int sheetIndex, int rowIndex, List<Object> row);

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "sheetData":
                sheetIndex++;
                break;
            case "row":
                rowIndex++;
                row = new ArrayList<>();
                break;
            case "c":
                cells = attributes.getValue("s");
                cellType = attributes.getValue("t");
                break;
            case "v":
                valueFlag = true;
                value = new StringBuilder();
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "sheetData":
                clearSheet();
                break;
            case "row":
                mapRow(sheetIndex, rowIndex, row);
                break;
            case "v":
                row.add(convertCellValue());
                valueFlag = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (valueFlag)
            value.append(ch, start, length);
    }

    public void setSharedStringsTable(SharedStringsTable sst) {
        this.sst = sst;
        strMap = new HashMap<>(sst.getCount());
    }

    private void clearSheet() {
        sst = null;
        strMap = null;
        row = null;
        cells = null;
        cellType = null;
        value = null;
        rowIndex = 0;
    }

    private Object convertCellValue() {
        String tmp = value.toString();
        Object result = tmp;

        if ("s".equals(cellType)) {     //string
            Integer key = Integer.parseInt(tmp);
            result = strMap.get(key);
            if (result == null)
                strMap.put(key, (String) (result = new XSSFRichTextString(sst.getEntryAt(key)).toString()));
        } else if ("n".equals(cellType)) {
            if ("2".equals(cells)) {    //date
                result = HSSFDateUtil.getJavaDate(Double.valueOf(tmp));
            }
        }
        return result;
    }
}
