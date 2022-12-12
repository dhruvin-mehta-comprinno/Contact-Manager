import { AuthenticationService } from 'src/app/Userauth/authentication.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Userauth/user';
import { ContactAuthService } from '../Contactdetails/contactAuth.service';



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  page:number=1;
  contactUpdate={
    name:"",
    lastName:"",
    email:"",
    contactNumber:"",

  }

  contactDetails:any;
  constructor(private authService: ContactAuthService, private auth: AuthenticationService,
    private router: Router) {
      this.getUserContacts();
  }
  p:any
  getUserContacts(){
    this.authService.getContacts().subscribe((data:any)=>{

      this.contactDetails = data;

    },(error: any) =>{
      console.log("some problem",error);
    });
  }
  deleteUserContact(contacts:any){
    
    this.authService.deleteContact(contacts.id).subscribe((data:any)=>{

      this.getUserContacts();
      
      alert("Contact Deleted Successfully");
      window.location.href="/dashboard";
    },error =>{
      console.log("some problem",error);
    });
  }
  

  deleteAllUserContact(){
    if(
      this.contactDetails.length==0
    ){
      alert("No Contacts Available");
    }
    else{
    this.authService.deleteAllContact().subscribe((data:any)=>{

      this.getUserContacts();
      alert("All Contacts Deleted Successfully");
      
    },(error:any) =>{
      console.log("some problem",error);
    });
  }
}
  update(contact:any){
    this.contactUpdate=contact;
  }
  updateUserContact(){
    
    this.authService.updateContact(this.contactUpdate).subscribe((data:any)=>{
      
      this.getUserContacts();
      alert("Contact Updated Successfully");
      window.location.href="/dashboard";
    },error =>{
      console.log("some problem",error);
    });
  }


}
