
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {ContactAuthService } from '../Contactdetails/contactAuth.service';
import {Contact} from '../Contactdetails/Contact';


@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  contact!: Contact;
  addForm!: FormGroup;
  constructor(private authService: ContactAuthService,
    private router: Router,private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      "name":new FormControl('',[ Validators.required]),
      "lastName":new FormControl('',[ Validators.required]),
      "email":new FormControl('',[ Validators.required]),
      "contactNumber":new FormControl('',[ Validators.required]),

      })

  }
  addContact() {
    if (this.addForm.invalid) {
      return;
    };
    this.contact = new Contact(this.addForm.get("name")?.value, this.addForm.get("lastName")?.value, this.addForm.get("email")?.value, this.addForm.get("contactNumber")?.value);
    console.log("Added data:", this.contact);
    this.authService.addContact(this.contact).subscribe((data: any) => {
      console.log("Contact added", data);
      alert("Contact added");
      
      

      this.router.navigate(['dashboard']);
    },(error: any) =>{
      console.log("Error in registering user", error);
    });


}

}
