/*
 * Copyright (c) 2019, JavaFamily Technology Corp, All Rights Reserved.
 *
 * The software and information contained herein are copyrighted and
 * proprietary to AngBoot Technology Corp. This software is furnished
 * pursuant to a written license agreement and may be used, copied,
 * transmitted, and stored only in accordance with the terms of such
 * license and with the inclusion of the above copyright notice. Please
 * refer to the file "COPYRIGHT" for further copyright and licensing
 * information. This software and information or any other copies
 * thereof may not be provided or otherwise made available to any other
 * person.
 */

package club.javafamily.runner.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Service
public class EmailService {

   private final JavaMailSender mailSender;

   @Value("${spring.mail.username}")
   private String SENDER;

   @Autowired
   public EmailService(JavaMailSender mailSender) {
      this.mailSender = mailSender;
   }

   /**
    * 发送普通邮件
    *
    * @param to      收件人
    * @param subject 主题
    * @param content 内容
    */
   public void sendSimpleMailMessage(String to, String subject, String content) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(SENDER);
      message.setTo(to);
      message.setSubject(subject);
      message.setText(content);

      mailSender.send(message);
   }

   /**
    * 发送 HTML 邮件
    *
    * @param to      收件人
    * @param subject 主题
    * @param content 内容
    */
   public void sendMimeMessage(String to, String subject, String content)
      throws MessagingException
   {
      MimeMessage message = mailSender.createMimeMessage();

      //true表示需要创建一个multipart message
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(SENDER);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content, true);
      mailSender.send(message);
   }

   /**
    * 发送带附件的邮件
    *
    * @param to       收件人
    * @param subject  主题
    * @param content  内容
    * @param filePath 附件路径
    */
   public void sendMimeMessage(String to, String subject, String content,
                               String filePath)
      throws MessagingException
   {
      MimeMessage message = mailSender.createMimeMessage();

      //true表示需要创建一个multipart message
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(SENDER);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content, true);

      FileSystemResource file = new FileSystemResource(new File(filePath));
      String fileName = file.getFilename();
      helper.addAttachment(fileName, file);

      mailSender.send(message);
   }

   /**
    * 发送带静态文件的邮件
    *
    * @param to       收件人
    * @param subject  主题
    * @param content  内容
    * @param rscIdMap 需要替换的静态文件
    */
   public void sendMimeMessage(String to, String subject,
                               String content, Map<String, String> rscIdMap)
      throws MessagingException
   {
      MimeMessage message = mailSender.createMimeMessage();

      //true表示需要创建一个multipart message
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(SENDER);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content, true);

      for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
         FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
         helper.addInline(entry.getKey(), file);
      }

      mailSender.send(message);
   }

   private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
}
