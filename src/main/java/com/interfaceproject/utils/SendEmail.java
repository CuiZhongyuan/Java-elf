package com.interfaceproject.utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 邮件发送测试报告.
 * 
 * @author jw
 * 
 */
public class SendEmail {  

    private String smtpHost; // 邮件服务器地址  
    private String sendUserName; // 发件人的用户名  
    private String sendUserPass; // 发件人密码  
    private MimeMessage message; // 邮件对象  
    private Properties properties = new Properties();  
    private Session session;  
    private Multipart mp ;// 附件添加的组件  
    private List<FileDataSource> files = new LinkedList<FileDataSource>();// 存放附件文件  
    private SendEmail(String smtpHost, String sendUserName, String sendUserPass, String to, String cc, String mailSubject, String mailBody,
                      List<String> attachments) {
        this.smtpHost = smtpHost;  
        this.sendUserName = sendUserName;  
        this.sendUserPass = sendUserPass;  

        init();  
        setFrom(sendUserName);
        setTo(to);  
        setCC(cc);  
        setSubject(mailSubject);  
        setBody(mailBody);  
        if (attachments != null) {  
            for (String attachment : attachments) {  
                addFileAffix(attachment);  
            }  
        }
        send();
    }  
    

  
    /** 
     * 邮件实体 
     *  
     * @param smtpHost 
     *            邮件服务器地址 
     * @param sendUserName 
     *            发件邮件地址 
     * @param sendUserPass 
     *            发件邮箱密码 
     * @param to 
     *            收件人，多个邮箱地址以半角逗号分隔 
     * @param cc 
     *            抄送，多个邮箱地址以半角逗号分隔 
     * @param mailSubject 
     *            邮件主题 
     * @param mailBody 
     *            邮件正文 
     * @param  attachments
     *            附件路径 
     * @return 
     */  
    public static SendEmail entity(String smtpHost, String sendUserName, String sendUserPass, String to, String cc, String mailSubject, String mailBody,
                                                     List<String> attachments) {
        return new SendEmail(smtpHost, sendUserName, sendUserPass, to, cc, mailSubject, mailBody, attachments);
    }  
  
    
    private void init() {     	

	  	properties.put("mail.transport.protocol", "smtp"); // 连接协议
	  	properties.put("mail.smtp.host", smtpHost); // 主机名
	  	properties.put("mail.smtp.port", 465);  // 端口号
	  	properties.put("mail.smtp.auth", "true");
	  	properties.put("mail.smtp.ssl.enable", "true");  // 设置是否使用ssl安全连接 ---一般都使用
	  	properties.put("mail.debug", "true"); // 设置是否显示debug信息 true 会在控制台显示相关信息

        session = Session.getInstance(properties);  
        // 置true可以在控制台（console)上看到发送邮件的过程  
        session.setDebug(true);  
        // 用session对象来创建并初始化邮件对象  
        message = new MimeMessage(session);  
        // 生成附件组件的实例  
        mp = new MimeMultipart();  
    } 

    
    
    /** 
     * 设置发件人地址 
     *  
     * @param from 
     *            发件人地址 
     * @return 
     */  
    private boolean setFrom(String from) {  
    	System.out.println(from);
        try {  
        	message.setFrom(new InternetAddress(from));  
        } catch (Exception e) {  
            return false;  
        }  
        return true;  
    }  
    
    /** 
     * 设置收件人地址 
     *  
     * @param to 收件人的地址
     * @return 
     */  
    private boolean setTo(String to) {  
    	System.out.println(to);
        if (to == null)  
            return false;  
        try {  
        	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));  
        } catch (Exception e) {  
            return false;  
        }  
        return true;  
    }  
    
    /** 
     * 设置抄送 
     *  
     * @param cc 
     * @return 
     */  
    private boolean setCC(String cc) {  
    	System.out.println(cc);
        if (cc == null) {  
            return false;  
        }  
        try {  
        	message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));  
        } catch (Exception e) {  
            return false;  
        }  
        return true;  
    }  
    
    /** 
     * 设置邮件主题 
     *  
     * @param mailSubject 
     * @return 
     */  
    private boolean setSubject(String mailSubject) {  
    	System.out.println(mailSubject);
        try {  
        	message.setSubject(mailSubject);  
        } catch (Exception e) {  
            return false;  
        }  
        return true;  
    }  
  
    /** 
     * 设置邮件内容,并设置其为文本格式或HTML文件格式，编码方式为UTF-8 
     *  
     * @param mailBody 
     * @return 
     */  
    private boolean setBody(String mailBody) {  
    	System.out.println(mailBody);
        try {  
            BodyPart messageBodyPart = new MimeBodyPart();  
            messageBodyPart.setContent(mailBody,"text/html;charset=UTF-8");
            // 在组件上添加邮件文本  
            mp.addBodyPart(messageBodyPart);  
            message.setContent(mp);
        } catch (Exception e) {  
            System.err.println("设置邮件正文时发生错误！" + e);  
            return false;  
        }  
        return true;  
    }  
  
    /** 
     * 添加一个附件 
     *  
     * @param filename 
     *            邮件附件的地址，只能是本机地址而不能是网络地址，否则抛出异常 
     * @return 
     */  
    public boolean addFileAffix(String filename) {  
        try {  
            if (filename != null && filename.length() > 0) {  
                BodyPart messageBodyPart = new MimeBodyPart();  
                DataSource source = new FileDataSource(filename);  
                messageBodyPart.setDataHandler(new DataHandler(source));  
                messageBodyPart.setFileName(MimeUtility.encodeText(source.getName(), "utf-8", null)); // 解决附件名称乱码  
                mp.addBodyPart(messageBodyPart);// 添加附件  
                message.setContent(mp);
            }  
        } catch (Exception e) {  
            System.err.println("增加邮件附件：" + filename + "发生错误！" + e);  
            return false;  
        }  
        return true;  
    }  
  
    /** 
     * 删除所有附件 
     *  
     * @return 
     */  
    public boolean delFileAffix() {  
        try {  
            FileDataSource fileds = null;  
            for (Iterator<FileDataSource> it = files.iterator(); it.hasNext();) {  
                fileds = it.next();  
                if (fileds != null && fileds.getFile() != null) {  
                    fileds.getFile().delete();  
                }  
            }  
        } catch (Exception e) {  
            return false;  
        }  
        return true;  
    }  
  
    /**
     * 
     * @return
     */
    private boolean send() {
		try {
			Log4jUtil.info("开始尝试连接"+sendUserName+"......");
			Transport transport = session.getTransport();
	        // 连接自己的邮箱账户
	        transport.connect(sendUserName, sendUserPass);// 密码为刚才得到的授权码
	        Log4jUtil.info("登录邮箱用户成功！");		
	        try {
	            transport.sendMessage(message, message.getAllRecipients());
		        Log4jUtil.info("发送邮件成功！");
			} catch (Exception e) {
				 Log4jUtil.error("发送邮件失败！");
				 return false;
			}
	    
		} catch (Exception e) {
			Log4jUtil.error("登录"+sendUserName+"失败！");
			return false;
		}
		return true;
    }
    /** 
     * 发送邮件 
     *  
     * @return 
     */  
	public static <E> void sendEmail(HashMap<String, Object> hasMap,String mailConfigPath) {
		
		HashMap<String, String> configInfo = new HashMap<String, String>();
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(new FileInputStream(mailConfigPath), "UTF-8"));
		} catch (IOException e) {
			Log4jUtil.error(e.getMessage());
		}

		Set<String> keys = properties.stringPropertyNames();
		for (String key : keys) {
			configInfo.put(key, properties.getProperty(key));
			System.out.println(key+":"+properties.getProperty(key));
		}
		
		try {
			String userName = configInfo.get("userName"); // 发件人邮箱  
	        String password = configInfo.get("password"); // 发件人密码  
	        String smtpHost = configInfo.get("smtpHost"); // 邮件服务器    
	        String to = configInfo.get("to"); // 收件人，多个收件人以半角逗号分隔  
	        String cc = configInfo.get("cc"); // 抄送，; 
	        String subject =configInfo.get("subject");// 主题
	        String body = (String) hasMap.get("reportSource");// 正文，可以用html格式
 
	        List<String> reportPath = Arrays.asList((String) hasMap.get("reportPath").toString());
	        List<String> screenshotPaths = (List<String>) hasMap.get("screenshotPaths");  
	        List<String> attachments = new ArrayList<String>();
	        attachments.addAll(reportPath);
	        attachments.addAll(screenshotPaths);
	        for (String string : attachments) {
				System.out.println(string);
			}  
	        SendEmail email = SendEmail.entity(smtpHost, userName, password, to, cc, subject, body, attachments);
		}
		catch(Exception e) {
			Log4jUtil.error(e);
		}
	}


    /**
     * @desc 将源文件/文件夹生成指定格式的压缩文件,格式zip
     * @param resourcesPath 源文件/文件夹
     * @param targetPath  目的压缩文件保存路径
     * @return void
     * @throws Exception
     */
    public static void compressedFile(String resourcesPath,String targetPath) throws Exception{
        final File resourcesFile = new File(resourcesPath);     //源文件
        final File targetFile = new File(targetPath);           //目的
        //如果目的路径不存在，则新建
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        final String targetName = resourcesFile.getName()+".zip";   //目的压缩文件名
        final FileOutputStream outputStream = new FileOutputStream(targetPath+"\\"+targetName);
        final ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));

        createCompressedFile(out, resourcesFile, "");

        out.close();
    }

    /**
     * @desc 生成压缩文件。
     *                  如果是文件夹，则使用递归，进行文件遍历、压缩
     *       如果是文件，直接压缩
     * @param out  输出流
     * @param file  目标文件
     * @return void
     * @throws Exception
     */
    public static void createCompressedFile(ZipOutputStream out,File file,String dir) throws Exception{
        //如果当前的是文件夹，则进行进一步处理
        if(file.isDirectory()){
            //得到文件列表信息
            final File[] files = file.listFiles();
            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir+"/"));

            dir = dir.length() == 0 ? "" : dir +"/";

            //循环将文件夹中的文件打包
            for(int i = 0 ; i < files.length ; i++){
                createCompressedFile(out, files[i], dir + files[i].getName());         //递归处理
            }
        }
        else{   //当前的是文件，打包处理
            //文件输入流
            final FileInputStream fis = new FileInputStream(file);

            out.putNextEntry(new ZipEntry(dir));
            //进行写操作
            int j =  0;
            final byte[] buffer = new byte[1024];
            while((j = fis.read(buffer)) > 0){
                out.write(buffer,0,j);
            }
            //关闭输入流
            fis.close();
            System.out.println("压缩文件已经生成...");
        }
    }
	
}  
