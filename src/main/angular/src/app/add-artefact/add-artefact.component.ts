import { Component, OnInit} from "@angular/core";
import { HttpEventType, HttpResponse} from "@angular/common/http";
import { Observable } from "rxjs";
import { takeLast, map } from 'rxjs/operators';
import { FileUploadService} from "../file.service";

@Component({
  selector: 'app-add-artefact',
  templateUrl: './add-artefact.component.html',
  styleUrls: [ './add-artefact.component.css' ]
})

export class AddArtefactComponent implements OnInit {
  selectedFiles?: FileList | null;
  currentFile?: File;
  progress = 0;
  message = '';

  fileInfos?: Observable<any>;

  constructor(private uploadService: FileUploadService) { }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  upload(): void {
    this.progress = 0;

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        this.uploadService.upload(this.currentFile).subscribe({
          next: (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.uploadService.getFiles();
            }
          },
          error: (err: any) => {
            console.log(err);
            this.progress = 0;

            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }

            this.currentFile = undefined;
          }
        });
      }

      this.selectedFiles = undefined;
    }
  }

  ngOnInit(): void {
    this.fileInfos = this.uploadService.getFiles().pipe(
      takeLast(5),
      map(files => files.reverse())
    );
  }

}
