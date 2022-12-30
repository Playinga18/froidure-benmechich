import { Component } from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpEventType} from "@angular/common/http";

import {FileService} from "../file.service";

@Component({
  selector: 'app-add-artefact',
  templateUrl: './add-artefact.component.html',
  styleUrls: ['./add-artefact.component.css']
})

export class AddArtefactComponent {
filenames: string[] = [];
  fileStatus = { status: '', requestType: '', percent: 0};
  files: File[] = [];
  shortLink: string = "";
  loading: boolean = false; // Flag variable
  title: string = "hello";

  constructor(private fileService: FileService){}

  ngOnInit(): void {
  }

  onUploadFiles(): void{
    const formData: FormData = new FormData();
    console.log("hey");

    for (let file in this.files) {
      console.log(file);
      /* this.fileService.upload(file).subscribe(
         (event: any) => {
           if (typeof (event) === 'object') {
             // Short link via api response
             this.shortLink = event.link;
             this.loading = false; // Flag variable
           }
         }
       );*/
    }
  }

  //upload file
  addFile(event: Event): void{
    const target = event.target as HTMLInputElement;
    const fileList = target.files as FileList;
    console.log(fileList);

    for (let i = 0; i < fileList.length; i++) {
      let file: File = fileList.item(i) as File;
      console.log("file " + file);
      this.files.push(file);
    }
  }

  private resportProgress(httpEvent: HttpEvent<string[]>) {

    switch (httpEvent.type){
      case HttpEventType.UploadProgress:
        this.uploadStatus(httpEvent.loaded, httpEvent.total!, 'Uploading');
        break;
      case HttpEventType.ResponseHeader:
        console.log('Header returned', httpEvent);
        break;
      case HttpEventType.Response:
        if(httpEvent.body instanceof Array){
          for(const filename of httpEvent.body){
            this.filenames.unshift(filename);
          }
        }
        this.fileStatus.status = 'done';
        break;

      default:
        console.log(httpEvent);
    }
  }

  private uploadStatus(loaded: number, total: number,  requestType: string) {
    this.fileStatus.status = 'progress';
    this.fileStatus.requestType = requestType;
    this.fileStatus.percent = Math.round(100 * loaded / total);
  }

  onDownloadFile(filename: string) {
  }
}
