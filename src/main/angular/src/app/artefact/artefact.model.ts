

export class Artefact{
  constructor(artefact: Artefact) {
    this.id = artefact.id;
    this.numVersion = artefact.numVersion;
    this.developer = artefact.developer;
    this.path = artefact.path;
    this.metaData = artefact.metaData;
    this.description = artefact.description;
  }


  id?: any;
  numVersion?: string;
  developer?: string;
  path?: string;
  metaData?: string;
  description?: string;
}
