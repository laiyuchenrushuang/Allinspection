package com.seatrend.vendor.allinspection;


import net.sf.json.xml.XMLSerializer;

/**
 * Created by ly on 2020/4/8 14:47
 */
public class JavaTest {
    public static void main(String[] args) {


//        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?><mobilegate><timestamp>232423423423</timestamp><txn>Transaction</txn><amt>0</amt></mobilegate>\n";
        String xmlStr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:end=\"http://endpoint.webservice.pda.seatrend.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <end:queryObjectOut>\n" +
                "         <!--Optional:-->\n" +
                "         <xtlb>18</xtlb>\n" +
                "         <!--Optional:-->\n" +
                "         <jkxlh>123</jkxlh>\n" +
                "         <!--Optional:-->\n" +
                "         <jkid>18W07</jkid>\n" +
                "         <!--Optional:-->\n" +
                "         <cjsqbh></cjsqbh>\n" +
                "         <!--Optional:-->\n" +
                "         <dwjgdm></dwjgdm>\n" +
                "         <!--Optional:-->\n" +
                "         <dwmc></dwmc>\n" +
                "         <!--Optional:-->\n" +
                "         <yhbz></yhbz>\n" +
                "         <!--Optional:-->\n" +
                "         <yhxm></yhxm>\n" +
                "         <!--Optional:-->\n" +
                "         <zdbs>127.0.0.1</zdbs>\n" +
                "         <!--Optional:-->\n" +
                "         <QueryXmlDoc>\n" +
                "         <![CDATA[<root><QueryCondition><jyjgbh>5100000114</jyjgbh></QueryCondition></root>\n" +
                "]]>\n" +
                "         </QueryXmlDoc>\n" +
                "      </end:queryObjectOut>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        XMLSerializer xmlSerializer = new XMLSerializer();
        String result = xmlSerializer.read(xmlStr).toString();


        System.out.println(result);



    }

//    public static String xml2json(String response) {
//        JSONObject jsonObj = null;
//        try {
//            jsonObj = XML.toJSONObject(response);
//        } catch (JSONException e) {
//            Log.e("JSON exception", e.getMessage());
//            e.printStackTrace();
//        }
//        return jsonObj.toString();
//    }

}
