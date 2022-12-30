import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";
import {FileService} from "./file.service";

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { AddArtefactComponent } from './add-artefact/add-artefact.component';
import { ListScoreComponent } from './list-score/list-score.component';
import { ScoreComponent } from './score/score.component';
import { ArtefactComponent } from './artefact/artefact.component';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    AddArtefactComponent,
    ListScoreComponent,
    ScoreComponent,
    ArtefactComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
  ],
  providers: [FileService],
  bootstrap: [AppComponent]
})
export class AppModule {}
