import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'
import { CommonModule } from '@angular/common'
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';

import { ResetRoutingModule } from './reset-routing.module';
import { ResetComponent } from './reset.component';
import { ResetService } from './reset.service';

@NgModule({
  declarations: [
    ResetComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    MatButtonModule,
    MatInputModule,
    MatDialogModule,
    MatIconModule,
    HttpClientModule,
    ResetRoutingModule
  ],
  providers: [
    ResetService
  ],
  bootstrap: []
})
export class ResetModule { }
