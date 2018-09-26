package com.hx.study.reactor.ip;

import org.springframework.util.StringUtils;
import qiniu.ip17mon.Locator;

import java.io.*;
import java.util.HashMap;

public class IpService {
    public void test() throws IOException {

    }

    public static void main(String[] args) throws IOException{
        readTxtFile("D://test//orderlist.txt");
    }

    public static HashMap<String,String> getIpInfo(String ip) throws IOException{
        //从本地加载IP库
        String ipBasePath = "D:\\17monipdb.datx";
        Locator locator = Locator.loadFromLocal(ipBasePath);
        HashMap<String,String> ipInfo = new HashMap<>();
        ipInfo.put("country",locator.find(ip).country);
        ipInfo.put("state",locator.find(ip).state);
        ipInfo.put("city",locator.find(ip).city);
        return ipInfo;
    }

    public static void readTxtFile(String filePath){
        PrintWriter pw = null;
        try {
            pw=new PrintWriter("D://1.txt");
            String encoding="utf-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while((lineTxt = bufferedReader.readLine()) != null)
                {
                    String[] txts = lineTxt.split("\\|");
                    String orderId = txts[1];
                    String ip = txts[2];
                    String status = txts[3];
                    HashMap<String,String> ipInfo;
                    if(StringUtils.isEmpty(ip)){
                        ipInfo = new HashMap();
                    }
                    else{
                        ipInfo= getIpInfo(ip.trim());
                    }
                    String sql = "('%s', '%s', '%s', '%s', '%s', '%s'),";
                    String result = String.format(sql, orderId.trim(),status.trim(),ip.trim(),ipInfo.get("country").trim(),ipInfo.get("state").trim(),ipInfo.get("city").trim());
                    pw.write(result);
                    pw.println();
                }
                read.close();

            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }finally {
            pw.flush();
            pw.close();

        }

    }

}
