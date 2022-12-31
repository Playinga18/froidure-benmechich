import {Injectable} from "@angular/core";
import { HttpClient } from '@angular/common/http';

import { Artefact} from "./artefact.model";
import {map, Observable} from "rxjs";

const baseUrl = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})

export class ArtefactService {
  constructor(private http: HttpClient) {}

  getArtefact(id: number): Observable<Artefact>{
    return this.http.get<Artefact>(`baseUrl/${id}`).pipe(
      map((artefact: any) => new Artefact(artefact))
    );
  }
}

