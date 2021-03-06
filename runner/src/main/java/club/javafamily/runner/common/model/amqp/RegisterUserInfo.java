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

package club.javafamily.runner.common.model.amqp;

import club.javafamily.runner.domain.Customer;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class RegisterUserInfo implements Serializable {
   private String account;
   private String email;
   private String password;
   private String token;
   private String verifyBaseLink;

   public String getAccount() {
      return account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getVerifyBaseLink() {
      return verifyBaseLink;
   }

   public void setVerifyBaseLink(String verifyBaseLink) {
      this.verifyBaseLink = verifyBaseLink;
   }


   public Customer convertModel() {
      Customer user = new Customer();
      user.autoGeneratorName();
      user.setPassword(password);
      user.setRegisterDate(new Date(System.currentTimeMillis()));
      user.setAccount(account);
      user.setEmail(email);

      return user;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      RegisterUserInfo that = (RegisterUserInfo) o;
      return account.equals(that.account) &&
         Objects.equals(password, that.password) &&
         Objects.equals(token, that.token) &&
         Objects.equals(verifyBaseLink, that.verifyBaseLink);
   }

   @Override
   public int hashCode() {
      return Objects.hash(account, password, token, verifyBaseLink);
   }

   @Override
   public String toString() {
      return "RegisterUserInfo{" +
         "account='" + account + '\'' +
         ", password='" + password + '\'' +
         ", token='" + token + '\'' +
         ", verifyBaseLink='" + verifyBaseLink + '\'' +
         '}';
   }
}
