import { LoginComponent } from './../Pages/login/login.component';
import { User } from './../Userauth/user';
import { Contact } from './../Contactdetails/Contact';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../Userauth/authentication.service';


@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})

export class UpdateComponent{
  constructor(public router:Router, public auth: AuthenticationService,private authService: AuthenticationService,) { }
  contactUpdate={
    name : this.auth.getNameFromToken(),
    username : this.auth.getUser(),
    password: this.auth.getLoginPassword(),

  }
  
  ngOnInit(): void {
    
  }
  visible:boolean = true;
  type:boolean = true;
  viewPassword(){
    this.visible = !this.visible;
    this.type = !this.type;
  }
  userDetails= this.auth.getNameFromToken();
  userDetails1 = this.auth.getUser();
  update(contact:any){
    this.contactUpdate=contact;
  }
  onSubmit(){
    this.auth.updateUser(this.contactUpdate).subscribe((data:any)=>{
      alert("User Updated Successfully");
      this.auth.setName(this.contactUpdate.name);
      this.auth.setUserName(this.contactUpdate.username);
      this.router.navigate(['dashboard']);
    },error =>{
      console.log("some problem",error);
    });
  }
}


