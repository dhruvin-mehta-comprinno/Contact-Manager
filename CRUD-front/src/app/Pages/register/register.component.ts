import { ResourceLoader } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/Userauth/authentication.service';
import { User } from 'src/app/Userauth/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  newUser!: User;
  registerForm!: FormGroup;
  constructor(private authService: AuthenticationService,
    private router: Router, private formBuilder: FormBuilder) { 
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      "name":new FormControl('',[ Validators.required]),
     "username":new FormControl('',[ Validators.required]),
      "password":new FormControl('',[ Validators.required]),

      })
  }

  visible:boolean = true;
  type:boolean = true;
  viewPassword(){
    this.visible = !this.visible;
    this.type = !this.type;
  }

  registerUser() {
    if (this.registerForm.invalid) {
      return;
    };
    this.newUser = new User(this.registerForm.get("name")?.value, this.registerForm.get("username")?.value, this.registerForm.get("password")?.value);
    // console.log("Register User data:", this.newUser);
    this.authService.registerUser(this.newUser).subscribe(data => {
      
      alert("User registered");
      
      

      this.router.navigate(['']);
    },error =>{
      console.log("Error in registering user", error);
    });


}

}
