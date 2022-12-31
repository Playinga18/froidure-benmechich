import { Component } from '@angular/core';
import { ArtefactService } from './artefact.service';
import { Artefact } from "./artefact.model";

@Component({
  selector: 'app-artefact-description',
  templateUrl: './artefact.component.html',
  styleUrls: ['./artefact.component.css']
})
export class ArtefactComponent {

  artefact: Artefact | undefined;

  constructor(private artefactService: ArtefactService) {}

  ngOnInit() {
    this.artefactService.getArtefact(this.artefact?.id).subscribe(artefact => {
      this.artefact = artefact;
    });
  }
}
