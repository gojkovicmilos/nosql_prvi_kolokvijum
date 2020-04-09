import { FilesService } from './../files.service';
import { Component, OnInit } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';

@Component({
  selector: 'app-file-manager',
  templateUrl: './file-manager.component.html',
  styleUrls: ['./file-manager.component.css']
})
export class FileManagerComponent implements OnInit {

  constructor(private FileService: FilesService) { }
  private files = [];
  private url = 'http://localhost:27017/files/upload';
  private uploader: FileUploader;

  ngOnInit() {
    this.uploader = new FileUploader({url: this.url});

    this.FileService.showFileNames().subscribe(response => {
      for (let i in response) {
        this.files[i] = {
          filename: response[i].filename,
          originalname: response[i].originalname,
          contentType: response[i].contentType
        };
      }
    });
  }

  downloadPdf(filename, contentType) {
    this.FileService.downloadPDF(filename, contentType).subscribe(
      (res) => {
        const file = new Blob([res.blob()], { type: contentType });
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL);
      }
    );
  }

}
