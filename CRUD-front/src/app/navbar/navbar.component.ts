import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../Userauth/authentication.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  userDetails = null;
  constructor(public router:Router, public auth: AuthenticationService) { 
    // this.userDetails = JSON.parse(localStorage.getItem('username')!) && JSON.parse(localStorage.getItem('name')!);
  }

  ngOnInit(): void {
  }

  logout()
  {
    this.auth.deleteToken();
    // window.location.reload();
    this.router.navigate([''])

  }
  
}
