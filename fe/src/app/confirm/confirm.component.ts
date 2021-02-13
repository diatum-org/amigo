import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConfirmService } from './confirm.service';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.scss']
})
export class ConfirmComponent implements OnInit {

  public status: string;
  private token: string;
  public mode: string;

  constructor(private route: ActivatedRoute,
      private confirmService: ConfirmService) {
    this.route.queryParams.subscribe(params => {
      if(params.email != null) {
        this.mode = "email address";
        this.token = params.email;
      }
      if(params.phone != null) {
        this.mode = "phone number";
        this.token = params.phone;
      }
    });
  }

  ngOnInit(): void {
    this.confirmService.getConfirmation(this.token).then(c => {
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
  
  public onConfirm() {
    this.confirmService.setConfirmation(this.token).then(() => {
      this.status = "confirmed";
    }).catch(err => {
      window.alert("failed to confirm contact method");
      console.log("confirm");
    });
  }
}
