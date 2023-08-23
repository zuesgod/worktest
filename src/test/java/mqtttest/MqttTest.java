package mqtttest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author zeus
 * @date 2023-06-02 13:07
 **/
public class MqttTest {


    private String jsonStr = "{\n" +
            "    \"sn\": \"000099\",\n" +
            "    \"topic\": \"/pub/181f07d5a7cf4a39946d16e9be3e2100/oCRBkckCsgXaRNwDcewYw3/A2\",\n" +
            "    \"status\": 0,\n" +
            "    \"snType\": \"DTSD1352\",\n" +
            "    \"type\": 1,\n" +
            "    \"add\": 2,\n" +
            "    \"rate\": 900,\n" +
            "    \"time\": \"2023-06-02 10:38:32\",\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"code\": \"A21\",\n" +
            "            \"value\": \"1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A22\",\n" +
            "            \"value\": \"400\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A23\",\n" +
            "            \"value\": \"2791857.3125\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A24\",\n" +
            "            \"value\": \"0\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A25\",\n" +
            "            \"value\": \"1307709.5\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A26\",\n" +
            "            \"value\": \"0\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A27\",\n" +
            "            \"value\": \"229.3\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A28\",\n" +
            "            \"value\": \"229.5\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A29\",\n" +
            "            \"value\": \"228.8\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A30\",\n" +
            "            \"value\": \"16\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A31\",\n" +
            "            \"value\": \"12\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A32\",\n" +
            "            \"value\": \"24\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A33\",\n" +
            "            \"value\": \"49.98\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A34\",\n" +
            "            \"value\": \"397.3\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A35\",\n" +
            "            \"value\": \"396.8\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A36\",\n" +
            "            \"value\": \"396.7\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A37\",\n" +
            "            \"value\": \"2.8\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A38\",\n" +
            "            \"value\": \"1.6\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A39\",\n" +
            "            \"value\": \"4.4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A40\",\n" +
            "            \"value\": \"9.2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A41\",\n" +
            "            \"value\": \"2.8\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A42\",\n" +
            "            \"value\": \"2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A43\",\n" +
            "            \"value\": \"3.2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A44\",\n" +
            "            \"value\": \"8.4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A45\",\n" +
            "            \"value\": \"4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A46\",\n" +
            "            \"value\": \"2.8\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A47\",\n" +
            "            \"value\": \"5.6\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A48\",\n" +
            "            \"value\": \"12.4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A49\",\n" +
            "            \"value\": \"0.73\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A50\",\n" +
            "            \"value\": \"0.643\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"code\": \"A51\",\n" +
            "            \"value\": \"0.805\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"delay\": 0\n" +
            "}";



    @Test
    void test1(){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = objectMapper.readValue(jsonStr, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> dataMap = new HashMap<>();
        for (Map<String, String> data : (Iterable<Map<String, String>>) jsonMap.get("data")) {
            dataMap.put(data.get("code"), data.get("value"));
        }
        System.out.println(dataMap);
        System.out.println("========================================");
        System.out.println("dataMap.get(\"A24\") = " + dataMap.get("A24"));
    }


    @Test
    public void test2() throws ParserConfigurationException, ParseException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = objectMapper.readValue(jsonStr, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> dataMap = new HashMap<>();
        for (Map<String, String> data : (Iterable<Map<String, String>>) jsonMap.get("data")) {
            dataMap.put(data.get("code"), data.get("value"));
        }
        // 创建一个XML文档对象
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        Element root = document.createElement("root");

        // 创建根元素state
        Element stateElement = document.createElement("state");
        stateElement.setTextContent("resent");

        // 创建根元素uploadtime
        Element uploadTimeElement = document.createElement("uploadTime");
        uploadTimeElement.setTextContent(new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));

        // 创建根元素data
        Element dataElement = document.createElement("data");

        // 创建子元素tp
        Element tpElement = document.createElement("tp");

        // 创建子元素columns
        Element columnsElement = document.createElement("columns");

        // 遍历数据Map中的条目
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            // 如果词典Map中存在该键
            if (AttrMap.containsKey(key)) {
                // 创建column元素
                Element columnElement = document.createElement("column");
                // 创建key元素
                Element keyElement = document.createElement("key");
                keyElement.setTextContent(AttrMap.getValue(key).getCode());
                // 创建value元素
                Element valueElement = document.createElement("value");
                valueElement.setTextContent(value);
                // 创建unit元素
                Element unitElement = document.createElement("unit");
                unitElement.setTextContent(AttrMap.getValue(key).getUnit());
                // 将key、value和unit元素添加到column元素中
                columnElement.appendChild(keyElement);
                columnElement.appendChild(valueElement);
                columnElement.appendChild(unitElement);
                // 将column元素添加到columns元素中
                columnsElement.appendChild(columnElement);
            }
        }

        // 创建根元素tpid
        Element tpidElement = document.createElement("tpid");
        tpidElement.setTextContent(jsonMap.get("sn").toString());

        // 创建根元素datatime
        Element datatimeElement = document.createElement("datatime");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = inputFormat.parse(jsonMap.get("time").toString());
        datatimeElement.setTextContent(new SimpleDateFormat("yyyyMMddHHmm").format(time));

        // 创建根元素quality
        Element qualityElement = document.createElement("quality");
        qualityElement.setTextContent("value");

        // 将columns元素添加到tp元素中
        tpElement.appendChild(tpidElement);
        // 将columns元素添加到tp元素中
        tpElement.appendChild(datatimeElement);
        // 将columns元素添加到tp元素中
        tpElement.appendChild(qualityElement);
        // 将columns元素添加到tp元素中
        tpElement.appendChild(columnsElement);
        // 将tp元素添加到data元素中
        dataElement.appendChild(tpElement);


        root.appendChild(dataElement);
        root.appendChild(stateElement);
        root.appendChild(uploadTimeElement);
        // 将data元素添加到XML文档对象中
        document.appendChild(root);
        // 将XML文档对象转换为XML字符串并打印到控制台
        Node rootNode = document.getDocumentElement();
        String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + nodeToString(rootNode);
        System.out.println(xmlString);
    }

    // 将节点转换为字符串的方法
    private static String nodeToString(Node node) {
        try {
            javax.xml.transform.Transformer transformer = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
            java.io.StringWriter sw = new java.io.StringWriter();
            javax.xml.transform.stream.StreamResult sr = new javax.xml.transform.stream.StreamResult(sw);
            transformer.transform(new javax.xml.transform.dom.DOMSource(node), sr);
            String str = sw.toString().toString();
            //截取掉虚拟根元素
            String prefix = "<root>";
            String suffix = "</root>";
            str = str.substring(prefix.length());
            str = str.substring(0, str.length() - suffix.length());
            return str;
        } catch (javax.xml.transform.TransformerException ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

    private Map<String,String> HandleJsonStr(){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = objectMapper.readValue(jsonStr, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> dataMap = new HashMap<>();
        for (Map<String, String> data : (Iterable<Map<String, String>>) jsonMap.get("data")) {
            dataMap.put(data.get("code"), data.get("value"));
        }
        return dataMap;
    }
}
