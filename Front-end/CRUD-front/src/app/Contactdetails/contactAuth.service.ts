import { Contact } from './Contact';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ContactAuthService {
  authServiceEndpoint:string = "http://localhost:8081/contacts";
  

    constructor(private http: HttpClient) { }
    getContacts(){
      const url = `${this.authServiceEndpoint}/get`;
      console.log(url);
      return this.http.get(url);
    }
    addContact(contact:any){
      const url = `${this.authServiceEndpoint}/add`;
      console.log(url);
      return this.http.post(url, contact);
    }
    deleteContact(id:any){
      return this.http.delete(`${this.authServiceEndpoint}/delete?id=` + id);
    }
    deleteAllContact(){
      const url = `${this.authServiceEndpoint}/deleteAll`;
      console.log(url);
      return this.http.delete(url);
    }
    updateContact(contact:any){
      const url = `${this.authServiceEndpoint}/update`;
      console.log(url);
      return this.http.put(url, contact);
    }
   
}
