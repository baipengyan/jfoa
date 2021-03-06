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

import { Component, NgZone, OnDestroy, OnInit } from "@angular/core";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { NotifyAllClientService } from "./common/client/notify-all-client.service";
import { ComponentTool } from "./common/util/component-tool";
import { BaseSubscription } from "./widget/base/BaseSubscription";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent extends BaseSubscription implements OnInit, OnDestroy {
   notification: string;

   constructor(private zone: NgZone,
               private modalService: NgbModal,
               private notifyService: NotifyAllClientService)
   {
      super();

      this.subscriptions.add(notifyService.getMessageChannel().onReceiveMessage().subscribe((message: string) => {
         if(this.notification == message || !!!message) {
            return;
         }

         this.notification = message;

         if(!!this.notification) {
            this.zone.runTask(() => {
               ComponentTool.showMessageDialog(this.modalService, "Notification", this.notification)
                  .then(() =>
                  {
                     this.notification = null;
                  });
            });
         }
      }));
   }

   ngOnInit(): void {
      document.body.className += " app-loaded";
   }
}
