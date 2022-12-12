import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/Userauth/authentication.service';
import { User } from 'src/app/Userauth/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user!: User;
  loginForm!: FormGroup;
  constructor(private authService: AuthenticationService,
    private router: Router,private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      "username": new FormControl('', [Validators.required]),
      "password": new FormControl('', [Validators.required]),

      })

  }
  visible:boolean = true;
  type:boolean = true;
  viewPassword(){
    this.visible = !this.visible;
    this.type = !this.type;
  }
  loginUser(){
    if (this.loginForm.invalid) {
      
      return;
    };
    this.user = new User(null, this.loginForm.get("username")?.value, this.loginForm.get("password")?.value);
    console.log("Login User data:", this.user);
    this.authService.setPassword(this.user.password);
    this.authService.loginUser(this.user).subscribe((data:any)=>{
      this.authService.setToken(data.accessToken);
      this.authService.setUserName(data.username);
      this.authService.setName(data.name);
      

      // console.log(data.name);
      // console.log(data.username);
      // console.log(data.accessToken);
      // console.log(data);
      alert("Login Successful");
        
        

        this.router.navigate(['dashboard']);



      

    },error =>{
      alert("Invalid Credentials");
      console.log("some problem",error);
    });



  }


}
