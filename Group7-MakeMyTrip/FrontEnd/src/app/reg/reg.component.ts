import { Component, OnInit } from '@angular/core';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';
import { PasswordValidator } from './password.validator';
import { UserAccount } from '../userAccount';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-reg',
  templateUrl: './reg.component.html',
  styleUrls: ['./reg.component.css']
})
export class RegComponent implements OnInit {
  title = 'Make My Trip registration page';
  submitted = false;
  response: any;
  userAccount: UserAccount = new UserAccount();
  constructor(private fb: FormBuilder, private route: Router, private userService: UserService) {}
  regForm: FormGroup;
  ngOnInit() {

/*     emailId: string;
    password: string;
    passwordConfirm: string;
    userName: string;
    mobileNo: number;
 */

    this.regForm = this.fb.group({
      userName: ['', [Validators.required, Validators.maxLength(20), Validators.minLength(3) ]],
      /* email:['',[ Validators.required,Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')] ], */
      emailId: ['', [ Validators.required, Validators.pattern('^[a-zA-Z0-9_.+-]+@gmail.com+$')] ],
      mobileNo: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern('[6-9]\\d{9}')]],
      password: ['', [ Validators.required, Validators.maxLength(15), Validators.minLength(6),
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')] ],
        passwordConfirm: ['', Validators.required ]
   });
 }

 /* get f() { return this.regForm.controls; } */




 onRegiter() {
this.submitted = true;
  console.log ( 'in register() method');
  console.log(this.userAccount);
  this.userService.createUser(this.userAccount)
      .subscribe((response) => {
        this.response = response;
        console.log(this.response); });
    /*   this.route.navigateByUrl("login");
   this.userAccount = new UserAccount();
    console.log(this.userAccount); */


 }

 onLogin() {
  console.log(this.response.userName);
  this.route.navigateByUrl('login');

 }



  }



/*   [‎1/‎18/‎2019 4:55 PM]  Carakatti, Prathyusha:
save() {
this.supportService.createCustomer(this.user)
.subscribe(data => console.log(data), error => console.log(error));
console.log(this.user)
this.user = new User();
}  */

/* import { Component } from '@angular/core';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular Form Validation Tutorial';
   regForm: FormGroup;
   constructor(private fb: FormBuilder) {
    this.createForm();
  }
   createForm() {
    this.regForm = this.fb.group({
       name: ['', Validators.required ]
    });
  }
}
 */




/* import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormBuilder } from '@angular/forms';

import { PasswordValidator } from './password.validator';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  registrationForm: FormGroup;
  constructor(private fb: FormBuilder) { }

  ngOnInit() {
     this.registrationForm = this.fb.group( {
      password: [''],
      confirmPassword: ['']
    } , { validator: PasswordValidator })

  } */
