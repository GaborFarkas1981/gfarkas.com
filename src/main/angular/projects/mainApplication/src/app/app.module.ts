import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MenubarModule} from "primeng/menubar";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {PanelMenuModule} from "primeng/panelmenu";
import {MegaMenuModule} from "primeng/megamenu";
import {MenuModule} from "primeng/menu";
import {SlideMenuModule} from "primeng/slidemenu";
import {TabMenuModule} from "primeng/tabmenu";
import {TieredMenuModule} from "primeng/tieredmenu";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MenubarModule,
    BrowserAnimationsModule,
    InputTextModule,
    ButtonModule,
    PanelMenuModule,
    MegaMenuModule,
    MenuModule,
    SlideMenuModule,
    TabMenuModule,
    TieredMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
