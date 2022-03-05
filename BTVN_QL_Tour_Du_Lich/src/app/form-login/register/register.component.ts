import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {SignUpForm} from "../../model/SignUpForm";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  status = "";
  form: any = {};
  hide = true;
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email
  ])
  signUpForm!: SignUpForm;
  error1: any = {
    message: "username_existed"
  }
  error2: any = {
    message: "email_existed"
  }
  success: any = {
    message: "yes"
  }

  getUrl()
  {
    return "url('https://media.doisongphapluat.com/media/tran-thi-bich-thao/2022/02/23/jennie-blackpink-khoe-ve-dep-thoi-thuong-trong-bo-anh-quang-cao-moi1.jpg')";
  }

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  ngSubmit() {
    this.signUpForm = new SignUpForm(
      this.form.name,
      this.form.username,
      this.form.email,
      this.form.password
    )
    this.authService.signUp(this.signUpForm).subscribe(data => {
      console.log("data == ", data);
      if (JSON.stringify(data) == JSON.stringify(this.error1)) {
        this.status = 'The username is existed! Please try again!'
      }
      if (JSON.stringify(data) == JSON.stringify(this.error2)) {
        this.status = 'The email is existed! Please try again!'
      }
      if (JSON.stringify(data) == JSON.stringify(this.success)) {
        this.status = 'Create account success!'
      }
    })
  }
}
