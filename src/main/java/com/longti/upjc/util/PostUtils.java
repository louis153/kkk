package com.longti.upjc.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.longti.upjc.formdata.IHead;
import com.longti.upjc.formdata.RequestProperty;





public class PostUtils {
	protected final transient static Logger logger = LoggerFactory.getLogger(PostUtils.class);
	public static String doPost(String urlStr, String param) throws Exception {
		// boolean state = false;
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置发送请求的方式
		conn.setRequestMethod("POST");
		// 设置参数的格式类型
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept-Charset", "utf-8");

		// 打开输入输出，在output中传输参数
		conn.setDoInput(true);
		conn.setDoOutput(true);

		OutputStream os = conn.getOutputStream();
		// 把参数写入output流，使用UTF-8编码
		os.write(param.getBytes("utf-8"));
		os.close();
		// 正常时返回的状态码为200
		if (conn.getResponseCode() == 200) {
			// state = true;
			// 获取返回的内容
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			// 输出返回的信息
			String line;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				return line;
			}
			br.close();
		} else {
			System.out.println("post" + "[ERROR] CODE:" + conn.getResponseCode());
			
		}
		return "get error:no value";
	}
	
	/**
     * Get请求，获得返回数据
     *
     * @param urlStr
     * @return
     * @throws Exception
     */
    public static String doGet(String urlStr,IHead head,String para) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;

        ByteArrayOutputStream baos = null;
        try {
        	if(para.isEmpty()){
        		url = new URL(urlStr);
        	}else{
        		url = new URL(urlStr+"?"+para);
        	}
            
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(200000);
            conn.setConnectTimeout(200000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            List<RequestProperty> lstProperty=head.getProperty();
            for(RequestProperty rp:lstProperty){
            	conn.setRequestProperty(rp.key,rp.value);
            }
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                return baos.toString();
            } else {
                throw new RuntimeException(" responseCode is not 200 ... ");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
            }
            conn.disconnect();
        }

        return null;

    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @return 所代表远程资源的响应结果
     * @throws Exception
     */
    public static String doPostGZip(String url,IHead header, String param) throws ServerException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        InputStream ism = null;
        HttpURLConnection conn = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            
            conn.setRequestProperty("Content-Type", "application/json");
            System.out.println(conn.getRequestProperty("contentType"));
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Accept-Encoding", "gzip");//gzip
            List<RequestProperty> lstProperty=header.getProperty();
            for(RequestProperty rp:lstProperty){
            	conn.setRequestProperty(rp.key,rp.value);
            }
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            
            
            if (param != null && !param.trim().equals("")) {
                // 获取URLConnection对象对应的输出流
            	//GZIPOutputStream gzipOutputStream=new GZIPOutputStream(conn.getOutputStream());
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数

                out.print(param);//使用url编码
                // flush输出流的缓冲
                
                out.flush();
                //gzipOutputStream.close();
                //5.得到服务器相应
                if (conn.getResponseCode() == 200) {
                    System.out.println("服务器已经收到表单数据！");
                } else {
                    System.out.println("请求失败！");
                    return "";
                }
            }
           
            ism = conn.getInputStream();            
            //如果支持则应该使用GZIPInputStream解压，否则会出现乱码无效数据
            //ism = new GZIPInputStream(conn.getInputStream());
            
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(ism,"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ServerException("服务器错误");
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }

            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
        }
        return result;
    }


	public static HashMap<String,String> GetReqParams(HttpServletRequest request){
		BufferedReader reader = null;
		HashMap<String,String> hmParams=new HashMap<String,String>();
		String line;
		try {
			reader = request.getReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		
		try {
			while((line=reader.readLine())!=null)
			{	   
	 			String[] lines=java.net.URLDecoder.decode(line,   "utf-8").split("&");
				for(int i=0;i<lines.length;i++){
					String str=lines[i];
				    hmParams.put(str.substring(0, str.indexOf("=")), str.substring(str.indexOf("=")+1,str.length()));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return hmParams;
			
	}
	
	
			
			// 测试service方法2
			public static String testService2() {
						
				String return_msg = "";
						try {
							
							String sms_url = "http://localhost:8080/sportsmapi/matchTypeService"; // 短信接口URL
							
							StringBuffer sb = new StringBuffer("");
							sb.append("{");
							sb.append("\"item_name\":\"").append("47ad62b72fa847a4a52e9fb519767394").append("\"");
							sb.append(",");
							sb.append("\"fb_status\":\"").append("0").append("\"");
							sb.append(",");
							sb.append("\"page_no\":\"").append("0").append("\"");
							sb.append("}");
							
							try {
								return_msg = doPost(sms_url, sb.toString());
							} catch (Exception e) {
								logger.error(e.getMessage());
							}
							System.out.println(return_msg);
						} catch (Exception e) {
							logger.error(e.getMessage());
						}
						return return_msg;
					}
			
}
