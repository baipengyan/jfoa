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

import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SearchResultResolver } from "../search-result/search-result-resolver.service";
import { SearchResultComponent } from "../search-result/search-result.component";
import { AuditViewComponent } from "./audit/audit-view.component";
import { EmMonitorComponent } from "./em-monitor.component";

const appRoutes: Routes = [
   {
      path: "",
      component: EmMonitorComponent,
      children: [
         {
            path: "audit",
            component: AuditViewComponent
         },
         {
            path: "search",
            component: SearchResultComponent,
            resolve: {
               searchResults: SearchResultResolver
            },
            runGuardsAndResolvers: "paramsOrQueryParamsChange",
         },
         {
            path: "**",
            redirectTo: "audit"
         }
      ]
   }
];

@NgModule({
   imports: [
      RouterModule.forChild(appRoutes)
   ],
   exports: [
      RouterModule
   ]
})
export class EmMonitorRoutingModule {
}
