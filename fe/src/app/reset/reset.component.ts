import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ResetService } from './reset.service';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.scss']
})
export class ResetComponent implements OnInit {

  public password: string = "";
  public validate: string = "";
  private token: string = "";
  public lowerColor: string = "#000000";
  public upperColor: string = "#000000";
  public numColor: string = "#000000";
  public specialColor: string = "#000000";
  public countColor: string = "#000000";
  public badColor: string = "#000000";
  public matchColor: string = "#000000";
  public badChar: boolean = false;
  public reset: boolean = false;
  public status: string;

  constructor(private route: ActivatedRoute,
      private resetService: ResetService) {
    this.token = this.route.snapshot.paramMap.get('token');
  }

  ngOnInit(): void {
    this.updateReset();
    this.resetService.getReset(this.token).then(c => {
      this.status = "valid";
    }).catch(err => {
      if(err.status == 410) {
        this.status = "expired";
      }
      if(err.status == 404) {
        this.status = "notfound";
      }
      console.log(err);
    });
  }

  public onReset() {
    this.reset = false;
    this.resetService.setReset(this.token, this.password).then(() => {
      this.status = "reset";
    }).catch(err => {
      console.log(err);
      window.alert("failed to reset password");
    });
  }

  public updatePassword() {
    this.updateReset();
  }

  public clearPassword() {
    this.password = "";
    this.updateReset();
  }

  public clearValidate() {
    this.validate = "";
    this.updatePassword();
  }

  public updateReset() {

    if(this.password == "") {
      this.reset = false;
      this.lowerColor = "#ED5A56";
      this.upperColor = "#ED5A56";
      this.specialColor = "#ED5A56";
      this.numColor = "#ED5A56";
      this.countColor = "#ED5A56";
      this.badColor = "#000000";
      this.matchColor = "#000000";
    }
    else {

      // init next flag
      this.reset = true;

      // test for lower case character
      var lower = /^(?=.*[a-z])/
      if(lower.test(this.password)) {
        this.lowerColor = "#000000";
      }
      else {
        this.lowerColor = "#ED5A56";
        this.reset = false;
      }

      // test for upper case character
      var upper = /^(?=.*[A-Z])/
      if(upper.test(this.password)) {
        this.upperColor = "#000000";
      }
      else {
        this.upperColor = "#ED5A56";
        this.reset = false;
      }

      // test for number character
      var num = /^(?=.*[0-9])/
      if(num.test(this.password)) {
        this.numColor = "#000000";
      }
      else {
        this.numColor = "#ED5A56";
        this.reset = false;
      }

      // test for special character
      var special = /^(?=.*[!@#\$%\^&])/
      if(special.test(this.password)) {
        this.specialColor = "#000000";
      }
      else {
        this.specialColor = "#ED5A56";
        this.reset = false;
      }

      // test for number of characters
      var count = /^(?=.{8,})/
      if(count.test(this.password)) {
        this.countColor = "#000000";
      }
      else {
        this.countColor = "#ED5A56";
        this.reset = false;
      }

      // test if contains other characters
      var other = /^[0-9a-zA-Z!@#\$\^&]+$/;
      if(this.password.length == 0 || other.test(this.password)) {
        this.badColor = "#000000";
      }
      else {
        this.badColor = "#ED5A56";
        this.reset = false;
      }

      // test if passowrds dont match
      if(this.password == this.validate) {
        this.matchColor = "#000000";
      }
      else {
        this.matchColor = "#ED5A56";
        this.reset = false;
      }
    }
  }

}
