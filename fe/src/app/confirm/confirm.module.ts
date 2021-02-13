import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'
import { CommonModule } from '@angular/common'
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';

import { ConfirmRoutingModule } from './confirm-routing.module';
import { ConfirmComponent } from './confirm.component';
import { ConfirmService } from './confirm.service';

@NgModule({
  declarations: [
    ConfirmComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    MatButtonModule,
    HttpClientModule,
    ConfirmRoutingModule
  ],
  providers: [
    ConfirmService
  ],
  bootstrap: []
})
export class ConfirmModule { }
