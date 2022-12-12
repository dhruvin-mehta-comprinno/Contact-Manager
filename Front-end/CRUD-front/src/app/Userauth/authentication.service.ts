import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'; 
import { Observable } from 'rxjs';
import { User } from './user';
// import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  authServiceEndpoint1:string = "http://localhost:8082/users";
  authServiceEndpoint:string = "http://localhost:8082/auth";
  requestHeaders = new HttpHeaders({
    "No-Auth":"True"});

  constructor(private http: HttpClient) { }

  registerUser(newUser:any) {
    const url = `${this.authServiceEndpoint}/register`;
    console.log(url);
    return this.http.post(url, newUser, {responseType: 'text'});
  }
  

  deleteToken() {
    // return localStorage.removeItem("accessToken");
    localStorage.clear();
   
  }
  setToken(accessToken:string) {
    return localStorage.setItem("accessToken", accessToken);
  }
  setUserName(username:any){
    localStorage.setItem('username',username);
  }
  setName(name:any){
    localStorage.setItem('name',name);
  }
  
  getToken() {
    return localStorage.getItem("accessToken");
  }
  getUserName(){
    localStorage.getItem('username');
  }
  getName(){
    localStorage.getItem('name');
  }
  loginUser(newUser:any) {
    const url = `${this.authServiceEndpoint}/login`;
    console.log(url);
    return this.http.post(url, newUser, {headers: this.requestHeaders});
  }
  public getUser(){
    let user1=localStorage.getItem('username');
    return user1;
  }
  public getNameFromToken(){
    let user1=localStorage.getItem('name');
    return user1;
  }
  updateUser(newUser:any) {
    const url = `${this.authServiceEndpoint1}/update`;
    console.log(url);
    return this.http.put(url, newUser);
  }
  setPassword(password:any){
    sessionStorage.setItem('password',password);
  }
  
  getPassword() {
    return sessionStorage.getItem("password");
  }
  getLoginPassword(){
    let user1 = sessionStorage.getItem('password');
    return user1;
  }

}
