import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ScoreComponent } from './score/score.component';
import { ArtefactComponent } from './artefact/artefact.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'artefact/:artefactId', component: ArtefactComponent },
  { path: 'score/:scoreId', component: ScoreComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
