import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {routing} from './app.route';
import {FormsModule} from "@angular/forms";
import {InputsComponent} from "./inputs.component";

@NgModule({
  declarations: [
    AppComponent, InputsComponent
  ],
  imports: [
    BrowserModule,
    routing,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule {
}
