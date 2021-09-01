import { Component } from '@angular/core';
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mainApplication';
  public topMenuItems: MenuItem[] = [];
  public leftMenuItems: MenuItem[] = [];

  ngOnInit() {
    this.topMenuItems = [
      {
        label:'File',
        icon:'pi pi-fw pi-file',
        items:[
          {
            label:'Download',
            icon:'pi pi-fw pi-download',
            items:[
              {
                label:'Resume',
                icon:'pi pi-fw pi-book'
              }
            ]
          },
          {
            separator:true
          },
        ]
      },
      {
        label:'Users',
        icon:'pi pi-fw pi-user',
        items:[
          {
            label:'New',
            icon:'pi pi-fw pi-user-plus',

          },
          {
            label:'Delete',
            icon:'pi pi-fw pi-user-minus',

          },
          {
            label:'Search',
            icon:'pi pi-fw pi-users',
            items:[
              {
                label:'Filter',
                icon:'pi pi-fw pi-filter',
                items:[
                  {
                    label:'Print',
                    icon:'pi pi-fw pi-print'
                  }
                ]
              },
              {
                icon:'pi pi-fw pi-bars',
                label:'List'
              }
            ]
          }
        ]
      },
      {
        label:'Media Markt',
        icon:'pi pi-fw pi-shopping-cart'
      }
    ];
    this.leftMenuItems = [
      {
        label:'This sidemenu',
        icon:'pi pi-fw pi-file',
      },
      {
        label:'should change',
        icon:'pi pi-fw pi-pencil',
      },
      {
        label:'according to the',
        icon:'pi pi-fw pi-pencil',
      },
      {
        label:'clicked button' ,
        icon:'pi pi-fw pi-user',
      },
      {
        label:'of the top menubar' ,
        icon:'pi pi-fw pi-user',
      },
    ];
  }
}
