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

import { EventConstants } from "../constants/url/event-constants";
import { ProjectEvent } from "./project-event";
import Frame = Stomp.Frame;

const SockJS = require("sockjs-client");
const Stomp = require('stompjs');

export class SocketClient {
   stompClient: any;

   constructor(private endpoint: string) {
      const socket = new SockJS(resolveURL(this.endpoint));
      this.stompClient = Stomp.over(socket);

      this.stompClient.maxWebSocketFrameSize = 64 * 1024;
      this.stompClient.debug = null;

      this.stompClient.connect({}, (frame) => {
         console.log("Connected: " + frame);
         // TODO receive command
         this.stompClient.subscribe(EventConstants.NOTIFY_ALL_TOPIC, (messagae: Frame) => {
            alert(messagae.body);
            console.log("======message===========", messagae);
         });
      }, (error) => {
         console.log("WebSocket Error: ", error);
      });
   }

   send(url: string, header: any, event: ProjectEvent): void {
      let dto: {[name: string]: string} = {};

      for(let property in event) {
         if(event.hasOwnProperty(property)) {
            let value: any = event[property];

            if((typeof value) !== "function") {
               dto[property] = value;
            }
         }
      }

      const body = JSON.stringify(dto);

      this.stompClient.send(EventConstants.APP_EVENT_PREFIX + url, header, body);
   }

}

function resolveURL(url: string): string {
   let base: HTMLBaseElement = <HTMLBaseElement> window.document.querySelector("base");
   let baseHref: string = base.href.replace(/\/$/, "");
   return baseHref + "/" + url;
}
