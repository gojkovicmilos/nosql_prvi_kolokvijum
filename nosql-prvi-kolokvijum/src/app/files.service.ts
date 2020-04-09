import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable()
export class FilesService {

  constructor(private http: HttpClient) { }

    downloadPDF(filename, filetype): any {
    return this.http.get('localhost:27017/files/' + filename,
    { responseType: 'blob'});
  }

  showFileNames() {
    return this.http.get('localhost:27017/files/');
  }
}