import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { AddArtefactComponent } from "./add-artefact/add-artefact.component";
import { ListScoreComponent } from './list-score/list-score.component';
import { ScoreComponent } from './score/score.component';
import { ArtefactComponent } from './artefact/artefact.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    AddArtefactComponent,
    ListScoreComponent,
    ScoreComponent,
    ArtefactComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
