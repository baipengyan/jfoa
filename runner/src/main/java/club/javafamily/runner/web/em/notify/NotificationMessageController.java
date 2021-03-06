/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 *
 * The software and information contained herein are copyrighted and
 * proprietary to JavaFamily Technology Corp. This software is furnished
 * pursuant to a written license agreement and may be used, copied,
 * transmitted, and stored only in accordance with the terms of such
 * license and with the inclusion of the above copyright notice. Please
 * refer to the file "COPYRIGHT" for further copyright and licensing
 * information. This software and information or any other copies
 * thereof may not be provided or otherwise made available to any other
 * person.
 */

package club.javafamily.runner.web.em.notify;

import club.javafamily.runner.service.NotificationService;
import club.javafamily.runner.web.em.notify.event.NotifyAllEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationMessageController {

   @MessageMapping("/notification/all")
   public void notify(@Payload NotifyAllEvent event) {
      notificationService.sendNotification(event.getMessage());
   }

   @Autowired
   public NotificationMessageController(NotificationService notificationService) {
      this.notificationService = notificationService;
   }

   private final NotificationService notificationService;
}
