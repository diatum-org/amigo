import { Injectable, Type, Component } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpEvent } from '@angular/common/http';
import { HttpUrlEncodingCodec } from '@angular/common/http';

import { Contact } from './contact';

@Injectable()
export class ConfirmService {

  private headers: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
  }

  public getConfirmation(token: string): Promise<Contact> {
    return this.httpClient.get<Contact>("confirm/account?token=" + token,
        { headers: this.headers, observe: 'body' }).toPromise();
  }

  public setConfirmation(token: string): Promise<void> {
    return this.httpClient.put<void>("confirm/account?token=" + token,
        { headers: this.headers, observe: 'body' }).toPromise();
  }

}


