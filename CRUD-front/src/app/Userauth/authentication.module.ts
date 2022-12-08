import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms'
import {ReactiveFormsModule} from '@angular/forms';
import {AuthenticationService} from './authentication.service'
import {HttpClientModule} from '@angular/common/http';
import { LoginComponent } from '../Pages/login/login.component';
import { RegisterComponent } from '../Pages/register/register.component';

const authRouter: Routes = [
  {
    path:'',
    children: [
      {
        path: 'register',
        component: RegisterComponent,
      },
      {
        path: '',
        component: LoginComponent,
      },

    ]
  }
];
@NgModule({
  declarations: [LoginComponent, RegisterComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(authRouter),

    FormsModule,
    HttpClientModule,
    ReactiveFormsModule

  ],
  providers:[AuthenticationService],
  exports: [
    RouterModule,
    RegisterComponent,
    LoginComponent,

  ]
})
export class AuthenticationModule { }
