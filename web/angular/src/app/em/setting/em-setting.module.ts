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

import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatListModule } from "@angular/material/list";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatSelectModule } from "@angular/material/select";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatTabsModule } from "@angular/material/tabs";
import { WidgetModule } from "../../widget/widget.module";
import { ClientManagerComponent } from "./client-manager/client-manager.component";
import { EmSettingRoutingModule } from "./em-setting-routing.module";
import { EmSettingComponent } from "./em-setting.component";

@NgModule({
   declarations: [
      EmSettingComponent,
      ClientManagerComponent
   ],
   imports: [
      CommonModule,
      FormsModule,
      ReactiveFormsModule,
      EmSettingRoutingModule,
      MatButtonModule,
      MatSnackBarModule,
      MatSidenavModule,
      MatInputModule,
      MatListModule,
      MatIconModule,
      MatTabsModule,
      MatCardModule,
      MatSelectModule,
      MatProgressSpinnerModule,
      WidgetModule
   ],
   entryComponents: [
   ],
   providers: [
   ]
})
export class EmSettingModule {
}
