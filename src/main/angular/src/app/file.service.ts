import { Injectable } from "@angular/core";
import {HttpClient, HttpEvent} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({providedIn: 'root'})
export class FileService{
  private server = 'http://localhost:8080';
  constructor(private http: HttpClient){}

  upload(file: File): Observable<any>{
    // Create form data
    const formData = new FormData();

    // Store form name as "file" with file data
    formData.append("file", file, file.name);

    // Make http post request over api
    // with formData as req
    return this.http.post(`${this.server}/file/upload`, formData)
  }

  //function to upload file
  /*
  upload(formData: FormData): Observable<HttpEvent<string[]>>{
    return this.http.post<string[]>(`${this.server}/file/upload`, formData,{
      reportProgress: true,
      observe: 'events'
    })
  }*/
}
