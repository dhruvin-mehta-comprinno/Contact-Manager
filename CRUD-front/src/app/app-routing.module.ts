import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './Pages/login/login.component';
import { RegisterComponent } from './Pages/register/register.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DashboardGuard } from './Userauth/dashboard.guard';
import { AddComponent } from './add/add.component';
import { UpdateComponent } from './update/update.component';

const routes: Routes = [
  { path: 'register', pathMatch: 'full', component: RegisterComponent},
  { path: '', pathMatch: 'full', component: LoginComponent},
  { path: 'dashboard', pathMatch: 'full', component: DashboardComponent, canActivate: [DashboardGuard]},
  { path: 'add', pathMatch: 'full', component: AddComponent, canActivate: [DashboardGuard]},
  { path: 'update', pathMatch: 'full', component: UpdateComponent, canActivate: [DashboardGuard]},
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
