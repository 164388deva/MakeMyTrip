import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegComponent } from './reg/reg.component';
import { ProductComponent } from './product/product.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [{path:'login', component:LoginComponent},
{ path:'reg', component:RegComponent},
{path:'',component:ProductComponent},{path:'home',component:HomeComponent}];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})


export class AppRoutingModule { }
export const routingComponents=[LoginComponent,RegComponent,ProductComponent,HomeComponent]
