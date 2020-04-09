import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/test/';

const USR_URL = 'http://localhost:8080/api/users/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  getAll() {
    return this.http.get(USR_URL);
  }

  get(id) {
    return this.http.get(`${USR_URL}/${id}`);
  }

  create(data) {
    return this.http.post(USR_URL, data);
  }

  update(id, data) {
    return this.http.put(`${USR_URL}/${id}`, data);
  }

  delete(id) {
    return this.http.delete(`${USR_URL}/${id}`);
  }

  deleteAll() {
    return this.http.delete(USR_URL);
  }

  findByTitle(title) {
    return this.http.get(`${USR_URL}?title=${title}`);
  }
}
