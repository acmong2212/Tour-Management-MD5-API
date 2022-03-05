import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterComponent} from "./form-login/register/register.component";

const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  {
    path: 'tour', loadChildren: ()=> import('./tour-management/tour-management.module').then(module => module.TourManagementModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
